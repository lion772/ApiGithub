package williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.model;

import android.widget.ImageView;

import java.io.Serializable;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Repositorio implements Serializable {

    public String nome;
    public List<ListaRepositorios> listaRepositorios;
    public String avatar;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ListaRepositorios> getRepositorios() {
        return listaRepositorios;
    }

    public void setRepositorios(List<ListaRepositorios> repositorios) {
        this.listaRepositorios = repositorios;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
