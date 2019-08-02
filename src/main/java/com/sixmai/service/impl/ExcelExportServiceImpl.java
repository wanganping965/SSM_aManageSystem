package com.sixmai.service.impl;

import com.sixmai.domain.RequirementChangeRecord;
import com.sixmai.mapper.RequirementChangeRecordMapper;
import com.sixmai.service.ExcelExportService;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Arrays.asList;

/**
 * Created by 未来人来xw on 2019/7/31.
 */
@Service("excelExportServiceImpl")
public class ExcelExportServiceImpl implements ExcelExportService {
    private final static List<String> priority_description_list = asList("业务部门年度重点工作", "业务部门绩效目标", "行领导关注", "业务负责人关注", "监管要求");
    private final static List<String> priority_list=asList("高","中","低");
    private final static List<String> demand_status_list=asList("意向需求","方案制定中","已回函，待排期","任务已下达","需求取消");
    private final static List<String> batch_list=asList("X91","X92","P901","X93","X94","P902","X95","X96","X97", "P903","X98","X99","X910","P904","X911","X912","待排期");
    private final static List<String> leadOrCooperate_list=asList("牵头","配合");
    private final static List<String> version_status_list=asList("有版本","配合测试");
    private final static List<String> development_model_list=asList("自主开发","厂商开发","合作开发");
    private final static List<String> is_newAddResources_list=asList("否","是");
    private final static List<String> is_performanceTest_list=asList("否","是");
    private final static List<String> is_dataTransfer_list=asList("否","是");
    private final static List<String> task_type_list=asList("项目","项目需求变更","维护需求变更","技术支持","联调测试","版本追平");

    RequirementChangeRecordMapper requirementChangeRecordMapper;

    @Autowired
    public void setRequirementChangeRecordMapper(RequirementChangeRecordMapper requirementChangeRecordMapper) {
        this.requirementChangeRecordMapper = requirementChangeRecordMapper;
    }

