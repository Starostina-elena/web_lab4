package org.lia.lab4_v3.Beans;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import org.lia.lab4_v3.DBEntity.UserEntity;

import javax.lang.model.type.NullType;

@Stateless
public class AuthBean {

    @PersistenceContext(unitName = "persistence-unit")
    private EntityManager entityManager;

    public UserEntity login(String username, String password) throws NoResultException {
        return (UserEntity)entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.username = :username AND u.password = :password")
                .setParameter("username", username)
                .setParameter("password", password)
                .getSingleResult();
    }

    public UserEntity register(String username, String password) throws PersistenceException {
        entityManager.persist(new UserEntity(username, password));
        return (UserEntity)entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.username = :username AND u.password = :password")
                .setParameter("username", username)
                .setParameter("password", password)
                .getSingleResult();
    }
}
