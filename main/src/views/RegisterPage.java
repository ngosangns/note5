package views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class RegisterPage extends Page {
	private JButton submit;
	private JButton login;
	private JTextArea username;
	private JTextArea password;
	private JTextArea repassword;
	
	public RegisterPage() {
		super();
		f.setTitle("Đăng ký");
		
	    username = new JTextArea();
	    username.setBounds(30, 40, 100, 20);
	    username.setForeground(new Color(0, 0, 0));
	    
	    password = new JTextArea();
	    password.setBounds(30, 70, 100, 20);
	    password.setForeground(new Color(0, 0, 0));
	    
	    repassword = new JTextArea();
	    repassword.setBounds(30, 100, 100, 20);
	    repassword.setForeground(new Color(0, 0, 0));
	    
	    submit = new JButton("Đăng ký");
		submit.setBounds(30, 130, 100, 20);
		
		login = new JButton("Đăng nhập");
		login.setBounds(30, 160, 100, 20);
		login.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
	            invoke("LoginPage");
		    }  
	    }); 
	    
	    f.add(username);
	    f.add(password);
	    f.add(repassword);
	    f.add(submit);
	    f.add(login);
	    f.setLayout(null);
	}
}
