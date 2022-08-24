package regex;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import javax.swing.text.MaskFormatter;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.TextArea;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JEditorPane;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;

import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;

public class rg1 extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public String ETEXT="\tMummy Duck lived on a farm. In her nest, she had five little eggs"
			+ " nand one big egg. One day, the five little eggs started to crack.\r\n\tTap,"
			+ " tap, tap! Five pretty, yellow baby ducklings came out. Then the big egg started to crack. "
			+ "Bang, bang, bang! One big, ugly duckling came out.\r\n\tNobody wanted to play with him. "
			+ "\u2018Go away\u2019, said his brothers and sisters.\r\n\tSo he went to find some new friends."
			+ "\r\n\tGo away!  Said the pig and horse.\r\n\tNo one wanted to be his friend. "
			+ "It started to get cold. It started to snow! The ugly duckling found an empty barn. "
			+ "And he lived there. He was cold, sad and alone.\r\n\t";
	// regexs-----------------------------------------------------------------------
	public String REGEX_PARAGRAPHS = "[^\\t]+";			//абзацы
	public String REGEX_SENTENSES  = "([^.!?]+[.!?])";	//предложения
	public String REGEX_WORDS  = "([^a-zA-Z']+)'*\\1*";	//слова
	// msssives--------------------------------------------------------------------- 1
	int[] 	 pharN 	=	new int 	[20]; 				// номер начала абзаца
	String[] phars	=	new String	[20]; 				// сами абзацы
	//------------------------------------------------------------------------------ 2
	int[]	 sentsN	=	new int		[20]; 					//количество  предложений в абзаце
	String[] sents	=	new String	[20]; 				//сами предложения
	//------------------------------------------------------------------------------ 3				
	String[] wrds	=	new String	[40]; 				//сами слова
	String[] wrdsN	=	new String	[40]; 				//позиции слов в предложении
	String[] wrdslen=	new String	[40]; 				//длины слов
	//------------------------------------------------------------------------------ 4
	int[] wrdsnum=	new int	[40]; 				//количества вхождений символа в слово
	int count=0;	// количество абзацев
//----------------------------------------------------------------------------------------------------------------------------
	private int [] num_Nphrss(final String alTxt) {
		int count=0;
		int leng=alTxt.length();
		Matcher m1 = Pattern.compile(REGEX_PARAGRAPHS).matcher(alTxt);	// компилируем регулярное выражение и ищем в text
	    while (m1.find())				// границы абзацев для внесения в массив абзацев
         count++; 					// количество абзацев
	     int[] 	 phrN 	=	new int 	[count+1]; // номер начала абзаца
	  //можно использовать split вместо этого
	     m1 = Pattern.compile(REGEX_PARAGRAPHS).matcher(alTxt);	// компилируем регулярное выражение и ищем в text
	     int i=0;
	     while (m1.find()) {		// границы абзацев для внесения в массив абзацев
	    	phrN[i]=m1.start();	// с какого символа начинается абзац
	    	i++;
	     }
	     phrN[count]=leng;				// конец последнего абзаца
	     System.out.println ("NUM OF PHARAS: "+count); 
	     return phrN;
	}
//----------------------------------------------------------------------------------------------------------------------------
	private String[] create_mass_pharass(final String alTxt) {
		int[] 	 pharN1 	=	new int 	[20]; // номерa начала абзаца
		String[] pharS1	=	new String	[20]; // сами абзацы
		int i=0;
		//можно использовать split вместо этого
	    pharN=num_Nphrss(alTxt);// границы абзацев 
	    while (i<pharN1.length)  {
	    	i++;
	    };
	    i=0;
	    while (i<pharN.length-1) { // заполняем массив абзацев
			pharS1[i]=alTxt.substring(pharN[i], pharN[i+1]);
			i++;
        }
		return pharS1;
	};
