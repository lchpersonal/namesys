package com.bbw.namesys.mapper;

import com.bbw.namesys.base.Mapper;
import com.bbw.namesys.service.interactive.InteractiveRes;
import com.bbw.namesys.service.interactive.InteractiveResProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InteractiveResMapper {


    @Insert("insert into interactiveres(info) values(#{info})")
    void add(@Param("info") String info);

    @Select("select * from interactiveres where id = #{id}")
    InteractiveRes selectById(@Param("id") int id);

    @SelectProvider(type = InteractiveResProvider.class, method = "selectByKeywordsSql")
    List<InteractiveRes> selectByKeywords(@Param("curId") int curId, @Param("keywords") String[] keywords);

    @Update("update interactiveres set info = #{info} where id = #{id}")
    void edit(@Param("id") int id, @Param("info") String info);
}
