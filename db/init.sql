--建表语句
create table if not exists public.class
(
    id          bigserial,
    create_time timestamp   not null,
    update_time timestamp   not null,
    name        varchar(20) not null,
    is_delete   bool        not null default false,
    CONSTRAINT pk_class_id PRIMARY KEY (id),
    CONSTRAINT uq_class_name UNIQUE (name)
);

create table if not exists public.teacher
(
    id          bigserial,
    create_time timestamp   not null,
    update_time timestamp   not null,
    name        varchar(20) not null,
    age         smallint    not null,
    gender      bool        not null,
    class_id    bigint,
    is_delete   bool        not null default false,
    CONSTRAINT pk_teacher_id PRIMARY KEY (id),
    CONSTRAINT uq_teacher_name UNIQUE (name),
    CONSTRAINT uq_teacher_class_id UNIQUE (class_id),
    CONSTRAINT chk_teacher_age CHECK ( age > 0 ),
    CONSTRAINT fk_teacher_class FOREIGN KEY (class_id) REFERENCES public.class (id) ON DELETE SET NULL
);

create table if not exists public.student
(
    id          bigserial,
    create_time timestamp   not null,
    update_time timestamp   not null,
    name        varchar(20) not null,
    age         smallint    not null,
    gender      bool        not null,
    class_id    bigint,
    is_delete   bool        not null default false,
    CONSTRAINT pk_student_id PRIMARY KEY (id),
    CONSTRAINT uq_student_name UNIQUE (name),
    CONSTRAINT chk_student_age CHECK ( age > 0 ),
    CONSTRAINT fk_student_class FOREIGN KEY (class_id) REFERENCES public.class (id) ON DELETE SET NULL
);

--测试数据
INSERT INTO public.class (create_time, update_time, name, is_delete)
VALUES ('2026-04-12 20:37:00', '2026-04-12 20:37:00', '软件工程1班', false),
       ('2026-04-12 20:37:00', '2026-04-12 20:37:00', '计算机科学1班', false),
       ('2026-04-12 20:37:00', '2026-04-12 20:37:00', '人工智能1班', false),
       ('2026-04-12 20:37:00', '2026-04-12 20:37:00', '大数据1班', false),
       ('2026-04-12 20:37:00', '2026-04-12 20:37:00', '网络安全1班', false);

INSERT INTO public.teacher (create_time, update_time, name, age, gender, class_id, is_delete)
VALUES ('2026-04-12 20:37:00', '2026-04-12 20:37:00', '盖伦', 32, true, 1, false),
       ('2026-04-12 20:37:00', '2026-04-12 20:37:00', '卡特琳娜', 28, false, 2, false),
       ('2026-04-12 20:37:00', '2026-04-12 20:37:00', '李青', 35, true, 3, false),
       ('2026-04-12 20:37:00', '2026-04-12 20:37:00', '卡莎', 18, false, NULL, false),
       ('2026-04-12 20:37:00', '2026-04-12 20:37:00', '墨菲特', 800, true, NULL, false);

INSERT INTO public.student (create_time, update_time, name, age, gender, class_id, is_delete)
VALUES
-- 软件工程1班（12人）
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '亚索', 20, true, 1, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '劫', 21, true, 1, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '提莫', 19, true, 1, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '艾希', 20, false, 1, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '金克丝', 18, false, 1, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '拉克丝', 19, false, 1, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '伊泽瑞尔', 20, true, 1, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '阿狸', 21, false, 1, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '锐雯', 20, false, 1, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '慎', 22, true, 1, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '凯南', 19, true, 1, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '德莱厄斯', 22, true, 1, false),

-- 计算机科学1班（12人）
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '德莱文', 21, true, 2, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '薇恩', 20, false, 2, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '锤石', 22, true, 2, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '璐璐', 19, false, 2, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '李白', 20, true, 2, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '韩信', 21, true, 2, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '赵云', 20, true, 2, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '貂蝉', 19, false, 2, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '鲁班七号', 18, true, 2, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '妲己', 20, false, 2, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '安琪拉', 19, false, 2, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '后羿', 21, true, 2, false),

-- 人工智能1班（12人）
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '虞姬', 20, false, 3, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '兰陵王', 22, true, 3, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '王昭君', 20, false, 3, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '程咬金', 22, true, 3, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '狄仁杰', 21, true, 3, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '李元芳', 19, true, 3, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '钟馗', 23, true, 3, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '花木兰', 20, false, 3, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '铠', 21, true, 3, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '百里守约', 20, true, 3, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '瑶', 18, false, 3, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '曜', 19, true, 3, false),

-- 大数据1班（12人，无班主任）
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '澜', 20, true, 4, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '夏洛特', 21, false, 4, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '姬小满', 19, false, 4, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '朵莉亚', 18, false, 4, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '敖隐', 20, true, 4, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '大司命', 21, true, 4, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '祝缘', 19, false, 4, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '科加斯', 22, true, 4, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '阿木木', 18, true, 4, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '捷风', 20, false, 4, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '贤者', 21, false, 4, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '炼狱', 23, true, 4, false),

-- 网络安全1班（12人，无班主任）
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '零', 22, true, 5, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '菲德', 20, false, 5, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '铁臂', 24, true, 5, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '猎枭', 21, true, 5, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '芮娜', 19, false, 5, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '蝰蛇', 22, false, 5, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '夜露', 20, true, 5, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '不死鸟', 21, true, 5, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '芮兹', 20, false, 5, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '丝凯', 19, false, 5, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '恺宙', 22, false, 5, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '暮蝶', 18, false, 5, false),

--无班级学生（5人）
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '大米', 21, true, NULL, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '面条', 20, false, NULL, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '炸鸡', 19, false, NULL, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '汉堡', 22, false, NULL, false),
('2026-04-12 20:37:00', '2026-04-12 20:37:00', '可乐', 18, false, NULL, false);

