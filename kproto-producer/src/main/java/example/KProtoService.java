package example;

import com.github.daniel.shuy.kafka.protobuf.serde.KafkaProtobufSerializer;
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

public class KProtoService extends KProtoGrpc.KProtoImplBase {

    Properties props;
    Producer<String, Message.Person> producer;

    public KProtoService() {
        this.props = new Properties();
        this.props.put("bootstrap.servers", "localhost:9092");
        this.producer = new KafkaProducer<>(props,
                new StringSerializer(),
                new KafkaProtobufSerializer<>());

    }

    @Override
    public void postMessage(Service.PostMessageRequest request, StreamObserver<Service.PostMessageResponse> responseObserver) {

        Future<RecordMetadata> topic1 = producer.send(new ProducerRecord<>("topic1", request.getPerson()));

        try {
            System.out.println(topic1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        responseObserver.onNext(Service.PostMessageResponse.newBuilder().build());
        responseObserver.onCompleted();
    }
}
