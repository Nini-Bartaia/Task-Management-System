package landing;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.Statement;


public class dashboard extends JFrame implements ActionListener {
    DefaultTableModel tableModel;

    JTextField updateName;

    public static String Desc;
    public static String oldDesc;

    public static String oldDate;
    JTextField descField2;


    public  static Boolean taskFound;
    JTextField removeNameField;
    JTextField findNameField;
    JTextField nameField;
    JTextField descField;

    public static String owner;
    dashboard(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

            //add
        JButton add = new JButton("Add");
        add.setFont(new Font("Arial", Font.PLAIN, 15));
        add.setSize(100, 20);
        add.setLocation(15, 20);
        add.setActionCommand("add");
        add.addActionListener(this);
        add(add);

        //Update
        JButton Update = new JButton("Update");
        Update.setFont(new Font("Arial", Font.PLAIN, 15));
        Update.setSize(100, 20);
        Update.setLocation(120, 20);
        Update.setActionCommand("update");
        Update.addActionListener(this);
        add(Update);

        //find
        JButton find = new JButton("Get My Tasks");
        find.setFont(new Font("Arial", Font.PLAIN, 15));
        find.setSize(100, 20);
        find.setLocation(240, 20);
        find.setActionCommand("find");
        find.addActionListener(this);
        add(find);

        //findOne

        JButton findOne = new JButton("FindOne");
        findOne.setFont(new Font("Arial", Font.PLAIN, 15));
        findOne.setSize(100, 20);
        findOne.setLocation(350, 20);
        findOne.setActionCommand("findOne");
        findOne.addActionListener(this);
        add(findOne);

        //remove

        JButton remove = new JButton("Remove");
        remove.setFont(new Font("Arial", Font.PLAIN, 15));
        remove.setSize(100, 20);
        remove.setLocation(460, 20);
        remove.setActionCommand("remove");
        remove.addActionListener(this);
        add(remove);

        setSize(600, 300);
        setLocation(450, 200);
        setVisible(true);

    }


    dashboard(String owner){

        this.owner= owner;
    }

    public void setOwner(String owner){

        this.owner= owner;
    }
    public String getOwner1(){

        return this.owner;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
       // getContentPane().removeAll();

        System.out.println(e);
        String selectedAction= e.getActionCommand();
        if(selectedAction.equals("add")) {



            new Create();
            Create create= new Create(owner);


        } else if(selectedAction.equals("find")){
             getContentPane().removeAll();

             table();


        }else if(selectedAction.equals("update")){

            update();

        }

        else if(selectedAction.equals("findOne")){

           // search();

                findOne();
        }else if(selectedAction.equals("remove")){

                    remove();
        }

    }


    public void table(){

        Statement statement;

        Object[][] data = {};


        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        String[] columns = {"Id", "Owner", "Task Name","Task Description", "Task Type","Deadline"};

        ResultSet res = null;

        try {

            String query = "SELECT id,Owner, taskName, taskDescription, TaskType, Deadline FROM TasksDataDb";
            statement = new baseMethods().connect().createStatement();
            res = statement.executeQuery(query);

             int rowCount = 0;
            while (res.next()) {
                rowCount++;
            }

             data = new Object[rowCount][columns.length];

             res = statement.executeQuery(query);

             int rowIndex = 0;
            while (res.next()) {
                data[rowIndex][0] = res.getString("id");
                data[rowIndex][1] = res.getString("Owner");
                data[rowIndex][2] = res.getString("TaskName");
                data[rowIndex][3] = res.getString("TaskDescription");
                data[rowIndex][4] = res.getString("TaskType");
                data[rowIndex][5] = res.getString("Deadline");
                rowIndex++;
            }

        } catch (Exception e) {

            System.out.println(e);
        }

        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        JLabel lblHeading = new JLabel("Tasks List");
        lblHeading.setFont(new Font("Arial", Font.TRUETYPE_FONT, 24));

        getContentPane().setLayout(new BorderLayout());

        getContentPane().add(lblHeading, BorderLayout.PAGE_START);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setLocation(450, 200);
        setVisible(true);

    }


