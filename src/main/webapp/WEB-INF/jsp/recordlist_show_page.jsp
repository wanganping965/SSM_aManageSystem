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
    <style type="text/css">
        ul{
            list-style-type:none;
            width:100%;
            padding:0;
            margin:0;
            float:left;
        }
        li {display:inline}
    </style>

</head>
<body>
<h2>首页 -- 所有需求列表页面</h2>
<%--<a id="btn" href="/requirementManage/gotoRecordList" class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回列表页</a>--%>
<div id="selectByConditions">
    <p>多条件查询：</p>
    <ul>
        <li>
            <label  for="demand_id5">需求编号：</label>
            <input class="easyui-textbox" id="demand_id5" style="width:60px;height: 25px " value="">

        </li>
        <li>
            <label for="demand_name5">需求名称：</label>
            <input class="easyui-textbox" id="demand_name5" style="width:60px;height: 25px" value="">

        </li>
        <li>
            <label for="priority5">优先级：</label>
            <select id="priority5" class="easyui-combobox" style="width:55px;height: 25px">
                <option value="0" selected></option>
                <option value="1">高</option>
                <option value="2">中</option>
                <option value="3">低</option>
            </select>

        </li>
        <li>
            <label for="priority_desc5">优先级说明：</label>
            <select id="priority_desc5" class="easyui-combobox" style="width:150px;height: 25px">
                <option value="0" selected></option>
                <option value="1">业务部门年度重点工作</option>
                <option value="2">业务部门绩效目标</option>
                <option value="3">行领导关注</option>
                <option value="4">业务负责人关注</option>
                <option value="5">监管要求</option>
            </select>

        </li>
        <li>
            <label for="demand_status5">需求状态：</label>
            <select id="demand_status5" class="easyui-combobox" style="width:120px;height: 25px">
                <option value="0" selected></option>
                <option value="1">意向需求</option>
                <option value="2">方案制定中</option>
                <option value="3">已回函，待排期</option>
                <option value="4">任务已下达</option>
                <option value="5">需求取消</option>
            </select>
        </li>
        <li>
            <label for="batch5">批次：</label>
            <select id="batch5" class="easyui-combobox" name="batch5" style="width:80px;height: 25px">
                <option value="0" selected></option><option value="1">X91</option>
                <option value="2">X92</option><option value="3">P901</option>
                <option value="4">X93</option><option value="5">X94</option>
                <option value="6">P902</option><option value="7">X95</option>
                <option value="8">X96</option><option value="9">X97</option>
                <option value="10">P903</option><option value="11">X98</option>
                <option value="12">X99</option><option value="13">X910</option>
                <option value="14">P904</option><option value="15">X911</option>
                <option value="16">X912</option><option value="17">待排期</option>
            </select>
        </li>
        <li>
            <label  for="business_department5">所属业务部门：</label>
            <input class="easyui-textbox" id="business_department5" style="width:60px;height: 25px" value="">
        </li>
        <li>
            <label  for="business_team5">所属业务团队：</label>
            <input class="easyui-textbox" id="business_team5" style="width:60px;height: 25px" value="">
        </li>
        <li><label for="leadOrCooperate5">牵头/配合：</label>
            <select id="leadOrCooperate5" class="easyui-combobox" style="width:70px;height: 25px">
                <option value="0" selected></option>
                <option value="1">牵头</option>
                <option value="2">配合</option>
            </select>
        </li>
        <li>
            <label for="version_status5">版本情况：</label>
            <select id="version_status5" class="easyui-combobox" name="version_status5" style="width:90px;height: 25px">
                <option value="0" selected></option>
                <option value="1">有版本</option>
                <option value="2">配合测试</option>
            </select>
        </li>
        <li>
            <label for="development_model5">开发模式：</label>
            <select id="development_model5" class="easyui-combobox" name="development_model5" style="width:90px;;height: 25px">
                <option value="0" selected></option>
                <option value="1">自主开发</option>
                <option value="2">厂商开发</option>
                <option value="3">合作开发</option>
            </select>
        </li>
        <li>
            <label  for="product_name5">产品英文简称：</label>
            <input class="easyui-textbox" id="product_name5" style="width:60px;height: 25px" value="">
        </li>
        <li>
            <label  for="task_code5">任务编号：</label>
            <input class="easyui-textbox" id="task_code5" style="width:60px;height: 25px" value="">
        </li>
        <li>
            <label  for="project_code5">项目编码：</label>
            <input class="easyui-textbox" id="project_code5" style="width:60px;height: 25px" value="">
        </li>
        <li>
            <label for="is_newAddResources5">是否新增资源：</label>
            <select id="is_newAddResources5" class="easyui-combobox" name="is_newAddResources5" style="width:75px;height: 25px">
                <option value="2" selected></option>
                <option value="1">是</option>
                <option value="0">否</option>
            </select>
        </li>
        <li>
            <label for="is_dataTransfer5">是否数据迁移：</label>
            <select id="is_dataTransfer5" class="easyui-combobox" name="is_dataTransfer5" style="width:75px;height: 25px">
                <option value="2" selected></option>
                <option value="1">是</option>
                <option value="0">否</option>
            </select>
        </li>
        <li>
            <label for="is_performanceTest5">是否性能测试：</label>
            <select id="is_performanceTest5" class="easyui-combobox" name="is_performanceTest5" style="width:75px;height: 25px">
                <option value="2" selected></option>
                <option value="1">是</option>
                <option value="0">否</option>
            </select>
        </li>
        <li>
            <label  for="task_type5">任务类型：</label>
            <select id="task_type5" class="easyui-combobox" name="task_type5" style="width:120px;height: 25px">
                <option value="0" selected></option>
                <option value="1">项目</option>
                <option value="2">项目需求变更</option>
                <option value="3">维护需求变更</option>
                <option value="4">技术支持</option>
                <option value="5">联调测试</option>
                <option value="6">版本追平</option>
            </select>
        </li>
    </ul>

    <a id="btn_search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">按条件查询</a>
    <form action="/exportExcel/getFilterRecordExpertToExcel" id="download_form" method="post" class="" style="display: inline;">
        <%--hidden存值区域--%>
        <input type="hidden" class="temp-value-kept" id="demandId-value" name="demand_id" value="">
        <input type="hidden" class="temp-value-kept" id="demandName-value" name="demand_name"  value="">
        <input type="hidden" class="temp-value-kept" id="priority-value" name="priority" value="0">
        <input type="hidden" class="temp-value-kept" id="priorityDesc-value" name="priority_desc" value="0">
        <input type="hidden" class="temp-value-kept" id="demandStatus-value" name="demand_status"  value="0">

        <input type="hidden" class="temp-value-kept" id="batch-value" name="batch"  value="0">
        <input type="hidden" class="temp-value-kept" id="businessDepartment-value" name="business_department"  value="">
        <input type="hidden" class="temp-value-kept" id="businessTeam-value" name="business_team" value="">
        <input type="hidden" class="temp-value-kept" id="leadOrCooperate-value" name="leadOrCooperate"  value="0">
        <input type="hidden" class="temp-value-kept" id="versionStatus-value" name="version_status"  value="0">

        <input type="hidden" class="temp-value-kept" id="developmentModel-value" name="development_model"  value="0">
        <input type="hidden" class="temp-value-kept" id="productName-value" name="product_name"  value="">
        <input type="hidden" class="temp-value-kept" id="taskCode-value" name="task_code"  value="">
        <input type="hidden" class="temp-value-kept" id="projectCode-value" name="project_code"  value="">
        <input type="hidden" class="temp-value-kept" id="isNewAddResources-value" name="is_newAddResources"  value="2">

        <input type="hidden" class="temp-value-kept" id="isDataTransfer-value" name="is_dataTransfer" value="2">
        <input type="hidden" class="temp-value-kept" id="is_performanceTest-value" name="is_performanceTest" value="2">
        <input type="hidden" class="temp-value-kept" id="taskType-value" name="task_type"  value="0">

        <input type="button" class="easyui-linkbutton" data-options="iconCls:'icon-save'" id="expert_excel" value="导出数据"/>
    </form>
