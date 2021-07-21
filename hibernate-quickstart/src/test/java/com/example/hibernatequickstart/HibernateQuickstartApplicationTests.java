package com.example.hibernatequickstart;

import com.example.hibernatequickstart.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@SpringBootTest
public class HibernateQuickstartApplicationTests {

    @Autowired
    private EntityManagerFactory entityManagerFactory;
    private Session session;
    private Transaction transaction;

    @BeforeEach
    public void before() {
        session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        transaction = session.beginTransaction();
    }

    @AfterEach
    public void after() {
        transaction.commit();
        session.close();
        session.getSessionFactory().close();
    }

    @Test
    public void test1() {
        /**
         * 查询所有
         */
        String hql = "from User";
        Query query = session.createQuery(hql);
        List<User> users = query.list();
        users.forEach(System.out::println);
    }

    @Test
    public void test2() {
        /**
         * where
         * 1. 可以配合（=, , <, >, <=, >=, between, not between, in ,not in, is, like）使用
         */
        String hql = "from User where id = 3";
        Query query = session.createQuery(hql);
        List<User> users = query.list();
        users.forEach(System.out::println);
    }

    @Test
    public void test3() {
        /**
         * 属性查询
         * 1. 子查询select后的字段
         * 2. 不能自动封装成相应的对象，因为查询出来的String类型，所以用Object接收
         */
        String hql = "select user.name from User user";
        Query query = session.createQuery(hql);
        List list = query.list();
        list.forEach(System.out::println);
    }

    @Test
    public void test4() {
        /**
         * 多属性查询
         * 1. 多属性查询，返回的数据为Object[]
         */
        String hql = "select user.name, user.sex from User user";
        Query query = session.createQuery(hql);
        List list = query.list();
        list.forEach(results -> {
            Object[] objs = (Object[]) results;
            System.out.println(objs[0] + "," + objs[1]);
        });
    }

    @Test
    public void test5() {
        /**
         * 多属性查询(数据封装)
         * 1. User对象仅用于数据的封装，并且子对指定的数据进行封装，其他数据均为NULL
         * 2. 必须提供需要构造属性的构造方法，并且顺序一致
         */
        String hql = "select new User(user.name, user.age) from User user";
        Query query = session.createQuery(hql);
        List list = query.list();
        list.forEach(results -> {
            User user = (User) results;
            System.out.println(user);
        });
    }

    @Test
    public void test6() {
        /**
         * 按条件进行更新
         */
        String hql = "update User set age = 100 where id = 1";
        int i = session.createQuery(hql).executeUpdate();
        System.out.println(i);
    }

    @Test
    public void test7() {
        /**
         * 批量更新
         * 优点：便捷性和性能的提升
         */
        String hql = "update User set age = 100";
        int i = session.createQuery(hql).executeUpdate();
        System.out.println(i);
    }

    @Test
    public void test8() {
        /**
         * 按条件删除
         * ps：在HQL delete/update子句的时候，必须特别注意它们对缓存策略的影响，极有可能导致缓存同步上的障碍
         */
        String hql = "delete User where name = '小龙女'";
        int i = session.createQuery(hql).executeUpdate();
        System.out.println(i);
    }

    @Test
    public void test9() {
        /**
         * 排序Order by
         * 1. 支持多字段排序
         * 2. 多字段排序：第一个字段如果是相同的值，进行第二字段排序
         * 3. asc从小到达，desc从大到小
         */
        String hql = "from User user order by user.age asc, user.id desc";
        Query query = session.createQuery(hql);
        List<User> users = query.list();
        users.forEach(System.out::println);
    }

    @Test
    public void test10() {
        /**
         * 分组Group by
         * 1. 通过having可对分组后的数据进行筛选
         */
        String hql = "select count(user), user.age from User user group by user.age having count(user) > 1";
        Query query = session.createQuery(hql);
        List list = query.list();
        list.forEach(results -> {
            Object[] objs = (Object[]) results;
            System.out.println(objs[0] + "============" + objs[1]);
        });
    }

    @Test
    public void test11() {
        /**
         * 参数绑定(引用占位符)
         * ps：解决SQL注入问题
         */
        String hql = "from User user where user.age = :age and user.name = :name";
        Query query = session.createQuery(hql);
        List<User> users = query.setParameter("age", 39).setParameter("name", "胡歌").list();
        users.forEach(System.out::println);
    }

    @Test
    public void test12() {
        /**
         * 参数绑定(引用占位符)
         * ps：Object[]的类型必须和表中对应字段的数据类型相同
         * setParameter和setParameterList区别
         */
        String hql = "from User user where user.id in (:users)";
        Object[] o = new Long[] {1l, 3l, 7l};
        Query query = session.createQuery(hql);
        List<User> users = query.setParameterList("users", o).list();
        users.forEach(System.out::println);
    }

    @Test
    public void test13() {
        /**
         * 引用查询
         * 1. 在xml中通过query标签定义一段HQL来使用
         * 2. 因为HQL在xml标签中可能会因为”<“造成冲突，所以使用CDATA[]包裹
         */
        Query query = session.getNamedQuery("queryTest");
        List<User> users = query.list();
        users.forEach(System.out::println);
    }

    @Test
    public void test14() {
        /**
         * 关联查询(迫切左外连接)
         */
        Query query = session.getNamedQuery("queryTest");
        List<User> users = query.list();
        users.forEach(System.out::println);
    }



}
