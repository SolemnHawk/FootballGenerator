public class GeneticsCode {

    private boolean ELITISM=true; // keep best lineup into future
    private int GEN_SIZE=20; //# of lineups per generation
    private int LIFETIME=100; //# of Gen to run

    public boolean getElite()
    {return ELITISM;
    }

    public int getGenSize(){
        return GEN_SIZE;
    }

    public int getLifetime(){
        return LIFETIME;
    }


}
