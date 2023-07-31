package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="messages")
@Getter
@Setter
public class Message {
    @Id
    @Column(name="mensaje_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String userId;
    private String date;
    private String textMessage;

    public Message (String userId, String date, String textMessage) {
        this.userId = userId;
        this.date = date;
        this.textMessage = textMessage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }
}
