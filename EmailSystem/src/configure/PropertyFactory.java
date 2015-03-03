package configure;

import java.util.Properties;

//属性制造工厂
public class PropertyFactory {
	public Properties getProperties(String protocol, String server ,String isAuth) {
		Properties prop = new Properties();
		//pop3协议
		if (protocol.equals("pop3")) 
		{
			prop.setProperty("mail.store.protocol", protocol);
			prop.setProperty("mail.pop3.host", server);
			prop.setProperty("mail.pop3.auth", isAuth);
		}
		//smtp协议
		else if(protocol.equals("smtp"))
		{
			prop.setProperty("mail.transport.protocol", protocol);
			prop.setProperty("mail.smtp.host", server);
			prop.setProperty("mail.smtp.auth", isAuth);
		}
		return prop;
	}

}
