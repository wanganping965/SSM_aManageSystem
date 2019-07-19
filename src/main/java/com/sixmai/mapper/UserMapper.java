package com.sixmai.mapper;

import com.sixmai.domain.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    List<UserInfo> login(String loginname);

    List<UserInfo> findAll();
    List<UserInfo> findUsersByRoleteam(int role);

    int addUser(UserInfo userInfo);
    void deleteUserByUsername(String username);
}