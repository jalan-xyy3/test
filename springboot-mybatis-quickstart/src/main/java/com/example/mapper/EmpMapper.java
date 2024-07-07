package com.example.mapper;

import com.example.model.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

/**
 * ClassName：EmpMapper
 * Description：
 *
 * @Author：xyy3
 * @CreateDate：2024/4/21 21:32
 * @Version: 1.0
 */
@Mapper
public interface EmpMapper {

    @Delete("DELETE FROM emp where id=#{id}")
    public boolean deleteEmp(Integer id);

    @Options(keyProperty = "id", useGeneratedKeys = true)
    @Insert("insert into emp (username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values (#{userName}, #{password}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
    public boolean insertEmp(Emp emp);

    @Update("update emp set username=#{userName},password=#{password},name=#{name},gender=#{gender},image=#{image}," +
            "job=#{job},entrydate=#{entrydate},dept_id=#{deptId},create_time=#{createTime},update_time=#{updateTime} where id=#{id}")
    public boolean updateEmp(Emp emp);

/*    @Select("select id,username, password, name, gender, image, job, entrydate as entryDate, dept_id, create_time, update_time from emp(nolock) where id=#{id}")
    public Emp selectEmpById(Integer id);*/

    @Select("select id,username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time from emp(nolock) where id=#{id}")
    public Emp selectEmpById(Integer id);

    @Select("select id,username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time from emp(nolock) " +
            "where name like CONCAT('%',#{name},'%') and gender=#{gender} and entrydate between #{beginDate} and #{endDate} order by update_time desc")
    public List<Emp> getEmpList(String name, short gender, LocalDate beginDate, LocalDate endDate);

    public List<Emp> list(String name, Short gender, LocalDate beginDate, LocalDate endDate);

    public boolean updateEmp2(Emp emp);

    public boolean deleteEmpByIds(List<Integer> ids);
}
