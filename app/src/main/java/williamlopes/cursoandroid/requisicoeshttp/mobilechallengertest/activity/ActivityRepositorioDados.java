package williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.R;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.model.Items;

public class ActivityRepositorioDados extends AppCompatActivity {

    private String itemSelecionado;
    private String descricaoSelecionada;
    private TextView textDescricao;
    private TextView textNomeRepositorio;
    private TextView textEstrela;
    private TextView textBranch;
    private TextView textLinguagem;
    private TextView textData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositorio_dados);

        textDescricao = findViewById(R.id.textDescricaoTexto);
        textNomeRepositorio = findViewById(R.id.textNomeRepositorio);
        textEstrela = findViewById(R.id.textEstrela);
        textBranch = findViewById(R.id.textBranch);
        textLinguagem = findViewById(R.id.textLinguagem);
        textData = findViewById(R.id.textData);

        //Recuperar repositório selecionado
        Bundle bundle = getIntent().getExtras();
        if ( bundle != null ){

            itemSelecionado = (String) bundle.getSerializable("ItemSelecionado");
            descricaoSelecionada = (String) bundle.getSerializable("DescricaoSelecionada");

            textNomeRepositorio.setText(itemSelecionado);
            textDescricao.setText(descricaoSelecionada);
        }
    }
}
