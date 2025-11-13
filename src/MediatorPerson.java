import java.awt.*;
import java.util.Random;

/**
 * Mediator class
 */
public class MediatorPerson {

    /**
     * It is the method for moving the person on the canvas.
     * @param p
     * @param size
     */
    public static void move(Person p, Dimension size) {
        // Rastgele yön seçme ve o yönde ilerleme...
        Random r=new Random();
        int path=r.nextInt(4);
        Rectangle rt = p.getPersonSpace();
        if(path == 0 && rt.x + 5 + p.getSpeed() <= size.width)
            rt.x += p.getSpeed();
        else if(path == 1 && rt.y - p.getSpeed()  >= 0)
            rt.y -= p.getSpeed();
        else if(path== 2 && rt.y + p.getSpeed()  + 5 <= size.height)
            rt.y += p.getSpeed();
        else if (path ==3 && rt.x -  p.getSpeed() >= 0)
            rt.x -= p.getSpeed();
        p.setPersonSpace(rt);
    }

    /**
     * It is the method for placing the person in the first position.
     * @param p
     * @param graphic
     */
    public static void locate(Person p, Graphics2D graphic) {
        graphic.setColor(p.getInfected());
        graphic.fillRect(p.getPersonSpace().x,p.getPersonSpace().y,p.getPersonSpace().width,p.getPersonSpace().height);
    }

}
