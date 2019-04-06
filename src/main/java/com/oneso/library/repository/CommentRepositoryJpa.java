package com.oneso.library.repository;

import com.oneso.library.domain.Comment;
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
public class CommentRepositoryJpa implements CommentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(Comment comment) {
        entityManager.persist(comment);
    }

    @Override
    public Comment findById(long id) {
        TypedQuery<Comment> query = entityManager.createQuery("select c from Comment c where c.id = :id",
                Comment.class);
        query.setParameter("id", id);

        return query.getSingleResult();
    }

    @Override
    public List<Comment> findByBookId(long book_id) {
        TypedQuery<Comment> query = entityManager.createQuery("select c from Comment c where c.book.id = :id",
                Comment.class);
        query.setParameter("id", book_id);

        return query.getResultList();
    }

    @Override
    public long count() {
        TypedQuery<Long> query = entityManager.createQuery("select count(c) from Comment c", Long.class);

        return query.getSingleResult();
    }

    @Override
    public long deleteById(long id) {
        Query query = entityManager.createQuery("delete from Comment c where c.id = :id");
        query.setParameter("id", id);

        return query.executeUpdate();
    }
}
