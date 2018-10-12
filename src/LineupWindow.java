import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class LineupWindow {


    LineupWindow(csvParser parser) {
        geneticLineup genetic=new geneticLineup();
        
        JFrame lineupFrame = new JFrame("Generate a Lineup");
//
        JLabel header=new JLabel();
        header.setFont(new Font ("Courier",Font.BOLD,14));
        header.setBounds(10,-120,900,300);
        lineupFrame.add(header);


        JButton lineup = new JButton("Generate Lineup");
        lineup.setBounds(10, 50, 150, 40);
        lineupFrame.add(lineup);

        lineupFrame.setSize(250,400);
        lineupFrame.setLocationRelativeTo(null);
        lineupFrame.setLayout(null);
        lineupFrame.setVisible(true);
        lineupFrame.toFront();
        lineupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JLabel QB=new JLabel(" ");
        QB.setBounds(10,80,200,40);
        QB.setVisible(false);
        lineupFrame.add(QB);

        JLabel RB1=new JLabel(" ");
        RB1.setBounds(10,110,200,40);
        RB1.setVisible(false);
        lineupFrame.add(RB1);

        JLabel RB2=new JLabel(" ");
        RB2.setBounds(10,140,200,40);
        RB2.setVisible(false);
        lineupFrame.add(RB2);

        JLabel WR1=new JLabel(" ");
        WR1.setBounds(10,170,200,40);
        WR1.setVisible(false);
        lineupFrame.add(WR1);

        JLabel WR2=new JLabel(" ");
        WR2.setBounds(10,200,200,40);
        WR2.setVisible(false);
        lineupFrame.add(WR2);

        JLabel WR3=new JLabel(" ");
        WR3.setBounds(10,230,200,40);
        WR3.setVisible(false);
        lineupFrame.add(WR3);

        JLabel TE=new JLabel(" ");
        TE.setBounds(10,260,200,40);
        TE.setVisible(false);
        lineupFrame.add(TE);

        JLabel FLEX=new JLabel(" ");
        FLEX.setBounds(10,290,200,40);
        FLEX.setVisible(false);
        lineupFrame.add(FLEX);

        JLabel DEF=new JLabel(" ");
        DEF.setBounds(10,320,200,40);
        DEF.setVisible(false);
        lineupFrame.add(DEF);

        //action listener
        lineup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                List<Player> lineup=genetic.start(parser);
                QB.setText("QB: "+ lineup.get(0).getPlayerName());
                QB.setVisible(true);
                RB1.setText("RB1: "+ lineup.get(1).getPlayerName());
                RB1.setVisible(true);
                RB2.setText("RB2: "+ lineup.get(2).getPlayerName());
                RB2.setVisible(true);
                WR1.setText("WR1: "+ lineup.get(3).getPlayerName());
                WR1.setVisible(true);
                WR2.setText("WR2: "+ lineup.get(4).getPlayerName());
                WR2.setVisible(true);
                WR3.setText("WR3: "+ lineup.get(5).getPlayerName());
                WR3.setVisible(true);
                TE.setText("TE: "+ lineup.get(6).getPlayerName());
                TE.setVisible(true);
                FLEX.setText("FLEX: "+ lineup.get(7).getPlayerName());
                FLEX.setVisible(true);
                DEF.setText("DEF: "+ lineup.get(8).getPlayerName());
                DEF.setVisible(true);
            }
        });

    }
}
