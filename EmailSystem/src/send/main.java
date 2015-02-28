package send;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import analysis.SimpleStoreMails;
import setup.MailSenderInfo;
import setup.SimpleMailSender;

public class main extends JFrame implements ActionListener{
	
	static MailSenderInfo mailInfo = new MailSenderInfo();
	SimpleStoreMails ssm=new SimpleStoreMails();
	static String sn=mailInfo.getUserName();
	
	private JLabel slable=new JLabel("发信人");
	private JLabel rlable=new JLabel("收信人");
	private JLabel tilable=new JLabel("标题");
	private JLabel colable =new JLabel("内容");
	static private JTextField sendName=new JTextField(sn);
	static private JTextField receiveName=new JTextField(60);
	static private JTextField title=new JTextField(60);
	static private JTextField content=new JTextField(60);
	static private JButton send=new JButton("发送");
	static private JButton recive=new JButton("接收");
	static private JButton look=new JButton("附件");
	 
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
		add(recive);
		add(look);
		
		
		send.addActionListener(this);
		recive.addActionListener(this);
		look.addActionListener(this);
		
		pack();
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==send){
			
			sendmail();
		}
		if(e.getSource()==recive){
			try {
				ssm.receiveEmail();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==look){
			
			JFileChooser choose = new JFileChooser();
			//弹出选择框
			int returnVal = choose.showOpenDialog(null);
			// 如果是选择了文件
			if(JFileChooser.APPROVE_OPTION == returnVal){
			       //打印出文件的路径
				String filename=String.valueOf(choose.getSelectedFile());
				mailInfo.setAttachFileNames(filename);
				System.out.println(mailInfo.getAttachFileNames());
			}
		}
	}
	
	public static void sendmail(){
		//这个类主要是设置邮件  
		
		String sName,rName,tl,cont,dir;
		sName=mailInfo.getUserName();
		rName=receiveName.getText().trim();
		cont=content.getText().trim();
		tl=title.getText().trim();
		System.out.println(rName+" "+tl+" "+cont);	
		   
	    mailInfo.setMailServerHost("smtp.sina.com.cn"); //不同邮箱的服务器不同 
	    mailInfo.setMailServerPort("25");   //端口号
	    mailInfo.setValidate(true);   
	    mailInfo.setFromAddress(mailInfo.getUserName());   
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
