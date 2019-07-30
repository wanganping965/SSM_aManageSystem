package com.sixmai.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sixmai.domain.RequirementChangeRecord;
import com.sixmai.mapper.RequirementChangeRecordMapper;
import com.sixmai.service.RequirementChangeRecordService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

/**
 * Created by 未来人来xw on 2019/7/18.
 */
@Service("requirementChangeRecordServiceImpl")
public class RequirementChangeRecordServiceImpl implements RequirementChangeRecordService {

    RequirementChangeRecordMapper requirementChangeRecordMapper;

    @Autowired
    public void setRequirementChangeRecordMapper(RequirementChangeRecordMapper requirementChangeRecordMapper) {
        this.requirementChangeRecordMapper = requirementChangeRecordMapper;
    }

    private String dateFormatterTransfer(String before) {
        String[] m_d_y = before.split("/");
        return m_d_y[2] + "-" + m_d_y[0] + "-" + m_d_y[1];
    }

    /**
     * 读取json文件，返回json串
     * @param fileName
     * @return
     */
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean setRequirementRecord(String demand_id, String demand_name, String demand_details,
                                        String demand_class, String demand_content, String priority,
                                        String priority_desc, String business_value, String demand_status,
                                        String batch, String business_department, String business_team,
                                        String leadOrCooperate, String product_name, String version_status,
                                        String workload, String external_workload, String vender_workload,
                                        String development_model, String main_product_situation,
                                        String demand_leader, String development_leader, String task_code,
                                        String project_code, String is_newAddResources, String is_dataTransfer,
                                        String is_performanceTest, String update_date, String technicalPlan_desc,
                                        String task_type, String UAT_versionNumber, String official_versionNumber,
                                        String shedule_functionTestVersion_submit,
                                        String shedule_functionTestVersion_finish, String shedule_officialVersion_submit,
                                        String date_of_production, String lastest_progress, String description,
                                        String team_responsible_for, String user_last_changed, String record_update_time) {
        RequirementChangeRecord record = new RequirementChangeRecord();
//        List<RequirementChangeRecord> recordList = requirementChangeRecordMapper.findRequirementRecordsByDemand_id(demand_id);
//        if(recordList.size() == 0){
        String classpath = this.getClass().getResource("/").getPath().replaceFirst("/", "");
        System.out.println("classpath is: " + classpath);
        String webappRoot = classpath.replaceAll("WEB-INF/classes/", "");
        System.out.println("webappRoot is: " + webappRoot);
        String json_path = webappRoot + "combobox_data.json";

        //TODO 读取文件中的json内容
        String json_str = readJsonFile(json_path);
        System.out.println(">>>>>>>>The json string's content is: "+json_str);
//TODO
//        TODO
//        List<Map<String,Object>> listMap = JSON.parseObject(json_str,new TypeReference<List<Map<String,Object>>(){});
        List<Map<String,Object>> json_format = (List<Map<String,Object>>) JSONArray.parse(json_str);

        System.out.println("the json file read content is: "+json_format);
        String[] priority_desc_list = priority_desc.split(",|，");
        String res_desc = "";

        for(int i=0;i<priority_desc_list.length; i ++){
            if(priority_desc_list[i].length() == 1){
                if(Integer.parseInt(priority_desc_list[i]) <= 5){
                    res_desc +=","+ priority_desc_list[i];
                }else{
                    res_desc += ","+json_format.get(Integer.parseInt(priority_desc_list[i])-1).get("text");
                }
            }else{
                res_desc +=","+ priority_desc_list[i];
            }
        }

        record.setAll(0, demand_id, demand_name, demand_details,
                demand_class, demand_content, Integer.parseInt(priority),
                res_desc, business_value, Integer.parseInt(demand_status),
                Integer.parseInt(batch), business_department, business_team,
                Integer.parseInt(leadOrCooperate), product_name, Integer.parseInt(version_status),
                workload, external_workload, vender_workload,
                Integer.parseInt(development_model), main_product_situation,
                demand_leader, development_leader, task_code,
                project_code, Integer.parseInt(is_newAddResources), Integer.parseInt(is_dataTransfer),
                Integer.parseInt(is_performanceTest),
                java.sql.Date.valueOf(dateFormatterTransfer(update_date)),
                technicalPlan_desc,
                Integer.parseInt(task_type), UAT_versionNumber,
                official_versionNumber,
                java.sql.Date.valueOf(dateFormatterTransfer(shedule_functionTestVersion_submit)),
                java.sql.Date.valueOf(dateFormatterTransfer(shedule_functionTestVersion_finish)),
                java.sql.Date.valueOf(dateFormatterTransfer(shedule_officialVersion_submit)),
                java.sql.Date.valueOf(dateFormatterTransfer(date_of_production)), lastest_progress, description,
                Integer.parseInt(team_responsible_for), user_last_changed,
                java.sql.Timestamp.valueOf(record_update_time));
//        }

        // 不管是修改还是新增，实际都是在表中新增了一条记录
        //每次展示的都是上一次最新修改的记录
        requirementChangeRecordMapper.addRequirementRecord(record);
        return true;
    }

