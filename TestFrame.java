import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.io.*;
import java.util.Date;
import java.util.*;
class Question
{
	String q,a1,a2,a3,a4,ca,pic,userAns;
	int qno;
	Question()
	{
		q=a1=a2=a3=a4=ca=pic=userAns="";
		qno=0;
	}
}

class TestFrame extends JFrame implements ActionListener
{
	String tname,us;
	JButton b1,b2,b3;
	JPanel p1,p2,p3;
	JLabel l;
	CardLayout cl;	
	TimeThread tt;
	ArrayList al;
	int g;
		
	TestFrame(String j,String t)
	{
		
		tname=j;
		us=t;
		b1=new JButton("Next");
		b2=new JButton("Quit");
		b3=new JButton("Submit");	
		l=new JLabel("");
		p1=new JPanel();
		p1.add(l);
		p3=new JPanel();
		p3.add(b1);
		p3.add(b2);
		p3.add(b3);
		p2=new JPanel();
		cl=new CardLayout();
		p2.setLayout(cl);
		try
		{
			Connection cn;
			PreparedStatement pstm;
			ResultSet rs;
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			cn=DriverManager.getConnection("Jdbc:Odbc:QuizAppDSN");
			pstm=cn.prepareStatement("Select * from Questions where Subject=?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			pstm.setString(1,tname);
			rs=pstm.executeQuery();
			al=new ArrayList();
			

			int i=0;
			while(rs.next())
			{
				Question q=new Question();
				q.q=rs.getString(2);
				q.pic=rs.getString(4);
				q.a1=rs.getString(5);
				q.a2=rs.getString(6);
				q.a3=rs.getString(7);
				q.a4=rs.getString(8);
				q.ca=rs.getString(9);
				al.add(q);
				i++;
			}
			int z=1;
			while(i>1 && z<=10)
			{			
				int qn=(int)Math.floor(Math.random()*i);
				Question q=(Question)al.get(qn);
				q.qno=z;
				QuestionPanel qp=new QuestionPanel(q);
				p2.add(qp,"q"+i);
				al.remove(qn);
				i--;
				z++;
			}
			tt=new TimeThread(us,tname,this,cl,p2);
			tt.start();
			g=1;

		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,e.toString());
			
		}

		setLayout(new BorderLayout());
		add(p1,"North");
		add(p2,"Center");
		add(p3,"South");
			
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		

	}

public void actionPerformed(ActionEvent ae)
{

					
		String s=ae.getActionCommand();
		if(s.equals("Quit"))
		{
				System.exit(0);
		}

		else
		if(s.equals("Next"))
		{
			if(g==10)
			{
				int c=p2.getComponentCount();
				int correct=0;
				int empty=0; 
				int incorrect=0;
				int result;
				int i;

				for(i=0; i<c; i++)
				{
					QuestionPanel qp=(QuestionPanel)p2.getComponent(i);
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
									JOptionPane.showMessageDialog(this,"Test Record Submitted");
									cn.close();
									
							}catch(Exception e)
							{
								JOptionPane.showMessageDialog(this,e.toString());
							}
				dispose();
				SubmitFrame sf=new SubmitFrame(us,tname,correct,incorrect,empty,result);
				sf.setSize(800,600);
				sf.setVisible(true);				
			
			}
			tt.stop();
			cl.next(p2);
			tt=new TimeThread(us,tname,this,cl,p2);
			tt.start();
			g++;
		}

		else
		if(s.equals("Submit"))
		{
		
				int c=p2.getComponentCount();
				int correct=0;
				int empty=0; 
				int incorrect=0;
				int result;
				int i;

				for(i=0; i<c; i++)
				{
					QuestionPanel qp=(QuestionPanel)p2.getComponent(i);
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
									JOptionPane.showMessageDialog(this,"Test Record Submitted");
									cn.close();
									
							}catch(Exception e)
							{
								JOptionPane.showMessageDialog(this,e.toString());
							}
				SubmitFrame sf=new SubmitFrame(us,tname,correct,incorrect,empty,result);
				sf.setSize(800,600);
				sf.setVisible(true);				
			


						}

		
}


		
}