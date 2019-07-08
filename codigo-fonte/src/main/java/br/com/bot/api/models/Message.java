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
    @SequenceGenerator(name="message_id_seq", sequenceName="message_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="message_id_seq")
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_bot")
    private Bot bot;

    @Column(name = "user_session_id", nullable = false, length = 40)
    private String userSession;

    @Column(name = "conversation_id", nullable = false, length = 40)
    private String conversationID;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "text", nullable = false)
    private String text;


}
