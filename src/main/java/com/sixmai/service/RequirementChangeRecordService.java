package com.sixmai.service;

import com.sixmai.domain.RequirementChangeRecord;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by 未来人来xw on 2019/7/18.
 *
 * 默认：所有传到service层的基础数据表示都是字符串类型
 */
public interface RequirementChangeRecordService {
    // *********************** 增加、修改（修改的时候，实际是在数据库中新增了一条新的record，记录下所有的历史改动记录） ***********************
    boolean setRequirementRecord(String demand_id, String demand_name, String demand_details,
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
                                 String data_of_production, String lastest_progress, String description,
                                 String team_responsible_for, String user_last_changed,String record_update_time);
    // *********************** 查看 ***********************
    Map<String,Object> findAllRequirementsRecord(String page, String rows);
    ArrayList<Map<String, Object>> findAllRequirementByOwnedTeam(String role,String page,String rows);
    ArrayList<Map<String, Object>> findAllRequirementByAndUsername(String role,String username,String page,String rows);
    int getTotalFoundRecordNumbers();

    //获取真个需求的变更的历史版本的列表，从上到下依次为最近依次更改和最开始登入的需求
    ArrayList<ArrayList<String>> findThisRequirementHistoryVersion(String demand_id);

    // 获取具体的对应于唯一标识id 的那条记录
    ArrayList<Map<String, Object>> getThisDetailRecordsById(String id);


    // *********************** 删除 ***********************
    boolean deleteRequirementRecordByDemand_id(String demand_id);
    boolean deleteRequirementRecordById(String id);
}
