package com.test;

import com.sixmai.service.LoginService;
import com.sixmai.service.impl.LoginServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.staticmock.MockStaticEntityMethods;

import java.util.HashMap;

import static org.junit.Assert.*;


/**
 * Created by 未来人来xw on 2019/7/17.
 */

public class LoginServiceImplTest{

    private LoginService loginService;

    @Before
    public void setUp() throws Exception {
        System.out.println("before!");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("after!");
    }

    @Test
    public void login() throws Exception {
        String username="prov";
        String password = "prov";
        int role = 1;
        HashMap<String, Object> mm = new HashMap<String, Object>();
        System.out.println(loginService.login(username,password,role).get("status"));
        System.out.println(loginService.login(username,password,role).get("status"));

    }

}