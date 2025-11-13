import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class OneByOneAddingPersonsGUI extends TemplateScreen{
    /**
     * The array that holds the added persons and their properties
     */
    private ArrayList<Person> person = new ArrayList<>();
    private Epidemic epidemic;
    /**
     * This page gets too many inputs so our variables are crowded.
     */
    private final JTextField speed;
    private final JComboBox mask_state;
    private final JComboBox distance;
    private final JComboBox time;

    /**
     * Updates the number showing the number of people remaining.
     * Every time a person is added, it decreases its value by one.
     */
    private JLabel textState;

    /**
     * An array that keeps track of whether the places on the canvas are occupied.
     */
    private ArrayList<ArrayList<Boolean>> state = new ArrayList<ArrayList<Boolean>>(975);

    /**
     * While the people are first randomly placed, the randomly selected place should not be occupied,
     * these are two variables used to control operations.
     */
    private int down;
    private int nth;

    public OneByOneAddingPersonsGUI(int numberOfPerson, Epidemic epidemic) {
        JPanel middle = new JPanel();
        middle.setBackground(new Color(gray));
        middle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        middle.setLayout(new BoxLayout(middle, BoxLayout.PAGE_AXIS));

        this.epidemic = epidemic;
        down = numberOfPerson;
        Random rand = new Random();
        nth = rand.nextInt(numberOfPerson) + 1;

        for(int i=0;i<975;++i)
            state.add(new ArrayList<Boolean>(575));

        for(int i=0;i < 975;++i){
            for(int j=0;j < 575;++j){
                state.get(i).add(false);
            }
        }

        textDesign(middle, "Kişi Ekleme İşlemi");

        JPanel info = new JPanel();
        info.setBackground(new Color(gray));
        info.setMaximumSize(new Dimension(800, 400));
        info.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        GridLayout infoGrid = new GridLayout(5, 1);
        infoGrid.setVgap(20);
        info.setLayout(infoGrid);
        speed = dataPanels(info, "Hareket Hızı","px/sn");
        ArrayList<String> elements= new ArrayList<>();
        elements.add("Maskeli");
        elements.add("Maskesiz");
        mask_state = addSelectFromList(info,elements,"Maske Durumu Seçiniz","");
        elements.clear();
        elements.add("0");
        elements.add("1");
        elements.add("2");
        elements.add("3");
        elements.add("4");
        elements.add("5");
        elements.add("6");
        elements.add("7");
        elements.add("8");
        elements.add("9");
        distance = addSelectFromList(info,elements,"Sosyal Mesafesi Seçiniz","pixel");
        elements.clear();
        elements.add("1");
        elements.add("2");
        elements.add("3");
        elements.add("4");
        elements.add("5");
        time = addSelectFromList(info,elements,"Buluşma Süresi Seçiniz", "saniye");

        textState = textDesign(info,"Kalan Kişi Sayısı " + down);

        middle.add(info);

        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(gray));
        buttons.setMaximumSize(new Dimension(500, 100));
        buttons.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        GridLayout buttonsGrid = new GridLayout(1, 2);
        buttonsGrid.setHgap(20);
        buttons.setLayout(buttonsGrid);
        addingButton(buttons, "Geri Dön", "BACK");
        addingButton(buttons, "Ekle", "ADD");
        middle.add(buttons);

        emptyBackground(item);
        item.add(middle);
        emptyBackground(item);
    }

    /**
     * To add person before starting the simulation or return the previous page.
     * @param e button checking
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command= e.getActionCommand();
        switch (command) {
            case "BACK" -> {
                new EpidemicPageGUI();
                main_page.setVisible(false);
            }
            case "ADD" -> {
                try {
                    int speed_str = Integer.parseInt((String) speed.getText());
                    if(speed_str < 0 || speed_str > 500)
                        throw new Exception();
                    double mask_str;
                    if(Objects.equals(mask_state.getSelectedItem(), "Maskeli"))
                        mask_str = 1.0;
                    else
                        mask_str = 0.2;
                    int distance_str = Integer.parseInt((String) Objects.requireNonNull(distance.getSelectedItem()));
                    int time_str = Integer.parseInt((String) Objects.requireNonNull(time.getSelectedItem()));
                    System.out.println("a");
                    ArrayList<int[]> coords = new ArrayList<>();
                    for(int i = 0; i < state.size();++i){
                        for(int j =0; j < state.get(i).size() ;++j){
                            boolean status = true;
                            for(int k=i;k<5 & status;++k){
                                for(int m=j;m<5 && status;++m){
                                    if (state.get(k).get(m))
                                        status = false;
                                }
                            }
                            if(status){
                                int[] coord= new int[]{i, j};
                                coords.add(coord);
                            }
                        }
                    }
                    Random rand = new Random();
                    int r = rand.nextInt(coords.size());
                    Person p1= new Person(speed_str,time_str,distance_str,mask_str, new Rectangle(new Point(coords.get(r)[0],coords.get(r)[1]), new Dimension(5,5)),Color.BLACK, (int) (100 * (1.00 - epidemic.getMortalityRate()) * 1000));
                    nth--;
                    if(nth == 0)
                        p1.setInfected(Color.RED);
                    System.out.println(p1);
                    person.add(p1);
                    down--;
                    textState.setText("Kalan Kişi Sayısı " + down);
                    if(down == 0) {
                        main_page.setVisible(false);
                        new SimulationGUI(person, epidemic);
                    }
                }
                catch(Exception exception){
                    System.out.println(exception.getMessage());
                }
            }
        }
    }


}
