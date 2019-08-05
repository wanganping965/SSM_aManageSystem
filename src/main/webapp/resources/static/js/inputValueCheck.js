 function valueChecked(demand_id, demand_name, demand_details, demand_class, demand_content,
                         priority_desc, business_value, business_department, business_team, product_name,
                         workload, external_workload, vender_workload, main_product_situation, demand_leader,
                         development_leader, task_code, project_code, technicalPlan_desc, UAT_versionNumber,
                         official_versionNumber, lastest_progress, description) {
    if (demand_id.length != 15) {
        alert("需求编号 长度不符合提示的标准！");
        return false;
    }
    else {
        if (demand_id[2] != '-' && demand_id[9] != '-') {
            alert("需求编号不符合提示的标准格式！");
            return false;
        }
        for (var i = 0; i < demand_id.length; i++) {
            if (i == 0 || i == 1) {
                if ((demand_id.charCodeAt(i) >= 97 && demand_id.charCodeAt(i) <= 122) ||
                    (demand_id.charCodeAt(i) >= 65 && demand_id.charCodeAt(i) <= 90)) {
                    continue;
                } else {
                    alert("需求编号 前两位必须是英文字符！");
                    return false;
                }
            }
            else if (i == 2 || i == 9) {
                if (demand_id[i] != '-') {
                    alert("需求编号不符合提示的标准格式！");
                    return false;
                }
            }
            else {
                if (demand_id.charCodeAt(i) < 48 || demand_id.charCodeAt(i) > 57) {
                    console.log("current index is :" + i);
                    console.log(demand_id[i])
                    alert("需求编号不符合提示的标准格式！ (FR-XXXXXX-XXXXX)其中每个X代表一个数字");
                    return false;
                }
            }
        }
    }

    if (demand_name.length == 0) {
        alert("需求名称不能为空，请正确填写！");
        return false;
    }

    if (demand_details.length == 0) {
        alert("需求细项不为空，请根据实际情况填写！");
        return false;
    }

    if (demand_class.length == 0) {
        alert("需求分类不为空，请根据实际情况填写！");
        return false;
    }

    if (demand_content.length == 0) {
        alert("需求内容不为空，请根据实际情况填写！");
        return false;
    }

    if (priority_desc.length == 0) {
        alert("优先级说明不为空，请根据实际情况填写！");
        return false;
    }

    if (business_value.length == 0) {
        alert("业务价值不为空，请根据实际情况填写！");
        return false;
    }
    if (business_department.length == 0) {
        alert("所属业务部门不为空，请根据实际情况填写！");
        return false;
    }
    if (business_team.length == 0) {
        alert("所属业务团队不为空，请根据实际情况填写！");
        return false;
    }


    if (product_name.length == 0) {
        alert("产品英文简称不为空，请根据实际情况填写！");
        return false;
    }
    if (workload.length == 0) {
        alert("自主工作量不为空，请根据实际情况填写！");
        return false;
    }
    if (external_workload.length == 0) {
        alert("其中外员工作量不为空，请根据实际情况填写！");
        return false;
    }
    if (vender_workload.length == 0) {
        alert("厂商工作量不为空，请根据实际情况填写！");
        return false;
    }
    if (main_product_situation.length == 0) {
        alert("主要涉及产品情况不为空，请根据实际情况填写！");
        return false;
    }

    if (demand_leader.length == 0) {
        alert("需求牵头人不为空，请根据实际情况填写！");
        return false;
    }
    if (development_leader.length == 0) {
        alert("开发牵头人不为空，请根据实际情况填写！");
        return false;
    }
    if (task_code.length == 0) {
        alert("任务编号不为空，请根据实际情况填写！");
        return false;
    } else {
        if (task_code.split("-").length != 3) {
            alert("任务编号不符合提示的标准格式！(字符串-数字串-数字串，形如: F-987654-55555)");
            return false;
        }
    }
    if (project_code.length == 0) {
        alert("项目编码不为空，请根据实际情况填写！");
        return false;
    }
    if (technicalPlan_desc.length == 0) {
        alert("技术方案说明不为空，请根据实际情况填写！");
        return false;
    }

    if (UAT_versionNumber.length == 0) {
        alert("UTA版本号不为空，请根据实际情况填写！");
        return false;
    }
    if (official_versionNumber.length == 0) {
        alert("正式版本号不为空，请根据实际情况填写！");
        return false;
    }
    if (lastest_progress.length == 0) {
        alert("最新进展不为空，请根据实际情况填写！");
        return false;
    }
    if (description.length == 0) {
        alert("说明不为空，请根据实际情况填写！");
        return false;
    }

    console.log("到达判断的最下面，返回true--->>> 1");
    return true;
}

