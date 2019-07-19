package com.sixmai.service;

import com.sixmai.domain.UserInfo;

import java.util.List;
import java.util.Map;

public interface LoginService {
    Map<String,Object> login(String loginname, String password,int role);
    List<UserInfo> findAll();
    int addUser(UserInfo userInfo);
}
