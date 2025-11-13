import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * This is the class population add page.
 */
public class CreatePopulationGUI extends TemplateScreen {
    private final Epidemic epidemic;

    public CreatePopulationGUI(Epidemic epidemic){
        JPanel middle = new JPanel();
        middle.setBackground(new Color(gray));
        middle.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
        middle.setLayout(new BoxLayout(middle,BoxLayout.PAGE_AXIS));

        textDesign(middle,"Kişileri Toplu/Teker Oluşturma Tipi");

        this.epidemic = epidemic;

        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(gray));
        buttons.setMaximumSize(new Dimension(500,500));
        buttons.setBorder(BorderFactory.createEmptyBorder(120, 75, 120, 75));
        GridLayout buttonsGrid= new GridLayout(3,1);
        buttonsGrid.setVgap(20);
        buttons.setLayout(buttonsGrid);
        addingButton(buttons,"Tüm Kişileri Sırasıyla Ekle","ONE");
        addingButton(buttons,"Tüm Kişileri Dosyayla Ekle","WHOLE");
        addingButton(buttons,"Geri Dön","BACK");
        middle.add(buttons);


        emptyBackground(item);
        item.add(middle);
        emptyBackground(item);
    }

    /**
     * There are three buttons,
     * Adding persons one by one,
     * Adding persons collectively via file
     * and going to the previous page.
     * @param e button controls
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command= e.getActionCommand();
        switch (command) {
            case "ONE" -> {
                String numberOfPerson = JOptionPane.showInputDialog(main_page,"Lütfen popülasyondaki kişi sayısı giriniz.", "Kişi Sayısı", JOptionPane.CLOSED_OPTION);
                int num;
                try {
                    assert numberOfPerson != null;
                    num = Integer.parseInt(numberOfPerson);
                    if(num <=0 || num > 24000)
                       throw new Exception();
                    System.out.println(numberOfPerson);
                    main_page.setVisible(false);
                    System.out.println(epidemic);
                    new OneByOneAddingPersonsGUI(num, epidemic);
                }
                catch(Exception exception){
                    JOptionPane.showMessageDialog(main_page,"Lütfen 0-24000 arası tam sayı giriniz.", "Girdi Hatası", JOptionPane.WARNING_MESSAGE);
                }
            }
            case "WHOLE" -> {
                main_page.setVisible(false);
                new AddPersonWithFileGUI(epidemic);
            }
            case "BACK" ->{
                main_page.setVisible(false);
                new EpidemicInfoGUI();
            }
        }
    }

}
