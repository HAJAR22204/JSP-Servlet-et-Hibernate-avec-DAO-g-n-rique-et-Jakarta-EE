package com.example.dao;

import com.example.model.Author;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class AuthorDAO extends GenericDAOImpl<Author, Long> {

    public Optional<Author> findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Author> q = session.createQuery(
                    "from Author where email = :email", Author.class);
            q.setParameter("email", email);
            return Optional.ofNullable(q.uniqueResult());
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<Author> findByNationality(String nationality) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Author> q = session.createQuery(
                    "from Author where lower(nationality) like lower(:nat)", Author.class);
            q.setParameter("nat", "%" + nationality + "%");
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}