import java.awt.*;

/**
 * It is the class of persons. Uses the mediator class.
 */
public class Person {
    private final int speed;
    private final int meeting;
    private final int socialDistance;
    private final double mask_state;
    private Color infected;
    private Rectangle space;
    private int lifetime;

    public Person(int sp, int meet, int distance, double mask, Rectangle person, Color infected, int lifetime){
        speed = sp;
        meeting = meet;
        socialDistance = distance;
        mask_state = mask;
        space = person;
        this.infected= infected;
        this.lifetime = lifetime;
    }

    public int getSpeed(){
        return speed;
    }

    public int getMeetingTime(){
        return meeting;
    }

    public int getSocialDistance(){
        return socialDistance;
    }

    public double getMaskState(){
        return mask_state;
    }

    public Color getInfected(){
        return infected;
    }

    public void setInfected(Color infected){
        this.infected = infected;
    }

    public void setLifetime(int life){
        lifetime = life;
    }

    public int getLifetime(){
        return lifetime;
    }

    public Rectangle getPersonSpace(){
        return space;
    }

    public void setPersonSpace(Rectangle r){ space = r; }

    // Mediator methods
    public void move(Dimension size) { MediatorPerson.move(this, size); }

    public void locate(Graphics2D graphic) { MediatorPerson.locate(this , graphic); }

    public String toString(){
        return speed + " " + meeting + " " + socialDistance + " " +mask_state + " " + lifetime + ".";
    }


}
