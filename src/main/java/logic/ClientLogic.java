package logic;

import dbEntities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.Transaction;
import userInterface.TableModel;

import java.sql.Date;
import java.util.Comparator;
import java.util.List;

public class ClientLogic {
    public TableModel clientToTable() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<ArrGenerate> list = em.createQuery("FROM ClientEntity ").getResultList();
        list.sort(Comparator.comparingInt(ArrGenerate::getId));


        em.close();
        String[] columnArr = {"id", "name", "address", "number", "person"};

        return new TableModel(5, list, columnArr);
    }

    public String addClient(String idTextField, String textField2,
                              String textField3, String textField4,
                              Boolean person) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        ClientEntity clientEntity;
        try {


            clientEntity = new ClientEntity(
                    Integer.parseInt(idTextField),
                    textField2,
                    textField3,
                    textField4,
                    person
            );

        } catch (Exception ex) {
            return ex.toString();
        }
        try {
            em.persist(clientEntity);
            em.getTransaction().commit();

        } catch (Exception ex) {
            return ex.toString();
        }
        em.close();
        return "Client added";
    }
    public String dellClient(String idTextField) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            int id = Integer.parseInt(idTextField);
            ClientEntity clientEntity = em.find(ClientEntity.class, id);
            em.remove(clientEntity);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            return e.toString();
        }
        return "Client delete";
    }

    public String changeClient(String idTextField, String textField2,
                             String textField3, String textField4,
                             Boolean person) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();

        Session s = em.unwrap(Session.class);
        Transaction tx1 = s.beginTransaction();
        try {

            ClientEntity clientEntity = em.find(ClientEntity.class, Integer.parseInt(idTextField));

            clientEntity.setFullName(textField2);
            clientEntity.setResidenceAddress(textField3);
            clientEntity.setPhoneNumber(textField4);
            clientEntity.setYouredicPerson(person);

            s.update(clientEntity);
            tx1.commit();
        } catch (Exception e) {
            return e.toString();
        }
        em.close();
        return "Client refresh";
    }


}
