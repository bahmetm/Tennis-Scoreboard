package com.bahmet.tennisscoreboard.dao;


import com.bahmet.tennisscoreboard.exception.DatabaseException;
import com.bahmet.tennisscoreboard.model.Player;
import com.bahmet.tennisscoreboard.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PlayerDAO {
    public Player findByName(String name) {
        String HQLQuery = "From Player where name = :name";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(HQLQuery, Player.class).setParameter("name", name).uniqueResult();
        } catch (Exception e) {
            throw new DatabaseException("Database error");
        }
    }

    public Player save(Player player) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(player);
            transaction.commit();
            return player;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new DatabaseException("Database error");
        }
    }
}
