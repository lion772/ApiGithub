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
    @SerializedName("language")
    public String language;
    @SerializedName("open_issues")
    public String open_issues;
    @SerializedName("created_at")
    public String created_at;

    public Integer idOwner; //Não tem valor referente na API para idOwner, porém é importante esse atributo para usar como parâmetro de comparação a fim de filtrar futuramente no banco de dados, e identificar cada usuário e seus respectivos repositórios


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOpen_issues() {
        return open_issues;
    }

    public void setOpen_issues(String open_issues) {
        this.open_issues = open_issues;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

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
