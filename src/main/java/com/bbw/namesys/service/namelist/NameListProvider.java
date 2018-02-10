package com.bbw.namesys.service.namelist;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NameListProvider {

    public static String genSelectSql(Map<String, Object> params) {

        SQL sql = new SQL();
        String username = params.get("username").toString();
        int echelon = Integer.parseInt(params.get("echelon").toString());
        String keyword = params.get("keyword").toString();
        sql.SELECT("`id`, `name` ").FROM("namedetailinfo").WHERE(String.format("username = '%s'", username));
        if (echelon > 0) {
            sql.WHERE("echelon = " + echelon);
        }
        if (StringUtils.isNotBlank(keyword)) {
            sql.WHERE("name like '%" + keyword + "%'");
        }
        sql.ORDER_BY("echelon");
        StringBuilder sqlBuilder = new StringBuilder(sql.toString());
        return sqlBuilder.toString();
    }

    public static String insertNamesSql(Map<String, Object> params) {

        StringBuilder sqlBuilder = new StringBuilder("insert ignore into namedetailinfo(username,name,addTime,updateTime) values");
        String username = params.get("username").toString();
        String names = params.get("names").toString();
        String[] nameArr = names.split(",");
        List<String> values = new ArrayList<>();
        for (int i = 0; i < nameArr.length; i++) {
            if (StringUtils.isNotBlank(nameArr[i])) {
                values.add(String.format("('%s','%s',now(),now())", username, nameArr[i]));
            }
        }
        sqlBuilder.append(StringUtils.join(values, ","));
        System.out.println(sqlBuilder.toString());
        return sqlBuilder.toString();
    }

    public static String modifyNameInfoSql(Map<String, Object> params) {
        NameDetailInfo detailInfo = (NameDetailInfo) params.get("detailInfo");
        SQL sql = new SQL();
        sql.UPDATE("namedetailinfo");
        sql.SET(String.format("`name`='%s'", detailInfo.getName()));
        sql.SET(String.format("`sex`=%s", detailInfo.getSex()));
        sql.SET(String.format("`age`=%s", detailInfo.getAge()));
        sql.SET(String.format("`nativePlace`='%s'", detailInfo.getNativePlace()));
        sql.SET(String.format("`workplace`='%s'", detailInfo.getWorkplace()));
        sql.SET(String.format("`health`='%s'", detailInfo.getHealth()));
        sql.SET(String.format("`education`='%s'", detailInfo.getEducation()));
        sql.SET(String.format("`characteristics`='%s'", detailInfo.getCharacteristics()));
        sql.SET(String.format("`relationship`='%s'", detailInfo.getRelationship()));
        sql.SET(String.format("`coexistenceMode`='%s'", detailInfo.getCoexistenceMode()));
        sql.SET(String.format("`evaluateMe`='%s'", detailInfo.getEvaluateMe()));
        sql.SET(String.format("`workExperience`='%s'", detailInfo.getWorkExperience()));
        sql.SET(String.format("`lifeExperience`='%s'", detailInfo.getLifeExperience()));
        sql.SET(String.format("`income`='%s'", detailInfo.getIncome()));
        sql.SET(String.format("`entrepreneurship`='%s'", detailInfo.getEntrepreneurship()));
        sql.SET(String.format("`vacations`='%s'", detailInfo.getVacations()));
        sql.SET(String.format("`maritalStatus`='%s'", detailInfo.getMaritalStatus()));
        sql.SET(String.format("`memberOfFamily`='%s'", detailInfo.getMemberOfFamily()));
        sql.SET(String.format("`familyStatus`='%s'", detailInfo.getFamilyStatus()));
        sql.SET(String.format("`familyIncomeAndSupport`='%s'", detailInfo.getFamilyIncomeAndSupport()));
        sql.SET(String.format("`character`='%s'", detailInfo.getCharacter()));
        sql.SET(String.format("`policyConcern`='%s'", detailInfo.getPolicyConcern()));
        sql.SET(String.format("`loveReading`='%s'", detailInfo.getLoveReading()));
        sql.SET(String.format("`hobby`='%s'", detailInfo.getHobby()));
        sql.SET(String.format("`specialty`='%s'", detailInfo.getSpecialty()));
        sql.SET(String.format("`topicOfLike`='%s'", detailInfo.getTopicOfLike()));
        sql.SET(String.format("`dream`='%s'", detailInfo.getDream()));
        sql.SET(String.format("`concept`='%s'", detailInfo.getConcept()));
        sql.SET(String.format("`reasonOfInvite`='%s'", detailInfo.getReasonOfInvite()));
        sql.SET(String.format("`others`='%s'", detailInfo.getOthers()));
        sql.SET("`updateTime`=now()");
        sql.WHERE(String.format("`id`=%s", detailInfo.getId()));
        System.out.println(sql.toString());
        return sql.toString();
    }

    public static String updEchelonSql(Map<String, Object> params) {
        String username = params.get("username").toString();
        int[] ids = (int[]) params.get("ids");
        int echelon = (int) params.get("echelon");
        String sql = "update namedetailinfo set echelon=%s where username = '%s' and id in(%s)";
        String idStr = StringUtils.join(ids, ',');
        sql = String.format(sql, echelon, username, idStr);
        return sql;

    }
}
