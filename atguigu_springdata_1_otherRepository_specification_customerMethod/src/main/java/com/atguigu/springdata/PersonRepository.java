package com.atguigu.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * @author CYH
 */
public interface PersonRepository extends JpaRepository<Person, Integer>, JpaSpecificationExecutor<Person>, PersonDao {


}
