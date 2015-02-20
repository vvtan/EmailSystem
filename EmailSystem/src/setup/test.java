package setup;

import javax.mail.*;
import java.util.*;

import javax.mail.internet.*;
/**
* @author Mo
*/
public class test
{
	String host="";
	String user="";
	String password="";
	
public void setHost(String host)
{
	this.host=host;
	}

public void setAccount(String user,String password)
{
	 this.user=user;
	 this.password=password;
}

public void send(String from,String to,String subject,String content)
{
	 Properties props = new Properties();
	 props.put("mail.smtp.host", host);//ָ��SMTP������
	 props.put("mail.smtp.auth", "true");//ָ���Ƿ���ҪSMTP��֤
	 try
 {
		 
  Session mailSession = Session.getDefaultInstance(props);
  
  mailSession.setDebug(true);//�Ƿ��ڿ���̨��ʾdebug��Ϣ
  
  Message message=new MimeMessage(mailSession);
  message.setFrom(new InternetAddress(from));//������
  message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));//�ռ���
  
  message.setSubject(subject);//�ʼ�����
  message.setText(content);//�ʼ�����
  message.saveChanges();
  
  Transport transport = mailSession.getTransport("smtp");
  transport.connect(host, user, password);
  transport.sendMessage(message, message.getAllRecipients());
  transport.close();
  
 }catch(Exception e)
 {
	 System.out.println(e);
 }
 
}
public static void main(String args[])
{
	Scanner sc=new Scanner(System.in);
	String text;
	text=sc.nextLine();
	test sm=new test();
	sm.setHost("smtp.163.com");//ָ��Ҫʹ�õ��ʼ�������
	sm.setAccount("15078333563@163.com","y17623M");//ָ���ʺź�����
 /*
* @param String �����˵ĵ�ַ
  * @param String �ռ��˵�ַ
  * @param String �ʼ�����
  * @param String �ʼ�����
 */
 sm.send("15078333563@163.com","987047845@qq.com","test",text);
}
} 