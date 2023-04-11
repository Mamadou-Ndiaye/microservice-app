package sn.ucad;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.KafkaListener;
import sn.ucad.event.OrderPlaceEvent;

@SpringBootApplication
@Slf4j
@EnableDiscoveryClient
public class NotificationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }


    @KafkaListener(topics="NotificationTopic")
    public void handleNotification(OrderPlaceEvent orderPlaceEvent) {

        //send email out to receive
        log.info("Received notification for order {}",orderPlaceEvent.getOrderNumber());

    }
}