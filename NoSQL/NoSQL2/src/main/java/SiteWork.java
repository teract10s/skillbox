import java.util.Random;
import java.util.Set;

public class SiteWork {
    private static final int USERS = 20;
    private static final int SLEEP = 1000;
    private static RedisStorage redis;

    public static void main(String[] args) throws InterruptedException {
        redis = new RedisStorage();
        redis.init();
        create();

        for (;;){
            for(int j = 1; j < USERS; j++) {
                if (new Random().nextInt(10) == 7) {
                    int userId = new Random().nextInt(20);
                    System.out.println("> Пользователь " + userId + " оплатил платную услугу");
                    Thread.sleep(100);
                    refactorQueue(1, userId);
                }
                int firstUserAtQueue = Integer.parseInt(redis.getFirstUserInQueue());
                System.out.println(" — На главной странице показываем пользователя " + firstUserAtQueue);
                Thread.sleep(100);
                refactorQueue(20, firstUserAtQueue);
            }
            Thread.sleep(SLEEP);
        }
    }

    private static void refactorQueue(int number, int userId){
        Set<String> queue = redis.getAllUser();
        if (number == 1){
            int i = 0;
            for (String user : queue){
                i++;
                if (i <= number){
                    redis.add(i + 1, Integer.parseInt(user));
                }
            }
            redis.add(number, userId);
            return;
        }

        int i = 0;
        for (String user : queue){
            i++;
            if (!user.equals(String.valueOf(userId))){
                redis.add(i - 1, Integer.parseInt(user));
            }
        }
        redis.add(number, userId);
    }

    private static void create(){
        for (int j = 1; j <= USERS; j++){
            redis.add(j, j);
        }
    }
}
