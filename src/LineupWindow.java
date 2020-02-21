import org.apache.commons.lang3.ObjectUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class LineupWindow {


    LineupWindow(csvParser parser) {
        GeneticsCode genetic=new GeneticsCode();
        
        JFrame lineupFrame = new JFrame("Generate a Lineup");
//
        JLabel header=new JLabel();
        header.setFont(new Font ("Courier",Font.BOLD,14));
        header.setBounds(10,-120,900,300);
        lineupFrame.add(header);


        JButton lineup = new JButton("Generate Lineup");
        lineup.setBounds(60, 20, 150, 40);
        lineupFrame.add(lineup);

        lineupFrame.setSize(400,400);
        lineupFrame.setLocationRelativeTo(null);
        lineupFrame.setLayout(null);
        lineupFrame.setVisible(true);
        lineupFrame.toFront();
        lineupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Set Bounds for Player Printouts
        JLabel QB=new JLabel(" ");
        QB.setBounds(40,50,350,40);
        QB.setVisible(true);
        lineupFrame.add(QB);
        JLabel QBTeam=new JLabel(" ");
        QBTeam.setBounds(210,50,350,40);
        QBTeam.setVisible(true);
        lineupFrame.add(QBTeam);
        JLabel QBSalary=new JLabel(" ");
        QBSalary.setBounds(270,50,350,40);
        QBSalary.setVisible(true);
        lineupFrame.add(QBSalary);
        JLabel QBProj=new JLabel(" ");
        QBProj.setBounds(330,50,350,40);
        QBProj.setVisible(true);
        lineupFrame.add(QBProj);

        JLabel RB1=new JLabel(" ");
        RB1.setBounds(40,80,350,40);
        RB1.setVisible(true);
        lineupFrame.add(RB1);
        JLabel RB1Team=new JLabel(" ");
        RB1Team.setBounds(210,80,350,40);
        RB1Team.setVisible(true);
        lineupFrame.add(RB1Team);
        JLabel RB1Salary=new JLabel(" ");
        RB1Salary.setBounds(270,80,350,40);
        RB1Salary.setVisible(true);
        lineupFrame.add(RB1Salary);
        JLabel RB1Proj=new JLabel(" ");
        RB1Proj.setBounds(330,80,350,40);
        RB1Proj.setVisible(true);
        lineupFrame.add(RB1Proj);

        JLabel RB2=new JLabel(" ");
        RB2.setBounds(40,110,350,40);
        RB2.setVisible(true);
        lineupFrame.add(RB2);
        JLabel RB2Team=new JLabel(" ");
        RB2Team.setBounds(210,110,350,40);
        RB2Team.setVisible(true);
        lineupFrame.add(RB2Team);
        JLabel RB2Salary=new JLabel(" ");
        RB2Salary.setBounds(270,110,350,40);
        RB2Salary.setVisible(true);
        lineupFrame.add(RB2Salary);
        JLabel RB2Proj=new JLabel(" ");
        RB2Proj.setBounds(330,110,350,40);
        RB2Proj.setVisible(true);
        lineupFrame.add(RB2Proj);

        JLabel WR1=new JLabel(" ");
        WR1.setBounds(40,140,350,40);
        WR1.setVisible(true);
        lineupFrame.add(WR1);
        JLabel WR1Team=new JLabel(" ");
        WR1Team.setBounds(210,140,350,40);
        WR1Team.setVisible(true);
        lineupFrame.add(WR1Team);
        JLabel WR1Salary=new JLabel(" ");
        WR1Salary.setBounds(270,140,350,40);
        WR1Salary.setVisible(true);
        lineupFrame.add(WR1Salary);
        JLabel WR1Proj=new JLabel(" ");
        WR1Proj.setBounds(330,140,350,40);
        WR1Proj.setVisible(true);
        lineupFrame.add(WR1Proj);

        JLabel WR2=new JLabel(" ");
        WR2.setBounds(40,170,350,40);
        WR2.setVisible(true);
        lineupFrame.add(WR2);
        JLabel WR2Team=new JLabel(" ");
        WR2Team.setBounds(210,170,350,40);
        WR2Team.setVisible(true);
        lineupFrame.add(WR2Team);
        JLabel WR2Salary=new JLabel(" ");
        WR2Salary.setBounds(270,170,350,40);
        WR2Salary.setVisible(true);
        lineupFrame.add(WR2Salary);
        JLabel WR2Proj=new JLabel(" ");
        WR2Proj.setBounds(330,170,350,40);
        WR2Proj.setVisible(true);
        lineupFrame.add(WR2Proj);

        JLabel WR3=new JLabel(" ");
        WR3.setBounds(40,200,350,40);
        WR3.setVisible(true);
        lineupFrame.add(WR3);
        JLabel WR3Team=new JLabel(" ");
        WR3Team.setBounds(210,200,350,40);
        WR3Team.setVisible(true);
        lineupFrame.add(WR3Team);
        JLabel WR3Salary=new JLabel(" ");
        WR3Salary.setBounds(270,200,350,40);
        WR3Salary.setVisible(true);
        lineupFrame.add(WR3Salary);
        JLabel WR3Proj=new JLabel(" ");
        WR3Proj.setBounds(330,200,350,40);
        WR3Proj.setVisible(true);
        lineupFrame.add(WR3Proj);

        JLabel TE=new JLabel(" ");
        TE.setBounds(40,230,350,40);
        TE.setVisible(true);
        lineupFrame.add(TE);
        JLabel TETeam=new JLabel(" ");
        TETeam.setBounds(210,230,350,40);
        TETeam.setVisible(true);
        lineupFrame.add(TETeam);
        JLabel TESalary=new JLabel(" ");
        TESalary.setBounds(270,230,350,40);
        TESalary.setVisible(true);
        lineupFrame.add(TESalary);
        JLabel TEProj=new JLabel(" ");
        TEProj.setBounds(330,230,350,40);
        TEProj.setVisible(true);
        lineupFrame.add(TEProj);

        JLabel FLEX=new JLabel(" ");
        FLEX.setBounds(40,260,350,40);
        FLEX.setVisible(true);
        lineupFrame.add(FLEX);
        JLabel FLEXTeam=new JLabel(" ");
        FLEXTeam.setBounds(210,260,350,40);
        FLEXTeam.setVisible(true);
        lineupFrame.add(FLEXTeam);
        JLabel FLEXSalary=new JLabel(" ");
        FLEXSalary.setBounds(270,260,350,40);
        FLEXSalary.setVisible(true);
        lineupFrame.add(FLEXSalary);
        JLabel FLEXProj=new JLabel(" ");
        FLEXProj.setBounds(330,260,350,40);
        FLEXProj.setVisible(true);
        lineupFrame.add(FLEXProj);

        JLabel DEF=new JLabel(" ");
        DEF.setBounds(40,290,350,40);
        DEF.setVisible(true);
        lineupFrame.add(DEF);
        JLabel DEFTeam=new JLabel(" ");
        DEFTeam.setBounds(210,290,350,40);
        DEFTeam.setVisible(true);
        lineupFrame.add(DEFTeam);
        JLabel DEFSalary=new JLabel(" ");
        DEFSalary.setBounds(270,290,350,40);
        DEFSalary.setVisible(true);
        lineupFrame.add(DEFSalary);
        JLabel DEFProj=new JLabel(" ");
        DEFProj.setBounds(330,290,350,40);
        DEFProj.setVisible(true);
        lineupFrame.add(DEFProj);

        JLabel Totals=new JLabel(" ");
        Totals.setBounds(10,320,350,40);
        Totals.setVisible(false);
        lineupFrame.add(Totals);
        //action listener
        lineup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                lineupSet lineup=genetic.runGenetic(parser);

                
                QB.setText("QB: "+ lineup.lineUp.get(0).getPlayerName());
                QBTeam.setText(lineup.lineUp.get(0).getPlayerTeam());
                QBSalary.setText(""+lineup.lineUp.get(0).getPlayerSalary());
                QBProj.setText(""+lineup.lineUp.get(0).getProjection());

                RB1.setText("RB1: "+ lineup.lineUp.get(1).getPlayerName());
                RB1Team.setText(lineup.lineUp.get(1).getPlayerTeam());
                RB1Salary.setText(""+lineup.lineUp.get(1).getPlayerSalary());
                RB1Proj.setText(""+lineup.lineUp.get(1).getProjection());

                RB2.setText("RB2: "+ lineup.lineUp.get(2).getPlayerName());
                RB2Team.setText(lineup.lineUp.get(2).getPlayerTeam());
                RB2Salary.setText(""+lineup.lineUp.get(2).getPlayerSalary());
                RB2Proj.setText(""+lineup.lineUp.get(2).getProjection());

                WR1.setText("WR1: "+ lineup.lineUp.get(3).getPlayerName());
                WR1Team.setText(lineup.lineUp.get(3).getPlayerTeam());
                WR1Salary.setText(""+lineup.lineUp.get(3).getPlayerSalary());
                WR1Proj.setText(""+lineup.lineUp.get(3).getProjection());

                WR2.setText("WR2: "+ lineup.lineUp.get(4).getPlayerName());
                WR2Team.setText(lineup.lineUp.get(4).getPlayerTeam());
                WR2Salary.setText(""+lineup.lineUp.get(4).getPlayerSalary());
                WR2Proj.setText(""+lineup.lineUp.get(4).getProjection());

                WR3.setText("WR3: "+ lineup.lineUp.get(5).getPlayerName());
                WR3Team.setText(lineup.lineUp.get(5).getPlayerTeam());
                WR3Salary.setText(""+lineup.lineUp.get(5).getPlayerSalary());
                WR3Proj.setText(""+lineup.lineUp.get(5).getProjection());

                TE.setText("TE: "+ lineup.lineUp.get(6).getPlayerName());
                TETeam.setText(lineup.lineUp.get(6).getPlayerTeam());
                TESalary.setText(""+lineup.lineUp.get(6).getPlayerSalary());
                TEProj.setText(""+lineup.lineUp.get(6).getProjection());

                FLEX.setText("FLEX: "+ lineup.lineUp.get(7).getPlayerName());
                FLEXTeam.setText(lineup.lineUp.get(7).getPlayerTeam());
                FLEXSalary.setText(""+lineup.lineUp.get(7).getPlayerSalary());
                FLEXProj.setText(""+lineup.lineUp.get(7).getProjection());

                DEF.setText("DEF: "+ lineup.lineUp.get(8).getPlayerName());
                DEFTeam.setText(lineup.lineUp.get(8).getPlayerTeam());
                DEFSalary.setText(""+lineup.lineUp.get(8).getPlayerSalary());
                DEFProj.setText(""+lineup.lineUp.get(8).getProjection());

                Totals.setText("     Total Cost: $"+ (lineup.lineUp.get(0).getPlayerSalary()+lineup.lineUp.get(1).getPlayerSalary()+lineup.lineUp.get(2).getPlayerSalary()+lineup.lineUp.get(3).getPlayerSalary()+lineup.lineUp.get(4).getPlayerSalary()
                        +lineup.lineUp.get(5).getPlayerSalary()+lineup.lineUp.get(6).getPlayerSalary()+lineup.lineUp.get(7).getPlayerSalary()+lineup.lineUp.get(8).getPlayerSalary())
                +"                  Projected Points: " +
                        ""+ (lineup.lineUp.get(0).getProjection()+lineup.lineUp.get(1).getProjection()+lineup.lineUp.get(2).getProjection()+lineup.lineUp.get(3).getProjection()+lineup.lineUp.get(4).getProjection()
                        +lineup.lineUp.get(5).getProjection()+lineup.lineUp.get(6).getProjection()+lineup.lineUp.get(7).getProjection()+lineup.lineUp.get(8).getProjection()));
                Totals.setVisible(true); 
            }
        });

    }
}
