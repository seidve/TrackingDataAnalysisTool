package userinterface;

import algorithm.*;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.TextField;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;//import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import com.sun.glass.events.KeyEvent;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import java.net.*;
import inputOutput.*; 
import testInputOutput.*;


public class Gui extends JFrame implements ActionListener{
	
	private JButton start = new JButton("Start Measurement");
	private JButton finish = new JButton("End Measurement");
	private JButton start2 = new JButton("Start 2.Measurement");
	private JButton finish2 = new JButton("End 2.Measurement"); 
	private JButton loadData = new JButton("Load Data");
	private JButton calculate = new JButton("Calculate");
	private JButton openIGTB = new JButton("Connect");
	private JButton loadTool = new JButton("Tool");
	
	private JTextField adresse= new JTextField(25); 
	private JTextField ValueData = new JTextField(15);
	
	private String[] messungen = {"Rauschen", "Correctness"}; 
	private JComboBox measurementtyp = new JComboBox(messungen);
	private JCheckBox cBJitterP = new JCheckBox("Jitterposition", false);
	private JCheckBox cBJitterR = new JCheckBox("Jitterrotation", false);
	private JCheckBox cBCorrectnessR = new JCheckBox("Accuracy-Rotation", false);
	private JCheckBox cBCorrectnessP = new JCheckBox("Accuracy-Position", false);
	
	private  JLabel LabelDataValue = new JLabel(); private  JLabel openITGL = new JLabel(); 
	private JLabel lValue = new JLabel(); private JLabel lCalcJR = new JLabel();  
	private JLabel lCalcC = new JLabel(); private JLabel lCalcJP = new JLabel();  
	
	private JMenuBar bar; private  JMenu menu ;private JMenuItem openItem; private JMenuItem closeItem;
	
	private JTextField toLoadField = new JTextField(5); private  JLabel toLoad = new JLabel();
	private JTextField distanceF = new JTextField(5); private  JLabel distance = new JLabel();
	private JTextField rotationAngel = new JTextField(5); private JLabel rotationL = new JLabel();
	private JTextField openIGTf = new JTextField(25); private JLabel openIGTl = new JLabel();
	
	TextField positionJitter = new TextField();
	File f; String valueP, valueD, valueR, valueL ;  
	//double correctness, accuracy, jitterR, jitterP;
	
	private final java.awt.List toolList = new java.awt.List(); 
	private final Label label2 = new Label("Available Tools");
	
	DataService dataS = new DataService();
	DataProcessor dataP = new DataProcessor();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	int toloadvalue;
	double toR, toD; 
	public Gui(){
		//allow window
		enableEvents(AWTEvent.WINDOW_EVENT_MASK); 
		init();	
	}
	
