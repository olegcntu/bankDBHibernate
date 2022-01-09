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

    public String addCredit(String idTextField, String amount,
                            String currency, String interest,
                            String time, String paid) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        CreditEntity creditEntity;
        try {


            creditEntity = new CreditEntity(
                    Integer.parseInt(idTextField),
                    Integer.parseInt(amount),
                    currency,
                    Integer.parseInt(interest),
                    Integer.parseInt(time),
                    Integer.parseInt(paid)
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

    public String changeCredit(String idTextField, String amount,
                               String currency, String interest,
                               String time, String paid) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();

        Session s = em.unwrap(Session.class);
        Transaction tx1 = s.beginTransaction();
        try {

            CreditEntity creditEntity = em.find(CreditEntity.class, Integer.parseInt(idTextField));

            creditEntity.setAmount(Integer.parseInt(amount));
            creditEntity.setCurrency(currency);
            creditEntity.setInterest(Integer.parseInt(interest));
            creditEntity.setEstimatedTime(Integer.parseInt(time));
            creditEntity.setAmountToBePaid(Integer.parseInt(paid));

            s.update(creditEntity);
            tx1.commit();
        } catch (Exception e) {
            return e.toString();
        }
        em.close();
        return "Credit refresh";
    }

}
