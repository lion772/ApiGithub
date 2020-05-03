package williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.AdapterRepositorios.AdapterRepositorios;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.BancoDeDados.GithubContract;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.BancoDeDados.SQLite;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.R;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.model.Items;

public class ActivityRepositorioDados extends AppCompatActivity {

    private String itemSelecionado;
    private String descricaoSelecionada;
    private TextView textDescricao;
    private TextView textNomeRepositorio;
    private TextView textoEstrela;
    private TextView textForks;
    private TextView textoLinguagem;
    private TextView textoData;
    private TextView textoAbertas;
    private TextView textoFechadas;
    private ImageView github;

    private SQLiteDatabase mDb;
    private String language;
    private String open_issues;
    private String created_at;
    private String stars;
    private String forks;
    private String closed_issues;
    private String html_url;
    static final String STATE_USER = "user";
    private String mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositorio_dados);

        textDescricao = findViewById(R.id.textDescricaoTexto);
        textNomeRepositorio = findViewById(R.id.textNomeRepositorio);
        textoEstrela = findViewById(R.id.textEstrela);
        textForks = findViewById(R.id.textForks);
        textoLinguagem = findViewById(R.id.textLinguagem);
        textoData = findViewById(R.id.textData);
        textoAbertas = findViewById(R.id.textAbertas);
        textoFechadas = findViewById(R.id.textFechadas);
        github = findViewById(R.id.imageGithub);

        SQLite dbHelper = new SQLite(this);
        mDb = dbHelper.getWritableDatabase();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Github");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState != null) { //Configurando o savedInstanceState para restaurar o estado que ela tinha na altura em que foi destruída
            mUser = savedInstanceState.getString(STATE_USER);
        } else {
            mUser = "NewUser";
        }

        //Recuperar repositório selecionado
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            itemSelecionado = (String) bundle.getSerializable("ItemSelecionado");
            descricaoSelecionada = (String) bundle.getSerializable("DescricaoSelecionada");
            language = (String) bundle.getSerializable("language");
            open_issues = (String) bundle.getSerializable("open_issues");
            created_at = (String) bundle.getSerializable("created_at");
            stars = (String) bundle.getSerializable("stars");
            forks = (String) bundle.getSerializable("forks");
            closed_issues = (String) bundle.getSerializable("closed_issues");
            html_url = (String) bundle.getSerializable("html_url");

            Log.i("número de estrelas", "onCreate: " + stars);

            textNomeRepositorio.setText(itemSelecionado);
            textDescricao.setText(descricaoSelecionada);
            textoLinguagem.setText(language);
            textoData.setText(created_at);
            textoAbertas.setText(open_issues);
            textoEstrela.setText(stars);
            textForks.setText(forks);
            textoFechadas.setText(closed_issues);
        }


       github.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(html_url));
               startActivity(intent);
           }
       });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(STATE_USER, String.valueOf(textDescricao));
        outState.putString(STATE_USER, String.valueOf(textNomeRepositorio));
        outState.putString(STATE_USER, String.valueOf(textoEstrela));
        outState.putString(STATE_USER, String.valueOf(textForks));
        outState.putString(STATE_USER, String.valueOf(textoLinguagem));
        outState.putString(STATE_USER, String.valueOf(textoData));
        outState.putString(STATE_USER, String.valueOf(textoAbertas));
        outState.putString(STATE_USER, String.valueOf(textoFechadas));
        outState.putString(STATE_USER, String.valueOf(github));
        super.onSaveInstanceState(outState);
    }
}
