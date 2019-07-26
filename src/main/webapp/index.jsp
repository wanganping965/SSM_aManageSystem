<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/commonpage/easyuisupport.jsp" %>

<html>
<body>
<h2>Hello World!</h2>
<%--<a href="${pageContext.request.contextPath}/user/findAll">查询所有用户</a>--%>

<div class="easyui-dialog" style="width:400px;height:200px"
     data-options="title:'Hello EasyUI',collapsible:true,iconCls:'icon-ok',onOpen:function(){}">
    this is content!!!
</div>
<div id=test>
    <input id="priority_desc" style="width:150px;height: 60px" name="priority_desc" value="">
    <a id="btn_keep" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">保存修改</a>
    <input type="hidden" id="keep_value" value="1">
</div>


<%--项目登录入口--%>
<jsp:forward page="login/toLogin"></jsp:forward>
</body>

<script type="text/javascript">
//    $('#priority_desc').combobox({
//        url:'combobox_data.json',
//        panelHeight:300,
//        valueField:'id',
//        textField:'text',
//        multiple:true, //设置可以复选
//        formatter:function (row) {
//            var opts = $(this).combobox('options');
//            return '<input type="checkbox" class="combobox-checkbox">' + row[opts.textField];//关键在这一步，在项前面加一个checkbox。opts这个是combobox对象。
//
//        }
//    });
//
//    $("#btn_keep").bind("click",function () {
//        $("#keep_value").val($('#priority_desc').val());
//        console.log($('#priority_desc').val());
//        console.log($("#priority_desc").combobox('getValue'))
//    })
</script>
</html>
