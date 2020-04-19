package williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.activity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Repositorio  {

    public String nome;
    public List<Repositorios> repositorios;
    public CircleImageView avatar;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Repositorios> getRepositorios() {
        return repositorios;
    }

    public void setRepositorios(List<Repositorios> repositorios) {
        this.repositorios = repositorios;
    }

    public CircleImageView getAvatar() {
        return avatar;
    }

    public void setAvatar(CircleImageView avatar) {
        this.avatar = avatar;
    }
}
