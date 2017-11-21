package com.atguigu.springdata;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by CYH on 2017/11/21 0021.
 */
public class PersonRepositoryImpl implements PersonDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void test() {
        Person person = entityManager.find(Person.class, 1);
        System.out.println("====> " + person);
    }

}
