package example;

import com.github.daniel.shuy.kafka.protobuf.serde.KafkaProtobufSerde;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Properties;

public class KAvroStream {


    public static void main(String... args) {
//        Serde<String> stringSerde = Serdes.String();
//        Serde<Message.Person> myValueSerde = new KafkaProtobufSerde(Message.Person.parser());
//
//
//        Properties config = new Properties();
//        config.put("application.id", "kproto-stream");
//        config.put("bootstrap.servers", "localhost:9092");
//
//        StreamsBuilder builder = new StreamsBuilder();
//        KStream<String, Message.Person> myValues = builder.stream("topic1", Consumed.with(stringSerde, myValueSerde));
//        KStream<String, Message.Person> filteredMyValues = myValues.filter((key, value) -> {
//            String name = value.getName();
//            System.out.println(name);
//            if (value.getName().length() > 1 && Character.isUpperCase(name.charAt(0))) {
//                System.out.println("Is capital, can pass to next stream");
//                return true;
//            }
//            System.out.println("Is not capital, filter out of stream");
//            return false;
//        });
//        filteredMyValues.to("topic2", Produced.with(stringSerde, myValueSerde));
//
//        Topology topology = builder.build();
//        KafkaStreams streams = new KafkaStreams(topology, config);
//        streams.setUncaughtExceptionHandler((thread, throwable) -> {
//            // ...
//        });
//        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
//        streams.start();
    }

}
