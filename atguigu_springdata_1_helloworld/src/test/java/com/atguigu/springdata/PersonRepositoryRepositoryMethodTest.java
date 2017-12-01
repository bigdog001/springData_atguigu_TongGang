package com.atguigu.springdata;

import org.junit.Test;

/**
 * Created by CYH on 2017/12/1.
 */
public class PersonRepositoryRepositoryMethodTest extends BasePersonRepositoryTest {

    @Test
    public void testFindOne() {
        Person person = personRepository.findOne(1);
        System.out.println(person);
    }

    @Test
    public void testDelete() {
        personRepository.delete(1);
    }
}
