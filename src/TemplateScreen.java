import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * This is main template class of the theme of GUI.
 */
public abstract class TemplateScreen extends JFrame implements ScreenConstants, ActionListener {
    protected final JFrame main_page = new JFrame("Uygulama Adı");
    protected JPanel item = new JPanel();
    protected JPanel border;
    protected final Epidemic epidemic = new Epidemic();

    /**
     * This method is template theme of GUI. Border is a border, it's color is purple and
     * Main panel color is item's color is gray.
     */
    public TemplateScreen(){
        // Border Frame
        border = new JPanel();
        border.setBackground(new Color(purple));
        border.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        border.setLayout(new BoxLayout(border,BoxLayout.PAGE_AXIS));

        // Main Frame
        item.setBackground(new Color(gray));
        item.setMaximumSize(new Dimension(1200,700));
        item.setLayout(new GridLayout(1,3));
        border.add(item);

        main_page.add(border);
        // Frame Settings
        main_page.setVisible(true);
        main_page.setSize(1200,700);
        main_page.setResizable(false);
        main_page.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * For input with the button on the GUI.
     * @param panel It is the panel to which the button will be added.
     * @param buttonText It is the text to be written on the button.
     * @param task It is the command that will be given when the button is pressed.
     * @return The button itself.
     */
    protected JButton addingButton(JPanel panel, String buttonText, String task){
        JButton register=new JButton(buttonText);
        register.setBackground(Color.WHITE);
        register.setOpaque(true);
        register.setForeground(new Color(text_color));
        register.setActionCommand(task);
        register.addActionListener(this);
        panel.add(register);
        return register;
    }

    /**
     * For labels used for input and page descriptions
     * @param panel It is the panel where the Label will be added.
     * @param text It is the text to be written.
     * @return It is the Label itself.
     */
    protected JLabel addLabel(JPanel panel, String text){
        JLabel label = new JLabel(text,SwingConstants.CENTER);
        label.setFont(new Font("Verdana", Font.PLAIN, 12));
        label.setForeground(new Color(text_color));
        panel.add(label);
        return label;
    }

    /**
     * Getting input is for the panel with Label - TextField - Label.
     * @param panel This is the main panel to which the trio will be added.
     * @param entry It is for receiving input in the blank section.
     * @param term It is the label used to write the type of input value to be entered by the user, Example: pixel/sn.
     * @return To get the input value, we must return the JTextfield.
     */
    protected JTextField dataPanels(JPanel panel, String entry,String term){
        JPanel data = new JPanel();
        data.setBackground(new Color(gray));
        BoxLayout dataGrid= new BoxLayout(data,BoxLayout.LINE_AXIS);
        data.setLayout(dataGrid);

        JPanel textSide = new JPanel();
        textSide.setMaximumSize(new Dimension(130,50));
        textSide.setBackground(Color.white);
        textSide.setLayout(new GridLayout());

        addLabel(textSide,entry);

        JTextField input = new JTextField("");
        input.setFont(new Font("Serif", Font.PLAIN, 15));
        input.setMaximumSize(new Dimension(400,50));
        data.add(textSide);
        data.add(input);

        if(!term.equals("")) {
            JPanel texts = new JPanel();
            texts.setMaximumSize(new Dimension(50, 50));
            texts.setBackground(Color.white);
            texts.setLayout(new GridLayout());
            addLabel(texts,term);
            data.add(texts);
        }

        panel.add(data);
        return input;
    }

    /**
     * Usually,Each page has a title to add them. For them.
     * @param panel It is the panel to which the title will be added.
     * @param textArea It is the title itself.
     * @return It is the Label itself.
     */
    protected JLabel textDesign(JPanel panel, String textArea){
        JPanel table = new JPanel();
        table.setBackground(new Color(gray));
        table.setMaximumSize(new Dimension(500,180));
        table.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        table.setLayout(new GridLayout());

        JPanel textTable = new JPanel();
        textTable.setBackground(Color.white);
        textTable.setLayout(new GridLayout());
        table.add(textTable);

        JLabel text = addLabel(textTable,textArea);

        panel.add(table);
        return text;
    }


    /**
     * Some entries are periodically limit spaced. ComboBox is more convenient. For adding a Combo Box.
     * @param parent It is the panel where the ComboBox will be added.
     * @param elements It is the list of entries in the ComboBox.
     * @param text It is the description of the input selected by the Combo Box.
     * @param term The type of input value.
     * @return ComboBox must be returned to get the input.
     */
    protected JComboBox addSelectFromList(JPanel parent, ArrayList<String> elements,String text,String term){
        JPanel temp = new JPanel();
        temp.setBackground(Color.white);
        temp.setMaximumSize(new Dimension(500,100));

        JPanel label = new JPanel();
        label.setBackground(Color.white);
        label.setMaximumSize(new Dimension(100,100));

        addLabel(label,text);

        temp.add(label);

        JPanel combo = new JPanel();
        combo.setBackground(Color.white);
        combo.setMaximumSize(new Dimension(300,100));
        JComboBox item = new JComboBox();
        for(String element:elements){
            item.addItem(element);
        }
        combo.add(item);
        temp.add(combo);

        JPanel terms = new JPanel();
        terms.setBackground(Color.white);
        terms.setMaximumSize(new Dimension(100,100));

        addLabel(terms, term);

        temp.add(terms);

        parent.add(temp);
        return item;
    }

    /**
     * All pages is actually 3 parts. We're just using the middle part.
     * We add the empty parts with the normal background color.
     * @param item It is the panel to which the blank panel will be added.
     */
    protected void emptyBackground(JPanel item){
        JPanel empty = new JPanel();
        empty.setBackground(new Color(gray));
        item.add(empty);
    }

    public Epidemic getEpidemic(){
        return epidemic;
    }

    /**
     *  I just leave this blank in the super abstract class, since it's a Button.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
