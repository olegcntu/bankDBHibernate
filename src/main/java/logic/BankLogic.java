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

    public String changeBank(String idTextField, String textField2,
                             String textField3, String textField4,
                             String textField5, String textField6) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();

        Session s=em.unwrap(Session.class);
        Transaction tx1=s.beginTransaction();

        BankEntity bankEntity=em.find(BankEntity.class, 1);

        bankEntity.setTown(textField2);
        bankEntity.setNumber(textField3);
        bankEntity.setAddress(textField4);
        bankEntity.setWorkStart(Double.parseDouble(textField5));
        bankEntity.setWorkEnd(Double.parseDouble(textField6));

        s.update(bankEntity);
        tx1.commit();

        em.close();
        return "Bank refresh";
    }


    public String addBank(String idTextField, String textField2,
                          String textField3, String textField4,
                          String textField5, String textField6) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        BankEntity bankEntity;
        try {
            bankEntity = new BankEntity(
                    Integer.parseInt(idTextField),
                    textField2,
                    textField3,
                    textField4,
                    Double.parseDouble(textField5),
                    Double.parseDouble(textField6));

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
