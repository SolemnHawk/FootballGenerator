import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

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
                Player newplayer=new Player(playerName,playerTeam,playerPos);
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
    public void saveDatabase() {

        try {
            FileOutputStream fos = new FileOutputStream("src/resources/Football-Database.csv");
            OutputStreamWriter osw=new OutputStreamWriter(fos);
            CSVWriter csvWriter = new CSVWriter(osw);


            String[] header = {"Name", "Team", "Position"};
            csvWriter.writeNext(header);
            List<Player> players = database.getDatabase();
            for (int i = 0; i < players.size(); i++) {
                String[] entries = {players.get(i).getPlayerName(),players.get(i).getPlayerTeam(),players.get(i).getPlayerPos()};
                csvWriter.writeNext(entries);
            }
        }
        catch(Exception e){
            System.err.println("Could not create file");
        }
    }
}
