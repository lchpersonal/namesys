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
    <script src="/static/js/jquery.min.js"></script>
    <link rel="stylesheet" href="/static/css/core.css"/>
    <style type="text/css">
        .divs{
            background: #fff;padding:15px 15px 15px 15px;
        }
        .ps{
            margin-bottom: 0px; color:#333;
        }
    </style>
</head>
<!--查询该主任的铺垫记录-->
<body class="m0">
<div style="border-bottom: 2px ridge #b6262f;" class="pb2 mb10">
    <a href="/" id="back" class="fl f18 m0 p0 dib red tdn">返回</a>
    <span class="mleft30">主任铺垫记录</span>
    <a href="####" id="homepage" class="fr f18 m0 p0 dib red tdn">首页</a>
</div>
<div style="background: #f5f5f5;min-height: 100%" class="outerDiv"></div>
<div class="loadMore f14" style="text-align: center;color:#888; margin-top: -10px;padding:5px 0 5px 0">加载更多</div>

<script type="text/javascript">
$(function () {
    var curId = 0;
    var childUsername = '${childUsername}';
    getData(curId);

    function getData(minId) {
        $.post("/children/pavingRecords.json", {curId: minId,childUsername:childUsername}, function (data) {
            if (data.result.code == 0) {
                var htm = "";
                var obj = 0;
                if(data.result.t != null){
                    for (var i = 0; i < data.result.t.length; i++) {
                        obj = data.result.t[i];
                        htm += '<div class="mb10 divs">\
                            <p class="f12 mb10 p0 clearfix" style="margin-top: 0px;color: #888">' + obj.pavingRecord.pavingTime + '&nbsp;&nbsp;' + obj.nameInfo.name + '\
                            </p>\
                            <p class="f14 p0 ps">' + obj.pavingRecord.record + '</p>\
                        </div>';
                    }
                    curId = obj.pavingRecord.id;
                }
                $(".outerDiv").append(htm);
                if(htm == ""){
                    $(".loadMore").css("display", "none");
                }
            }
        });
    }

    $(".loadMore").click(function(){
        getData(curId);
    });

});
</script>
</body>
</html>
