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
        .interactiveRes {
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
    <span class="mleft30">增加互动资源</span>
</div>
<h class="f15">请输入互动资源：
    <span class="fr mr10 f12" style="color:red"><span class="wordcount">0</span>/800</span>
</h>
<textarea rows="20" placeholder="严格按照以下格式：
【所属网】A1
【姓名】张三
【性别】男
【籍贯】中国
【年龄】45
【推荐人】李四
【传统行业】服装厂,工地食堂,家政
【付出工作】操作，坚定信心，转网。
【电话】15777900000
【微信】15777900000
【备注】带过同事,朋友,网友,老公,小叔子本人形象气质佳，带人经验丰富家庭氛围特别好" class="interactiveRes" onkeyup="checkNumber()"></textarea>
<span class="fl f12" style="color: red" id="prompt"></span>
<input type="button" value="添&nbsp;&nbsp;加" id="add" class="fr w100 h25 mt10 mr10 bgred"
       style="border: none; color:#fff"/>
<script type="text/javascript">
    function checkNumber() {
        var content = $(".interactiveRes").val();
        $(".wordcount").html(content.length);
    }

    $(function () {
        $(".interactiveRes").focus();

        lock = false;
        $("#add").click(function () {
            if (lock) {
                return;
            }
            lock = true;
            var record = $(".interactiveRes").val();
            if (record == "" || $.trim(record) == " ") {
                $("#prompt").text("互动信息不能为空~");
                lock = false;
                return;
            }
            var content = $(".interactiveRes").val();
            var count = content.length;
            if (count > 800) {
                $("#prompt").text("不能超过800字");
                lock = false;
                return;
            }
            $.post("/interactive_new/add.json", {'info': record}, function (data) {
                if (data.result.code == 0) {
                    window.location.href = "/jst/interactive_new/res";
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
