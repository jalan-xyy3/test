package com.example.mapper;

import com.example.model.Users;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ClassName：UsersMapper
 * Description：
 *
 * @Author：xyy3
 * @CreateDate：2024/4/16 22:26
 * @Version: 1.0
 */
@Mapper
public interface UsersMapper {
    @Select("select id,name,age,gender,phone from USERS with(nolock)")
    public List<Users> getUsers();

    @Delete("delete from USERS where id=#{id}")
    public void deleteUser(Integer id);
}
