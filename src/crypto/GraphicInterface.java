package crypto;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class GraphicInterface 
{
	private JFrame frame;
	private JTextField lastX;
	private JLabel txtTextulOriginal;
	private JLabel txtTextulCriptat;
	private JTextField a;
	private JTextField b;
	private JTextField m;
	private JTextField array1;
	private JTextField k;
	private JTextField j;
	private JTextField r;
	private JTextField array2;
	private JTextField c;
	private int nr;
	/**
	 * Launch the application.
	 */
	public static void run() 
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try 
				{
					GraphicInterface window = new GraphicInterface();
					window.frame.setVisible(true);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GraphicInterface() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(680, 400, 662, 371);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea OriginalText = new JTextArea();
		OriginalText.setText("");
		OriginalText.setToolTipText("");
		OriginalText.setBounds(35, 206, 277, 90);
		frame.getContentPane().add(OriginalText);
		
		JTextArea CryptedText = new JTextArea();
		CryptedText.setToolTipText("");
		CryptedText.setText("");
		CryptedText.setBounds(344, 206, 277, 90);
		frame.getContentPane().add(CryptedText);
		
		JButton btnNewButton = new JButton("LehmerGenerator");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{	
				nr=1;
				LehmerGenerator Lg = new LehmerGenerator(Long.parseLong(lastX.getText()), Long.parseLong(a.getText()), Long.parseLong(b.getText()), Long.parseLong(m.getText()));
				Vernam v = new Vernam(Lg.generate().toString(),OriginalText.getText());
				CryptedText.setText(v.getEcnrypted());
			}
		});
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setBounds(21, 12, 140, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("RanrotGenerator");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				nr=2;

				String values = array1.getText().replaceAll("\\s","");
				
				String[] strValues = values.split(",");

				int[] intValues = new int[strValues.length];
				
				for(int i = 0; i < strValues.length; i++) {
				    try {
				       intValues[i] = Integer.parseInt(strValues[i]);
				    } catch (NumberFormatException nfe) {
				       // The string does not contain a parsable integer.
				    }

				}
				
				RanrotGenerator Rg = new RanrotGenerator(intValues,Integer.parseInt(k.getText()), Integer.parseInt(j.getText()), Integer.parseInt(r.getText()));
				Vernam v = new Vernam(Rg.generate().toString(),OriginalText.getText());
				CryptedText.setText(v.getEcnrypted());
			}
		});
		btnNewButton_1.setBackground(Color.GREEN);
		btnNewButton_1.setBounds(21, 46, 140, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("MotherOfAllGenerator");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				nr=3;
				
				String values = array2.getText().replaceAll("\\s","");
				
				String[] strValues = values.split(",");

				int[] intValues = new int[strValues.length];
				
				for(int i = 0; i < strValues.length; i++) {
				    try {
				       intValues[i] = Integer.parseInt(strValues[i]);
				    } catch (NumberFormatException nfe) {
				       // The string does not contain a parsable integer.
				    }

				}
				MotherOfAllGenerator Mg = new MotherOfAllGenerator(intValues,Integer.parseInt(c.getText()));
				Vernam v = new Vernam(Mg.generate().toString(),OriginalText.getText());
				CryptedText.setText(v.getEcnrypted());
			}
		});
		btnNewButton_2.setBackground(new Color(255, 250, 205));
		btnNewButton_2.setBounds(21, 80, 140, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		lastX = new JTextField();
		lastX.setBounds(207, 12, 103, 20);
		frame.getContentPane().add(lastX);
		lastX.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Select File");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				OpenFile f = new OpenFile();
				try 
				{
					f.PickFile();
				} catch(Exception e)
				{
					e.printStackTrace();
				}
				OriginalText.setText(f.sb.toString());
			}
		});
		btnNewButton_3.setBounds(35, 140, 125, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		txtTextulOriginal = new JLabel();
		txtTextulOriginal.setBackground(UIManager.getColor("Button.background"));
		txtTextulOriginal.setText("Textul Original");
		txtTextulOriginal.setBounds(131, 175, 86, 20);
		frame.getContentPane().add(txtTextulOriginal);
		
		txtTextulCriptat = new JLabel();
		txtTextulCriptat.setText("Textul Criptat");
		txtTextulCriptat.setBounds(441, 175, 86, 20);
		frame.getContentPane().add(txtTextulCriptat);
		
		JButton btnInsertIntoFile = new JButton("Insert into File");
		btnInsertIntoFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame f = new JFrame();  
	            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
				JRootPane root = f.getRootPane();
				
				try {
				FileWriter w = new FileWriter("test.txt", true);
				if(nr == 1)
				{
					w.write("Cheie Lehmer: ");
					w.write("lastx = ");w.write(lastX.getText());
					w.write("   a = ");w.write(a.getText());
					w.write("   b = ");w.write(b.getText());
					w.write("   m = ");w.write(m.getText());
				} else if(nr == 2)
				{
					w.write("Cheie Ranrot: ");
					w.write("array = ");w.write(array1.getText());
					w.write("   k = ");w.write(k.getText());
					w.write("   j = ");w.write(j.getText());
					w.write("   r = ");w.write(r.getText());
				}else
				{
					w.write("Cheie MotherOfAll: ");
					w.write("array = ");w.write(array2.getText());
					w.write("   c = ");w.write(c.getText());
				}
				w.write("\n\n");
				w.write(CryptedText.getText());
				w.write("\n\n\n");
				w.close();
				JOptionPane.showMessageDialog(root, "Datele au fost salvate cu success");
				} catch (Exception e){
					JOptionPane.showMessageDialog(root, "Error");
					
				}
			}
		});
		btnInsertIntoFile.setBounds(344, 140, 145, 23);
		frame.getContentPane().add(btnInsertIntoFile);
		
		JLabel lblLastx = new JLabel();
		lblLastx.setText("lastX:");
		lblLastx.setBackground(SystemColor.menu);
		lblLastx.setBounds(171, 12, 41, 20);
		frame.getContentPane().add(lblLastx);
		
		JLabel lblA = new JLabel();
		lblA.setText("a:");
		lblA.setBackground(SystemColor.menu);
		lblA.setBounds(330, 11, 26, 20);
		frame.getContentPane().add(lblA);
		
		JLabel lblB = new JLabel();
		lblB.setText("b:");
		lblB.setBackground(SystemColor.menu);
		lblB.setBounds(430, 11, 26, 20);
		frame.getContentPane().add(lblB);
		
		JLabel lblM = new JLabel();
		lblM.setText("m:");
		lblM.setBackground(SystemColor.menu);
		lblM.setBounds(527, 11, 26, 20);
		frame.getContentPane().add(lblM);
		
		a = new JTextField();
		a.setColumns(10);
		a.setBounds(349, 11, 46, 20);
		frame.getContentPane().add(a);
		
		b = new JTextField();
		b.setColumns(10);
		b.setBounds(450, 12, 46, 20);
		frame.getContentPane().add(b);
		
		m = new JTextField();
		m.setColumns(10);
		m.setBounds(543, 11, 46, 20);
		frame.getContentPane().add(m);
		
		JLabel lblArray = new JLabel();
		lblArray.setText("array:");
		lblArray.setBackground(SystemColor.menu);
		lblArray.setBounds(171, 45, 41, 20);
		frame.getContentPane().add(lblArray);
		
		array1 = new JTextField();
		array1.setColumns(10);
		array1.setBounds(207, 45, 103, 20);
		frame.getContentPane().add(array1);
		
		JLabel lblK = new JLabel();
		lblK.setText("k:");
		lblK.setBackground(SystemColor.menu);
		lblK.setBounds(330, 45, 26, 20);
		frame.getContentPane().add(lblK);
		
		k = new JTextField();
		k.setColumns(10);
		k.setBounds(349, 44, 46, 20);
		frame.getContentPane().add(k);
		
		JLabel lblJ = new JLabel();
		lblJ.setText("j:");
		lblJ.setBackground(SystemColor.menu);
		lblJ.setBounds(430, 44, 26, 20);
		frame.getContentPane().add(lblJ);
		
		j = new JTextField();
		j.setColumns(10);
		j.setBounds(450, 45, 46, 20);
		frame.getContentPane().add(j);
		
		JLabel lblR = new JLabel();
		lblR.setText("r:");
		lblR.setBackground(SystemColor.menu);
		lblR.setBounds(527, 45, 26, 20);
		frame.getContentPane().add(lblR);
		
		r = new JTextField();
		r.setColumns(10);
		r.setBounds(543, 44, 46, 20);
		frame.getContentPane().add(r);
		
		JLabel lblArray_1 = new JLabel();
		lblArray_1.setText("array:");
		lblArray_1.setBackground(SystemColor.menu);
		lblArray_1.setBounds(171, 79, 41, 20);
		frame.getContentPane().add(lblArray_1);
		
		array2 = new JTextField();
		array2.setColumns(10);
		array2.setBounds(207, 79, 103, 20);
		frame.getContentPane().add(array2);
		
		JLabel lblC = new JLabel();
		lblC.setText("c:");
		lblC.setBackground(SystemColor.menu);
		lblC.setBounds(330, 79, 26, 20);
		frame.getContentPane().add(lblC);
		
		c = new JTextField();
		c.setColumns(10);
		c.setBounds(349, 78, 46, 20);
		frame.getContentPane().add(c);
		
		
		
		
	}
}
