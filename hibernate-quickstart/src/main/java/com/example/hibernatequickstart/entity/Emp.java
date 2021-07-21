package com.example.hibernatequickstart.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Emp {
    private int empno;
    private String ename;
    private String job;
    private Integer mgp;
    private Timestamp hiredate;
    private BigDecimal sal;
    private BigDecimal comm;
    private Integer deptno;
    private Dept dept;
}
