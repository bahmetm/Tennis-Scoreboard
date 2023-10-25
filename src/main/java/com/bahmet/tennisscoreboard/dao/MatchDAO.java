package com.bahmet.tennisscoreboard.dao;

import com.bahmet.tennisscoreboard.exception.DatabaseException;
import com.bahmet.tennisscoreboard.model.Match;
import com.bahmet.tennisscoreboard.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class MatchDAO {
    public Long findMatchesQuantity() {
        String HQLQuery = "SELECT COUNT(*) FROM Match";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(HQLQuery, Long.class).uniqueResult();
        } catch (Exception e) {
            throw new DatabaseException("Database error");
        }
    }

    public Long findMatchesQuantityByPlayerName(String playerName) {
        String HQLQuery = "SELECT COUNT(*) FROM Match WHERE playerOne.name = :playerName OR playerTwo.name = :playerName";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(HQLQuery, Long.class).setParameter("playerName", playerName).uniqueResult();
        } catch (Exception e) {
            throw new DatabaseException("Database error");
        }
    }

    public List<Match> findAll() {
        String HQLQuery = "FROM Match";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(HQLQuery, Match.class).list();
        } catch (Exception e) {
            throw new DatabaseException("Database error");
        }
    }

//    public Page<Match> findAllPaginated(int pageNumber, int pageSize)

//    public List<Match> findByPlayerName(String name)

//    public Page<Match> findByPlayerNamePaginated(int pageNumber, int pageSize, String name)

    public Match save(Match match) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(match);
            session.getTransaction().commit();
            return match;
        } catch (Exception e) {
            throw new DatabaseException("Database error");
        }
    }
}
