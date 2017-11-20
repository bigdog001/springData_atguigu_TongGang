package com.atguigu.springdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author CYH
 * @date 2017/11/20 0020
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;


    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void updatePersonEmail(Integer id, String email) {
        personRepository.updatePersonEmail(id, email);
    }


}
