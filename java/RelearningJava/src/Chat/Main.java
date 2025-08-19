package Chat;

public class Main {
    public static void main(String[] args) {
        Client client1 = new Client();
        client1.createUser("ellistrator", "canucks");
        client1.login("ellistrator", "canucks");

        Client client2 = new Client();
        client2.createUser("scooby", "doo");
        client2.login("scooby", "doo");
        
        client1.sendMessage("scooby", "what up scoob?");
        client2.sendMessage("ellistrator", "hey ellistrator!!!");
        client1.sendMessage("scooby", "doing good?");
        client2.sendMessage("ellistrator", "doing great!");

        printMessages("ellistrator", "scooby");
    }

    static void printMessages(String fromUser, String toUser) {
        Server server = Server.getServer();
        for (Message m: server.getSortedMessagesBetweenUsers(fromUser, toUser)) {
            System.out.println(m.toUserString());
        }
    }
}
