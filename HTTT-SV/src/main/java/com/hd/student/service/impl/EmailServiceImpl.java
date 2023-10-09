package com.hd.student.service.impl;

import com.hd.student.entity.OnlineService;
import com.hd.student.exception.ResourceNotFoundException;
import com.hd.student.repository.OnlineServiceRepository;
import com.hd.student.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private OnlineServiceRepository onlineServiceRepository;


    @Override
    public void sendNotifyAccept(OnlineService onlineService) {

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Date currentDate = cld.getTime();

        cld.add(Calendar.DATE, onlineService.getServiceCate().getNumOfDate());

        Date expectedDate = cld.getTime();

        String currentDateFormatted = formatter.format(currentDate);
        String expectedDateFormatted = formatter.format(expectedDate);

        String body = "Trạng thái cập nhật yêu cầu của bạn đã cập nhật thành " + onlineService.getStatus()
                + ". Vui lòng kiểm tra quá trình yêu cầu này. Thời gian dự kiến có thể nhận kết quả ở VVT.005 " +
                "hoặc ship qua đường bưu điện.\n\n"
                + "Thời gian hiện tại: " + currentDateFormatted + "\n"
                + "Dự kiến thời gian sau 5 ngày: " + expectedDateFormatted;
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(onlineService.getUser().getEmail());
            messageHelper.setSubject("Xác nhận yêu cầu dịch vụ của bạn hoàn tất");
            messageHelper.setText(body, false);  // true: enable HTML content
        };

        mailSender.send(preparator);
    }
}
