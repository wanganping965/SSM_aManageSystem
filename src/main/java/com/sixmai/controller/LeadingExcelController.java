package com.sixmai.controller;

/**
 * Created by 未来人来xw on 2019/7/30.
 */
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixmai.domain.RequirementChangeRecord;
import com.sixmai.service.RequirementChangeRecordService;
import com.sixmai.util.ImportExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
@RequestMapping("/uploadExcel")
@SessionAttributes({"username","role"})
public class LeadingExcelController {

    private RequirementChangeRecordService requirementChangeRecordService;

    @Autowired
    public void setRequirementChangeRecordService(@Qualifier("requirementChangeRecordServiceImpl") RequirementChangeRecordService requirementChangeRecordService) {
        this.requirementChangeRecordService = requirementChangeRecordService;
    }

    @RequestMapping("/form")
    public String form(HttpServletRequest request)throws Exception{
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        InputStream in =null;
        List<List<Object>> listob = null;
        MultipartFile file = multipartRequest.getFile("upfile");

        if(file.isEmpty()){
            throw new Exception("文件不存在！");
        }
        in = file.getInputStream();
        listob = new ImportExcelUtil().getBankListByExcel(in,file.getOriginalFilename());
        in.close();

        //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
        for (int i = 0; i < listob.size(); i++) {
            List<Object> lo = listob.get(i);

        }
        return null;
    }

    @RequestMapping(value="/ajax")
    public void ajaxUploadExcel(HttpServletRequest request, HttpServletResponse response,
                                @ModelAttribute("role")String team_responsible_for,
                                @ModelAttribute("username")String user_last_changed) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        InputStream in =null;
        List<List<Object>> listob = null;
        MultipartFile file = multipartRequest.getFile("upfile");
        if(file.isEmpty()){
            throw new Exception("文件不存在！");
        }


        in = file.getInputStream();
        listob = new ImportExcelUtil().getBankListByExcel(in,file.getOriginalFilename());


        System.out.println("utils获取的Excel表中的内容为："+listob.toString());
        //获取当前的时间 年月日时分秒
        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String record_update_time = "";

        //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
        for (int i = 0; i < listob.size(); i++) {
            if(i ==0 && listob.get(0).size() != 39){
                throw new Exception("导入表格的列数不合法！");
            }
            List<Object> lo = listob.get(i);

            record_update_time = sdFormatter.format(nowTime);
            if (requirementChangeRecordService.setRequirementRecordByExcelData(
                    lo.get(1).toString(),lo.get(2).toString(),lo.get(3).toString(),lo.get(4).toString(),lo.get(5).toString(),
                    lo.get(6).toString(),lo.get(7).toString(),lo.get(8).toString(),lo.get(9).toString(),lo.get(10).toString(),
                    lo.get(11).toString(),lo.get(12).toString(),lo.get(13).toString(),lo.get(14).toString(),lo.get(15).toString(),
                    lo.get(16).toString(),lo.get(17).toString(),lo.get(18).toString(),lo.get(19).toString(),lo.get(20).toString(),
                    lo.get(21).toString(),lo.get(22).toString(),lo.get(23).toString(),lo.get(24).toString(),lo.get(25).toString(),
                    lo.get(26).toString(),lo.get(27).toString(),lo.get(28).toString(),lo.get(29).toString(),lo.get(30).toString(),
                    lo.get(31).toString(),lo.get(32).toString(),lo.get(33).toString(),lo.get(34).toString(),lo.get(35).toString(),
                    lo.get(36).toString(),lo.get(37).toString(),lo.get(38).toString(),
                    team_responsible_for,user_last_changed,record_update_time) == false){
                System.out.println("第"+(i + 1)+"行数据存在不合法项");
                throw new Exception("第"+(i + 1)+"行数据存在不合法项！");
            }
        }
        PrintWriter out = null;
        response.setCharacterEncoding("utf-8");  //防止ajax接受到的中文信息乱码
        out = response.getWriter();
        out.print("文件导入成功！");
        out.flush();
        out.close();
    }

    //多文件上传(也包含单文件)
    @RequestMapping(value="/ajaxMulti")
    public void ajaxUploadExcel11(HttpServletRequest request,HttpServletResponse response) throws Exception {
        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

        InputStream in =null;
        List<List<Object>> listob = null;
        //判断 request 是否有文件上传,即多部分请求
        if(multipartResolver.isMultipart(request)) {
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> files =  multiRequest.getFiles("file");

           for(int k=0;k<files.size();k ++){
               MultipartFile file = files.get(k);

               in = file.getInputStream();
               if(file.isEmpty()){
                   throw new Exception("第"+k+" 个文件不存在！");
               }
               listob = new ImportExcelUtil().getBankListByExcel(in,file.getOriginalFilename());

               //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
               for (int i = 0; i < listob.size(); i++) {
                   List<Object> lo = listob.get(i);
//            Family1 family = new Family();
//            family1.setJtbh(String.valueOf(lo.get(0)));
//            System.out.println("打印信息-->"+family.toString());
                   for(int j=0; j<lo.size(); j ++)
                   {
                       System.out.print(" "+lo.get(j));
                   }
                   System.out.println();

               }
           }
        }

        PrintWriter out = null;
        response.setCharacterEncoding("utf-8");  //防止ajax接受到的中文信息乱码
        out = response.getWriter();
        out.print("文件导入成功！");
        out.flush();
        out.close();
    }


}