    @Override
    public Map<String, Object> findAllRequirementsRecord(String page, String rows) {
        int nums = Integer.parseInt(rows);
        int start = (Integer.parseInt(page) - 1) * nums;
        ArrayList<Map<String, Object>> recordlist = new ArrayList<Map<String, Object>>();
        List<Object> recordss = requirementChangeRecordMapper.findAllRequirement(start, nums);
        Long total = new Long(0);
        if (CollectionUtils.isNotEmpty(recordss)) {
            List<RequirementChangeRecord> records = (List<RequirementChangeRecord>) recordss.get(0);
            total = ((List<Long>) recordss.get(1)).get(0);


            for (int i = 0; i < records.size(); i++) {
                Map<String, Object> tmp = new HashMap<String, Object>();
                RequirementChangeRecord record = records.get(i);
                tmp.put("id", "" + record.getId());
                tmp.put("demand_id", "" + record.getDemand_id());
                tmp.put("demand_name", "" + record.getDemand_name());
                tmp.put("priority", record.getPriority());
                
                String priority_desc = record.getPriority_desc();
                String[] priority_desc_list = priority_desc.split(",|，");//后台数据库里面该字段的设置标准，第一个,号之前没有可用值

                String classpath = this.getClass().getResource("/").getPath().replaceFirst("/", "");
                String webappRoot = classpath.replaceAll("WEB-INF/classes/", "");
                String json_path = webappRoot + "combobox_data.json";

                // 读取文件中的json内容
                String json_str = readJsonFile(json_path);
                List<Map<String,Object>> json_format = (List<Map<String,Object>>) JSONArray.parse(json_str);

                String res_desc = "";

                for(int j=1;j < priority_desc_list.length; j ++){
                    if(priority_desc_list[j].length() == 1){
                        if(j==1) {
                            res_desc += "1."+json_format.get(Integer.parseInt(priority_desc_list[j]) - 1).get("text");
                        }else {
                            res_desc += "," +j+"."+ json_format.get(Integer.parseInt(priority_desc_list[j]) - 1).get("text");
                        }
                    }else{
                        res_desc +=","+j+"."+ priority_desc_list[j];
                    }
                }
                
                tmp.put("priority_desc", res_desc);
                tmp.put("demand_status", record.getDemand_status());
                tmp.put("batch", record.getBatch());
                tmp.put("business_department", "" + record.getBusiness_department());
                tmp.put("business_team", "" + record.getBusiness_team());
                tmp.put("leadOrCooperate", record.getLeadOrCooperate());
                tmp.put("product_name", "" + record.getProduct_name());
                tmp.put("version_status", record.getVersion_status());
                tmp.put("development_model", record.getDevelopment_model());
                tmp.put("demand_leader", "" + record.getDemand_leader());
                tmp.put("development_leader", "" + record.getDevelopment_leader());
                tmp.put("task_code", "" + record.getTask_code());
                tmp.put("project_code", "" + record.getProject_code());
                tmp.put("is_newAddResources", record.getIs_newAddResources());
                tmp.put("is_dataTransfer", record.getIs_dataTransfer());
                tmp.put("is_performanceTest", record.getIs_performanceTest());
                tmp.put("update_date", "" + record.getUpdate_date());
                tmp.put("task_type", record.getTask_type());
                tmp.put("UAT_versionNumber", "" + record.getUAT_versionNumber());
                tmp.put("official_versionNumber", "" + record.getOfficial_versionNumber());
                tmp.put("shedule_functionTestVersion_submit", "" + record.getShedule_functionTestVersion_submit());
                tmp.put("shedule_functionTestVersion_finish", "" + record.getShedule_functionTestVersion_finish());
                tmp.put("shedule_officialVersion_submit", "" + record.getShedule_officialVersion_submit());
                tmp.put("date_of_production", "" + record.getDate_of_production());
                tmp.put("lastest_progress", "" + record.getLastest_progress());
                tmp.put("description", "" + record.getDescription());
                recordlist.add(tmp);
            }
        }

        Map<String, Object> res = new HashMap<String, Object>();
        res.put("rows", recordlist);
        res.put("total", total);
        return res;
    }

