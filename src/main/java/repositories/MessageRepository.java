package repositories;

import entities.Message;
import lombok.experimental.Delegate;

public class MessageRepository implements Repository<Message> {

    @Delegate
    private Repository<Message> delegate;
}
