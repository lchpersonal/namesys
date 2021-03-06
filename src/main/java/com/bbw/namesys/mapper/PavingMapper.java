package com.bbw.namesys.mapper;


import com.bbw.namesys.base.Mapper;
import com.bbw.namesys.service.paving.PavingRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface PavingMapper {


    @Insert("insert into pavingrecord(nameInfoId,username,record,pavingTime) values" +
            "(#{nameInfoId},#{username},#{record},now())")
    void addPavingRecord(@Param("nameInfoId") int nameInfoId, @Param("record") String record,
                         @Param("username") String username);

    @Select("select * from pavingrecord where nameInfoId=#{nameInfoId} order by pavingTime desc")
    List<PavingRecord> select(@Param("nameInfoId") int nameInfoId);

    @Select("select * from pavingrecord where username=#{username} and id < #{curId} order by id desc limit 10")
    List<PavingRecord> selectByUsername(@Param("username") String username, @Param("curId") int curId);

    @Update("update pavingrecord set record = #{record} where id = #{nameInfoId}")
    void editPavingRecord(@Param("nameInfoId") int nameInfoId, @Param("record") String record);

    @Select("select * from pavingRecord where id = #{id}")
    PavingRecord selectPavingRecord(@Param("id") int recordId);
}
