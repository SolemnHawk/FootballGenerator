import java.util.ArrayList;
import java.util.List;

public class lineupSet {

    int[] posCounter=new int[6];
    //posCounter-> |qbCounter|rbCounter|wrCounter|teCounter|defCounter|flexCounter|

    List<Player> lineUp=new ArrayList<>();
    Player[] sortedLineup=new Player[9];
    private int lineupCost=0;
    private double lineupProj=0.0;
    private double fitness=0.0;
    int viableCount=0;
    private double fitnessPercent=0.0;
    private int setSize=0;


    private final int SALARY_CAP=60000;

    public lineupSet(){

    }
    public int getSetSize(){return setSize;}
    public boolean playerViable(Player newPlayer) {

        if(viableCount>300)//infinite loop break
        {
            clearLineUp();
            viableCount = 0;
            System.out.println("I was stuck in a loop");
            return false;
        }
        //If player already is in lineup, do not add
        if (lineUp.size() != 0) {
            for (int i = 0; i < lineUp.size(); i++)
                if (lineUp.get(i).equals(newPlayer)) {
                    viableCount++;
                    return false;
                }
        }

        //Dont select players that wont score
        if ((newPlayer.getProjection())<1) {
            viableCount++;
            return false;
        }
        //Check salary cost limits

        //Player wont break salaray cap
        if (lineupCost + newPlayer.getPlayerSalary() > SALARY_CAP) {
            viableCount++;
            return false;
        }

        //Player will not prevent new additions from breaking cap
        if(lineUp.size()!=9)
            if ((SALARY_CAP-lineupCost+newPlayer.getPlayerSalary())<4500) {
                viableCount++;
                return false;
            }

        String playerPos = newPlayer.getPlayerPos();

        if(playerPos.equals("RB")) {
            if (posCounter[1] >= 2 && posCounter[5]==1) //if RB is full, check Flex, if full, dont add
            {
                viableCount++;
                return false;
            }
        }
        else if(playerPos.equals("WR")) {
            if (posCounter[2] >= 3 && posCounter[5]==1) //if WR is full, check Flex, if full, dont add
            {
                viableCount++;
                return false;
            }

        }

        viableCount=0;
        return true;
    }
    public void addPlayer(Player newPlayer){
        //Check for player position limits
        String playerPos=newPlayer.getPlayerPos();

        if (playerPos.equals("QB"))
        {
            lineUp.add(newPlayer);
            posCounter[0]++;
        }
        else if(playerPos.equals("RB"))
        {
            if (posCounter[1]>=2) //if RB is full, check Flex, if full, dont add
            {
                lineUp.add(newPlayer);
                posCounter[5]++; //mark flex position taken
                posCounter[1]++;//mark flex is a RB
            }
            else {
                lineUp.add(newPlayer);
                posCounter[1]++; //mark RB has added a player
            }
        }
        else if(playerPos.equals("WR"))
        {
            if (posCounter[2]>=3) //if WR is full, check Flex, if full, dont add
            {
                lineUp.add(newPlayer);
                posCounter[5]++; //mark flex position taken
                posCounter[2]++;//mark flex is another WR
            }
            else {
                lineUp.add(newPlayer);
                posCounter[2]++; //mark WR has added a player
            }
        }
        else if (playerPos.equals("TE"))
        {
            lineUp.add(newPlayer);
            posCounter[3]++;
        }
        else if (playerPos.equals("D"))
        {
            lineUp.add(newPlayer);
            posCounter[4]++;
        }
        setSize++;
        lineupCost=lineupCost+newPlayer.getPlayerSalary();
        lineupProj=lineupProj+newPlayer.getProjection();
    }
    public void sortLineup(){ //sort list of players into printable lineup

        Player temp;
        int rbCount=0;
        int wrCount=0;

        for(int i=0;i<9;i++)
        {
            if(lineUp.get(i).getPlayerPos().equals("QB"))
                sortedLineup[0]=lineUp.get(i);
            if(lineUp.get(i).getPlayerPos().equals("RB") && rbCount==0) {
                sortedLineup[1] = lineUp.get(i);
                rbCount++;
            }
            else if(lineUp.get(i).getPlayerPos().equals("RB") && rbCount==1) {
                sortedLineup[2] = lineUp.get(i);
                rbCount++;
            }
            else if(lineUp.get(i).getPlayerPos().equals("RB") && rbCount==2) {
                sortedLineup[7] = lineUp.get(i);
                rbCount++;
            }
            if(lineUp.get(i).getPlayerPos().equals("WR") && wrCount==0) {
                sortedLineup[3] = lineUp.get(i);
                wrCount++;
            }
            else if(lineUp.get(i).getPlayerPos().equals("WR") && wrCount==1) {
                sortedLineup[4] = lineUp.get(i);
                wrCount++;
            }
            else if(lineUp.get(i).getPlayerPos().equals("WR") && wrCount==2) {
                sortedLineup[5] = lineUp.get(i);
                wrCount++;
            }
            else if(lineUp.get(i).getPlayerPos().equals("WR") && wrCount==3) {
                sortedLineup[7] = lineUp.get(i);
                wrCount++;
            }
            if(lineUp.get(i).getPlayerPos().equals("TE"))
                sortedLineup[6]=lineUp.get(i);
            if(lineUp.get(i).getPlayerPos().equals("D"))
                sortedLineup[8]=lineUp.get(i);

        }
        //check which RB has higher salary to list first
        if(sortedLineup[2].getPlayerSalary() > sortedLineup[1].getPlayerSalary()) {
            temp=sortedLineup[1];
            sortedLineup[1] = sortedLineup[2];
            sortedLineup[2] = temp;
        }

        //WR salary check for sortting
        double a=sortedLineup[3].getPlayerSalary();
        double b=sortedLineup[4].getPlayerSalary();
        double c=sortedLineup[5].getPlayerSalary();

        if(b>a&&b>c)
        {
            temp=sortedLineup[3];
            sortedLineup[3] = sortedLineup[4];
            sortedLineup[4]=temp;
            if(c>a) {
                temp=sortedLineup[4];
                sortedLineup[4] = sortedLineup[5];
                sortedLineup[5] = temp;
            }
        }
        else
        {
            temp=sortedLineup[3];
            sortedLineup[3] = sortedLineup[5];
            sortedLineup[5]=temp;
            if(b>a) {
                temp=sortedLineup[4];
                sortedLineup[4] = sortedLineup[5];
                sortedLineup[5]=temp;
            }

        }

    }
    public void findFitness(){
        for (int i=0;i<lineUp.size();i++)
            fitness=fitness+lineUp.get(i).getProjection();
    }
    public double getFitness(){
        return fitness;
    }
    public void printLineup(){
        System.out.println("QB: "+sortedLineup[0].getPlayerName());
        System.out.println("RB1: "+sortedLineup[1].getPlayerName());
        System.out.println("RB2: "+sortedLineup[2].getPlayerName());
        System.out.println("WR1: "+sortedLineup[3].getPlayerName());
        System.out.println("WR2: "+sortedLineup[4].getPlayerName());
        System.out.println("WR3: "+sortedLineup[5].getPlayerName());
        System.out.println("TE: "+sortedLineup[6].getPlayerName());
        System.out.println("FLEX: "+sortedLineup[7].getPlayerName());
        System.out.println("DEF: "+sortedLineup[8].getPlayerName());
    }
    public int getLineupCost(){
        return lineupCost;
    }
    public void setFitnessPercent(double total, double previous){
        fitnessPercent=fitness/total;
        fitnessPercent=fitnessPercent+previous;
    }
    public double getFitnessPercent(){
        return fitnessPercent;
    }
    public void clearLineUp(){
        posCounter=new int[6];
        lineUp.clear();
        fitness=0.0;
        fitnessPercent=0.0;
        sortedLineup=new Player[9];
        lineupCost=0;
        lineupProj=0.0;
        setSize=0;
        viableCount=0;


    }


}