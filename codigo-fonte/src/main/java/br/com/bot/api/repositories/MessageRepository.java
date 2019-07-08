package br.com.bot.api.repositories;

import br.com.bot.api.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {

    @Query("SELECT m FROM Message m WHERE m.conversationId = :conversationId")
    List<Message> findConversations(@Param("conversationId") String conversationId);
}
