package example;

import com.github.daniel.shuy.kafka.protobuf.serde.KafkaProtobufDeserializer;
import example.contract.Message;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class KProtoConsumer {


    public static void main(String... args) {
        Properties config = new Properties();
        config.put("application.id", "kproto-consumer");
        config.put("bootstrap.servers", "localhost:9092");
        config.put("group.id", "group-1");
// props.put(..., ...);

        Consumer<String, Message.Person> consumer = new KafkaConsumer<>(config,
                new StringDeserializer(),
                new KafkaProtobufDeserializer(Message.Person.parser()));

        List<String> topics = new ArrayList<String>();
        topics.add("topic2");

        consumer.subscribe(topics);
        while (true) {
            ConsumerRecords<String, Message.Person> records = consumer.poll(100);

            records.forEach(record -> {
                String key = record.key();
                Message.Person value = record.value();
                System.out.println(value);
                // ...
            });
        }
    }

}
