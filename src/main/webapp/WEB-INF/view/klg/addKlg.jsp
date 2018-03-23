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
        .content {
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
    <span class="mleft30">添加行业知识</span>
</div>
<h class="f15">请输入标题：</h>
<div class="mb10">
    <input class="width80 title" type="text">
</div>
<h class="f15">请输入互动资源：
    <span class="fr mr10 f12" style="color:red"><span class="wordcount">0</span>/65535</span>
</h>
<textarea rows="20" placeholder="请输入内容……" class="content" onkeyup="checkNumber()"></textarea>
<span class="fl f12" style="color: red" id="prompt"></span>
<input type="button" value="添&nbsp;&nbsp;加" id="add" class="fr w100 h25 mt10 mr10 bgred"
       style="border: none; color:#fff"/>
<script type="text/javascript">
    function checkNumber() {
        var content = $(".content").val();
        $(".wordcount").html(content.length);
    }

    $(function () {
        $(".title").focus();
        lock = false;
        $("#add").click(function () {
            if (lock) {
                return;
            }
            lock = true;
            var title = $(".title").val();
            if (title == "" || $.trim(title) == " ") {
                $("#prompt").text("标题不能为空~");
                lock = false;
                return;
            }
            if (title.length > 50) {
                $("#prompt").text("标题字数不能超过50字");
                lock = false;
                return;
            }
            var record = $(".content").val();
            if (record == "" || $.trim(record) == " ") {
                $("#prompt").text("内容不能为空~");
                lock = false;
                return;
            }
            var content = $(".content").val();
            var count = content.length;
            if (count > 65535) {
                $("#prompt").text("内容字数不能超过65535字");
                lock = false;
                return;
            }
            $.post("/klg/add.json", {'title': title, 'content': content}, function (data) {
                if (data.result.code == 0) {
                    window.location.href = "/jst/klg/klg";
                } else {
                    $("#prompt").text(data.result.detail);
                }
                lock = false;
            });

        });
    });


</script>
</body>
</html>
