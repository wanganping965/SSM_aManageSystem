package com.sixmai.controller;

import com.alibaba.fastjson.JSON;

import com.sixmai.domain.RequirementChangeRecord;
import com.sixmai.service.RequirementChangeRecordService;
import com.sun.xml.internal.bind.v2.TODO;
import org.apache.commons.collections.map.HashedMap;
import org.aspectj.apache.bcel.util.ClassLoaderRepository;
import org.omg.PortableServer.POAPackage.ObjectAlreadyActive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 未来人来xw on 2019/7/18.
 */
@Controller
@RequestMapping("/requirementManage")
@SessionAttributes({"username","role"})
public class RequirementChangeRecordController {
    private RequirementChangeRecordService requirementChangeRecordService;
    @Autowired
    public void setRequirementChangeRecordService(@Qualifier("requirementChangeRecordServiceImpl") RequirementChangeRecordService requirementChangeRecordService) {
        this.requirementChangeRecordService = requirementChangeRecordService;
    }

    /***  *********************************************************************
     *
     *                   返回从数据库获取的数据，以json字符串的形式返回
     *     *********************************************************************
     */
    @RequestMapping(value = "/getAllRecordList")
    @ResponseBody
    public Map<String,Object> getAllRequirementRecordList(HttpServletRequest request)
    {
        // 页码和每页记录的条数
        String start = request.getParameter("page");
        String rows = request.getParameter("rows");

        Map<String,Object> json_RowsDataWithTotalNumbers = requirementChangeRecordService.findAllRequirementsRecord(start,rows);
//        System.out.println("total is: "+json_RowsDataWithTotalNumbers.get("total"));

        return json_RowsDataWithTotalNumbers;
    }

//    TODO 多级模糊查询
    @RequestMapping(value = "/getAllRecordListByFilters")
    @ResponseBody
//    public Map<String,Object> getAllRecordListByFilters(@RequestParam("demand_id")String demand_id, @RequestParam("demand_name")String demand_name,
//                                                        @RequestParam("priority")String priority, @RequestParam("priority_desc")String priority_desc,
//                                                        @RequestParam("demand_status")String demand_status, @RequestParam("batch")String batch,
//                                                        @RequestParam("business_department")String business_department, @RequestParam("business_team")String business_team,
//                                                        @RequestParam("leadOrCooperate")String leadOrCooperate, @RequestParam("version_status")String version_status,
//                                                        @RequestParam("development_model")String development_model, @RequestParam("product_name")String product_name,
//                                                        @RequestParam("task_code")String task_code, @RequestParam("project_code")String project_code,
//                                                        @RequestParam("is_newAddResources")String is_newAddResources, @RequestParam("is_dataTransfer")String is_dataTransfer,
//                                                        @RequestParam("is_performanceTest")String is_performanceTest,@RequestParam("task_type")String task_type,
//                                                        @ModelAttribute("role") String role, HttpServletRequest request)
    public Map<String,Object> getAllRecordListByFilters(String demand_id, String demand_name,
                                                        String priority, String priority_desc,
                                                        String demand_status, String batch,
                                                        String business_department,String business_team,
                                                        String leadOrCooperate, String version_status,
                                                        String development_model, String product_name,
                                                        String task_code, String project_code,
                                                        String is_newAddResources, String is_dataTransfer,
                                                        String is_performanceTest,String task_type,
                                                         String role, HttpServletRequest request)
       {
        // 页码和每页记录的条数
        String start = request.getParameter("page");
        String rows = request.getParameter("rows");

        Map<String,Object> json_RowsDataWithTotalNumbers = new HashMap<String,Object>();
        if(demand_id == null && demand_name==null && priority==null &&priority_desc ==null && demand_status==null &&
                batch==null && business_department==null && business_team==null && leadOrCooperate==null &&
                version_status==null && development_model==null && product_name==null && task_code==null &&
                 project_code==null &&  is_newAddResources==null && is_dataTransfer==null &&  is_performanceTest==null
                && task_type==null){
            json_RowsDataWithTotalNumbers = requirementChangeRecordService.findAllRequirementsRecord(start,rows);
        }else{
            json_RowsDataWithTotalNumbers = requirementChangeRecordService.findAllRequirementsRecordByFilters(start,rows,
                    demand_id, demand_name, priority,  priority_desc.trim(), demand_status,  batch, business_department,  business_team,
                    leadOrCooperate,  version_status, development_model,  product_name, task_code,  project_code, is_newAddResources,
                    is_dataTransfer, is_performanceTest, task_type, role);
        }
        System.out.println("demand_name 是："+demand_name);
        System.out.println("模糊查询获取的数据为: "+json_RowsDataWithTotalNumbers);

        return json_RowsDataWithTotalNumbers;
    }


