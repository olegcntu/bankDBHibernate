package userForm;

import dbEntities.AccountantEntity;
import dbEntities.BankEntity;
import dbEntities.ContractEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class Manager {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

     //   BankEntity b1 = new BankEntity(122, "1", "1", "1", 10.00, 19.00);

//        BankEntity b1 = new BankEntity();
//        b1.setId(121);
//        b1.setTown("1");
//        b1.setAddress("1");
//        b1.setNumber("+380969794628");
//        b1.setWorkStart(10.00);
//        b1.setWorkEnd(19.00);

        //  ContractEntity employee = em.find(ContractEntity.class, 1);
        // em.remove(employee);
       // em.persist(b1);

        em.getTransaction().commit();
        em.close();


//        List list = em.createQuery("FROM Bank").getResultList();
//        list.forEach(x-> System.out.println(x));
//        em.close();


    }
}
