package sample;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * 邮件发送类
 * @author wslixiaoliang
 */
public class Email {

    private static final Logger LOGGER = Logger.getLogger(Email.class.getName());

    /**
     *
     * @param username 登录用户名
     * @param password 密码
     * @param fromNickname 发件人昵称
     * @param toEmail 收件人邮箱
     * @param toNickname 收件人昵称
     * @param title 邮件主题
     * @param content 邮件内容
     */

    public static void sendEmail(String username, String password, String toEmail, String toNickname,String fromNickname, String title, String content){

        //连接邮件服务器参数配置
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol","smtp");//使用的协议（JavaMail规范要求）
        properties.setProperty("mail.smtp.host","smtp.qq.com");//发件人的邮箱的 SMTP 服务器地址
        properties.setProperty("mail.smtp.auth","true");//需要请求认证
        properties.setProperty("mail.debug","true");//是否打印发送时的信息


        //创建会话
        Session session  = Session.getDefaultInstance(properties);

        try{
            Transport transport = session.getTransport();

            //初始化邮件对象
            MimeMessage message= new MimeMessage(session);
            message.setFrom(new InternetAddress(username,fromNickname,"UTF-8"));//发件人
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail, toNickname,"UTF-8"));//收件人
            message.setSubject(title,"UTF-8");//邮件主题
            message.setContent(content,"text/html;charset=UTF-8");

            //使用:邮箱账号&密码,连接邮件服务器(这里认证的邮箱必须与message中的发件人邮箱一致)
            transport.connect(username,password);

            //发送邮件
            transport.sendMessage(message,message.getAllRecipients());

            // 关闭连接
            transport.close();
        }
        catch(UnsupportedEncodingException e){

            LOGGER.info("邮件内容编码异常"+e);
        }
        catch (NoSuchProviderException e){

            LOGGER.info("服务缺失异常"+e);
        }
        catch(MessagingException e){

            LOGGER.info("邮件发送异常"+e);
            e.printStackTrace();
        }

    }


}





