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
    public int findPlayer(String playerName){

        for (int i=0;i<playerSet.size();i++)
        {
            String name=playerSet.get(i).getPlayerName();
            if (name.equals(playerName))
                return i;
        }
        return -1;
    }

    public void addPlayer(Player newplayer){
        playerSet.add(newplayer);
        databaseSize++;
    }
    public void updatePlayerProj(float playerProj, int playerPositon) {
        playerSet.get(playerPositon).setProjections(playerProj);
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
            System.out.println(sortedSet.get(0).get(i).getPlayerName()+ " "+ sortedSet.get(0).get(i).getProjection());
        }
        System.out.println("\n\n\nRB's");
        for (int i=0;i<sortedSet.get(1).size();i++)
        {
            System.out.println(sortedSet.get(1).get(i).getPlayerName()+ " "+ sortedSet.get(1).get(i).getProjection());
        }
        System.out.println("\n\n\nWR's");
        for (int i=0;i<sortedSet.get(2).size();i++)
        {
            System.out.println(sortedSet.get(2).get(i).getPlayerName()+ " "+ sortedSet.get(2).get(i).getProjection());
        }
        System.out.println("\n\n\nTE's");
        for (int i=0;i<sortedSet.get(3).size();i++)
        {
            System.out.println(sortedSet.get(3).get(i).getPlayerName()+ " "+ sortedSet.get(3).get(i).getProjection());
        }
        System.out.println("\n\n\nDEF's");
        for (int i=0;i<sortedSet.get(4).size();i++)
        {
            System.out.println(sortedSet.get(4).get(i).getPlayerName()+ " "+ sortedSet.get(4).get(i).getProjection());
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
    public void trimDatabase(){
        for (int i=0;i<playerSet.size();i++)
        {
            if (playerSet.get(i).getProjection()<1.0) {
                playerSet.remove(i);
                i = i - 1;
            }
        }
    }

    public ArrayList<ArrayList<Player>> getSortedDatabase(){
        return sortedSet;
    }

    public List<Player> getDatabase(){
        return playerSet;
    }
}
