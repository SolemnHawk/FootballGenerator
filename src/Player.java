public class Player {

    private String playerName;
    private String playerTeam;
    private String playerPos;
    private int playerSalary;
    private float projPoints;

    public Player(String name, String team, String position, String salary)
    {
        playerName=name;
        playerTeam=team;
        playerPos=position;
        playerSalary=Integer.parseInt(salary);
    }

    public void setProjections(float newProj){
        projPoints=newProj;
    }

    public int getPlayerSalary(){
        return playerSalary;
    }

    public float getProjection(){
        return projPoints;
    }

    public String getPlayerName(){
        return playerName;
    }

    public String getPlayerPos(){
        return playerPos;
    }

    public String getPlayerTeam(){
        return playerTeam;
    }

}
