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
    <title>模板</title>
    <script src="/static/js/jquery.min.js"></script>
    <link rel="stylesheet" href="/static/css/core.css"/>
    <style type="text/css">
        table {
            table-layout: fixed;
            empty-cells: show;
            border-collapse: collapse;
            margin: 0 0 15px 0px;
        }

        td {
            height: 20px;
        }

        table.t {
            border: 1px solid #ea7264;
            color: #666;
        }

        table.t td {
            border: 1px dotted #f0cfcf;
            padding: 0 2px 0;
            font-size: 13px;
            padding: 3px 0 5px 3px;
        }

        table.t tr.a1 {
            background-color: #fceded;
        }

        .tdl {
            padding-right: 2px;
            text-align: center;
            width: 24%
        }

        .divcss {
            border: 1px solid #ea7264;
            background: #fceded;
            padding: 4px 0 3px 3px;
            font-size: 13px;
            color: #666
        }

    </style>
</head>
<body>
<div style="border-bottom: 2px ridge #b6262f;" class="pb2">
    <a href="javascript:history.back();"  id="back" class="fl f18 m0 p0 dib red tdn">返回</a>
    <span class="mleft30">详细信息</span>
    <a href="####" id="homepage" class="fr f18 m0 p0 dib red tdn">首页</a>
</div>
<c:if test="${data.code!=0}">
    <span>${data.detail}</span>
</c:if>
<c:if test="${data.code==0&&data.t!=null}">
    <br/>
    <h class="f15 fl">基本信息</h>
    <input type="button" value="编辑" id="btn" status="1" class="fr w50 h20 bgred"
           style="border: none; color:#fff;margin-bottom: 3px"/>
    <table width="100%" class="t">
        <tr class="a1">
            <td class="tdl">姓&nbsp;&nbsp;名</td>
            <td id="name">${data.t.name}</td>
        </tr>
        <tr>
            <td class="tdl">性&nbsp;&nbsp;别</td>
            <td id="sex1">
                <select id="sex" disabled>
                    <option value="-1" <c:if test="${data.t.sex==-1}">selected</c:if>>未设置</option>
                    <option value="0" <c:if test="${data.t.sex==0}">selected</c:if>>男</option>
                    <option value="1" <c:if test="${data.t.sex==1}">selected</c:if>>女</option>
                </select>
            </td>
        </tr>
        <tr class="a1">
            <td class="tdl">年&nbsp;&nbsp;龄</td>
            <td id="age">${data.t.age}</td>
        </tr>
        <tr>
            <td class="tdl">籍&nbsp;&nbsp;贯</td>
            <td id="nativePlace">${data.t.nativePlace}</td>
        </tr>
        <tr class="a1">
            <td class="tdl">工作地</td>
            <td id="workplace">${data.t.workplace}</td>
        </tr>
        <tr>
            <td class="tdl">健康状况</td>
            <td id="health">${data.t.health}</td>
        </tr>
        <tr class="a1">
            <td class="tdl">文化程度</td>
            <td id="education">${data.t.education}</td>
        </tr>
        <tr>
            <td class="tdl">体貌特征</td>
            <td id="characteristics">${data.t.characteristics}</td>
        </tr>
    </table>

    <h class="f15">与我的关系</h>
    <table width="100%" class="t">
        <tr class="a1">
            <td class="tdl">关&nbsp;&nbsp;系</td>
            <td id="relationship">${data.t.relationship}</td>
        </tr>
        <tr>
            <td class="tdl">相处模式</td>
            <td id="coexistenceMode">${data.t.coexistenceMode}</td>
        </tr>
        <tr class="a1">
            <td class="tdl">对我评价</td>
            <td id="evaluateMe">${data.t.evaluateMe}</td>
        </tr>
    </table>

    <h class="f15">工作情况</h>
    <table width="100%" class="t">
        <tr class="a1">
            <td class="tdl">工作经历</td>
            <td id="workExperience">${data.t.workExperience}</td>
        </tr>
        <tr>
            <td class="tdl">人生阅历</td>
            <td id="lifeExperience">${data.t.lifeExperience}</td>
        </tr>
        <tr class="a1">
            <td class="tdl">收入情况</td>
            <td id="income">${data.t.income}</td>
        </tr>
        <tr>
            <td class="tdl">经商创业</td>
            <td id="entrepreneurship">${data.t.entrepreneurship}</td>
        </tr>
        <tr class="a1">
            <td class="tdl">休假情况</td>
            <td id="vacations">${data.t.vacations}</td>
        </tr>
    </table>

    <h class="f15">情感婚姻状况</h>
    <table width="100%" class="t">
        <tr class="a1">
            <td class="tdl">婚姻状况</td>
            <td id="maritalStatus">${data.t.maritalStatus}</td>
        </tr>
    </table>
    <h class="f15">家庭情况</h>
    <table width="100%" class="t">
        <tr class="a1">
            <td class="tdl">家庭成员</td>
            <td id="memberOfFamily">${data.t.memberOfFamily}</td>
        </tr>
        <tr>
            <td class="tdl">家庭地位</td>
            <td id="familyStatus">${data.t.familyStatus}</td>
        </tr>
        <tr class="a1">
            <td class="tdl">收入支出</td>
            <td id="familyIncomeAndSupport">${data.t.familyIncomeAndSupport}</td>
        </tr>
    </table>

    <h class="f15">个人性格</h>
    <table width="100%" class="t">
        <tr class="a1">
            <td class="tdl">性格特点</td>
            <td id="character">${data.t.character}</td>
        </tr>
        <tr>
            <td class="tdl">政策关注</td>
            <td id="policyConcern">${data.t.policyConcern}</td>
        </tr>
        <tr class="a1">
            <td class="tdl">兴趣爱好</td>
            <td id="hobby">${data.t.hobby}</td>
        </tr>
        <tr>
            <td class="tdl">特&nbsp;&nbsp;长</td>
            <td id="specialty">${data.t.specialty}</td>
        </tr>
        <tr class="a1">
            <td class="tdl">喜欢话题</td>
            <td id="topicOfLike">${data.t.topicOfLike}</td>
        </tr>
        <tr>
            <td class="tdl">有无梦想</td>
            <td id="dream">${data.t.dream}</td>
        </tr>
        <tr class="a1">
            <td class="tdl">思想观念</td>
            <td id="concept">${data.t.concept}</td>
        </tr>
        <tr>
            <td class="tdl">喜爱读书</td>
            <td id="loveReading">${data.t.loveReading}</td>
        </tr>
    </table>
    <h class="f15">邀约理由</h>
    <div class="divcss mb10" id="reasonOfInvite">${data.t.reasonOfInvite}</div>
    <h class="f15">其他</h>
    <div class="divcss" id="others">${data.t.others}</div>
    <input type="text" value="${data.t.id}" id="id" disabled hidden/>
