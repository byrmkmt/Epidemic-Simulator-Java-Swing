import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Only the 1000x600 screen part of the simulation screen is its class.
 */
public class SimulationScreen extends JPanel implements Runnable {
    ArrayList<Person> persons = new ArrayList<>();
    Epidemic epidemic;
    private volatile boolean state=true;
    private volatile boolean kill = true;
    private final Timer timer;
    private volatile int healthy;
    private volatile int dead;
    private volatile int patient;
    private JLabel h1, p1, d1, t1;

    public SimulationScreen(ArrayList<Person> persons, Epidemic epidemic, int x, int y, int z){
        this.epidemic = epidemic;
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setPreferredSize(new Dimension(1000, 600));
        setMaximumSize(new Dimension(1000, 600));
        setBorder(BorderFactory.createLoweredBevelBorder());
        this.persons = persons;
        healthy = x;
        dead = y;
        patient = z;
        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Person p : persons) {
                    p.move(getSize());
                    if(p.getLifetime() > 0)
                        p.setLifetime(p.getLifetime() - 50);
                    else{
                        if(patient >= 0) {
                            dead++;
                            patient--;
                            p.setInfected(Color.BLUE);
                        }
                        else {
                            timer.stop();
                            kill = false;
                        }
                    }
                }
                repaint();
            }
        });
    }


    public void paintComponent(Graphics graphic) {
        // Sabit arka plan rengi
        graphic.setColor(Color.white);
        graphic.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(graphic);
        for (Person person : persons) {
            Graphics2D g = (Graphics2D) graphic.create();
            person.locate(g);
            if(person.getInfected() == Color.RED)
            coordsCheck(person, graphic);
            g.dispose();
        }
    }

    /**
     * Are there any people next to the sick person?
     * @param person
     * @param g
     */
    private void coordsCheck(Person person, Graphics g){
        Rectangle d = person.getPersonSpace();
        int l = person.getSocialDistance();
        int[] x = {d.x,d.x + 5};
        int[] y = {d.y,d.y + 5};
        for(Person p:persons){
            int[] nx = {p.getPersonSpace().x,p.getPersonSpace().x + 5};
            int[] ny = {p.getPersonSpace().y,p.getPersonSpace().y + 5};
            for(int i=0;i<2;++i){
                for(int j=0;j<2;++j){
                    if(nx[j]!=x[i] && ny[j]!=y[i]) {
                        if (Math.abs(nx[j] - x[i]) <= l) {
                            if(calculateInfection(person,p, epidemic)) {
                                if(p.getInfected()!=Color.RED) {
                                    p.setInfected(Color.RED);
                                    patient++;
                                    healthy--;
                                }
                            }
                        }
                        if (Math.abs(ny[j] - y[i]) <= l) {
                            if(calculateInfection(person,p, epidemic)) {
                                if(p.getInfected()!=Color.RED) {
                                    p.setInfected(Color.RED);
                                    patient++;
                                    healthy--;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * It is the class that calculates the probability of injection. If it is above 0.02, the person becomes sick.
     * @param patient
     * @param victim
     * @param epidemic
     * @return
     */
    private boolean calculateInfection(Person patient, Person victim, Epidemic epidemic){
        double min_dist = Math.min(patient.getSocialDistance(), victim.getSocialDistance());
        double max_time = Math.min(patient.getMeetingTime(), victim.getMeetingTime());
        double spreading = epidemic.getSpreadingFactor();
        double timing = (1.0 + (max_time/10.0));
        double pt_mask = patient.getMaskState();
        double vc_mask = victim.getMaskState();
        double dist= (1.0 - (min_dist/10.0));
        double probability = Math.min(spreading *  timing * pt_mask * vc_mask * dist, 1.0);
        System.out.println(probability);
        return probability * 1000 > 20;
    }

    public boolean getState(){
        return state;
    }

    public void run() {
        while(kill) {
            if (state) {
                //  System.out.println("Devam Ediyor.");
                h1.setText("Sağlıklı = " + healthy);
                p1.setText("Hasta = " + (patient + 1)) ;
                d1.setText("Ölü =  " + (dead - 1)) ;
                timer.start();
            }
            if (!state) {
                // System.out.println("Duraklatıldı.");
                timer.stop();
            }
        }
    }

    public void stop() {
        state = false;
    }

    public void start() {
        state = true;
    }

    public void setHealth(int num){
        healthy = num;
    }

    public void setPatient(int num){
        patient = num;
    }

    public void setDead(int num){
        dead = num;
    }

    public void setLabels(JLabel h, JLabel p, JLabel d, JLabel t){
        h1 = h;
        p1 = p;
        d1 = d;
        t1 = t;
    }

}