    @Override
    public Map<String, Object> findAllRequirementsRecordByFilters(String page, String rows, String demand_id, String demand_name, String priority, String priority_desc,
                                                                  String demand_status, String batch, String business_department, String business_team, String leadOrCooperate,
                                                                  String version_status, String development_model, String product_name, String task_code, String project_code,
                                                                  String is_newAddResources, String is_dataTransfer, String is_performanceTest, String task_type,String role) {
        int nums = Integer.parseInt(rows);
        int start = (Integer.parseInt(page) - 1) * nums;
        ArrayList<Map<String, Object>> recordlist = new ArrayList<Map<String, Object>>();

        System.out.println("demand_name in service layer is:"+ demand_name);
        List<Object> recordss = requirementChangeRecordMapper.findAllRequirementBYFilters(start, nums,demand_id,demand_name,Integer.parseInt(priority.trim()),priority_desc,
                Integer.parseInt(demand_status.trim()), Integer.parseInt(batch.trim()),business_department,business_team,Integer.parseInt(leadOrCooperate),
                Integer.parseInt(version_status.trim()),Integer.parseInt(development_model.trim()),product_name,task_code,project_code,
                Integer.parseInt(is_newAddResources.trim()),Integer.parseInt(is_dataTransfer.trim()),Integer.parseInt(is_performanceTest.trim()),
                Integer.parseInt(task_type.trim()),role);
        Long total = new Long(0);
        if (CollectionUtils.isNotEmpty(recordss)) {
            List<RequirementChangeRecord> records = (List<RequirementChangeRecord>) recordss.get(0);
            total = ((List<Long>) recordss.get(1)).get(0);


            for (int i = 0; i < records.size(); i++) {
                Map<String, Object> tmp = new HashMap<String, Object>();
                RequirementChangeRecord record = records.get(i);
                tmp.put("id", "" + record.getId());
                tmp.put("demand_id", "" + record.getDemand_id());
                tmp.put("demand_name", "" + record.getDemand_name());
                tmp.put("priority", record.getPriority());

                String priority_desc1 = record.getPriority_desc();
                String[] priority_desc_list = priority_desc1.split(",|，");//后台数据库里面该字段的设置标准，第一个,号之前没有可用值

                String classpath = this.getClass().getResource("/").getPath().replaceFirst("/", "");
                String webappRoot = classpath.replaceAll("WEB-INF/classes/", "");
                String json_path = webappRoot + "combobox_data.json";

                // 读取文件中的json内容
                String json_str = readJsonFile(json_path);
                List<Map<String,Object>> json_format = (List<Map<String,Object>>) JSONArray.parse(json_str);

                String res_desc = "";

                for(int j=1;j < priority_desc_list.length; j ++){
                    if(priority_desc_list[j].length() == 1){
                        if(j==1) {
                            res_desc += "1."+json_format.get(Integer.parseInt(priority_desc_list[j]) - 1).get("text");
                        }else {
                            res_desc += "," +j+"."+ json_format.get(Integer.parseInt(priority_desc_list[j]) - 1).get("text");
                        }
                    }else{
                        res_desc +=","+j+"."+ priority_desc_list[j];
                    }
                }

                tmp.put("priority_desc", res_desc);
                tmp.put("demand_status", record.getDemand_status());
                tmp.put("batch", record.getBatch());
                tmp.put("business_department", "" + record.getBusiness_department());
                tmp.put("business_team", "" + record.getBusiness_team());
                tmp.put("leadOrCooperate", record.getLeadOrCooperate());
                tmp.put("product_name", "" + record.getProduct_name());
                tmp.put("version_status", record.getVersion_status());
                tmp.put("development_model", record.getDevelopment_model());
                tmp.put("demand_leader", "" + record.getDemand_leader());
                tmp.put("development_leader", "" + record.getDevelopment_leader());
                tmp.put("task_code", "" + record.getTask_code());
                tmp.put("project_code", "" + record.getProject_code());
                tmp.put("is_newAddResources", record.getIs_newAddResources());
                tmp.put("is_dataTransfer", record.getIs_dataTransfer());
                tmp.put("is_performanceTest", record.getIs_performanceTest());
                tmp.put("update_date", "" + record.getUpdate_date());
                tmp.put("task_type", record.getTask_type());
                tmp.put("UAT_versionNumber", "" + record.getUAT_versionNumber());
                tmp.put("official_versionNumber", "" + record.getOfficial_versionNumber());
                tmp.put("shedule_functionTestVersion_submit", "" + record.getShedule_functionTestVersion_submit());
                tmp.put("shedule_functionTestVersion_finish", "" + record.getShedule_functionTestVersion_finish());
                tmp.put("shedule_officialVersion_submit", "" + record.getShedule_officialVersion_submit());
                tmp.put("date_of_production", "" + record.getDate_of_production());
                tmp.put("lastest_progress", "" + record.getLastest_progress());
                tmp.put("description", "" + record.getDescription());
                recordlist.add(tmp);
            }
        }

        Map<String, Object> res = new HashMap<String, Object>();
        res.put("rows", recordlist);
        res.put("total", total);
        return res;
    }

