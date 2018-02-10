package com.bbw.namesys.service.user;

import com.bbw.namesys.base.Result;
import com.bbw.namesys.base.Results;
import com.bbw.namesys.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User selectUser(String username) {
        return userMapper.selectUser(username);
    }

    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    public Result addUser(User user) {
        if (userMapper.addUser(user) > 0) {
            return Results.success();
        }
        return Results.error("添加失败");
    }

    public Result modifyPassword(String username, String password) {
        User user = userMapper.selectUser(username);
        if(user == null){
            return Results.error("该用户不存在");
        }
        userMapper.modifyPasswrod(username, password);
        return Results.success();
    }

    public Result addUser(String username, String name) {
        User user = selectUser(username);
        if (user != null) {
            return Results.error("用户名已存在");
        }
        user = new User();
        user.setName(name);
        user.setUsername(username);
        return addUser(user);
    }
}
