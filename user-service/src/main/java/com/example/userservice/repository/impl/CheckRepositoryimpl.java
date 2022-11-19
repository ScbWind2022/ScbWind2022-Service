package com.example.userservice.repository.impl;

import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.model.Check;
import com.example.userservice.repository.CheckRepository;
import org.checkerframework.checker.units.qual.C;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

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
        try{
            em.createNativeQuery("INSERT INTO user_and_check (user_id,check_id) VALUES (?1,?2)")
                    .setParameter(1,user_id)
                    .setParameter(2,check_id)
                    .executeUpdate();
        } catch (DataIntegrityViolationException d){
            throw new UserNotFoundException("User id or check id not exist");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Check> getCheksByUserEmail(String email) {
        try {
            final List<Check> checks = em.createQuery("SELECT c FROM Check c " +
                            "LEFT JOIN FETCH c.user AS cu WHERE LOWER(cu.email) = ?1", Check.class)
                    .setParameter(1,email.toLowerCase())
                    .getResultList();
            return checks;
        } catch (NoResultException n){
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Check getCheckByIdAndUserEmail(Long check_id, String email) {
        try{
            final Check check = em.createQuery("SELECT c FROM Check c " +
                            "LEFT JOIN c.user AS cu WHERE c.id = ?1 AND LOWER(cu.email) = ?2", Check.class)
                    .setParameter(1,check_id)
                    .setParameter(2,email.toLowerCase())
                    .getSingleResult();
            return check;
        } catch (NoResultException n){
            return null;
        }
    }

    @Override
    @Transactional
    public boolean updateEnableByIdAndUserEmail(Long check_id, String email,boolean bol) {
        em.createQuery("UPDATE Check c SET c.enabled = ?3 WHERE LOWER(c.user.email) = ?1 AND c.id = ?2")
                .setParameter(1,email.toLowerCase())
                .setParameter(2,check_id)
                .setParameter(3,bol)
                .executeUpdate();
        return true;
    }

    @Override
    @Transactional
    public void changeSumById(Long chek_id, Integer sum) {
        em.createQuery("UPDATE Check c SET c.count = ?1 WHERE c.id = ?2")
                .setParameter(1,sum)
                .setParameter(2,chek_id)
                .executeUpdate();
    }
}
