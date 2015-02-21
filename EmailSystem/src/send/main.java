package send;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import setup.EmailGUI;
import setup.MailSenderInfo;
import setup.SimpleMailSender;

public class main extends JFrame implements ActionListener{
	
	private JLabel slable=new JLabel("������");
	private JLabel rlable=new JLabel("������");
	private JLabel tilable=new JLabel("����");
	private JLabel colable =new JLabel("����");
	static private JTextField sendName=new JTextField(60);
	static private JTextField receiveName=new JTextField(60);
	static private JTextField title=new JTextField(60);
	static private JTextField content=new JTextField(60);
	static private JButton send=new JButton("����");
	
	MailSenderInfo mailInfo = new MailSenderInfo();
	 
	public main() {
		// TODO Auto-generated constructor stub
		super("EmailSystem");
		setLayout(new FlowLayout());
		add(slable);
		add(sendName);
		add(rlable);
		add(receiveName);
		add(tilable);
		add(title);
		add(colable);
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
			
			sendmail();
		}
	}
	public static void sendmail(){
		
		MailSenderInfo mailInfo = new MailSenderInfo();
		String sName,rName,tl,cont;
		sName=sendName.getText().trim();
		rName=receiveName.getText().trim();
		cont=content.getText().trim();
		tl=title.getText().trim();
		System.out.println(sName+" "+rName+" "+tl+" "+cont);	
		   
	    mailInfo.setMailServerHost("smtp.163.com");   
	    mailInfo.setMailServerPort("25");   
	    mailInfo.setValidate(true);   
	    mailInfo.setUserName("XXXXX");  //���������� 
	    mailInfo.setPassword("XXXXX");//������������   
	    mailInfo.setFromAddress(sName);   
	    mailInfo.setToAddress(rName); 
	    mailInfo.setSubject(tl);   
	    mailInfo.setContent(cont);   
	        //�������Ҫ�������ʼ�  
	    SimpleMailSender sms = new SimpleMailSender();  
	    sms.sendTextMail(mailInfo);//���������ʽ   
	    sms.sendHtmlMail(mailInfo);//����html��ʽ 
	}
    public static void main(String[] args){  
        //�������Ҫ�������ʼ�  
      new main();
    }  
}
