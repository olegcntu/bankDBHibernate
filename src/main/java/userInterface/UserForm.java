package userInterface;

import dbEntities.BankEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserForm extends JFrame {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JPanel Bank;
    private JPanel Accountant;
    private JPanel Client;
    private JPanel Credit;
    private JPanel Contract;
    private JTabbedPane tabbedPane2;
    private JPanel BankAdd;
    private JPanel BankDelete;
    private JPanel BankCheck;
    private JPanel BankChange;
    private JTextField idTextField;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton addButton;
    private JTextArea textArea1;
    private JTextField textField6;
    private JTable table1;
    private JButton button1;

    public UserForm(String title) {
        super(title);
        this.setContentPane(mainPanel);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBank();
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new UserForm("Bank system");
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public void addBank(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        BankEntity bankEntity = null;
        try {
            bankEntity = new BankEntity(
                    (int) Integer.parseInt(idTextField.getText()),
                    textField2.getText(),
                    textField3.getText(),
                    textField4.getText(),
                    Double.parseDouble(textField5.getText()),
                    Double.parseDouble(textField6.getText()));

        } catch (Exception ex) {
            textArea1.setText(ex.toString());
        }
        try {
            em.persist(bankEntity);
            em.getTransaction().commit();

        } catch (Exception ex) {
            textArea1.setText(ex.toString());
        }
        em.close();
    }
}
