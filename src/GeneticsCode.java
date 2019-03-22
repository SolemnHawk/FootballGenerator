public class GeneticsCode {

    public void runGenetic(csvParser parser)
    {
        geneticLineup genetic=new geneticLineup();

        while (!genetic.generationFull())
        {
                genetic.createLineup(parser);
        }

        for(int i=0;i<genetic.getGEN_SIZE();i++)
            System.out.println("Generation "+(i+1)+" fitness: "+ genetic.fullGeneration[i].getFitness());
    }
}
