package calenGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

//import calenGUI.Tasks.BHandler;


public class calender extends JFrame{
	
	private JPanel mainPanel,eastP,moveButtons,daysP,daysPanel,dateP,panel1,tasksButtons,northP;
	private JButton previousM,nextM,previousY,nextY,daysofM[],add;
	private JTextArea tasks;
	private JLabel MMYY,dateforeastP,dateoftheday,clock,daysofW;
	private static final String days[]={"SUN","MON","TUES","WED","THUR","FRI","SAT"};
	private Color col=new Color(94,123,141);
	private Font font=new Font("Arial",Font.BOLD,10);
	private Calendar c=new GregorianCalendar();
	private int numofday,disp=0;
	public ArrayList<Events> task=new ArrayList<Events>();
	public static int y;
	private Locale l=new Locale("en");
	private DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy",l); 
	private boolean z=false;
	public calender() {
		mainPanel=new JPanel(new BorderLayout());
		eastP=new JPanel(new BorderLayout(5,5));
		eastP.setSize(300,300);
		previousM=new JButton("<");
		previousM.setFont(font);
		nextM=new JButton(">");
		nextM.setFont(font);
		previousY=new JButton("<<");
		previousY.setFont(font);
		nextY=new JButton(">>");
		nextY.setFont(font);
		ButtonHandler handler=new ButtonHandler();
		previousY.addActionListener(handler);
		previousM.addActionListener(handler);
		nextM.addActionListener(handler);
		nextY.addActionListener(handler);
		MMYY=new JLabel(getMonthName(c)+"  -  "+c.get(Calendar.YEAR));
	    MMYY.setFont(new Font("Arial",Font.BOLD,16));
	    MMYY.setForeground(Color.white);
		moveButtons=new JPanel();
		moveButtons.add(previousY);
		moveButtons.add(previousM);
		moveButtons.add(MMYY);
		moveButtons.add(nextM);
		moveButtons.add(nextY);
		moveButtons.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));//gaps between the buttons
		mainPanel.add(moveButtons,BorderLayout.NORTH);
		moveButtons.setBackground(col);
		
		daysP=new JPanel(new GridLayout(1,7,3,3));
		for(int i=0;i<7;i++) {
			daysofW=new JLabel(days[i],JLabel.CENTER); //loop for initializing the days constants and adding them to the daysP
			daysofW.setFont(new Font("Arial",Font.BOLD,12));
			daysP.add(daysofW);
			daysofW.setForeground(Color.white);
			
			//daysofW.setEnabled(false);
		}
		dateP=new JPanel(new BorderLayout());
		dateP.setBackground(col);
		daysP.setBackground(col);
		daysPanel=new JPanel(new GridLayout(6,7,3,3));// for the dates
		daysPanel.setBackground(col);
		daysofM=new JButton[42];
		
		for(int i=0;i<42;i++) {
			daysofM[i]=new JButton();
			daysofM[i].setBackground(Color.white);
			//daysofM[j][i].setPreferredSize(new Dimension(40,40));
			daysofM[i].setMaximumSize(new Dimension (10,10));
			daysPanel.add(daysofM[i]);
			daysofM[i].addActionListener(handler);
		}
	
		setDateButtons(c);
		daysPanel.setMaximumSize(new Dimension(100,100));
		dateP.add(daysP,BorderLayout.NORTH);
		dateP.add(daysPanel,BorderLayout.CENTER);
		JPanel temp=new JPanel();
		temp.setSize(dateP.getWidth(), 400);
		temp.setBackground(col);
		dateP.add(temp,BorderLayout.SOUTH);
		mainPanel.add(dateP,BorderLayout.CENTER);
		mainPanel.setBackground(col);
		panel1=new JPanel(new BorderLayout(5,5));
		panel1.add(eastP,BorderLayout.EAST);
		panel1.add(mainPanel,BorderLayout.CENTER);
		
		dateforeastP=new JLabel("---");
		eastP.add(dateforeastP,BorderLayout.NORTH);
		dateforeastP.setForeground(Color.white);
		
		tasks=new JTextArea("                          Tasks for the day are:                              ");
		tasks.setEditable(false);
		JScrollPane vertical=new JScrollPane();
		vertical = new JScrollPane(tasks);
        vertical.setVerticalScrollBarPolicy(22);
        
        JPanel pp=new JPanel(new BorderLayout());
        pp.add(vertical,BorderLayout.EAST);
		eastP.add(pp,BorderLayout.CENTER);
	
		eastP.setBackground(col);
		Border b=BorderFactory.createRaisedBevelBorder();
		b=BorderFactory.createLineBorder(Color.lightGray);
		b=BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		eastP.setBorder(b);
		tasksButtons=new JPanel();
		add=new JButton("   ADD A TASK  ");
		add.setFont(font);
		add.addActionListener(handler);
		tasksButtons.add(add);
		tasksButtons.setBackground(col);
		eastP.add(tasksButtons,BorderLayout.SOUTH);
		//Locale l=new Locale("en");
		//DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy",l); 
		c=Calendar.getInstance();
		dateoftheday=new JLabel(String.format("                                                                                                                                           "+sdf.format(c.getTime())));
		dateoftheday.setFont(new Font("Arial",Font.BOLD,14));
		dateoftheday.setForeground(Color.white);
		clock=new JLabel("CLOCK");
		clock.setForeground(Color.white);
		int i=getFirstDayOfMonth(c)-2;
		c=Calendar.getInstance();
		daysofM[i+c.get(Calendar.DAY_OF_MONTH)].setBackground(Color.LIGHT_GRAY);
		numofday=i+c.get(Calendar.DAY_OF_MONTH);
		northP=new JPanel();
		northP.add(clock);
		northP.add(dateoftheday);
		northP.setBackground(col);
		panel1.add(northP,BorderLayout.NORTH);
		panel1.setBackground(col);
		add(panel1);	
		//c=Calendar.getInstance();
	}
	
	public int getFirstDayOfMonth(Calendar cal) {
		cal.set(Calendar.DAY_OF_MONTH,1);
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	public void setDateButtons(Calendar cal) {
		int i=getFirstDayOfMonth(cal)-1;
		int n=1;
		for (int j=0;j<i;j++) {
			daysofM[j].setVisible(false);
		}
		for(int j=i;j<cal.getActualMaximum(Calendar.DAY_OF_MONTH)+i;j++) {
			daysofM[j].setVisible(true);
			daysofM[j].setText(Integer.toString(n));
			n++;
		}
		for (int j=cal.getActualMaximum(Calendar.DAY_OF_MONTH)+i;j<42;j++) {	
			daysofM[j].setVisible(false);	
		}
		
		
	}
	private class ButtonHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()==previousY) {
				c.add(Calendar.YEAR, -1);
				setDateButtons(c);
				MMYY.setText(getMonthName(c)+"  -  "+c.get(Calendar.YEAR));
				disp-=12;
				daysofM[numofday].setBackground(Color.white);
				if(disp==0) daysofM[numofday].setBackground(Color.LIGHT_GRAY);
				setHighlight(c);
			}
			else if(e.getSource()==previousM) {
				c.add(Calendar.MONTH, -1);
				setDateButtons(c);
				MMYY.setText(getMonthName(c)+"  -  "+c.get(Calendar.YEAR));
				disp-=1;
				daysofM[numofday].setBackground(Color.white);
				if(disp==0) daysofM[numofday].setBackground(Color.LIGHT_GRAY);
				setHighlight(c);
			}
			else if(e.getSource()==nextM) {
				c.add(Calendar.MONTH, 1);
				setDateButtons(c);
				MMYY.setText(getMonthName(c)+"  -  "+c.get(Calendar.YEAR));
				disp+=1;
				daysofM[numofday].setBackground(Color.white);
				if(disp==0) daysofM[numofday].setBackground(Color.LIGHT_GRAY);
				setHighlight(c);
			}
			else if(e.getSource()==nextY) {
				c.add(Calendar.YEAR, 1);
				setDateButtons(c);
				MMYY.setText(getMonthName(c)+"  -  "+c.get(Calendar.YEAR));
				disp+=12;
				daysofM[numofday].setBackground(Color.white);
				if(disp==0) daysofM[numofday].setBackground(Color.LIGHT_GRAY);
				setHighlight(c);
			}
			else if(e.getSource()==add) {
				Tasks t=new Tasks();
				t.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				t.setLocation(50, 50);
				t.setVisible(true);
				t.setSize(720,350);
			} 
			else {
				int m=c.get(Calendar.MONTH);
				int y=c.get(Calendar.YEAR);
				int j=0,d;
				for(int i=0;i<42;i++) {
					if (e.getSource()==daysofM[i]) j=i;
				}
				int st=getFirstDayOfMonth(c);
				d=j-st+2;
				tasks.setText("                          Tasks for the day are:                              ");
				for(int i=0;i<task.size();i++) {
					if(task.get(i).dayT==d&& task.get(i).monthT==m&&task.get(i).yearT==y) {tasks.append(task.get(i).toString());tasks.append("\n"
							+ "*******************************");}
					
				}
				Calendar b=new GregorianCalendar();
				b.set(Calendar.MONTH,m);
				b.set(Calendar.YEAR,y);
				b.set(Calendar.DAY_OF_MONTH,d);
				dateforeastP.setText(sdf.format(b.getTime()));
				deleteDup();
				
				
				
			}
			
			
			
		}
	}
	public class Tasks extends JFrame {
		private JLabel l1,l2,l3,l4,l5;
		public JTextField title,location,startingTime,endTime,day;
		private JPanel p,pDate,pButtons,pTime,pTime1;
		public JComboBox month,year,hour,minute,hourE,minuteE,am0,am1;
		private String months[]= {" Jan "," Feb "," Mar "," Apr "," May "," Jun "," Jul "," Aug "," Sep "," Oct "," Nov "," Dec "};
		private String years[]= {"2019","2020","2021","2022"};
		private JButton addT,modify,delete,clear;
		public calender calen;
		private String[] h,m;

		public Tasks() {
			super("Tasks for the day");
			DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
			DecimalFormat twoDigit= new DecimalFormat("00",symbols);
			l1=new JLabel("   Title");
			l2=new JLabel("Location");
			l3=new JLabel("Starting Time");
			l4=new JLabel("End Time");
			l5=new JLabel("Date");
			month=new JComboBox(months);
			year=new JComboBox(years);
			pDate=new JPanel();
			day=new JTextField(4);
			day.setToolTipText("Enter a valid date");
			pDate.add(day);
			pDate.add(month);
			pDate.add(year);
			addT=new JButton("   ADD ");
			modify=new JButton(" MODIFY ");
			delete=new JButton(" DELETE ");
			delete.setToolTipText("DELETE THE TASK");
			clear=new JButton("CLEAR");
			clear.setToolTipText("CLEAR ALL FIELDS");
			pButtons=new JPanel();
			pButtons.add(addT);
			pButtons.add(modify);
			pButtons.add(delete);
			pButtons.add(clear);
			String a[]= {"AM","PM"};
			am0=new JComboBox(a);
			am1=new JComboBox(a);
			h=new String[12];
			m=new String[60];
			for (int i=0;i<12;i++) {
				h[i]=twoDigit.format(i+1);
			}
			for(int i=0;i<60;i++) {
				m[i]=twoDigit.format(i);
			}
			pTime=new JPanel();
			hour=new JComboBox(h);
			minute=new JComboBox(m);
			pTime1=new JPanel();
			hourE=new JComboBox(h);
			minuteE=new JComboBox(m);
			pTime1.add(hourE);
			pTime1.add(new JLabel(":"));
			pTime1.add(minuteE);
			pTime1.add(am1);
			pTime1.setBackground((new Color(94,123,141)));
			
			pTime.add(hour);
			pTime.add(new JLabel(":"));
			pTime.add(minute);
			pTime.add(am0);
			pTime.setBackground(new Color(94,123,141));
			title=new JTextField(17);
			location=new JTextField(17);
			p=new JPanel();
		    p.setLayout(new GridBagLayout());
		    GridBagConstraints c=new GridBagConstraints();
		    c.gridx=0;
		    c.gridy=0;
		    c.anchor=GridBagConstraints.LINE_END;
		    p.add(l1,c);
		    c.gridy++;
		    p.add(l2,c);
		    c.gridy++;
		    p.add(l5,c);
		    c.gridy++;
		    p.add(l3,c);
		    c.gridy++;
		    p.add(l4,c);
		    c.gridx=1;
		    c.gridy=0;
		    c.anchor=GridBagConstraints.LINE_START;
		    p.add(title,c);
		    c.gridy++;
		    p.add(location,c);
		    c.gridy++;
		    p.add(pDate,c);
		    c.gridy++;
		    p.add(pTime,c);
		    c.gridy++;
		    p.add(pTime1,c);
		    c.gridy=5;
		    c.gridx=0;
		    c.gridwidth=2;
		    c.weighty=0;
		    c.insets = new Insets(30,0,0,0); 
		    p.add(pButtons,c);
		    pDate.setBackground(new Color(94,123,141));
		    p.setBackground(new Color(94,123,141));
		    pButtons.setBackground(new Color(94,123,141));
		    BHandler Bhandler=new BHandler();
		    addT.addActionListener(Bhandler);
		    clear.addActionListener(Bhandler);
		    delete.addActionListener(Bhandler);
		    modify.addActionListener(Bhandler);
		    add(p);
		    calen= new calender();
			
		}

		private class BHandler implements ActionListener{
			
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==addT) {
					boolean f=true;
					Events ee=new Events();
					ee.titleT=title.getText();
					ee.locationT=location.getText();
					ee.dayT=Integer.parseInt(day.getText());
					ee.monthT=month.getSelectedIndex();
					ee.yearT=year.getSelectedIndex()+2019;
					ee.hourStart=hour.getSelectedIndex()+1;
					if(am0.getSelectedIndex()==1) ee.hourStart+=12;
					ee.minStart=minute.getSelectedIndex();
					ee.hourEnd=hourE.getSelectedIndex()+1;
					ee.minEnd=minuteE.getSelectedIndex();
					if(am1.getSelectedIndex()==1) ee.hourEnd+=12;
					Calendar cal=new GregorianCalendar();
					cal.set(Calendar.YEAR,ee.yearT);
					cal.set(Calendar.MONTH,ee.monthT);
					if(ee.hourEnd<ee.hourStart||(ee.hourEnd==ee.hourStart&&ee.minStart>ee.minEnd)||ee.hourEnd==ee.hourStart&&ee.minStart==ee.minEnd) f=false;
					if (ee.dayT>cal.getActualMaximum(Calendar.DAY_OF_MONTH)) f=false;
					boolean b=true;
					for(int i=0;i<task.size();i++) {
						if(task.get(i).dayT==ee.dayT&&task.get(i).monthT==ee.monthT&&task.get(i).yearT==ee.yearT&&(task.get(i).hourStart==ee.hourStart||(task.get(i).hourStart<ee.hourStart&&task.get(i).hourEnd>ee.hourEnd||task.get(i).hourEnd==ee.hourEnd)))
							b=false;
					
					}
					if(b==false)JOptionPane.showMessageDialog(null, "You Have a Task At The Same Time");
				    if(f&&b) {task.add(ee);
				    highlight(ee.dayT,ee.monthT,ee.yearT);
				    JOptionPane.showMessageDialog(null, "Task Added");
				    }
				    else if(!f)JOptionPane.showMessageDialog(null, "DATA INSERTED INVALID");
				    setHighlight(c);
				}
				else if (e.getSource()==clear) {
					title.setText(" ");
					location.setText(" ");
					day.setText(" ");
					hour.setSelectedIndex(0);
					minute.setSelectedIndex(0);
					am0.setSelectedIndex(0);
					am1.setSelectedIndex(0);
					month.setSelectedIndex(0);
					year.setSelectedIndex(0);
					hourE.setSelectedIndex(0);
					minuteE.setSelectedIndex(0);
				}
				else if(e.getSource()==delete) {
					boolean f=true;
					boolean b=false;
					Events ee=new Events();
					ee.titleT=title.getText();
					ee.locationT=location.getText();
					ee.dayT=Integer.parseInt(day.getText());
					ee.monthT=month.getSelectedIndex();
					ee.yearT=year.getSelectedIndex()+2019;
					ee.hourStart=hour.getSelectedIndex()+1;
					if(am0.getSelectedIndex()==1) ee.hourStart+=12;
					ee.minStart=minute.getSelectedIndex();
					ee.hourEnd=hourE.getSelectedIndex()+1;
					ee.minEnd=minuteE.getSelectedIndex();
					if(am1.getSelectedIndex()==1) ee.hourEnd+=12;
					Calendar cal=new GregorianCalendar();
					cal.set(Calendar.YEAR,ee.yearT);
					cal.set(Calendar.MONTH,ee.monthT);
					if(ee.hourEnd<ee.hourStart||(ee.hourEnd==ee.hourStart&&ee.minStart>ee.minEnd)) f=false;
					if (ee.dayT>cal.getActualMaximum(Calendar.DAY_OF_MONTH)) f=false;
				    if(f) {
				    	for(int i=0;i<task.size();i++)
				    	{
				    		if(task.get(i).equals(ee)) task.remove(i);
				    		{JOptionPane.showMessageDialog(null, "Task Deleted");
				    		b=true;
				    	}}
				    	unhighlight(ee.dayT,ee.monthT,ee.yearT);
				    	
				    }
				    else  JOptionPane.showMessageDialog(null, "Invalid Data");
				    if(!b) JOptionPane.showMessageDialog(null, "Could Not Find Task");
					
				}
				else if(e.getSource()==modify) {
					int j=0;
				if(z==false) {	
					int hS=0;
					int hE=0;
					
					if(am0.getSelectedIndex()==1) hS=12;
					if(am1.getSelectedIndex()==1) hE=12;
					hS+=hour.getSelectedIndex()+1;
					hE+=hourE.getSelectedIndex()+1;
					
					for (int i=0;i<task.size();i++) {
						if(task.get(i).dayT==Integer.parseInt(day.getText())) z=true;
						else z=false;
						if(task.get(i).monthT==month.getSelectedIndex()) z=true;
						else z=false;
						if(task.get(i).hourStart==hS&& task.get(i).hourEnd==hE)z =true;
						else z=false;
						if(task.get(i).yearT==year.getSelectedIndex()+2019) z=true;
						else z=false;
						if(task.get(i).minStart==minute.getSelectedIndex()&&task.get(i).minEnd==minuteE.getSelectedIndex()) z=true;
						else z=false;
						if(z) {j=i;break;}
						
						}
					if(z)JOptionPane.showMessageDialog(null, "TASK FOUND,PLEASE RE-ENTER THE MODIFIED TASK");
					else JOptionPane.showMessageDialog(null, "COULDN't FIND TASK");
				}
				else {
					task.get(j).titleT=title.getText();
					task.get(j).locationT=location.getText();
					task.get(j).dayT=Integer.parseInt(day.getText());
					task.get(j).monthT=month.getSelectedIndex();
					task.get(j).yearT=year.getSelectedIndex()+2019;
					task.get(j).hourStart=hour.getSelectedIndex()+1;
					if(am0.getSelectedIndex()==1) task.get(j).hourStart+=12;
					task.get(j).minStart=minute.getSelectedIndex();
					task.get(j).hourEnd=hourE.getSelectedIndex()+1;
					task.get(j).minEnd=minuteE.getSelectedIndex();
					if(am1.getSelectedIndex()==1) task.get(j).hourEnd+=12;
					JOptionPane.showMessageDialog(null, "TASK MODIFIED");
					z=false;
					  highlight(task.get(j).dayT,task.get(j).monthT,task.get(j).yearT);
					  setHighlight(c);
				}
				}
			}
			}


		}

	
	public String getMonthName(Calendar cal) {
		int n=cal.get(Calendar.MONTH);
		String months[]= {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
		return months[n];
	}
	public void highlight (int d,int m,int y) {
		Calendar cal=new GregorianCalendar();
		cal.set(Calendar.MONTH,m);
		cal.set(Calendar.YEAR,y);
		int n=getFirstDayOfMonth(cal);
		n+=d;
		if(cal.get(Calendar.MONTH)==c.get(Calendar.MONTH)) {daysofM[n-2].setForeground(Color.red);}
		
	}
	public void unhighlight (int d,int m,int y) {
		Calendar cal=new GregorianCalendar();
		cal.set(Calendar.MONTH,m);
		cal.set(Calendar.YEAR,y);
		int n=getFirstDayOfMonth(cal);
		n+=d;
		if(cal.get(Calendar.MONTH)==c.get(Calendar.MONTH)) {daysofM[n-2].setForeground(Color.black);}
		
	}
	
	public void setTimeLabel(String s) {
		clock.setText(s);
		
	}
	
	public void setHighlight(Calendar calen) {
		int n=getFirstDayOfMonth(calen);
		int j=0;
		for(int i=0;i<42;i++) daysofM[i].setForeground(Color.black);
		
		for (int i=0;i<task.size();i++) {
			if(task.get(i).monthT==(calen.get(Calendar.MONTH))&&task.get(i).yearT==calen.get(Calendar.YEAR)) {j=task.get(i).dayT+n-2;daysofM[j].setForeground(Color.RED);
			 }
		}
	}
	
	public void  deleteDup() {
		for (int i=0;i<task.size();i++) {
			for(int j=i+1;j<task.size();j++) {
				if(task.get(i).equals(task.get(j))) task.remove(j);
			}
		}
	}
	
	public static void 	main(String[] args) {
		// TODO Auto-generated method stub
		calender c=new calender();
		MyRunnable r=new MyRunnable(c);
		Thread t=new Thread(r);
		t.start();
		c.setDefaultCloseOperation(EXIT_ON_CLOSE);
		c.setVisible(true);
		c.setSize(720,350);
	      
	      
	      
	}}


