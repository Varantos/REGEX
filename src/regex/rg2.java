package regex;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class rg2 {

	private JFrame frame;

	
	public String ETEXT="<notes>\r\n"
			+			  "<note id = \"1\">\r\n"
			+ 				 "<to>Вася</to>\r\n"
			+				 "<from>Света</from>\r\n"
			+				 "<heading>Напоминание</heading>\r\n"
			+ 				 "<body>Позвони мне завтра!</body>\r\n"
			+ 			   "</note>\r\n"
			+ 			   "<note id = \"2\">\r\n"
			+ 				  "<to>Петя</to>\r\n"
			+ 				  "<from>Маша</from>\r\n"
			+ 				  "<heading>Важное напоминание</heading>\r\n"
			+				  "<body/>\r\n"      //тег без тела
			+ 			  "</note>\r\n"   
			+ 		    "</notes>";
	
	
	
	
	
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rg2 window = new rg2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public rg2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
