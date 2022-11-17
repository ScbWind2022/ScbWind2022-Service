package com.example.userservice.repository.impl;

import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    @Transactional
    public void save(User user) {
        em.persist(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        em.remove(em.contains(user) ? user : em.merge(user));
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserAndRoleByEmail(String email) {
        try {
            final User user = em.createQuery("SELECT u FROM User u " +
                            "LEFT JOIN FETCH u.roles WHERE LOWER(u.email) = ?1 ", User.class)
                    .setParameter(1,email.toLowerCase())
                    .getSingleResult();
            return user;
        } catch (NoResultException n){
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        try{
            final User user = em.createQuery("SELECT u FROM User u WHERE u.email = ?1",User.class)
                    .setParameter(1,email)
                    .getSingleResult();
            return user;
        } catch (NoResultException n){
            return null;
        }
    }
}