    /**
     * @Param :titles: 表的表头属性
     *
     * */
    @Override
    public void export(String[] titles, ServletOutputStream out,
                       String demand_id, String demand_name,
                       String priority, String priority_desc,
                       String demand_status, String batch,
                       String business_department, String business_team,
                       String leadOrCooperate, String version_status,
                       String development_model, String product_name,
                       String task_code, String project_code,
                       String is_newAddResources, String is_dataTransfer,
                       String is_performanceTest,String task_type,String role) throws Exception {
        try{
            // 第一步，创建一个workbook，对应一个Excel文件
            HSSFWorkbook workbook = new HSSFWorkbook();

            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet hssfSheet = workbook.createSheet("sheet1");

            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short

            HSSFRow row = hssfSheet.createRow(0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle hssfCellStyle = workbook.createCellStyle();

            //居中样式
            hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            // 设置表头属性名称
            HSSFCell hssfCell = null;
            for (int i = 0; i < titles.length; i++) {
                hssfCell = row.createCell(i);//列索引从0开始
                hssfCell.setCellValue(titles[i]);//列名1
                hssfCell.setCellStyle(hssfCellStyle);//列居中显示
            }

            //TODO 通过查询获取 添加查询的List《REquirementRecordList记录，然后写入到Excel中
            // 第五步，获取满足条件的数据库数据列表
            List<RequirementChangeRecord> requirementChangeRecordList = requirementChangeRecordMapper.findAllRequirementBYFiltersWithoutPages(demand_id,demand_name,Integer.parseInt(priority.trim()),priority_desc,
                    Integer.parseInt(demand_status.trim()), Integer.parseInt(batch.trim()),business_department,business_team,Integer.parseInt(leadOrCooperate),
                    Integer.parseInt(version_status.trim()),Integer.parseInt(development_model.trim()),product_name,task_code,project_code,
                    Integer.parseInt(is_newAddResources.trim()),Integer.parseInt(is_dataTransfer.trim()),Integer.parseInt(is_performanceTest.trim()),
                    Integer.parseInt(task_type.trim()),Integer.parseInt(role));

            for (int i = 0; i < requirementChangeRecordList.size(); i++) {
                row = hssfSheet.createRow(i+1);

                RequirementChangeRecord record = requirementChangeRecordList.get(i);

                // 第六步，创建单元格，并设置值
                String  id = "";
                String demand_id1 = "";
                String demand_name1= "";
                String demand_details1= "";
                String demand_class1= "";
                String demand_content1= "";
                String priority1= "";
                String priority_desc1= "";
                String business_value1= "";
                String demand_status1= "";
                String batch1= "";
                String business_department1= "";
                String business_team1= "";
                String leadOrCooperate1= "";
                String product_name1= "";
                String version_status1= "";
                String workload1= "";
                String external_workload1= "";
                String vender_workload1= "";
                String development_model1= "";
                String main_product_situation1= "";
                String demand_leader1= "";
                String development_leader1= "";
                String task_code1= "";
                String project_code1= "";
                String is_newAddResources1= "";
                String is_dataTransfer1= "";
                String is_performanceTest1= "";
                String update_date1= "";
                String technicalPlan_desc1= "";
                String task_type1= "";
                String UAT_versionNumber1= "";
                String official_versionNumber1= "";
                String shedule_functionTestVersion_submit1= "";
                String shedule_functionTestVersion_finish1= "";
                String shedule_officialVersion_submit1= "";
                String date_of_production1= "";
                String lastest_progress1= "";
                String description1= "";

                if(record.getId() != -1){
                    id += record.getId();
                }
                row.createCell(0).setCellValue(id);
                if(record.getDemand_id() != null){
                    demand_id1 += record.getDemand_id();
                }
                row.createCell(1).setCellValue(demand_id1);
                if(record.getDemand_name() != null){
                    demand_name1 += record.getDemand_name();
                }
                row.createCell(2).setCellValue(demand_name1);
                if(record.getDemand_details() !=null){
                    demand_details1 = record.getDemand_details();
                }
                row.createCell(3).setCellValue(demand_details1);

                if(record.getDemand_class() != null){
                    demand_class1 += record.getDemand_class();
                }
                row.createCell(4).setCellValue(demand_class1);
                if(record.getDemand_content() !=null){
                    demand_content1 = record.getDemand_content();
                }
                row.createCell(5).setCellValue(demand_content1);

                priority1 += priority_list.get(record.getPriority()-1);
                row.createCell(6).setCellValue(priority1);
                String[] priority_split_list = record.getPriority_desc().split(",|，");
                for(int j = 1;j<priority_split_list.length;j ++) {
                    if (priority_split_list[j].length() == 1) {
                        priority_desc1 += j + "." + priority_description_list.get(Integer.parseInt(priority_split_list[j]) - 1) + "；";
                    } else {
                        priority_desc1 += j + "." + priority_split_list[j] + "；";
                    }
                }
                row.createCell(7).setCellValue(priority_desc1);

                if(record.getBusiness_value() !=null){
                    business_value1 = record.getBusiness_value();
                }
                row.createCell(8).setCellValue(business_value1);
                demand_status1 += demand_status_list.get(record.getDemand_status()-1);
                row.createCell(9).setCellValue(demand_status1);

                batch1 += batch_list.get(record.getBatch()-1);
                row.createCell(10).setCellValue(batch1);

                if(record.getBusiness_department() !=null){
                    business_department1 = record.getBusiness_department();
                }
                row.createCell(11).setCellValue(business_department1);
                if(record.getBusiness_team() !=null){
                    business_team1 = record.getBusiness_team();
                }
                row.createCell(12).setCellValue(business_team1);
                leadOrCooperate1 += leadOrCooperate_list.get(record.getLeadOrCooperate()-1);
                row.createCell(13).setCellValue(leadOrCooperate1);

                if(record.getProduct_name() !=null){
                    product_name1 = record.getProduct_name();
                }
                row.createCell(14).setCellValue(product_name1);
                version_status1 += version_status_list.get(record.getVersion_status()-1);
                row.createCell(15).setCellValue(version_status1);

                if(record.getWorkload() !=null){
                    workload1 = record.getWorkload();
                }
                row.createCell(16).setCellValue(workload1);
                if(record.getExternal_workload() !=null){
                    external_workload1 = record.getExternal_workload();
                }
                row.createCell(17).setCellValue(external_workload1);
                if(record.getVender_workload() !=null){
                    vender_workload1 = record.getVender_workload();
                }
                row.createCell(18).setCellValue(vender_workload1);
                development_model1 += development_model_list.get(record.getDevelopment_model()-1);
                row.createCell(19).setCellValue(development_model1);

                if(record.getMain_product_situation() !=null){
                    main_product_situation1 = record.getMain_product_situation();
                }
                row.createCell(20).setCellValue(main_product_situation1);
                if(record.getDemand_leader() !=null){
                    demand_leader1 = record.getDemand_leader();
                }
                row.createCell(21).setCellValue(demand_leader1);
                if(record.getDevelopment_leader() !=null){
                    development_leader1 = record.getDevelopment_leader();
                }
                row.createCell(22).setCellValue(development_leader1);
                if(record.getTask_code() !=null){
                    task_code1 = record.getTask_code();
                }
                row.createCell(23).setCellValue(task_code1);
                if(record.getProject_code() !=null){
                    project_code1 = record.getProject_code();
                }
                row.createCell(24).setCellValue(project_code1);
                is_newAddResources1 += is_newAddResources_list.get(record.getIs_newAddResources());
                row.createCell(25).setCellValue(is_newAddResources1);
                is_dataTransfer1 += is_dataTransfer_list.get(record.getIs_dataTransfer());
                row.createCell(26).setCellValue(is_dataTransfer1);
                is_performanceTest1 += is_performanceTest_list.get(record.getIs_performanceTest());
                row.createCell(27).setCellValue(is_performanceTest1);

                update_date1 += record.getUpdate_date();
                row.createCell(28).setCellValue(update_date1);
                if(record.getTechnicalPlan_desc() !=null){
                    technicalPlan_desc1 = record.getTechnicalPlan_desc();
                }
                row.createCell(29).setCellValue(technicalPlan_desc1);
                task_type1 += task_type_list.get(record.getTask_type()-1);
                row.createCell(30).setCellValue(task_type1);

                if(record.getUAT_versionNumber() !=null){
                    UAT_versionNumber1 = record.getUAT_versionNumber();
                }
                row.createCell(31).setCellValue(UAT_versionNumber1);
                if(record.getOfficial_versionNumber() !=null){
                    official_versionNumber1 = record.getOfficial_versionNumber();
                }
                row.createCell(32).setCellValue(official_versionNumber1);

                shedule_functionTestVersion_submit1 += record.getShedule_functionTestVersion_submit();
                row.createCell(33).setCellValue(shedule_functionTestVersion_submit1);
                shedule_functionTestVersion_finish1 += record.getShedule_functionTestVersion_finish();
                row.createCell(34).setCellValue(shedule_functionTestVersion_finish1);
                shedule_officialVersion_submit1 += record.getShedule_officialVersion_submit();
                row.createCell(35).setCellValue(shedule_officialVersion_submit1);
                date_of_production1 += record.getDate_of_production();
                row.createCell(36).setCellValue(date_of_production1);
                if(record.getLastest_progress() !=null){
                    lastest_progress1 = record.getLastest_progress();
                }
                row.createCell(37).setCellValue(lastest_progress1);
                if(record.getDescription() !=null){
                    description1 = record.getDescription();
                }
                row.createCell(38).setCellValue(description1);
            }

            // 第七步，将文件输出到客户端浏览器
            try {
                workbook.write(out);
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("导出信息失败！");

        }
    }

}