    public void update(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel taskLabel = new JLabel("Task Name");
        taskLabel.setBounds(150, 50, 100, 30);
        taskLabel.setFont(new Font("serif", Font.BOLD, 15));


        add(taskLabel);

        JTextField updateName= new JTextField();
        updateName.setBounds(250, 50, 150, 30);
        add(updateName);

        JButton search = new JButton("Search");
        search.setFont(new Font("Arial", Font.PLAIN, 15));
        search.setSize(100, 20);
        search.setLocation(250, 100);

        add(search);




        JLabel descLabel = new JLabel("Description");
        descLabel.setBounds(150, 150, 100, 30);
        descLabel.setFont(new Font("serif", Font.BOLD, 15));


        add(descLabel);

         descField2 = new JTextField();
        descField2.setBounds(250, 150, 150, 30);
        add(descField2);

        JLabel label = new JLabel("Date of Deadline");
        label.setFont(new Font("serif", Font.BOLD, 15));
        label.setSize(400, 20);
        label.setLocation(150, 200);
        add(label);

        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setSize(100, 50);
        dateChooser.setLocation(300, 200);
        add(dateChooser);



        search.addActionListener(ev->{
            System.out.println(descField2.getText());


            String[][] result = new baseMethods().findOne(updateName.getText());

            String firstElement = result[0][0];
            String SecondElemenet=result[0][1];
            String ThirdElemenet=result[0][2];

            oldDesc=SecondElemenet;
            oldDate=ThirdElemenet;
            System.out.println("First element: " + firstElement);
            System.out.println("sec element: " + SecondElemenet);
            System.out.println("3 element: " + ThirdElemenet);



            descField2.setText(SecondElemenet);




        });
        JButton update = new JButton("Update");
        update.setFont(new Font("Arial", Font.PLAIN, 15));
        update.setSize(100, 20);
        update.setLocation(220, 260);
        //update.addActionListener(this);

        update.addActionListener(res->{

            new baseMethods().updateName(oldDesc,descField2.getText(), descField2.getText());



        });
        add(update);

        setSize(600, 350);
        setLocation(450, 200);
        setVisible(true);





    }


    public void findOne(){

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        JLabel taskLabel = new JLabel("Task Name");
        taskLabel.setFont(new Font("Serif", Font.BOLD, 15));

         findNameField = new JTextField(20);

        JLabel idLabel = new JLabel("Id");
        idLabel.setFont(new Font("Serif", Font.BOLD, 15));
        JLabel descLabel = new JLabel("Description");
        descLabel.setFont(new Font("Serif", Font.BOLD, 15));
        JLabel deadlineLabel = new JLabel("Deadline");
        deadlineLabel.setFont(new Font("Serif", Font.BOLD, 15));

        JTextField idField = new JTextField(20);
        JTextField descField = new JTextField(20);
        JTextField deadline = new JTextField(20);


        JButton findButton = new JButton("Find");


        setVisible(true);


        findButton.addActionListener(e -> {

            String[][] result = new baseMethods().findOne(findNameField.getText());

                String firstElement = result[0][0];
                String SecondElemenet=result[0][1];
                String ThirdElemenet=result[0][2];
                System.out.println("First element: " + firstElement);
                System.out.println("sec element: " + SecondElemenet);
                System.out.println("3 element: " + ThirdElemenet);
                idField.setText(firstElement);
                descField.setText(SecondElemenet);
                deadline.setText(ThirdElemenet);


        });

        panel.add(taskLabel);
        panel.add(findNameField);
        panel.add(findButton);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(descLabel);
        panel.add(descField);
        panel.add(deadlineLabel);
        panel.add(deadline);


        frame.add(panel);
        frame.setSize(1200, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }


    public void remove(){

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();


        JLabel taskLabel = new JLabel("Task Name");
        taskLabel.setFont(new Font("Serif", Font.BOLD, 15));

        removeNameField = new JTextField(20);

        JButton removeButton = new JButton("remove");




        setVisible(true);


        removeButton.addActionListener(e -> {
            System.out.println(removeNameField);
            new baseMethods().remove(removeNameField.getText());





        });

        panel.add(taskLabel);
        panel.add(removeNameField);
        panel.add(removeButton);

        frame.add(panel);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


    }

