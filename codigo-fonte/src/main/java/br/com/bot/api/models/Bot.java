package br.com.bot.api.models;

import br.com.bot.api.utils.IdentifierUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_bot")
public class Bot implements Serializable {

    @Id
    @Column(name = "id", nullable = false, length = 40)
    private String id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;


    public Bot(String name){
        this.id = IdentifierUtils.generateRandomID();
        this.name = name;
    }

}
