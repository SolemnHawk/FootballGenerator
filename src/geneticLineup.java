import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class geneticLineup {
    public List<Player> start(csvParser parser){

        ArrayList<ArrayList<Player>> playerSet=parser.database.getSortedDatabase();

        lineupSet set=new lineupSet();

        Random rand=new Random();
        int randomVal;

        int lineupCounter=9; //number of players in a full lineup

        while (lineupCounter!=0)
        {
            randomVal=rand.nextInt(playerSet.size());


            if(set.playerViable(playerSet.get(randomVal))==true) { //If player meets criteria add to lineup
                set.addPlayer(playerSet.get(randomVal));
                lineupCounter--;
            }

        }

        set.sortLineup();
        return set.getlineUp();
    }
}
