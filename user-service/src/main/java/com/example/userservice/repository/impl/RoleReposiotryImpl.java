package com.example.userservice.repository.impl;

import com.example.userservice.model.Role;
import com.example.userservice.repository.RoleRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class RoleReposiotryImpl implements RoleRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    @Transactional
    public void save(Role role) {
        em.persist(role);
    }

    @Override
    @Transactional
    public void delete(Role role) {
        em.remove(em.contains(role) ? role : em.merge(role));
    }

    @Override
    @Transactional(readOnly = true)
    public Role getUserRole() {
        try {
            final Role role = em.createQuery("SELECT r FROM Role r WHERE LOWER(r.name) = ?1", Role.class)
                    .setParameter(1,"role_user")
                    .getSingleResult();
            return role;
        } catch (NoResultException n){
            return null;
        }
    }
}
