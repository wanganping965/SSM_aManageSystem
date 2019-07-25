package com.sixmai.mapper;

import com.sixmai.domain.RequirementChangeRecord;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableServer.POAPackage.ObjectAlreadyActive;

import java.util.List;

/**
 * Created by 未来人来xw on 2019/7/18.
 */
public interface RequirementChangeRecordMapper {
    // 用于展示所用的需求变更数据
    List<Object> findAllRequirement(@Param("start") int start, @Param("rows") int rows);
//    int findTheNumberOfAllRequirement();
    int getTotalFoundRecordNumber();
    //用于获取本团队的所有能处理的需求
    List<RequirementChangeRecord> findAllRequirementByOwnedTeam(@Param("role") int role,@Param("start")int start,@Param("rows")int rows);
//    int findTheNumberOfAllRequirementByOwnedTeam(int role);

    //用于获取本账号处理过的需求记录
    List<RequirementChangeRecord> findAllRequirementByAndUsername(@Param("role") int role,@Param("username") String username,@Param("start") int start,@Param("rows") int rows);
//    int findTheNumberOfAllRequirementByAndUsername(int role,String username);

    //获取同一需求编号的历史变更版本列表
    List<Object> findRequirementRecordsByDemand_id(@Param("demand_id") String demand_id, @Param("start") int start, @Param("rows") int rows);
    //根据具体的id，查找到具体的记录
    RequirementChangeRecord findRequirementRecordsById(int id);

    void addRequirementRecord(RequirementChangeRecord requirementChangeRecord);

    void changeRequirementRecordById(RequirementChangeRecord requirementChangeRecord);

    void  deleteRequirementRecordByDemand_id(String demand_id);
    void  deleteRequirementRecordById(int id);
}
