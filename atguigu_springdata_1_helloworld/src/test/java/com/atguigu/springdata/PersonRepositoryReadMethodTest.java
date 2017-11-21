package com.atguigu.springdata;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;

/**
 * Created by CYH on 2017/11/20 0020.
 */
public class PersonRepositoryReadMethodTest extends BasePersonRepositoryTest {


    @Test
    public void getByLastName() throws Exception {
        Person person = personRepository.getByLastName("cc");
        System.out.println(person);
    }

    @Test
    public void testGetByLastNameStartingWithAndIdLessThan() {
        List<Person> personList = personRepository.getByLastNameStartingWithAndIdLessThan("B", 5);
        System.out.println(personList);
    }


    @Test
    public void testGetByEmailInAndBirthLessThan() {
        List<Person> personList =
                personRepository.getByEmailInAndBirthLessThan(Arrays.asList("C@qq.com", "F@qq.com"), new Date());
        System.out.println(personList);
    }

    @Test
    public void testGetMaxIdPerson() {
        Person maxIdPerson = personRepository.getMaxIdPerson();
        System.out.println(maxIdPerson);
    }

    @Test
    public void testQueryAnnotationParams1() {
        List<Person> personList = personRepository.testQueryAnnotationParams1("X", "X@qq.com");
        System.out.println(personList);
    }

    @Test
    public void testQueryAnnotationParams2() {
        List<Person> personList = personRepository.testQueryAnnotationParams2("Z@qq.com", "Z");
        System.out.println(personList);
    }

    @Test
    public void testQueryAnnotationLikeParam() {
        List<Person> personList = personRepository.testQueryAnnotationLikeParam("D", "F@qq");
        System.out.println(personList);
    }

    @Test
    public void testQueryAnnotationLikeParam2() {
        List<Person> personList = personRepository.testQueryAnnotationLikeParam2("D@qq", "F");
        System.out.println(personList);
    }

    @Test
    public void getTotalCount() {
        BigInteger totalCount = personRepository.getTotalCount();
        System.out.println(totalCount);
    }


}
