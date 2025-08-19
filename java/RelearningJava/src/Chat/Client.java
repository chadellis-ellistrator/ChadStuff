package Chat;

import java.util.List;

public class Client {
    private Server server = Server.getServer();
    private String userName;

    public void createUser(String name, String password) {
        server.createUser(name, password);
    }

    public boolean login(String name, String password) {
        if (server.loginUser(name, password)) {
            this.userName = name;
            return true;
        }
        return false;
    }

    public void sendMessage(String toUserName, String message) {
        server.addUserMessage(userName, toUserName, message);
    }

    public List<Message> getMessages(String toUserName) {
        return server.getMessages(this.userName, toUserName);
    }
}
