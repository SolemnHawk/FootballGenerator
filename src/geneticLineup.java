import java.util.List;
import java.util.Random;

public class geneticLineup {
    public List<Player> start(csvParser parser){

        List<Player> playerSet=parser.database.getDatabase();

        lineupSet set=new lineupSet();

        Random rand=new Random();
        int randomVal;

        int lineupCounter=9; //number of players in a full lineup

        while (lineupCounter!=0)
        {
            randomVal=rand.nextInt(playerSet.size());


            if(set.playerViable(playerSet.get(randomVal))==true) {
                set.addPlayer(playerSet.get(randomVal));
                lineupCounter--;
            }

        }

        set.sortLineup();
        return set.getlineUp();
    }
}
