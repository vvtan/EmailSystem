package send;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import setup.MailSenderInfo;
import setup.SimpleMailSender;

public class main extends JFrame implements ActionListener{
	
	private JLabel slable=new JLabel("发信人");
	private JLabel rlable=new JLabel("收信人");
	private JLabel tilable=new JLabel("标题");
	private JLabel colable =new JLabel("内容");
	static private JTextField sendName=new JTextField(60);
	static private JTextField receiveName=new JTextField(60);
	static private JTextField title=new JTextField(60);
	static private JTextField content=new JTextField(60);
	static private JButton send=new JButton("发送");
	
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
		//这个类主要是设置邮件  
		MailSenderInfo mailInfo = new MailSenderInfo();
		String sName,rName,tl,cont;
		sName=sendName.getText().trim();
		rName=receiveName.getText().trim();
		cont=content.getText().trim();
		tl=title.getText().trim();
		System.out.println(sName+" "+rName+" "+tl+" "+cont);	
		   
	    mailInfo.setMailServerHost("smtp.sina.com.cn"); //不同邮箱的服务器不同 
	    mailInfo.setMailServerPort("25");   //端口号
	    mailInfo.setValidate(true);   
	    mailInfo.setUserName("test605592311@sina.com");  //已注册的邮箱，例如QQ或163邮箱
	    mailInfo.setPassword("test123");//邮箱密码   
	    mailInfo.setFromAddress(sName);   
	    mailInfo.setToAddress(rName); 
	    mailInfo.setSubject(tl);   
	    mailInfo.setContent(cont);   
	    	//这个类主要来发送邮件 
	    SimpleMailSender sms = new SimpleMailSender();  
	    //此处只要选择一种发送形式即可
	    sms.sendTextMail(mailInfo);//发送文体格式
	   // sms.sendHtmlMail(mailInfo);//发送html格式 
	}
    public static void main(String[] args){  
      
      new main();
    }  
}
