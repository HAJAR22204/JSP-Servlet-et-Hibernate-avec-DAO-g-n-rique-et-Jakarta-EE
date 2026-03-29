package com.example.dao;

import com.example.model.Category;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Optional;

public class CategoryDAO extends GenericDAOImpl<Category, Long> {

    public Optional<Category> findByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Category> q = session.createQuery(
                    "from Category where lower(name) = lower(:name)", Category.class);
            q.setParameter("name", name);
            return Optional.ofNullable(q.uniqueResult());
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
