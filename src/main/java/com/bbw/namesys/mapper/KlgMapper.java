package com.bbw.namesys.mapper;

import com.bbw.namesys.base.Mapper;
import com.bbw.namesys.service.klg.Klg;
import com.bbw.namesys.service.klg.KlgSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface KlgMapper {

    @Insert("insert into klg(`title`,`content`,`addUser`,`addTime`,`updateUser`) values(#{title},#{content},#{username}" +
            ",now(),#{username})")
    void insert(@Param("title") String title, @Param("content") String content, @Param("username") String username);

    @Update("update klg set title=#{title}, updateUser=#{updateUser}, content=#{content} where id=#{id}")
    void update(@Param("title") String title, @Param("content") String content, @Param("id") int id, @Param("updateUser") String updateUser);

    @SelectProvider(type = KlgSqlProvider.class, method = "selectSql")
    List<Klg> select(@Param("id") int curId, @Param("keys") String[] keys);

    @Select("select * from klg where id = #{id}")
    Klg selectById(@Param("id") int id);
}
