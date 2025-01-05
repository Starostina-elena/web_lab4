package org.lia.lab4_v3.Beans;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.lia.lab4_v3.DBEntity.PointEntity;

import java.util.ArrayList;

@Stateless
public class ResultTableBean {

    @PersistenceContext(unitName = "persistence-unit")
    private EntityManager entityManager;

    public ArrayList<PointEntity> check() {
        Query namedQuery = entityManager.createQuery("SELECT p FROM PointEntity p ORDER BY p.date DESC LIMIT 10");
        ArrayList<PointEntity> res = (ArrayList<PointEntity>) namedQuery.getResultList();
        return res;
    }

}
