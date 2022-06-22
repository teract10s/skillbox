import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Loader {
    public static void main(String[] args) throws Exception {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        AtomicBoolean flag = new AtomicBoolean(true);
        Consumer consumer = new Consumer(queue, flag);
        Producer producer = new Producer(queue, flag);
        producer.producing();
        consumer.consuming();
        consumer.consuming();
    }
}