    @Override
    public Map<String, Object> findThisRequirementHistoryVersion(String demand_id, String page, String rows) {
        int nums = Integer.parseInt(rows);
        int start = (Integer.parseInt(page) - 1) * nums;
        ArrayList<Map<String, Object>> recordlist = new ArrayList<Map<String, Object>>();
        List<Object> recordss = requirementChangeRecordMapper.findRequirementRecordsByDemand_id(demand_id, nums, start);
        Long total = new Long(0);
        if (CollectionUtils.isNotEmpty(recordss)) {
            List<RequirementChangeRecord> records = (List<RequirementChangeRecord>) recordss.get(0);
            total = ((List<Long>) recordss.get(1)).get(0);

            for (int i = 0; i < records.size(); i++) {
                Map<String, Object> tmp = new HashMap<String, Object>();
                RequirementChangeRecord record = records.get(i);

                tmp.put("id", "" + record.getId());
                tmp.put("demand_id", "" + record.getDemand_id());
                tmp.put("demand_name", "" + record.getDemand_name());
                tmp.put("demand_details", "" + record.getDemand_details());
                tmp.put("demand_class", "" + record.getDemand_class());

                tmp.put("demand_content", "" + record.getDemand_content());
                tmp.put("priority", record.getPriority());
                
                
                String priority_desc = record.getPriority_desc();
                String[] priority_desc_list = priority_desc.split(",|，");//后台数据库里面该字段的设置标准，第一个,号之前没有可用值

                String classpath = this.getClass().getResource("/").getPath().replaceFirst("/", "");
                String webappRoot = classpath.replaceAll("WEB-INF/classes/", "");
                String json_path = webappRoot + "combobox_data.json";

                // 读取文件中的json内容
                String json_str = readJsonFile(json_path);
                List<Map<String,Object>> json_format = (List<Map<String,Object>>) JSONArray.parse(json_str);

                String res_desc = "";

                for(int j=1;j < priority_desc_list.length; j ++){
                    if(priority_desc_list[j].length() == 1){
                        if(j==1) {
                            res_desc +="1." + json_format.get(Integer.parseInt(priority_desc_list[j]) - 1).get("text");
                        }else {
                            res_desc += ","+j+"." + json_format.get(Integer.parseInt(priority_desc_list[j]) - 1).get("text");
                        }
                    }else{
                        res_desc +=","+j+"."+ priority_desc_list[j];
                    }
                }

                tmp.put("priority_desc", res_desc);
                tmp.put("business_value", "" + record.getBusiness_value());
                tmp.put("demand_status", record.getDemand_status());

                tmp.put("batch", record.getBatch());
                tmp.put("business_department", "" + record.getBusiness_department());
                tmp.put("business_team", "" + record.getBusiness_team());
                tmp.put("leadOrCooperate", record.getLeadOrCooperate());
                tmp.put("product_name", "" + record.getProduct_name());

                tmp.put("version_status", record.getVersion_status());
                tmp.put("workload", "" + record.getWorkload());
                tmp.put("external_workload", "" + record.getExternal_workload());
                tmp.put("vender_workload", "" + record.getVender_workload());
                tmp.put("development_model", record.getDevelopment_model());

                tmp.put("main_product_situation", "" + record.getMain_product_situation());
                tmp.put("demand_leader", "" + record.getDemand_leader());
                tmp.put("development_leader", "" + record.getDevelopment_leader());
                tmp.put("task_code", "" + record.getTask_code());
                tmp.put("project_code", "" + record.getProject_code());

                tmp.put("is_newAddResources", record.getIs_newAddResources());
                tmp.put("is_dataTransfer", record.getIs_dataTransfer());
                tmp.put("is_performanceTest", record.getIs_performanceTest());
                tmp.put("update_date", "" + record.getUpdate_date());
                tmp.put("technicalPlan_desc", "" + record.getTechnicalPlan_desc());

                tmp.put("task_type", record.getTask_type());
                tmp.put("UAT_versionNumber", "" + record.getUAT_versionNumber());
                tmp.put("official_versionNumber", "" + record.getOfficial_versionNumber());
                tmp.put("shedule_functionTestVersion_submit", "" + record.getShedule_functionTestVersion_submit());
                tmp.put("shedule_functionTestVersion_finish", "" + record.getShedule_functionTestVersion_finish());

                tmp.put("shedule_officialVersion_submit", "" + record.getShedule_officialVersion_submit());
                tmp.put("date_of_production", "" + record.getDate_of_production());
                tmp.put("lastest_progress", "" + record.getLastest_progress());
                tmp.put("description", "" + record.getDescription());

                recordlist.add(tmp);
            }
        }

        Map<String, Object> res = new HashMap<String, Object>();
        res.put("rows", recordlist);
        res.put("total", total);
        return res;
    }


