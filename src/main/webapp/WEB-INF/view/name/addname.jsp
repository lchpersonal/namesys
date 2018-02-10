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
        .names {
            width: 100%; /*自动适应父布局宽度*/
            overflow: auto;
            word-break: break-all;
            /*在ie中解决断行问题(防止自动变为在一行显示，主要解决ie兼容问题，ie8中当设宽度为100%时，文本域类容超过一行时，当我们双击文本内容就会自动变为一行显示，所以只能用ie的专有断行属性“word-break或word-wrap”控制其断行)*/
        }
    </style>
</head>
<body>
<div style="border-bottom: 2px ridge #b6262f;" class="pb2 mb20">
    <a href="javascript:history.back();" id="back" class="fl f18 m0 p0 dib red tdn">返回</a>
    <span class="mleft30">添加名单</span>
</div>
<h class="f15">请输入要添加的姓名：</h>
<textarea rows="12" placeholder="批量添加用逗号隔开……" class="names" id="names"></textarea>
<span class="fl f12" style="color: red" id="prompt"></span>
<input type="button" value="添&nbsp;&nbsp;加" id="add" class="fr w100 h25 mt10 mr10 bgred" style="border: none; color:#fff"/>
<script type="text/javascript">
    $(function () {
        $(".names").focus();
        $("#add").click(function () {
            var names = $.trim($("#names").val());
            if (names == "" || names == " ") {
                $("#prompt").text("请输入要添加的姓名");
                return;
            }
            if (/[^\u4e00-\u9fa5a-zA-Z,， 0-9]/.test(names)) {
                $("#prompt").text("名单中只允许含有中文、英文、逗号和空格");
                return;
            }
            var newNames = names.replace(/，/g, ",").replace(/ /g, "");
            if (newNames.indexOf(",,") > -1) {
                $("#prompt").text("不要输入多个连续的逗号");
                return;
            }
            var nameArr = newNames.split(",");
            for (var i = 0; i < nameArr.length; i++) {
                if (nameArr[i].length > 5) {
                    $("#prompt").text("名字最大长度为5");
                    return;
                }
            }

            $.post("/name/add.json", {'names': newNames}, function (data) {
                if (data.result.code == 0) {
                    $("#prompt").text("添加成功" + data.result.t + "个名字");
                    $("#names").val("");
                    $("#names").focus();
                } else {
                    $("#prompt").text(data.result.detail);
                }
            });
        });
    });


</script>
</body>
</html>
