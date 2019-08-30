package app;

import models.ScheduleEntity;
import services.ScheduleService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ScheduleSelectionFrame extends JFrame {
    private static final int WINDOW_HEIGHT = 300;
    private static final int WINDOW_WIDTH = 200;
    private static ScheduleEntity result = new ScheduleEntity();

    public ScheduleEntity getResult(){return result;}

    public ScheduleSelectionFrame(){
        super("Выбор графика");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //основная панель
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 1, 5, 0));

        //модель для списка должностей
        final DefaultListModel listModel = new DefaultListModel();
        ScheduleService schService = new ScheduleService();
        final List<ScheduleEntity> schedules;
        schedules = schService.getAllSchedules();
        for (ScheduleEntity sch : schedules
        ) {
            listModel.addElement(sch.getShifts() + " по " + sch.getWorkTimeHours() + " часов");

        }

        final JList scheduleList = new JList(listModel);

        final JScrollPane scrollPane = new JScrollPane(scheduleList);
        mainPanel.add(scrollPane);

        //кнопка OK
        final JButton okButton = new JButton("ОК");
        okButton.setFocusable(false);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = scheduleList.getSelectedIndex();
                    result = schedules.get(id);
                    dispose();
                }
                catch (ArrayIndexOutOfBoundsException ex){
                    JOptionPane.showMessageDialog(null, "Ошибка! Выберите график.");
                }
            }
        });
        buttonsPanel.add(okButton);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        //параметры формы
        getContentPane().add(mainPanel);
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
