package Chat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private String fromUserName;
    private String toUserName;
    private String message;
    private LocalDateTime created;
    private static DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm:ss.SSS a");

    public Message(String fromUserName, String toUserName, String message) {
        this.fromUserName = fromUserName;
        this.toUserName = toUserName;
        this.message = message;
        this.created = LocalDateTime.now();
    }
    
    public LocalDateTime getCreated() {
        return this.created;
    }

    public String toUserString() {
        return this.created.format(customFormatter) + ": [" + fromUserName + "->" + toUserName + "] " + message;
    }

    public String toString() {
        return this.created.format(customFormatter) + ": " + message;
    }
}
