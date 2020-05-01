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
    public int open_issues;
    @SerializedName("created_at")
    public String created_at;
    @SerializedName("stargazers_count")
    public Integer stargazers_count;
    @SerializedName("forks")
    public Integer forks;
    @SerializedName("html_url")
    public String html_url;

    public Integer closed_issues;
    public Integer idOwner; //Não tem valor referente na API para idOwner, porém é importante esse atributo para usar como parâmetro de comparação a fim de filtrar futuramente no banco de dados, e identificar cada usuário e seus respectivos repositórios


    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public Integer getForks() {
        return forks;
    }

    public void setForks(Integer forks) {
        this.forks = forks;
    }

    public Integer getClosed_issues() {
        return closed_issues;
    }

    public void setClosed_issues(Integer closed_issues) {
        this.closed_issues = closed_issues;
    }

    public Integer getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(Integer stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getOpen_issues() {
        return open_issues;
    }

    public void setOpen_issues(int open_issues) {
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
