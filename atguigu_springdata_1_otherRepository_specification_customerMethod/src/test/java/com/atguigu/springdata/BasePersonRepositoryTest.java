package com.atguigu.springdata;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by CYH on 2017/11/20 0020.
 */
public class BasePersonRepositoryTest {

    protected ApplicationContext context;
    protected PersonRepository personRepository;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        personRepository = context.getBean(PersonRepository.class);
    }
}
