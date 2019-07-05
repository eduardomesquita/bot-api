package br.com.bot.api.models;

import br.com.bot.api.utils.IdentifierUtil;
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
@Table(name = "tb_bot")
@Entity
public class Bot implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;



    public Bot(String name){
        this.id = IdentifierUtil.generateRandomID();
        System.out.println("asssssssssssssssssssssssssssssss");
        this.name = name;
    }

}
