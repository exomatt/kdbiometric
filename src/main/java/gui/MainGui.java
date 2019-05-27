/*
 * Created by JFormDesigner on Mon May 27 14:32:00 CEST 2019
 */

package gui;

import javafx.util.Pair;
import lombok.extern.java.Log;
import utils.FileSampleOperations;
import utils.Sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author exo
 */
@Log
public class MainGui extends JFrame {
    private List<Pair<Integer, Long>> queue = new LinkedList<>();

    private List<Sample> sampleList = new ArrayList<>();

    public MainGui() {
        initComponents();
    }

    private void textFieldDataFromUserActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void textFieldDataFromUserKeyPressed(KeyEvent e) {
        synchronized (queue) {
            queue.add(new Pair<>(e.getKeyCode(), System.currentTimeMillis()));
        }
    }


    private void textFieldDataFromUserKeyReleased(KeyEvent e) {
        Pair<Integer, Long> integerLongPair;
        synchronized (queue) {
            integerLongPair = queue.get(0);
            queue.remove(0);
        }
        long measuredTime = System.currentTimeMillis() - integerLongPair.getValue();
        String keyText = e.getKeyText(integerLongPair.getKey());
        log.info(" Text: " + keyText + " time" + measuredTime);
        sampleList.add(new Sample(keyText, measuredTime));
    }

    private void buttonResetActionPerformed(ActionEvent e) {
        log.info(Arrays.toString(sampleList.toArray()));
        sampleList.clear();
        textFieldDataFromUser.setText("");
        synchronized (queue) {
            queue.clear();
        }
        textFieldUsername.setText("");
    }

    private void textFieldDataFromUserKeyTyped(KeyEvent e) {
        // TODO add your code here
    }

    private void buttonSaveActionPerformed(ActionEvent e) {
        String username = textFieldUsername.getText();
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username should not be empty!!!!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        String data = textFieldDataFromUser.getText();
        if (data.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data textfield should not be empty!!!!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        StringBuilder stringBuilder = new StringBuilder();
        sampleList.forEach(p -> stringBuilder.append(p.getLetter()));
        String regex = "ala ma kota kot ma ale sierotka ma rysia a ada ma raka";
        String userString = stringBuilder.toString().toLowerCase().replaceAll("space", " ");
        System.out.println(userString);
        System.out.println(regex);
        if (userString.matches(regex)) {
            try {
                FileSampleOperations.save(username + ".txt", sampleList);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Some problems with file !!!!", "Error", JOptionPane.ERROR_MESSAGE);
                log.severe("File problem" + ex.getMessage() + Arrays.toString(ex.getStackTrace()));
            }
        } else {
            JOptionPane.showMessageDialog(this, "Text dont match to template :( !!!!", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - exo
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        textFieldUsername = new JTextField();
        label1 = new JLabel();
        label2 = new JLabel();
        buttonSave = new JButton();
        buttonReset = new JButton();
        textFieldDataFromUser = new JTextField();

        //======== this ========
        Container contentPane = getContentPane();

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("text");
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //---- label1 ----
        label1.setText("Username");

        //---- label2 ----
        label2.setText("ala ma kota kot ma ale sierotka ma rysia a ada ma raka");

        //---- buttonSave ----
        buttonSave.setText("Save");
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonSaveActionPerformed(e);
            }
        });

        //---- buttonReset ----
        buttonReset.setText("reset");
        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonResetActionPerformed(e);
            }
        });

        //---- textFieldDataFromUser ----
        textFieldDataFromUser.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                textFieldDataFromUserKeyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                textFieldDataFromUserKeyReleased(e);
            }

            @Override
            public void keyTyped(KeyEvent e) {
                textFieldDataFromUserKeyTyped(e);
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(51, 51, 51)
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addComponent(label1)
                                                        .addComponent(textFieldUsername, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(buttonSave)))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(118, 118, 118)
                                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(275, 275, 275)
                                                .addComponent(buttonReset))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(192, 192, 192)
                                                .addComponent(textFieldDataFromUser, GroupLayout.PREFERRED_SIZE, 418, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(190, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(label1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFieldUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonSave)
                                .addGap(30, 30, 30)
                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                .addComponent(textFieldDataFromUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(buttonReset)
                                .addGap(151, 151, 151))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - exo
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JTextField textFieldUsername;
    private JLabel label1;
    private JLabel label2;
    private JButton buttonSave;
    private JButton buttonReset;
    private JTextField textFieldDataFromUser;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
