public class GeneticsCode {

    public void runGenetic(csvParser parser)
    {
        geneticLineup genetic=new geneticLineup();

        while (!genetic.generationFull())
        {
                genetic.createLineup(parser);
        }

        genetic.sortGeneration(genetic.fullGeneration,0,genetic.getGEN_SIZE()-1);
         for(int i=0;i<genetic.getGEN_SIZE();i++)
           System.out.println("Lineup "+(i+1)+" fitness: "+ String.format("%.2f", genetic.fullGeneration[i].getFitness()));
    }
}
