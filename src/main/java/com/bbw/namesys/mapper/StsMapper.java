package com.bbw.namesys.mapper;

import com.bbw.namesys.base.Mapper;
import com.bbw.namesys.service.sts.EchelonCountPair;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StsMapper {


    @Select("select echelon,count(*) `count` from `namedetailinfo` where username = #{username} group by echelon")
    List<EchelonCountPair> selectEchelonCountPairs(@Param("username") String username);

    @Select("select count(*) from (select distinct nameInfoId from pavingrecord where " +
            " username= #{username} and datediff(now(),pavingTime)<#{datediff})A;")
    int selectPavingStsInfo(@Param("username") String username, @Param("datediff") int datediff);

    @Select("select count(*) from (select distinct nameInfoId from pavingrecord where username= #{username})A;")
    int selectPavingCount(@Param("username") String username);

}
