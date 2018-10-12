import java.util.ArrayList;
import java.util.List;

public class lineupSet {

    int[] posCounter=new int[6];
    //posCounter-> |qbCounter|rbCounter|wrCounter|teCounter|defCounter|flexCounter|

    List<Player> lineUp=new ArrayList<>();
    List<Player> sortedLineup=new ArrayList<>();
    private int lineupCost=0;
    private double lineupProj=0.0;

    private int SALARY_CAP=60000;

    public lineupSet(){

    }
    public boolean playerViable(Player newPlayer) {
        //If player already is in lineup, do not add
        if (lineUp.size() != 0) {
            for (int i = 0; i < lineUp.size(); i++)
                if (lineUp.get(i).equals(newPlayer))
                    return false;
        }
        //Check salary cost limits
        if ((lineupCost + newPlayer.getPlayerSalary() > SALARY_CAP))
            return false;

        String playerPos = newPlayer.getPlayerPos();

        if (playerPos.equals("QB")) {
            if (posCounter[0] == 1)    //if QB is already in lineup, dont add
                return false;

        }
        else if(playerPos.equals("RB")) {
            if (posCounter[1] >= 2 && posCounter[5]==1) //if RB is full, check Flex, if full, dont add
            {
                return false;
            }
        }
        else if(playerPos.equals("WR")) {
            if (posCounter[2] >= 3 && posCounter[5]==1) //if WR is full, check Flex, if full, dont add
            {
                return false;
            }

        }
        else if (playerPos.equals("TE")) {
            if (posCounter[3] == 1)    //if TE is already in lineup, dont add
                return false;
        }
        else if (playerPos.equals("D")) {
            if (posCounter[4] == 1)    //if DEF is already in lineup, dont add
                return false;
        }

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
        lineUp.size();
        lineupCost=lineupCost+newPlayer.getPlayerSalary();
        lineupProj=lineupProj+newPlayer.getProjection();
    }
    public void sortLineup(){ //sort list of players into printable lineup
        int qb=0;
        int rb1=0;
        int rb2=0;
        int wr1=0;
        int wr2=0;
        int wr3=0;
        int te=0;
        int flex=0;
        Player tempPlayer;
        lineUp.size();
        Player tempFlex=lineUp.get(1);

        //find QB position, add to SortedLineup, remove from tempLineup
        while (!lineUp.get(qb).getPlayerPos().equals("QB"))
            qb++;
        sortedLineup.add(lineUp.get(qb));
        lineUp.remove(qb);

        //find RB positions, sort them by salary, add to SortedLineup, remove from tempLineup
        while (!lineUp.get(rb1).getPlayerPos().equals("RB"))
            rb1++;
        sortedLineup.add(lineUp.get(rb1));
        lineUp.remove(rb1);
        while (!lineUp.get(rb2).getPlayerPos().equals("RB"))
            rb2++;
        sortedLineup.add(lineUp.get(rb2));
        lineUp.remove(rb2);
        
        //sort 2 RB's by salary
        tempPlayer=sortedLineup.get(1);
        if(sortedLineup.get(1).getPlayerSalary()<sortedLineup.get(2).getPlayerSalary()){
            sortedLineup.remove(1);
            sortedLineup.add(tempPlayer);
        }
        //check if flex is an RB
        if (posCounter[1]==3)
        {
            while (!lineUp.get(flex).getPlayerPos().equals("RB"))
                flex++;
            tempFlex=lineUp.get(flex);
            lineUp.remove(flex);
        }

        //find WR positions, sort them by salary, add to SortedLineup, remove from tempLineup
        while (!lineUp.get(wr1).getPlayerPos().equals("WR"))
            wr1++;
        Player tempPlayer1=lineUp.get(wr1);
        lineUp.remove(wr1);
        while (!lineUp.get(wr2).getPlayerPos().equals("WR"))
            wr2++;
        Player tempPlayer2=lineUp.get(wr2);
        lineUp.remove(wr2);
        while (!lineUp.get(wr3).getPlayerPos().equals("WR"))
            wr3++;
        Player tempPlayer3=lineUp.get(wr3);
        lineUp.remove(wr3);

        //sort 3 wr's by salary

        if(tempPlayer1.getPlayerSalary()>=tempPlayer2.getPlayerSalary() && tempPlayer1.getPlayerSalary()>=tempPlayer3.getPlayerSalary()) {
            sortedLineup.add(tempPlayer1);
            if(tempPlayer2.getPlayerSalary()>tempPlayer3.getPlayerSalary()) {
                sortedLineup.add(tempPlayer2);
                sortedLineup.add(tempPlayer3);
            }
            else {
                sortedLineup.add(tempPlayer3);
                sortedLineup.add(tempPlayer2);
            }
        }
        else if(tempPlayer2.getPlayerSalary()>=tempPlayer1.getPlayerSalary() && tempPlayer2.getPlayerSalary()>=tempPlayer3.getPlayerSalary()) {
            sortedLineup.add(tempPlayer2);
            if(tempPlayer1.getPlayerSalary()>tempPlayer3.getPlayerSalary()) {
                sortedLineup.add(tempPlayer1);
                sortedLineup.add(tempPlayer3);
            }
            else {
                sortedLineup.add(tempPlayer3);
                sortedLineup.add(tempPlayer1);
            }
        }
        else if(tempPlayer3.getPlayerSalary()>=tempPlayer1.getPlayerSalary() && tempPlayer3.getPlayerSalary()>=tempPlayer2.getPlayerSalary()) {
            sortedLineup.add(tempPlayer3);
            if(tempPlayer1.getPlayerSalary()>tempPlayer2.getPlayerSalary()) {
                sortedLineup.add(tempPlayer1);
                sortedLineup.add(tempPlayer2);
            }
            else {
                sortedLineup.add(tempPlayer2);
                sortedLineup.add(tempPlayer1);
            }
        }
        //check if flex is an wr
        if (posCounter[2]==4)
        {
            while (!lineUp.get(flex).getPlayerPos().equals("WR"))
                flex++;
            tempFlex=lineUp.get(flex);
            lineUp.remove(flex);
        }

        //Find TE and add to sorted lineup
        while (!lineUp.get(te).getPlayerPos().equals("TE"))
            te++;
        sortedLineup.add(lineUp.get(te));
        lineUp.remove(te);
        
        //Add FLEX position to lineup
        sortedLineup.add(tempFlex);
        
        //Add DEF to sorted lineup
        sortedLineup.add(lineUp.get(0));
    }
    public List<Player> getlineUp() {
        return sortedLineup;
    }
}
