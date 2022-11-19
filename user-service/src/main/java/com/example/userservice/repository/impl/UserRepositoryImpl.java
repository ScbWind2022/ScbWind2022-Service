package com.example.userservice.repository.impl;

import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

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

    @Override
    @Transactional(readOnly = true)
    public List<User> getNotAcceptedUser() {
        try {
            final List<User> users = em.createQuery("SELECT u FROM User u WHERE u.accepted = FALSE", User.class)
                    .getResultList();
            return users;
        } catch (NoResultException n){
            return null;
        }
    }

    @Override
    @Transactional
    public void acceptedUserById(Long id) {
        em.createQuery("UPDATE User u SET u.accepted = TRUE WHERE u.id = ?1")
                .setParameter(1,id)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void bannedUserById(Long id) {
        em.createQuery("UPDATE User u SET u.banned = TRUE WHERE u.id = ?1")
                .setParameter(1,id)
                .executeUpdate();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getBannedUser() {
        try{
            final List<User> users = em.createQuery("SELECT u FROM User u WHERE u.banned = TRUE",User.class)
                    .getResultList();
            return users;
        } catch (NoResultException n){
            return null;
        }
    }

    @Override
    @Transactional
    public void removeBannedUserById(Long id) {
        em.createQuery("UPDATE User u SET u.banned = FALSE WHERE u.id = ?1")
                .setParameter(1,id)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void openSession(String email) {
        em.createQuery("UPDATE User u SET u.inSession = TRUE WHERE LOWER(u.email) = ?1")
                .setParameter(1,email.toLowerCase())
                .executeUpdate();
    }

    @Override
    @Transactional
    public void closeSession(String email) {
        em.createQuery("UPDATE User u SET u.inSession = FALSE WHERE LOWER(u.email) = ?1")
                .setParameter(1,email.toLowerCase())
                .executeUpdate();
    }
}
