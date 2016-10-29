import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;
class MyTextField extends JTextField
{
		MyTextField(String t)
		{
				super(t);
				Font f=new Font("Arial",Font.BOLD,18);
				setFont(f);
				setBackground(Color.white);
				setForeground(Color.pink);
		}

		MyTextField(int t)
		{
				super(t);
				Font f=new Font("Arial",Font.BOLD,18);
				setFont(f);
				setBackground(Color.white);
				setForeground(Color.pink);
		}
}



class MyLabel extends JLabel
{

		MyLabel(String t)
		{
				super(t);
				Font f=new Font("Arial",Font.BOLD,25);
				setFont(f);
				setBackground(Color.yellow);
				setForeground(Color.orange);
		}

		

	
}
			
class QuizApp extends JFrame implements ActionListener
{

			JButton b,b1,b2;
			MyLabel l1,l2,l3,l4,l5,l6,l7,l9,l10;
			JLabel l8;
			MyTextField  t1,t4,t5,t6,t7;
			JPasswordField pf1,pf2,pf3;
			JComboBox c1;
			ImageIcon ic;
			JPanel p1,p2,p3,p4,p5,p6,p7,p8;
			ImagePanel ip;

			QuizApp()
			{

					l1=new MyLabel("UserId");
					l2=new MyLabel("Password");
					l3=new MyLabel("Confirm Password");
					l4=new MyLabel("UserName");
					l5=new MyLabel("Gender");
					l6=new MyLabel("PhotoGraph");
					l7=new MyLabel("City");
					l9=new MyLabel("UserId");
					l10=new MyLabel("Password");

					b=new JButton("Sign In");
					b1=new JButton("Register");
					b2=new JButton("Browse");


					t1=new MyTextField(20);
					t4=new MyTextField(20);
					t5=new MyTextField(20);
					t6=new MyTextField(20);	
					t7=new MyTextField(20);
					

					pf1=new JPasswordField(20);
					pf2=new JPasswordField(20);
					pf3=new JPasswordField(20);

					c1=new JComboBox();
					c1.addItem("Male");
					c1.addItem("Female");
					

					ImageIcon ic=new ImageIcon("b.png");
					l8=new JLabel(ic);

					p1=new JPanel();
					p1.add(l9);
					p1.add(t7);
					p1.add(l10);
					p1.add(pf1);
					p1.add(b);

					p2=new JPanel();
					p2.add(l8);

					p3=new JPanel();
					p3.setLayout(new GridLayout(5,2,10,10));
					p3.add(l1);
					p3.add(t1);
					p3.add(l2);
					p3.add(pf2);
					p3.add(l3);
					p3.add(pf3);
					p3.add(l4);
					p3.add(t4);
					p3.add(l5);
					p3.add(c1);
					
					p4=new JPanel();
					p5=new JPanel();
					p6=new JPanel();
					p7=new JPanel();
					p8=new JPanel();

					p8.add(t5);
					p8.add(b2);

					p4.setLayout(new GridLayout(1,3,10,10));
					p4.add(l6);
					p4.add(p8);

					

					p5.setLayout(new GridLayout(2,2,10,10));
					p5.add(l7);
					p5.add(t6);
					p5.add(b1);

					p6.setLayout(new BorderLayout());
					p6.add(p3,"North");
					p6.add(p4,"Center");
					p6.add(p5,"South");

					
					p7.setLayout(new BorderLayout());
					p7.add(p1,"North");
					p7.add(p2,"West");
					p7.add(p6,"East");

					ip=new ImagePanel(new ImageIcon("b1.jpg").getImage());	

					BorderLayout bl=new BorderLayout();
								
					setLayout(bl);
					bl.getContentPane().add(ip);
					bl.add(p7,"North");

					b.addActionListener(this);
					b1.addActionListener(this);
					b2.addActionListener(this);

}

public void actionPerformed(ActionEvent ae)
{

			String s;
			s=ae.getActionCommand();
			File fs;
			JFileChooser fc;
			
			if(s.equals("Sign In"))
			{
				String uid=t7.getText();
				String pwd=pf1.getText();
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
						SignInFrame sf=new SignInFrame(uid);
						sf.setSize(800,600);
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
				
			if(s.equals("Browse"))
			{

				fc=new JFileChooser();
				fc.showOpenDialog(this);
				fs=fc.getSelectedFile();

				t5.setText(fs+"");
			}

			if(s.equals("Register"))
			{

				Connection cn;
				PreparedStatement pstm;
				ResultSet rs;
			
				String usd,pw,cpw,un,g,photo,city;
				usd=t1.getText();
				pw=pf2.getText();
				cpw=pf3.getText();
				un=t4.getText();
				photo=t5.getText();
				city=t6.getText();
				Object m=c1.getSelectedItem();
				String d=(String)m;

				if(usd.equals("") || usd.equals("") || usd.equals("") || usd.equals("") || usd.equals("") || usd.equals("") || usd.equals("") )
				{
					JOptionPane.showMessageDialog(this,"Please fill all the fields");
				}

				else
				{
					if(pw.equals(cpw))
					{
							try
							{

									Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
									cn=DriverManager.getConnection("Jdbc:Odbc:QuizAppDSN");
									pstm=cn.prepareStatement("Insert into Users Values(?,?,?,?,?,?)");
									pstm.setString(1,usd);
									pstm.setString(2,pw);
									pstm.setString(3,un);
									pstm.setString(4,d);
									pstm.setString(5,photo);
									pstm.setString(6,city);

									pstm.executeUpdate();
									JOptionPane.showMessageDialog(this,"Successfully Registered");
									t1.setText("");
									pf2.setText("");	
									pf3.setText("");
									t4.setText("");
									t5.setText("");	
									t6.setText("");					
							}catch(Exception e)
							{
								JOptionPane.showMessageDialog(this,e.toString());
							}
							
					}
				
					else
					{
						JOptionPane.showMessageDialog(this,"Password not Match");
					}
				}
			}
				
}

public static void main(String arg[])
{
			QuizApp q=new QuizApp();
			q.setSize(800,600);
			q.setVisible(true);
			q.setTitle("QuizApp");
			
}
}
					

					

					

					
					