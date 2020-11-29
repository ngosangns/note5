package views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class LoginPage extends Page {
	private JButton submit;
	private JButton signUp;
	private JTextArea username;
	private JTextArea password;
	
	public LoginPage() {
		super();
		f.setTitle("Đăng nhập");
		
	    username = new JTextArea();
	    username.setBounds(30, 40, 100, 20);
	    username.setForeground(new Color(0, 0, 0));
	    
	    password = new JTextArea();
	    password.setBounds(30, 70, 100, 20);
	    password.setForeground(new Color(0, 0, 0));
	    
	    submit = new JButton("Đăng nhập");
		submit.setBounds(30, 100, 100, 20);
		
		signUp = new JButton("Đăng ký");
		signUp.setBounds(30, 130, 100, 20);
		signUp.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
	            invoke("RegisterPage");
		    }  
	    });  
	    
	    f.add(username);
	    f.add(password);
	    f.add(submit);
	    f.add(signUp);
	    f.setLayout(null);
	}
}
