package example;

import com.github.daniel.shuy.kafka.protobuf.serde.KafkaProtobufSerializer;
import example.acontract.AColor;
import example.acontract.APerson;
import example.contract.KProtoGrpc;
import example.contract.Message;
import example.contract.Service;
import io.grpc.stub.StreamObserver;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class KAvroService extends KProtoGrpc.KProtoImplBase {

    Properties props;
    Producer<String, Message.Person> producer;

    public KAvroService() {
        this.props = new Properties();
        this.props.put("bootstrap.servers", "localhost:9092");
        this.producer = new KafkaProducer<>(props,
                new StringSerializer(),
                new KafkaProtobufSerializer<>());

    }

    @Override
    public void postMessage(Service.PostMessageRequest request, StreamObserver<Service.PostMessageResponse> responseObserver) {

        Message.Person person = request.getPerson();

        APerson.Builder aPersonBuilder = APerson.newBuilder();
        aPersonBuilder.setName(person.getName());
        aPersonBuilder.setLength(person.getLength());
        aPersonBuilder.setColor(AColor.valueOf(person.getColor().name()));


//        Future<RecordMetadata> topic1 = producer.send(new ProducerRecord<>("atopic1", aPersonBuilder.build()));
//
//        try {
//            System.out.println(topic1.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        responseObserver.onNext(Service.PostMessageResponse.newBuilder().build());
//        responseObserver.onCompleted();
    }
}
