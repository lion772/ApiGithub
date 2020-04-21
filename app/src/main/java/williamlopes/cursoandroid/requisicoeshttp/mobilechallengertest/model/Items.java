package williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.model;

import java.io.Serializable;
import java.util.List;

public class Items implements Serializable {

    public DadosUsuario owner;
    public String name;
    public String description;
    public Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DadosUsuario getOwner() {
        return owner;
    }

    public void setOwner(DadosUsuario owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
