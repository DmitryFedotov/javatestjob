package app;

import models.EmployeesEntity;
import models.PhonesEntity;
import models.PhonesTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddEmpFrame extends JFrame {
    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 320;
    private static final int FIELD_HEIGHT = 22;
    private ScheduleSelectionFrame scheduleSelectionFrame;
    private PosSelectionFrame posSelectionFrame;
    private EmployeesEntity emp;
    private List<PhonesEntity> phones = new ArrayList<>();
    private JTable phonesTable = new JTable();

    public EmployeesEntity getEmp(){
        return emp;
    }
    public List<PhonesEntity> getPhones(){return phones;}

    private PhonesTableModel setData(PhonesEntity phone){
        phones.add(phone);
        return new PhonesTableModel(phones);
    }

    public AddEmpFrame() {
        super("Добавления сотрудника");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //основная панель
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2, 5, 0));

        JPanel buttonsShowPanel = new JPanel();
        buttonsShowPanel.setLayout(new GridLayout(2, 1, 5, 0));

        JPanel buttPanel = new JPanel();
        buttPanel.setLayout(new GridLayout(1, 1, 5, 0));


        //фамилия
        final JLabel lastNameLabel = new JLabel("Фамилия ");
        lastNameLabel.setHorizontalAlignment(JLabel.LEFT);
        mainPanel.add(lastNameLabel);

        final JTextField lastNameTF = new JTextField(FIELD_HEIGHT);
        lastNameTF.setPreferredSize(new Dimension(WINDOW_WIDTH, FIELD_HEIGHT));
        mainPanel.add(lastNameTF);

        //имя
        final JLabel firstNameLabel = new JLabel("Имя ", JLabel.LEFT);
        mainPanel.add(firstNameLabel);

        final JTextField firstNameTF = new JTextField(20);
        firstNameTF.setPreferredSize(new Dimension(WINDOW_WIDTH, FIELD_HEIGHT));
        mainPanel.add(firstNameTF);

        //отчество
        final JLabel middleNameLabel = new JLabel("Отчество ", JLabel.LEFT);
        mainPanel.add(middleNameLabel);

        final JTextField middleNameTF = new JTextField(20);
        middleNameTF.setPreferredSize(new Dimension(WINDOW_WIDTH, FIELD_HEIGHT));
        mainPanel.add(middleNameTF);

        //адрес
        final JLabel addressLabel = new JLabel("Адрес ", JLabel.LEFT);
        mainPanel.add(addressLabel);

        final JTextArea addressTF = new JTextArea(3,100);
        addressTF.setPreferredSize(new Dimension(WINDOW_WIDTH, FIELD_HEIGHT));
        JScrollPane scrollAddressArea = new JScrollPane(addressTF,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        mainPanel.add(scrollAddressArea);

        //дата рождения
        final JLabel birthdayLabel = new JLabel("Дата рождения ", JLabel.LEFT);
        mainPanel.add(birthdayLabel);

        final JTextField birthdayTF = new JTextField(11);
        birthdayTF.setPreferredSize(new Dimension(WINDOW_WIDTH, FIELD_HEIGHT));
        mainPanel.add(birthdayTF);
        final boolean[] flag = {true};
        birthdayTF.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(flag[0]){
                    JOptionPane.showMessageDialog(null, "Формат даты YYYY-MM-DD");
                    flag[0] = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                flag[0] = true;
            }
        });

        //комментарий
        final JLabel commentLabel = new JLabel("Комментарий ", JLabel.LEFT);
        mainPanel.add(commentLabel);

        final JTextArea commentTF = new JTextArea(3,100);
        JScrollPane scrollCommentArea = new JScrollPane(commentTF,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        mainPanel.add(scrollCommentArea);

        //кнопка выбора должности
        final JButton positionButton = new JButton("Выбор должности");
        positionButton.setFocusable(false);
        positionButton.setPreferredSize(new Dimension(WINDOW_WIDTH, 30));
        positionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        PosSelectionFrame.setDefaultLookAndFeelDecorated(true);
                        posSelectionFrame = new PosSelectionFrame();
                        posSelectionFrame.setVisible(true);
                    }
                });
            }
        });
        buttonsShowPanel.add(positionButton);

        //кнопка выбора графика
        final JButton schedulesButton = new JButton("Выбор графика");
        schedulesButton.setFocusable(false);
        schedulesButton.setPreferredSize(new Dimension(WINDOW_WIDTH, 30));
        schedulesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        ScheduleSelectionFrame.setDefaultLookAndFeelDecorated(true);
                        scheduleSelectionFrame = new ScheduleSelectionFrame();
                        scheduleSelectionFrame.setVisible(true);
                    }
                });
            }
        });
        buttonsShowPanel.add(schedulesButton);
        mainPanel.add(buttonsShowPanel);

        //список телефонов
        final JLabel phonesLabel = new JLabel("Список телефонов ");
        mainPanel.add(phonesLabel);

        //phonesTable.setModel(new PhonesTableModel(phones));
        final JScrollPane scrollPane = new JScrollPane(phonesTable);
        mainPanel.add(scrollPane);

        //кнопка открытия формы добавления номера телефона
        final JButton phoneAddButton = new JButton("Добавить");
        phoneAddButton.setFocusable(false);
        phoneAddButton.setPreferredSize(new Dimension(WINDOW_WIDTH, 30));
        phoneAddButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        AddPhoneFrame.setDefaultLookAndFeelDecorated(true);
                        AddPhoneFrame addPhoneFrame = new AddPhoneFrame();
                        addPhoneFrame.setVisible(true);
                        addPhoneFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                            public void windowClosed(java.awt.event.WindowEvent event) {

                                PhonesEntity phone = addPhoneFrame.getPhone();
                                phonesTable.setModel(setData(phone));
                            }
                        });
                    }
                });
            }
        });
        buttPanel.add(phoneAddButton);
        mainPanel.add(buttPanel);

        //кнопка OK
        final JButton okButton = new JButton("ОК");
        okButton.setFocusable(false);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date bd;
                if(!lastNameTF.getText().isEmpty() && !firstNameTF.getText().isEmpty() && !middleNameTF.getText().isEmpty() &&
                        !addressTF.getText().isEmpty() && !birthdayTF.getText().isEmpty() && !commentTF.getText().isEmpty()) {
                    try {
                        emp = new EmployeesEntity();
                        bd = format.parse(birthdayTF.getText());
                        emp.setLastName(lastNameTF.getText());
                        emp.setFirstName(firstNameTF.getText());
                        emp.setMiddleName(middleNameTF.getText());
                        emp.setAdress(addressTF.getText());
                        emp.setBirthday(bd);
                        emp.setComment(commentTF.getText());
                        emp.setPositionsByPositionId(posSelectionFrame.getResult());
                        emp.setScheduleByScheduleId(scheduleSelectionFrame.getResult());

                        dispose();
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Ошибка! Введён не верный формат даты.");
                    } catch (NullPointerException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Все поля должны быть заполнены.");
                    }
                }
                else
                    JOptionPane.showMessageDialog(null, "Все поля должны быть заполнены.");



            }
        });
        buttonsPanel.add(okButton);

        //кнопка CANCEL
        final JButton cancelButton = new JButton("Отмена");
        cancelButton.setFocusable(false);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonsPanel.add(cancelButton);
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
