package com.hotelReservation.config;

import com.hotelReservation.observer.BookingSubject;
import com.hotelReservation.observer.BookingSubjectImpl;
import com.hotelReservation.services.EmailService;
import com.hotelReservation.services.RoomFactoryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    /*SINGLETON PATTERN: The Spring framework creates a single bean/object and later it is injected when needed.*/
    @Bean
    @Scope("singleton")
    public BookingSubject bookingSubject()
    {
        BookingSubjectImpl bookingSubject = new BookingSubjectImpl();
        bookingSubject.registerObserver(emailServiceBean());
        return bookingSubject;
    }

    @Bean
    @Scope("singleton")
    public EmailService emailServiceBean() {
        return new EmailService();
    }

    @Bean
    @Scope("singleton")
    public RoomFactoryService roomFactoryBean(){ return new RoomFactoryService();}

}
