/*
 * Created by JFormDesigner on Mon May 27 14:32:00 CEST 2019
 */

package gui;

import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author exo
 */
public class MainGui extends JFrame {
    int lastCode = -1;
    long timeLast = -1;

    public MainGui() {
        initComponents();
    }

    private void textFieldDataFromUserActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void textFieldDataFromUserKeyPressed(KeyEvent e) {
        lastCode = e.getKeyCode();
        timeLast = System.currentTimeMillis();
    }

    private void textFieldDataFromUserKeyReleased(KeyEvent e) {
        if (lastCode != -1) {
            long measuredTime = System.currentTimeMillis() - timeLast;
            System.out.println(String.format("Code:%d,time%d", lastCode, measuredTime));
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - exo
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        textFieldUsername = new JTextField();
        label1 = new JLabel();
        textFieldDataFromUser = new JTextField();
        label2 = new JLabel();
        buttonSave = new JButton();

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

        //---- textFieldDataFromUser ----
        textFieldDataFromUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textFieldDataFromUserActionPerformed(e);
            }
        });
        textFieldDataFromUser.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                textFieldDataFromUserKeyPressed(e);
                textFieldDataFromUserKeyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                textFieldDataFromUserKeyReleased(e);
            }
        });

        //---- label2 ----
        label2.setText("ala ma kota kot ma ale sierotka ma rysia a ada ma raka");

        //---- buttonSave ----
        buttonSave.setText("Save");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                    .add(contentPaneLayout.createSequentialGroup()
                            .add(contentPaneLayout.createParallelGroup()
                                    .add(contentPaneLayout.createSequentialGroup()
                                            .add(51, 51, 51)
                                            .add(contentPaneLayout.createParallelGroup()
                                                    .add(label1)
                                                    .add(textFieldUsername, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
                                                    .add(buttonSave)))
                                    .add(contentPaneLayout.createSequentialGroup()
                                            .add(118, 118, 118)
                                            .add(label2, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE))
                                    .add(contentPaneLayout.createSequentialGroup()
                                            .add(78, 78, 78)
                                            .add(textFieldDataFromUser, GroupLayout.PREFERRED_SIZE, 646, GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(84, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                    .add(contentPaneLayout.createSequentialGroup()
                            .add(16, 16, 16)
                            .add(label1)
                            .addPreferredGap(LayoutStyle.RELATED)
                            .add(textFieldUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .add(18, 18, 18)
                            .add(buttonSave)
                            .add(30, 30, 30)
                            .add(label2, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.RELATED, 27, Short.MAX_VALUE)
                            .add(textFieldDataFromUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .add(234, 234, 234))
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
    private JTextField textFieldDataFromUser;
    private JLabel label2;
    private JButton buttonSave;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
