package com.atguigu.springdata;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 1. Repository ��һ���սӿ�. ����һ����ǽӿ� 2. �����Ƕ���Ľӿڼ̳��� Repository, ��ýӿڻᱻ IOC ����ʶ��Ϊһ�� Repository Bean. ���뵽 IOC ������.
 * ���������ڸýӿ��ж�������һ���淶�ķ���. 3. ʵ����, Ҳ����ͨ�� @RepositoryDefinition ע��������̳� Repository �ӿ�
 */
/**
 * �� Repository �ӽӿ�����������
 * 1. �������������. ����Ҫ����һ���Ĺ淶
 * 2. ��ѯ������ find | read | get ��ͷ
 * 3. �漰������ѯʱ�������������������ؼ�������
 * 4. Ҫע����ǣ���������������ĸ��д��
 * 5. ֧�����Եļ�����ѯ. ����ǰ���з�������������, ������ʹ��, ����ʹ�ü�������. 
 * ����Ҫʹ�ü�������, ������֮��ʹ�� _ ��������. 
 */
//@RepositoryDefinition(domainClass = Person.class, idClass = Integer.class)
@Repository
public interface PersonRepsotory extends JpaRepository<Person, Integer>, JpaSpecificationExecutor<Person>, PersonDao {

    /**
     * ���� lastName ����ȡ��Ӧ�� Person
     * @param lastName
     * @return
     */
    Person getByLastName(String lastName);

    /**
     * WHERE lastName LIKE ?% AND id < ?
     * @param lastName
     * @param id
     * @return
     */
    List<Person> getByLastNameStartingWithAndIdLessThan(String lastName, Integer id);

    /**
     * WHERE lastName LIKE %? AND id < ?
     * @param lastName
     * @param id
     * @return
     */
    List<Person> getByLastNameEndingWithAndIdLessThan(String lastName, Integer id);

    /**
     * WHERE email IN (?, ?, ?) OR birth < ?
     * @param emails
     * @param birth
     * @return
     */
    List<Person> getByEmailInAndBirthLessThan(List<String> emails, Date birth);

    /**
     * WHERE a.id > ?
     * @param id
     * @return
     */
    List<Person> getByAddress_IdGreaterThan(Integer id);

    /**
     * ��ѯ id ֵ�����Ǹ� Person
     * ʹ�� @Query ע������Զ��� JPQL �����ʵ�ָ����Ĳ�ѯ
     * @return
     */
    @Query("SELECT p FROM Person p WHERE p.id = (SELECT max(p2.id) FROM Person p2)")
    Person getMaxIdPerson();

    /**
     * Ϊ @Query ע�⴫�ݲ����ķ�ʽ1: ʹ��ռλ��.
      * @param lastName
     * @param email
     * @return
     */
    @Query("SELECT p FROM Person p WHERE p.lastName = ?1 AND p.email = ?2")
    List<Person> testQueryAnnotationParams1(String lastName, String email);

    /**
     * Ϊ @Query ע�⴫�ݲ����ķ�ʽ1: ���������ķ�ʽ.
      * @param email
     * @param lastName
     * @return
     */
    @Query("SELECT p FROM Person p WHERE p.lastName = :lastName AND p.email = :email")
    List<Person> testQueryAnnotationParams2(@Param("email") String email, @Param("lastName") String lastName);

    /**
     * SpringData ������ռλ������� %%.
      * @param lastName
     * @param email
     * @return
     */
    @Query("SELECT p FROM Person p WHERE p.lastName LIKE %?1% OR p.email LIKE %?2%")
    List<Person> testQueryAnnotationLikeParam(String lastName, String email);

    /**
     * SpringData ������ռλ������� %%.
      * @param email
     * @param lastName
     * @return
     */
    @Query("SELECT p FROM Person p WHERE p.lastName LIKE %:lastName% OR p.email LIKE %:email%")
    List<Person> testQueryAnnotationLikeParam2(@Param("email") String email, @Param("lastName") String lastName);

    /**
     * ���� nativeQuery=true ������ʹ��ԭ���� SQL ��ѯ
     * @return
     */
    @Query(value = "SELECT count(id) FROM jpa_persons", nativeQuery = true)
    long getTotalCount();

    /**
     * ����ͨ���Զ���� JPQL ��� UPDATE �� DELETE ����. ע��: JPQL ��֧��ʹ�� INSERT
     * �� @Query ע���б�д JPQL ���, ������ʹ�� @Modifying ��������. ��֪ͨ SpringData, ����һ�� UPDATE �� DELETE ����
     * UPDATE �� DELETE ������Ҫʹ������, ��ʱ��Ҫ���� Service ��. �� Service ��ķ���������������.
     * Ĭ�������, SpringData ��ÿ��������������, ������һ��ֻ������. ���ǲ�������޸Ĳ���!
     * @param id
     * @param email
     */
    @Modifying
    @Query("UPDATE Person p SET p.email = :email WHERE id = :id")
    void updatePersonEmail(@Param("id") Integer id, @Param("email") String email);
}
