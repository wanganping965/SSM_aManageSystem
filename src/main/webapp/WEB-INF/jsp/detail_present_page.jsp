<%--
  Created by IntelliJ IDEA.
  User: 未来人来xw
  Date: 2019/7/18
  Time: 15:47
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
    <title>需求详情页</title>


</head>
<body>
<a id="btn" href="/requirementManage/gotoRecordList" class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回列表页</a>
<div id="content"  style="">
    <div>
        <a id="btn_toUpdatePage" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">去修订该条记录</a>
        <a id="btn_watch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cvs'">查看该需求的过往修订版本</a>
    </div>
    <table id="dataList" class="easyui-datagrid">

    </table>

    <%--hidden存值区域--%>
    <input type="hidden" class="temp-value-kept" id="textbox-id-value" value="1">

    <input type="hidden" class="temp-value-kept" id="textbox-demandId-value" value="1">

    <input type="hidden" class="temp-value-kept" id="textbox-priority-value" value="1">
    <input type="hidden" class="temp-value-kept" id="textbox-demandStatus-value" value="1">
    <input type="hidden" class="temp-value-kept" id="textbox-batch-value" value="1">
    <input type="hidden" class="temp-value-kept" id="textbox-leadOrCooperate-value" value="1">
    <input type="hidden" class="temp-value-kept" id="textbox-versionStatus-value" value="1">
    <input type="hidden" class="temp-value-kept" id="textbox-developmentMode-value" value="1">
    <input type="hidden" class="temp-value-kept" id="textbox-isNewAddResources-value" value="1">
    <input type="hidden" class="temp-value-kept" id="textbox-isDataTransfer-value" value="1">
    <input type="hidden" class="temp-value-kept" id="textbox-isPerformanceTest-value" value="1">
    <input type="hidden" class="temp-value-kept" id="textbox-taskType-value" value="1">


</div>

