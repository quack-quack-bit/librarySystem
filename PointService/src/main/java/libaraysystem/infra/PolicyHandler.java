package libaraysystem.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import libaraysystem.config.kafka.KafkaProcessor;
import libaraysystem.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    PointSystemRepository pointSystemRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReviewRegistered'"
    )
    public void wheneverReviewRegistered_IncreasePoint(
        @Payload ReviewRegistered reviewRegistered
    ) {
        ReviewRegistered event = reviewRegistered;
        System.out.println(
            "\n\n##### listener IncreasePoint : " + reviewRegistered + "\n\n"
        );

        // Sample Logic //
        PointSystem.increasePoint(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OutOfBookStock'"
    )
    public void wheneverOutOfBookStock_IncreasePoint(
        @Payload OutOfBookStock outOfBookStock
    ) {
        OutOfBookStock event = outOfBookStock;
        System.out.println(
            "\n\n##### listener IncreasePoint : " + outOfBookStock + "\n\n"
        );

        // Sample Logic //
        PointSystem.increasePoint(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookRented'"
    )
    public void wheneverBookRented_DecreasePoint(
        @Payload BookRented bookRented
    ) {
        BookRented event = bookRented;
        System.out.println(
            "\n\n##### listener DecreasePoint : " + bookRented + "\n\n"
        );

        // Sample Logic //
        PointSystem.decreasePoint(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
