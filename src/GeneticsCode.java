import java.text.DecimalFormat;

public class GeneticsCode {

    public lineupSet runGenetic(csvParser parser) {

        geneticLineup genetic = new geneticLineup(parser);

        int genCount = 1;
        int i = 0;
        int genOptimaCount=1;
        double currentOptima=0.0;
        while (i < genetic.getGEN_SIZE())  //populate
        {
            genetic.createLineup(i);
            genetic.fullGeneration[i].findFitness();
            i++;
        }

       while (genCount <= genetic.getLIFETIME()) {
            genetic.sortGeneration(genetic.fullGeneration, 0, genetic.getGEN_SIZE() - 1);//sort generation by fitness
            genetic.calculateFitnessRate();  //find total Fitness for generation
           if(genetic.fullGeneration[0].getFitness()==currentOptima) {
               genOptimaCount++;

               if(genOptimaCount==genetic.getCutoff()) {
                   System.out.println("Genetic run ended at "+ genOptimaCount+"th generation due to lack of improvement.");
                   break;
               }
           }
           else
           {
               currentOptima=genetic.fullGeneration[0].getFitness();
               genOptimaCount=1;
           }
//            System.out.println("The best Lineup for Generation " + (genCount) + " is: ");
//            genetic.fullGeneration[0].printLineup();
//            DecimalFormat df = new DecimalFormat("0.00");
//            System.out.println("\nProjected Points:" + df.format(genetic.fullGeneration[0].getFitness()));
//            System.out.println("========================================================");

            genCount++;
            genetic.evolveGeneration(); //Create next generation
        }

       return genetic.fullGeneration[0];
    }
}
