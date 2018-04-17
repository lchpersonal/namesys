package com.bbw.namesys.service.interactive_new;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class InteractiveResNewProvider {

    public static String genAddSql(Map<String, Object> params) {
        InteractiveResNew interactiveResNew = (InteractiveResNew) params.get("interactiveResNew");
        String sql = String.format("insert ignore into interactiveres_new(net,name,sex,age,nativeplace,parent,tradition," +
                        "payfor,tel,wechart,other) values('%s','%s','%s',%s,'%s','%s','%s','%s','%s','%s','%s')", interactiveResNew.getNet(),
                interactiveResNew.getName(),
                interactiveResNew.getSex(),
                interactiveResNew.getAge(),
                interactiveResNew.getNativeplace(),
                interactiveResNew.getParent(),
                interactiveResNew.getTradition(),
                interactiveResNew.getPayfor(),
                interactiveResNew.getTel(),
                interactiveResNew.getWechart(),
                interactiveResNew.getOther());
        System.out.println(sql);
        return sql;
    }

    public static String genUpdateSql(Map<String, Object> params) {
        int id = (int) params.get("id");
        InteractiveResNew interactiveResNew = (InteractiveResNew) params.get("interactiveResNew");
        String sql = String.format("update interactiveres_new set net='%s',name='%s',sex='%s',age=%s,nativeplace='%s',parent='%s'," +
                        "tradition='%s',payfor='%s',tel='%s',wechart='%s',other='%s' where id=%s", interactiveResNew.getNet(),
                interactiveResNew.getName(),
                interactiveResNew.getSex(),
                interactiveResNew.getAge(),
                interactiveResNew.getNativeplace(),
                interactiveResNew.getParent(),
                interactiveResNew.getTradition(),
                interactiveResNew.getPayfor(),
                interactiveResNew.getTel(),
                interactiveResNew.getWechart(),
                interactiveResNew.getOther(),
                id);
        System.out.println(sql);
        return sql;
    }

    public static String genSelectByKeys(Map<String, Object> params) {
        SelectKeys selectKeys = (SelectKeys) params.get("selectKeys");
        StringBuilder sql = new StringBuilder("select * from interactiveres_new where id>" + selectKeys.getCurId());
        if (!StringUtils.isBlank(selectKeys.getName())) {
            sql.append(" and name like '%" + selectKeys.getName() + "%'");
        }
        if (!StringUtils.isBlank(selectKeys.getNativePlace())) {
            sql.append(" and nativeplace like '%" + selectKeys.getNativePlace() + "%'");
        }
        if (!StringUtils.isBlank(selectKeys.getTradition())) {
            sql.append(" and tradition like '%" + selectKeys.getTradition() + "%'");
        }
        if (!StringUtils.isBlank(selectKeys.getPayfor())) {
            sql.append(" and payfor like '%" + selectKeys.getPayfor() + "%'");
        }
        sql.append(" limit 10");
        return sql.toString();
    }
}
