import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class csvParser {
    playerDatabase database=new playerDatabase();
    public void parseSalary(String fileName){
        String filePath= "src/resources/"+fileName;
        String playerName="";
        String playerTeam="";
        String playerPos="";
        String playerSalary="";
        int count=1;
        try
        {
            FileReader filereader=new FileReader(filePath);
            CSVReader reader=new CSVReaderBuilder(filereader).withSkipLines(1).build();
            List<String[]> allData=reader.readAll();

            for(String[] row:allData){
                for (String cell:row){
                    if (count==2)
                        playerPos=cell;
                    else if(count==4)
                        playerName=cell;
                    else if(count==8)
                        playerSalary=cell;
                    else if(count==10)
                        playerTeam=cell;
                    count++;
                }
                Player newplayer=new Player(playerName,playerTeam,playerPos,playerSalary);
                database.addPlayer(newplayer);
               // System.out.print(newplayer.getPlayerName()+"\t"+newplayer.getPlayerPos()+"\t"+newplayer.getPlayerTeam()+"\t"+newplayer.getPlayerSalary());
                count=1;
                //System.out.println();
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void parseProjection(String fileName){
        String filePath= "src/resources/"+fileName;
        String playerName="";
        String playerProj="";
        float projection=0;
        int count=1;
        int nameSpot=0;
        int pointSpot=0;
        int counter=1;
        try
        {
            FileReader filereader=new FileReader(filePath);
            CSVReader reader=new CSVReaderBuilder(filereader).withSkipLines(0).build();
            List<String[]> allData = new ArrayList<>();
            String[] line;
            int lineskip=0;
            line=reader.readNext();
            allData.add(line);

            for(String[] row:allData) {
                for (String cell : row) {
                    if (cell.equals("Player"))
                        nameSpot = counter;
                    else if (cell.equals("FPTS"))
                        pointSpot = counter;
                    counter++;
                }
            }
            line=reader.readNext();
            allData.clear();
            while ((line = reader.readNext()) != null) {
                allData.add(line);
            }

            for(String[] row:allData){
                for (String cell:row){
                    if (count==nameSpot) {
                        playerName = cell;
                    }
                    else if(count==pointSpot) {
                        playerProj = cell;
                        projection=Float.parseFloat(playerProj);
                    }
                    count++;
                }
                if(playerName.equals(""))
                {}
                else if(database.findPlayer(playerName))
                    database.updatePlayerProj(playerName,projection);
                count=1;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void printDatabase()
    {
     database.printPlayers();
    }
}
