<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/commonpage/easyuisupport.jsp" %>

<html>
<body>
<h2>Hello World!</h2>
<a href="${pageContext.request.contextPath}/user/findAll">查询所有用户</a>

<div class="easyui-dialog" style="width:400px;height:200px"
     data-options="title:'Hello EasyUI',collapsible:true,iconCls:'icon-ok',onOpen:function(){}">
    this is content!!!
</div>

<jsp:forward page="login/toLogin"></jsp:forward>
</body>
</html>
