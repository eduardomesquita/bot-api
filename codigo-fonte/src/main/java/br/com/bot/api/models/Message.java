package br.com.bot.api.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "tb_message")
public class Message implements Serializable {

    @Id
    @Column(name = "id", nullable = false, length = 40)
    private String id;

    @Column(name = "message_to", nullable = false, length = 40)
    private String to;

    @Column(name = "message_from", nullable = false, length = 40)
    private String from;

    @Column(name = "conversation_id", nullable = false, length = 40)
    private String conversationId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "text", nullable = false)
    private String text;


}
