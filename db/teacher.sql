--分页
create or replace function page_teacher(start int, size int)
    returns table
            (
                id          int,
                name        varchar,
                age         int,
                gender      bool,
                class_name  varchar,
                student_num int,
                create_time timestamp,
                update_time timestamp
            )
as
$$
with student_num_table as (
         select c.id, count(s.id) as student_num
         from class c
                  left join student s on s.class_id = c.id and s.is_delete = false
         where c.is_delete = false
         group by c.id)
select t.id,
       t.name,
       t.age,
       t.gender,
       c.name as class_name,
       snt.student_num,
       t.create_time,
       t.update_time
from teacher t
         left join class c on t.class_id = c.id and c.is_delete = false
         left join student_num_table snt on snt.id = c.id
where t.is_delete = false
order by t.id
limit $2 offset $1
$$
    language sql
    stable;

--查找
create or replace function query_teacher(id bigint default null, name varchar default null,
                                         class_name varchar default null)
    returns table
            (
                id          int,
                name        varchar,
                age         int,
                gender      bool,
                class_name  varchar,
                student_num int,
                create_time timestamp,
                update_time timestamp
            )
as
$$
with student_num_table as (
         select c.id, count(s.id) as student_num
         from class c
                  left join student s on s.class_id = c.id and s.is_delete = false
         where c.is_delete = false
         group by c.id)
select t.id,
       t.name,
       t.age,
       t.gender,
       c.name as class_name,
       snt.student_num,
       t.create_time,
       t.update_time
from teacher t
         left join class c on t.class_id = c.id and c.is_delete = false
         left join student_num_table snt on snt.id = c.id
where t.is_delete = false
  and ($1 is null or t.id = $1)
  and ($2 is null or t.name like '%' || $2 || '%')
  and ($3 is null or c.name like '%' || $3 || '%')
order by t.id;
$$
    language sql
    stable;

--创建
create or replace function create_teacher(name varchar, age int, gender bool, class_id bigint default null)
    returns void
as
$$
insert into teacher
    (create_time, update_time, name, age, gender, class_id, is_delete)
values (now()::timestamp, now()::timestamp, $1, $2, $3, $4, false);
$$
    language sql volatile;

create or replace function del_teacher(id bigint)
    returns void
as
$$
begin
    update teacher t
    set is_delete = true,
        class_id = null,
        update_time = now()
    where t.id = $1
      and t.is_delete = false;
end;
$$
    language plpgsql volatile;

create or replace function update_teacher(id bigint, name varchar, age int, gender bool, class_id bigint)
    returns void
as
$$
begin
    update teacher t
    set name        = $2,
        age         = $3,
        gender      = $4,
        update_time = now()
    where t.id = $1
      and t.is_delete = false;

    if $5 is null then
        update teacher t
        set class_id = null,
            update_time = now()
        where t.id = $1
          and t.is_delete = false;
    else
        update teacher t
        set class_id = null,
            update_time = now()
        where t.class_id = $5
          and t.id <> $1
          and t.is_delete = false;

        update teacher t
        set class_id = $5,
            update_time = now()
        where t.id = $1
          and t.is_delete = false;
    end if;
end;
$$
    language plpgsql
    volatile;
-----函数
select *
from page_teacher(0, 3);


select *
from query_teacher(class_name := '班');

select create_teacher('路易斯', 18, true, 3);

select del_teacher(3);

select update_teacher(4,'卡萨丁',80,true,6)





