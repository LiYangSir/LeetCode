import java.util.*;

public class Solution355 {

}
class Twitter {
    private int timestamp = 0;
    class User{
        private int id;
        private Set<Integer> followed = new HashSet<>();  // 关注者
        private Tweet head;  // 发表的文章

        public User(int id) {
            this.id = id;
            followed.add(id);
        }
        public void follow(int id) {
            followed.add(id);
        } // 需要保证User的存在
        public void unfollow(int id) {  // 无需保证User的存在
            if (this.id != id) {
                followed.remove(id);
            }
        }
        public void post(int tweetId) {
            Tweet tweet = new Tweet(tweetId, timestamp++);
            tweet.next = head;
            head = tweet;
        }
    }
    class Tweet{
        private int context;
        private int time;
        private Tweet next;

        public Tweet(int context, int time) {
            this.context = context;
            this.time = time;
        }
    }

    private Map<Integer, User> map = new HashMap<>();
    public void postTweet(int userId, int tweetId) {
        if (!map.containsKey(userId)) {
            map.put(userId, new User(userId));
        }
        User user = map.get(userId);
        user.post(tweetId);
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        if (!map.containsKey(userId)) {
            map.put(userId, new User(userId));
            return res;
        }
        // 或许最新的自己和关注着的最新消息
        Set<Integer> users = map.get(userId).followed;
        PriorityQueue<Tweet> queue = new PriorityQueue<>((o1, o2) -> o2.time - o1.time);
        for (Integer user : users) {
            Tweet head = map.get(user).head;
            if (head == null) continue;
            queue.add(head);
        }
        while (!queue.isEmpty()) {
            if (res.size() == 10) break;
            Tweet tweet = queue.poll();
            res.add(tweet.context);
            if (tweet.next != null) queue.add(tweet.next);
        }
        return res;
    }

    public void follow(int followerId, int followeeId) {
        addOrNothing(followerId);
        addOrNothing(followeeId);
        User user = map.get(followerId);
        user.follow(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (map.containsKey(followerId)) {
            User user = map.get(followerId);
            user.unfollow(followeeId);
        }
    }

    private void addOrNothing(int userId) {
        if (!map.containsKey(userId)) {
            map.put(userId, new User(userId));
        }
    }
}