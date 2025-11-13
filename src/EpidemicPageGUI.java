import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * It is the Home Page class.
 */
public class EpidemicPageGUI extends TemplateScreen {

    public EpidemicPageGUI() {
        JPanel operationTable = new JPanel();
        operationTable.setBackground(new Color(gray));
        operationTable.setLayout(new GridLayout(3,1));

        JPanel table = new JPanel();
        table.setBackground(new Color(gray));
        table.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        table.setLayout(new GridLayout());

        JPanel textTable = new JPanel();
        textTable.setBackground(Color.white);
        textTable.setLayout(new GridLayout());
        table.add(textTable);

        textDesign(textTable,"Salgın Simülasyonu");

        operationTable.add(table);
        epidemicInfo(operationTable);
        exitButton(operationTable);

        emptyBackground(item);
        item.add(operationTable);
        emptyBackground(item);

    }

    /**
     * There is a button on the page to exit simulation.
     * @param operationTable main panel of the page
     */
    private void exitButton(JPanel operationTable){
        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(gray));
        buttons.setBorder(BorderFactory.createEmptyBorder(65, 150, 105, 150));
        GridLayout buttonLayout = new GridLayout(1,1);
        buttons.setLayout(buttonLayout);
        addingButton(buttons,"Çıkış","EXIT");
        operationTable.add(buttons);
    }

    /**
     * There is a button to continue the simulation program.
     * @param operationTable main panel of the page.
     */
    private void epidemicInfo(JPanel operationTable){
        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(gray));
        buttons.setBorder(BorderFactory.createEmptyBorder(50, 50, 65, 50));
        GridLayout buttonLayout = new GridLayout(1,1);
        buttons.setLayout(buttonLayout);
        addingButton(buttons,"Simülasyon Oluşturmak İçin Tıklayınız","START");
        operationTable.add(buttons);
    }


    /**
     * One of the two buttons is for program exit, the other is for proceeding from the next page.
     * @param e For button state control
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command= e.getActionCommand();
        switch (command) {
            case "START" -> {
                new EpidemicInfoGUI();
                main_page.setVisible(false);
            }
            case "EXIT" -> {
                main_page.setVisible(false);
            }
        }
    }

}
