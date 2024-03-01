package landing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class landingPage extends JFrame implements ActionListener  {

    public landingPage() throws InterruptedException {


        JFrame frame = new JFrame("Task Management System");
        JPanel panel = new JPanel();
        frame.getContentPane();
        //header
        JLabel label = new JLabel("Task Management System");
        Dimension size = label.getPreferredSize();
        label.setBounds(230, 20,300, 100);
        label.setFont(new Font("Arial", Font.BOLD, 20)); // Set font size to 24

        panel.setLayout(null);
        panel.add(label);
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 0, 0));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(panel);
        frame.setSize(750, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        //login
        JButton login = new JButton("Login");
        login.setFont(new Font("Arial", Font.PLAIN, 15));
        login.setSize(100, 20);
        login.setLocation(280, 155);
        login.addActionListener(this);
        panel.add(login);

     //register

//        JButton register = new JButton("Register");
//        register.setFont(new Font("Arial", Font.PLAIN, 15));
//        register.setSize(100, 20);
//        register.setLocation(15, 100);
//        register.addActionListener(this);
//        panel.add(register);

    }

    public void actionPerformed(ActionEvent e){

        setVisible(false);
        new login();


    }



}
