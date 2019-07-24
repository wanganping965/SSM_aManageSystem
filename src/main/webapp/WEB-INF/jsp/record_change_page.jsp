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
<h2>首页 -- 所有需求列表页面</h2>
<div id="content"  style="">
    <a id="btn_keep" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">保存修改</a>
    <a id="btn_cancel" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-refresh'">取消</a>
    <table id="dataList" class="easyui-datagrid">

    </table>

    <%--hidden存值区域--%>
    <input type="hidden" class="temp-value-kept" id="textbox-id-value" value="1">
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

    })
    $(document).ready(function() {
        var id = '${id}';
//        console.log("id is: "+id);
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
                {field: 'demand_id',title:'需求编号',width:80},

                {field: 'demand_name',title:'需求名称',width:120,formatter:function (value,row,index) {
                    return "<input class=\"easyui-textbox\" id='demandName2' data-options=\"multiline:true\" style=\"width:120px;height: 60px\" value=\""+ value +"\">";
                }},
                {field: 'demand_details',title:'需求细项',width:200,formatter:function (value,row,index) {
                    return "<input class=\"easyui-textbox\" id='demandDetails2' data-options=\"multiline:true\" style=\"width:200px;height: 60px\" value=\""+ value +"\">";
                }},

                {field: 'demand_class',title:'需求分类',width:80,formatter:function (value,row,index) {
                    return "<input class=\"easyui-textbox\" id='demandClass2' data-options=\"multiline:true\" style=\"width:80px;height: 60px\" value=\""+ value +"\">";
                }},
                {field: 'demand_content',title:'需求内容',width:200,formatter:function (value,row,index) {
                    return "<input class=\"easyui-textbox\" id='demandContent2' data-options=\"multiline:true\" style=\"width:200px;height: 60px\" value=\""+ value +"\">";
                }},
                {field: 'priority',title:'优先级',width:60,formatter:function(value,row,index){
//                    if(value == 1){
//                        return "高";
//                    }else if(value == 2){
//                        return "中";
//                    }else{
//                        return "低";
//                    }
                    // 将这个值存储在hidden input里面，后续再存进下拉框中
//                    var it = document.getElementsByName("")
                    $('#textbox-priority-value').val(value);
//                    console.log("创建时，value is : "+ value);
                    console.log($('#textbox-priority-value').val());

                    return '<select id="priority1" class="easyui-combobox" name="priority1" style="width:60px;"><option value="1">高</option><option value="2">中</option><option value="3">低</option></select>';
            }},
                {field: 'priority_desc',title:'优先级说明',width:80},

                {field: 'business_value',title:'业务价值',width:150,formatter:function (value,row,index) {
                    return "<input class=\"easyui-textbox\" id='businessValue2' style=\"width:150px;height: 60px\" value=\""+ value +"\">";
                }},
                {field: 'demand_status',title:'需求状态',width:60,formatter:function(value,row,index){

                    $('#textbox-demandStatus-value').val(value);
                    return '<select id="demandStatus1" class="easyui-combobox" name="demandStatus1" style="width:60px;"><option value="1">意向需求</option><option value="2">方案制定中</option><option value="3">已回函，待排期</option><option value="4">任务已下达</option><option value="5">需求取消</option></select>';
                }},
                {field: 'batch',title:'批次',width:40,formatter:function(value,row,index){
                    $('#textbox-batch-value').val(value);
                    return '<select id="batch1" class="easyui-combobox" name="batch1" style="width:40px;"><option value="1">X91</option><option value="2">X92</option><option value="3">P901</option><option value="4">X93</option><option value="5">X94</option><option value="6">P902</option><option value="7">X95</option><option value="8">X96</option><option value="9">X97</option><option value="10">P903</option><option value="11">X98</option><option value="12">X99</option><option value="13">X910</option><option value="14">P904</option><option value="15">X911</option><option value="16">X912</option><option value="17">待排期</option></select>'
                }},
                {field: 'business_department',title:'所属业务部门',width:90,formatter:function (value,row,index) {
                    return "<input class=\"easyui-textbox\" id='businessDepartment2' style=\"width:90px;height: 60px\" value=\""+ value +"\">";
                }},

                {field: 'business_team',title:'所属业务团队',width:90,formatter:function (value,row,index) {
                    return "<input class=\"easyui-textbox\" id='businessTeam2' style=\"width:90px;height: 60px\" value=\""+ value +"\">";
                }},
                {field: 'leadOrCooperate',title:'牵头/配合',width:80,formatter:function(value,row,index){
                    $('#textbox-leadOrCooperate-value').val(value);
                    return '<select id="leadOrCooperate1" class="easyui-combobox" name="leadOrCooperate1" style="width:80px;"><option value="1">牵头</option><option value="2">配合</option></select>'
                }},
                {field: 'product_name',title:'产品英文简称',width:120,formatter:function (value,row,index) {
                    return "<input class=\"easyui-textbox\" id='productName2' style=\"width:120px;height: 60px\" value=\""+ value +"\">";
                }},
                {field: 'version_status',title:'版本情况',width:80,formatter:function(value,row,index){
                    $('#textbox-versionStatus-value').val(value);
                    return '<select id="versionStatus1" class="easyui-combobox" name="versionStatus1" style="width:80px;"><option value="1">有版本</option><option value="2">配合测试</option></select>'
                }},

                {field: 'workload',title:'自主工作量',width:90,formatter:function (value,row,index) {
                    return "<input class=\"easyui-textbox\" id='workload2' style=\"width:90px;height: 60px\" value=\""+ value +"\">";
                }},
                {field: 'external_workload',title:'其中外员工作量',width:90,formatter:function (value,row,index) {
                    return "<input class=\"easyui-textbox\" id='externalWorkload2' style=\"width:90px;height: 60px\" value=\""+ value +"\">";
                }},
                {field: 'vender_workload',title:'厂商工作量',width:100,formatter:function (value,row,index) {
                    return "<input class=\"easyui-textbox\" id='venderWorkload2' style=\"width:100px;height: 60px\" value=\""+ value +"\">";
                }},
                {field: 'development_model',title:'开发模式',width:70,formatter:function(value,row,index){
                    $('#textbox-developmentMode-value').val(value);
                    return '<select id="developmentModel1" class="easyui-combobox" name="developmentModel1" style="width:70px;"><option value="1">自主开发</option><option value="2">厂商开发</option><option value="3">合作开发</option></select>'
                }},

                {field: 'main_product_situation',title:'主要涉及产品情况',width:180,formatter:function (value,row,index) {
                    return "<input class=\"easyui-textbox\" id='mainProductSituation2' style=\"width:180px;height: 60px\" + value=\""+ value +"\">";
                }},
                {field: 'demand_leader',title:'需求牵头人',width:80,formatter:function (value,row,index) {
                    return "<input class=\"easyui-textbox\" id='demandLeader2' style=\"width:80px;height: 60px\" value=\""+ value +"\">";
                }},
                {field: 'development_leader',title:'开发牵头人',width:80,formatter:function (value,row,index) {
                    return "<input class=\"easyui-textbox\" id='developmentLeader2' style=\"width:80px;height: 60px\" value=\""+ value +"\">";
                }},
                {field: 'task_code',title:'任务编号',width:120,formatter:function (value,row,index) {
                    return "<input class=\"easyui-textbox\" id='taskCode2' style=\"width:120px;height: 60px\" value=\""+ value +"\">";
                }},

                {field: 'project_code',title:'项目编码',width:130,formatter:function (value,row,index) {
                    return "<input class=\"easyui-textbox\" id='projectCode2' style=\"width:130px;height: 60px\" value=\""+ value +"\">";
                }},
                {field: 'is_newAddResources',title:'是否新增资源',width:90,formatter:function(value,row,index){
                    $('#textbox-isNewAddResources-value').val(value);
                    return '<select id="isNewAddResources1" class="easyui-combobox" name="isNewAddResources1" style="width:90px;"><option value="1">是</option><option value="0">否</option></select>'
                }},
                {field: 'is_dataTransfer',title:'是否数据迁移',width:90,formatter:function(value,row,index){
                    $('#textbox-isDataTransfer-value').val(value);
                    return '<select id="isDataTransfer1" class="easyui-combobox" name="isDataTransfer1" style="width:90px;"><option value="1">是</option><option value="0">否</option></select>'
                }},
                {field: 'is_performanceTest',title:'是否性能测试',width:90,formatter:function(value,row,index){
                    $('#textbox-isperformancetest-value').val(value);

                    return '<select id="isPerformanceTest1" class="easyui-combobox" name="isPerformanceTest1" style="width:90px;"><option value="1">是</option><option value="0">否</option></select>'
                }},


                {field: 'update_date',title:'更新日期',width:80},
                {field: 'technicalPlan_desc',title:'技术方案说明',width:180,formatter:function (value,row,index) {
                    return "<input class=\"easyui-textbox\" id='technicalPlanDesc2' style=\"width:180px;height: auto\" value=\""+ value +"\">";
                }},
                {field: 'task_type',title:'任务类型',width:80,formatter:function(value,row,index){
                    $('#textbox-taskType-value').val(value);
                    return '<select id="taskType1" class="easyui-combobox" name="taskType1" style="width:80px;"><option value="1">项目</option><option value="2">项目需求变更</option><option value="3">维护需求变更</option><option value="4">技术支持</option><option value="5">联调测试</option><option value="6">版本追平</option></select>'
                }},
                {field: 'UAT_versionNumber',title:'UTA版本号',width:140,formatter:function (value,row,index) {
                    return "<input class=\"easyui-textbox\" id='UAT_versionNumber2' style=\"width:140px;height: auto\" value=\""+ value +"\">";
                }},

                {field: 'official_versionNumber',title:'正式版本号',width:140,formatter:function (value,row,index) {
                    return "<input class=\"easyui-textbox\" id='officialVersionNumber2' style=\"width:140px;height: auto\" value=\""+ value +"\">";
                }},
                {field: 'shedule_functionTestVersion_submit',title:'计划功能测试版本提交日期',width:180},
                {field: 'shedule_functionTestVersion_finish',title:'计划功能测试完成日期',width:180},
                {field: 'shedule_officialVersion_submit',title:'计划提交正式版日期',width:180},

                {field: 'date_of_production',title:'投产日期',width:100},
                {field: 'lastest_progress',title:'最新进展',width:200,formatter:function (value,row,index) {
                    return "<input class=\"easyui-textbox\" id='lastestProgress2' style=\"width:200px;height: auto\" value=\""+ value +"\">";
                }},
                {field: 'description',title:'说明',width:120,formatter:function (value,row,index) {
                    return "<input class=\"easyui-textbox\" id='description2' style=\"width:120px;height: auto\" value=\""+ value +"\">";
                }}
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

            $('#btn_keep').bind('click',function(){
//            todo: 数据修改
                $.messager.confirm('确定','是否确定<span style="color: red;font-size: 20px;">修改该条数据？',function (r) {
                    if(r){
                        var id = $("#textbox-id-value").val();
                        var rowIndex = $('#dataList').datagrid('getRowIndex', parseInt(id));//id是关键字值
                        var data = $('#dataList').datagrid('getData').rows[rowIndex];

                        console.log("the html str is : "+ $("#demandName2").val());
//                        console.log("the html str is : "+ $("#demandName2").value);
                        $.ajax({
                            url:'/requirementManage/updateThisRequirementRecord',
                            type:'post',
                            data:{
                                'demand_id':data.demand_id,
                                'demand_name':$("#demandName2").textbox('getValue'),
                                'demand_details':$("#demandDetails2").textbox('getValue'),
                                'demand_class':$("#demandClass2").textbox('getValue'),
                                'demand_content':$("#demandContent2").textbox('getValue'),
                                'priority':$("#priority1").val().toString(),
                                'priority_desc':data.priority_desc,
                                'business_value':$("#businessValue2").textbox('getValue'),
                                'demand_status':$("#demandStatus1").val().toString(),
                                'batch':$("#batch1").val().toString(),
                                'business_department':$("#businessDepartment2").textbox('getValue'),
                                'business_team':$("#businessTeam2").textbox('getValue'),
                                'leadOrCooperate':$("#leadOrCooperate1").val().toString(),
                                'product_name':$("#productName2").textbox('getValue'),
                                'version_status':$("#versionStatus1").val().toString(),
                                'workload':$("#workload2").textbox('getValue'),
                                'external_workload':$("#externalWorkload2").textbox('getValue'),
                                'vender_workload':$("#venderWorkload2").textbox('getValue'),
                                'development_model':$("#developmentModel1").val().toString(),
                                'main_product_situation':$("#mainProductSituation2").textbox('getValue'),
                                'demand_leader':$("#demandLeader2").textbox('getValue'),
                                'development_leader':$("#developmentLeader2").textbox('getValue'),
                                'task_code':$("#taskCode2").textbox('getValue'),
                                'project_code':$("#projectCode2").textbox('getValue'),
                                'is_newAddResources':$("#isNewAddResources1").val().toString(),
                                'is_dataTransfer':$("#isDataTransfer1").val().toString(),
                                'is_performanceTest':$("#isPerformanceTest1").val().toString(),
                                'update_date':data.update_date,
                                'technicalPlan_desc':$("#technicalPlanDesc2").textbox('getValue'),
                                'task_type':$(data.task_type).val().toString(),
                                'UAT_versionNumber':$("#UAT_versionNumber2").textbox('getValue'),
                                'official_versionNumber':$("#officialVersionNumber2").textbox('getValue'),
                                'shedule_functionTestVersion_submit':data.shedule_functionTestVersion_submit,
                                'shedule_functionTestVersion_finish':data.shedule_functionTestVersion_finish,
                                'shedule_officialVersion_submit':data.shedule_officialVersion_submit,
                                'data_of_production':data.data_of_production,
                                'lastest_progress':$("#lastestProgress2").textbox('getValue'),
                                'description':$("#description2").textbox('getValue')
                            },
                            dataType:'text',
                            success:function (data) {
                                if(data == 'ok'){
                                    var nextUrl = "/requirementManage/gotoRecordList";
                                    window.location.href = nextUrl;
                                }
                                else{
                                    $.messager.alert('提示','修改失败：<br />'+data," info");

                                }
                            }
                        })
//                    var checkValue=$("#priority1").val();  //获取Select选择的Value
                    }
                });
//            alert("保存修改");
            });

            $('#btn_cancel').bind('click',function(){
                var datagrid = $('#dataList');
                datagrid.datagrid("reload"); //刷新页面
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
