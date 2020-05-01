package williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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

    private SQLiteDatabase mDb;
    private Integer idUsuario;
    private String language;
    private String open_issues;
    private String created_at;
    private String stars;
    private String forks;
    private String closed_issues;


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

        SQLite dbHelper = new SQLite(this);
        mDb = dbHelper.getWritableDatabase();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Github");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Recuperar repositório selecionado
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            itemSelecionado = (String) bundle.getSerializable("ItemSelecionado");
            descricaoSelecionada = (String) bundle.getSerializable("DescricaoSelecionada");
            idUsuario = (Integer) bundle.getSerializable("id");
            language = (String) bundle.getSerializable("language");
            open_issues = (String) bundle.getSerializable("open_issues");
            created_at = (String) bundle.getSerializable("created_at");
            stars = (String) bundle.getSerializable("stars");
            forks = (String) bundle.getSerializable("forks");
            closed_issues = (String) bundle.getSerializable("closed_issues");


            textNomeRepositorio.setText(itemSelecionado);
            textDescricao.setText(descricaoSelecionada);
            textoLinguagem.setText(language);
            textoData.setText(created_at);
            textoAbertas.setText(open_issues);
            textoEstrela.setText(stars);
            textForks.setText(forks);
            textoFechadas.setText(closed_issues);

        }

        //listarAtributos(idUsuario);

    }

    /*private void listarAtributos(Integer idUsuario){

        String consulta = "SELECT language, open_issues, created_at, idOwner FROM item" +
                            " WHERE idOwner =" + idUsuario;

        //Estrutura de controle que permite percorrer sobre os registros
        Cursor cursor = mDb.rawQuery(consulta, null);
        if (cursor != null){
            while (cursor.moveToNext()){

                String linguagem = cursor.getString(cursor.getColumnIndex("language"));
                String open_issues = cursor.getString(cursor.getColumnIndex("open_issues"));
                String created_at = cursor.getString(cursor.getColumnIndex("created_at"));
                Log.i("linguagem", "listarRepositorios: " + linguagem);
                Log.i("open_issues", "listarRepositorios: " + open_issues);

                textoLinguagem.setText(linguagem);
                textoData.setText(created_at);


            }
        }*/


        //Estrutura de controle que permite percorrer sobre os registros
        /*Cursor cursor2 = mDb.query(GithubContract.ItemsEntry.tabelaNome, null, GithubContract.ItemsEntry.colunaIdOwner + " == " + idUsuario  , null, null, null, null);
        if (cursor2 != null){
            while (cursor2.moveToNext()){

                String linguagem = cursor2.getString(cursor2.getColumnIndex(GithubContract.ItemsEntry.colunaLanguage));
                String open_issues = cursor2.getString(cursor2.getColumnIndex(GithubContract.ItemsEntry.colunaOpenIssues));
                String created_at = cursor2.getString(cursor2.getColumnIndex(GithubContract.ItemsEntry.colunaCreatedAt));

                Log.i("linguagem", "listarRepositorios: " + linguagem);
                Log.i("open_issues", "listarRepositorios: " + open_issues);
                Log.i("created_at", "listarRepositorios: " + created_at);

                textoLinguagem.setText(linguagem);
                textoAbertas.setText(open_issues);
                textoData.setText(created_at);

            }
        }
    }*/
}
