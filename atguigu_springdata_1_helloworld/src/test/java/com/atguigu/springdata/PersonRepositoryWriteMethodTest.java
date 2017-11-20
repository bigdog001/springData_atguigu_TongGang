package com.atguigu.springdata;

import org.junit.Test;
import org.springframework.dao.InvalidDataAccessApiUsageException;

/**
 * Created by CYH on 2017/11/20 0020.
 */
public class PersonRepositoryWriteMethodTest extends BasePersonRepositoryTest {

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testUpdatePersonEmail() {
        personRepository.updatePersonEmail(1, "AA@163.com");
    }

    @Test
    public void testUpdatePersonEmail2() {
        personService.updatePersonEmail(1, "AA@163.com");
    }

}
