package com.example;

import com.example.mapper.EmpMapper;
import com.example.mapper.UsersMapper;
import com.example.model.Emp;
import com.example.model.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SpringbootMybatisQuickstartApplicationTests {

    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private EmpMapper empMapper;

    @Test
    public void testGetUsers(){
        List<Users> listUser = usersMapper.getUsers();
        listUser.stream().forEach(user->{
            System.out.println(user);
        });
    }

    @Test
    public void testConnectionGetUsers() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;database=MyBatis;TrustServerCertificate=True";
        String userName = "9798";
        String password = "sa";

        Connection connection = DriverManager.getConnection(url,userName,password);

        String sql = "select id,name,age,gender,phone from USERS with(nolock)";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        List<Users> users = new ArrayList<>();

        while (resultSet.next()){
            Users user = new Users();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setAge(resultSet.getInt("age"));
            user.setGender(resultSet.getInt("gender"));
            user.setPhone(resultSet.getString("phone"));
            users.add(user);
        }
        statement.close();
        connection.close();

        System.out.println("测试连接");
        users.stream().forEach(user->{
            System.out.println(user);
        });
    }

    @Test
    public void testDeleteUser(){
        System.out.println("删除前数据：");
        List<Users> users = usersMapper.getUsers();
        users.stream().forEach(user->{
            System.out.println(user);
        });

        usersMapper.deleteUser(1);

        System.out.println("删除后数据:");

        List<Users> afterUsers = usersMapper.getUsers();
        afterUsers.stream().forEach(user->{
            System.out.println(user);
        });
    }

    @Test
    public void testInsertEmp(){
        Emp emp = new Emp();
        emp.setUserName("xyy3");
        emp.setPassword("maxjohy");
        emp.setName("喜彦云");
        emp.setGender((short)1);
        emp.setImage("1.jpg");
        emp.setJob((short)1);
        emp.setEntrydate(LocalDate.now());
        emp.setDeptId(1);
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());

        boolean bRet = empMapper.insertEmp(emp);
        System.out.println(bRet);
        System.out.println(emp.getId());
    }

    @Test
    public void testDeleteEmp(){
        boolean bRet = empMapper.deleteEmp(35);
        System.out.println(bRet);
    }

    @Test
    public void testUpdateEmp(){
        Emp emp = empMapper.selectEmpById(18);
        if(null != emp){
            System.out.println(emp);
            emp.setName(emp.getName()+"AA");
            emp.setCreateTime(LocalDateTime.of(2024,05,03,20,20,20));
            emp.setUpdateTime(LocalDateTime.now());
            boolean bRet = empMapper.updateEmp(emp);
            System.out.println(bRet);
        }
        else{
            System.out.println("id:18 未获取到指定数据!");
        }
    }

    @Test
    public void testGetEmpList(){
        LocalDate begin = LocalDate.of(2000,1,1);
        LocalDate end = LocalDate.of(2024,1,1);
        List<Emp> empList = empMapper.getEmpList("张",(short)1,begin,end);
        System.out.println(empList);
    }

    @Test
    public void testList(){
        LocalDate begin = LocalDate.of(2000,1,1);
        LocalDate end = LocalDate.of(2024,1,1);
        List<Emp> empList = empMapper.list(null,(short)1,null,null);
        System.out.println(empList);
    }
    @Test
    public void testUpdateEmp2(){
        Emp emp = new Emp();
        emp.setId(18);
        emp.setName("金庸1");
        //emp.setUpdateTime(LocalDateTime.now());
        boolean bRet = empMapper.updateEmp2(emp);
        System.out.println(bRet);
    }
    @Test
    public void testDeleteEmpByIds(){
        List<Integer> ids = Arrays.asList(36,34);
        boolean bRet = empMapper.deleteEmpByIds(ids);
        System.out.println(bRet);
    }
}
