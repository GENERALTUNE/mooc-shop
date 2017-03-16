package org.generaltune.email;

import com.sun.mail.util.MailSSLSocketFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Calendar;
import java.util.Properties;

/**
 * Created by zhumin on 2017/3/15.
 */
public class SendMailTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Test
    public void sendMail() {
        // of course you would use DI in any real-world cases
        // 收件人电子邮箱
        try{
            Properties props = new Properties();

            // 开启debug调试
            props.setProperty("mail.debug", "true");
            // 发送服务器需要身份验证
            props.setProperty("mail.smtp.auth", "true");
            // 设置邮件服务器主机名
            props.setProperty("mail.host", "smtp.126.com");
            // 发送邮件协议名称
            props.setProperty("mail.transport.protocol", "smtp");

            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.ssl.socketFactory", sf);

            Session session = Session.getInstance(props);

            Message msg = new MimeMessage(session);
            msg.setSubject("测试邮件");
            StringBuilder builder = new StringBuilder();
            builder.append("来自网易的邮件 = " + "你好！");
            builder.append("\n页面正文");
            builder.append("\n时间 " + Calendar.getInstance().getTime());
            msg.setText(builder.toString());

            //**发送人的邮箱地址**
            msg.setFrom(new InternetAddress("generaltune@126.com"));

            Transport transport = session.getTransport();
            transport.connect("smtp.126.com", "generaltune@126.com", "*");

            transport.sendMessage(msg, new Address[] { new InternetAddress("577174042@qq.com") });
            transport.close();

        }catch ( Exception e) {
            logger.error("[SendMailTest][sendMail]{}", e.getStackTrace());
        }
    }
}
