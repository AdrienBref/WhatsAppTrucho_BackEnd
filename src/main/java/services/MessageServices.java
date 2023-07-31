package services;

import config.HibernateUtil;
import entities.Message;
import org.hibernate.Session;
import repositories.MessageRepository;
import utils.JsonParser;

import java.util.ArrayList;

public class MessageServices {
    private ArrayList <String> stringMessages  = new ArrayList<>();
    private ArrayList <Message> messagesStack = new ArrayList<>();

    public ArrayList<String> getStringMessages() {
        return stringMessages;
    }

    public void addToMessageStack(String message) {

        JsonParser jsonParser = new JsonParser();

        stringMessages.add(message);
        Message messageToJson = jsonParser.parse(message, Message.class);
        messagesStack.add(messageToJson);

        messageToDb(messageToJson);

    }

    public void messageToDb(Message message) {

        Session session = HibernateUtil.get().openSession();

        MessageRepository messageRepository = new MessageRepository(session);

        messageRepository.save(message);

        session.close();

    }


}
