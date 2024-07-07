package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * ClassName：Emp
 * Description：
 *
 * @Author：xyy3
 * @CreateDate：2024/4/21 21:32
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
    public Integer id;
    public String userName;
    public String password;
    public String name;
    public Short gender;
    public String image;
    public Short job;
    public LocalDate entrydate;
    public Integer deptId;
    public LocalDateTime createTime;
    public LocalDateTime updateTime;
}
