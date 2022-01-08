package userInterface;

import dbEntities.BankEntity;
import dbEntities.ArrGenerate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import logic.BankLogic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;

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
    private JButton deleteButton;
    private JTextField textField1;
    private JTextArea textArea2;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField12;
    private JTextArea textArea3;
    private JButton changeButton;

    public UserForm(String title) {
        super(title);
        this.setContentPane(mainPanel);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);



        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                BankLogic bankLogic = new BankLogic();
                textArea1.setText(bankLogic.addBank(idTextField.getText(), textField2.getText(), textField3.getText()
                        , textField4.getText(), textField5.getText(), textField6.getText()));
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BankLogic bankLogic = new BankLogic();
                table1.setModel(bankLogic.bankToTable());
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BankLogic bankLogic = new BankLogic();
                textArea2.setText(bankLogic.dellBank(textField1.getText()));
            }
        });
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BankLogic bankLogic = new BankLogic();
                textArea3.setText(bankLogic.changeBank(textField7.getText(), textField8.getText(), textField9.getText()
                        , textField10.getText(), textField11.getText(), textField12.getText()));
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


}
