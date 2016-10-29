import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class SubmitFrame extends JFrame
{
		String t,k;
		int c,i,e,r;
		MyLabel  l1,l2,l3,l4,l5,l6; 
		JPanel p;
		SubmitFrame(String us,String tname,int correct, int incorrect, int empty, int result)
			{

				t=us;
				k=tname;
				c=correct;
				i=incorrect;
				e=empty;
				r=result;

				l1=new MyLabel("User Id         ="+"   "+t);
				l2=new MyLabel("Subject         ="+"   "+k);
				l3=new MyLabel("Correct         ="+"   "+c);
				l4=new MyLabel("Incorrect       ="+"   "+i);
				l5=new MyLabel("Empty Fields    ="+"   "+e);
				l6=new MyLabel("Result          ="+"   "+r);
				
				p=new JPanel();
				p.setLayout(new GridLayout(6,1,10,10));
				p.add(l1);
				p.add(l2);
				p.add(l3);
				p.add(l4);
				p.add(l5);
				p.add(l6);

				setLayout(new FlowLayout());
				add(p);


				
				
			}
}