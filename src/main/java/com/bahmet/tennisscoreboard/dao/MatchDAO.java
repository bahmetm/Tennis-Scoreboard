package com.bahmet.tennisscoreboard.dao;

import com.bahmet.tennisscoreboard.exception.DatabaseException;
import com.bahmet.tennisscoreboard.model.Match;
import com.bahmet.tennisscoreboard.model.Page;
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

    public Page<Match> findAllPaginated(int pageNumber, int pageSize) {
        String HQLQuery = "FROM Match";
        List<Match> matches;
        Long totalElementsQuantity;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            matches = session.createQuery(HQLQuery, Match.class)
                    .setFirstResult((pageNumber - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .list();
            totalElementsQuantity = findMatchesQuantity();
        } catch (Exception e) {
            throw new DatabaseException("Database error");
        }

        long totalPagesQuantity = (totalElementsQuantity % pageSize == 0) ? totalElementsQuantity / pageSize : totalElementsQuantity / pageSize + 1;

        return new Page<>(matches, pageNumber, pageSize, totalPagesQuantity, totalElementsQuantity);
    }

    public List<Match> findByPlayerName(String name) {
        String HQLQuery = "FROM Match WHERE playerOne.name = :name OR playerTwo.name = :name";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(HQLQuery, Match.class).setParameter("name", name).list();
        } catch (Exception e) {
            throw new DatabaseException("Database error");
        }
    }

    public Page<Match> findByPlayerNamePaginated(int pageNumber, int pageSize, String name) {
        String HQLQuery = "FROM Match WHERE playerOne.name = :name OR playerTwo.name = :name";
        List<Match> matches;
        Long totalElementsQuantity;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            matches = session.createQuery(HQLQuery, Match.class)
                    .setParameter("name", name)
                    .setFirstResult((pageNumber - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .list();
            totalElementsQuantity = findMatchesQuantityByPlayerName(name);
        } catch (Exception e) {
            throw new DatabaseException("Database error");
        }

        long totalPagesQuantity = (totalElementsQuantity % pageSize == 0) ? totalElementsQuantity / pageSize : totalElementsQuantity / pageSize + 1;

        return new Page<>(matches, pageNumber, pageSize, totalPagesQuantity, totalElementsQuantity);
    }

    public Match save(Match match) {
        System.out.println(match);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(match);
            session.getTransaction().commit();
            return match;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException("Database error");
        }
    }
}
