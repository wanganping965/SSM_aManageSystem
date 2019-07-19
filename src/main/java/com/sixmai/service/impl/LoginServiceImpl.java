package com.sixmai.service.impl;

import com.sixmai.domain.UserInfo;
import com.sixmai.mapper.UserMapper;
import com.sixmai.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("loginServiceImpl")
public class LoginServiceImpl implements LoginService {
    UserMapper userMapper;
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Map<String, Object> login(String loginname, String password, int role) {
        HashMap<String, Object> mm = new HashMap<String, Object>();
        List<UserInfo> users = userMapper.login(loginname);
        String msg = "msg";
        if ((users.size() == 0)) {
            mm.put("status", "401");
            mm.put(msg, "noUser");
        }
        else{
            UserInfo user = users.get(0);
            if (!password.equals(user.getPassword()) ){
                mm.put("status", "401");
                mm.put(msg, "passwordError");
            }else if(role != user.getRole()) {
                mm.put("status", "401");
                mm.put(msg, "noRoot");
            }else {
                mm.put("status", "200");
                mm.put(msg, "loginSuccess");
            }
        }
        System.out.println("msg in service :" + mm.get("msg"));
        return mm;
    }

    @Override
    public List<UserInfo> findAll() {
        return userMapper.findAll();
    }

    @Override
    public int addUser(UserInfo userInfo) {
        return userMapper.addUser(userInfo);
    }
}
