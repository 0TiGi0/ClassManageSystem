--分页
create or replace function page_student(start int, size int)
    returns table
            (
                id           int,
                name         varchar,
                age          int,
                gender       bool,
                teacher_name varchar,
                class_name   varchar,
                create_time  varchar,
                update_time  varchar
            )
as
$$
select s.id,
       s.name,
       s.age,
       s.gender,
       t.name as teacher_name,
       c.name as class_name,
       s.create_time,
       s.update_time
from student s
         left join class c on s.class_id = c.id and c.is_delete = false
         left join teacher t on t.class_id = c.id and t.is_delete = false
where s.is_delete = false
order by s.id
limit $2 offset $1;
$$
    language sql stable;

--查找
create or replace function query_student(id bigint default null, name varchar default null,
                                         teacher_name varchar default null, class_name varchar default null)
    returns table
            (
                id           int,
                name         varchar,
                age          int,
                gender       bool,
                teacher_name varchar,
                class_name   varchar,
                create_time  varchar,
                update_time  varchar
            )
as
$$
select s.id,
       s.name,
       s.age,
       s.gender,
       t.name as teacher_name,
       c.name as class_name,
       s.create_time,
       s.update_time
from student s
         left join class c on s.class_id = c.id and c.is_delete = false
         left join teacher t on t.class_id = c.id and t.is_delete = false
where s.is_delete = false
  and ($1 is null or s.id = $1)
  and ($2 is null or s.name like '%' || $2 || '%')
  and ($3 is null or t.name like '%' || $3 || '%')
  and ($4 is null or c.name like '%' || $4 || '%')
order by s.id
$$
    language sql stable;

create or replace function create_student(name varchar, age int, gender bool, class_id bigint default null)
    returns void
as
$$
declare
    new_student_id bigint;
begin
    insert into student
        (create_time, update_time, name, age, gender, is_delete)
    values (now()::timestamp,
            now()::timestamp,
            $1,
            $2,
            $3,
            false)
    returning id into new_student_id;
    if class_id is not null then
        perform set_class_for_student(new_student_id, class_id);
    end if;
end;
$$
    language plpgsql volatile;

create or replace function del_student(id bigint)
    returns void
as
$$
update student s
set class_id   = null,
    is_delete  = true,
    update_time = now()
where s.id = $1;
$$
    language sql volatile;

create or replace function update_student(id bigint, name varchar, age int, gender bool, class_id bigint default null)
    returns void
as
$$
begin
    update student s
    set name = $2,
        age = $3,
        gender = $4,
        update_time = now()
    where s.id = $1
      and s.is_delete = false;

    if class_id is not null then
        perform set_class_for_student($1, $5);
    end if;

end;
$$
    language plpgsql volatile;

--函数
select *
from page_student(0, 5);

select *
from query_student();

select create_student('小葵', 20, true, 5);

select del_student(37);

select update_student(1, '亚索', 10, true,6);

