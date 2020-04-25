package williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
    private TextView textEstrela;
    private TextView textBranch;
    private TextView textoLinguagem;
    private TextView textoData;
    private TextView textoAbertas;

    private SQLiteDatabase mDb;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositorio_dados);

        textDescricao = findViewById(R.id.textDescricaoTexto);
        textNomeRepositorio = findViewById(R.id.textNomeRepositorio);
        textEstrela = findViewById(R.id.textEstrela);
        textBranch = findViewById(R.id.textBranch);
        textoLinguagem = findViewById(R.id.textLinguagem);
        textoData = findViewById(R.id.textData);
        textoAbertas = findViewById(R.id.textAbertas);

        SQLite dbHelper = new SQLite(this);
        mDb = dbHelper.getWritableDatabase();

        Integer idUsuario = getIntent().getIntExtra(GithubContract.OwnerEntry.colunaId, -1);
        Log.d("idUsuariooooooooo", "onCreate: " + idUsuario);

        //Recuperar repositório selecionado
        Bundle bundle = getIntent().getExtras();
        if ( bundle != null ){

            itemSelecionado = (String) bundle.getSerializable("ItemSelecionado");
            descricaoSelecionada = (String) bundle.getSerializable("DescricaoSelecionada");

            textNomeRepositorio.setText(itemSelecionado);
            textDescricao.setText(descricaoSelecionada);

        }

        listarAtributos(idUsuario);

    }

    private void listarAtributos(Integer idUsuario){



        String consulta = "SELECT language, open_issues, created_at, idOwner FROM item" +
                            " WHERE id =" + idUsuario;

        //Estrutura de controle que permite percorrer sobre os registros
        Cursor cursor = mDb.rawQuery(consulta, null);
        cursor.moveToFirst();
        if (cursor != null){
            while (cursor.moveToNext()){

                String linguagem = cursor.getString(cursor.getColumnIndex(GithubContract.ItemsEntry.colunaLanguage));
                String open_issues = cursor.getString(cursor.getColumnIndex(GithubContract.ItemsEntry.colunaOpenIssues));
                String created_at = cursor.getString(cursor.getColumnIndex(GithubContract.ItemsEntry.colunaCreatedAt));
                Log.i("linguagem", "listarRepositorios: " + linguagem);
                Log.i("open_issues", "listarRepositorios: " + open_issues);

                textoLinguagem.setText(linguagem);
                textoData.setText(created_at);


            }
        }


        //Estrutura de controle que permite percorrer sobre os registros
        /*Cursor cursor = mDb.query(GithubContract.ItemsEntry.tabelaNome, null, GithubContract.ItemsEntry.colunaIdOwner + " == " + idUsuario  , null, null, null, null);
        if (cursor != null){
            while (cursor.moveToNext()){

                String linguagem = cursor.getString(cursor.getColumnIndex(GithubContract.ItemsEntry.colunaLanguage));
                String open_issues = cursor.getString(cursor.getColumnIndex(GithubContract.ItemsEntry.colunaOpenIssues));
                String created_at = cursor.getString(cursor.getColumnIndex(GithubContract.ItemsEntry.colunaCreatedAt));

                Log.i("linguagem", "listarRepositorios: " + linguagem);
                Log.i("open_issues", "listarRepositorios: " + open_issues);
                Log.i("created_at", "listarRepositorios: " + created_at);

                textoLinguagem.setText(linguagem);
                textoAbertas.setText(open_issues);
                textoData.setText(created_at);

            }
        }*/
    }
}
