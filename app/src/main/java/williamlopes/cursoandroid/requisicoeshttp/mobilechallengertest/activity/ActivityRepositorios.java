package williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.R;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.model.ListaRepositorios;

public class ActivityRepositorios extends AppCompatActivity {

    public String nome;
    public List<ListaRepositorios> repositorios;
    public CircleImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositorios);


    }
}
