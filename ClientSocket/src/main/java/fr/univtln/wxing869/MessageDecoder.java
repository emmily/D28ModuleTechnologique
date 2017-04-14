package fr.univtln.wxing869;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.StringReader;
import java.util.Date;

/**
 * Created by wenlixing on 23/02/2017.
 */
public class MessageDecoder implements Decoder.Text<Message> {

    public Message decode(String s) throws DecodeException {
        Message message = new Message();
        JsonObject jsonObject = Json.createReader(new StringReader(s)).readObject();
        String content = jsonObject.getString("content");
        //System.out.println("content "+content);
        message.setContent(content);
        String sender = jsonObject.getString("sender");
        //System.out.println("sender "+sender);
        message.setSender(sender);
        message.setReceived(new Date());
        return message;
    }

    public boolean willDecode(String s) {
        return s.startsWith("{");
    }

    public void init(EndpointConfig endpointConfig) {

    }

    public void destroy() {

    }
}
