package com.sixmai.domain;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by 未来人来xw on 2019/7/18.
 */
public class RequirementChangeRecord {
    private int id;
    private String demand_id;
    private String demand_name;
    private String demand_details;
    private String demand_class;
    private String demand_content;
    private int priority;
    private int priority_desc;
    private String business_value;
    private int demand_status;
    private int batch;
    private String business_department;
    private String business_team;
    private int leadOrCooperate;
    private String product_name;
    private int version_status;
    private String workload;
    private String external_workload;
    private String vender_workload;
    private int development_model;
    private String main_product_situation;
    private String demand_leader;
    private String development_leader;
    private String task_code;
    private String project_code;
    private int is_newAddResources;
    private int is_dataTransfer;
    private int is_performanceTest;
    private Date update_date;
    private String technicalPlan_desc;
    private int task_type;
    private String UAT_versionNumber;
    private String official_versionNumber;
    private Date shedule_functionTestVersion_submit;
    private Date shedule_functionTestVersion_finish;
    private Date shedule_officialVersion_submit;
    private Date date_of_production;
    private String lastest_progress;
    private String description;
    private int team_responsible_for;
    private String user_last_changed;
    private Timestamp record_update_time;

    @Override
    public String toString() {
        return "RequirementChangeRecord{" +
                "id='" + id + '\'' +
                "demand_id='" + demand_id + '\'' +
                ", demand_name='" + demand_name + '\'' +
                ", demand_details='" + demand_details + '\'' +
                ", demand_class='" + demand_class + '\'' +
                ", demand_content='" + demand_content + '\'' +
                ", priority=" + priority +
                ", priority_desc=" + priority_desc +
                ", business_value='" + business_value + '\'' +
                ", demand_status=" + demand_status +
                ", batch=" + batch +
                ", business_department='" + business_department + '\'' +
                ", business_team='" + business_team + '\'' +
                ", leadOrCooperate=" + leadOrCooperate +
                ", product_name='" + product_name + '\'' +
                ", version_status=" + version_status +
                ", workload='" + workload + '\'' +
                ", external_workload='" + external_workload + '\'' +
                ", vender_workload='" + vender_workload + '\'' +
                ", development_model=" + development_model +
                ", main_product_situation='" + main_product_situation + '\'' +
                ", demand_leader='" + demand_leader + '\'' +
                ", development_leader='" + development_leader + '\'' +
                ", task_code='" + task_code + '\'' +
                ", project_code='" + project_code + '\'' +
                ", is_newAddResources=" + is_newAddResources +
                ", is_dataTransfer=" + is_dataTransfer +
                ", is_performanceTest=" + is_performanceTest +
                ", update_date=" + update_date +
                ", technicalPlan_desc='" + technicalPlan_desc + '\'' +
                ", task_type=" + task_type +
                ", UAT_versionNumber='" + UAT_versionNumber + '\'' +
                ", official_versionNumber='" + official_versionNumber + '\'' +
                ", shedule_functionTestVersion_submit=" + shedule_functionTestVersion_submit +
                ", shedule_functionTestVersion_finish=" + shedule_functionTestVersion_finish +
                ", shedule_officialVersion_submit=" + shedule_officialVersion_submit +
                ", date_of_production=" + date_of_production +
                ", lastest_progress='" + lastest_progress + '\'' +
                ", description='" + description + '\'' +
                ", team_responsible_for=" + team_responsible_for +
                ", user_last_changed='" + user_last_changed + '\'' +
                ", record_update_time=" + record_update_time +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAll(int id,
                       String demand_id,
                       String demand_name,
                       String demand_details,
                       String demand_class,
                       String demand_content,
                       int priority,
                       int priority_desc,
                       String business_value,
                       int demand_status,
                       int batch,
                       String business_department,
                       String business_team,
                       int leadOrCooperate,
                       String product_name,
                       int version_status,
                       String workload,
                       String external_workload,
                       String vender_workload,
                       int development_model,
                       String main_product_situation,
                       String demand_leader,
                       String development_leader,
                       String task_code,
                       String project_code,
                       int is_newAddResources,
                       int is_dataTransfer,
                       int is_performanceTest,
                       Date update_date,
                       String technicalPlan_desc,
                       int task_type,
                       String UAT_versionNumber,
                       String official_versionNumber,
                       Date shedule_functionTestVersion_submit,
                       Date shedule_functionTestVersion_finish,
                       Date shedule_officialVersion_submit,
                       Date date_of_production,
                       String lastest_progress,
                       String description,
                       int team_responsible_for,
                       String user_last_changed,
                       Timestamp record_update_time
                       )
    {
        setId(id);
        setDemand_id(demand_id);
        setDemand_name(demand_name);
        setDemand_details(demand_details);
        setDemand_class(demand_class);
        setDemand_content(demand_content);
        setPriority(priority);
        setPriority_desc(priority_desc);
        setBusiness_value(business_value);
        setDemand_status(demand_status);
        setBatch(batch);
        setBusiness_department(business_department);
        setBusiness_team(business_team);
        setLeadOrCooperate(leadOrCooperate);
        setProduct_name(product_name);
        setVersion_status(version_status);
        setWorkload(workload);
        setExternal_workload(external_workload);
        setVender_workload(vender_workload);
        setDevelopment_model(development_model);
        setMain_product_situation(main_product_situation);
        setDemand_leader(demand_leader);
        setDevelopment_leader(development_leader);
        setTask_code(task_code);
        setProject_code(project_code);
        setIs_newAddResources(is_newAddResources);
        setIs_dataTransfer(is_dataTransfer);
        setIs_performanceTest(is_performanceTest);
        setUpdate_date(update_date);
        setTechnicalPlan_desc(technicalPlan_desc);
        setTask_type(task_type);
        setUAT_versionNumber(UAT_versionNumber);
        setOfficial_versionNumber(official_versionNumber);
        setShedule_functionTestVersion_submit(shedule_functionTestVersion_submit);
        setShedule_functionTestVersion_finish(shedule_functionTestVersion_finish);
        setShedule_officialVersion_submit(shedule_officialVersion_submit);
        setDate_of_production(date_of_production);
        setLastest_progress(lastest_progress);
        setDescription(description);
        setTeam_responsible_for(team_responsible_for);
        setUser_last_changed(user_last_changed);
        setRecord_update_time(record_update_time);
    }

    public Date getShedule_functionTestVersion_submit() {
        return shedule_functionTestVersion_submit;
    }

    public void setShedule_functionTestVersion_submit(Date shedule_functionTestVersion_submit) {
        this.shedule_functionTestVersion_submit = shedule_functionTestVersion_submit;
    }

    public String getDemand_id() {
        return demand_id;
    }

    public void setDemand_id(String demand_id) {
        this.demand_id = demand_id;
    }

    public String getDemand_name() {
        return demand_name;
    }

    public void setDemand_name(String demand_name) {
        this.demand_name = demand_name;
    }

    public String getDemand_details() {
        return demand_details;
    }

    public void setDemand_details(String demand_details) {
        this.demand_details = demand_details;
    }

    public String getDemand_class() {
        return demand_class;
    }

    public void setDemand_class(String demand_class) {
        this.demand_class = demand_class;
    }

    public String getDemand_content() {
        return demand_content;
    }

    public void setDemand_content(String demand_content) {
        this.demand_content = demand_content;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority_desc() {
        return priority_desc;
    }

    public void setPriority_desc(int priority_desc) {
        this.priority_desc = priority_desc;
    }

    public String getBusiness_value() {
        return business_value;
    }

    public void setBusiness_value(String business_value) {
        this.business_value = business_value;
    }

    public int getDemand_status() {
        return demand_status;
    }

    public void setDemand_status(int demand_status) {
        this.demand_status = demand_status;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public String getBusiness_department() {
        return business_department;
    }

    public void setBusiness_department(String business_department) {
        this.business_department = business_department;
    }

    public String getBusiness_team() {
        return business_team;
    }

    public void setBusiness_team(String business_team) {
        this.business_team = business_team;
    }

    public int getLeadOrCooperate() {
        return leadOrCooperate;
    }

    public void setLeadOrCooperate(int leadOrCooperate) {
        this.leadOrCooperate = leadOrCooperate;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getVersion_status() {
        return version_status;
    }

    public void setVersion_status(int version_status) {
        this.version_status = version_status;
    }

    public String getWorkload() {
        return workload;
    }

    public void setWorkload(String workload) {
        this.workload = workload;
    }

    public String getExternal_workload() {
        return external_workload;
    }

    public void setExternal_workload(String external_workload) {
        this.external_workload = external_workload;
    }

    public String getVender_workload() {
        return vender_workload;
    }

    public void setVender_workload(String vender_workload) {
        this.vender_workload = vender_workload;
    }

    public int getDevelopment_model() {
        return development_model;
    }

    public void setDevelopment_model(int development_model) {
        this.development_model = development_model;
    }

    public String getMain_product_situation() {
        return main_product_situation;
    }

    public void setMain_product_situation(String main_product_situation) {
        this.main_product_situation = main_product_situation;
    }

    public String getDemand_leader() {
        return demand_leader;
    }

    public void setDemand_leader(String demand_leader) {
        this.demand_leader = demand_leader;
    }

    public String getDevelopment_leader() {
        return development_leader;
    }

    public void setDevelopment_leader(String development_leader) {
        this.development_leader = development_leader;
    }

    public String getTask_code() {
        return task_code;
    }

    public void setTask_code(String task_code) {
        this.task_code = task_code;
    }

    public String getProject_code() {
        return project_code;
    }

    public void setProject_code(String project_code) {
        this.project_code = project_code;
    }

    public int getIs_newAddResources() {
        return is_newAddResources;
    }

    public void setIs_newAddResources(int is_newAddResources) {
        this.is_newAddResources = is_newAddResources;
    }

    public int getIs_dataTransfer() {
        return is_dataTransfer;
    }

    public void setIs_dataTransfer(int is_dataTransfer) {
        this.is_dataTransfer = is_dataTransfer;
    }

    public int getIs_performanceTest() {
        return is_performanceTest;
    }

    public void setIs_performanceTest(int is_performanceTest) {
        this.is_performanceTest = is_performanceTest;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public String getTechnicalPlan_desc() {
        return technicalPlan_desc;
    }

    public void setTechnicalPlan_desc(String technicalPlan_desc) {
        this.technicalPlan_desc = technicalPlan_desc;
    }

    public int getTask_type() {
        return task_type;
    }

    public void setTask_type(int task_type) {
        this.task_type = task_type;
    }

    public String getUAT_versionNumber() {
        return UAT_versionNumber;
    }

    public void setUAT_versionNumber(String UAT_versionNumber) {
        this.UAT_versionNumber = UAT_versionNumber;
    }

    public String getOfficial_versionNumber() {
        return official_versionNumber;
    }

    public void setOfficial_versionNumber(String official_versionNumber) {
        this.official_versionNumber = official_versionNumber;
    }

    public Date getShedule_functionTestVersion_finish() {
        return shedule_functionTestVersion_finish;
    }

    public void setShedule_functionTestVersion_finish(Date shedule_functionTestVersion_finish) {
        this.shedule_functionTestVersion_finish = shedule_functionTestVersion_finish;
    }

    public Date getShedule_officialVersion_submit() {
        return shedule_officialVersion_submit;
    }

    public void setShedule_officialVersion_submit(Date shedule_officialVersion_submit) {
        this.shedule_officialVersion_submit = shedule_officialVersion_submit;
    }

    public Date getDate_of_production() {
        return date_of_production;
    }

    public void setDate_of_production(Date date_of_production) {
        this.date_of_production = date_of_production;
    }

    public String getLastest_progress() {
        return lastest_progress;
    }

    public void setLastest_progress(String lastest_progress) {
        this.lastest_progress = lastest_progress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTeam_responsible_for() {
        return team_responsible_for;
    }

    public void setTeam_responsible_for(int team_responsible_for) {
        this.team_responsible_for = team_responsible_for;
    }

    public String getUser_last_changed() {
        return user_last_changed;
    }

    public void setUser_last_changed(String user_last_changed) {
        this.user_last_changed = user_last_changed;
    }

    public Timestamp getRecord_update_time() {
        return record_update_time;
    }

    public void setRecord_update_time(Timestamp record_update_time) {
        this.record_update_time = record_update_time;
    }
}
