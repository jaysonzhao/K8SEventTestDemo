package com.wdjr.springbootquick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//这个类的所有方法返回的数据直接返回给浏览器（如果是对象转为json数据）
/*@ResponseBody
@Controller*/
@RestController
@RequestMapping("/hello")
public class HelloController {

    //@RequestMapping(path="/hello")
    @GetMapping("/{crId}")
    public  String  hello(@PathVariable("crId") String crId){
        RawCustomResourceExample.CreateCR(crId); 
        return "hello ";
    }
    //REST API
}
