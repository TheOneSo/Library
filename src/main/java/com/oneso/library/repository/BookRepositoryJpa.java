package com.oneso.library.repository;

import com.oneso.library.domain.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(Book book) {
        entityManager.persist(book);
    }

    @Override
    public Book findById(long id) {
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b where b.id = :id",
                Book.class);
        query.setParameter("id", id);

        return query.getSingleResult();
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b", Book.class);

        return query.getResultList();
    }

    @Override
    public long count() {
        TypedQuery<Long> query = entityManager.createQuery("select count(b) from Book b", Long.class);

        return query.getSingleResult();
    }

    @Override
    public List<Book> findAllBookByAuthorId(long author_id) {
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b where b.author.id = :id",
                Book.class);
        query.setParameter("id", author_id);

        return query.getResultList();
    }

    @Override
    public List<Book> findAllBookByGenreId(long genre_id) {
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b where b.genre.id = :id",
                Book.class);
        query.setParameter("id", genre_id);

        return query.getResultList();
    }

    @Override
    public long deleteById(long id) {
        Query query = entityManager.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);

        return query.executeUpdate();
    }
}
