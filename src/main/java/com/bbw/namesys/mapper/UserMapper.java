package com.bbw.namesys.mapper;


import com.bbw.namesys.base.Mapper;
import com.bbw.namesys.service.user.User;
import com.bbw.namesys.service.user.UserSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user where username = #{username}")
    User selectUser(@Param("username") String username);

    @Select("select * from user")
    List<User> selectAll();

    @InsertProvider(type = UserSqlProvider.class,method = "addUserSql")
    int addUser(@Param("user") User user);

    @Update("update user set password=#{newpassword} where username = #{username}")
    int modifyPasswrod(@Param("username") String username, @Param("newpassword") String newpassword);
}
