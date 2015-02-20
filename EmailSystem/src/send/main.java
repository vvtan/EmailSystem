package send;

import setup.MailSenderInfo;
import setup.SimpleMailSender;

public class main {
	
    public static void main(String[] args){  
        //这个类主要是设置邮件  
      MailSenderInfo mailInfo = new MailSenderInfo();   
      mailInfo.setMailServerHost("smtp.163.com");   
      mailInfo.setMailServerPort("25");   
      mailInfo.setValidate(true);   
      mailInfo.setUserName("15078333563@163.com");   
      mailInfo.setPassword("y17623M");//您的邮箱密码   
      mailInfo.setFromAddress("15078333563@163.com");   
      mailInfo.setToAddress("987047845@qq.com"); 
      mailInfo.setSubject("Test2");   
      mailInfo.setContent("This is a test E-mail");   
        //这个类主要来发送邮件  
      SimpleMailSender sms = new SimpleMailSender();  
         sms.sendTextMail(mailInfo);//发送文体格式   
         sms.sendHtmlMail(mailInfo);//发送html格式  
    }  
}
