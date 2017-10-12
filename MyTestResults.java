import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.*;

class MyTestResults extends JFrame
{
			String k;
			Connection cn;
			PreparedStatement pstm;
			ResultSet rs;
			JTable t;
			MyTestResults(String h)
			{

				k=h;
				String head[]={"Test No","TestDate","TestTime","UserId","Subject","TotalQuestions","CorrectAnswers","Empty","IncorrectAnswers","Result"};
				String data[][];
				String td,tt,usd,s;
				int i,c,tn,tq,ca,em,ia,r,j=1;
				MyLabel l;
				try
				{
					l=new MyLabel("Your Test Results");

					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					cn=DriverManager.getConnection("Jdbc:Odbc:QuizAppDSN");
					pstm=cn.prepareStatement("Select* from Tests where UserId=?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
					pstm.setString(1,k);
					rs=pstm.executeQuery();
					

					c=0;
					while(rs.next())
					{

						c++;
					}

					data=new String[c][10];
					rs.first();
					i=0;

					do
					{
						
						tn=rs.getInt(1);
						td=rs.getString(2);
						tt=rs.getString(3);
						usd=rs.getString(4);
						s=rs.getString(5);
						tq=rs.getInt(6);
						ca=rs.getInt(7);
						em=rs.getInt(8);
						ia=rs.getInt(9);
						r=rs.getInt(10);
						data[i][0]=j+"";
						data[i][1]=td;
						data[i][2]=tt;
						data[i][3]=usd;
						data[i][4]=s;
						data[i][5]=tq+"";
						data[i][6]=ca+"";
						data[i][7]=em+"";
						data[i][8]=ia+"";
						data[i][9]=r+"";
						i++;
						j++;
					}
					while(rs.next());
					rs.close();
					t=new JTable(data,head);
					JScrollPane jsp=new JScrollPane(t);
					
					setLayout(new BorderLayout());
					add(jsp,"Center");
					add(l,"North");
					
					
				}catch(Exception e)
				{
					System.out.println(e.toString());
				}

		}
}
