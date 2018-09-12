import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.imageio.*;
import java.io.IOException;

public class startWindow {

    String results;
    String query;
    String combo;
    startWindow() {

        JFrame f = new JFrame("Job Hunt Tracker");
//        ut.println("Could not find image.");
//        }

//


        JLabel header=new JLabel();
        header.setFont(new Font ("Courier",Font.BOLD,14));
        header.setBounds(10,-120,900,300);
        header.setText("<html>Football Money Maker!<br/> Here you may:.</html>");
        f.add(header);

        //New Job button
        JButton newRosterButton = new JButton("Generate a roster");
        newRosterButton.setBounds(20, 80, 150, 40);
        f.add(newRosterButton);

        //Edit Job button
        JButton editRosterButton = new JButton("Manually build");
        editRosterButton.setBounds(20, 180, 150, 40);
        f.add(editRosterButton);

        //Print Job button
        JButton updateRosterButton = new JButton("Update Database");
        updateRosterButton.setBounds(20, 280, 150, 40);
        f.add(updateRosterButton);

        f.setSize(250,400);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //action listener
        newRosterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                //new newjobWindow();
                f.setVisible(false);
            }
        });
        editRosterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        updateRosterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new updateDatabaseWindow();
            }
        });
    }
}
