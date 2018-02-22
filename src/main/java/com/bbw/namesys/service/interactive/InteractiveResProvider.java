package com.bbw.namesys.service.interactive;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InteractiveResProvider {

    public static String selectByKeywordsSql(Map<String, Object> params) {
        String[] keys = (String[]) params.get("keywords");
        int curId = (int) params.get("curId");
        String sql = "select * from interactiveres where %s limit 10";
        List<String> whereClauses = new ArrayList();
        whereClauses.add(String.format(" id > %s ", curId));
        if (keys != null && keys.length > 0) {
            for (String key : keys) {
                whereClauses.add(" info like '%" + key + "%' ");
            }
        }
        sql = String.format(sql, StringUtils.join(whereClauses, "and"));
        return sql;
    }


}
