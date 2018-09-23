import java.util.ArrayList;
import java.util.List;

public class playerDatabase {


    private List<Player> playerSet=new ArrayList<Player>();
    private int databaseSize;

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
    public void updatePlayerProj(String playerName, float playerProj)
    {
        for(int i=0;i<playerSet.size();i++)
        {
             if(playerSet.get(i).getPlayerName().contains(playerName))
            {
                playerSet.get(i).setProjections(playerProj);
            }
        }
    }
    public void printPlayers()
    {
        for (int i=0;i<playerSet.size();i++)
        {
            System.out.println(playerSet.get(i).getPlayerName()+"\t"+playerSet.get(i).getPlayerPos()+"\t"+playerSet.get(i).getPlayerTeam()+"\t"+playerSet.get(i).getPlayerSalary()+"\t"+playerSet.get(i).getProjection());
        }
    }
    public List<Player> getDatabase(){
        return playerSet;
    }
}