    @RequestMapping(value = "/getThisHistoryRecordList")
    @ResponseBody
    public Map<String,Object> getThisHistoryRecordList(HttpServletRequest request)
    {
        // 页码和每页记录的条数
        String start = request.getParameter("page");
        String rows = request.getParameter("rows");
        String demand_id = request.getParameter("demand_id");
//        Map<String,Object> json_RowsDataWithTotalNumbers = requirementChangeRecordService.findAllRequirementsRecord(start,rows);

        Map<String,Object> json_RowsDataWithTotalNumbers = requirementChangeRecordService.findThisRequirementHistoryVersion(demand_id,start,rows);
        System.out.println("the rows of the demand history version is: "+json_RowsDataWithTotalNumbers.get("rows"));

        return json_RowsDataWithTotalNumbers;
    }

    @RequestMapping(value = "/getOurTeamRecordList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getOurTeamRecordList(@ModelAttribute("role") int role,HttpServletRequest request)
    {
        // 页码和每页记录的条数
        String start = request.getParameter("page");
        String rows = request.getParameter("rows");
        ArrayList<Map<String,Object>> rowsData = requirementChangeRecordService.findAllRequirementByOwnedTeam(""+role,start,rows);
        int total = requirementChangeRecordService.getTotalFoundRecordNumbers();
        Map<String,Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("total",total);
        jsonMap.put("rows",rowsData);

        return jsonMap;
    }
    @RequestMapping(value = "/getRecordListRelatedToThisUsername",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getRecordListRelatedToThisUsername(@ModelAttribute("username") String username,@ModelAttribute("role") int role,HttpServletRequest request)
    {
        // 页码和每页记录的条数
        String start = request.getParameter("page");
        String rows = request.getParameter("rows");
        ArrayList<Map<String,Object>> rowsData = requirementChangeRecordService.findAllRequirementByAndUsername(""+role,username,start,rows);
        int total = requirementChangeRecordService.getTotalFoundRecordNumbers();
        Map<String,Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("total",total);
        jsonMap.put("rows",rowsData);

        return jsonMap;
    }

    @RequestMapping(value = "/thisRecordDetails")
    @ResponseBody
    public Map<String, Object> postThisRecordDetails(HttpServletRequest request)
    {
        String id = request.getParameter("id");
        ArrayList<Map<String, Object>> rowsData = requirementChangeRecordService.getThisDetailRecordsById(id);

        Map<String,Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("total","1");
        jsonMap.put("rows",rowsData);
        return jsonMap;
    }

    @RequestMapping("/getNullRecordDetails")
    @ResponseBody
    public Map<String,Object> postNullRecordDetails()
    {
        ArrayList<Map<String,Object>> rowsData = requirementChangeRecordService.getNullDetailRecord();

        Map<String,Object> jsonMap = new HashMap<String,Object>();
        jsonMap.put("total","1");
        jsonMap.put("rows",rowsData);

        return jsonMap;
    }


    /***  *********************************************************************
     *
     *                   新增即删除记录的controller操作
     *     *********************************************************************
     */

    @RequestMapping(value = "/updateThisRequirementRecord",method = RequestMethod.POST)
    @ResponseBody
    public String updateThisRequirementRecord(@RequestParam("demand_id")String demand_id,
                                              @RequestParam("demand_name")String demand_name, @RequestParam("demand_details")String demand_details,
                                              @RequestParam("demand_class")String demand_class, @RequestParam("demand_content")String demand_content,
                                              @RequestParam("priority")String priority,
                                              @RequestParam("priority_desc")String priority_desc, @RequestParam("business_value")String business_value,
                                              @RequestParam("demand_status")String demand_status,
                                              @RequestParam("batch")String batch, @RequestParam("business_department")String business_department,
                                              @RequestParam("business_team")String business_team, @RequestParam("leadOrCooperate")String leadOrCooperate,
                                              @RequestParam("product_name")String product_name,
                                              @RequestParam("version_status")String version_status, @RequestParam("workload")String workload,
                                              @RequestParam("external_workload")String external_workload, @RequestParam("vender_workload")String vender_workload,
                                              @RequestParam("development_model")String development_model, @RequestParam("main_product_situation")String main_product_situation,
                                              @RequestParam("demand_leader")String demand_leader, @RequestParam("development_leader")String development_leader,
                                              @RequestParam("task_code")String task_code, @RequestParam("project_code")String project_code,
                                              @RequestParam("is_newAddResources")String is_newAddResources, @RequestParam("is_dataTransfer")String is_dataTransfer,
                                              @RequestParam("is_performanceTest")String is_performanceTest, @RequestParam("update_date")String update_date,
                                              @RequestParam("technicalPlan_desc")String technicalPlan_desc, @RequestParam("task_type")String task_type,
                                              @RequestParam("UAT_versionNumber")String UAT_versionNumber, @RequestParam("official_versionNumber")String official_versionNumber,
                                              @RequestParam("shedule_functionTestVersion_submit") String shedule_functionTestVersion_submit,
                                              @RequestParam("shedule_functionTestVersion_finish")String shedule_functionTestVersion_finish, @RequestParam("shedule_officialVersion_submit")String shedule_officialVersion_submit,
                                              @RequestParam("data_of_production")String data_of_production, @RequestParam("lastest_progress")String lastest_progress,
                                              @RequestParam("description")String description,
                                              @ModelAttribute("role")String team_responsible_for, @ModelAttribute("username")String user_last_changed)
    {
        //获取当前的时间 年月日时分秒
        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String record_update_time = sdFormatter.format(nowTime);

        if(requirementChangeRecordService.setRequirementRecord(demand_id,  demand_name,  demand_details,
                demand_class,  demand_content,  priority,
                priority_desc,  business_value,  demand_status,
                batch,  business_department,  business_team,
                leadOrCooperate,  product_name,  version_status,
                workload,  external_workload,  vender_workload,
                development_model,  main_product_situation,
                demand_leader,  development_leader,  task_code,
                project_code,  is_newAddResources,  is_dataTransfer,
                is_performanceTest,  update_date,  technicalPlan_desc,
                task_type,  UAT_versionNumber,
                official_versionNumber,shedule_functionTestVersion_submit,
                shedule_functionTestVersion_finish,  shedule_officialVersion_submit,
                data_of_production,  lastest_progress,  description,
                team_responsible_for,  user_last_changed, record_update_time)){
            return "ok";
        }
        else{
            return "noValid";
        }
    }


    // 只删除这个需求所有的在数据库中的最近一条记录，即当前查看的记录
    @RequestMapping(value = "/deleteThisRequirementRecord",method = RequestMethod.POST)
    @ResponseBody
    public String deleteThisRequirementRecord(HttpServletRequest request)
    {
        String id = request.getParameter("id");
        if(requirementChangeRecordService.deleteRequirementRecordById(id)){
            return "ok";
        }
        else{
            return "noValid";
        }
    }

    // 删除这个需求所有的在数据库中的记录
    @RequestMapping(value = "/deleteAllHistoryRecord")
    @ResponseBody
    public String deleteAllHistoryRecord(HttpServletRequest request)
    {
        String demand_id = request.getParameter("demand_id");
        if(requirementChangeRecordService.deleteRequirementRecordByDemand_id(demand_id)){
            return "ok";
        }
        else{
            return "noValid";
        }
    }



    /***  *********************************************************************
     *
     *                   COntroller层 页面跳转
     *     *********************************************************************
     */

    @RequestMapping("/gotoRecordList")
    public String gotoRecordList()
    {
        return "recordlist_show_page";
    }

    //需要知道被访问或者被修改的记录的唯一表示，通过request获取，然后传递给具体的页面
    // 从列表页的消息传过来 需要进入具体页的 需求唯一标识即id
    @RequestMapping("/gotoRecordDetails")
    public String gotoRecordDetails(HttpServletRequest request, ModelMap modelMap)
    {
        String id = request.getParameter("id");
        modelMap.addAttribute("id",id);
        return "detail_present_page";
    }
    @RequestMapping("/gotoRecordChange")
    public String gotoRecordChange(HttpServletRequest request, ModelMap modelMap)
    {
        String id = request.getParameter("id");
        modelMap.addAttribute("id",id);
        return "record_change_page";
    }
    @RequestMapping("/gotoRecordHistoryList")
    public String gotoRecordHistoryList(HttpServletRequest request,ModelMap modelMap)
    {
        String demand_id = request.getParameter("demand_id");
        modelMap.addAttribute("demand_id",demand_id);
        return "record_historyList_page";
    }

    @RequestMapping("/gotoRecordAdd")
    public String gotoRecordAdd()
    {
        return "record_newAdd_page";
    }
}
