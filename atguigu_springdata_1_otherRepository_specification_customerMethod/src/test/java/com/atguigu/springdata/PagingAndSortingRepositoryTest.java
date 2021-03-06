package com.atguigu.springdata;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

/**
 * Created by CYH on 2017/11/21 0021.
 */
public class PagingAndSortingRepositoryTest extends BasePersonRepositoryTest {

    @Test
    public void testPagingAndSortingRepository() {
        // pageNo 从 0 开始.
        int pageNo = 3 - 1;
        int pageSize = 5;
        // Pageable 接口通常使用的其 PageRequest 实现类. 其中封装了需要分页的信息排序相关的.
        // Sort 封装了排序的信息
        // Order 是具体针对于某一个属性进行升序还是降序.
        Order order1 = new Order(Direction.ASC, "id");
        Order order2 = new Order(Direction.DESC, "email");
        Sort sort = new Sort(order1, order2);

        PageRequest pageable = new PageRequest(pageNo, pageSize, sort);
        Page<Person> page = personRepository.findAll(pageable);

        System.out.println("总记录数: " + page.getTotalElements());
        System.out.println("当前第几页: " + (page.getNumber() + 1));
        System.out.println("总页数: " + page.getTotalPages());
        System.out.println("当前页面的 List: " + page.getContent());
        System.out.println("当前页面的记录数: " + page.getNumberOfElements());
    }
}
