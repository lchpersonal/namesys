package com.bbw.namesys.service.user;

import com.bbw.namesys.base.Constants;

import java.util.Map;

public class UserSqlProvider {

    public static String addUserSql(Map<String, Object> params) {
        User user = (User) params.get("user");
        return String.format("insert into `user`(`username`,`name`,`password`) values('%s','%s','%s')",
                user.getUsername(), user.getName(), Constants.DEFAULT_PASSWORD);

    }
}
