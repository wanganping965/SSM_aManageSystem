<%--
  Created by IntelliJ IDEA.
  User: 未来人来xw
  Date: 2019/7/18
  Time: 15:46
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
    <title>需求列表页</title>


</head>
<body>
<h2>Basic Layout</h2>
<div id="content"  style="">
    <table id="dataList" class="easyui-datagrid">

    </table>
</div>

<script type="text/javascript">
    $(document).ready(function() {
        $('#dataList').datagrid({
            title: '需求列表',
            border: true,
            nowrap:false,
            method: "post",
            pageSize: 20,//每页显示的记录条数，默认为10 
            pageList: [20, 40, 50, 100],//可以设置每页记录条数的列表 
            url: "/requirementManage/getAllRecordList",
            idField: 'id',
//                singleSelect: true,//是否单选
            pagination: true,//分页控件 ***************想要分页此属性为true
            rownumbers: true,
            loadMsg : '数据正在加载,请耐心的等待...',
            remoteSort: false,
            rowStyler:function(rowIndex,rowData){
                if(rowIndex % 2==0){
                    return 'color:gray';
                }
            },//设置列的style，可以改变字体颜色，背景颜色等
            frozenColumns:[[]], // 滚动条
            columns: [[
                {field: 'id',title:'序号',width:50},
                {field: 'demand_id',title:'需求编号',width:80},
                {field: 'demand_name',title:'需求名称',width:80},
//                    {field: 'demand_details',title:'需求细项',width:50},

                {field: 'priority',title:'优先级',width:80,formatter:function(value,row,index){
                    if(value == 1){
                        return "高";
                    }else if(value == 2){
                        return "中";
                    }else{
                        return "低";
                    }
                }},
                {field: 'priority_desc',title:'优先级说明',width:80},
                {field: 'demand_status',title:'需求状态',width:60,formatter:function(value,row,index){
                    if(value == 1){
                        return "意向需求";
                    }else if(value == 2){
                        return "方案制定中";
                    }else if(value == 3){
                        return "已回函，待排期";
                    }else if(value == 4){
                        return "任务已下达";
                    }else {  // value == 5
                        return "需求取消";
                    }
                }},
                {field: 'batch',title:'批次',width:30,formatter:function(value,row,index){
                    if(value == 1){
                        return "X91";
                    }else if(value == 2){
                        return "X92";
                    }else if(value == 3){
                        return "P901";
                    }else if(value == 4){
                        return "X93";
                    }else if(value == 5){
                        return "X94";
                    }else if(value == 6){
                        return "P902";
                    }else if(value == 7){
                        return "X95";
                    }else if(value == 8){
                        return "X96";
                    }else if(value == 9){
                        return "X97";
                    }else if(value == 10){
                        return "P903";
                    }else if(value == 11){
                        return "X98";
                    }else if(value == 12){
                        return "X99";
                    }else if(value == 13){
                        return "X910";
                    }else if(value == 14){
                        return "P904";
                    }else if(value == 15){
                        return "X911";
                    }else if(value == 16){
                        return "X912";
                    }else {  // value == 5
                        return "待排期";
                    }
                }},

                {field: 'business_department',title:'所属业务部门',width:50},
                {field: 'business_team',title:'所属业务团队',width:50},
                {field: 'leadOrCooperate',title:'牵头/配合',width:25,formatter:function(value,row,index){
                    if(value == 1){
                        return "牵头";
                    }else{
                        return "配合";
                    }
                }},
                {field: 'product_name',title:'产品英文简称',width:80},

                {field: 'version_status',title:'版本情况',width:40,formatter:function(value,row,index){
                    if(value == 1){
                        return "有版本";
                    }else{
                        return "配合测试";
                    }
                }},
                {field: 'development_model',title:'开发模式',width:40,formatter:function(value,row,index){
                    if(value == 1){
                        return "自主开发";
                    }else if(value == 2) {
                        return "厂商开发";
                    }else
                        return "合作开发";
                }},
                {field: 'demand_leader',title:'需求牵头人',width:30},
                {field: 'development_leader',title:'开发牵头人',width:30},


                {field: 'task_code',title:'任务编号',width:80},
                {field: 'project_code',title:'项目编码',width:80},
                {field: 'is_newAddResources',title:'是否新增资源',width:80,formatter:function(value,row,index){
                    if(value == 1){
                        return "是";
                    }else{
                        return "否";
                    }
                }},
                {field: 'is_dataTransfer',title:'是否数据迁移',width:80,formatter:function(value,row,index){
                    if(value == 1){
                        return "是";
                    }else{
                        return "否";
                    }
                }},

                {field: 'is_performanceTest',title:'是否性能测试',width:80,formatter:function(value,row,index){
                    if(value == 1){
                        return "是";
                    }else{
                        return "否";
                    }
                }},
                {field: 'update_date',title:'更新日期',width:50},
                {field: 'task_type',title:'任务类型',width:50,formatter:function(value,row,index){
                    if(value == 1){
                        return "项目";
                    }else if(value == 2) {
                        return "项目需求变更";
                    }else if(value == 3) {
                        return "维护需求变更";
                    }else if(value == 4) {
                        return "技术支持";
                    }else if(value == 5) {
                        return "联调测试";
                    }else
                        return "版本追平";
                }},
                {field: 'UAT_versionNumber',title:'UTA版本号',width:60},

                {field: 'official_versionNumber',title:'正式版本号',width:60},
                {field: 'shedule_functionTestVersion_submit',title:'计划功能测试版本提交日期',width:60},
                {field: 'shedule_functionTestVersion_finish',title:'计划功能测试完成日期',width:60},
                {field: 'shedule_officialVersion_submit',title:'计划提交正式版日期',width:60},
                {field: 'date_of_production',title:'投产日期',width:60},

                {field: 'lastest_progress',title:'最新进展',width:50},
                {field: 'description',title:'说明',width:80}
            ]]
        });
    });

    $(window).resize(function () {
        fitCoulms()
    })

    //  解决列属性太多，显示不全的问题
    $('#dataList').datagrid({
        onLoadSuccess:function(data){
            if(data.total==0){
                var dc = $(this).data('datagrid').dc;
                var header2Row = dc.header2.find('tr.datagrid-header-row');
                dc.body2.find('table').append(header2Row.clone().css({"visibility":"hidden"}));
//                    $('#dataList').datagrid('insertRow',{
//                        row:{}
//                    })
//                    $("tr[datagrid-row-index='0']").css({"visibility":"hidden"});
            }
        }
    });

    function fitCoulms() {
        $('#dataList').datagrid({
            fitColumns:true
        })
    }
</script>
</body>
</html>
