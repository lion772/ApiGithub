package williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.model;

import java.io.Serializable;
import java.util.List;

public class Items implements Serializable {

    public List<DadosUsuario> owner;
    public String name;
    public String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DadosUsuario> getOwner() {
        return owner;
    }

    public void setOwner(List<DadosUsuario> owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
