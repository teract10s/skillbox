import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Consumer {
    private final LinkedBlockingQueue<String> queue;
    private final ExecutorService service;
    private final AtomicBoolean flag;

    public Consumer(LinkedBlockingQueue<String> queue, AtomicBoolean flag) {
        service = Executors.newFixedThreadPool(4);
        this.queue = queue;
        this.flag = flag;
    }

    public void consuming(){
        for (int i = 0; i < 4; i++) {
            Thread consumer = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (flag.get()) {
                        try {
                            String way = queue.poll(100, TimeUnit.MILLISECONDS);
                            assert way != null;
                            new StreamingWriter(new FileOutputStream(way));
                        } catch (InterruptedException | FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            service.submit(consumer);
        }
    }
}