    @Override
    public ArrayList<Map<String, Object>> findAllRequirementByOwnedTeam(String role, String page, String rows) {
        ArrayList<Map<String, Object>> recordlist = new ArrayList<Map<String, Object>>();
        int nums = Integer.parseInt(rows);
        int start = (Integer.parseInt(page) - 1) * nums;
        List<RequirementChangeRecord> records = requirementChangeRecordMapper.findAllRequirementByOwnedTeam(Integer.parseInt(role), start, nums);
        for (int i = 0; i < records.size(); i++) {
            Map<String, Object> tmp = new HashMap<String, Object>();
            RequirementChangeRecord record = records.get(i);
            tmp.put("id", "" + record.getId());
            tmp.put("demand_id", "" + record.getDemand_id());
            tmp.put("demand_name", "" + record.getDemand_name());
//            tmp.put("", "" + record.getDemand_details());
//            tmp.put("", "" + record.getDemand_class());
//            tmp.put("", "" + record.getDemand_content());
            tmp.put("priority", record.getPriority());
            tmp.put("priority_desc", "" + record.getPriority_desc());
//            tmp.put("", "" + record.getBusiness_value());
            tmp.put("demand_status", record.getDemand_status());
            tmp.put("batch", record.getBatch());
            tmp.put("business_department", "" + record.getBusiness_department());
            tmp.put("business_team", "" + record.getBusiness_team());
            tmp.put("leadOrCooperate", record.getLeadOrCooperate());
            tmp.put("product_name", "" + record.getProduct_name());
            tmp.put("version_status", record.getVersion_status());
//            tmp.put("", "" + record.getWorkload());
//            tmp.put("", "" + record.getExternal_workload());
//            tmp.put("", "" + record.getVender_workload());
            tmp.put("development_model", record.getDevelopment_model());
//            tmp.put("", "" + record.getMain_product_situation());
            tmp.put("demand_leader", "" + record.getDemand_leader());
            tmp.put("development_leader", "" + record.getDevelopment_leader());
            tmp.put("task_code", "" + record.getTask_code());
            tmp.put("project_code", "" + record.getProject_code());
            tmp.put("is_newAddResources", record.getIs_newAddResources());
            tmp.put("is_dataTransfer", record.getIs_dataTransfer());
            tmp.put("is_performanceTest", record.getIs_performanceTest());
            tmp.put("update_date", "" + record.getUpdate_date());
//            tmp.put("", "" + record.getTechnicalPlan_desc());
            tmp.put("task_type", record.getTask_type());
            tmp.put("UAT_versionNumber", "" + record.getUAT_versionNumber());
            tmp.put("official_versionNumber", "" + record.getOfficial_versionNumber());
            tmp.put("shedule_functionTestVersion_submit", "" + record.getShedule_functionTestVersion_submit());
            tmp.put("shedule_functionTestVersion_finish", "" + record.getShedule_functionTestVersion_finish());
            tmp.put("shedule_officialVersion_submit", "" + record.getShedule_officialVersion_submit());
            tmp.put("date_of_production", "" + record.getDate_of_production());
            tmp.put("lastest_progress", "" + record.getLastest_progress());
            tmp.put("description", "" + record.getDescription());
//            tmp.put("", "" + record.getTeam_responsible_for());
//            tmp.put("", "" + record.getUser_last_changed());
//            tmp.put("", "" + record.getRecord_update_time());

            recordlist.add(tmp);
        }
        return recordlist;
    }

