import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class geneticLineup  extends lineupSet{

    private Random rand;
    private boolean ELITISM=true; // keep best lineup into future
    private double MUTATE_RATE=1; // rate of mutation/100
    private int GEN_SIZE=10; //# of lineups per generation
    private int LIFETIME=10; //# of Gen to run


    private double totalFitness=0;
    csvParser parser;

    ArrayList<ArrayList<Player>> playerSet;
    lineupSet[] fullGeneration=new lineupSet[GEN_SIZE];
    lineupSet[] generationCopy=new lineupSet[GEN_SIZE];

    lineupSet set;
    int lineupCount=0;

    public geneticLineup(csvParser parse){
        rand = new Random();
        csvParser parser=parse;
        playerSet = parser.database.getSortedDatabase();
    }
    public int getLIFETIME(){
        return LIFETIME;
    }
    public void createLineup() {

        int randomVal;
        lineupSet set = new lineupSet();

        while(set.getSetSize()<=8) {
            //add smaller cost positions first to create easier viability checks
            if (set.getSetSize() == 0) {
                randomVal = rand.nextInt(playerSet.get(4).size()); //DEF addition first
                if (set.playerViable(playerSet.get(4).get(randomVal)) == true) { //If player meets criteria add to lineup
                    set.addPlayer(playerSet.get(4).get(randomVal));
                }
            } else if (set.getSetSize() == 1) {
                randomVal = rand.nextInt(playerSet.get(3).size()); //TE
                if (set.playerViable(playerSet.get(3).get(randomVal)) == true) { //If player meets criteria add to lineup
                    set.addPlayer(playerSet.get(3).get(randomVal));
                }
            } else if (set.getSetSize() == 2) {
                randomVal = rand.nextInt(playerSet.get(0).size()); //QB
                if (set.playerViable(playerSet.get(0).get(randomVal)) == true) { //If player meets criteria add to lineup
                    set.addPlayer(playerSet.get(0).get(randomVal));
                }
            } else if (set.getSetSize() == 3 || set.getSetSize() == 4) {
                    randomVal = rand.nextInt(playerSet.get(1).size()); //RB
                    if (set.playerViable(playerSet.get(1).get(randomVal)) == true) { //If player meets criteria add to lineup
                        set.addPlayer(playerSet.get(1).get(randomVal));
                    }
            } else if (set.getSetSize() == 5 || set.getSetSize() == 6 || set.getSetSize() == 7) {
                    randomVal = rand.nextInt(playerSet.get(2).size()); //WR
                    if (set.playerViable(playerSet.get(2).get(randomVal)) == true) { //If player meets criteria add to lineup
                        set.addPlayer(playerSet.get(2).get(randomVal));
                    }
            }
            //Selecting position for the flex
            else if (set.getSetSize() == 8) {
                randomVal = rand.nextInt(2);
                if (randomVal == 0) { //flex will be a RB
                    randomVal = rand.nextInt(playerSet.get(1).size());
                    if (set.playerViable(playerSet.get(1).get(randomVal)) == true) { //If player meets criteria add to lineup
                        set.addPlayer(playerSet.get(1).get(randomVal));
                    }
                } else
                    randomVal = rand.nextInt(playerSet.get(2).size());
                if (set.playerViable(playerSet.get(2).get(randomVal)) == true) { //If player meets criteria add to lineup
                    set.addPlayer(playerSet.get(2).get(randomVal));
                }
            }
        }
        set.sortLineup();
        set.findFitness();
        fullGeneration[lineupCount]=set;
        lineupCount++;
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
            previous = fullGeneration[i].getFitnessPercent();
        }
    }
    public void evolveGeneration() {
            int lineupCounter = 0;

            generationCopy = fullGeneration.clone();

            //clear current lineup to be overwritten
            fullGeneration[lineupCounter] = new lineupSet();

            //Take best Lineup First (take the best two?)
            if (ELITISM == true) {
                fullGeneration[lineupCounter] = generationCopy[lineupCounter];
                lineupCounter++;
            }

            while (lineupCounter != (GEN_SIZE)) {

                fullGeneration[lineupCounter] = new lineupSet();

                generationCopy[lineupCounter].printLineup();
                System.out.println(generationCopy[lineupCounter].getFitness());
                System.out.println("");

                //Selection Step
                double randSelect1 = rand.nextDouble(); //Parent 1
                double randSelect2 = rand.nextDouble(); //Parent 2

                int parent1 = 0;
                boolean parent1Check = false;
                int parent2 = 0;
                boolean parent2Check = false;



                for (int i = 0; i < GEN_SIZE; i++) //increment and find first lineup above selection point, take the 1 right before
                {
                    if (randSelect1 > generationCopy[i].getFitnessPercent() && !parent1Check) {
                        parent1 = i;
                        parent1Check = true;
                    }
                    if (randSelect2 > generationCopy[i].getFitnessPercent() && !parent2Check) {
                        parent2 = i;
                        parent2Check = true;
                    }
                }

                //Crossover
                int i=0;
               while (fullGeneration[lineupCounter].getSetSize()!=9){  //increment through the entire lineup

                    int randPick = rand.nextInt(2); //50/50 chance to swap the current player if they fit

                    if (randPick == 1) {
                        if (fullGeneration[lineupCounter].playerViable(generationCopy[parent2].sortedLineup[i]))  //if player is viable in new gen, add to new lineup
                            fullGeneration[lineupCounter].addPlayer(generationCopy[parent2].sortedLineup[i]);
                        else if (fullGeneration[lineupCounter].playerViable(generationCopy[parent1].sortedLineup[i])) //If chosen player wasnt viable, is the other lineup?
                            fullGeneration[lineupCounter].addPlayer(generationCopy[parent1].sortedLineup[i]);
                        else                                                                 //If neither matches, try and find a fit
                        {
                            fullGeneration[lineupCounter].clearLineUp();
                            createLineup();
                            fullGeneration[lineupCounter]=set;
                            System.out.println("Lineup " + " was mutated");
                            lineupCounter++;
                            i=0;
                        }
                    } else if (randPick == 0) {
                        if (fullGeneration[lineupCounter].playerViable(generationCopy[parent1].sortedLineup[i]))  //if player is viable in new gen, add to new lineup
                            fullGeneration[lineupCounter].addPlayer(generationCopy[parent1].sortedLineup[i]);
                        else if (fullGeneration[lineupCounter].playerViable(generationCopy[parent2].sortedLineup[i])) //If chosen player wasnt viable, is the other lineup?
                            fullGeneration[lineupCounter].addPlayer(generationCopy[parent2].sortedLineup[i]);
                        else                                                                 //If neither matches, try and find a fit
                        {
                            fullGeneration[lineupCounter].clearLineUp();
                            createLineup();
                            fullGeneration[lineupCounter]=set;
                            System.out.println("Lineup " + " was mutated");
                            lineupCounter++;
                            i=0;
                        }
                    }
                    i++;
                }

                fullGeneration[lineupCounter].sortLineup(); //sort into normal order
                fullGeneration[lineupCounter].findFitness();
                lineupCounter++;
            }
         }
}

