package logic;

import dbEntities.ArrGenerate;
import dbEntities.BankEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.Transaction;
import userInterface.TableModel;

import java.util.Comparator;
import java.util.List;

public class BankLogic {

    public String dellBank(String idTextField) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            int id = Integer.parseInt(idTextField);
            BankEntity bankEntity = em.find(BankEntity.class, id);
            em.remove(bankEntity);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            return e.toString();
        }
        return "Bank delete";
    }

    public String changeBank(String idTextField, String town,
                             String number, String address,
                             String start, String end) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();

        Session s = em.unwrap(Session.class);
        Transaction tx1 = s.beginTransaction();
        try {

            BankEntity bankEntity = em.find(BankEntity.class, Integer.parseInt(idTextField));

            bankEntity.setTown(town);
            bankEntity.setNumber(number);
            bankEntity.setAddress(address);
            bankEntity.setWorkStart(Double.parseDouble(start));
            bankEntity.setWorkEnd(Double.parseDouble(end));

            s.update(bankEntity);
            tx1.commit();
        } catch (Exception e) {
            return e.toString();
        }
        em.close();
        return "Bank refresh";

    }


    public String addBank(String idTextField, String town,
                          String number, String address,
                          String start, String end) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        BankEntity bankEntity;
        try {
            bankEntity = new BankEntity(
                    Integer.parseInt(idTextField),
                    town,
                    number,
                    address,
                    Double.parseDouble(start),
                    Double.parseDouble(end));

        } catch (Exception ex) {
            return ex.toString();
        }
        try {
            em.persist(bankEntity);
            em.getTransaction().commit();

        } catch (Exception ex) {
            return ex.toString();
        }
        em.close();
        return "Bank added";
    }

    public TableModel bankToTable() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<ArrGenerate> list = em.createQuery("FROM BankEntity").getResultList();
        list.sort(Comparator.comparingInt(ArrGenerate::getId));


        em.close();
        String[] columnArr = {"id", "town", "number", "address", "work start", "work end"};

        return new TableModel(6, list, columnArr);
    }
}