function isdate(intYear, intMonth, intDay) {

    if (isNaN(intYear) || isNaN(intMonth) || isNaN(intDay))
        return false;

    if (intMonth > 12 || intMonth < 1)
        return false;

    if (intDay < 1 || intDay > 31)
        return false;

    if ((intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11) && (intDay > 30))
        return false;

    if (intMonth == 2) {

        if (intDay > 29)
            return false;

        if ((((intYear % 100 == 0) && (intYear % 400 != 0)) || (intYear % 4 != 0)) && (intDay > 28))
            return false;

    }

    return true;
}

function checkDateValidation(date1) {
    var sp = date1.split(/[-,/]/);
    if (isdate(parseInt(sp[0]), parseInt(sp[1]), parseInt(sp[2]))) {
        return true;
    }
    return false;
}

function relateDateChecked(shedule_functionTestVersion_submit, shedule_functionTestVersion_finish,
                              shedule_officialVersion_submit, date_of_production, type) {
    // 1 年/月/日 or 年-月-日  2 月/日/年
    if (type == 2) {
        var sp = shedule_functionTestVersion_submit.split("/")
        shedule_functionTestVersion_submit = "";
        shedule_functionTestVersion_submit += sp[2] + "-" + sp[0] + "-" + sp[1];

        sp = shedule_functionTestVersion_finish.split("/")
        shedule_functionTestVersion_finish = "";
        shedule_functionTestVersion_finish += sp[2] + "-" + sp[0] + "-" + sp[1];

        sp = shedule_officialVersion_submit.split("/")
        shedule_officialVersion_submit = "";
        shedule_officialVersion_submit += sp[2] + "-" + sp[0] + "-" + sp[1];

        sp = date_of_production.split("/")
        date_of_production = "";
        date_of_production += sp[2] + "-" + sp[0] + "-" + sp[1];
    }
    if (!checkDateValidation(shedule_functionTestVersion_submit)) {
        alert("计划功能测试版本提交日期 日期格式错误 1，示例：1970-01-01 或者 1970/01/01！");
        return false;
    }
    if (!checkDateValidation(shedule_functionTestVersion_finish)) {
        alert("计划功能测试版本提交日期 日期格式错误 2，示例：1970-01-01 或者 1970/01/01！");
        return false;
    }
    if (!checkDateValidation(shedule_officialVersion_submit)) {
        alert("计划功能测试版本提交日期 日期格式错误 3，示例：1970-01-01 或者 1970/01/01！");
        return false;
    }
    if (!checkDateValidation(date_of_production)) {
        alert("计划功能测试版本提交日期 日期格式错误 4，示例：1970-01-01 或者 1970/01/01！");
        return false;
    }
    if (new Date(shedule_functionTestVersion_submit) > new Date(shedule_functionTestVersion_finish)) {
        alert("计划功能测试版本提交日期 和 计划功能测试完成日期 时间先后有冲突，请根据实际情况填写！");
        return false;
    }
    if (new Date(shedule_functionTestVersion_finish) > new Date(shedule_officialVersion_submit)) {
        alert("计划功能测试完成日期 和 计划提交正式版日期 时间先后有冲突，请根据实际情况填写！");
        return false;
    }
    if (new Date(shedule_officialVersion_submit) > new Date(date_of_production)) {
        alert("计划提交正式版日期时间 和 投产日期 先后有冲突，请根据实际情况填写！");
        return false;
    }

    console.log("到达判断的最下面，返回true--->>> 2");
    return true;
};