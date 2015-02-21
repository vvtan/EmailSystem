package setup;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.accessibility.Accessible;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EmailGUI extends JFrame implements ActionListener{
	
	private JTextField sendName=new JTextField(60);
	private JTextField receiveName=new JTextField(60);
	private JTextField content=new JTextField(60);
	private JButton send=new JButton("·¢ËÍ");
	
	MailSenderInfo mailInfo = new MailSenderInfo();
	 
	public EmailGUI() {
		// TODO Auto-generated constructor stub
		super("EmailSystem");
		setLayout(new FlowLayout());
		add(sendName);
		add(receiveName);
		add(content);
		add(send);
		send.addActionListener(this);
		pack();
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==send){
			String sName,rName,cont;
			sName=sendName.getText().trim();
			rName=receiveName.getText().trim();
			cont=content.getText().trim();
			System.out.println(sName+" "+rName+" "+cont);
		}
	}
}
