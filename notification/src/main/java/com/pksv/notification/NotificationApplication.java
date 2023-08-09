package com.pksv.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "com.pksv.notification",
                "com.pksv.amqp"
        }
)
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

/*    @Bean
    CommandLineRunner commandLineRunner(
            RabbitMQMessageProducer producer,
            NotificationConfig config
    ) {
        return args -> {
            producer.publish(
                    new Person("pksv",50),
                    config.getInternalExchange(),
                    config.getInternalNotificationRoutingKey());
        };
    }

    record Person(String name, int age) {
    }*/
}