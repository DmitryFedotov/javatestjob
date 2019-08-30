package app;

import models.EmployeesEntity;
import models.PhonesEntity;
import models.PhonesTableModel;
import services.PhonesService;
import services.PositionService;
import services.ScheduleService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InformFrame extends JFrame {
    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 300;
    private static final int FIELD_HEIGHT = 22;

    public InformFrame(EmployeesEntity emp){
        super("Просмотр информации о сотруднике");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //основная панель
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 1, 5, 0));

        //кнопка OK
        final JButton okButton = new JButton("ОК");
        okButton.setFocusable(false);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonsPanel.add(okButton);

        //фамилия
        final JLabel lastNameLabel = new JLabel("Фамилия ", JLabel.LEFT);
        mainPanel.add(lastNameLabel);

        final JTextField lastNameTF = new JTextField(15);
        lastNameTF.setPreferredSize(new Dimension(WINDOW_WIDTH, FIELD_HEIGHT));
        lastNameTF.setEditable(false);
        lastNameTF.setText(emp.getLastName());
        mainPanel.add(lastNameTF);

        //имя
        final JLabel firstNameLabel = new JLabel("Имя ");
        mainPanel.add(firstNameLabel);

        final JTextField firstNameTF = new JTextField(15);
        firstNameTF.setPreferredSize(new Dimension(WINDOW_WIDTH, FIELD_HEIGHT));
        firstNameTF.setEditable(false);
        firstNameTF.setText(emp.getFirstName());
        mainPanel.add(firstNameTF);

        //отчество
        final JLabel middleNameLabel = new JLabel("Отчество ");
        mainPanel.add(middleNameLabel);

        final JTextField middleNameTF = new JTextField(15);
        middleNameTF.setPreferredSize(new Dimension(WINDOW_WIDTH, FIELD_HEIGHT));
        middleNameTF.setEditable(false);
        middleNameTF.setText(emp.getMiddleName());
        mainPanel.add(middleNameTF);

        //адресс
        final JLabel addressLabel = new JLabel("Адрес ");
        mainPanel.add(addressLabel);

        final JTextArea addressTF = new JTextArea(3,100);
        JScrollPane scrollAddressArea = new JScrollPane(addressTF,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);;
        addressTF.setEditable(false);
        addressTF.setText(emp.getAdress());
        mainPanel.add(scrollAddressArea);

        //дата рождения
        final JLabel birthdayLabel = new JLabel("Дата рождения ");
        mainPanel.add(birthdayLabel);

        final JTextField birthdayTF = new JTextField(15);
        birthdayTF.setPreferredSize(new Dimension(WINDOW_WIDTH, FIELD_HEIGHT));
        birthdayTF.setEditable(false);
        birthdayTF.setText(emp.getBirthday().toString());
        mainPanel.add(birthdayTF);

        //комментарий
        final JLabel commentLabel = new JLabel("Комментарий ");
        mainPanel.add(commentLabel);

        final JTextArea commentTF = new JTextArea(3,100);
        JScrollPane scrollCommentArea = new JScrollPane(commentTF,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        commentTF.setEditable(false);
        commentTF.setText(emp.getComment());
        mainPanel.add(scrollCommentArea);

        //должность
        final JLabel positionLabel = new JLabel("Должность ");
        mainPanel.add(positionLabel);

        final JTextField positionTF = new JTextField(15);
        positionTF.setPreferredSize(new Dimension(WINDOW_WIDTH, FIELD_HEIGHT));
        positionTF.setEditable(false);
        PositionService posService = new PositionService();
        positionTF.setText(posService.getPosition(emp.getPositionsByPositionId()));
        mainPanel.add(positionTF);

        //график
        final JLabel scheduleLabel = new JLabel("График работы ");
        mainPanel.add(scheduleLabel);

        final JTextField scheduleTF = new JTextField(15);
        scheduleTF.setPreferredSize(new Dimension(WINDOW_WIDTH, FIELD_HEIGHT));
        scheduleTF.setEditable(false);
        ScheduleService scheduleService = new ScheduleService();
        scheduleTF.setText(scheduleService.getSchedule(emp.getScheduleByScheduleId()));
        mainPanel.add(scheduleTF);

        //список телефонов
        final JLabel phonesLabel = new JLabel("Список телефонов ");
        mainPanel.add(phonesLabel);

        //модель для списка телефонов

        PhonesService phonesService = new PhonesService();
        List<PhonesEntity> phones = phonesService.getPhonesById(emp.getId());
        PhonesTableModel model = new PhonesTableModel(phones);
        final JTable phonesTable = new JTable(model);

        final JScrollPane scrollPane = new JScrollPane(phonesTable);
        mainPanel.add(scrollPane);
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
