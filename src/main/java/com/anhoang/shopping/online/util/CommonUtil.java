package com.anhoang.shopping.online.util;

import com.anhoang.shopping.online.model.ProductOrder;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class CommonUtil {

    @Autowired
    private JavaMailSender mailSender;

    public Boolean sendMail(String url, String recipientEmail) throws UnsupportedEncodingException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("daspabitra55@gmail.com", "Shopping Cart");
        helper.setTo(recipientEmail);

        String content = "<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>" + "<p><a href=\"" + url
                + "\">Change my password</a></p>";
        helper.setSubject("Password Reset");
        helper.setText(content, true);
        mailSender.send(message);
        return true;
    }

    public static String generateUrl(HttpServletRequest request) {
        String scheme = request.getScheme();             // http hoặc https
        String serverName = request.getServerName();     // localhost hoặc domain
        int serverPort = request.getServerPort();        // 8080, 80, 443
        String contextPath = request.getContextPath();   // /project nếu có

        StringBuilder url = new StringBuilder();
        url.append(scheme).append("://").append(serverName);

        // chỉ thêm port nếu không phải port mặc định
        if ((scheme.equals("http") && serverPort != 80) ||
                (scheme.equals("https") && serverPort != 443)) {
            url.append(":").append(serverPort);
        }

        url.append(contextPath);

        return url.toString();
    }



    public Boolean sendMailForProductOrder(ProductOrder order){
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("daspabitra55@gmail.com", "Shopping Cart");
        helper.setTo(order.getOrderAddress().getEmail());

        String content = "<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>" + "<p><a href=\"" + url
                + "\">Change my password</a></p>";

        helper.setSubject("Password Reset");
        helper.setText(content, true);
        mailSender.send(message);
        return true;
    }

}
