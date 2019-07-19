package com.sixmai.service.impl;

import com.sixmai.domain.RequirementChangeRecord;
import com.sixmai.mapper.RequirementChangeRecordMapper;
import com.sixmai.service.RequirementChangeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
                                        String shedule_functionTestVersion_finish, String shedule_officialVersion_submit,
                                        String date_of_production, String lastest_progress, String description,
                                        String team_responsible_for, String user_last_changed,String record_update_time) {
        RequirementChangeRecord record = new RequirementChangeRecord();
//        List<RequirementChangeRecord> recordList = requirementChangeRecordMapper.findRequirementRecordsByDemand_id(demand_id);
//        if(recordList.size() == 0){
        record.setAll( demand_id,  demand_name,  demand_details,
                demand_class,  demand_content,  Integer.parseInt(priority),
                Integer.parseInt(priority_desc),  business_value,  Integer.parseInt(demand_status),
                Integer.parseInt(batch),  business_department,  business_team,
                Integer.parseInt(leadOrCooperate),  product_name,  Integer.parseInt(version_status),
                workload,  external_workload,  vender_workload,
                Integer.parseInt(development_model),  main_product_situation,
                demand_leader,  development_leader,  task_code,
                project_code,  Integer.parseInt(is_newAddResources),  Integer.parseInt(is_dataTransfer),
                Integer.parseInt(is_performanceTest),
                java.sql.Date.valueOf(update_date),
                technicalPlan_desc,
                Integer.parseInt(task_type),  UAT_versionNumber,
                official_versionNumber,
                java.sql.Date.valueOf(shedule_functionTestVersion_finish),
                java.sql.Date.valueOf(shedule_officialVersion_submit),
                java.sql.Date.valueOf(date_of_production),  lastest_progress,  description,
                Integer.parseInt(team_responsible_for),  user_last_changed,
                java.sql.Timestamp.valueOf(record_update_time));
//        }

        // 不管是修改还是新增，实际都是在表中新增了一条记录
        //每次展示的都是上一次最新修改的记录
        requirementChangeRecordMapper.addRequirementRecord(record);
        return true;
    }

    @Override
    public ArrayList<ArrayList<String>> findAllRequirementsRecord() {
        ArrayList<ArrayList<String>> recordlist = new ArrayList<ArrayList<String>>();
        List<RequirementChangeRecord> records =  requirementChangeRecordMapper.findAllRequirement();
        for(int i=0; i < records.size();i ++){
            ArrayList<String> tmp = new ArrayList<String>();
            RequirementChangeRecord record = records.get(i);
            tmp.add("" + record.getDemand_id());
            tmp.add("" + record.getDemand_name());
            tmp.add("" + record.getDemand_details());
            tmp.add("" + record.getDemand_class());
            tmp.add("" + record.getDemand_content());
            tmp.add("" + record.getPriority());
            tmp.add("" + record.getPriority_desc());
            tmp.add("" + record.getBusiness_value());
            tmp.add("" + record.getDemand_status());
            tmp.add("" + record.getBatch());
            tmp.add("" + record.getBusiness_department());
            tmp.add("" + record.getBusiness_team());
            tmp.add("" + record.getLeadOrCooperate());
            tmp.add("" + record.getProduct_name());
            tmp.add("" + record.getVersion_status());
            tmp.add("" + record.getWorkload());
            tmp.add("" + record.getExternal_workload());
            tmp.add("" + record.getVender_workload());
            tmp.add("" + record.getDevelopment_model());
            tmp.add("" + record.getMain_product_situation());
            tmp.add("" + record.getDemand_leader());
            tmp.add("" + record.getDevelopment_leader());
            tmp.add("" + record.getTask_code());
            tmp.add("" + record.getProject_code());
            tmp.add("" + record.getIs_newAddResources());
            tmp.add("" + record.getIs_dataTransfer());
            tmp.add("" + record.getIs_performanceTest());
            tmp.add("" + record.getUpdate_date());
            tmp.add("" + record.getTechnicalPlan_desc());
            tmp.add("" + record.getTask_type());
            tmp.add("" + record.getUAT_versionNumber());
            tmp.add("" + record.getOfficial_versionNumber());
            tmp.add("" + record.getShedule_functionTestVersion_finish());
            tmp.add("" + record.getShedule_officialVersion_submit());
            tmp.add("" + record.getDate_of_production());
            tmp.add("" + record.getLastest_progress());
            tmp.add("" + record.getDescription());
            tmp.add("" + record.getTeam_responsible_for());
            tmp.add("" + record.getUser_last_changed());
            tmp.add("" + record.getRecord_update_time());

            recordlist.add(tmp);
        }
        return recordlist;
    }

    @Override
    public ArrayList<ArrayList<String>> findAllRequirementByOwnedTeam(String role) {
        ArrayList<ArrayList<String>> recordlist = new ArrayList<ArrayList<String>>();
        List<RequirementChangeRecord> records =  requirementChangeRecordMapper.findAllRequirementByOwnedTeam(Integer.parseInt(role));
        for(int i=0; i < records.size();i ++){
            ArrayList<String> tmp = new ArrayList<String>();
            RequirementChangeRecord record = records.get(i);
            tmp.add("" + record.getDemand_id());
            tmp.add("" + record.getDemand_name());
            tmp.add("" + record.getDemand_details());
            tmp.add("" + record.getDemand_class());
            tmp.add("" + record.getDemand_content());
            tmp.add("" + record.getPriority());
            tmp.add("" + record.getPriority_desc());
            tmp.add("" + record.getBusiness_value());
            tmp.add("" + record.getDemand_status());
            tmp.add("" + record.getBatch());
            tmp.add("" + record.getBusiness_department());
            tmp.add("" + record.getBusiness_team());
            tmp.add("" + record.getLeadOrCooperate());
            tmp.add("" + record.getProduct_name());
            tmp.add("" + record.getVersion_status());
            tmp.add("" + record.getWorkload());
            tmp.add("" + record.getExternal_workload());
            tmp.add("" + record.getVender_workload());
            tmp.add("" + record.getDevelopment_model());
            tmp.add("" + record.getMain_product_situation());
            tmp.add("" + record.getDemand_leader());
            tmp.add("" + record.getDevelopment_leader());
            tmp.add("" + record.getTask_code());
            tmp.add("" + record.getProject_code());
            tmp.add("" + record.getIs_newAddResources());
            tmp.add("" + record.getIs_dataTransfer());
            tmp.add("" + record.getIs_performanceTest());
            tmp.add("" + record.getUpdate_date());
            tmp.add("" + record.getTechnicalPlan_desc());
            tmp.add("" + record.getTask_type());
            tmp.add("" + record.getUAT_versionNumber());
            tmp.add("" + record.getOfficial_versionNumber());
            tmp.add("" + record.getShedule_functionTestVersion_finish());
            tmp.add("" + record.getShedule_officialVersion_submit());
            tmp.add("" + record.getDate_of_production());
            tmp.add("" + record.getLastest_progress());
            tmp.add("" + record.getDescription());
            tmp.add("" + record.getTeam_responsible_for());
            tmp.add("" + record.getUser_last_changed());
            tmp.add("" + record.getRecord_update_time());

            recordlist.add(tmp);
        }
        return recordlist;
    }

    @Override
    public ArrayList<ArrayList<String>> findAllRequirementByAndUsername(String role, String username) {
        ArrayList<ArrayList<String>> recordlist = new ArrayList<ArrayList<String>>();
        List<RequirementChangeRecord> records =  requirementChangeRecordMapper.findAllRequirementByAndUsername(Integer.parseInt(role),username);
        for(int i=0; i < records.size();i ++){
            ArrayList<String> tmp = new ArrayList<String>();
            RequirementChangeRecord record = records.get(i);
            tmp.add("" + record.getDemand_id());
            tmp.add("" + record.getDemand_name());
            tmp.add("" + record.getDemand_details());
            tmp.add("" + record.getDemand_class());
            tmp.add("" + record.getDemand_content());
            tmp.add("" + record.getPriority());
            tmp.add("" + record.getPriority_desc());
            tmp.add("" + record.getBusiness_value());
            tmp.add("" + record.getDemand_status());
            tmp.add("" + record.getBatch());
            tmp.add("" + record.getBusiness_department());
            tmp.add("" + record.getBusiness_team());
            tmp.add("" + record.getLeadOrCooperate());
            tmp.add("" + record.getProduct_name());
            tmp.add("" + record.getVersion_status());
            tmp.add("" + record.getWorkload());
            tmp.add("" + record.getExternal_workload());
            tmp.add("" + record.getVender_workload());
            tmp.add("" + record.getDevelopment_model());
            tmp.add("" + record.getMain_product_situation());
            tmp.add("" + record.getDemand_leader());
            tmp.add("" + record.getDevelopment_leader());
            tmp.add("" + record.getTask_code());
            tmp.add("" + record.getProject_code());
            tmp.add("" + record.getIs_newAddResources());
            tmp.add("" + record.getIs_dataTransfer());
            tmp.add("" + record.getIs_performanceTest());
            tmp.add("" + record.getUpdate_date());
            tmp.add("" + record.getTechnicalPlan_desc());
            tmp.add("" + record.getTask_type());
            tmp.add("" + record.getUAT_versionNumber());
            tmp.add("" + record.getOfficial_versionNumber());
            tmp.add("" + record.getShedule_functionTestVersion_finish());
            tmp.add("" + record.getShedule_officialVersion_submit());
            tmp.add("" + record.getDate_of_production());
            tmp.add("" + record.getLastest_progress());
            tmp.add("" + record.getDescription());
            tmp.add("" + record.getTeam_responsible_for());
            tmp.add("" + record.getUser_last_changed());
            tmp.add("" + record.getRecord_update_time());

            recordlist.add(tmp);
        }
        return recordlist;
    }

    @Override
    public ArrayList<ArrayList<String>> findThisRequirementHistoryVersion(String demand_id) {
        ArrayList<ArrayList<String>> recordlist = new ArrayList<ArrayList<String>>();
        List<RequirementChangeRecord> records =  requirementChangeRecordMapper.findRequirementRecordsByDemand_id(demand_id);
        for(int i=0; i < records.size();i ++){
            ArrayList<String> tmp = new ArrayList<String>();
            RequirementChangeRecord record = records.get(i);
            tmp.add("" + record.getDemand_id());
            tmp.add("" + record.getDemand_name());
            tmp.add("" + record.getDemand_details());
            tmp.add("" + record.getDemand_class());
            tmp.add("" + record.getDemand_content());
            tmp.add("" + record.getPriority());
            tmp.add("" + record.getPriority_desc());
            tmp.add("" + record.getBusiness_value());
            tmp.add("" + record.getDemand_status());
            tmp.add("" + record.getBatch());
            tmp.add("" + record.getBusiness_department());
            tmp.add("" + record.getBusiness_team());
            tmp.add("" + record.getLeadOrCooperate());
            tmp.add("" + record.getProduct_name());
            tmp.add("" + record.getVersion_status());
            tmp.add("" + record.getWorkload());
            tmp.add("" + record.getExternal_workload());
            tmp.add("" + record.getVender_workload());
            tmp.add("" + record.getDevelopment_model());
            tmp.add("" + record.getMain_product_situation());
            tmp.add("" + record.getDemand_leader());
            tmp.add("" + record.getDevelopment_leader());
            tmp.add("" + record.getTask_code());
            tmp.add("" + record.getProject_code());
            tmp.add("" + record.getIs_newAddResources());
            tmp.add("" + record.getIs_dataTransfer());
            tmp.add("" + record.getIs_performanceTest());
            tmp.add("" + record.getUpdate_date());
            tmp.add("" + record.getTechnicalPlan_desc());
            tmp.add("" + record.getTask_type());
            tmp.add("" + record.getUAT_versionNumber());
            tmp.add("" + record.getOfficial_versionNumber());
            tmp.add("" + record.getShedule_functionTestVersion_finish());
            tmp.add("" + record.getShedule_officialVersion_submit());
            tmp.add("" + record.getDate_of_production());
            tmp.add("" + record.getLastest_progress());
            tmp.add("" + record.getDescription());
            tmp.add("" + record.getTeam_responsible_for());
            tmp.add("" + record.getUser_last_changed());
            tmp.add("" + record.getRecord_update_time());

            recordlist.add(tmp);
        }
        return recordlist;
    }

    @Override
    public ArrayList<String> getThisDetailRecordsById(String id) {

        RequirementChangeRecord record = requirementChangeRecordMapper.findRequirementRecordsById(Integer.parseInt(id));

        ArrayList<String> tmp = new ArrayList<String>();
        tmp.add("" + record.getDemand_id());
        tmp.add("" + record.getDemand_name());
        tmp.add("" + record.getDemand_details());
        tmp.add("" + record.getDemand_class());
        tmp.add("" + record.getDemand_content());
        tmp.add("" + record.getPriority());
        tmp.add("" + record.getPriority_desc());
        tmp.add("" + record.getBusiness_value());
        tmp.add("" + record.getDemand_status());
        tmp.add("" + record.getBatch());
        tmp.add("" + record.getBusiness_department());
        tmp.add("" + record.getBusiness_team());
        tmp.add("" + record.getLeadOrCooperate());
        tmp.add("" + record.getProduct_name());
        tmp.add("" + record.getVersion_status());
        tmp.add("" + record.getWorkload());
        tmp.add("" + record.getExternal_workload());
        tmp.add("" + record.getVender_workload());
        tmp.add("" + record.getDevelopment_model());
        tmp.add("" + record.getMain_product_situation());
        tmp.add("" + record.getDemand_leader());
        tmp.add("" + record.getDevelopment_leader());
        tmp.add("" + record.getTask_code());
        tmp.add("" + record.getProject_code());
        tmp.add("" + record.getIs_newAddResources());
        tmp.add("" + record.getIs_dataTransfer());
        tmp.add("" + record.getIs_performanceTest());
        tmp.add("" + record.getUpdate_date());
        tmp.add("" + record.getTechnicalPlan_desc());
        tmp.add("" + record.getTask_type());
        tmp.add("" + record.getUAT_versionNumber());
        tmp.add("" + record.getOfficial_versionNumber());
        tmp.add("" + record.getShedule_functionTestVersion_finish());
        tmp.add("" + record.getShedule_officialVersion_submit());
        tmp.add("" + record.getDate_of_production());
        tmp.add("" + record.getLastest_progress());
        tmp.add("" + record.getDescription());
        tmp.add("" + record.getTeam_responsible_for());
        tmp.add("" + record.getUser_last_changed());
        tmp.add("" + record.getRecord_update_time());

        return tmp;
    }

    @Override
    public boolean deleteRequirementRecordByDemand_id(String demand_id) {
        try{
            requirementChangeRecordMapper.deleteRequirementRecordByDemand_id(demand_id);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        finally {

        }
    }

    @Override
    public boolean deleteRequirementRecordById(String id) {
        try{
            requirementChangeRecordMapper.deleteRequirementRecordById(Integer.parseInt(id));
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        finally {

        }
    }
}
