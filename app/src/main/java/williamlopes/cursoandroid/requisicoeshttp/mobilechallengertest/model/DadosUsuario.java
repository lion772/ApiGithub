package williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.model;

public class DadosUsuario {

    public String login;
    public String avatar_url;
    public Integer id;

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }
}
