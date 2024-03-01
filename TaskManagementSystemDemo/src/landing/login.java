package landing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class login extends JFrame implements ActionListener {

    JTextField tfusername;
    JTextField tfpassword;
    JPasswordField pfpassword;

    login(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);


        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(40, 20, 100, 30);
        add(lblusername);

         tfusername = new JTextField();
        tfusername.setBounds(150, 20, 150, 30);
        add(tfusername);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(40, 70, 100, 30);
        add(lblpassword);

         pfpassword = new JPasswordField();
        pfpassword.setBounds(150, 70, 150, 30);
        add(pfpassword);

        JButton login = new JButton("LOGIN");
        login.setBounds(150, 140, 150, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        setSize(600, 300);
        setLocation(450, 200);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {



        if(!tfusername.getText().isEmpty() && !pfpassword.getText().isEmpty()) {

            new dashboard();
            new dashboard(tfusername.getText());
        }else{
            JFrame frame= new JFrame();

            JOptionPane.showMessageDialog(frame, "You Should Provide Login Information", "Error", JOptionPane.ERROR_MESSAGE);
        }



    }

    }
