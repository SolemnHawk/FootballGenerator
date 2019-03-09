import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class geneticLineup {
    public Player[] start(csvParser parser) {

        ArrayList<ArrayList<Player>> playerSet = parser.database.getSortedDatabase();

        lineupSet set = new lineupSet();

        Random rand = new Random();
        int randomVal;
        int count=0;

        int lineupCounter = 9; //number of players in a full lineup

        //add smaller cost positions first to create easier viability checks
        randomVal = rand.nextInt(playerSet.get(4).size()); //DEF addition first
        while(lineupCounter==9) {
            if (set.playerViable(playerSet.get(4).get(randomVal)) == true) { //If player meets criteria add to lineup
                set.addPlayer(playerSet.get(4).get(randomVal));
                lineupCounter--;
            }
        }

        while(lineupCounter==8) {
            randomVal = rand.nextInt(playerSet.get(3).size()); //TE
            if (set.playerViable(playerSet.get(3).get(randomVal)) == true) { //If player meets criteria add to lineup
                set.addPlayer(playerSet.get(3).get(randomVal));
                lineupCounter--;
            }
        }

        while(lineupCounter==7) {
            randomVal = rand.nextInt(playerSet.get(0).size()); //QB
            if (set.playerViable(playerSet.get(0).get(randomVal)) == true) { //If player meets criteria add to lineup
                set.addPlayer(playerSet.get(0).get(randomVal));
                lineupCounter--;
            }
        }

        while(lineupCounter>4) {
            while (count < 2) {
                randomVal = rand.nextInt(playerSet.get(1).size()); //RB
                if (set.playerViable(playerSet.get(1).get(randomVal)) == true) { //If player meets criteria add to lineup
                    set.addPlayer(playerSet.get(1).get(randomVal));
                    lineupCounter--;
                    count++;
                }
            }
        }

        count=0;
        while(lineupCounter>1) {
            while (count < 3) {
                randomVal = rand.nextInt(playerSet.get(2).size()); //WR
                if (set.playerViable(playerSet.get(2).get(randomVal)) == true) { //If player meets criteria add to lineup
                    set.addPlayer(playerSet.get(2).get(randomVal));
                    lineupCounter--;
                    count++;
                }
            }
        }
        //Selecting position for the flex
        while(lineupCounter>0) {
            randomVal = rand.nextInt(2);
            System.out.println(randomVal);
            if (randomVal == 0) { //flex will be a RB
                randomVal = rand.nextInt(playerSet.get(1).size());
                if (set.playerViable(playerSet.get(1).get(randomVal)) == true) { //If player meets criteria add to lineup
                    set.addPlayer(playerSet.get(1).get(randomVal));
                    lineupCounter--;
                }
            } else
                randomVal = rand.nextInt(playerSet.get(2).size());
            if (set.playerViable(playerSet.get(2).get(randomVal)) == true) { //If player meets criteria add to lineup
                set.addPlayer(playerSet.get(2).get(randomVal));
                lineupCounter--;
            }
        }
        set.sortLineup();
        return set.getlineUp();
    }
}
