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
        .divs {
            background: #fff;
            padding: 15px 15px 15px 15px;
        }

        .ps {
            margin-bottom: 0px;
            color: #333;
        }

        em {
            color: red;
            font-style: normal;
        }

        input::-webkit-input-placeholder {
            font-size: 12px;
        }

        input:-ms-input-placeholder {
            font-size: 12px;
        }

        input::-moz-placeholder {
            font-size: 12px;
        }
    </style>
</head>
<!--對所有人的鋪墊記錄-->
<body class="m0">
<div style="border-bottom: 2px ridge #b6262f;" class="pb2 mb10">
    <a href="/" id="back" class="fl f18 m0 p0 dib red tdn">返回</a>
    <span class="mleft30">互动资源</span>
    <a href="/jst/interactive/addRes" id="homepage" class="fr f18 m0 p0 dib red tdn">添加</a>
</div>
<div class="mt5" style="padding: 2px 0 5px 2px;background: #f7f0f4;">
    <span class="f14 ml5">关键字:</span>
    <input type="text" id="keyword" placeholder="多个关键词实例：图|视频" style="width: 200px" value="${keyword}"/>
    <input type="button" value="搜索" id="search" class="searchBtn"/>
</div>
<div style="background: #f5f5f5;min-height: 100%" class="outerDiv"></div>
<div class="loadMore f14" style="text-align: center;color:#888; margin-top: -10px;padding:5px 0 5px 0">加载更多</div>

<script type="text/javascript">
    $(function () {
        var curId = 0;
        var change = false;
        var oldKey = "";

        function highlight(content, keys) {
            if (keys == "" || keys == undefined) {
                return content;
            }
            var reg = new RegExp('(' + keys + ')', 'g');
            return content.replace(reg, "<em>$1</em>");
        }

        function getData(maxId, keyword) {
            $.post("/interactive/getByKeys.json", {curId: maxId, keyword: keyword}, function (data) {
                if (data.result.code == 0) {
                    change = oldKey != keyword ? true : false;
                    var htm = "";
                    var obj = 0;
                    if (data.result.t != null) {
                        for (var i = 0; i < data.result.t.length; i++) {
                            obj = data.result.t[i];
                            var info = highlight(obj.info, keyword);
                            htm += '<div class="mb10 divs">\
                            <p class="f12 mb10 p0 clearfix" style="margin-top: 0px;color: #888">\
                                <a href="javascript:void(0);" class="edit fr mr5" infoid="' + obj.id + '">编辑</a>\
                            </p>\
                            <p class="f14 p0 ps">' + info + '</p>\
                        </div>';
                        }
                        curId = obj.id;
                    }
                    if (change) {
                        $(".outerDiv").html(htm);
                    } else {
                        $(".outerDiv").append(htm);
                    }
                    if (htm == "") {
                        $(".loadMore").css("display", "none");
                    }
                    oldKey = keyword;
                } else {
                    alert(data.result.detail);
                }
            });
        }

        $("#search").click(function () {
            var keys = $.trim($("#keyword").val());
            keys = keys.replace(/｜/g, "|");
            if (typeof keys != "undefined" && keys != null && keys != "") {
                var reg = new RegExp("[|\u4E00-\u9FFF]+", "g");
                if (!reg.test(keys)) {
                    alert("搜索关键词只能是中文~");
                    return;
                }
            }
            var maxId = 0;
            $(".outerDiv").html("");
            getData(maxId, keys);
            $(".loadMore").css("display", "block");
        });

        $(".loadMore").click(function () {
            var keys = $.trim($("#keyword").val());
            if (typeof keys != "undefined" && keys != null && keys != "") {
                var reg = new RegExp("[|\u4E00-\u9FFF]+", "g");
                if (!reg.test(keys)) {
                    alert("搜索关键词只能是中文~");
                    return;
                }
            }
            getData(curId, keys);
        });

        $("body").on("click", ".edit", function () {
            var that = $(this);
            var infoid = that.attr("infoid");
            location.href = "/interactive/editRes?id=" + infoid;
        });

        $("#search").click();
    });
</script>
</body>
</html>
