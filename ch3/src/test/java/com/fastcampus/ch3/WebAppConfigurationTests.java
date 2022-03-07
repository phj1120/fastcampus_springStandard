package com.fastcampus.ch3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
// servlet-context 를 기반으로 webApplicationContext 생성함
// 일단 아래의 테스트를 진행하려면 @WebApplicationConfiguration 어노태이션 추가
// servlet-context 에 resource 태그를 지우면 어노테이션을 안 붙여도
// ApplicationContext 는 사용 가능은 함
// 하지만 WebApplicationContext 가 없어 MockMVC 는 사용 불가능 함
// 결국 MockMVC 를 쓰기 위해서는 @WebAppConfiguration 어노테이션 필요
@WebAppConfiguration
//servlet-context 을 테스트에서 사용하려면 javax.servlet version 3.1.0 사용
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/**/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class WebAppConfigurationTests {

    @Autowired
    private ApplicationContext ac;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception{
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void contextLoads() throws Exception {
        if (ac != null) {
            String[] beans = ac.getBeanDefinitionNames();

            for (String bean : beans) {
                System.out.println("bean : " + bean);
            }
        }
    }

    @Test
    public void webAppContextLoads() throws Exception {
        if (wac != null) {
            String[] beans = wac.getBeanDefinitionNames();

            for (String bean : beans) {
                System.out.println("bean : " + bean);
            }
        }
    }
}


