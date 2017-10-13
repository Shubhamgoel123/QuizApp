import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;

class StartGame extends JFrame implements ActionListener
{
		JButton b,b1;
		MyLabel l,l1;
		MyTextField t;
		JPasswordField pf;
		public StartGame()
		{
			setLayout(new BorderLayout());
			setContentPane(new JLabel(new ImageIcon("l.jpg")));
			setLayout(new FlowLayout());
			b=new JButton("Sign In");             //BUTTON FOR SIGN IN
			b1=new JButton("Sign Up");
			l=new MyLabel("User Id");
			l1=new MyLabel("Password");
			t=new MyTextField(20);
			pf=new JPasswordField(20);
			add(l);add(t);add(l1);add(pf);add(b);add(b1);

			b.addActionListener(this);
			b1.addActionListener(this);
		}

		public static void main(String arg[])
		{

			StartGame sg=new StartGame();
			sg.setTitle("Quizup Startup");
			sg.setSize(1367,768);
			sg.setVisible(true);
		}

		public void actionPerformed(ActionEvent ae)
		{
				String s=ae.getActionCommand();
				if(s.equals("Sign In"))
				{
					String uid=t.getText();
				String pwd=pf.getText();
				try
				{
					
					Connection cn;
					PreparedStatement pstm;
					ResultSet rs;
			
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					cn=DriverManager.getConnection("Jdbc:Odbc:QuizAppDSN");
					pstm=cn.prepareStatement("Select * from Users where UserId=? and Password=?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
					pstm.setString(1,uid);
					pstm.setString(2,pwd);
					rs=pstm.executeQuery();

					if(rs.next())
					{
						dispose();
						SignInFrame sf=new SignInFrame(uid);
						sf.setSize(1367,810);
						sf.setVisible(true);
					}

					else
					{
						JOptionPane.showMessageDialog(this,"UserId or Password Incorrect");
					}
				}catch(Exception e)
				{
					JOptionPane.showMessageDialog(this,e.toString());
				}
				}

				else
				if(s.equals("Sign Up"))
				{
					dispose();
					QuizApp qp=new QuizApp();
					qp.setSize(1367,810);
					qp.setVisible(true);
				}
				
		}
}
