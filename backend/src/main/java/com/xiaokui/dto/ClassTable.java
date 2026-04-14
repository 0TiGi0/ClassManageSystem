package com.xiaokui.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassTable {
    private Integer id;
    private String name;
    private Integer studentNum;
    private String teacherName;
    private String createTime;
    private String updateTime;
}
