package com.hotelReservation.services;

import com.hotelReservation.dto.HotelBookingRequest;
import com.hotelReservation.entities.HotelBooking;
import com.hotelReservation.observer.BookingObserver;
import com.hotelReservation.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/*OBSERVER PATTERN: The concrete observer class interface.*/
@Service
public class EmailService implements BookingObserver{

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public void update(HotelBooking hbr) {

        // Recipient's email ID needs to be mentioned.
        String to = userLoginService.getEmail(hbr.getUsername());
        // Sender's email ID needs to be mentioned
        String from = "pradyumnna1100@outlook.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp-mail.outlook.com");
        properties.put("mail.smtp.port", "587");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(from, "Prady1100@");

            }

        });

        // Used to debug SMTP issues
        //session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Booking Services!");

            // Now set the actual message
            String hotelname = hotelRepository.findById(hbr.getHotelid()).get().getName();
            String msg = String.format("Booking details of %s:\nHotel name:%s" +
                            "\nRooms Booked:%s\nBooked From:%s\nBooked To:%s\nAddons:%s",
                    hbr.getUsername(),hotelname,
                    hbr.getRoomsbooked(),hbr.getBookedfrom(), hbr.getBookedto(), hbr.getAddons());
            message.setText(msg);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
