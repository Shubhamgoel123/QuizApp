import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class SignInFrame extends JFrame implements ActionListener
{

			JMenuBar mb;
			JMenu m1,m2,m4,m5,m6;
			JMenuItem i1,i2,i3,i4,i5,i6,i7,m3;
			JLabel l,l1;
			String t;
			ImageIcon ic;
			JPanel p;
			
			SignInFrame(String h)
			{
					ic=new ImageIcon("q.jpg");
					l=new JLabel(ic);
					setLayout(new FlowLayout());
					t=h;
					mb=new JMenuBar();
					m1=new JMenu("Options");
					m2=new JMenu("Exit");
					m3=new JMenuItem("Mega Test");
					m4=new JMenu("Tv Series");
					m5=new JMenu("Start New Test");
					m6=new JMenu("Branch");
					i1=new JMenuItem("Edit Profile");
					i2=new JMenuItem("My Test Results");
					i3=new JMenuItem("Exit");
					i4=new JMenuItem("Friends");
					i5=new JMenuItem("Game of Thrones");
					i6=new JMenuItem("Sherlocks Homes");
					i7=new JMenuItem("IT");

					mb.add(m1);
					mb.add(m2);
					m1.add(i1);
					m1.add(i2);
					m1.add(m5);
					m2.add(i3);
					m5.add(m3);
					m5.add(m4);
					m5.add(m6);
					m4.add(i4);
					m4.add(i5);
					m4.add(i6);
					m6.add(i7);

					setJMenuBar(mb);
					setTitle("QuizApp >"+t);

					l1=new JLabel("");
					p=new JPanel();
					p.setLayout(new BorderLayout());
					p.add(l1,"North");
					p.add(l,"South");
					add(p);			
					i1.addActionListener(this);
					i2.addActionListener(this);
					i3.addActionListener(this);
					i4.addActionListener(this);
					i7.addActionListener(this);
					i5.addActionListener(this);
					i6.addActionListener(this);
					

			}


public void actionPerformed(ActionEvent ae)
{

			String s;
			s=ae.getActionCommand();

			if(s.equals("Friends") || s.equals("Game of Thrones") ||  s.equals("Sherlock Holmes") || s.equals("IT"))
			{
					TestFrame tf=new TestFrame(s,t);		
					tf.setSize(1367,810);
					tf.setVisible(true);
					tf.setTitle("QuizApp >"+t+">"+s);
			}

			if(s.equals("Edit Profile"))
			{
				EditProfile ep=new EditProfile(t);
				ep.setSize(1367,810);
				ep.setVisible(true);
				
			}

			if(s.equals("My Test Results"))
			{
				MyTestResults mtr=new MyTestResults(t);
				mtr.setSize(1367,810);
				mtr.setVisible(true);
				mtr.setTitle(t+"    Test Results");
				
			}
}


}