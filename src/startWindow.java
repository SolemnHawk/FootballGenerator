import javax.swing.*;
import javax.swing.JDialog;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.*;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class startWindow {

    csvParser parser=new csvParser();

    startWindow() {

        parser.setBaseDatabase();

        JFrame startFrame = new JFrame("Job Hunt Tracker");

        //Pushing image as a backdrop
        BufferedImage img=null;
        try {
             img = ImageIO.read(new File("src/resources/Main Background.png"));
        }
        catch(IOException   e){
            e.printStackTrace();
        }

        JLabel background=new JLabel(new ImageIcon(img));
        background.setBounds(0,0,250,400);


        JButton updateRosterButton = new JButton("1. Update Database");
        updateRosterButton.setBounds(50, 80, 150, 40);
        startFrame.add(updateRosterButton);

        JButton newRosterButton = new JButton("2. Generate a Roster");
        newRosterButton.setBounds(50, 180, 150, 40);
        startFrame.add(newRosterButton);
        newRosterButton.setEnabled(false);

        JButton editRosterButton = new JButton("3. Manually Build");
        editRosterButton.setBounds(50, 280, 150, 40);
        startFrame.add(editRosterButton);
        editRosterButton.setEnabled(false);

        JButton closeButton=new JButton("Exit");
        closeButton.setLocation(195,380);
        closeButton.setSize(55,20);
        startFrame.add(closeButton);

        startFrame.setSize(250,400);
        startFrame.setLocationRelativeTo(null);
        startFrame.setUndecorated(true);
        startFrame.setLayout(null);
        startFrame.setVisible(true);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.add(background);


        //action listener
        newRosterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                parser.database.trimDatabase();
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
                newRosterButton.setEnabled(true);
                new updateDatabaseWindow(parser);

            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
