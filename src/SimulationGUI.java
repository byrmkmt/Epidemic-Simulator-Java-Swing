import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * It is the class that includes all parts of our simulation screen.
 */
public class SimulationGUI extends TemplateScreen{
    private final JButton option;
    private final SimulationScreen panel;
    private volatile int healthy;
    private volatile int patient;
    private volatile int dead;

    public SimulationGUI(ArrayList<Person> persons, Epidemic epidemic){
        border.setLayout(new BoxLayout(border, BoxLayout.Y_AXIS));
        border.remove(item);

        setHealthy(persons.size() - 1);
        setPatient(1);
        setDead(0);

        panel = new SimulationScreen(persons, epidemic,healthy,patient,dead);
        Thread thread = new Thread(panel, "Simulation Thread");

        border.add(panel);

        JPanel button = new JPanel();
        button.setBackground(Color.white);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(1000, 50));
        button.setMaximumSize(new Dimension(1000, 50));

        JLabel healthy = textDesign(button,"Sağlıklı = " + getHealthy());
        button.add(healthy);

        JLabel patient = textDesign(button,"Hasta = " + getPatient());
        button.add(patient);

        option = addingButton(button,"Durdur","");

        JLabel dead = textDesign(button, "Ölü = " + getDead());
        button.add(dead);

        JLabel treatment = textDesign(button,"Tedavide = " + " ? ");
        button.add(treatment);
        border.add(button);

        panel.setLabels(healthy, patient, dead, treatment);
        thread.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean s = panel.getState();
        if(s) {
            option.setText("Devam");
            panel.stop();
        }
        else {
            option.setText("Durdur");
            panel.start();
        }
    }

    public int getHealthy(){
        return healthy;
    }

    public int getPatient(){
        return patient;
    }

    public int getDead(){
        return dead;
    }

    public void setDead(int dead){
        this.dead = dead;
    }

    public void setHealthy(int healthy){
        this.healthy = healthy;
    }

    public void setPatient(int patient){
        this.patient = patient ;
    }

}
