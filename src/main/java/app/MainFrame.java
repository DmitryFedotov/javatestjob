package app;

import models.*;
import services.EmployeeService;
import services.PhonesService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;
import java.util.TimeZone;

public class MainFrame extends JFrame {
    private static final int WINDOW_HEIGHT = 500;
    private static final int WINDOW_WIDTH = 1100;
    private EmployeeService empService = new EmployeeService();
    private PhonesService phonesService = new PhonesService();
    private List<EmployeesEntity> employees;
    private final JTable table = new JTable();

    private EmpTableModel setData(){
        //получение списка сотрудников
        employees = empService.getAllEmp();

        //модель данных для JTable
        return new EmpTableModel(employees);
    }

    private MainFrame() {
        super("Список сотрудников");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //основная панель
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        //создание таблицы
        EmpTableModel model = setData();
        table.setModel(model);
        table.setFocusable(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        //группа кнопок внизу
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2, 2, 5, 0));
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        //кнопка просмотра информации
        final JButton viewInformButton = new JButton("Просмотр информации");
        viewInformButton.setFocusable(false);
        viewInformButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = table.getSelectedRow();
                try{
                   final EmployeesEntity emp =  employees.get(id);
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            InformFrame.setDefaultLookAndFeelDecorated(true);
                            new InformFrame(emp).setVisible(true);
                        }
                    });

                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(null, "Ошибка! Выберите строку.");
                }

            }
        });
        buttonsPanel.add(viewInformButton);

        //кнопка редактирования информации
        final JButton updateButton = new JButton("Редактировать");
        updateButton.setFocusable(false);
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = table.getSelectedRow();
                try{
                    final EmployeesEntity emp =  employees.get(id);
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            UpdateEmpFrame.setDefaultLookAndFeelDecorated(true);
                            final UpdateEmpFrame updateEmpFrame = new UpdateEmpFrame(emp);
                            updateEmpFrame.setVisible(true);
                            updateEmpFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                                public void windowClosed(java.awt.event.WindowEvent event) {

                                    EmployeesEntity emp = updateEmpFrame.getEmp();
                                    if(updateEmpFrame.getKeyChanged()) {
                                        if (empService.updateEmp(emp)) {
                                            JOptionPane.showMessageDialog(null, "Информация о сотруднике успешно обновлёна");
                                            table.setModel(setData());
                                        } else
                                            JOptionPane.showMessageDialog(null, "Ошибка обновления информации");
                                    }

                                }
                            });
                        }
                    });

                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(null, "Ошибка! Выберите строку.");
                }
            }
        });
        buttonsPanel.add(updateButton);

        //кнопка добавления сотрудника
        final JButton addButton = new JButton("Добавить");
        addButton.setFocusable(false);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        AddEmpFrame.setDefaultLookAndFeelDecorated(true);
                        final AddEmpFrame addEmpFrame = new AddEmpFrame();
                        addEmpFrame.setVisible(true);
                        addEmpFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                            public void windowClosed(java.awt.event.WindowEvent event) {
                                EmployeesEntity emp = addEmpFrame.getEmp();
                                List<PhonesEntity> phones = addEmpFrame.getPhones();
                                if(emp != null) {
                                    if (empService.createEmp(emp, phones)) {
                                        JOptionPane.showMessageDialog(null, "Сотрудник успешно добавлен");
                                        table.setModel(setData());
                                    } else
                                        JOptionPane.showMessageDialog(null, "Ошибка добавления сотрудника");
                                }

                            }
                        });
                    }
                });
            }
        });
        buttonsPanel.add(addButton);

        //кнопка удаления сотрудника
        final JButton removeButton = new JButton("Удалить");
        removeButton.setFocusable(false);
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = table.getSelectedRow();
                try{
                    if (empService.deleteEmp(employees.get(id)))
                      JOptionPane.showMessageDialog(null, "Сотрудник успешно удалён.");
                    else
                        JOptionPane.showMessageDialog(null, "Ошибка удаления.");
                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(null, "Ошибка! Выберите строку.");
                }
                finally {
                    table.setModel(setData());
                }

            }
        });
        buttonsPanel.add(removeButton);

        //группа кнопок вверху
        JPanel buttonsPanel1 = new JPanel();//правые кнопки
        buttonsPanel1.setLayout(new GridLayout(1, 3, 5, 0));
        mainPanel.add(buttonsPanel1, BorderLayout.NORTH);

        //комбобокс списков должностей
        String[] posStr = {"Все должности", "Выбор должности"};
        final JComboBox positionBox= new JComboBox(posStr);
        positionBox.setFocusable(false);
        positionBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selection = positionBox.getSelectedIndex();
                switch (selection) {
                    case 0:
                        table.setModel(setData());
                        break;
                    case 1:
                        final PosSelectionFrame posSelectionFrame = new PosSelectionFrame();
                        posSelectionFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                            public void windowClosed(java.awt.event.WindowEvent event) {
                                int id = posSelectionFrame.getResult().getId();
                                if(id != 0) {
                                    employees = empService.getByPos(id);
                                    if (employees.size() != 0)
                                        table.setModel(new EmpTableModel(employees));
                                    else
                                        JOptionPane.showMessageDialog(null, "Нет сотрудников с такой должностью.");
                                }
                             }
                        });
                        break;
                }
            }
        });
        buttonsPanel1.add(positionBox);

        //комбобокс списков графиков
        String[] schStr = {"Все графики", "Выбор графика"};
        final JComboBox scheduleBox= new JComboBox(schStr);
        scheduleBox.setFocusable(false);
        scheduleBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selection = scheduleBox.getSelectedIndex();
                switch (selection) {
                    case 0:
                        table.setModel(setData());
                        break;
                    case 1:
                        final ScheduleSelectionFrame scheduleSelectionFrame = new ScheduleSelectionFrame();
                        scheduleSelectionFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                            public void windowClosed(java.awt.event.WindowEvent event) {
                                int id = scheduleSelectionFrame.getResult().getId();
                                if(id != 0) {
                                    employees = empService.getBySchedule(id);
                                    if (employees.size() != 0)
                                        table.setModel(new EmpTableModel(employees));
                                    else
                                        JOptionPane.showMessageDialog(null, "Нет сотрудников с таким графиком.");
                                }
                            }
                        });
                        break;
                }
            }
        });
        buttonsPanel1.add(scheduleBox);

        //комбобокс поиска сотрудников
        final String[] empStr = {"Все сотрудники", "Поиск по ФИО", "Поиск по номеру телефона"};
        final JComboBox searchEmpBox= new JComboBox(empStr);
        searchEmpBox.setFocusable(false);
        searchEmpBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selection = searchEmpBox.getSelectedIndex();
                switch (selection) {
                    case 0:
                        table.setModel(setData());
                        break;
                    case 1:
                        final  FindByFIOFrame frame = new FindByFIOFrame();
                        frame.addWindowListener(new java.awt.event.WindowAdapter() {
                            public void windowClosed(java.awt.event.WindowEvent event) {
                                String lastName = frame.getLastName(), firstName = frame.getFirstName(), middleName = frame.getMiddleName();
                                if(lastName != null && firstName != null && middleName != null) {
                                    employees = empService.searchByFIO(lastName, firstName, middleName);
                                    if (employees.size() != 0)
                                        table.setModel(new EmpTableModel(employees));
                                    else
                                        JOptionPane.showMessageDialog(null, "Сотрудник не найден.");
                                }
                            }
                        });
                        break;
                    case 2:
                        final  FindByNumberFrame numberFrame = new FindByNumberFrame();
                        numberFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                            public void windowClosed(java.awt.event.WindowEvent event) {
                                String phoneNumber = numberFrame.getNumber();
                                if(phoneNumber != null) {
                                    employees = empService.getEmpById(phonesService.getEmpByNumber(phoneNumber));
                                    if (employees.size() != 0)
                                        table.setModel(new EmpTableModel(employees));
                                    else
                                        JOptionPane.showMessageDialog(null, "Сотрудник не найден.");
                                }
                            }
                        });
                        break;
                }
            }
        });
        buttonsPanel1.add(searchEmpBox);

        //параметры формы
        getContentPane().add(mainPanel);
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                new MainFrame();
                TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

            }
        });
    }

}
