package logic;

import dbEntities.AccountantEntity;
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

public class AccountantLogic {

    public String addAccountant(String idTextField, String idBankTextField,
                          String name, String address,
                          String position, String phone) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        AccountantEntity accountantEntity;
        try {
            BankEntity bankEntity=em.find(BankEntity.class,   Integer.parseInt(idBankTextField));

            accountantEntity = new AccountantEntity(
                    Integer.parseInt(idTextField), bankEntity,name,
                    address,position, phone);

        } catch (Exception ex) {
            return ex.toString();
        }
        try {
            em.persist(accountantEntity);
            em.getTransaction().commit();

        } catch (Exception ex) {
            return ex.toString();
        }
        em.close();
        return "Accountant added";
    }


    public TableModel accountantToTable() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<ArrGenerate> list = em.createQuery("FROM AccountantEntity ").getResultList();
        list.sort(Comparator.comparingInt(ArrGenerate::getId));


        em.close();
        String[] columnArr = {"id", "id bank", "full name", "residence address", "position", "phone number"};

        return new TableModel(6, list, columnArr);
    }

    public String dellAccountant(String idTextField) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            int id = Integer.parseInt(idTextField);
            AccountantEntity accountantEntity = em.find(AccountantEntity.class, id);
            em.remove(accountantEntity);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            return e.toString();
        }
        return "Accountant delete";
    }

    public String changeAccountant(String idTextField, String bankId,
                             String textField3, String textField4,
                             String textField5, String textField6) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();

        Session s = em.unwrap(Session.class);
        Transaction tx1 = s.beginTransaction();
        try {

            AccountantEntity accountantEntity = em.find(AccountantEntity.class, Integer.parseInt(idTextField));
            BankEntity bankEntity = em.find(BankEntity.class, bankId);

            accountantEntity.setBankByIdBank(bankEntity);
            accountantEntity.setFullName(textField3);
            accountantEntity.setResidenceAddress(textField4);
            accountantEntity.setPosition(textField5);
            accountantEntity.setPhoneNumber(textField6);

            s.update(accountantEntity);
            tx1.commit();
        } catch (Exception e) {
            return e.toString();
        }
        em.close();
        return "Accountant refresh";
    }


}
