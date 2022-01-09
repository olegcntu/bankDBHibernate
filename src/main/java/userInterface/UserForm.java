package userInterface;

import dbEntities.BankEntity;
import dbEntities.ArrGenerate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import logic.AccountantLogic;
import logic.BankLogic;
import logic.ContractLogic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
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
    private JTabbedPane tabbedPane3;
    private JTable table2;
    private JButton updateButton;
    private JTextField textField13;
    private JTextField textField14;
    private JTextField textField15;
    private JTextField textField16;
    private JTextField textField17;
    private JTextField textField18;
    private JTextArea textArea4;
    private JButton addButton1;
    private JTextField textField19;
    private JButton deleteButton1;
    private JTextArea textArea5;
    private JTextField textField20;
    private JTextField textField21;
    private JTextField textField22;
    private JTextField textField23;
    private JTextField textField24;
    private JTextField textField25;
    private JTextArea textArea6;
    private JButton changeButton1;
    private JTabbedPane tabbedPane4;
    private JTable table3;
    private JButton updateButton1;
    private JTextField textField26;
    private JTextField textField27;
    private JTextField textField28;
    private JTextField textField29;
    private JTextField textField30;
    private JTextField textField31;
    private JButton addButton2;
    private JTextArea textArea7;
    private JTextField textField32;
    private JButton deleteButton2;
    private JTextArea textArea8;
    private JTextField textField33;
    private JTextField textField34;
    private JTextField textField35;
    private JTextField textField36;
    private JTextField textField37;
    private JTextField textField38;
    private JButton changeButton2;
    private JTextArea textArea9;

    public UserForm(String title) {
        super(title);
        this.setContentPane(mainPanel);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);


        addButton.addActionListener(e -> {

            BankLogic bankLogic = new BankLogic();
            textArea1.setText(bankLogic.addBank(idTextField.getText(), textField2.getText(), textField3.getText()
                    , textField4.getText(), textField5.getText(), textField6.getText()));
        });
        button1.addActionListener(e -> {
            BankLogic bankLogic = new BankLogic();
            table1.setModel(bankLogic.bankToTable());
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
        updateButton.addActionListener(e -> {
            AccountantLogic accountantLogic = new AccountantLogic();
            table2.setModel(accountantLogic.accountantToTable());
        });

        addButton1.addActionListener(e -> {

            AccountantLogic accountantLogic = new AccountantLogic();
            textArea4.setText(accountantLogic.addAccountant(textField13.getText(), textField14.getText(),
                    textField15.getText(), textField16.getText(),
                    textField17.getText(), textField18.getText()));
        });
        deleteButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountantLogic accountantLogic = new AccountantLogic();
                textArea4.setText(accountantLogic.dellAccountant(textField19.getText()));
            }
        });
        changeButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountantLogic accountantLogic = new AccountantLogic();
                textArea6.setText(accountantLogic.changeAccountant(textField20.getText(),textField21.getText(),
                        textField22.getText(),textField23.getText(),textField24.getText(),textField25.getText()));
            }
        });
        updateButton1.addActionListener(e -> {
            ContractLogic contractLogic = new ContractLogic();
            table3.setModel(contractLogic.contractToTable());
        });


        addButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ContractLogic contractLogic = new ContractLogic();
                textArea7.setText(contractLogic.addContract(textField26.getText(),textField27.getText(),
                        textField28.getText(),textField29.getText(),textField30.getText(),textField31.getText()));
            }
        });
        deleteButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ContractLogic contractLogic = new ContractLogic();
                textArea8.setText(contractLogic.dellContract(textField32.getText()));
            }
        });
        changeButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ContractLogic contractLogic = new ContractLogic();
                textArea9.setText(contractLogic.changeContract(textField33.getText(),textField34.getText(),
                        textField35.getText(),textField36.getText(),textField37.getText(),textField38.getText()));
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
