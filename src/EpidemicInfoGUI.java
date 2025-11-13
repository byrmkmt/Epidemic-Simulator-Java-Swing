import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Objects;

/**
 * First we get the characteristics of the disease of the epidemic. This page is it.
 */
public class EpidemicInfoGUI extends TemplateScreen implements ItemListener {
    private final JComboBox spread;
    private final JComboBox mortality;

    public EpidemicInfoGUI(){
        JPanel middle = new JPanel();
        middle.setBackground(new Color(gray));
        middle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        middle.setLayout(new BoxLayout(middle,BoxLayout.PAGE_AXIS));

        textDesign(middle,"Salgın Hastalık Bilgileri");

        JPanel info = new JPanel();
        info.setBackground(new Color(gray));
        info.setMaximumSize(new Dimension(500,300));
        info.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        GridLayout infoGrid= new GridLayout(3,1);
        infoGrid.setVgap(20);
        info.setLayout(infoGrid);
        epidemic.setName(dataPanels(info,"İsim","").getText());

        ArrayList<String> elements = new ArrayList<>();
        elements.add("0.5");
        elements.add("0.6");
        elements.add("0.7");
        elements.add("0.8");
        elements.add("0.9");
        elements.add("1.0");
        spread = addSelectFromList(info, elements,"Yayılım Oranı", "");
        spread.addItemListener(this);

        elements.clear();

        elements.add("0.1");
        elements.add("0.2");
        elements.add("0.3");
        elements.add("0.4");
        elements.add("0.5");
        elements.add("0.6");
        elements.add("0.7");
        elements.add("0.8");
        elements.add("0.9");
        mortality = addSelectFromList(info, elements,"Ölüm Oranı  ", ""); middle.add(info);
        mortality.addItemListener(this);

        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(gray));
        buttons.setMaximumSize(new Dimension(500,100));
        buttons.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
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

    /**
     * One of the two buttons is to return to the previous page, the other is for proceeding from the next page.
     * @param e For button state control
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command= e.getActionCommand();
        switch (command) {
            case "BACK" -> {
                new EpidemicPageGUI();
                main_page.setVisible(false);
            }
            case "GO" -> {
                new CreatePopulationGUI(epidemic);
                main_page.setVisible(false);
            }
        }
    }

    /**
     * To check states the combo boxes
     * @param e  For combo box state control
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        double mort_rate = Double.parseDouble((String) Objects.requireNonNull(mortality.getSelectedItem()));
        double spread_rate = Double.parseDouble((String) Objects.requireNonNull(spread.getSelectedItem()));
        epidemic.setRate(mort_rate);
        epidemic.setSpread(spread_rate);
    }
}
