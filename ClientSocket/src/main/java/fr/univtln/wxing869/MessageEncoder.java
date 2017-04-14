package fr.univtln.wxing869;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * Created by wenlixing on 23/02/2017.
 */
public class MessageEncoder implements Encoder.Text<Message> {
    public String encode(Message message) throws EncodeException {
        return Json.createObjectBuilder().add("content", message.getContent())
                .add("sender", message.getSender())
                .add("received", "")
                .build().toString();
    }

    public void init(EndpointConfig endpointConfig) {

    }

    public void destroy() {

    }
}
