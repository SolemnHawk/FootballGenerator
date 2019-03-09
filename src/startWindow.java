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

    csvParser parser=new csvParser();
    startWindow() {

        parser.setBaseDatabase();

        JFrame startFrame = new JFrame("Job Hunt Tracker");


        JLabel header=new JLabel();
        header.setFont(new Font ("Courier",Font.BOLD,14));
        header.setBounds(10,-120,900,300);
        header.setText("<html>Football Money Maker!<br/> Here you may:.</html>");
        startFrame.add(header);

        //New Job button
        JButton newRosterButton = new JButton("Generate a roster");
        newRosterButton.setBounds(20, 80, 150, 40);
        startFrame.add(newRosterButton);

        //Edit Job button
        JButton editRosterButton = new JButton("Manually build");
        editRosterButton.setBounds(20, 180, 150, 40);
        startFrame.add(editRosterButton);

        //Print Job button
        JButton updateRosterButton = new JButton("Update Database");
        updateRosterButton.setBounds(20, 280, 150, 40);
        startFrame.add(updateRosterButton);

        startFrame.setSize(250,400);
        startFrame.setLocationRelativeTo(null);
        startFrame.setLayout(null);
        startFrame.setVisible(true);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //action listener
        newRosterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                startFrame.setVisible(false);
                parser.database.sortDatabase();
                new LineupWindow(parser);
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
                startFrame.setVisible(false);
                new updateDatabaseWindow();
            }
        });
    }
}
