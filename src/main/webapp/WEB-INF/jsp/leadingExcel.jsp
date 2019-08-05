<%--
  Created by IntelliJ IDEA.
  User: 未来人来xw
  Date: 2019/7/30
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
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
<html>
<head>
    <title>excel导入页</title>
    <script type="text/javascript">

        /*  ajax 方式上传文件操作 */
        $(document).ready(function(){
            $("#btn").click(function(){ if(checkData()){
                $('#form1').ajaxSubmit({
                    url:'/uploadExcel/ajax',
                    dataType: 'text',
                    success: resutlMsg,
                    error: errorMsg
                });
                function resutlMsg(msg){
                    alert(msg);
                    $("#upfile").val("");
                }
                function errorMsg(){
                    alert("导入excel出错！");
                }
            }
            });
        });

        //JS校验form表单信息
        function checkData(){
            var fileDir = $("#upfile").val();
            var suffix = fileDir.substr(fileDir.lastIndexOf("."));
            if("" == fileDir){
                alert("选择需要导入的Excel文件！");
                return false;
            }
            if(".xls" != suffix && ".xlsx" != suffix ){
                alert("选择Excel格式的文件导入！");
                return false;
            }
            return true;
        }
    </script>

    <script type="text/css">
        .select_logout_button{
            position: fixed;/*固定位置*/
            z-index:99;/*设置优先级显示，保证不会被覆盖*/
            right:0px; top:0px;  /* 固定在右上角 */
            text-align: center;
        }
    </script>
</head>
<body>
    <h2>Excel报表数据上传页</h2>

    <a id="btn111" href="/requirementManage/gotoRecordList" class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回首页</a>
    <a id="btn22" href="/login/logout" class="easyui-linkbutton select_logout_button" data-options="iconCls:'icon-cancel'">登出</a>

    <br><br>
    <form method="POST"  enctype="multipart/form-data" id="form1" action="uploadExcel/form">

        <label>上传文件: </label>
        <input id="upfile" type="file" name="upfile"><br> <br>

        <%--<input type="submit" value="表单提交" onclick="return checkData()">--%>
        <input type="button" value="ajax提交" id="btn" name="btn" >

    </form>
</body>
</html>
