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
    <span class="mleft30">编辑互动资料</span>
</div>
<h class="f15">请修改互动资料：
    <span class="fr mr10 f12 wordcountwrapper" style="color:red;display: none"><span class="wordcount">0</span>/800</span>
</h>
<textarea rows="12" placeholder="请修改互动资料……" class="interactiveRes" onkeyup="checkNumber()">
<c:if test="${result.code==0}">${result.t.info}</c:if><c:if test="${result.code!=0}">${data.detail}</c:if>
</textarea>
<span class="fl f12" style="color: red" id="prompt"></span>
<input type="button" value="修&nbsp;&nbsp;改" id="edit" infoid="${result.t.id}" class="fr w100 h25 mt10 mr10 bgred"
       style="border: none; color:#fff"/>
<script type="text/javascript">
    function checkNumber() {
        var content = $(".interactiveRes").val();
        $(".wordcount").html(content.length);
        $(".wordcountwrapper").fadeIn();
    }


    $(function () {
        $("#edit").click(function () {
            var info = $(".interactiveRes").val();
            if (info == "" || $.trim(info) == " ") {
                $("#prompt").text("互动资源不能为空~");
                return;
            }
            if (info.length > 800) {
                $("#prompt").text("最多800字");
                return;
            }
            var infoid = $(this).attr("infoid");
            $.post("/interactive/edit.json", {'id': infoid, 'info': info}, function (data) {
                if (data.result.code == 0) {
                    location.href = window.history.back();
                    location.reload();
                } else {
                    $("#prompt").text(data.result.detail);
                }
            });

        });
    });

</script>
</body>
</html>
