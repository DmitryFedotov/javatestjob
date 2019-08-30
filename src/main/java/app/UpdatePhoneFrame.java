package app;

import models.PhonesEntity;
import services.PhonesService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePhoneFrame extends JFrame {
    private static final int WINDOW_HEIGHT = 160;
    private static final int WINDOW_WIDTH = 200;
    private PhonesEntity phone;
    private boolean keyChanged = false;
    private PhonesService phonesService = new PhonesService();

    public PhonesEntity getPhone(){return phone;}
    public boolean getKeyChanged(){return keyChanged;}

    public UpdatePhoneFrame(PhonesEntity phone){
        super("Редактировать номер телефона");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.phone = phone;

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
        phoneNumberTF.setText(phone.getNumber());
        mainPanel.add(phoneNumberTF);

        final JLabel typeLabel = new JLabel("Тип", JLabel.LEFT);
        mainPanel.add(typeLabel);

        final JTextField typeTF = new JTextField(15);
        typeTF.setPreferredSize(new Dimension(WINDOW_WIDTH, 10));
        typeTF.setText(phone.getType());
        mainPanel.add(typeTF);

        //кнопка OK
        final JButton okButton = new JButton("ОК");
        okButton.setFocusable(false);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!phoneNumberTF.getText().isEmpty() && !typeTF.getText().isEmpty()){
                    if(phonesService.isValid(phoneNumberTF.getText())) {
                        phone.setNumber(phoneNumberTF.getText());
                        phone.setType(typeTF.getText());
                        keyChanged = true;
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Некорректно введён номер телефона");
                        JOptionPane.showMessageDialog(null, "Формат: +7/8********* слитно");
                    }
                }
                else
                    JOptionPane.showMessageDialog(null,"Оба поля должны быть заполнены");
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
