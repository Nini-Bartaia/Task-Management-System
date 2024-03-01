package landing;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class RepeateableTask extends JFrame implements ActionListener {

    JFormattedTextField intField;

    public String username;

    public static LocalDateTime localDateTime;

    public static String taskName;
    public static String taskDesc;



    public static String taskType;


    public static LocalDateTime deadline;
    public static String owner;
    RepeateableTask(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);



        JLabel label = new JLabel("Date of Deadline");
        label.setFont(new Font("serif", Font.PLAIN, 15));
        label.setSize(400, 20);
        label.setLocation(150, 50);
        add(label);

        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setSize(70, 30);
        dateChooser.setLocation(150, 100);
        add(dateChooser);

        JLabel label1 = new JLabel("Number of work");
        label1.setFont(new Font("serif", Font.PLAIN, 15));
        label1.setSize(400, 20);
        label1.setLocation(150, 150);
        add(label1);


        intField = new JFormattedTextField();
        intField.setSize(60, 50);
        intField.setLocation(250, 150);
        add(intField);


        JButton save = new JButton("save");
        save.setBounds(150, 200, 80, 30);
        save.setBackground(Color.BLACK);
        save.setForeground(Color.WHITE);
        save.addActionListener(this);
        save.setActionCommand("save");
        add(save);


        setSize(600, 300);
        setLocation(450, 200);
        setVisible(true);

    }


    RepeateableTask(String taskName, String taskDesc, String taskType,String owner, LocalDateTime deadline){

            this.taskName=taskName;
            this.taskDesc=taskDesc;
            this.taskType=taskType;
            this.deadline=deadline;
            this.owner= owner;
            System.out.println("Task Name: " + taskName);



        }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public String getDesc() {
        return taskDesc;
    }

    public void setDesc(String desc) {
        this.taskDesc = desc;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setType(String type) {
        this.taskType = type;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;

    }

    public void setOwner(String owner){

        this.owner= owner;
    }
    public String getOwner1(){

        return this.owner;
    }






    @Override
    public void actionPerformed(ActionEvent e){

        baseMethods con= new baseMethods();

        if ("save".equals(e.getActionCommand())) {
            JDateChooser dateChooser = (JDateChooser) ((JButton) e.getSource()).getParent().getComponent(1);
            Date selectedDate = dateChooser.getDate();
             String intString = intField.getText();
            //System.out.println(intString.getClass().getSimpleName());
            System.out.println(intString);
            Integer intValue;
            try {
                //intValue =  intField.getValue();
                intValue = Integer.parseInt(intField.getText());

            } catch (NumberFormatException ex) {
                JFrame frame = new JFrame( );
                JOptionPane.showMessageDialog(frame, "Invalid integer value");
                return;
            }
            if (selectedDate != null) {
                LocalDateTime localDateTime = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                System.out.println("Selected Date and Time: " + localDateTime);

                new baseMethods().createRow(this.getTaskName(), this.getDesc(),this.getTaskType(),this.getOwner1(),localDateTime);


            } else {
                System.out.println("No date selected.");
            }
        }


    }
}
