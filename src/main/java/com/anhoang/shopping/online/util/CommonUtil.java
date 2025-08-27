package com.anhoang.shopping.online.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.anhoang.shopping.online.model.ProductOrder;

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

    String msg=null;;

    public Boolean sendMailForProductOrder(ProductOrder order,String status) throws Exception
	{
		
		msg="<p>Hello [[name]],</p>"
				+ "<p>Thank you order <b>[[orderStatus]]</b>.</p>"
				+ "<p><b>Product Details:</b></p>"
				+ "<p>Name : [[productName]]</p>"
				+ "<p>Category : [[category]]</p>"
				+ "<p>Quantity : [[quantity]]</p>"
				+ "<p>Price : [[price]]</p>"
				+ "<p>Payment Type : [[paymentType]]</p>";
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("daspabitra55@gmail.com", "Shooping Cart");
		helper.setTo(order.getOrderAddress().getEmail());

		msg=msg.replace("[[name]]",order.getOrderAddress().getFirstName());
		msg=msg.replace("[[orderStatus]]",status);
		msg=msg.replace("[[productName]]", order.getProduct().getTitle());
		msg=msg.replace("[[category]]", order.getProduct().getCategory());
		msg=msg.replace("[[quantity]]", order.getQuantity().toString());
		msg=msg.replace("[[price]]", order.getPrice().toString());
		msg=msg.replace("[[paymentType]]", order.getPaymentType());
		
		helper.setSubject("Product Order Status");
		helper.setText(msg, true);
		mailSender.send(message);
		return true;
	}
}
