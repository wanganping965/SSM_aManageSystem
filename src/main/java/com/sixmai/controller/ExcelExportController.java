package com.sixmai.controller;

import com.sixmai.service.ExcelExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by 未来人来xw on 2019/7/31.
 */
@Controller
@RequestMapping("/exportExcel")
@SessionAttributes({"username","role"})
public class ExcelExportController {

    private ExcelExportService excelExportService;

    @Autowired
    public void setExcelExportService(@Qualifier("excelExportServiceImpl") ExcelExportService excelExportService) {
        this.excelExportService = excelExportService;
    }

    @RequestMapping("/getFilterRecordExpertToExcel")
    @ResponseBody
    public String getFilterRecordExpertToExcel(@RequestParam("demand_id")String demand_id, @RequestParam("demand_name")String demand_name,
                                               @RequestParam("priority")String priority, @RequestParam("priority_desc")String priority_desc,
                                               @RequestParam("demand_status")String demand_status, @RequestParam("batch")String batch,
                                               @RequestParam("business_department")String business_department, @RequestParam("business_team")String business_team,
                                               @RequestParam("leadOrCooperate")String leadOrCooperate, @RequestParam("version_status")String version_status,
                                               @RequestParam("development_model")String development_model, @RequestParam("product_name")String product_name,
                                               @RequestParam("task_code")String task_code, @RequestParam("project_code")String project_code,
                                               @RequestParam("is_newAddResources")String is_newAddResources, @RequestParam("is_dataTransfer")String is_dataTransfer,
                                               @RequestParam("is_performanceTest")String is_performanceTest, @RequestParam("task_type")String task_type,
                                               @ModelAttribute("role") String role, HttpServletResponse response)
    {
        response.setContentType("application/msexcel;charset=UTF-8");

        try{
            ServletOutputStream out = response.getOutputStream();
//            response.reset();// 重置输出流
//            try {
                //设置文件头：最后一个参数是设置下载文件名(这里我们叫：Records.xls)
//                response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(name+".xls", "UTF-8"));
            response.setHeader("Content-Disposition", "attachment;fileName=Records.xlsx");
//            } catch (UnsupportedEncodingException e1) {
//                e1.printStackTrace();
//            }
            // 定义输出类型
//            response.setContentType("application/msexcel;charset=UTF-8");

            String[] titles = { "序号", "需求编号", "需求名称", "需求细项" ,"需求分类", "需求内容","优先级", "优先级说明","业务价值",
                    "需求状态", "批次", "所属业务部门", "所属业务团队",
            "牵头/配合", "产品", "版本情况", "自主工作量" ,"其中外员工作量", "厂商工作量", "开发模式", "主要涉及产品情况",
                    "需求牵头人", "开发牵头人", "任务编号", "项目编码" ,"是否新增资源", "是否数据迁移", "是否性能测试", "更新日期",
                    "技术方案说明", "任务类型", "UTA版本号", "正式版本号" ,"计划功能测试版本提交日期", "计划功能测试完成日期",
                    "计划提交正式版日期", "投产日期","最新进展","说明"};
            excelExportService.export(titles, out,demand_id, demand_name, priority,  priority_desc.trim(), demand_status,  batch, business_department,
                    business_team, leadOrCooperate,  version_status, development_model,  product_name, task_code,  project_code, is_newAddResources,
                    is_dataTransfer, is_performanceTest, task_type, role);
            return "success";
        } catch(Exception e){
            e.printStackTrace();
            return "导出信息失败";
        }
    }

}
