package configure;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;

public class DNSQuery {
	String domain = null;
	String smtpServer = null;
	public void DNSQuery(String domain){
		this.domain = domain;
	}
	public String getSMTPServer() throws NamingException
	{
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.dns.DnsContextFactory");
		DirContext ctx = (DirContext) new InitialContext(env);
		Attributes attrsAll = ctx.getAttributes(domain);
		Attributes attrsMX = ctx.getAttributes(domain,new String[]{"MX"});
		Attribute attrMX = attrsAll.get("MX");
		String recordMX = (String)attrMX.get();
		smtpServer = recordMX.substring(recordMX.indexOf(" ")+1);
		return smtpServer;
	}

}
