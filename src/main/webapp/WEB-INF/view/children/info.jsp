<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="Description" content=""/>
    <meta name="keywords" content=""/>
    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <link rel="stylesheet" href="/static/css/core.css"/>

    <style type="text/css">
        table {
            table-layout: fixed;
            empty-cells: show;
            border-collapse: collapse;
            margin: 0 0 15px 0px;
        }

        td {
            height: 20px;
        }

        table.t {
            border: 1px solid #ea7264;
            color: #666;
        }

        table.t td {
            border: 1px dotted #f0cfcf;
            padding: 0 2px 0;
            font-size: 13px;
            padding: 3px 0 5px 3px;
        }

        table.t tr.a1 {
            background-color: #fceded;
        }

        .tdl {
            padding-right: 2px;
            text-align: center;
            width: 24%
        }
    </style>
</head>
<body>
<div style="border-bottom: 2px ridge #b6262f;" class="pb2 mb20">
    <a href="#" onclick="javascript :history.back(-1);" id="back" class="fl f18 m0 p0 dib red tdn">返回</a>
    <span class="mleft30">主任信息</span>
    <a href="/" id="homepage" class="fr f18 m0 p0 dib red tdn">首页</a>
</div>
<table width="100%" align="center" class="mt30 t">
    <tr class="a1">
        <td align="center">姓名</td>
        <td align="center">${name}</td>
    </tr>
    <tr>
        <td align="center">名单总数</td>
        <td align="center">${data.t.nameCount}</td>
    </tr>
    <tr class="a1">
        <td align="center">第一梯队</td>
        <td align="center">${data.t.eleclon1Count}</td>
    </tr>
    <tr>
        <td align="center">第二梯队</td>
        <td align="center">${data.t.eleclon2Count}</td>
    </tr>
    <tr class="a1">
        <td align="center">第三梯队</td>
        <td align="center">${data.t.eleclon3Count}</td>
    </tr>
    <tr>
        <td align="center">未分梯队</td>
        <td align="center">${data.t.uneleclonCount}</td>
    </tr>
    <tr class="a1">
        <td align="center">近一周铺垫</td>
        <td align="center">${data.t.pavingWeekCount}</td>
    </tr>
    <tr>
        <td align="center">近一月铺垫</td>
        <td align="center">${data.t.pavingMonthCount}</td>
    </tr>
</table>
<input type="button" class="fr w150 h25 mt10 mr10 bgred" style="border: none; color:#fff" value="看TA的铺垫记录" username="${childUsername}" id="pavingRecord"/>
<script type="text/javascript">
    $(function () {
        $("#pavingRecord").click(function () {
            var childUsername = $(this).attr("username");
            window.location.href = "/children/pavingRecords.html?childUsername=" + childUsername;
        });
    });
</script>
</body>
</html>