    @Override
    public ArrayList<Map<String, Object>> findAllRequirementByAndUsername(String role, String username, String page, String rows) {
        ArrayList<Map<String, Object>> recordlist = new ArrayList<Map<String, Object>>();
        int nums = Integer.parseInt(rows);
        int start = (Integer.parseInt(page) - 1) * nums;
        List<RequirementChangeRecord> records = requirementChangeRecordMapper.findAllRequirementByAndUsername(Integer.parseInt(role), username, start, nums);
        for (int i = 0; i < records.size(); i++) {
            Map<String, Object> tmp = new HashMap<String, Object>();
            RequirementChangeRecord record = records.get(i);
            tmp.put("id", "" + record.getId());
            tmp.put("demand_id", "" + record.getDemand_id());
            tmp.put("demand_name", "" + record.getDemand_name());
//            tmp.put("", "" + record.getDemand_details());
//            tmp.put("", "" + record.getDemand_class());
//            tmp.put("", "" + record.getDemand_content());
            tmp.put("priority", record.getPriority());
            tmp.put("priority_desc", "" + record.getPriority_desc());
//            tmp.put("", "" + record.getBusiness_value());
            tmp.put("demand_status", record.getDemand_status());
            tmp.put("batch", record.getBatch());
            tmp.put("business_department", "" + record.getBusiness_department());
            tmp.put("business_team", "" + record.getBusiness_team());
            tmp.put("leadOrCooperate", record.getLeadOrCooperate());
            tmp.put("product_name", "" + record.getProduct_name());
            tmp.put("version_status", record.getVersion_status());
//            tmp.put("", "" + record.getWorkload());
//            tmp.put("", "" + record.getExternal_workload());
//            tmp.put("", "" + record.getVender_workload());
            tmp.put("development_model", record.getDevelopment_model());
//            tmp.put("", "" + record.getMain_product_situation());
            tmp.put("demand_leader", "" + record.getDemand_leader());
            tmp.put("development_leader", "" + record.getDevelopment_leader());
            tmp.put("task_code", "" + record.getTask_code());
            tmp.put("project_code", "" + record.getProject_code());
            tmp.put("is_newAddResources", record.getIs_newAddResources());
            tmp.put("is_dataTransfer", record.getIs_dataTransfer());
            tmp.put("is_performanceTest", record.getIs_performanceTest());
            tmp.put("update_date", "" + record.getUpdate_date());
//            tmp.put("", "" + record.getTechnicalPlan_desc());
            tmp.put("task_type", record.getTask_type());
            tmp.put("UAT_versionNumber", "" + record.getUAT_versionNumber());
            tmp.put("official_versionNumber", "" + record.getOfficial_versionNumber());
            tmp.put("shedule_functionTestVersion_submit", "" + record.getShedule_functionTestVersion_submit());
            tmp.put("shedule_functionTestVersion_finish", "" + record.getShedule_functionTestVersion_finish());
            tmp.put("shedule_officialVersion_submit", "" + record.getShedule_officialVersion_submit());
            tmp.put("date_of_production", "" + record.getDate_of_production());
            tmp.put("lastest_progress", "" + record.getLastest_progress());
            tmp.put("description", "" + record.getDescription());
//            tmp.put("", "" + record.getTeam_responsible_for());
//            tmp.put("", "" + record.getUser_last_changed());
//            tmp.put("", "" + record.getRecord_update_time());

            recordlist.add(tmp);
        }
        return recordlist;
    }

    @Override
    public int getTotalFoundRecordNumbers() {
        return requirementChangeRecordMapper.getTotalFoundRecordNumber();
    }

