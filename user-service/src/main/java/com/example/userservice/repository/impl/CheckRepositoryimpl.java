package com.example.userservice.repository.impl;

import com.example.userservice.model.Check;
import com.example.userservice.repository.CheckRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CheckRepositoryimpl implements CheckRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    @Transactional
    public void save(Check check) {
        em.persist(check);
    }

    @Override
    @Transactional
    public void delete(Check check) {
        em.remove(em.contains(check) ? check : em.merge(check));
    }

    @Override
    @Transactional
    public void updateUserAndCheck(Long user_id, Long check_id) {
        em.createNativeQuery("INSERT INTO user_and_check (user_id,check_id) VALUES (?1,?2)")
                .setParameter(1,user_id)
                .setParameter(2,check_id)
                .executeUpdate();
    }
}
