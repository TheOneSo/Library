package com.oneso.library.repository;

import com.oneso.library.domain.Author;
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
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void insert(Author author) {
        entityManager.persist(author);
    }

    @Override
    public Author findById(long id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    public long count() {
        TypedQuery<Long> query = entityManager.createQuery("select count(a) from Author a", Long.class);
        return query.getSingleResult();
    }

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = entityManager.createQuery("select a from Author a", Author.class);

        return query.getResultList();
    }

    @Override
    public long deleteById(long id) {
        Query query = entityManager.createQuery("delete from Author e where e.id = :id");
        return query.setParameter("id", id).executeUpdate();
    }
}
