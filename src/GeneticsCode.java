import java.text.DecimalFormat;

public class GeneticsCode {

    public void runGenetic(csvParser parser)
    {
        geneticLineup genetic=new geneticLineup(parser);

        int genCount=1;
        int convergenceCheck=0;

        while (!genetic.generationFull())  //populate
        {
                genetic.createLineup();
        }

        while(genCount<=genetic.getLIFETIME()) {
            genetic.sortGeneration(genetic.fullGeneration, 0, genetic.getGEN_SIZE() - 1);//sort generation by fitness
            genetic.calculateFitnessRate();  //find total Fitness for generation
            genetic.evolveGeneration(); //Create next generation
            System.out.println("The best Lineup for Generation "+ (genCount) + " is: ");
            genetic.fullGeneration[0].printLineup();
            DecimalFormat df= new DecimalFormat("0.00");
            System.out.println("\nProjected Points:" + df.format(genetic.fullGeneration[0].getFitness()));
            System.out.println("========================================================");
            genCount++;


        }
    }
}