</c:if>

<script type="text/javascript">

    var curAttrs = ["id", "name", "sex", "age", "nativePlace", "workplace", "health", "education",
        "characteristics", "relationship", "coexistenceMode", "evaluateMe",
        "workExperience", "lifeExperience", "income", "entrepreneurship", "vacations",
        "maritalStatus", "memberOfFamily", "familyStatus", "familyIncomeAndSupport",
        "character", "policyConcern", "hobby", "specialty", "topicOfLike", "dream",
        "concept", "loveReading", "reasonOfInvite", "others"];

    var lock = false;
    $("#btn").click(function () {
        if (lock) {
            return;
        }
        lock = true;
        var that = $(this);
        var status = that.attr("status");
        if (status == "1") {
            for (var i = 0; i < curAttrs.length; i++) {
                $("#" + curAttrs[i]).attr("contenteditable", "true");
            }
            $("#sex").removeAttr("disabled");
            that.attr("status", "2");
            that.val("保存");
            $("#name").focus();
        } else {
            var params ={};
            for (var i = 0; i < curAttrs.length; i++) {
                var curEle = $("#" + curAttrs[i]);
                params[curAttrs[i]] = curEle.text();
                curEle.attr("contenteditable", "false");
            }
            doSave(params);
            that.attr("status", "1");
            that.val("编辑");
            $("#sex").attr("disabled", true);
        }
        lock = false;
    });

    function doSave(params) {
        params.sex = $("#sex").val();
        params.id = $("#id").val();
        $.post("/name/modify.json", params, function (data) {
            if (data.result.code == 0) {
                location.reload();
            }
        });
    }

</script>
</body>
</html>