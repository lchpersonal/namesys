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
    <script src="http://www.gongjuji.net/Content/files/jquery.md5.js"></script>
    <link rel="stylesheet" href="/static/css/core.css"/>
    <style type="text/css">

        body {
           /* background: url(/static/bk.jpg) no-repeat;*/
            height: 100%;
            width: 100%;
            overflow: hidden;
            background-size: cover;
        }

        .sfont{
            color: #fff;font-size: 25px
        }

    </style>
</head>
<body class="m0">
<div style="background: #000; height: 100%; width: 100%">
    <div style="text-align: center;position: relative;top: 50%;transform: translateY(-50%);">
        <div style="margin-bottom: 20px">
            <img src="/static/img/et.png" width="200px" style="opacity: 0.8"/>
        </div>
        <div style="margin-bottom: 10px">
            <span class="sfont">页面被外星人偷走了！</span>
        </div>
        <br/>
        <br/>
        <br/>
    </div>
</div>
</body>
</html>
