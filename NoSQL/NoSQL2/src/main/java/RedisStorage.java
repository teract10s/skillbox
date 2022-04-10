import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import static java.lang.System.out;

public class RedisStorage {
    private RedissonClient redisson;
    private RScoredSortedSet<String> onlineUsers;
    private RKeys rKeys;
    private final static String KEY = "USERS";

    void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            out.println("Не удалось подключиться к Redis");
            out.println(Exc.getMessage());
        }
        rKeys = redisson.getKeys();
        onlineUsers = redisson.getScoredSortedSet(KEY);
        rKeys.delete(KEY);
    }

    void shutdown() {
        redisson.shutdown();
    }

    void add(int numberInTheQueue, int userId) {
        onlineUsers.add(numberInTheQueue, String.valueOf(userId));
    }

    void delete(int numberInTheQueue) {
        onlineUsers.remove(numberInTheQueue);
    }

    public RScoredSortedSet<String> getOnlineUsers() {
        return onlineUsers;
    }

    public String getFirstUserInQueue(){
        return onlineUsers.first();
    }
}