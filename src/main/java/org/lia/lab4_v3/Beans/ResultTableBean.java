package org.lia.lab4_v3.Beans;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.lia.lab4_v3.DBEntity.PointEntity;
import org.lia.lab4_v3.DBEntity.UserEntity;
import org.lia.lab4_v3.Models.AddPointRequest;
import java.sql.Timestamp;

import java.util.ArrayList;

@Stateless
public class ResultTableBean {

    @PersistenceContext(unitName = "persistence-unit")
    private EntityManager entityManager;

    public ArrayList<PointEntity> getResults(UserEntity user) {
        Query namedQuery = entityManager.createQuery("SELECT p FROM PointEntity p WHERE p.creator_id = :user ORDER BY p.date DESC");
        namedQuery.setParameter("user", user.getId());
        ArrayList<PointEntity> res = (ArrayList<PointEntity>) namedQuery.getResultList();
        return res;
    }

    public PointEntity addResult(AddPointRequest addPointRequest, UserEntity user) {
        PointEntity point = new PointEntity();
        double x = addPointRequest.getX();
        double y = addPointRequest.getY();
        double r = addPointRequest.getR();
        point.setX(addPointRequest.getX());
        point.setY(addPointRequest.getY());
        point.setR(addPointRequest.getR());
        point.setCreator_id(user.getId());
        point.setDate(new Timestamp(System.currentTimeMillis()));
        boolean result = (x >= 0 && y >= 0 && x <= r && y <= r) ||
                (x <= 0 && y >= 0 && x >= -r && y <= x / 2 + r / 2) ||
                (x >= 0 && y <= 0 && x * x + y * y <= r * r);
        point.setResult(result);
        entityManager.persist(point);
        return point;
    }

}
