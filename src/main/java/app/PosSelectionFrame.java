package app;

import models.PositionsEntity;
import services.PositionService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PosSelectionFrame extends JFrame {
    private static final int WINDOW_HEIGHT = 300;
    private static final int WINDOW_WIDTH = 200;
    private static PositionsEntity result = new PositionsEntity();

    public PosSelectionFrame(){
        super("Выбор должности");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //основная панель
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 1, 5, 0));

        //модель для списка должностей
        final DefaultListModel listModel = new DefaultListModel();
        PositionService posService = new PositionService();
        final List<PositionsEntity> positions;
        positions = posService.getAllPos();
        for (PositionsEntity pos : positions
        ) {
            listModel.addElement(pos.getName());

        }

        final JList posList = new JList(listModel);


        final JScrollPane scrollPane = new JScrollPane(posList);
        mainPanel.add(scrollPane);

        //кнопка OK
        final JButton okButton = new JButton("ОК");
        okButton.setFocusable(false);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = posList.getSelectedIndex();
                    result = positions.get(id);
                    dispose();
                }
                catch (ArrayIndexOutOfBoundsException ex){
                    JOptionPane.showMessageDialog(null, "Ошибка! Выберите должность.");
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

    public PositionsEntity getResult() {
        return result;
    }
}
