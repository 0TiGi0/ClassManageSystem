package com.xiaokui.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherTable {
    private Integer id;
    private String name;
    private Integer age;
    private Boolean gender;
    private String className;
    private Integer studentNum;
    private String createTime;
    private String updateTime;
}