</div>
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
            pageSize: 3,//每页显示的记录条数，默认为10 
            pageList: [3, 40, 50, 100],//可以设置每页记录条数的列表 
            url: "/requirementManage/getAllRecordListByFilters",
            idField: 'id',
            toolbar: [{
                text:"新增",
                iconCls: 'icon-add',
                handler: function(){
                    addRecord();
                }
            },'-',{
                text:"修改",
                iconCls: 'icon-edit',
                handler: function(){
                    updateOneRecord();
                }
            },'-',{
                text:"删除",
                iconCls: 'icon-remove',
                handler: function(){
                    deleteRecords();
                }
            },'-',{
                text:"帮助",
                iconCls: 'icon-help',
                handler: function(){
                    alert('help')
                }
            },'-',{
                text:"查看详情",
                iconCls: 'icon-add',
                handler: function(){
                    viewThisRecord();
                }
            },'-',{
                text:"查看历史修改记录",
                iconCls: 'icon-filter',
                handler: function(){
                    viewThisRecordHistoryList();
                }
            }
//            ,'-',{
//                text:"多条件查询后导出为Excel",
//                iconCls: 'icon-save',
//                handler: function(){
//                    exportTOExcel();
//                }
//            }
            ,'-',{
                text:"通过Excel导入数据",
                iconCls: 'icon-add',
                handler: function(){
                    importDataFromExcel();
                }
            }],
            striped:true, // 条纹化，奇偶行不同背景
            singleSelect: false, <%-- 多行选择--%>
            checkOnSelect:true, <%-- 复选框--%>
            pagination: true,//分页控件 ***************想要分页此属性为true
            rownumbers: true,
            loadMsg : '数据正在加载,请耐心的等待...',
            remoteSort: false,
            rowStyler:function(rowIndex,rowData){
                if(rowIndex % 2==0){
                    return 'background-color:pink;color:blue;font-weight:bold;';
                }
            },//设置列的style，可以改变字体颜色，背景颜色等
            frozenColumns:[[]], // 滚动条
            columns: [[
                {field: 'id',title:'序号',width:50, checkbox:true},
                {field: 'demand_id',title:'需求编号',width:80},
                {field: 'demand_name',title:'需求名称',width:80},
//                    {field: 'demand_details',title:'需求细项',width:50},

                {field: 'priority',title:'优先级',width:60,formatter:function(value,row,index){
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
                {field: 'batch',title:'批次',width:40,formatter:function(value,row,index){
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

                {field: 'business_department',title:'所属业务部门',width:90},
                {field: 'business_team',title:'所属业务团队',width:90},
                {field: 'leadOrCooperate',title:'牵头/配合',width:60,formatter:function(value,row,index){
                    if(value == 1){
                        return "牵头";
                    }else{
                        return "配合";
                    }
                }},
                {field: 'product_name',title:'产品英文简称',width:120},

                {field: 'version_status',title:'版本情况',width:80,formatter:function(value,row,index){
                    if(value == 1){
                        return "有版本";
                    }else{
                        return "配合测试";
                    }
                }},
                {field: 'development_model',title:'开发模式',width:70,formatter:function(value,row,index){
                    if(value == 1){
                        return "自主开发";
                    }else if(value == 2) {
                        return "厂商开发";
                    }else
                        return "合作开发";
                }},
                {field: 'demand_leader',title:'需求牵头人',width:80},
                {field: 'development_leader',title:'开发牵头人',width:80},


                {field: 'task_code',title:'任务编号',width:80},
                {field: 'project_code',title:'项目编码',width:130},
                {field: 'is_newAddResources',title:'是否新增资源',width:90,formatter:function(value,row,index){
                    if(value == 1){
                        return "是";
                    }else{
                        return "否";
                    }
                }},
                {field: 'is_dataTransfer',title:'是否数据迁移',width:90,formatter:function(value,row,index){
                    if(value == 1){
                        return "是";
                    }else{
                        return "否";
                    }
                }},

                {field: 'is_performanceTest',title:'是否性能测试',width:90,formatter:function(value,row,index){
                    if(value == 1){
                        return "是";
                    }else{
                        return "否";
                    }
                }},
                {field: 'update_date',title:'更新日期',width:80},
                {field: 'task_type',title:'任务类型',width:80,formatter:function(value,row,index){
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
                {field: 'UAT_versionNumber',title:'UTA版本号',width:140},

                {field: 'official_versionNumber',title:'正式版本号',width:140},
                {field: 'shedule_functionTestVersion_submit',title:'计划功能测试版本提交日期',width:180},
                {field: 'shedule_functionTestVersion_finish',title:'计划功能测试完成日期',width:180},
                {field: 'shedule_officialVersion_submit',title:'计划提交正式版日期',width:180},
                {field: 'date_of_production',title:'投产日期',width:100},

                {field: 'lastest_progress',title:'最新进展',width:100},
                {field: 'description',title:'说明',width:120}
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
            }
        }
    });

    function fitCoulms() {
        $('#dataList').datagrid({
            fitColumns:false
        })
    }

    function deleteRecords()
    {
        var datagrid = $('#dataList');
        //getChecked:在复选框呗选中的时候返回所有行。（该方法自1.3版开始可用）
        var row = datagrid.datagrid("getChecked");
        if(row.length >= 1){
            $.messager.confirm('确定','是否确定<span style="color: red;font-size: 20px;">删除选中的'+row.length+'条</span>数据？',function (r) {
                if(r){
                    for(var i = row.length-1;i >= 0; i --){
                        $.get("/requirementManage/deleteAllHistoryRecord",{demand_id:row[i].demand_id},
                            function (result){
                                if(result == "ok"){
                                    datagrid.datagrid("reload"); //刷新页面
                                }
                                else{
                                    $.messager.alert('提示','此数据删除失败：<br />'+result.msg,"info");
                                }
                            }
                        )
                    }
                }
            });
        }else{
            $.messager.alert('提示','您未勾选要删除的数据',"info");
        }
    }

    function updateOneRecord() {
        var datagrid = $('#dataList');
        //getChecked:在复选框呗选中的时候返回所有行。（该方法自1.3版开始可用）
        var row = datagrid.datagrid("getChecked");
        if(row.length == 1){
            var nextUrl = "/requirementManage/gotoRecordChange?id="+row[0].id;
            window.location.href = nextUrl;
        }else if(row.length == 0){
            $.messager.alert('提示','您还未选择记录！',"info");
        }else{
            $.messager.alert('提示','您选择超过一条记录！',"info");
        }
    }

    function viewThisRecord() {
        var datagrid = $('#dataList');
        //getChecked:在复选框呗选中的时候返回所有行。（该方法自1.3版开始可用）
        var row = datagrid.datagrid("getChecked");
        if(row.length == 1){
            var nextUrl = "/requirementManage/gotoRecordDetails?id="+row[0].id;
            window.location.href = nextUrl;
        }else if(row.length == 0){
            $.messager.alert('提示','您还未选择记录！',"info");
        }else{
            $.messager.alert('提示','您选择超过一条记录！',"info");
        }
    }
    
    function addRecord() {
        var nextUrl = "/requirementManage/gotoRecordAdd";
        window.location.href = nextUrl;
    }

    // 将当前满足条件的记录导出到excel中，并下载(form表格方式提交)
    $("#expert_excel").on('click',function () {
        console.log("hello,i was clicked! for download");
        $("#download_form").submit();
    })

    // 将当前满足条件的记录导出到excel中，并下载（ajax方式提交，不可用）
    function exportTOExcel() {
        //   $("#dataList").datagrid('toExcel','records.xls');

        //   获取当前页面的条件搜索条件
        var demand_id5 = $("#demand_id5").val();
        var demand_name5 = $("#demand_name5").val();
        var priority5 = $("#priority5").val();
        var priority_desc5 = $("#priority_desc5").val();
        var demand_status5 = $("#demand_status5").val();

        var batch5 = $("#batch5").val();
        var business_department5 = $("#business_department5").val();
        var business_team5 = $("#business_team5").val();
        var leadOrCooperate5 = $("#leadOrCooperate5").val();
        var version_status5 = $("#version_status5").val();

        var development_model5 = $("#development_model5").val();
        var product_name5 = $("#product_name5").val();
        var task_code5 = $("#task_code5").val();
        var project_code5 = $("#project_code5").val();
        var is_newAddResources5 = $("#is_newAddResources5").val();

        var is_dataTransfer5 = $("#is_dataTransfer5").val();
        var is_performanceTest5 = $("#is_performanceTest5").val();
        var task_type5 = $("#task_type5").val();

        $.ajax({
            url:"/exportExcel/getFilterRecordExpertToExcel",
            type:"post",
            data:{
                'demand_id':demand_id5,
                'demand_name':demand_name5,
                'priority':priority5,
                'priority_desc':priority_desc5,
                'demand_status':demand_status5,
                'batch':batch5,
                'business_department':business_department5,
                'business_team':business_team5,
                'leadOrCooperate':leadOrCooperate5,
                'version_status':version_status5,
                'development_model':development_model5,
                'product_name':product_name5,
                'task_code':task_code5,
                'project_code':project_code5,
                'is_newAddResources':is_newAddResources5,
                'is_dataTransfer':is_dataTransfer5,
                'is_performanceTest':is_performanceTest5,
                'task_type':task_type5
            },
            success:function (data) {
                if(data == "success"){
                    $.messager.alert('提示','按当前条件下载记录到Excel成功！',"info");
                }
                else{
                    $.messager.alert('提示','信息导出失败！',"error");
                }
            }
        })
    }

//    跳转到Excel数据导入页
    function importDataFromExcel(){
        var nextUrl = "/login/gotoLeadingExcelkPage";
        window.location.href = nextUrl;
    }
    function viewThisRecordHistoryList(){
        var datagrid = $('#dataList');
        //getChecked:在复选框呗选中的时候返回所有行。（该方法自1.3版开始可用）
        var row = datagrid.datagrid("getChecked");
        if(row.length == 1){
            var nextUrl = "/requirementManage/gotoRecordHistoryList?demand_id="+row[0].demand_id;
            window.location.href = nextUrl;
        }else if(row.length == 0){
            $.messager.alert('提示','您还未选择记录！',"warning");
        }else{
            $.messager.alert('提示','您选择超过一条记录！',"warning");
        }
    }


    $("#btn_search").bind("click",function () {
        //获取搜索框的值
        var demand_id5 = $("#demand_id5").val();
        var demand_name5 = $("#demand_name5").val();
        var priority5 = $("#priority5").val();
        var priority_desc5 = $("#priority_desc5").val();
        var demand_status5 = $("#demand_status5").val();

        var batch5 = $("#batch5").val();
        var business_department5 = $("#business_department5").val();
        var business_team5 = $("#business_team5").val();
        var leadOrCooperate5 = $("#leadOrCooperate5").val();
        var version_status5 = $("#version_status5").val();

        var development_model5 = $("#development_model5").val();
        var product_name5 = $("#product_name5").val();
        var task_code5 = $("#task_code5").val();
        var project_code5 = $("#project_code5").val();
        var is_newAddResources5 = $("#is_newAddResources5").val();

        var is_dataTransfer5 = $("#is_dataTransfer5").val();
        var is_performanceTest5 = $("#is_performanceTest5").val();
        var task_type5 = $("#task_type5").val();

        //将搜索框的值复制到数据表格下载的form中的hidden的input框中来,以提供进行数据下载时候的条件参数
        $("#demandId-value").val(demand_id5);
        $("#demandName-value").val(demand_name5);
        $("#priority-value").val(priority5);
        $("#priorityDesc-value").val(priority_desc5);
        $("#demandStatus-value").val(demand_status5);

        $("#batch-value").val(batch5);
        $("#businessDepartment-value").val(business_department5);
        $("#businessTeam-value").val(business_team5);
        $("#leadOrCooperate-value").val(leadOrCooperate5);
        $("#versionStatus-value").val(version_status5);

        $("#developmentModel-value").val(development_model5);
        $("#productName-value").val(product_name5);
        $("#taskCode-value").val(task_code5);
        $("#projectCode-value").val(project_code5);
        $("#isNewAddResources-value").val(is_newAddResources5);

        $("#isDataTransfer-value").val(is_dataTransfer5);
        $("#is_performanceTest-value").val(is_performanceTest5);
        $("#taskType-value").val(task_type5);
//        console.log("five, i am here!");

        $("#dataList").datagrid('load',{
            demand_id:demand_id5,
            demand_name:demand_name5,
            priority:priority5,
            priority_desc:priority_desc5,
            demand_status:demand_status5,
            batch:batch5,
            business_department:business_department5,
            business_team:business_team5,
            leadOrCooperate:leadOrCooperate5,
            version_status:version_status5,
            development_model:development_model5,
            product_name:product_name5,
            task_code:task_code5,
            project_code:project_code5,
            is_newAddResources:is_newAddResources5,
            is_dataTransfer:is_dataTransfer5,
            is_performanceTest:is_performanceTest5,
            task_type:task_type5
        })
    })
</script>
</body>
</html>
