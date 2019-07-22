<%@ page language="java" contentType="text/html;charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--加载easyui组件--%>
<%@ include file="/WEB-INF/commonpage/easyuisupport.jsp" %>

<!DOCTYPE html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html class="loginHtml">
<head>
	<base href="<%=basePath%>">

	<meta charset="utf-8">
	<title>登录</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">


	<script type="text/javascript">
        $(function() {
            $("#login").dialog({
                title : '登录',
                backcolor:'#00f',
                iconCls : 'icon-lock',
                width : '420',
                height : '230',
                closable : false,
                minimizable : false,
                maximizable : false,
                resizable : false,
                modal : true,
                buttons : [ {
                    text : '登录',
                    iconCls : 'icon-ok',
                    handler:function(){
                        doLogin();
                    }
                } ]
            });
        });


        function doLogin(){
            if($("input[name='username']").val()=="" || $("input[name='password']").val()==""){
                $("#showMsg").html("用户名或密码为空，请输入");
                $("input[name='login']").focus();
            }
            else{
                console.log("i am clicked!!!");
                //获取选择的角色值 0-5 ,0-4 代表职能部门，5代表管理员
				var obj = $("#selectmenu").get(0);
                var roleVal = obj.selectedIndex;
                console.log("index is :"+roleVal);

//                //将隐藏传递信息的subjectId赋值
//                $("#roleValue").val(roleVal);

                $("#login").dialog("close");

				$.ajax({
				    url:'/login/login',
					type:'post',
					data:{
				        'username':$("#userName").val(),
						'password':$("#passWord").val(),
						'role':roleVal
					},
					dataType:'text',
					success:function(data){
				        if(data == "login_success"){
				            var nextUrl = "/requirementManage/gotoRecordList";
				            window.location.href = nextUrl;
						}else{
				            var errormsg="";
				            if(data == "noUser"){
				                errormsg = "用户不存在!";
							}
				            if(data == "passwordError"){
				                errormsg = "密码错误！";
							}else if(data == "noRoot"){
                                errormsg = "您在系统中无该权限！";
                            }
                			console.log(errormsg);

//							easyui的alert提示框
//                            并且，设置 EasyUI $.messager.alert控件 关闭事件
                            var msg = $.messager.alert('登录提示',errormsg,'error',function () {
                                window.location.reload();
                            });
							$(msg).window({
							    onBeforeClose:function () {
                                    window.location.reload();
                                }
							})
						}
					}

				})
            }
        }
	</script>
</head>

<body class="loginBody">
<div id="login" class="easyui-window" style="padding-top: 5px;padding-left: 15px" >
	<form id="loginForm" action="" method="post">
		<table>
			<tr>
				<td>
					<table>
						<tr><td>用户名</td><td><input  class="easyui-validatebox"   id="userName" name="username" type="text"/></td><td></td></tr>
						<tr><td>密 码</td><td><input  class="easyui-validatebox"  id="passWord" name="password" type="password"></td><td></td></tr>
						<tr>
							<td>角 色</td>
							<td>
								<select  id="selectmenu" name="roleValue">
									<option value="1">普通用户1</option>
									<option value="2">普通用户2</option>
									<option value="3">普通用户3</option>
									<option value="4">普通用户4</option>
									<option value="5">普通用户5</option>
									<option value="0">管理员</option>
								</select>
							</td>
						</tr>
						<div style="text-align: center;color: red;" id="showMsg"></div>
					</table>
				</td>
				<td>
					<img src="images/head.png"/>
				</td>
			</tr>
		</table>
		<%--&lt;%&ndash;用于存储最终选择的提交身份&ndash;%&gt;--%>
		<%--<input type="hidden" id="roleValue" name="role" value=""/>--%>
		<%----%>
	</form>

</div>



</body>


</html>