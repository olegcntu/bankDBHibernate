package logic;

import dbEntities.ArrGenerate;
import dbEntities.ClientEntity;
import dbEntities.CreditEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.Transaction;
import userInterface.TableModel;

import java.util.Comparator;
import java.util.List;

public class CreditLogic {
    public TableModel creditToTable() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<ArrGenerate> list = em.createQuery("FROM CreditEntity ").getResultList();
        list.sort(Comparator.comparingInt(ArrGenerate::getId));


        em.close();
        String[] columnArr = {"id", "amount", "currency", "interest", "time", "amount to be paid"};

        return new TableModel(6, list, columnArr);
    }

    public String addCredit(String idTextField, String textField2,
                            String textField3, String textField4,
                            String textField5, String textField6) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        CreditEntity creditEntity;
        try {


            creditEntity = new CreditEntity(
                    Integer.parseInt(idTextField),
                    Integer.parseInt(textField2),
                    textField3,
                    Integer.parseInt(textField4),
                    Integer.parseInt(textField5),
                    Integer.parseInt(textField6)
            );

        } catch (Exception ex) {
            return ex.toString();
        }
        try {
            em.persist(creditEntity);
            em.getTransaction().commit();

        } catch (Exception ex) {
            return ex.toString();
        }
        em.close();
        return "Credit added";
    }

    public String dellCredit(String idTextField) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            int id = Integer.parseInt(idTextField);
            CreditEntity creditEntity = em.find(CreditEntity.class, id);
            em.remove(creditEntity);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            return e.toString();
        }
        return "Credit delete";
    }

    public String changeCredit(String idTextField, String textField2,
                               String textField3, String textField4,
                               String textField5, String textField6) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();

        Session s = em.unwrap(Session.class);
        Transaction tx1 = s.beginTransaction();
        try {

            CreditEntity creditEntity = em.find(CreditEntity.class, Integer.parseInt(idTextField));

            creditEntity.setAmount(Integer.parseInt(textField2));
            creditEntity.setCurrency(textField3);
            creditEntity.setInterest(Integer.parseInt(textField4));
            creditEntity.setEstimatedTime(Integer.parseInt(textField5));
            creditEntity.setAmountToBePaid(Integer.parseInt(textField6));

            s.update(creditEntity);
            tx1.commit();
        } catch (Exception e) {
            return e.toString();
        }
        em.close();
        return "Credit refresh";
    }

}
