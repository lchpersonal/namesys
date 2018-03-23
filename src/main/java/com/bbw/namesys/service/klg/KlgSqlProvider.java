package com.bbw.namesys.service.klg;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KlgSqlProvider {

    public static String selectSql(Map<String, Object> params) {
        String[] keys = (String[]) params.get("keys");
        int curId = (int) params.get("id");
        String sql = "select * from klg where %s order by id desc limit 10";
        List<String> whereClauses = new ArrayList();
        whereClauses.add(String.format(" id < %s ", curId));
        if (keys != null && keys.length > 0) {
            for (String key : keys) {
                whereClauses.add(" title like '%" + key + "%' ");
            }
        }
        sql = String.format(sql, StringUtils.join(whereClauses, "and"));
        return sql;
    }
}
