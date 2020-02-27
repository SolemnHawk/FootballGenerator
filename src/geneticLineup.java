import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class geneticLineup extends lineupSet{

    private Random rand;
    private boolean ELITISM=true; // keep best lineup into future
    private double MUTATE_RATE=1; // rate of mutation/100
    private int GEN_SIZE=300; //# of lineups per generation
    private int LIFETIME=1000; //# of Gen to run
    private int optimizationCutoff=(LIFETIME/10);



    csvParser parser;

    ArrayList<ArrayList<Player>> playerSet;
    lineupSet[] fullGeneration=new lineupSet[GEN_SIZE];
    lineupSet[] nextGeneration=new lineupSet[GEN_SIZE];

    lineupSet set;

    public geneticLineup(csvParser parse){
        rand = new Random();
        csvParser parser=parse;
        playerSet = parser.database.getSortedDatabase();
    }
    public int getLIFETIME(){
        return LIFETIME;
    }
    public void createLineup(int lineupCount) {

        int randomVal;
        set = new lineupSet();

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
        fullGeneration[lineupCount]=set;
    }
    public int getGEN_SIZE(){return GEN_SIZE;}
    public int getCutoff(){return optimizationCutoff;}
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
        double totalFitness=0.0;
        for (int i=0;i<GEN_SIZE;i++)
            totalFitness+=fullGeneration[i].getFitness();

        for (int i=0;i<GEN_SIZE;i++) {
            fullGeneration[i].setFitnessPercent(totalFitness, previous);
            previous = fullGeneration[i].getFitnessPercent();
        }
    }
    public void evolveGeneration() {
            int lineupCounter = 0;
            nextGeneration=new lineupSet[GEN_SIZE];

            //Take best Lineup First (take the best two?)
            if (ELITISM == true) {
                nextGeneration[lineupCounter] = fullGeneration[lineupCounter];
                lineupCounter++;
            }

            while (lineupCounter != (GEN_SIZE)) {

                //Selection Step
                double randSelect1 = rand.nextDouble(); //Parent 1
                double randSelect2 = rand.nextDouble(); //Parent 2

                int parent1 = 0;
                boolean parent1Check = false;
                int parent2 = 0;
                boolean parent2Check = false;

                int i=0;
                while((!parent1Check) || (!parent2Check)) //increment and find first lineup above selection point, take the 1 right before
                { double fitnessTest=fullGeneration[i].getFitnessPercent();
                    if (randSelect1 < fitnessTest && !parent1Check) {
                        parent1 = i;
                        parent1Check = true;
                    }
                    if (randSelect2 < fitnessTest && !parent2Check) {
                        parent2 = i;
                        parent2Check = true;
                    }
                    i++;
                }


                nextGeneration[lineupCounter]=new lineupSet();
                //Crossover
                i=0;
               while (nextGeneration[lineupCounter].getSetSize()!=9){  //increment through the entire lineup

                    int randPick = rand.nextInt(2); //50/50 chance to swap the current player if they fit

                    if (randPick == 1) {
                        if (nextGeneration[lineupCounter].playerViable(fullGeneration[parent2].sortedLineup[i]))  //if player is viable in new gen, add to new lineup
                            nextGeneration[lineupCounter].addPlayer(fullGeneration[parent2].sortedLineup[i]);
                        else if (nextGeneration[lineupCounter].playerViable(fullGeneration[parent1].sortedLineup[i])) //If chosen player wasnt viable, is the other lineup?
                            nextGeneration[lineupCounter].addPlayer(fullGeneration[parent1].sortedLineup[i]);
                        else                                                                 //If neither matches, try and find a fit
                        {
                            nextGeneration[lineupCounter].clearLineUp();
                            createLineup(lineupCounter);
                            nextGeneration[lineupCounter]=set;
                            //System.out.println("\nLineup " +lineupCounter+ " was mutated\n");
                        }
                    } else if (randPick == 0) {
                        if (nextGeneration[lineupCounter].playerViable(fullGeneration[parent1].sortedLineup[i]))  //if player is viable in new gen, add to new lineup
                            nextGeneration[lineupCounter].addPlayer(fullGeneration[parent1].sortedLineup[i]);
                        else if (nextGeneration[lineupCounter].playerViable(fullGeneration[parent2].sortedLineup[i])) //If chosen player wasnt viable, is the other lineup?
                            nextGeneration[lineupCounter].addPlayer(fullGeneration[parent2].sortedLineup[i]);
                        else                                                                 //If neither matches, try and find a fit
                        {
                            nextGeneration[lineupCounter].clearLineUp();
                            createLineup(lineupCounter);
                            nextGeneration[lineupCounter]=set;
                            //System.out.println("\nLineup " +lineupCounter+ " was mutated\n");
                        }
                    }
                    i++;
                }

                nextGeneration[lineupCounter].sortLineup(); //sort into normal order
                nextGeneration[lineupCounter].findFitness();
                lineupCounter++;
            }
            fullGeneration=nextGeneration;
         }
}

