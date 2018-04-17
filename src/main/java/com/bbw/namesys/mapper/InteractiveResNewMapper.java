package com.bbw.namesys.mapper;

import com.bbw.namesys.base.Mapper;
import com.bbw.namesys.service.interactive_new.InteractiveResNew;
import com.bbw.namesys.service.interactive_new.InteractiveResNewProvider;
import com.bbw.namesys.service.interactive_new.SelectKeys;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InteractiveResNewMapper {

    @InsertProvider(type = InteractiveResNewProvider.class, method = "genAddSql")
    int add(@Param("interactiveResNew") InteractiveResNew interactiveResNew);

    @UpdateProvider(type = InteractiveResNewProvider.class, method = "genUpdateSql")
    int edit(@Param("id") int id, @Param("interactiveResNew") InteractiveResNew interactiveResNew);

    @Select("select * from interactiveres_new where id=#{id}")
    InteractiveResNew select(@Param("id") int id);

    @SelectProvider(type = InteractiveResNewProvider.class, method = "genSelectByKeys")
    List<InteractiveResNew> selectByKeys(@Param("selectKeys") SelectKeys selectKeys);
}
