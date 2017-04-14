package fr.univtln.wxing869;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

/**
 * Created by wenlixing on 23/02/2017.
 */

@ServerEndpoint(value = "/chat", encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class Server {

    static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    public Server() {
    }

    @OnOpen
    public void onOpen(Session session){
        System.out.println(session.getId()+" joined the chat room. ");
        peers.add(session);
    }
    @OnMessage
    public void onMessage(Message message, Session session) throws IOException, EncodeException {
        //broadcast the message
        for(Session p : peers){
            if(!session.getId().equals(p.getId())){ //do not send to the sender
                p.getBasicRemote().sendObject(message);
            }
        }
    }
    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        System.out.println(session.getId()+" left the chat room. ");
        peers.remove(session);
        //notify peers about leaving the chat room
        for(Session peer : peers){
            Message message = new Message();
            message.setSender("Server");
            message.setContent(session.getId()+" left the chat room. ");
            message.setReceived(new Date());
            peer.getBasicRemote().sendObject(message);
        }
    }
    public static void main(String[] args) {

        org.glassfish.tyrus.server.Server server =
                new org.glassfish.tyrus.server.Server("0.0.0.0",8025, "/", null, Server.class);

        try {
            server.start();
            System.out.println("Press any key to stop the server..");
            new Scanner(System.in).nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.stop();
        }
    }
}
