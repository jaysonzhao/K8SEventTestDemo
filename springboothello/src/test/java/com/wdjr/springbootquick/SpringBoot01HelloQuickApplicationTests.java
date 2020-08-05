package com.wdjr.springbootquick;

import com.wdjr.springbootquick.controller.HelloController;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.IOException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
//@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = SpringBoot01HelloQuickApplication.class)
@WebMvcTest(value = HelloController.class)
//@WebAppConfiguration
public class SpringBoot01HelloQuickApplicationTests {
    
       @Autowired
	private MockMvc mockMvc;


    @Test
    public void contextLoads() throws Exception {

  RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/hello");

   MvcResult result = mockMvc.perform(requestBuilder).andReturn();

   System.out.println(result.getResponse());
   int status = result.getResponse().getStatus();
   assertEquals(200, status);
    }

}
