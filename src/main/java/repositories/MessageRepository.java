package repositories;

import entities.Message;
import lombok.experimental.Delegate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MessageRepository implements Repository<Message> {

    private final Session session;

    public MessageRepository(Session session) {
        this.session = session;
    }

    @Override
    public void save(Message message) {
        Transaction trx = session.beginTransaction();
        session.persist(message);
        trx.commit();
    }

    @Override
    public List<Message> findAll() {
        return null;
    }

    @Override
    public Message findOneById(long id) {
        return null;
    }

    @Override
    public void update(Message message) {

    }

    @Override
    public void delete(Message message) {

    }
}
