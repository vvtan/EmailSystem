package configure;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


//身份验证器制造工厂
public class AuthenticatorFactory extends Authenticator {
	String username = null;
	String password = null;

	public AuthenticatorFactory(String user, String pass) {
		username = user;
		password = pass;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}

}
