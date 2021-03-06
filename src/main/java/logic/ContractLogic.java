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

public class ContractLogic {

    public TableModel contractToTable() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<ArrGenerate> list = em.createQuery("FROM ContractEntity ").getResultList();
        list.sort(Comparator.comparingInt(ArrGenerate::getId));


        em.close();
        String[] columnArr = {"id", "accountant id", "client id", "credit id", "date", "pledge"};

        return new TableModel(6, list, columnArr);
    }

        public String addContract(String idTextField, String accountant,
                              String client, String credit,
                              String textField5, String textField6) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        ContractEntity contractEntity;
        try {

            AccountantEntity accountantEntity = em.find(AccountantEntity.class, accountant);
            ClientEntity clientEntity = em.find(ClientEntity.class, client);
            CreditEntity creditEntity = em.find(CreditEntity.class, credit);

            contractEntity = new ContractEntity(
                    Integer.parseInt(idTextField),
                    accountantEntity,
                    clientEntity,
                    creditEntity,
                    Date.valueOf(textField5),
                    textField6);

        } catch (Exception ex) {
            return ex.toString();
        }
        try {
            em.persist(contractEntity);
            em.getTransaction().commit();

        } catch (Exception ex) {
            return ex.toString();
        }
        em.close();
        return "Contract added";
    }

    public String dellContract(String idTextField) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            int id = Integer.parseInt(idTextField);
            ContractEntity contractEntity = em.find(ContractEntity.class, id);
            em.remove(contractEntity);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            return e.toString();
        }
        return "Contract delete";
    }

    public String changeContract(String idTextField, String accountant,
                                 String client, String credit,
                                 String data, String pledge) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();

        Session s = em.unwrap(Session.class);
        Transaction tx1 = s.beginTransaction();
        try {

            ContractEntity contractEntity = em.find(ContractEntity.class, Integer.parseInt(idTextField));
            AccountantEntity accountantEntity = em.find(AccountantEntity.class, Integer.parseInt(accountant));
            ClientEntity clientEntity = em.find(ClientEntity.class, Integer.parseInt(client));
            CreditEntity creditEntity = em.find(CreditEntity.class, Integer.parseInt(credit));

            contractEntity.setAccountantByAccountantId(accountantEntity);
            contractEntity.setClientByClientId(clientEntity);
            contractEntity.setCreditByCreditId(creditEntity);
            contractEntity.setDateOfConckusion(Date.valueOf(data));
            contractEntity.setPledge(pledge);

            s.update(contractEntity);
            tx1.commit();
        } catch (Exception e) {
            return e.toString();
        }
        em.close();
        return "Contract refresh";
    }


}
