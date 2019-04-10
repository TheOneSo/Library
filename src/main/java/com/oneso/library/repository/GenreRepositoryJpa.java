package com.oneso.library.repository;

import com.oneso.library.domain.Genre;
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
public class GenreRepositoryJpa implements GenreRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(Genre genre) {
        entityManager.persist(genre);
    }

    @Override
    public Genre findById(long id) {
        TypedQuery<Genre> query = entityManager.createQuery("select g from Genre g where g.id = :id",
                Genre.class);
        query.setParameter("id", id);

        return query.getSingleResult();
    }

    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> query = entityManager.createQuery("select g from Genre g", Genre.class);

        return query.getResultList();
    }

    @Override
    public long count() {
        TypedQuery<Long> query = entityManager.createQuery("select count(g) from Genre g", Long.class);

        return query.getSingleResult();
    }

    @Override
    public long deleteById(long id) {
        Query query = entityManager.createQuery("delete from Genre g where g.id = :id");
        query.setParameter("id", id);

        return query.executeUpdate();
    }
}
