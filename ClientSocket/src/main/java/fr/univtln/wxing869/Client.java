package fr.univtln.wxing869;

import org.glassfish.tyrus.client.ClientManager;

import javax.json.Json;
import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * Created by wenlixing on 23/02/2017.
 */
@ClientEndpoint(encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class Client {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

    @OnMessage
    public void onMessage(Message message) {
        System.out.println(String.format("[%s:%s] %s",
                simpleDateFormat.format(message.getReceived()), message.getSender(), message.getContent()));
    }

    public static final String SERVER = "ws://myServer:8025/chat";


    public static void main(String[] args) throws Exception {
        ClientManager client = ClientManager.createClient();
        String str;

        // connect to server
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Tiny Chat!");
        System.out.println("What's your name?");
        String user = scanner.nextLine();
        Session session = client.connectToServer(Client.class, URI.create(SERVER));
        System.out.println("You are logged in as: " + user);


        // repeatedly read a message and send it to the server (until quit)
        do {
            str = scanner.nextLine();

            session.getBasicRemote().sendText(Json.createObjectBuilder()
                    .add("content", str)
                    .add("sender", user)
                    .build().toString());
            } while (!str.equalsIgnoreCase("quit"));


    }

}
