/**
 * It is the class that includes epidemic characteristics.
 */
public class Epidemic {
    private double spreadingFactor = 0.5;
    private double mortalityRate = 0.1;
    private String name = "";

    public Epidemic(){

    }

    public Epidemic(String name, double spreadingFactor, double mortalityRate){
        this.spreadingFactor = spreadingFactor;
        this.mortalityRate = mortalityRate;
        this.name = name;
    }

    public double getSpreadingFactor(){
        return spreadingFactor;
    }

    public double getMortalityRate(){
        return mortalityRate;
    }

    public String getEpidemicName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setRate(double rate){
        mortalityRate = rate;
    }

    public void setSpread(double spread){
        spreadingFactor = spread;
    }

    public String toString(){
        return name + ", " + spreadingFactor +", " + mortalityRate;
    }
}
