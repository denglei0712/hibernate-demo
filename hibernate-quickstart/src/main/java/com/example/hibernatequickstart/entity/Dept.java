package com.example.hibernatequickstart.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Dept {
    private Integer deptno;
    private String dname;
    private String loc;
    private List<Emp> emps;
}
