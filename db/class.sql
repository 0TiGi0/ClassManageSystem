--分页
create or replace function page_class(start int, size int)
    returns table
            (
                id           int,
                name         varchar,
                student_num  int,
                teacher_name varchar,
                create_time  timestamp,
                update_time  timestamp
            )
as
$$
with student_num_table as (
         select c.id, count(s.id) as student_num
         from class c
                  left join student s on s.class_id = c.id and s.is_delete = false
         where c.is_delete = false
         group by c.id),
     teacher_name_table as (
         select c.id, t.name as teacher_name
         from class c
                  left join teacher t on t.class_id = c.id and t.is_delete = false
         where c.is_delete = false)
select c.id,
       c.name,
       coalesce(snt.student_num, 0) as student_num,
       tnb.teacher_name,
       c.create_time,
       c.update_time
from class c
         left join student_num_table snt on c.id = snt.id
         left join teacher_name_table tnb on c.id = tnb.id
where c.is_delete = false
order by c.id
limit $2 offset $1;
$$
    LANGUAGE sql
    STABLE;

--查找
create or replace function query_class(id bigint default null, name varchar default null)
    returns table
            (
                id           int,
                name         varchar,
                student_num  int,
                teacher_name varchar,
                create_time  timestamp,
                update_time  timestamp
            )
as
$$
with student_num_table as (
         select c.id, count(s.id) as student_num
         from class c
                  left join student s on s.class_id = c.id and s.is_delete = false
         where c.is_delete = false
         group by c.id),
     teacher_name_table as (
         select c.id, t.name as teacher_name
         from class c
                  left join teacher t on t.class_id = c.id and t.is_delete = false
         where c.is_delete = false)
select c.id,
       c.name,
       coalesce(snt.student_num, 0) as student_num,
       tnb.teacher_name,
       c.create_time,
       c.update_time
from class c
         left join student_num_table snt on c.id = snt.id
         left join teacher_name_table tnb on c.id = tnb.id
where c.is_delete = false
  and ($1 is null or c.id = $1)
  and ($2 is null or c.name like '%' || $2 || '%')
order by c.id
$$
    LANGUAGE sql
    STABLE;

--获取某班学生
create or replace function all_student(id bigint)
    returns table
            (
                id   int,
                name varchar
            )
as
$$
select s.id,
       s.name
from student s
         join class c on s.class_id = c.id and c.is_delete = false
where s.is_delete = false
  and c.id = $1;
$$
    language sql stable;

--创建
create or replace function create_class(name varchar, teacher_id bigint default null)
    returns void
as
$$
declare
    new_class_id bigint;
begin
    insert into class (create_time, update_time, name, is_delete)
    values (now(), now(), $1, false)
    returning id into new_class_id;

    if teacher_id is not null then
        update teacher
        set class_id = new_class_id,
            update_time = now()
        where id = teacher_id
          and is_delete = false;
    end if;
end;
$$ language plpgsql volatile;

--删除
create or replace function del_class(id bigint)
    returns void
as
$$
declare
    del_class_id bigint;
begin
    update class
    set is_delete = true,
        update_time = now()
    where id = $1
    returning id into del_class_id;

    if del_class_id is not null then
        update teacher
        set class_id = null,
            update_time = now()
        where class_id = del_class_id
          and is_delete = false;

        update student
        set class_id = null,
            update_time = now()
        where class_id = del_class_id
          and is_delete = false;
    end if;
end;
$$
    language plpgsql volatile;

create or replace function update_class(name varchar, id bigint, teacher_id bigint)
    returns void
as
$$
begin
    update class
    set name = $1,
        update_time = now()
    where id = $2
      and is_delete = false;

    if $3 is null then
        update teacher
        set class_id = null,
            update_time = now()
        where class_id = $2
          and is_delete = false;
    else
        update teacher
        set class_id = null,
            update_time = now()
        where class_id = $2
          and id <> $3
          and is_delete = false;

        update teacher
        set class_id = $2,
            update_time = now()
        where id = $3
          and is_delete = false;
    end if;
end;
$$
    language plpgsql volatile;
---------函数
select *
from page_class(0, 2);

select *
from query_class();

select *
from all_student(2);

select create_class('斗罗学院');

select del_class(2);

select update_class('斗罗',2,4);