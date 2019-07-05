package br.com.bot.api.repositories;

import br.com.bot.api.models.Bot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BotRepository extends JpaRepository<Bot, String> {

    @Query("SELECT b FROM Bot b WHERE b.name = :name")
    Bot findByName(@Param("name") String name);

}