//----------------------------------------------------------------------------------------------------------------------------
private String[] creat_mass_sents( String[] phars) {// разбираем текс на абзацы
			int count=0;	// кол-во абзацев
			int cntc=0;		// кол-во предложений
			int pharCNT=0;	// счетчик абзацев
			int sentsCNT=0;	// счетчик предложений в абзаце
			String[] sents	=	new String	[20]; //сами предложения
			while (pharCNT<phars.length) { 
				if (phars[pharCNT]!=null) {
					count++;// количество абзацев
				};
				pharCNT++;
	        }
			pharCNT=0;				 
	       while (pharCNT<count) { //перебираем параграфы
	    	   Matcher m2 = Pattern.compile(REGEX_SENTENSES).matcher(phars[pharCNT]);// разбираем параграф
	    	   System.out.println();
	    	   while (m2.find()) { //ищем  предложения в каждом абзаце
	    		   sents[cntc]=m2.group(1).trim();
	    		   System.out.println("::: "+sents[cntc]);
 	    		   sentsCNT++;
	    		   cntc++;
	    	   }
	    	   sentsN[pharCNT]=sentsCNT;
	    	   System.out.print("("+sentsN[pharCNT]+" sentences...)\n");
	    	   sentsCNT=0;
	    	   pharCNT++;
	       }
		return sents;
		};
////----------------------------------------------------------------------------------------------------------------------------
private String[] creat_mass_wrds_in_sents(final String sents) {
	String[] words	=	new String	[40]; //слова в предложении
    words = sents.split(REGEX_WORDS);
	return words;
};
//----------------------------------------------------------------------------------------------------------------------------		
private String sort_wrds_in_sent(final String[] wrds) {// сортируем массив слов и формируем новое предложение
	String	sent = null;
	String tmpS =null;
	boolean sorted=true;
 	int i=0; 
 	while (sorted) {
 		sorted=false; 
 		for ( i = 1; i < wrds.length; i++)  { //перебираем слова в предложении и делаем перестановки		 
 			if (wrds[i].length()< wrds[i-1].length()) { 
 				tmpS=wrds[i];
 				wrds[i]=wrds[i-1];
 				wrds[i-1]=tmpS;
 				sorted=true;
 			}
 			
 		}
 	}	
	return sent;
};
//----------------------------------------------------------------------------------------------------------------------------
private int[] count_symbls_in_words(final String[] wrds, String smbl) {// количество символов в слове
	int cnt_wrd=0;					// счетчик слов
	int cnt_sym=0;					// счетчик символов
	int[] symnum1=	new int	[40]; 	// количества вхождений символа в слово
	//-------------------------------------------------------------------	
	for (cnt_wrd=0;cnt_wrd<= wrds.length;cnt_wrd++) {					//перебираем слова
		for (cnt_sym=0;cnt_sym<=wrds[cnt_wrd].length();cnt_sym++ ) {	//символы в словах
			System.out.println(wrds[cnt_wrd].charAt(cnt_sym)+"#");
		
			// bla-bla-bla	bla-bla-bla  bla-bla-bla	
		
		}
		 System.out.println();
	};
	return  symnum1;
}
//--------------------------------------------------------------------------------------------------------------------------		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rg1 frame = new rg1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public rg1() throws ParseException {
		setTitle("REGULAR EXPRESSIONS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 700, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		final JTextArea txtOUTPUT = new JTextArea(5,10);
		txtOUTPUT.setBounds(24, 8, 675,500);
		txtOUTPUT.setLineWrap(true);
		txtOUTPUT.setWrapStyleWord(true);
		
		JScrollPane jp = new JScrollPane(txtOUTPUT); // панели прокрутки
		jp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jp.setBounds(5, 200, 675, 500);
			setPreferredSize(new Dimension(5, 195));//перенос по словам
			getContentPane().add(jp, BorderLayout.CENTER);
		JButton btnNewButton = new JButton("1.PHARAGRAPHS...");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton.setBounds(10, 175, 120, 20);
		contentPane.add(btnNewButton);
		
		MaskFormatter formatter = new MaskFormatter("A");
		formatter.setPlaceholderCharacter(' ');
		final JFormattedTextField symbl_fld = new JFormattedTextField(formatter);
		symbl_fld.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				symbl_fld.setText("");
			}
		});
		symbl_fld.setBounds(294, 175, 32, 20);
		symbl_fld.setText("A");
		contentPane.add(symbl_fld);
		
		JButton btnNewButton_1 = new JButton("2. SENTENCES...");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//сортируем слова в предложениях
