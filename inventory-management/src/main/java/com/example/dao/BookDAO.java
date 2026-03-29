package com.example.dao;

import com.example.model.Book;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class BookDAO extends GenericDAOImpl<Book, Long> {

    @Override
    public List<Book> fetchAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "select b from Book b left join fetch b.category", Book.class
            ).list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public Optional<Book> findByIsbn(String isbn) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Book> q = session.createQuery(
                    "from Book where isbn = :isbn", Book.class);
            q.setParameter("isbn", isbn);
            return Optional.ofNullable(q.uniqueResult());
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<Book> searchBooks(String keyword) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Book> q = session.createQuery(
                    "select b from Book b left join fetch b.category " +
                            "where lower(b.title) like lower(:kw) " +
                            "or lower(b.genre) like lower(:kw) " +
                            "or lower(b.isbn) like lower(:kw)", Book.class);
            q.setParameter("kw", "%" + keyword + "%");
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}