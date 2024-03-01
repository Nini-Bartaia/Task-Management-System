package landing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;


public class Create extends JFrame implements ActionListener {

    private static String owner;
    private String name;
    private String description;
    public static LocalDateTime date;
    public  static LocalDateTime deadline;

    private LimitedTimeTask limitedTimeTask;
    JTextField nameField;
    JTextField descField;
    JTextField taskType;

    JRadioButton r1, r2, r3;
    JFormattedTextField intField;


    public Create(){



        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel name = new JLabel("Task Name");
        name.setBounds(40, 20, 100, 30);
        add(name);

         nameField = new JTextField();
        nameField.setBounds(150, 20, 150, 30);
        add(nameField);

        JLabel desc = new JLabel("Task Description");
        desc.setBounds(40, 70, 100, 30);
        add(desc);

        descField = new JTextField();
        descField.setBounds(150, 70, 150, 30);
        add(descField);

      //radio buttons
         r1=new JRadioButton("LimitedTimeTask");
         r2=new JRadioButton("RepeatableTask");
         r3=new JRadioButton("BasicTask");
        r1.setBounds(75,130,100,30);
        r2.setBounds(175,130,100,30);
        r3.setBounds(275,130,100,30);

        ButtonGroup group = new ButtonGroup();
        group.add(r1);
        group.add(r2);
        group.add(r3);


        add(r1);
        add(r2);
        add(r3);


        JButton save = new JButton("SAVE");
        save.setBounds(150, 170, 150, 30);
        save.setBackground(Color.BLACK);
        save.setForeground(Color.WHITE);
        save.addActionListener(this);
        add(save);



        setSize(600, 300);
        setLocation(450, 200);
        setVisible(true);


    }

    public Create(LocalDateTime deadline){

        this.deadline=deadline;


    }
    public void setDeadline(LocalDateTime deadline){

       this.deadline=deadline;
    }
    public LocalDateTime getDeadline(){

        return this.deadline;
    }


    public Create (String owner){
        this.owner= owner;
    }
    public void setOwner(String owner){

        this.owner= owner;
    }
    public String getOwner1(){

        return this.owner;
    }

    public void actionPerformed(ActionEvent e ){

        baseMethods con= new baseMethods();

        String selectedAction= e.getActionCommand();

        System.out.println(selectedAction);


        if(selectedAction.equals("SAVE")){

            if(!nameField.getText().isEmpty() && !descField.getText().isEmpty()&& r3.isSelected()) {

                new baseMethods().createRow(nameField.getText(),descField.getText(),r3.getText(),this.owner,null );


            }else if(r1.isSelected()) {


                new LimitedTimeTask();
               LimitedTimeTask limitedTimeTask = new LimitedTimeTask(nameField.getText(),descField.getText(),r1.getText(),this.owner,null );


            } else if (r2.isSelected()) {
                con.taskType = r1.getText();
                new RepeateableTask();

                RepeateableTask repeatTask = new RepeateableTask(nameField.getText(),descField.getText(),r2.getText(),this.owner,null );


            }

            else{
                JFrame frame= new JFrame();

                JOptionPane.showMessageDialog(frame, "You Should Provide Information", "Error", JOptionPane.ERROR_MESSAGE);

            }

        }

    }


    public static void main(String[] args) {

    }
}
