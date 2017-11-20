package com.atguigu.springdata;

import java.util.Date;

import javax.sql.DataSource;

import org.junit.Test;

/**
 * Created by CYH on 2017/11/20 0020.
 */
public class PersonRepositorySaveDataTest extends BasePersonRepositoryTest {

    @Test
    public void testDataSource() throws Exception {
        DataSource dataSource = context.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void testSave() {
        for (int x = 0; x < 26; x++) {
            char ch = (char) ('A' + x);
            Person person = new Person();
            person.setEmail(ch + "@qq.com");
            person.setLastName(ch + "");
            person.setBirth(new Date());
            personRepository.save(person);
        }
    }


}
