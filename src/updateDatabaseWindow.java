import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class updateDatabaseWindow {


    updateDatabaseWindow(csvParser parser) {


        FileDialog dialog=new FileDialog((Frame)null, "Select Salary File");

        JDialog databaseFrame = new JDialog();

        JLabel header=new JLabel();
        header.setFont(new Font ("Courier",Font.BOLD,14));
        header.setBounds(10,-120,900,300);
        header.setText("Here you may:");
        databaseFrame.add(header);

        //Salary button
        JButton updateSalaryButton = new JButton("Update Salaries");
        updateSalaryButton.setBounds(10, 80, 150, 40);
        databaseFrame.add(updateSalaryButton);

        JLabel salaryUpdate=new JLabel("");
        salaryUpdate.setBounds(10,110,150,40);
        salaryUpdate.setVisible(false);
        databaseFrame.add(salaryUpdate);

        //Projection button
        JButton updateProjectionButton = new JButton("Update Projections");
        updateProjectionButton.setBounds(10, 180, 150, 40);
        databaseFrame.add(updateProjectionButton);
        updateProjectionButton.setEnabled(false);

        JLabel projections =new JLabel("QB        RB       WR");
        projections.setBounds(10,210,150,40);
        projections.setVisible(true);
        databaseFrame.add(projections);

        JLabel projections2 =new JLabel("TE        DST");
        projections2.setBounds(30,225,150,40);
        projections2.setVisible(true);
        databaseFrame.add(projections2);

        JLabel QBprojectionUpdate=new JLabel("X");
        QBprojectionUpdate.setBounds(32,210,150,40);
        QBprojectionUpdate.setVisible(false);
        databaseFrame.add(QBprojectionUpdate);

        JLabel RBprojectionUpdate=new JLabel("X");
        RBprojectionUpdate.setBounds(74,210,150,40);
        RBprojectionUpdate.setVisible(false);
        databaseFrame.add(RBprojectionUpdate);

        JLabel WRprojectionUpdate=new JLabel("X");
        WRprojectionUpdate.setBounds(115,210,150,40);
        WRprojectionUpdate.setVisible(false);
        databaseFrame.add(WRprojectionUpdate);

        JLabel TEprojectionUpdate=new JLabel("X");
        TEprojectionUpdate.setBounds(50,225,150,40);
        TEprojectionUpdate.setVisible(false);
        databaseFrame.add(TEprojectionUpdate);

        JLabel DSTprojectionUpdate=new JLabel("X");
        DSTprojectionUpdate.setBounds(95,225,150,40);
        DSTprojectionUpdate.setVisible(false);
        databaseFrame.add(DSTprojectionUpdate);

        //Return button
        JButton returnButton = new JButton("Finished");
        returnButton.setBounds(10, 280, 150, 40);
        databaseFrame.add(returnButton);

        databaseFrame.setSize(250,400);
        databaseFrame.setLocationRelativeTo(null);
        databaseFrame.setLayout(null);
        databaseFrame.setVisible(true);
        databaseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        //action listener
        updateSalaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dialog.setMode(FileDialog.LOAD);
                dialog.setVisible(true);
                String file=dialog.getDirectory() + dialog.getFile();
                salaryUpdate.setVisible(true);
                if (parser.parseSalary(file)==true) {
                    updateSalaryButton.setEnabled(false);
                    salaryUpdate.setText("Salaries updated!");
                    updateProjectionButton.setEnabled(true);
                }
                else
                    salaryUpdate.setText("File Failed to Open!");
            }
        });
        updateProjectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                dialog.setMode(FileDialog.LOAD);
//                dialog.setVisible(true);
//                String file=dialog.getFile();
//
//                if(parser.parseProjection(file)==true)
//                    if(file.contains("QB"))
//                        QBprojectionUpdate.setVisible(true);
//                    else if(file.contains("WR"))
//                        WRprojectionUpdate.setVisible(true);
//                    else if(file.contains("RB"))
//                        RBprojectionUpdate.setVisible(true);
//                    else if(file.contains("TE"))
//                        TEprojectionUpdate.setVisible(true);
//                    else if(file.contains("DST"))
//                        DSTprojectionUpdate.setVisible(true);

                parser.parseProjection("FantasyPros_Fantasy_Football_Projections_QB.csv");
                QBprojectionUpdate.setVisible(true);
                parser.parseProjection("FantasyPros_Fantasy_Football_Projections_WR.csv");
                WRprojectionUpdate.setVisible(true);
                parser.parseProjection("FantasyPros_Fantasy_Football_Projections_RB.csv");
                RBprojectionUpdate.setVisible(true);
                parser.parseProjection("FantasyPros_Fantasy_Football_Projections_TE.csv");
                TEprojectionUpdate.setVisible(true);
                parser.parseProjection("FantasyPros_Fantasy_Football_Projections_DST.csv");
                DSTprojectionUpdate.setVisible(true);

            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                databaseFrame.dispose();
            }
        });

    }
}
