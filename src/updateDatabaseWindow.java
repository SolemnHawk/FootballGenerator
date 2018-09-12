import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.*;
import java.io.IOException;

public class updateDatabaseWindow {

  csvParser parser=new csvParser();
    updateDatabaseWindow() {
        FileDialog dialog=new FileDialog((Frame)null, "Select Salary File");

        JFrame f = new JFrame("Update Database");
//        ut.println("Could not find image.");
//        }

//


        JLabel header=new JLabel();
        header.setFont(new Font ("Courier",Font.BOLD,14));
        header.setBounds(10,-120,900,300);
        header.setText("Here you may:");
        f.add(header);

        //New Job button
        JButton updateSalaryButton = new JButton("Update Salaries");
        updateSalaryButton.setBounds(10, 80, 150, 40);
        f.add(updateSalaryButton);

        JLabel salaryUpdate=new JLabel("Salaries updated.");
        salaryUpdate.setBounds(10,110,150,40);
        salaryUpdate.setVisible(false);
        f.add(salaryUpdate);

        //Edit Job button
        JButton updateProjectionButton = new JButton("Update Projections");
        updateProjectionButton.setBounds(10, 180, 150, 40);
        f.add(updateProjectionButton);

        JLabel projections =new JLabel("QB        RB       WR");
        projections.setBounds(10,210,150,40);
        projections.setVisible(true);
        f.add(projections);

        JLabel projections2 =new JLabel("TE        DST");
        projections2.setBounds(30,225,150,40);
        projections2.setVisible(true);
        f.add(projections2);

        JLabel QBprojectionUpdate=new JLabel("X");
        QBprojectionUpdate.setBounds(32,210,150,40);
        QBprojectionUpdate.setVisible(false);
        f.add(QBprojectionUpdate);

        JLabel RBprojectionUpdate=new JLabel("X");
        RBprojectionUpdate.setBounds(74,210,150,40);
        RBprojectionUpdate.setVisible(false);
        f.add(RBprojectionUpdate);

        JLabel WRprojectionUpdate=new JLabel("X");
        WRprojectionUpdate.setBounds(115,210,150,40);
        WRprojectionUpdate.setVisible(false);
        f.add(WRprojectionUpdate);

        JLabel TEprojectionUpdate=new JLabel("X");
        TEprojectionUpdate.setBounds(50,225,150,40);
        TEprojectionUpdate.setVisible(false);
        f.add(TEprojectionUpdate);

        JLabel DSTprojectionUpdate=new JLabel("X");
        DSTprojectionUpdate.setBounds(95,225,150,40);
        DSTprojectionUpdate.setVisible(false);
        f.add(DSTprojectionUpdate);
        //Return button
        JButton returnButton = new JButton("Finished");
        returnButton.setBounds(10, 280, 150, 40);
        f.add(returnButton);

        f.setSize(250,400);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        //action listener
        updateSalaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dialog.setMode(FileDialog.LOAD);
                dialog.setVisible(true);
                String file=dialog.getFile();
                parser.parseSalary(file);
                salaryUpdate.setVisible(true);

            }
        });
        updateProjectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                dialog.setMode(FileDialog.LOAD);
//                dialog.setVisible(true);
//                String file=dialog.getFile();

//                if(file.contains("QB"))
//                    QBprojectionUpdate.setVisible(true);
//                else if(file.contains("RB"))
//                    RBprojectionUpdate.setVisible(true);
//                else if(file.contains("WR"))
//                    WRprojectionUpdate.setVisible(true);
//                else if(file.contains("TE"))
//                    TEprojectionUpdate.setVisible(true);
//                else if(file.contains("DST"))
//                    DSTprojectionUpdate.setVisible(true);

                parser.parseProjection("FantasyPros_Fantasy_Football_Projections_WR.csv");
                parser.parseProjection("FantasyPros_Fantasy_Football_Projections_QB.csv");
                parser.parseProjection("FantasyPros_Fantasy_Football_Projections_RB.csv");
                parser.parseProjection("FantasyPros_Fantasy_Football_Projections_TE.csv");
                parser.parseProjection("FantasyPros_Fantasy_Football_Projections_DST.csv");

//
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parser.printDatabase();
            }
        });

    }
}
