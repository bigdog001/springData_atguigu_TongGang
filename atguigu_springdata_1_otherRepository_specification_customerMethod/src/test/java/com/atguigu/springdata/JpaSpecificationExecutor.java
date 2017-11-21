package com.atguigu.springdata;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by CYH on 2017/11/21 0021.
 */
public class JpaSpecificationExecutor extends BasePersonRepositoryTest {

    /**
     * 目标: 实现带查询条件的分页. id > 5 的条件
     *
     * 调用 JpaSpecificationExecutor 的 Page<T> findAll(Specification<T> spec, Pageable pageable);
     * Specification: 封装了 JPA Criteria 查询的查询条件 Pageable: 封装了请求分页的信息: 例如 pageNo, pageSize, Sort
     */
    @Test
    public void testJpaSpecificationExecutor() {
        int pageNo = 3 - 1;
        int pageSize = 5;
        PageRequest pageable = new PageRequest(pageNo, pageSize);

        // 通常使用 Specification 的匿名内部类
        Specification<Person> specification = new Specification<Person>() {
            /**
             * @param *root: 代表查询的实体类.
             * @param query: 可以从中得到 Root 对象, 即告知 JPA Criteria 要查询哪一个实体类. 还可以添加查询条件, 还可以结合
             *        EntityManager 对象得到最终查询的 TypedQuery 对象.
             * @param *cb: CriteriaBuilder 对象. 用于创建 Criteria 相关对象的工厂. 当然可以从中获取到 Predicate 对象
             * @return: *Predicate 类型, 代表一个查询条件.
             */
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                Path path = root.get("id");
                Predicate predicate = builder.gt(path, 5);
                return predicate;
            }
        };

        Page<Person> page = personRepository.findAll(specification, pageable);

        System.out.println("总记录数: " + page.getTotalElements());
        System.out.println("当前第几页: " + (page.getNumber() + 1));
        System.out.println("总页数: " + page.getTotalPages());
        System.out.println("当前页面的 List: " + page.getContent());
        System.out.println("当前页面的记录数: " + page.getNumberOfElements());
    }


}
