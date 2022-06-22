import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Producer {
    private final ExecutorService service;
    private final LinkedBlockingQueue<String> queue;
    private final AtomicBoolean flag;

    public Producer(LinkedBlockingQueue<String> queue, AtomicBoolean flag) {
        service = Executors.newFixedThreadPool(4);
        this.queue = queue;
        this.flag = flag;
    }

    public void producing(){
        for (int i = 0; i < 4; i++) {
            Thread producer = new Thread(new Runnable() {
                final Random random = new Random();
                @Override
                public void run() {
                    while (flag.get()) {
                        queue.add("res/number" + random.nextInt());
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            service.submit(producer);
        }
    }
}
