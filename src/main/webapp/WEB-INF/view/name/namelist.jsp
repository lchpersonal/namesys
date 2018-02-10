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
<body>
<div style="border-bottom: 2px ridge #b6262f;" class="pb2">
    <a href="javascript:history.back();" id="back" class="fl f18 m0 p0 dib red tdn">返回</a>
    <span class="mleft30">名单列表</span>
    <a href="####" id="homepage" class="fr f18 m0 p0 dib red tdn">首页</a>
</div>
<div class="mt5" style="padding: 2px 0 5px 2px;background: #f7f0f4;">
    <span class="f16">梯队:</span>
    <select name="echelon" id="echelon">
        <option value="0">全部</option>
        <option value="1" <c:if test="${echelon==1}">selected</c:if>>第一梯队</option>
        <option value="2" <c:if test="${echelon==2}">selected</c:if>>第二梯队</option>
        <option value="3" <c:if test="${echelon==3}">selected</c:if>>第三梯队</option>
    </select>
    <span class="f16 ml5">关键字:</span>
    <input type="text" id="keyword" class="w75" value="${keyword}"/>
    <input type="button" value="搜索" id="search" class="searchBtn"/>
</div>
<hr class="linecss"/>
<c:if test="${data.code!=0}">
    <span>${data.detail}</span>
</c:if>
<c:if test="${data.code==0}">
    <table width="100%">
        <c:forEach items="${data.t}" var="nameinfo" varStatus="cur">
            <c:if test="${cur.index%3==0}">
                <c:if test="cur.index%3!=0">
                    </tr>
                </c:if>
                    <tr>
             </c:if>
            <td>
                <a href="javascript:void(0);" id='${nameinfo.id}' class='name hrefcss fl w75 bgred'>
                        ${nameinfo.name}
                </a>
            </td>
            </c:forEach>
    </table>
</c:if>

<script type="text/javascript">
    $("#search").click(function () {
        var echelon = $("#echelon").val();
        var keyword = $("#keyword").val();
        window.location.href = "/name/select.html?echelon="+echelon+"&keyword="+keyword;
    });

   $(".name").click(function () {
      var id = $(this).attr("id");
       window.location.href = "/name/detail.html?id=" + id;
   });
</script>
</body>
</html>
