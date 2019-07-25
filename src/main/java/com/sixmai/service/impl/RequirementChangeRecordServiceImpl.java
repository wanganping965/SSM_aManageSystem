package com.sixmai.service.impl;

import com.sixmai.domain.RequirementChangeRecord;
import com.sixmai.mapper.RequirementChangeRecordMapper;
import com.sixmai.service.RequirementChangeRecordService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private String dateFormatterTransfer(String before){
        String[] m_d_y = before.split("/");
        return m_d_y[2]+"-"+m_d_y[0]+"-"+m_d_y[1];
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
        record.setAll(0, demand_id, demand_name, demand_details,
                demand_class, demand_content, Integer.parseInt(priority),
                Integer.parseInt(priority_desc), business_value, Integer.parseInt(demand_status),
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
    public Map<String,Object> findAllRequirementsRecord(String page, String rows) {
        int nums = Integer.parseInt(rows);
        int start = (Integer.parseInt(page) - 1) * nums;
        ArrayList<Map<String, Object>> recordlist = new ArrayList<Map<String, Object>>();
        List<Object> recordss = requirementChangeRecordMapper.findAllRequirement(start, nums);
        Long total = new Long(0);
        if(CollectionUtils.isNotEmpty(recordss)){
            List<RequirementChangeRecord> records = (List<RequirementChangeRecord>)recordss.get(0);
            total = ((List<Long>)recordss.get(1)).get(0);


            for (int i = 0; i < records.size(); i++) {
                Map<String, Object> tmp = new HashMap<String, Object>();
                RequirementChangeRecord record = records.get(i);
                tmp.put("id", "" + record.getId());
                tmp.put("demand_id", "" + record.getDemand_id());
                tmp.put("demand_name", "" + record.getDemand_name());
                tmp.put("priority", record.getPriority());
                tmp.put("priority_desc", "" + record.getPriority_desc());
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

        Map<String,Object> res = new HashMap<String,Object>();
        res.put("rows",recordlist);
        res.put("total",total);
        return res;
    }

    @Override
    public Map<String,Object> findThisRequirementHistoryVersion(String demand_id,String page, String rows) {
        int nums = Integer.parseInt(rows);
        int start = (Integer.parseInt(page) - 1) * nums;
        ArrayList<Map<String, Object>> recordlist = new ArrayList<Map<String, Object>>();
        List<Object> recordss = requirementChangeRecordMapper.findRequirementRecordsByDemand_id(demand_id,nums,start);
        Long total = new Long(0);
        if(CollectionUtils.isNotEmpty(recordss)){
            List<RequirementChangeRecord> records = (List<RequirementChangeRecord>)recordss.get(0);
            total = ((List<Long>)recordss.get(1)).get(0);

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
                tmp.put("priority_desc", "" + record.getPriority_desc());
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

        Map<String,Object> res = new HashMap<String,Object>();
        res.put("rows",recordlist);
        res.put("total",total);
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
        tmp.put("priority_desc", "" + record.getPriority_desc());
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
        tmp.put("demand_details"," ");
        tmp.put("demand_class", " ");
        tmp.put("demand_content", " ");
        tmp.put("priority", 3);
        tmp.put("priority_desc",1);
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
