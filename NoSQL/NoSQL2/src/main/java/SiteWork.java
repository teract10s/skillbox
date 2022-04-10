import java.util.Random;

public class SiteWork {
    private static final int USERS = 20;
    private static final int SLEEP = 1000;
    private static RedisStorage redis;

    public static void main(String[] args) throws InterruptedException {
        redis = new RedisStorage();
        redis.init();
        init();

        int i = 0;
        for (;;){
            i++;
            for(int j = 0; j < USERS; j++) {
                if (i == 10) {
                    i = 0;
                    int userId = new Random().nextInt(20);
                    redis.delete(userId);
                    redis.add(1, userId);
                }
                String firstUserAtQueue = redis.getFirstUserInQueue();
                System.out.println("На главной странице показываем пользователя " + firstUserAtQueue);
                redis.delete(Integer.parseInt(firstUserAtQueue));
                redis.add(20, Integer.parseInt(firstUserAtQueue));
            }
            Thread.sleep(SLEEP);
        }
    }

    private static void init(){
        for (int j = 0; j < USERS; j++){
            redis.add(j, j);
        }
    }
}
