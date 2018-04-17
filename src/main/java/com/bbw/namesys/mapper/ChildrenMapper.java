package com.bbw.namesys.mapper;

import com.bbw.namesys.base.Mapper;
import com.bbw.namesys.service.user.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChildrenMapper {

    @Select("select `username`,`name`,`type` from `user` where parentUsername = #{username} and `type`= 0 ")
    List<UserInfo> select(@Param("username") String username);
}
