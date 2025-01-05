package org.lia.lab4_v3.Beans;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.lia.lab4_v3.DBEntity.PointEntity;
import org.lia.lab4_v3.DBEntity.UserEntity;
import org.lia.lab4_v3.Models.Coordinates;
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

    public PointEntity addResult(Coordinates coordinates, UserEntity user) {
        PointEntity point = new PointEntity();
        double x = coordinates.getX();
        double y = coordinates.getY();
        double r = coordinates.getR();
        point.setX(coordinates.getX());
        point.setY(coordinates.getY());
        point.setR(coordinates.getR());
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
