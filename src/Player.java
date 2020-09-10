public class Player {

    private String playerName;
    private String playerTeam;
    private String playerPos;
    private int playerSalary;
    private float projPoints=0;

    public Player(String name, String team, String position, int salary, float projection)
    {
        playerName=name;
        playerTeam=team;
        playerPos=position;
        playerSalary=salary;
        projPoints=projection;
    }

    public void setProjections(float newProj){
        if (projPoints==0)
            projPoints=newProj;
        else
            projPoints=(projPoints+newProj)/2;
    }

    public int getPlayerSalary(){
        return playerSalary;
    }

    public void setSalary(int salary){
        playerSalary=salary;
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
