package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindByFIOFrame extends JFrame {
    private static final int WINDOW_HEIGHT = 200;
    private static final int WINDOW_WIDTH = 200;

    private String lastName;
    private String firstName;
    private String middleName;

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public FindByFIOFrame(){
        super("Поиск по ФИО");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //основная панель
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 1, 5, 0));


        //фамилия
        final JLabel lastNameLabel = new JLabel("Фамилия ", JLabel.LEFT);
        mainPanel.add(lastNameLabel);

        final JTextField lastNameTF = new JTextField(15);
        lastNameTF.setPreferredSize(new Dimension(WINDOW_WIDTH, 10));
        mainPanel.add(lastNameTF);

        //имя
        final JLabel firstNameLabel = new JLabel("Имя ", JLabel.LEFT);
        mainPanel.add(firstNameLabel);

        final JTextField firstNameTF = new JTextField(15);
        firstNameTF.setPreferredSize(new Dimension(WINDOW_WIDTH, 10));
        mainPanel.add(firstNameTF);

        //отчество
        final JLabel middleNameLabel = new JLabel("Отчество ", JLabel.LEFT);
        mainPanel.add(middleNameLabel);

        final JTextField middleNameTF = new JTextField(15);
        middleNameTF.setPreferredSize(new Dimension(WINDOW_WIDTH, 10));
        mainPanel.add(middleNameTF);

        //кнопка OK
        final JButton okButton = new JButton("ОК");
        okButton.setFocusable(false);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!lastNameTF.getText().isEmpty() && !firstNameTF.getText().isEmpty() && !middleNameTF.getText().isEmpty()) {
                    lastName = lastNameTF.getText();
                    firstName = firstNameTF.getText();
                    middleName = middleNameTF.getText();
                    dispose();
                }
                else
                    JOptionPane.showMessageDialog(null,"Все поля должны быть заполнены");
            }
        });
        buttonsPanel.add(okButton);
        mainPanel.add(buttonsPanel);

        //параметры формы
        getContentPane().add(mainPanel);
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
