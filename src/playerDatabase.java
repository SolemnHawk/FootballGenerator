import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class playerDatabase {


    private ArrayList<Player> playerSet=new ArrayList<Player>();
    private int databaseSize;
    private ArrayList<ArrayList<Player>> sortedSet=new ArrayList<>();

    public playerDatabase(){
    databaseSize=0;
    }
    public boolean findPlayer(String playerName){
        for (int i=0;i<playerSet.size();i++)
        {
            if (playerSet.get(i).getPlayerName().contains(playerName));
                return true;
        }
        return false;
    }

    public void addPlayer(Player newplayer){
        playerSet.add(newplayer);
        databaseSize++;
    }
    public void updatePlayerProj(String playerName, float playerProj) {
        for(int i=0;i<playerSet.size();i++)  //if multiple projections, get average
        {
             if(playerSet.get(i).getPlayerName().contains(playerName))
            {
                playerSet.get(i).setProjections(playerProj);
            }
        }
    }

    public void updatePlayerSalary(String playerName, int playerSalary) {
        for (int i = 0; i < playerSet.size(); i++) {
            if (playerSet.get(i).getPlayerName().contains(playerName)) {
                playerSet.get(i).setSalary(playerSalary);

            }
        }
    }
    public void printPlayers() {
        System.out.println("QB's");
        for (int i=0;i<sortedSet.get(0).size();i++)
        {
            System.out.println(sortedSet.get(0).get(i).getPlayerName());
        }
        System.out.println("\n\n\nRB's");
        for (int i=0;i<sortedSet.get(1).size();i++)
        {
            System.out.println(sortedSet.get(1).get(i).getPlayerName());
        }
        System.out.println("\n\n\nWR's");
        for (int i=0;i<sortedSet.get(2).size();i++)
        {
            System.out.println(sortedSet.get(2).get(i).getPlayerName());
        }
        System.out.println("\n\n\nTE's");
        for (int i=0;i<sortedSet.get(3).size();i++)
        {
            System.out.println(sortedSet.get(3).get(i).getPlayerName());
        }
        System.out.println("\n\n\nDEF's");
        for (int i=0;i<sortedSet.get(4).size();i++)
        {
            System.out.println(sortedSet.get(4).get(i).getPlayerName());
        }
    }

    public void sortDatabase(){
         ArrayList<Player> qbList=new ArrayList<Player>();
         ArrayList<Player> rbList=new ArrayList<Player>();
         ArrayList<Player> wrList=new ArrayList<Player>();
         ArrayList<Player> teList=new ArrayList<Player>();
         ArrayList<Player> defList=new ArrayList<Player>();

        for (int i=0;i<playerSet.size();i++)
        {
            if (playerSet.get(i).getPlayerPos().equals("QB"))
                qbList.add(playerSet.get(i));
            else if( playerSet.get(i).getPlayerPos().equals("RB"))
                rbList.add(playerSet.get(i));
            else if( playerSet.get(i).getPlayerPos().equals("WR"))
                wrList.add(playerSet.get(i));
            else if( playerSet.get(i).getPlayerPos().equals("TE"))
                teList.add(playerSet.get(i));
            else if( playerSet.get(i).getPlayerPos().equals("D"))
                defList.add(playerSet.get(i));
        }
        sortedSet.add(qbList);
        sortedSet.add(rbList);
        sortedSet.add(wrList);
        sortedSet.add(teList);
        sortedSet.add(defList);
    }

    public ArrayList<ArrayList<Player>> getSortedDatabase(){
        return sortedSet;
    }

    public List<Player> getDatabase(){
        return playerSet;
    }
}
