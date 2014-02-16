import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.swing.*;

public class OpenRCSNew extends JFrame implements ActionListener
{
    JTextField UserName;
     JLabel untitled_2;
     JButton CreateUser;
     JLabel untitled_4;
     
   public OpenRCSNew()
   {
     getContentPane().setLayout(null);
     makeNGUI();
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   void makeNGUI()
   {
     	UserName = new JTextField();
	UserName.setLocation(99,44);
	UserName.setSize(300,20);
	UserName.setText("");
	UserName.setColumns(10);
	getContentPane().add(UserName);

	untitled_2 = new JLabel();
	untitled_2.setLocation(173,19);
	untitled_2.setSize(150,20);
	untitled_2.setText("Enter a Username");
	getContentPane().add(untitled_2);

	CreateUser = new JButton();
	CreateUser.setLocation(171,70);
	CreateUser.setSize(150,20);
	CreateUser.setText("Create User");
	getContentPane().add(CreateUser);

	untitled_4 = new JLabel();
	untitled_4.setLocation(58,94);
	untitled_4.setSize(400,20);
	untitled_4.setText("After creation, select the user in the list and Edit");
	getContentPane().add(untitled_4);

	setTitle("Blockland-OpenRC Server : New User");
	setSize(500,150);
	setVisible(true);
	setResizable(true);
	
	CreateUser.addActionListener(this);
	
   }
   @SuppressWarnings("unchecked")
public void actionPerformed(ActionEvent act) {
	   if(act.getSource() == CreateUser && UserName.getText() != "") {
		  echo("Got into usercheck");
		   try {
			OpenRCSMain.addUser(UserName.getText());
		} catch (UnsupportedEncodingException e) {
			echo("Problem reading UTF-8 encoding format.");
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			echo("There was a problem encoding an SHA-256 hash.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error creating user file");
			e.printStackTrace();
		}
		   super.dispose();
	   }
   }
   public static void echo(String echod) {
	   System.out.println(echod);
   }
   public static void main( String args[] )
   {
     new OpenRCSNew();
   }
}  

