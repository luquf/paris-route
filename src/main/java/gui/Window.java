package gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Window extends JFrame {
	
	public Window() {
		super();
		this.setTitle("Bouton");
	    this.setSize(400, 300);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setLayout(new GridLayout(3, 2));
	    this.getContentPane().add(new JLabel("From"));
	    this.getContentPane().add(new JTextField(""));
	    this.getContentPane().add(new JLabel("To"));
	    this.getContentPane().add(new JTextField(""));
	    this.getContentPane().add(new JButton("3"));
	    this.setVisible(true);
	}
}