	private void init(){
		
		JTabbedPane tabbedPane = new JTabbedPane();
		panel1 = new JPanel();
		panel2 = new JPanel();
		tabbedPane.addTab("CSV", panel1);
		tabbedPane.addTab("ITG", panel2 );
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		//Searching for data file
		bar = new JMenuBar();
		menu = new JMenu("Search for Data ");
		openItem = new JMenuItem("Open");
	    closeItem = new JMenuItem("Close"); 
	    menu.add(openItem);
	    menu.add(closeItem);
	    bar.add(menu);
	    bar.setBounds(5, 20, 120, 20);
	    panel1.add(bar);
	    
	 	JLabel l0 = new JLabel(" CSV-Datafile:");
	 	l0.setBounds(20, 40, 120, 20);
	 	panel1.add(l0); 
	 	adresse.setBounds(210, 40, 250, 20);
	 	panel1.add(adresse); 
	 	loadData.setBounds(460, 40, 120, 20);
	 	panel1.add(loadData);
	 	
	 	toLoad.setText("Number of files to load");
	 	toLoad.setBounds(20, 140, 180, 25);
		panel1.add(toLoad);
		toLoadField.setBounds(210, 140, 180, 20);
		panel1.add("n", toLoadField);
	 
		
		distance.setText("Distant indication");
		distance.setBounds(20, 240, 130, 20);
		panel1.add(distance);
		distanceF.setBounds(210, 240, 180, 20);
		panel1.add(distanceF);
		
		rotationL.setText("Angel indication");
		rotationL.setBounds(20, 340, 170, 20);
		panel1.add(rotationL);
		rotationAngel.setBounds(210,340, 180, 20);
		panel1.add(rotationAngel);
		
		openITGL.setText("Online IGT-Link");
		openITGL.setBounds(40, 120, 170, 20);
		panel2.add(openITGL);
	 	openIGTf.setBounds(120, 120, 120, 20);
		panel2.add(openIGTf);
		openIGTl.setBounds(90, 120, 120, 20);
		panel2.add(openIGTl);
		openIGTB.setBounds(250, 120, 120, 25);
		panel2.add(openIGTB); 
		
		JLabel measuredTyp = new JLabel("Measurementtyp"); 
		measuredTyp.setBounds(650, 100, 120, 60);
		panel1.add(measuredTyp);
		measurementtyp.setBounds(800, 100, 120, 20);
		panel1.add(measurementtyp); 
		start.setBounds(650, 170, 160, 60);
		panel1.add(start);
		start.setForeground(Color.GREEN);
		finish.setBounds(815, 170, 150, 60);
		panel1.add(finish);
		finish.setForeground(Color.RED);
		
		//cBRotation.setBounds(650, 470, 120, 60);
		//panel1.add(cBRotation);
		cBJitterR.setBounds(650, 400, 150, 30);
		panel1.add(cBJitterR);
		//cBJitterR.setEnabled(false);
		cBJitterP.setBounds(650, 420, 150, 30);
		panel1.add(cBJitterP);
		//cBJitterP.setEnabled(false);
		cBCorrectnessR.setBounds(650, 440, 150, 30);
		panel1.add(cBCorrectnessR);
		cBCorrectnessP.setBounds(650, 460, 150, 30);
		panel1.add(cBCorrectnessP);
		calculate.setBounds(800, 400, 130, 60);
		panel1.add(calculate);
		
		toolList.setBounds(20, 700, 120, 80);
		panel1.add(toolList);
		loadTool.setBounds(200, 700, 150, 25);
		panel1.add(loadTool);

		//Connection with ActionListener
		start.addActionListener(this);
		finish.addActionListener(this);
		loadData.addActionListener(this);
		calculate.addActionListener(this);
		measurementtyp.addActionListener(this);
		start2.addActionListener(this);
		finish2.addActionListener(this);
		cBJitterR.addActionListener(this);
		cBJitterP.addActionListener(this);
		cBCorrectnessR.addActionListener(this);
		cBCorrectnessP.addActionListener(this);
		toLoadField.addActionListener(this);
		distanceF.addActionListener(this);
		rotationAngel.addActionListener(this);
		toolList.addActionListener(this);
		loadTool.addActionListener(this);
		openIGTB.addActionListener(this);
		
		panel1.setLayout(null);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane();
		this.add(tabbedPane);
	
		openItem.addActionListener(new java.awt.event.ActionListener() {
	        //opens actionPerformed by clicking openItem
	        public void actionPerformed(java.awt.event.ActionEvent e) {
	      
	            FileFilter filter = new FileNameExtensionFilter("Testreihe", "csv");
				JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				fc.addChoosableFileFilter(filter);
				int returnValue = fc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION){
				    File selctedFile = fc.getSelectedFile();
				    String path2 = selctedFile.getAbsolutePath();
				    CSVFileReader.setPath(path2);     
				}
		        }});}

		public void actionPerformed(ActionEvent e) {
				Object src = e.getSource();
				String path ;   	
				try{ 
					 if (src == openIGTB ) {
								Start_Stop_IGTLink.setTestappValue();
								OpenIGTLinkConnection.setTestappValue();
								Start_Stop_IGTLink.startIGTWindow();
								
						}
						
					if(src == loadData){ 
						f = new File(adresse.getText());
						path = f.getAbsolutePath();
						if( f.exists()== true && path.endsWith(".csv")){
							CSVFileReader.setPath(path);
						
						}else{
							JOptionPane.showMessageDialog(null, "Ungueltiger Dateityp", 
									"Warnung", JOptionPane.WARNING_MESSAGE);
						}
					}else if(src == start || src == start2 ){
						JOptionPane.showMessageDialog(null, "Jetzt das Ger�t ruhig liegen lassen", "Warnung", JOptionPane.WARNING_MESSAGE);	
						//hand over valueL to coordinatesystem
						//Selection
						
					}else if(src == finish || src == finish2 ){
					//	measurement-werte speichern 
						
							
					}else if(src == measurementtyp){
						String selected = (String) measurementtyp.getSelectedItem();
						if("Correctness".equals(selected)){
							
							distanceF.setEnabled(true);
							rotationAngel.setEnabled(true);
							start2.setBounds(650, 280, 160, 60);
							panel1.add(start2);
							start2.setForeground(Color.GREEN);
							start2.setEnabled(true);
							finish2.setBounds(815, 280, 150, 60);
							panel1.add(finish2);
							finish2.setForeground(Color.RED);
							finish2.setEnabled(true);
								
						}if("Rauschen".equals(selected)){							
							start2.setEnabled(false);
							finish2.setEnabled(false);
							distanceF.setEnabled(false);
							rotationAngel.setEnabled(false);
						}
 
					}else if(src == calculate){
						
						valueL = toLoadField.getText();
						toloadvalue = Integer.parseInt(valueL);
						
						List <ToolMeasure> toolMeasures = dataS.loadNextData(toloadvalue);
						List <ToolMeasure> firstMeasurement = toolMeasures ;
						List <ToolMeasure> secondMeasurement = toolMeasures ;
						
						for (ToolMeasure tm : toolMeasures){
							toolList.add(tm.getName());
							firstMeasurement = toolMeasures ;
							if (toolMeasures.contains(firstMeasurement)){
								toolMeasures.clear();
								
								for (ToolMeasure tm2 : toolMeasures){
									toolList.add(tm2.getName());
									secondMeasurement = toolMeasures ;
							}
						}
						
						}
						
						ToolMeasure tool = dataS.getToolByName(toolList.getSelectedItem());
						AverageMeasurement avgMes = tool.getAverageMeasurement();
					
						lValue.setText("Calculatet Value");
						lValue.setBounds(650, 510, 130, 30);
						panel1.add(lValue);
						lValue.setForeground(Color.BLUE);
						
						lCalcJR.setBounds(650, 540, 200, 30);
						panel1.add(lCalcJR);
						lCalcC.setBounds(650, 580, 200, 30);
						panel1.add(lCalcC);
						lCalcJP.setBounds(650, 620, 200, 40);
						panel1.add(lCalcJP);
						
						if(cBJitterR.isSelected()){
							
							lCalcJR.setText(String.valueOf(avgMes.getRotationError()));
						
						}if(cBJitterP.isSelected()){
							lCalcJP.setText(String.valueOf(avgMes.getError()));
							
						}if(cBCorrectnessR.isSelected()){
							valueR = rotationAngel.getText(); 
							toR = Double.parseDouble(valueR);
							lCalcC.setText(String.valueOf( dataS.getAccuracyRotation(toR, firstMeasurement.get(0).getMeasurement().get(0), secondMeasurement.get(0).getMeasurement().get(0))));
							
						}
						if(cBCorrectnessP.isSelected()){
							valueD = distance.getText();
							toD = Double.parseDouble(valueD);
							lCalcC.setText(String.valueOf(dataS.getAccuracy(toD, firstMeasurement.get(0).getAverageMeasurement(),  secondMeasurement.get(0).getAverageMeasurement())));
							
						}
						else if(src == loadTool){
							
							
						}
					}
				//	else if (src == openIGTB ) {
//					 
//						Start_Stop_IGTLink link = new Start_Stop_IGTLink();
//						link.startIGTWindow();
////							Start_Stop_IGTLink.setTestappValue();
////							OpenIGTLinkConnection.setTestappValue();
////							Start_Stop_IGTLink.startIGTWindow();
////							Start_Stop_IGTLink.startIGTWindow();
//							
//					}
//					
						
					
				}catch(Exception ep){
					if(f.exists()==false){
						JOptionPane.showMessageDialog(null,"Dateipfad existiert nicht",
								"Fenstertitel",JOptionPane.ERROR_MESSAGE);
					}else{

						JOptionPane.showMessageDialog(null,"Fehlermeldung","Fenstertitel",JOptionPane.ERROR_MESSAGE);
					} 
				}
				}					
			
		//close window
				protected void processWindowsEvent(WindowEvent e){
				super.processWindowEvent(e);
					if (e.getID() == WindowEvent.WINDOW_CLOSING) {
						System.exit(0); //Prgm wird beendet
					}
				}

		
}
