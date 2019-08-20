import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class geneticLineup  extends lineupSet{

    Random rand = new Random();
    private boolean ELITISM=true; // keep best lineup into future
    private double MUTATE_RATE=1; // rate of mutation/100
    private int GEN_SIZE=100; //# of lineups per generation
    private int LIFETIME=1000; //# of Gen to run
    private int chromosomeCount=0;

    private double totalFitness=0;
    csvParser parser;

    ArrayList<ArrayList<Player>> playerSet;
    lineupSet[] fullGeneration=new lineupSet[GEN_SIZE];
    lineupSet[] generationCopy=new lineupSet[GEN_SIZE];
    
    public geneticLineup(csvParser parse){
        csvParser parser=parse;
        playerSet = parser.database.getSortedDatabase();
    }
    public int getLIFETIME(){
        return LIFETIME;
    }
    public void createLineup() {

        lineupSet set = new lineupSet();


        int randomVal;
        int count=0;

        int lineupCounter = 9; //number of players in a full lineup

        //add smaller cost positions first to create easier viability checks
        while(set.getSetSize()==0) {
            randomVal = rand.nextInt(playerSet.get(4).size()); //DEF addition first
            if (set.playerViable(playerSet.get(4).get(randomVal)) == true) { //If player meets criteria add to lineup
                set.addPlayer(playerSet.get(4).get(randomVal));
                lineupCounter--;
            }
        }

        while(set.getSetSize()==1) {
            randomVal = rand.nextInt(playerSet.get(3).size()); //TE
            if (set.playerViable(playerSet.get(3).get(randomVal)) == true) { //If player meets criteria add to lineup
                set.addPlayer(playerSet.get(3).get(randomVal));
                lineupCounter--;
            }
        }

        while(set.getSetSize()==2) {
            randomVal = rand.nextInt(playerSet.get(0).size()); //QB
            if (set.playerViable(playerSet.get(0).get(randomVal)) == true) { //If player meets criteria add to lineup
                set.addPlayer(playerSet.get(0).get(randomVal));
                lineupCounter--;
            }
        }

        while(set.getSetSize()<5) {
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
        while(set.getSetSize()<8) {
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
        while(set.getSetSize()<9) {
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
    public int getGEN_SIZE(){return GEN_SIZE;}
    private int partition(lineupSet arr[], int low, int high) {
        double pivot =  arr[high].getFitness();
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than or
            // equal to pivot
            if (fullGeneration[j].getFitness() >= pivot)
            {
                i++;
                lineupSet temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        lineupSet temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }
    public void sortGeneration(lineupSet arr[], int low, int high){ //Quicksort
        if (low<high)
        {
            int pi=partition(arr,low,high);
            sortGeneration(arr, low, pi-1);
            sortGeneration(arr, pi+1, high);
        }
    }
    void calculateFitnessRate() {
        double previous=0.0;

        for (int i=0;i<GEN_SIZE;i++)
            totalFitness+=fullGeneration[i].getFitness();

        for (int i=0;i<GEN_SIZE;i++) {
            fullGeneration[i].setFitnessPercent(totalFitness, previous);
            previous=fullGeneration[i].getFitnessPercent();

        }

    }
    public void evolveGeneration() {
            int lineupCounter = 0;
            generationCopy = fullGeneration.clone();


            //Take best Lineup First
            if (ELITISM == true) {
                fullGeneration[lineupCounter] = generationCopy[lineupCounter];
                lineupCounter++;
            }

            while (lineupCounter != (GEN_SIZE)) {
                //clear current lineup to be overwritten

                fullGeneration[lineupCounter] = new lineupSet();

                //Selection Step
                double randSelect1 = rand.nextDouble(); //Parent 1
                double randSelect2 = rand.nextDouble(); //Parent 2

                int parent1 = 0;
                boolean parent1Check = false;
                int parent2 = 0;
                boolean parent2Check = false;

                for (int i = 0; i < GEN_SIZE; i++) //increment and find first lineup above selection point, take the 1 right before
                {
                    if (randSelect1 < generationCopy[i].getFitnessPercent() && !parent1Check) {
                        parent1 = i;
                        parent1Check = true;
                    }
                    if (randSelect2 < generationCopy[i].getFitnessPercent() && !parent2Check) {
                        parent2 = i;
                        parent2Check = true;
                    }
                }

//            System.out.println("Parent Lineup 1:");
//            generationCopy[parent1].printLineup();
//            System.out.println(generationCopy[parent1].getFitness());
//            System.out.println();
//            System.out.println("Parent Lineup 2:");
//            generationCopy[parent2].printLineup();
//            System.out.println(generationCopy[parent2].getFitness());

                //Crossover
                for (int i = 0; i < 9; i++) {  //increment through the entire lineup

                    int randPick = rand.nextInt(2); //50/50 chance to swap the current player if they fit

                    if (randPick == 1) {
                        if (fullGeneration[lineupCounter].playerViable(generationCopy[parent2].sortedLineup[i]))  //if player is viable in new gen, add to new lineup
                            fullGeneration[lineupCounter].addPlayer(generationCopy[parent2].sortedLineup[i]);
                        else if (fullGeneration[lineupCounter].playerViable(generationCopy[parent1].sortedLineup[i])) //If chosen player wasnt viable, is the other lineup?
                            fullGeneration[lineupCounter].addPlayer(generationCopy[parent1].sortedLineup[i]);
                        else                                                                 //If neither matches, try and find a fit
                        {
                            while (fullGeneration[lineupCounter].sortedLineup[i] == null) { //generate a new player based on position in error
                                if (generationCopy[parent1].sortedLineup[i].getPlayerPos().equals("QB")) {
                                    int randomVal = rand.nextInt(playerSet.get(0).size());
                                    if (fullGeneration[lineupCounter].playerViable(playerSet.get(0).get(randomVal)))
                                        fullGeneration[lineupCounter].addPlayer(playerSet.get(0).get(randomVal));
                                } else if (generationCopy[parent1].sortedLineup[i].getPlayerPos().equals("RB")) {
                                    int randomVal = rand.nextInt(playerSet.get(1).size());
                                    if (fullGeneration[lineupCounter].playerViable(playerSet.get(1).get(randomVal)))
                                        fullGeneration[lineupCounter].addPlayer(playerSet.get(1).get(randomVal));
                                } else if (generationCopy[parent1].sortedLineup[i].getPlayerPos().equals("WR")) {
                                    int randomVal = rand.nextInt(playerSet.get(2).size());
                                    if (fullGeneration[lineupCounter].playerViable(playerSet.get(2).get(randomVal)))
                                        fullGeneration[lineupCounter].addPlayer(playerSet.get(2).get(randomVal));
                                } else if (generationCopy[parent1].sortedLineup[i].getPlayerPos().equals("TE")) {
                                    int randomVal = rand.nextInt(playerSet.get(3).size());
                                    if (fullGeneration[lineupCounter].playerViable(playerSet.get(3).get(randomVal)))
                                        fullGeneration[lineupCounter].addPlayer(playerSet.get(3).get(randomVal));
                                } else if (generationCopy[parent1].sortedLineup[i].getPlayerPos().equals("D")) {
                                    int randomVal = rand.nextInt(playerSet.get(4).size());
                                    if (fullGeneration[lineupCounter].playerViable(playerSet.get(4).get(randomVal)))
                                        fullGeneration[lineupCounter].addPlayer(playerSet.get(4).get(randomVal));
                                }
                                //   System.out.println(fullGeneration[lineupCounter].sortedLineup[i].getPlayerName() + " was mutated");
                            }
                        }
                    } else if (randPick == 0) {
                        if (fullGeneration[lineupCounter].playerViable(generationCopy[parent1].sortedLineup[i]))  //if player is viable in new gen, add to new lineup
                            fullGeneration[lineupCounter].addPlayer(generationCopy[parent1].sortedLineup[i]);
                        else if (fullGeneration[lineupCounter].playerViable(generationCopy[parent2].sortedLineup[i])) //If chosen player wasnt viable, is the other lineup?
                            fullGeneration[lineupCounter].addPlayer(generationCopy[parent2].sortedLineup[i]);
                        else                                                                 //If neither matches, try and find a fit
                        {
                            while (fullGeneration[lineupCounter].sortedLineup[i] == null) {
                                if (generationCopy[parent1].sortedLineup[i].getPlayerPos().equals("QB")) {
                                    int randomVal = rand.nextInt(playerSet.get(0).size());
                                    if (fullGeneration[lineupCounter].playerViable(playerSet.get(0).get(randomVal)))
                                        fullGeneration[lineupCounter].addPlayer(playerSet.get(0).get(randomVal));
                                } else if (generationCopy[parent1].sortedLineup[i].getPlayerPos().equals("RB")) {
                                    int randomVal = rand.nextInt(playerSet.get(1).size());
                                    if (fullGeneration[lineupCounter].playerViable(playerSet.get(1).get(randomVal)))
                                        fullGeneration[lineupCounter].addPlayer(playerSet.get(1).get(randomVal));
                                } else if (generationCopy[parent1].sortedLineup[i].getPlayerPos().equals("WR")) {
                                    int randomVal = rand.nextInt(playerSet.get(2).size());
                                    if (fullGeneration[lineupCounter].playerViable(playerSet.get(2).get(randomVal)))
                                        fullGeneration[lineupCounter].addPlayer(playerSet.get(2).get(randomVal));
                                } else if (generationCopy[parent1].sortedLineup[i].getPlayerPos().equals("TE")) {
                                    int randomVal = rand.nextInt(playerSet.get(3).size());
                                    if (fullGeneration[lineupCounter].playerViable(playerSet.get(3).get(randomVal)))
                                        fullGeneration[lineupCounter].addPlayer(playerSet.get(3).get(randomVal));
                                } else if (generationCopy[parent1].sortedLineup[i].getPlayerPos().equals("D")) {
                                    int randomVal = rand.nextInt(playerSet.get(4).size());
                                    if (fullGeneration[lineupCounter].playerViable(playerSet.get(4).get(randomVal)))
                                        fullGeneration[lineupCounter].addPlayer(playerSet.get(4).get(randomVal));
                                }
                                // System.out.println(fullGeneration[lineupCounter].sortedLineup[i].getPlayerName() + " was mutated");
                            }
                        }
                    }
                }

                fullGeneration[lineupCounter].sortLineup(); //sort into normal order
                fullGeneration[lineupCounter].findFitness();
                lineupCounter++;
            }
         }
}

