package send;

import setup.MailSenderInfo;
import setup.SimpleMailSender;

public class main {
	
    public static void main(String[] args){  
        //�������Ҫ�������ʼ�  
      MailSenderInfo mailInfo = new MailSenderInfo();   
      mailInfo.setMailServerHost("smtp.163.com");   
      mailInfo.setMailServerPort("25");   
      mailInfo.setValidate(true);   
      mailInfo.setUserName("XXXX");   
      mailInfo.setPassword("XXXX");//������������   
      mailInfo.setFromAddress("XXXXX");   
      mailInfo.setToAddress("XXXXX"); 
      mailInfo.setSubject("Test2");   
      mailInfo.setContent("This is a test E-mail");   
        //�������Ҫ�������ʼ�  
      SimpleMailSender sms = new SimpleMailSender();  
         sms.sendTextMail(mailInfo);//���������ʽ   
         sms.sendHtmlMail(mailInfo);//����html��ʽ  
    }  
}
