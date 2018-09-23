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

    public void setBaseDatabase(){
        String filePath= "src/resources/Football-Database.csv";
        String playerName="";
        String playerTeam="";
        String playerPos="";
        String playerSalary;
        String playerProj;
        float projection=0;
        int salary=0;
        int count=1;
        int nameSpot=0;
        int pointSpot=0;
        int teamSpot=0;
        int posSpot=0;
        int salarySpot=0;
        int counter=1;
        try
        {
            FileReader filereader=new FileReader(filePath);
            CSVReader reader=new CSVReaderBuilder(filereader).build();
            List<String[]> allData = new ArrayList<>();
            String[] line;
            line=reader.readNext();
            allData.add(line);

            for(String[] row:allData) {
                for (String cell : row) {
                    if (cell.equals("Player"))
                        nameSpot = counter;
                    else if (cell.equals("Projection"))
                        pointSpot = counter;
                    else if(cell.equals("Team"))
                        teamSpot=counter;
                    else if(cell.equals("Position"))
                        posSpot=counter;
                    else if(cell.equals("Salary"))
                        salarySpot=counter;
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
                    else if(count==salarySpot) {
                        playerSalary = cell;
                        salary = Integer.parseInt(playerSalary);
                    }
                    else if(count==teamSpot){
                        playerTeam=cell;
                    }
                    else if(count==posSpot){
                        playerPos=cell;
                    }
                    count++;
                }
                Player newPlayer=new Player(playerName,playerTeam,playerPos,salary,projection);
                database.addPlayer(newPlayer);
                count=1;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
    public void parseSalary(String fileName){
        String filePath= "src/resources/"+fileName;
        String playerName="";
        String playerSalary="";
        int salary=0;
        int count=1;
        int nameSpot=0;
        int salarySpot=0;
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
                    if (cell.equals("Nickname"))
                        nameSpot = counter;
                    else if (cell.equals("Salary"))
                        salarySpot = counter;
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
                    else if(count==salarySpot) {
                        playerSalary = cell;
                        salary= Integer.parseInt(playerSalary);
                    }
                    count++;
                }
                if(playerName.equals(""))
                {}
                else if(database.findPlayer(playerName))
                    database.updatePlayerSalary(playerName,salary);
                else
                    System.out.println(playerName+" Not found from salary");
                count=1;
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
                else
                    System.out.println(playerName+" not found in projections");
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


            String[] header = {"Name", "Team", "Position","Salary","Projection"};
            csvWriter.writeNext(header);
            List<Player> players = database.getDatabase();
            for (int i = 0; i < players.size(); i++) {
                String[] entries = {players.get(i).getPlayerName(),players.get(i).getPlayerTeam(),players.get(i).getPlayerPos(),"0","0"};
                csvWriter.writeNext(entries);
            }
        }
        catch(Exception e){
            System.err.println("Could not create file");
        }
    }
}
