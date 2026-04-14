--获取班级（对学生）
--获取所有存在教师的班级，用于学生选择班
select c.id, c.name
from class c
         join teacher t on c.id = t.class_id and t.is_delete = false
where c.is_delete = false;

--获取班级（对教师）
--获取所有不存在教师的班级，用于教师选择班
select c.id, c.name
from class c
where c.is_delete = false
  and c.id not in (select t.class_id from teacher t where t.class_id is not null and t.is_delete = false);

--设置学生班级函数
create or replace function set_class_for_student(student_id bigint, class_id bigint)
    returns void
as
$$
update student s
set class_id = $2,
    update_time = now()
where s.id = $1
  and s.is_delete = false
  and ($2 is null or exists (select 1 from class c where c.id = $2 and c.is_delete = false));
$$ language sql volatile;