//-----------------------------------------------------------------------------------------------------
				int i=0;
				int j=0;
				int y=0;
				int k=1;
				System.out.println();
				txtOUTPUT.setText("");
				txtOUTPUT.append("SENTENCES!\n ");
				while ( j<sents.length-1) { 
					txtOUTPUT.append("\n:::  "+sents[j]+"\n");
					
					wrds=creat_mass_wrds_in_sents(sents[j]);
					sents[j]=sort_wrds_in_sent(wrds);
					
					for(i=0; i<wrds.length;i++)	txtOUTPUT.append(wrds[i]+" # ");
					txtOUTPUT.append("\n");

					if ( k== sentsN[y] ) {
						txtOUTPUT.append("!----( "+k+" sentences)-------------------SORTED PHARAGRAPH \n");
						y++;k=0;
					}
					j++;k++;
				}
			}
		
		});
		btnNewButton_1.setBounds(134, 175, 120, 20);
		contentPane.add(btnNewButton_1);
		JButton btnNewButton_2 = new JButton("3. WORDS...");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//сортировать слова в предложениях по количеству символа в слове	
//---------------------------------------------------------------------------------------------------------------------------------			
				txtOUTPUT.setText("");				
				//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				//JOptionPane.showMessageDialog(null, "MY regrets!.. :( ","REGULAR EXPRESSIONS", JOptionPane.PLAIN_MESSAGE);
				int i=0;
				int j=0;
				int k=0;
				int y=0;
				String sym=symbl_fld.getText();
				// 1. в каждом слове посчитать кол-во символов и записть в массив по количеству слов. как предложения
				// 2.  сортировать массив колличеств вхождений и одновременно слова в предложении
				sents= creat_mass_sents(  phars );
				System.out.println("-------------------------");
				while ( j<sents.length) { 
					txtOUTPUT.append("\n:::  "+sents[j]+"\n");
		
					wrdsnum=count_symbls_in_words(wrds,sym.trim()); // считаем символы в слове
					//!!!сортировка слов!!!
					
					for(i=0; i<wrds.length;i++)	txtOUTPUT.append(wrds[i]+" ** ");
					txtOUTPUT.append("\n");
					if ( k== sentsN[y] ) {
						txtOUTPUT.append("!----( "+k+" sentences)-------------------SORTED PHARAGRAPH \n");
						y++;k=0;
					}
					j++;k++;
				}
				
				
			}
		});
		btnNewButton_2.setBounds(334, 175, 120, 20);
		contentPane.add(btnNewButton_2);
		
		final JTextArea txtBritish = new JTextArea();
		txtBritish.setFont(new Font("Monospaced", Font.PLAIN, 11));
		txtBritish.setWrapStyleWord(true);
		txtBritish.setLineWrap(true);
		txtBritish.setBounds(5, 5, 670, 165);
		txtBritish.setText(ETEXT);
		txtBritish.setRows(14);
		contentPane.add(txtBritish);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// Разбор текста  на абзацы  и их сортировка тут 
//-------------------------------------------------------------------------------------------------------------
				int	   i=0;
				int	   tmp=0;
				phars= create_mass_pharass(txtBritish.getText());
				sents= creat_mass_sents(  phars );
				txtOUTPUT.setText("");
				count=0;i=0;
				while (i<phars.length) { // количество абзацев
					if (phars[i]!=null)	count++;
					i++;
				}
				i=0;// сортировка абзацев по количеству предложений
				System.out.println("");
				String tmpS="";
				boolean needIt = true;
				while (needIt) {
		       		needIt = false;
		       		for ( i = 1; i < count; i++) {
		       			if  (sentsN[i]>sentsN[i-1]) { 
		       				tmpS=phars[i];
		       				phars[i]=phars[i-1];
		       				phars[i-1]=tmpS;
		       				tmp=sentsN[i];
		       				sentsN[i]=sentsN[i-1];
		       				sentsN[i-1]=tmp;
		       				needIt=true;
		       			}
		       		}
		        }
		       System.out.println("!-----------------------------------!");
		       System.out.println("!--SORT-SORT-SORT-SORT-PHARAGRAPHS--!");
		       System.out.println("!-----------------------------------!");
		      //---------разбираем заново на предложения отсортированный массив абзацев
		       sents= creat_mass_sents(  phars );
		       txtOUTPUT.setText("");

		       txtOUTPUT.append("SORTED PHARAGRAPHS!\n");;
		       for(i=0; i<count;i++)  {
		    	   txtOUTPUT.append("------------------\n ");
		    	   txtOUTPUT.append(phars[i]);
		    	   txtOUTPUT.append( "("+sentsN[i]+" sentences...)\n");
		       }
	    	   txtOUTPUT.append("------------------\n ");
	    	   
			}
		});
	}
}
