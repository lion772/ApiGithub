package williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.AdapterRepositorios.AdapterRepositorios;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.BancoDeDados.GithubContract;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.BancoDeDados.SQLite;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.R;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.listener.RecyclerItemClickListener;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.model.Items;

public class ActivityRepositorios extends AppCompatActivity {

    private TextView nomeUsuario;
    private List<Items> listaRep = new ArrayList<>();
    private List<String> listaItems = new ArrayList<>();
    private List<String> listaDescricao = new ArrayList<>();
    private List<String> listaLinguagem = new ArrayList<>();
    private List<String> listaOpenIssues = new ArrayList<>();
    private List<String> listaCriacao = new ArrayList<>();
    private CircleImageView avatar;
    private RecyclerView recyclerRepositorios;

    private AdapterRepositorios adapterRepositorios;
    private SQLiteDatabase mDb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositorios);

        recyclerRepositorios = findViewById(R.id.recyclerRepositorios);
        avatar = findViewById(R.id.imagemUsuario);
        nomeUsuario = findViewById(R.id.textoNomeUsuario);

        SQLite dbHelper = new SQLite(this);
        mDb = dbHelper.getWritableDatabase();


        Integer idUsuario = getIntent().getIntExtra(GithubContract.OwnerEntry.colunaId, -1);
        listarRepositorios(idUsuario);


        //Verificação da existência de tabelas
        String verifica = "SELECT name FROM sqlite_master" +
                          " WHERE type='table' AND name='owner'";

        Cursor cursorVerifica = mDb.rawQuery(verifica, null);
        if (cursorVerifica != null){
            while(cursorVerifica.moveToNext()){
                Log.i("numeroTabelas", "onCreate: " + cursorVerifica);
            }
        }

    }

    private void listarRepositorios(final Integer idUsuario){

        String consulta = "SELECT avatar_url, login, id FROM owner" +
                        " WHERE id =" + idUsuario;

        //Estrutura de controle que permite percorrer sobre os registros
        Cursor cursorOwner = mDb.rawQuery(consulta, null);
        if (cursorOwner != null){
            while (cursorOwner.moveToNext()){

                String avatarUrl = cursorOwner.getString(cursorOwner.getColumnIndex("avatar_url"));
                String nomeUsuarioInterface = cursorOwner.getString(cursorOwner.getColumnIndex("login"));
                /*Log.i("avatar", "listarRepositorios: " + avatarUrl);
                Log.i("login", "listarRepositorios: " + nomeUsuarioInterface);*/

                nomeUsuario.setText(nomeUsuarioInterface);
                Uri uri = Uri.parse(avatarUrl);
                Glide.with(ActivityRepositorios.this).load(uri).into(avatar);

            }
        }

        //Estrutura de controle que permite percorrer sobre os registros
        Cursor cursor = mDb.query(GithubContract.ItemsEntry.tabelaNome, null, GithubContract.ItemsEntry.colunaIdOwner + " == " + idUsuario, null, null, null, null); //Recupera todas as tabelas com null
        if (cursor != null){
            while (cursor.moveToNext()){
                String nome = cursor.getString(cursor.getColumnIndex(GithubContract.ItemsEntry.colunaName));
                String descricao = cursor.getString(cursor.getColumnIndex(GithubContract.ItemsEntry.colunaDescription));
                final String language = cursor.getString(cursor.getColumnIndex(GithubContract.ItemsEntry.colunaLanguage));
                final String created_at = cursor.getString(cursor.getColumnIndex(GithubContract.ItemsEntry.colunaCreatedAt));
                final String open_issues = cursor.getString(cursor.getColumnIndex(GithubContract.ItemsEntry.colunaOpenIssues));

                listaItems.add(nome);
                listaDescricao.add(descricao);


                //Configurando o adapter
                adapterRepositorios = new AdapterRepositorios(listaItems, listaDescricao, ActivityRepositorios.this);
                recyclerRepositorios.setAdapter(adapterRepositorios);
                recyclerRepositorios.setHasFixedSize(true);
                recyclerRepositorios.setLayoutManager(new LinearLayoutManager(this));

                //Configurando o recycler
        recyclerRepositorios.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(),
                recyclerRepositorios,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        String itemSelecionado = listaItems.get(position);
                        String descricaoSelecionada = listaDescricao.get(position);
                        Integer id = idUsuario;
                        String linguagem = language;
                        String issues = open_issues;
                        String criacao = created_at;

                        Intent i = new Intent(ActivityRepositorios.this, ActivityRepositorioDados.class);
                        i.putExtra("ItemSelecionado", itemSelecionado);
                        i.putExtra("DescricaoSelecionada", descricaoSelecionada);
                        i.putExtra("id", id);
                        i.putExtra("language", linguagem);
                        i.putExtra("open_issues", issues);
                        i.putExtra("created_at", criacao);
                        startActivity(i);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }

        ));


            }
        }


    }
}
