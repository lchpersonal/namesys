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
        .echelonHrefCss {
            font-size: 9px;
            color: #fff;
            display: block;
            border: 1px solid #eee;
            text-decoration: none;
            cursor: auto;
            line-height: 15px;
            text-align: center;
            margin: 0 5px 5px 2px;
        }

        li {
            font-size: 12px;
            margin-bottom: 1px;
            color: #fff;
            text-align: center;
            /*兼容iphone*/
            cursor:pointer;
            height: 25px;
            line-height: 25px;
        }

        .pd2 {
            padding: 2px
        }

        .lh21{
            line-height: 21px;}
    </style>
</head>
<body>
<div style="border-bottom: 2px ridge #b6262f;" class="pb2 mb20">
    <a href="javascript:history.back();" id="back" class="fl f18 m0 p0 dib red tdn">返回</a>
    <span class="mleft30">分梯队</span>
</div>
<table class="width100 tb" align="center" style="min-height:90%;height:90%;">
    <tr>
        <td class="width20" style="border: 1px dashed #b6262f;min-height:100%;height:100%">
            <div class="" style="min-height:100%;height:100%; overflow-y: auto">
                <ul class="names pd2">

                </ul>
            </div>
        </td>
        <td class="width80">
            <div style="min-height:100%; height:100%;">
                <span class="clearfix">
                    <span class="f12">第一梯队</span>
                    <input type="button" value="保&nbsp;存" id="save" class="fr f12 bgred"
                           style="border: none; margin-bottom: 2px;color: #fff"/>
                    <label class="fr" style="padding-right: 10px;">
                        <input type="checkbox" id="unecheloned" style="width: 11px;height: 11px;margin: 0px"/>
                        <span class="f12">只显示未分梯队的人</span>
                    </label>
                </span>
                <div class="echeloned echelon1 clearfix divbdcss"
                     style="min-height:27%;height:27%; overflow-y: auto"></div>
                <span class="f12">第二梯队</span>
                <div class="echeloned echelon2  clearfix divbdcss"
                     style="min-height:28%;height:28%; overflow-y: auto"></div>
                <span class="f12">第三梯队</span>
                <div class="echeloned echelon3  clearfix divbdcss"
                     style="min-height:32%;height:32%; overflow-y: auto"></div>
            </div>
        </td>
    </tr>
</table>
<div id="chooseDiv" style="display: none; position: absolute;color:#fff; border: 1px solid" class="bgred">
    <span class="f12 mb5">选择梯队：</span><br/>
    <label>
        <span class="f15 ml10 lh21">第一梯队</span>
        <input class="radio1" type="radio" name="rdo" value="1"/>
    </label><br/>
    <label>
        <span class="f15 ml10 lh21">第二梯队</span>
        <input class="radio2" type="radio" name="rdo" value="2"/>
    </label><br/>
    <label class="mt5">
        <span class="f15 ml10 lh21">第三梯队</span>
        <input class="radio3" type="radio" name="rdo" value="3"/>
    </label>
</div>
<div class="alertBkDiv">
</div>
<div class="alertdiv bgred" style="opacity: 0.8; color:#fff">
</div>


<script type="text/javascript">
    $(function () {

        loadData();

        $("#unecheloned").click(function () {
            console.log("checked");
            loadData();
        });

        function  loadData() {
            $.post("/echelon/select.json", {}, function (data) {
                if (data.result.code == 0) {
                    var echelons = data.result.t;
                    var names = {name0: "", name1: "", name2: "", name3: ""};
                    for (var i = 0; i < echelons.length; i++) {
                        var eleName = "name" + echelons[i].echelon;
                        names[eleName] += "<li id=" + echelons[i].id + " class='fl ml5 mt6 w58 bgred lis'>"
                            + echelons[i].name + "</li>"
                    }
                    $(".names").html(names.name0);
                    if (!$("#unecheloned").is(':checked')) {
                        $(".echelon1").html(names.name1);
                        $(".echelon2").html(names.name2);
                        $(".echelon3").html(names.name3);
                    } else {
                        $(".echelon1").html("");
                        $(".echelon2").html("");
                        $(".echelon3").html("");
                    }
                }
            });
        }

        $(document).on("click", ".names .lis", function (event) {
            var left = event.clientX;
            var top = event.clientY;
            $("#chooseDiv").css({'left': left, 'top': top, 'z-index': 999999});
            $("#chooseDiv").fadeIn();
            event.stopPropagation();
            var curEle = $(this);
            $('input:radio[name="rdo"]').unbind('click').click(function (e) {
                var that = $('input:radio[name="rdo"]:checked');
                var val = that.val();
                curEle.remove();
                $(".echelon" + val).append(curEle.prop("outerHTML"));
                that.attr("checked", false);
                $("#chooseDiv").css({'display': 'none', 'z-index': -999999});
                e.stopPropagation();
                return;
            });
        });

        $("#chooseDiv").click(function (e) {
            e.stopPropagation();
        });

        $(document).click(function () {
            $("#chooseDiv").css({'display': 'none', 'z-index': -999999});
        });

        $(document).on("click", ".echeloned li", function () {
            var that = $(this);
            $(this).remove();
            $(".names").prepend(that.prop("outerHTML"));
        });

        $("#save").click(function () {
            var ids1 = [];
            var ids2 = [];
            var ids3 = [];
            var ids0 = [];
            $(".echelon1").find("li").each(function () {
                ids1.push($(this).attr("id"));
            });

            $(".echelon2").find("li").each(function () {
                ids2.push($(this).attr("id"));
            });

            $(".echelon3").find("li").each(function () {
                ids3.push($(this).attr("id"));
            });

            $(".names").find("li").each(function () {
                ids0.push($(this).attr("id"));
            });
            $.post("/echelon/upd.json", {
                "ids0": ids0.toString(),
                "ids1": ids1.toString(),
                "ids2": ids2.toString(),
                "ids3": ids3.toString()
            }, function (data) {
                var msg = "保存成功";
                if (data.result.code != 0) {
                    msg = data.result.detail;
                }
                $(".alertdiv").text(msg);
                $(".alertBkDiv").show();
                $(".alertdiv").fadeIn();
                 setTimeout(function () {
                     $(".alertdiv").fadeOut();
                     $(".alertBkDiv").fadeOut();
                 }, 1200);
            })
        });

    });
</script>
</body>
</html>
