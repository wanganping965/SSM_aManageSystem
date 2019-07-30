<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/commonpage/easyuisupport.jsp" %>

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
<%--<h2>Hello World!</h2>--%>
<div id="selectByConditions">
    <p>多级查询：</p>

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
        <li>
            <a id="btn_search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">按条件查询</a>
        </li>
    </ul>

</div>
</body>

<%--<a href="${pageContext.request.contextPath}/user/findAll">查询所有用户</a>--%>

<%--<div class="easyui-dialog" style="width:400px;height:200px"--%>
     <%--data-options="title:'Hello EasyUI',collapsible:true,iconCls:'icon-ok',onOpen:function(){}">--%>
    <%--this is content!!!--%>
<%--</div>--%>
<div id=test>
    <%--<input id="priority_desc" style="width:150px;height: 60px" name="priority_desc" value="">--%>
    <%--<a id="btn_keep" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">保存修改</a>--%>
    <%--<input type="hidden" id="keep_value" value="1">--%>

        <%--<input id="ss" class="easyui-searchbox" style="width:300px"--%>
                     <%--data-options="prompt:'Please Input Value',menu:'#mm'">--%>
        <%--<div id="mm" style="width:120px">--%>
            <%--<div data-options="name:'all',iconCls:'icon-ok'">All Records</div>--%>
            <%--<div data-options="name:'sports'">Sports News</div>--%>
        <%--</div>--%>

        <%--<input id="ss1" class="easyui-searchbox" style="width:300px"--%>
               <%--data-options="searcher:qq,prompt:'Please Input Value',menu:'#mm1'">--%>
        <%--<div id="mm1" style="width:120px">--%>
            <%--<div data-options="name:'all',iconCls:'icon-ok'">All Records</div>--%>
            <%--<div data-options="name:'sports'">Sports News</div>--%>
        <%--</div>--%>
</div>

<%--<table id="dg"></table>--%>

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
//
//        console.log("获取单个值");
//        console.log($("#priority_desc").combobox('getValue'));
//        console.log("获取多个值");
//        console.log($("#priority_desc").combobox('getValues'))
//        var tmp = $("#priority_desc").combobox('getValues');
//        for(var i = 0;i<tmp._size(); i ++)
//        {
//            console.log
//        }
//
//    })
</script>
<script type="text/javascript">
//    function qq(value,name){
//        alert(value+":"+name);
//        var ss =$('#ss');
//        alert(ss.value+":::" +ss.name);
//    }
    $("#btn_search").bind("click",function () {
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

        console.log(""+demand_id5);
        console.log("demand_id's type is :"+Object.prototype.toString.call(demand_id5));
        console.log(""+demand_name5);
        console.log("demand_id's type is :"+Object.prototype.toString.call(demand_name5));

        console.log(""+priority5);
        console.log("priority's type is :"+Object.prototype.toString.call(priority5));

        console.log(""+priority_desc5);
        console.log("demand_id's type is :"+Object.prototype.toString.call(priority_desc5));

        console.log(""+demand_status5);
        console.log("demand_id's type is :"+Object.prototype.toString.call(demand_status5));


        console.log(""+batch5);
        console.log("demand_id's type is :"+Object.prototype.toString.call(batch5));

        console.log(""+business_department5);
        console.log("demand_id's type is :"+Object.prototype.toString.call(business_department5));

        console.log(""+business_team5);
        console.log("demand_id's type is :"+Object.prototype.toString.call(business_team5));

        console.log(""+leadOrCooperate5);
        console.log("demand_id's type is :"+Object.prototype.toString.call(leadOrCooperate5));

        console.log(""+version_status5);
        console.log("demand_id's type is :"+Object.prototype.toString.call(version_status5));


        console.log(""+development_model5);
        console.log("demand_id's type is :"+Object.prototype.toString.call(development_model5));

        console.log(""+product_name5);
        console.log("demand_id's type is :"+Object.prototype.toString.call(product_name5));

        console.log(""+task_code5);
        console.log("demand_id's type is :"+Object.prototype.toString.call(task_code5));

        console.log(""+project_code5);
        console.log("demand_id's type is :"+Object.prototype.toString.call(project_code5));

        console.log(""+is_newAddResources5);
        console.log("demand_id's type is :"+Object.prototype.toString.call(is_newAddResources5));


        console.log(""+is_dataTransfer5);
        console.log("demand_id's type is :"+Object.prototype.toString.call(is_dataTransfer5));

        console.log(""+is_performanceTest5);
        console.log("demand_id's type is :"+Object.prototype.toString.call(is_performanceTest5));

        console.log(""+task_type5);
        console.log("demand_id's type is :"+Object.prototype.toString.call(task_type5));


        $.ajax({
            url:'',
            type:'POST',
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
            dataType:'json',
            success:function (data) {
                $('#dataList').datagrid('loadData',data);
            }
        })
    })
</script>
</html>
