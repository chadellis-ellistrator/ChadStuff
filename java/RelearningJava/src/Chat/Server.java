package Chat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Server {
    private static Server server = null;
    private List<User> users = new ArrayList<>();
    private Map<String, Map<String, List<Message>>> userMessages = new HashMap<>();

    public static Server getServer() {
        if (server == null) {
            server = new Server();
        }
        return server;
    }

    public void createUser(String name, String password) {
        if (this.getOptionalUser(name).isPresent()) {
            throw new IllegalArgumentException("User with name: " + name + "already exists");
        }

        users.add(new User(name, password));
        userMessages.put(name, new HashMap<String, List<Message>>());
    }

    private Optional<User> getOptionalUser(String name) {
        return users.stream().filter(u -> u.getName().equals(name)).findFirst();
    }

    public void addUserMessage(String fromUserName, String toUserName, String message) {
        Optional<User> fromUser = this.getOptionalUser(fromUserName);
        if (fromUser.isEmpty()) {
            throw new IllegalArgumentException("User " + fromUserName + " does not exist");
        }
        if (!fromUser.get().isLoggedIn()) {
            throw new IllegalArgumentException("User " + fromUserName + " is not logged in!");
        }

        Map<String, List<Message>> userMap = userMessages.get(fromUserName);
        if (userMap == null) {
            throw new IllegalArgumentException("User " + fromUserName + " is broken!");
        }
        
        if (this.getOptionalUser(toUserName).isEmpty()) {
            throw new IllegalArgumentException("User " + toUserName + " does not exist");
        }
        List<Message> toUserMessages = userMap.get(toUserName);
        if (toUserMessages == null) {
            toUserMessages = new ArrayList<Message>();
            userMap.put(toUserName, toUserMessages);
        }
        toUserMessages.add(new Message(fromUserName, toUserName, message));
    }

    public List<Message> getMessages(String fromUser, String toUser) {
        Map<String, List<Message>> userMap = userMessages.get(fromUser);
        if (userMap != null) {
            return userMap.get(toUser);
        }
        return null;
    }

    private List<Message> getFromToMessages(String fromUser, String toUser) {
        Map<String, List<Message>> userMap = userMessages.get(fromUser);
        if (userMap != null) {
            return userMap.get(toUser);
        }
        return Collections.emptyList();
    }

    public List<Message> getSortedMessagesBetweenUsers(String fromUser, String toUser) {
        List<Message> fromToMessages = getFromToMessages(fromUser, toUser);
        List<Message> toFromMessages = getFromToMessages(toUser, fromUser);

        return Stream.concat(fromToMessages.stream(), toFromMessages.stream())
                .sorted(Comparator.comparing(Message::getCreated))
                .collect(Collectors.toList());
    }

    public boolean loginUser(String name, String password) {
        Optional<User> user = this.getOptionalUser(name);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User " + name + " not found!");
        }
        return user.get().authenticate(password);
    }
}
