package com.bbw.namesys.utils;

import org.apache.commons.lang3.StringUtils;

public class HtmlUtil {


    public static String htmlTagTrans(String content) {
        if (StringUtils.isBlank(content)) {
            return content;
        }
        return content.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }

    public static String db2Html(String content) {
        if (StringUtils.isBlank(content)) {
            return content;
        }
        return content.replaceAll("\r\n|\r|\n", "<br/>");
    }

    public static String highlight(String content, String[] keys) {
        if (StringUtils.isBlank(content)) {
            return content;
        }
        return content.replaceAll("\r\n|\r|\n", "<br/>");
    }
}
