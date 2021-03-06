package com.atguigu.springdata;

import java.util.Date;

import org.junit.Test;

/**
 * Created by CYH on 2017/11/30 0030.
 *
 * Saving an entity can be performed via the CrudRepository.save(…) Method.
 * It will persist or merge the given entity using the underlying JPA EntityManager.
 * If the entity has not been persisted yet Spring Data JPA will save the entity
 * via a call to the entityManager.persist(…) method,
 * otherwise the entityManager.merge(…) method will be called.
 *
 * https://docs.spring.io/spring-data/jpa/docs/2.0.2.RELEASE/reference/html/#jpa.entity-persistence.saving-entites
 */
public class PersonRepositoryUpdateTest extends BasePersonRepositoryTest {


    /**
     * 用Save方法来更新数据=====是可以的
     * 注意：需要提供Id（不提供的话就变成了普通的插入操作）
     * 底层实现时，先会根据Id做 Select 操作，然后执行 Update 操作
     */
    @Test
    public void testUpdateByUsingSaveMethodWithExistId() {
        Person person = new Person();
        //一定要有Id
        person.setId(1);
        person.setEmail("cyhbyw_test@qq.com");
        person.setLastName("cyhbyw_TEST");
        person.setBirth(new Date());
        personRepository.save(person);
    }

    /**
     * 若对象Id不存在，也可以插入
     * 底层实现时，先会根据Id做 Select 操作，然后执行 Insert 操作（因为Id不存在，所以就相当于直接插入）
     */
    @Test
    public void testUpdateByUsingSaveMethodWithNonexistenceId() {
        Person person = new Person();
        //一定要有Id
        person.setId(111111);
        person.setEmail("cyhbyw_nonexistence@qq.com");
        person.setLastName("nonexistence");
        person.setBirth(new Date());
        personRepository.save(person);
    }


}
