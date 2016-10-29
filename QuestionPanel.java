import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.io.*;
import java.util.Date;
import java.util.*;

class TimeThread extends Thread
{
	int sec;
	CardLayout cl;
	JPanel p2;
	TestFrame t;
	String us,tname;
	int correct=0,incorrect=0,empty=0,result=0;
	TimeThread(String q,String w,TestFrame tf,CardLayout c,JPanel p)
	{
		
		cl=c;
		p2=p;
		t=tf;
		tname=w;
		us=q;
	}
	public void run()
	{
		int i;
		sec=20;
		try
		{
			for(i=sec;i>=1;i--)
			{
				t.l.setText(i+"");
				Thread.sleep(1000);
			}
		}catch(Exception e)
		{
		}
		if(t.g==10)
		{
			int c=p2.getComponentCount();
				int correct=0;
				int empty=0; 
				int incorrect=0;
				int result;
				int o;

				for(o=0; o<c; o++)
				{
					QuestionPanel qp=(QuestionPanel)p2.getComponent(o);
					Question Q=qp.Q;

				
					if(Q.userAns.equals(Q.ca))
					correct++;
					else
					if(Q.userAns.equals(""))
					empty++;
					else
					incorrect++;
				}
				
				result=correct;

				Date dt=new Date();
				int dd,mm,yy;
				dd=dt.getDate();
				mm=dt.getMonth();
				yy=dt.getYear();
				String cd=dd+"/"+mm+"/"+yy;

				int mmm,hh,ss;
				ss=dt.getSeconds();
				mmm=dt.getMinutes();
				hh=dt.getHours();
				String ct=hh+":"+mmm+":"+ss;
				Connection cn;
				PreparedStatement pstm;
	
						try
						{

							Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
							cn=DriverManager.getConnection("Jdbc:Odbc:QuizAppDSN");
							pstm=cn.prepareStatement("Insert into  Tests(TestDate,TestTime,UserID,Subject,TotalQuestions,CorrectAnswers,Empty,IncorrectAnswers,Result) Values(?,?,?,?,?,?,?,?,?)");
									pstm.setString(1,cd);
									pstm.setString(2,ct);
									pstm.setString(3,us);
									pstm.setString(4,tname);
									pstm.setString(5,"10");
									pstm.setInt(6,correct);
									pstm.setInt(7,empty);
									pstm.setInt(8,incorrect);
									pstm.setInt(9,result);
									pstm.executeUpdate();
									JOptionPane.showMessageDialog(t,"Test Record Submitted");
									cn.close();
									
							}catch(Exception e)
							{
								JOptionPane.showMessageDialog(t,e.toString());
							}
				t.dispose();
				SubmitFrame sf=new SubmitFrame(us,tname,correct,incorrect,empty,result);
				sf.setSize(800,600);
				sf.setVisible(true);				
			
			
			t.dispose();
		}
		t.g++;
	cl.next(p2);
	TimeThread tt=new TimeThread(us,tname,t,cl,p2);
	tt.start();

	}
}

		


class QuestionPanel extends JPanel implements ItemListener
{

		JLabel l1,tm,pic;
		ImageIcon ic;
		JRadioButton r1,r2,r3,r4;
		ButtonGroup bg;
		JPanel cp;
		Question Q;

		QuestionPanel(Question q)
		{
			Q=q;
			tm=new JLabel("");
			l1=new JLabel("Question ("+q.qno+")  "+q.q);
			Font f=new Font("Arial",Font.ITALIC,25);
			l1.setFont(f);
			l1.setBackground(Color.yellow);
			l1.setForeground(Color.red);
			r1=new JRadioButton(q.a1);
			r2=new JRadioButton(q.a2);
			r3=new JRadioButton(q.a3);
			r4=new JRadioButton(q.a4);
			bg=new ButtonGroup();
			bg.add(r1);bg.add(r2); bg.add(r3); bg.add(r4);
			Font f1=new Font("Arial",Font.ITALIC,20);
			r1.setFont(f1);
			r1.setForeground(Color.blue);
			r2.setFont(f1);
			r2.setForeground(Color.blue);
			r3.setFont(f1);
			r3.setForeground(Color.blue);
			r4.setFont(f1);
			r4.setForeground(Color.blue);
			ic=new ImageIcon(q.pic);
			pic=new JLabel(ic);
			cp=new JPanel(new GridLayout(6,1,10,20));
			cp.add(tm); cp.add(l1); cp.add(r1); cp.add(r2); cp.add(r3); cp.add(r4);   						
			setLayout(new BorderLayout());
			add(cp,"Center");
			add(pic,"East");
			r1.addItemListener(this);
			r2.addItemListener(this);
			r3.addItemListener(this);
			r4.addItemListener(this);
			

    		}
		public void itemStateChanged(ItemEvent ie)
		{
			if(r1.isSelected())
				Q.userAns=Q.a1;
			else
			if(r2.isSelected())
				Q.userAns=Q.a2;

			else
			if(r3.isSelected())
				Q.userAns=Q.a3;
			else
			if(r4.isSelected())
				Q.userAns=Q.a4;
				

		}

}

			
				
		
				

			

				
		
		