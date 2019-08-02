package com.sixmai.service;

import javax.servlet.ServletOutputStream;
import java.io.OutputStream;

/**
 * Created by 未来人来xw on 2019/7/31.
 */
public interface ExcelExportService {
    public void export(String[] titles, ServletOutputStream out,
                       String demand_id, String demand_name,
                       String priority, String priority_desc,
                       String demand_status, String batch,
                       String business_department, String business_team,
                       String leadOrCooperate, String version_status,
                       String development_model, String product_name,
                       String task_code, String project_code,
                       String is_newAddResources, String is_dataTransfer,
                       String is_performanceTest,String task_type,String role) throws Exception;
}
