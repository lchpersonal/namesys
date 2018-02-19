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
</head>
<body class="m0">
<div style="border-bottom: 2px ridge #b6262f;" class="pb2 mb10">
    <a href="javascript:history.back();" id="back" class="fl f18 m0 p0 dib red tdn">返回</a>
    <span class="mleft30">铺垫记录</span>
    <a href="javascript:void(0);" id="add" nameinfoid="${id}" class="fr f18 m0 p0 dib red tdn">添加铺垫记录</a>
</div>

<div style="background: #f5f5f5;min-height: 100%">
<c:if test="${data.code == 0}">
    <c:if test="${data.t !=null}">
        <c:forEach var="pavingRecord" items="${data.t}">
            <div class="mb10" style="background: #fff;padding:15px 15px 15px 15px">
                <p class="f12 mb10 p0 clearfix" style="margin-top: 0px;color: #888">
                    <fmt:formatDate value="${pavingRecord.pavingTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    <a href="javascript:void(0);" class="edit fr mr5" nameInfoId="${pavingRecord.nameInfoId}" recordId="${pavingRecord.id}">
                        编辑
                    </a>
                </p>
                <p class="f14 p0" style="margin-bottom: 0px; color:#333">${pavingRecord.record}</p>
            </div>
        </c:forEach>
    </c:if>

    <c:if test="${data.t ==null||empty data.t}">
        <div>
            此人没有铺垫记录！
        </div>
    </c:if>
</c:if>
<c:if test="${data.code != 0}">
    好像系统出错啦！ ${data.detail}
</c:if>
</div>
<script type="text/javascript">
$(function () {
    $("#add").click(function () {
        var id = $(this).attr("nameinfoid");
        location.href="/paving/addRecordPage.html?id="+id;
    });

    $(".edit").click(function(){
        var that = $(this);
       var nameInfoId =  that.attr("nameInfoId");
       var recordId = that.attr("recordId");
        window.location.href = "/paving/editRecordPage.html?nameInfoId=" + nameInfoId + "&recordId=" + recordId;
    });
});
</script>
</body>
</html>
