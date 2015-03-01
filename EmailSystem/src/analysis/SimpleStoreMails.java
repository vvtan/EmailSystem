package analysis;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.FetchProfile;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;

import setup.MailSenderInfo;

/**
 * 邮件接收测试
 * 
 */
public class SimpleStoreMails {

	MailSenderInfo mailInfo = new MailSenderInfo();
	public void receiveEmail(){
		try {
			// 设置连接信息, 生成一个 Session
			Properties props = new Properties();
			props.put("mail.transport.protocol", "pop3");// POP3 收信协议
			props.put("mail.pop.port", "25");
			props.put("mail.debug", "false");//是否输出调试信息
			String user =mailInfo.getUserName();  
		    String pwd = mailInfo.getPassword(); 
		    String pop3Server = "pop.sina.com"; //需要邮箱内设置开启POP3/SMTP服务
		    
			Session session = Session.getInstance(props);

			//  获取 Store 并连接到服务器
			Store store = session.getStore("pop3");
			store.connect(pop3Server, user, pwd);
			// 通过 Store 打开默认目录 Folder
			Folder folder = store.getDefaultFolder();// 默认父目录

			Folder popFolder = folder.getFolder("INBOX");// 获取收件箱
			popFolder.open(Folder.READ_ONLY);// 只读模式
			// 列出来收件箱 下所有邮件
			Message[] messages = popFolder.getMessages();
			// 取出来邮件数
			int msgCount = popFolder.getMessageCount();

			for (int i = 0; i < msgCount; i++) {
				
				Message msg = messages[i];// 单个邮件
				// 发件人信息
				Address[] froms = msg.getFrom();
				if (froms != null) {
					System.out.println("发件人信息:" + froms[0]);
//					InternetAddress addr = (InternetAddress) froms[0];
				}

				System.out.println("邮件主题:" + msg.getSubject()); 
//	            messages[i].writeTo(System.out); //输出邮件内容包含邮件头 	            
	            
				Object obj = msg.getContent();
				
				if (!(obj instanceof Multipart)) {
					
					System.out.println(("邮件正文为："+messages[i].getContent()));
				} 
				else {
					
					Multipart multipart = (Multipart) msg.getContent();
					// MultiPart包含所有邮件内容(正文+附件)
					System.out.println("邮件共有" 
					+ multipart.getCount() + "部分组成");

					// 依次处理各个部分
					for (int j = 0, n = multipart.getCount(); j < n; j++) {
					
						Part part = multipart.getBodyPart(j);/*
						 * 解包, 取出MultiPart的各个部分, 每部分可能是邮件内容,
						 * 也可能是另一个小包裹(MultipPart)判断此包裹内容是不是一个小包裹,
						 *  一般这一部分是 正文Content-Type: multipart/alternative
						 */
						
						if (part.getContent() instanceof Multipart) {
							// 转成小包裹
							Multipart p = (Multipart) part.getContent();
							System.out.println("小包裹中有" + p.getCount() + "部分");
							// 列出小包裹中所有内容
							for (int k = 0; k < p.getCount(); k++) {
								System.out.println("小包裹内容:"
										+ p.getBodyPart(k).getContent());
								System.out.println("内容类型:"
										+ p.getBodyPart(k).getContentType());
							}
						}
						// 处理是否为附件信息
						String disposition = part.getDisposition();
						if (disposition != null) {

							System.out.println("发现附件: " + part.getFileName());
							System.out
									.println("内容类型: " + part.getContentType());
							System.out.println("附件内容:" + part.getContent());
							// 打开附件的输入流读取附件字节并存储到文件中
							java.io.InputStream in = part.getInputStream();

							java.io.FileOutputStream out = new FileOutputStream(
									part.getFileName());
							int data;
							while ((data = in.read()) != -1) {
								out.write(data);
							}
							in.close();
							out.close();
						}
						else{
							System.out.println(("邮件正文为："+multipart.getBodyPart(0).getContent()));
						}
					}
				}

				// 6. 删除单个邮件, 标记一下邮件需要删除, 不会真正执行删除操作
				// msg.setFlag(Flags.Flag.DELETED, true);
			}

			// 7. 关闭 Folder 会真正删除邮件, false 不删除
			popFolder.close(true);
			// 8. 关闭 store, 断开网络连接
			store.close();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
