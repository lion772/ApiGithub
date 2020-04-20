package williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.model;

import java.io.Serializable;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Repositorio implements Serializable {

    public String nome;
    public List<ListaRepositorios> repositorios;
    public CircleImageView avatar;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ListaRepositorios> getRepositorios() {
        return repositorios;
    }

    public void setRepositorios(List<ListaRepositorios> repositorios) {
        this.repositorios = repositorios;
    }

    public CircleImageView getAvatar() {
        return avatar;
    }

    public void setAvatar(CircleImageView avatar) {
        this.avatar = avatar;
    }
}
