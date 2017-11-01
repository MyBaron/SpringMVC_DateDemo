package com.Controller;

import com.Entity.TestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/11/1.
 */

@Controller
@RequestMapping("/Test")
public class testController {


    @RequestMapping("/form")
    @ResponseBody
    private String form(TestEntity testEntity){
        System.out.println("测试成功");
        System.out.println(testEntity);
        return "ok";
    }
}