<script type="text/javascript">
    $(function(){

//        重写日期输出的格式化formatter函数
        $.fn.datebox.defaults.formatter = function(date){
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            var d = date.getDate();
            return m+'/'+d+'/'+y;
        }
    })

    function transformerDateToStyle(dateString)
    {
        var dateList = dateString.split("-");
        if(dateList.length != 3){
            console.log("后台传输过来的时间格式错误！");
            $.messager.alert('提示','后台传输过来的时间格式错误！：<br />'," info");
        }
        else{
            return dateList[1]+'/' + dateList[2]+'/'+dateList[0];
        }
    }

    $(document).ready(function() {
        var id = '${id}';
        $('#dataList').datagrid({
            title: '需求列表',
            border: true,
            nowrap:false,
            method: "post",
//            pageSize: 1,//每页显示的记录条数，默认为10 
//            pageList: [1],//可以设置每页记录条数的列表 
            url: "/requirementManage/thisRecordDetails?id="+id,
            idField: 'id',
//            pagination: true,//分页控件 ***************想要分页此属性为true
            rownumbers: true,
            loadMsg : '数据正在加载,请耐心的等待...',
            remoteSort: false,
            frozenColumns:[[]], // 滚动条
            columns: [[
                {field: 'id',title:'序号',width:50,formatter:function (value,row,index) {
                    $('#textbox-id-value').val(value);
                    return value;
                }},
                {field: 'demand_id',title:'需求编号',width:80,formatter:function (value,row,index) {
                    $("#textbox-demandId-value").val(value);
                    return "" + value;
                }},

                {field: 'demand_name',title:'需求名称',width:120},
                {field: 'demand_details',title:'需求细项',width:200,},

                {field: 'demand_class',title:'需求分类',width:80},
                {field: 'demand_content',title:'需求内容',width:200},
                {field: 'priority',title:'优先级',width:63,formatter:function(value,row,index){
                    if(value == 1){
                        return "高";
                    }else if(value == 2){
                        return "中";
                    }else{
                        return "低";
                    }
                }},
                {field: 'priority_desc',title:'优先级说明',width:80},

                {field: 'business_value',title:'业务价值',width:150},
                {field: 'demand_status',title:'需求状态',width:93,formatter:function(value,row,index){
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
                {field: 'batch',title:'批次',width:83,formatter:function(value,row,index){
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
                {field: 'leadOrCooperate',title:'牵头/配合',width:83,formatter:function(value,row,index){
                    if(value == 1){
                        return "牵头";
                    }else{
                        return "配合";
                    }
                }},
                {field: 'product_name',title:'产品英文简称',width:120},
                {field: 'version_status',title:'版本情况',width:83,formatter:function(value,row,index){
                    if(value == 1){
                        return "有版本";
                    }else{
                        return "配合测试";
                    }
                }},

                {field: 'workload',title:'自主工作量',width:90},
                {field: 'external_workload',title:'其中外员工作量',width:90},
                {field: 'vender_workload',title:'厂商工作量',width:100},
                {field: 'development_model',title:'开发模式',width:78,formatter:function(value,row,index){
                    if(value == 1){
                        return "自主开发";
                    }else if(value == 2) {
                        return "厂商开发";
                    }else
                        return "合作开发";
                }},

                {field: 'main_product_situation',title:'主要涉及产品情况',width:180},
                {field: 'demand_leader',title:'需求牵头人',width:80},
                {field: 'development_leader',title:'开发牵头人',width:80},
                {field: 'task_code',title:'任务编号',width:120},

                {field: 'project_code',title:'项目编码',width:130},
                {field: 'is_newAddResources',title:'是否新增资源',width:93,formatter:function(value,row,index){
                    if(value == 1){
                        return "是";
                    }else{
                        return "否";
                    }
                }},
                {field: 'is_dataTransfer',title:'是否数据迁移',width:93,formatter:function(value,row,index){
                    if(value == 1){
                        return "是";
                    }else{
                        return "否";
                    }
                }},
                {field: 'is_performanceTest',title:'是否性能测试',width:93,formatter:function(value,row,index){
                    if(value == 1){
                        return "是";
                    }else{
                        return "否";
                    }
                }},

                {field: 'update_date',title:'更新日期',width:178},
                {field: 'technicalPlan_desc',title:'技术方案说明',width:180},
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

                {field: 'date_of_production',title:'投产日期',width:180},
                {field: 'lastest_progress',title:'最新进展',width:200},
                {field: 'description',title:'说明',width:125}
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

//            将创建datagrid的时候，下拉框中的对应的值存储在hidden input中的内容，重新来更新下拉框
            var val = $('#textbox-priority-value').val();
            $("#priority1").val(parseInt(val));   // 设置Select的Value值为4的项选中
            //            console.log("before ,val is: "+ val);
            //            console.log("after ,value is : "+$("#priority1").val());
            val = $('#textbox-demandStatus-value').val();
            $("#demandStatus1").val(parseInt(val));

            val = $('#textbox-batch-value').val();
            $("#batch1").val(parseInt(val));

            val = $('#textbox-leadOrCooperate-value').val();
            $("#leadOrCooperate1").val(parseInt(val));

            val = $('#textbox-versionStatus-value').val();
            $("#versionStatus1").val(parseInt(val));

            val = $('#textbox-developmentMode-value').val();
            $("#developmentMode1").val(parseInt(val));

            val = $('#textbox-isNewAddResources-value').val();
            $("#isNewAddResources1").val(parseInt(val));

            val = $('#textbox-isDataTransfer-value').val();
            $("#isDataTransfer1").val(parseInt(val));

            val = $('#textbox-isPerformanceTest-value').val();
            $("#isPerformanceTest1").val(parseInt(val));

            val = $('#textbox-taskType-value').val();
            $("#taskType1").val(parseInt(val));

            $('#sheduleFunctionTestVersionSubmit3').datebox({
                required:true
            });
            $('#sheduleFunctionTestVersionFinish3').datebox({
                required:true
            });
            $('#sheduleOfficialVersionSubmit3').datebox({
                required:true
            });
            $('#dateOfProduction3').datebox({
                required:true
            });
            $('#updateDate3').datebox({
                required:true
            });

            // 给按钮绑定转换到修改页面的功能
            $('#btn_toUpdatePage').bind('click',function(){
                var id = $("#textbox-id-value").val();
                var nextUrl = "/requirementManage/gotoRecordChange?id="+id;
                window.location.href = nextUrl;
            });

//            绑定查看该需求编号对应的所有历史版本的记录页面
            $('#btn_watch').bind('click',function(){
//                 获取demand_id的值，并传到后台，进入到该需求的历史版本页
                var demand_id = $('#textbox-demandId-value').val();
                var nextUrl = "/requirementManage/gotoRecordHistoryList?demand_id="+demand_id;
                window.location.href = nextUrl;
            })
        }
    });

    function fitCoulms() {
        $('#dataList').datagrid({
            fitColumns:false
        })
    }

</script>
</body>
</html>