    // 清空已有的文件内容，以便下次重新写入新的内容
    public static void clearInfoForFile(String fileName) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Map<String, Object>> getThisDetailRecordsById(String id) {

        RequirementChangeRecord record = requirementChangeRecordMapper.findRequirementRecordsById(Integer.parseInt(id));

        Map<String, Object> tmp = new HashMap<String, Object>();

        tmp.put("id", "" + record.getId());
        tmp.put("demand_id", "" + record.getDemand_id());
        tmp.put("demand_name", "" + record.getDemand_name());
        tmp.put("demand_details", "" + record.getDemand_details());
        tmp.put("demand_class", "" + record.getDemand_class());
        tmp.put("demand_content", "" + record.getDemand_content());
        tmp.put("priority", record.getPriority());

        //TODO 提取到priority_desc字段，拆分做一个下来，写入一个combobox对应的
        String priority_desc = record.getPriority_desc();
        String[] desc_list = priority_desc.split(",|，");
        String desc_res = "";
        boolean[] tan = {false, false, false, false, false};
        String[] priority_description = {"业务部门年度重点工作", "业务部门绩效目标", "行领导关注", "业务负责人关注", "监管要求"};

        ArrayList<Map<String, Object>> prio_desc_json = new ArrayList<Map<String, Object>>();
        for (int i = 1; i < desc_list.length; i++) {
            // 记录预设的优先级说明选项，哪些被选择
            if (desc_list[i].length() == 1) {
                tan[Integer.parseInt(desc_list[i])] = true;
                desc_res += priority_description[Integer.parseInt(desc_list[i])-1]+"; ";//已有内部说明查询字典获取text
            }
        }
        //String[] priority_description = {"业务部门年度重点工作", "业务部门绩效目标", "行领导关注", "业务负责人关注", "监管要求"};
        for (int i = 0; i < 5; i++) {
            if (tan[i]) {
                Map<String, Object> temp = new HashMap<String, Object>();
                temp.put("id", i + 1);
                temp.put("text", priority_description[i]);
                temp.put("selected", true);
                prio_desc_json.add(temp);
            } else {
                Map<String, Object> temp = new HashMap<String, Object>();
                temp.put("id", i + 1);
                temp.put("text", priority_description[i]);
                prio_desc_json.add(temp);
            }

        }
        int count = 6;
        for (int i = desc_list.length - 1; i >= 0; i--) {
            if (desc_list[i].length() == 1)
                break;
            Map<String, Object> temp = new HashMap<String, Object>();
            temp.put("id", count);
            temp.put("text", desc_list[i]);
            temp.put("selected", true);
            prio_desc_json.add(temp);
            count++;
            
            desc_res += desc_list[i]+"; ";//其他说明直接连接
        }

        System.out.println("the prio_desc_json 输入的数据是：" + prio_desc_json);

        // 将数据prio_desc_json写入到对应的json文件中，
        String classpath = this.getClass().getResource("/").getPath().replaceFirst("/", "");
        System.out.println("classpath is: " + classpath);
        String webappRoot = classpath.replaceAll("WEB-INF/classes/", "");
        System.out.println("webappRoot is: " + webappRoot);

        String json_path = webappRoot + "combobox_data.json";
        System.out.println("json_path is: " + json_path);

        clearInfoForFile(json_path); //先清空内容，再写入
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(json_path), prio_desc_json);
        } catch (IOException e) {
            e.printStackTrace();
        }


        tmp.put("priority_desc",desc_res);
        tmp.put("business_value", "" + record.getBusiness_value());
        tmp.put("demand_status", record.getDemand_status());
        tmp.put("batch", record.getBatch());
        tmp.put("business_department", "" + record.getBusiness_department());
        tmp.put("business_team", "" + record.getBusiness_team());
        tmp.put("leadOrCooperate", record.getLeadOrCooperate());
        tmp.put("product_name", "" + record.getProduct_name());
        tmp.put("version_status", record.getVersion_status());
        tmp.put("workload", "" + record.getWorkload());
        tmp.put("external_workload", "" + record.getExternal_workload());
        tmp.put("vender_workload", "" + record.getVender_workload());
        tmp.put("development_model", record.getDevelopment_model());
        tmp.put("main_product_situation", "" + record.getMain_product_situation());
        tmp.put("demand_leader", "" + record.getDemand_leader());
        tmp.put("development_leader", "" + record.getDevelopment_leader());
        tmp.put("task_code", "" + record.getTask_code());
        tmp.put("project_code", "" + record.getProject_code());
        tmp.put("is_newAddResources", record.getIs_newAddResources());
        tmp.put("is_dataTransfer", record.getIs_dataTransfer());
        tmp.put("is_performanceTest", record.getIs_performanceTest());
        tmp.put("update_date", "" + record.getUpdate_date());
        tmp.put("technicalPlan_desc", "" + record.getTechnicalPlan_desc());
        tmp.put("task_type", record.getTask_type());
        tmp.put("UAT_versionNumber", "" + record.getUAT_versionNumber());
        tmp.put("official_versionNumber", "" + record.getOfficial_versionNumber());
        tmp.put("shedule_functionTestVersion_submit", "" + record.getShedule_functionTestVersion_submit());
        tmp.put("shedule_functionTestVersion_finish", "" + record.getShedule_functionTestVersion_finish());
        tmp.put("shedule_officialVersion_submit", "" + record.getShedule_officialVersion_submit());
        tmp.put("date_of_production", "" + record.getDate_of_production());
        tmp.put("lastest_progress", "" + record.getLastest_progress());
        tmp.put("description", "" + record.getDescription());

        ArrayList<Map<String, Object>> recordlist = new ArrayList<Map<String, Object>>();
        recordlist.add(tmp);
        return recordlist;
    }

    @Override
    public ArrayList<Map<String, Object>> getNullDetailRecord() {
        Map<String, Object> tmp = new HashMap<String, Object>();

        tmp.put("id", "" + 1);
        tmp.put("demand_id", "FR-XXXXXX-XXXXX");
        tmp.put("demand_name", " ");
        tmp.put("demand_details", " ");
        tmp.put("demand_class", " ");
        tmp.put("demand_content", " ");
        tmp.put("priority", 3);

        // 提取到priority_desc字段，拆分做一个下来，写入一个combobox对应的json文件中
        String desc_res = "";
        String[] priority_description = {"业务部门年度重点工作", "业务部门绩效目标", "行领导关注", "业务负责人关注", "监管要求"};

        ArrayList<Map<String, Object>> prio_desc_json = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < 5; i++) {
            Map<String, Object> temp = new HashMap<String, Object>();
            temp.put("id", i + 1);
            temp.put("text", priority_description[i]);
            prio_desc_json.add(temp);
        }

        // 将数据prio_desc_json写入到对应的json文件中，
        String classpath = this.getClass().getResource("/").getPath().replaceFirst("/", "");
        String webappRoot = classpath.replaceAll("WEB-INF/classes/", "");
        String json_path = webappRoot + "combobox_data.json";

        //先清空内容，再写入
        clearInfoForFile(json_path);
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(json_path), prio_desc_json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tmp.put("priority_desc", "");
        tmp.put("business_value", " ");
        tmp.put("demand_status", 1);
        tmp.put("batch", 1);
        tmp.put("business_department", " ");
        tmp.put("business_team", " ");
        tmp.put("leadOrCooperate", 1);
        tmp.put("product_name", " ");
        tmp.put("version_status", 1);
        tmp.put("workload", " ");
        tmp.put("external_workload", " ");
        tmp.put("vender_workload", " ");
        tmp.put("development_model", 1);
        tmp.put("main_product_situation", " ");
        tmp.put("demand_leader", " ");
        tmp.put("development_leader", " ");
        tmp.put("task_code", " ");
        tmp.put("project_code", " ");
        tmp.put("is_newAddResources", 1);
        tmp.put("is_dataTransfer", 1);
        tmp.put("is_performanceTest", 1);
        tmp.put("update_date", "1970-01-01");
        tmp.put("technicalPlan_desc", " ");
        tmp.put("task_type", 1);
        tmp.put("UAT_versionNumber", " ");
        tmp.put("official_versionNumber", " ");
        tmp.put("shedule_functionTestVersion_submit", "1970-01-01");
        tmp.put("shedule_functionTestVersion_finish", "1970-01-01");
        tmp.put("shedule_officialVersion_submit", "1970-01-01");
        tmp.put("date_of_production", "1970-01-01");
        tmp.put("lastest_progress", " ");
        tmp.put("description", " ");

        ArrayList<Map<String, Object>> recordlist = new ArrayList<Map<String, Object>>();
        recordlist.add(tmp);
        return recordlist;
    }

    @Override
    public boolean deleteRequirementRecordByDemand_id(String demand_id) {
        try {
            requirementChangeRecordMapper.deleteRequirementRecordByDemand_id(demand_id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {

        }
    }

    @Override
    public boolean deleteRequirementRecordById(String id) {
        try {
            requirementChangeRecordMapper.deleteRequirementRecordById(Integer.parseInt(id));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {

        }
    }
}
