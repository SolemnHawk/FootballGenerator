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

    private final int SALARY_CAP=60000;

    public lineupSet(){

    }
    public boolean playerViable(Player newPlayer) {

        if(viableCount>200)//infinite loop break
        {
            for(int i=0;i<lineUp.size();i++)
                System.out.println(lineUp.get(i).getPlayerName());
            System.out.println("I'm Stuck in a loop");
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
            if ((SALARY_CAP-lineupCost+newPlayer.getPlayerSalary()/(9-lineUp.size()))<4600) {
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
        lineupCost=lineupCost+newPlayer.getPlayerSalary();
        lineupProj=lineupProj+newPlayer.getProjection();
    }
    public void sortLineup(){ //sort list of players into printable lineup

        lineUp.size();

        sortedLineup[0]=lineUp.get(2);

        //check which RB has higher salary to list first
        if(lineUp.get(3).getPlayerSalary() >= lineUp.get(4).getPlayerSalary()) {
            sortedLineup[1] = lineUp.get(3);
            sortedLineup[2] = lineUp.get(4);
        }
        else
        {
            sortedLineup[1]=lineUp.get(4);
            sortedLineup[2]=lineUp.get(3);
        }

        //WR salary check for sortting
            double a=lineUp.get(5).getPlayerSalary();
            double b=lineUp.get(6).getPlayerSalary();
            double c=lineUp.get(7).getPlayerSalary();
            
        if(a>b&&a>c)
        {
            sortedLineup[3]=lineUp.get(5);
            if(b>c) {
                sortedLineup[4] = lineUp.get(6);
                sortedLineup[5] = lineUp.get(7);
            }
            else {
                sortedLineup[4] = lineUp.get(7);
                sortedLineup[5] = lineUp.get(6);
            }
        }
        else if(b>a&&b>c)
        {
            sortedLineup[3]=lineUp.get(6);
            if(a>c) {
                sortedLineup[4] = lineUp.get(5);
                sortedLineup[5] = lineUp.get(7);
            }
            else {
                sortedLineup[4] = lineUp.get(7);
                sortedLineup[5] = lineUp.get(5);
            }
        }
        else
        {
            sortedLineup[3]=lineUp.get(7);
            if(a>b) {
                sortedLineup[4] = lineUp.get(5);
                sortedLineup[5] = lineUp.get(6);
            }
            else {
                sortedLineup[4] = lineUp.get(6);
                sortedLineup[5] = lineUp.get(5);
            }
        }
        
        sortedLineup[6]=lineUp.get(1);
        sortedLineup[7]=lineUp.get(8);
        sortedLineup[8]=lineUp.get(0);

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
    public Player[] getlineUp() {
        return sortedLineup;
    }
}
