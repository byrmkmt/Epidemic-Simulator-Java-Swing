import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * It is the class that collectively reads people from file.
 */
public class AddPersonWithFileGUI extends TemplateScreen {

    // private ArrayList Person, Kişi Sayısı
    private JLabel text;
    private JPanel middle;
    private String path;
    private ArrayList<Person> persons = new ArrayList<Person>();
    private Epidemic epidemic;

    public AddPersonWithFileGUI(Epidemic epidemic){
        middle = new JPanel();
        middle.setBackground(new Color(gray));
        middle.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        middle.setLayout(new BoxLayout(middle,BoxLayout.PAGE_AXIS));
        this.epidemic = epidemic;

        textDesign(middle,"Kişiler Dosyası Seçimi");

        JPanel info = new JPanel();
        info.setBackground(new Color(gray));
        info.setMaximumSize(new Dimension(500,300));
        info.setBorder(BorderFactory.createEmptyBorder(30, 50, 20, 50));
        GridLayout infoGrid= new GridLayout(2,1);
        info.setLayout(infoGrid);

        JButton button = addingButton(info,"Dosya Yolu Seçiniz","FILEPATH");
        button.setFont(new Font("Arial", Font.BOLD, 16));
        text= textDesign(info,"Dosya Seçilmedi");

        middle.add(info);

        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(gray));
        buttons.setMaximumSize(new Dimension(500,150));
        buttons.setBorder(BorderFactory.createEmptyBorder(75, 0, 25, 0));
        GridLayout buttonsGrid= new GridLayout(1,2);
        buttonsGrid.setHgap(20);
        buttons.setLayout(buttonsGrid);
        addingButton(buttons,"Geri Dön","BACK");
        addingButton(buttons,"Devam","GO");
        middle.add(buttons);

        emptyBackground(item);
        item.add(middle);
        emptyBackground(item);
    }

    public void finderFilePath(JPanel main){
        JFileChooser j = new JFileChooser();
        j.showSaveDialog(null);
        main.add(j);
    }

    /**
     * It is a method of reading files and getting person information.
     */
    public void fileRead(){
        try {
            File f = new File(path);
            Scanner reader = new Scanner(f);
            Random rand = new Random();
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                data = data.replaceAll("\\s+","");
                String[] result = data.split(",");
                int x = rand.nextInt(995);
                int y = rand.nextInt(595);
                if(result.length == 4)
                    persons.add(new Person(Integer.parseInt(result[0]),Integer.parseInt(result[2]),Integer.parseInt(result[3]), Double.parseDouble(result[1]),new Rectangle(new Point(x,y), new Dimension(5,5)),Color.BLACK,10000));
            }
            int r = rand.nextInt(persons.size());
            persons.get(r).setInfected(Color.RED);
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command= e.getActionCommand();
        switch (command) {
            case "BACK" -> {
                new EpidemicPageGUI();
                main_page.setVisible(false);
            }
            case "GO" -> {
                if(text.getText().equals("Dosya Seçildi.")){
                    fileRead();
                    new SimulationGUI(persons,epidemic);
                    main_page.setVisible(false);
                }
                else
                    JOptionPane.showMessageDialog(middle, "Lütfen .person uzantılı dosya seçiniz.", "Girdi Girilmedi", JOptionPane.ERROR_MESSAGE);
            }
            case "FILEPATH" -> {
                JFileChooser j = new JFileChooser();
                j.showOpenDialog(null);
                path = j.getSelectedFile().getAbsolutePath();
                System.out.println(path);
                text.setText("Dosya Seçildi.");
            }
        }
    }

}
