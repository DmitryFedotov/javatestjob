package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindByNumberFrame extends JFrame {
    private static final int WINDOW_HEIGHT = 110;
    private static final int WINDOW_WIDTH = 200;

    private String phoneNumber;

    public String getNumber() {
        return phoneNumber;
    }

    public FindByNumberFrame(){
        super("Поиск по номеру телефона");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //основная панель
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 1, 5, 0));

        final JLabel phoneNumberLabel = new JLabel("Номер телефона", JLabel.LEFT);
        mainPanel.add(phoneNumberLabel);

        final JTextField phoneNumberTF = new JTextField(15);
        phoneNumberTF.setPreferredSize(new Dimension(WINDOW_WIDTH, 10));
        mainPanel.add(phoneNumberTF);

        //кнопка OK
        final JButton okButton = new JButton("ОК");
        okButton.setFocusable(false);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!phoneNumberTF.getText().isEmpty()){
                    phoneNumber = phoneNumberTF.getText();
                    dispose();
                }
                else
                    JOptionPane.showMessageDialog(null,"Поле с номером должно быть заполнено");
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
