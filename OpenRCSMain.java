//openrc
import java.awt.Color;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.*;

public class OpenRCSMain extends JFrame implements ActionListener
{
	JButton NewUser;
	JButton EditUser;
	JList UserList;
	static DefaultListModel j;
	static byte[] digest;
     
	public OpenRCSMain()
	{
		getContentPane().setLayout(null);
		createGUI();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@SuppressWarnings("unchecked")
	void createGUI()
	{
		j = new DefaultListModel();
		//Make a new user
		NewUser = new JButton();
		NewUser.setLocation(236,600);
		NewUser.setSize(150,20);
		NewUser.setForeground( new Color(-52480) );
		NewUser.setBackground( new Color(-16711936) );
		NewUser.setText("New User....");
		getContentPane().add(NewUser);

		//Select a user from UserList and edit them
		EditUser = new JButton();
		EditUser.setLocation(3,600);
		EditUser.setSize(150,20);
		EditUser.setForeground( new Color(-16776961) );
		EditUser.setBackground( new Color(-26317) );
		EditUser.setText("Edit User....");
		getContentPane().add(EditUser);

		//List of the users
		UserList = new JList(j);
		UserList.setLocation(44,5);
		UserList.setSize(300,585);
		getContentPane().add(UserList);
		//j.addElement("YaH");
		
		//Frame superclass properties
		setTitle("Blockland OpenRC Server");
		setSize(400,650);
		setBackground( new Color(-10066330) );
		setVisible(true);
		setResizable(true);
		NewUser.addActionListener(this);
	}
	public static void main(String args[]) throws NoSuchAlgorithmException, IOException
	{
		new OpenRCSMain();
		addUser("joe 30hds9085s Blockland-Joe");
		addUser("johnPd 30nsf93n5e Blockland-JohnPD");
		addUser("mybut 392ds0974w Blockland-mybut");
		System.out.println(userData("johnPd"));
	}

	public void actionPerformed(ActionEvent act) {
		if(act.getSource() == NewUser) {
			OpenRCSNew.main(null);
		}
		
	}
	
	public static void updateUsers() throws IOException {
		for(int i = 0; i < j.getSize(); i++) {
			String element = j.getElementAt(i).toString();
			System.out.println("Got element " + element);
			String username = getWord(element, 0);
			System.out.println("Got username " + username);
			String path = username + ".txt";
			System.out.println(path);
			System.out.println("User file - " + path);
			File user = new File(path);
			String directory = getWord(element, 2);
			System.out.println("Got directory " + directory);
			if(!user.exists()) {
				try {
					user.createNewFile();
			} catch (IOException e) {
				System.out.println("Could not create file. Error");
				e.printStackTrace();
				}
			}
			
			BufferedWriter writeOut = new BufferedWriter(new FileWriter(user.getAbsoluteFile()));
			writeOut.write(getWord(element, 1));
			writeOut.write("\n" + directory);
			writeOut.close();
		}
	}
	
	public static void addUser(String username) throws NoSuchAlgorithmException, IOException {
		j.addElement(username);
	}
	
	public static String makeHash(String str) throws UnsupportedEncodingException {
		MessageDigest hg;
		try {
			hg = MessageDigest.getInstance("SHA-256");
			hg.update(str.getBytes("UTF-8")); 
			digest = hg.digest();
			System.out.println(digest);
		} catch (NoSuchAlgorithmException e) {
			OpenRCSNew.echo("SHA 256 error");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			OpenRCSNew.echo("UTF-8 is unsupported!");
			e.printStackTrace();
		}
		return digest.toString();
	}
	
	public static String getWord(String str, int place)
  	{
		String[] wds = str.split(" ");
		String wrd = wds[place];
		return wrd;
	}

  	public static int getWordCount(String str)
  	{
    		String[] wrds = str.split(" ");
    		return wrds.length;
  	}
  	
  	public static String userData(String usr) {
		for(int jx = 0; jx < j.getSize(); jx++) {
			String ele = j.getElementAt(jx).toString();
			System.out.println(ele);
			if(getWord(ele, 0) == usr) {
				String comp = getWord(ele, 1) + " " + getWord(ele, 2);
				System.out.println(comp);
				return comp;
			}
		}
		return "err";
  	}
	
}  
