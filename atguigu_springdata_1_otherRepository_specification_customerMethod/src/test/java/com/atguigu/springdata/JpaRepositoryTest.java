package com.atguigu.springdata;

import java.util.Date;

import org.junit.Test;

/**
 * Created by CYH on 2017/11/21 0021.
 */
public class JpaRepositoryTest extends BasePersonRepositoryTest {

    @Test
    public void testJpaRepositoryWithExistingId() {
        Person person = new Person();
        person.setBirth(new Date());
        person.setEmail("asdf@atguigu.com");
        person.setLastName("ASDF");
        // 有Id且已存在，则会覆盖
        person.setId(26);

        Person person2 = personRepository.saveAndFlush(person);

        System.out.println(person == person2);
    }

    @Test
    public void testJpaRepositoryWithNonExistingId() {
        Person person = new Person();
        person.setBirth(new Date());
        person.setEmail("asdf@atguigu.com");
        person.setLastName("ASDF");
        // 有Id但不存在，则会添加
        person.setId(Integer.MAX_VALUE);

        Person person2 = personRepository.saveAndFlush(person);

        System.out.println(person == person2);
    }

    @Test
    public void testJpaRepositoryWithoutId() {
        Person person = new Person();
        person.setBirth(new Date());
        person.setEmail("qwer@atguigu.com");
        person.setLastName("QWER");
        // 无Id则是添加

        Person person2 = personRepository.saveAndFlush(person);

        System.out.println(person == person2);
    }
}
