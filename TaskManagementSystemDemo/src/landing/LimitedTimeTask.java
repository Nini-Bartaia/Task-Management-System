package landing;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class LimitedTimeTask extends JFrame implements ActionListener {


    public String username;

    public static LocalDateTime localDateTime;

    public static String taskName;
    public static String taskDesc;



    public static String taskType;


    public static LocalDateTime deadline;
    public static String owner;


    LimitedTimeTask(String taskName, String taskDesc, String taskType,String owner, LocalDateTime deadline){

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

    LimitedTimeTask(){

        // Implement logic for LimitedTimeTask
        System.out.println("LimitedTimeTask selected works");


        JFrame frame = new JFrame( );
        JPanel panel = new JPanel();
        frame.getContentPane();

        frame.add(panel);
        frame.setSize(600, 300);
        frame.setLocation(450, 200);
        frame.setVisible(true);

        JLabel label = new JLabel("Date of Deadline");
        label.setFont(new Font("serif", Font.PLAIN, 20));
        label.setSize(400, 20);
        label.setLocation(50, 300);
        panel.add(label);

        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setSize(200, 100);
        dateChooser.setLocation(70, 300);
        panel.add(dateChooser);



        JButton save = new JButton("save");
        save.setBounds(150, 370, 150, 30);
        save.setBackground(Color.BLACK);
        save.setForeground(Color.WHITE);
        save.addActionListener(this);
        save.setActionCommand("save");
        panel.add(save);

//        JButton back = new JButton("back");
//        back.setBounds(50, 470, 150, 30);
//        back.setBackground(Color.BLACK);
//        back.setForeground(Color.WHITE);
//        back.addActionListener(this);
//        back.setActionCommand("back");
//        panel.add(back);
    }





    @Override
    public void actionPerformed (ActionEvent e){


                if ("save".equals(e.getActionCommand())) {
            JDateChooser dateChooser = (JDateChooser) ((JButton) e.getSource()).getParent().getComponent(1);
            Date selectedDate = dateChooser.getDate();

            if (selectedDate != null) {
                localDateTime = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                System.out.println("Selected Date and Time: " + localDateTime);

                new baseMethods().createRow(this.getTaskName(), this.getDesc(),this.getTaskType(),this.getOwner1(),localDateTime);

            } else {
                System.out.println("No date selected.");
            }



        }



    }
}
