import java.util.ArrayList;
import java.util.Random;

public class geneticLineup  extends lineupSet{


    private boolean ELITISM=true; // keep best lineup into future
    private double MUTATE_RATE=.01; // rate of mutation
    private int GEN_SIZE=20; //# of lineups per generation
    private int LIFETIME=100; //# of Gen to run
    private int chromosomeCount=0;
    lineupSet[] fullGeneration=new lineupSet[GEN_SIZE];
    
    public geneticLineup(){
    }

    public void createLineup(csvParser parser) {

        ArrayList<ArrayList<Player>> playerSet = parser.database.getSortedDatabase();

        lineupSet set = new lineupSet();

        Random rand = new Random();
        int randomVal;
        int count=0;

        int lineupCounter = 9; //number of players in a full lineup

        //add smaller cost positions first to create easier viability checks
        while(lineupCounter==9) {
            randomVal = rand.nextInt(playerSet.get(4).size()); //DEF addition first
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
        set.findFitness();
        fullGeneration[chromosomeCount]=set;
        chromosomeCount++;
    }
    public boolean generationFull(){
        if (chromosomeCount<GEN_SIZE)
            return false;
        return true;
    }
    public double getFitness(lineupSet lineup){
        return lineup.getFitness();
    }
    public int getGEN_SIZE(){return GEN_SIZE;}
}
