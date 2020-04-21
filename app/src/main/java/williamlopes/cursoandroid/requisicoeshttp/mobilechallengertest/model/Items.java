package williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Items implements Serializable {
    @SerializedName("owner")
    public DadosUsuario owner;

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    @SerializedName("id")
    public Integer id;

    public Integer idOwner; //Não tem valor referente na API

    public Integer getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Integer idOwner) {
        this.idOwner = idOwner;
    }

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
