package com.bbw.namesys.mapper;

import com.bbw.namesys.base.Mapper;
import com.bbw.namesys.service.namelist.EchelonInfo;
import com.bbw.namesys.service.namelist.NameDetailInfo;
import com.bbw.namesys.service.namelist.NameInfo;
import com.bbw.namesys.service.namelist.NameListProvider;
import org.apache.ibatis.annotations.*;
import org.w3c.dom.NameList;

import java.util.List;
import java.util.Map;

@Mapper
public interface NameListMapper {


    @SelectProvider(type = NameListProvider.class, method = "genSelectSql")
    List<NameInfo> select(@Param("username") String username, @Param("echelon") int echelon,
                          @Param("keyword") String keyword);

    @Select("select * from namedetailinfo where id = #{nameInfoId}")
    NameDetailInfo selectNameDetailInfo(@Param("nameInfoId") int id);

    @InsertProvider(type = NameListProvider.class, method = "insertNamesSql")
    int addNames(@Param("username") String username, @Param("names") String names);

    @InsertProvider(type = NameListProvider.class, method = "modifyNameInfoSql")
    void modifyNameInfo(@Param("detailInfo") NameDetailInfo detailInfo);

    @Select("select id,name,echelon from namedetailinfo where username = #{username}")
    List<EchelonInfo> selectEchelonInfo(@Param("username") String username);


    @UpdateProvider(type = NameListProvider.class, method = "updEchelonSql")
    void updEchelonInfos(@Param("username") String username, @Param("ids") int[] ids,
                         @Param("echelon") int echelon);

    @Select("select id,name from namedetailinfo where id = #{id} and username = #{username}")
    NameInfo selectNameInfo(@Param("id") int id, @Param("username") String username);

    @SelectProvider(type = NameListProvider.class, method = "selectNameInfosSql")
    List<NameInfo> selectNameInfos(@Param("ids") List<Integer> ids);
}
