package williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

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

    private TextView nome;
    private List<String> listaItems = new ArrayList<>();
    private CircleImageView avatar;
    private RecyclerView recyclerRepositorios;

    private AdapterRepositorios adapterRepositorios;
    private SQLiteDatabase mDb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositorios);

        recyclerRepositorios = findViewById(R.id.recyclerRepositorios);

        SQLite dbHelper = new SQLite(this);
        mDb = dbHelper.getWritableDatabase();

        Integer idUsuario = getIntent().getIntExtra(GithubContract.OwnerEntry.colunaId, -1);

        listarRepositorios(idUsuario);

        /*



        //Configurando o recycler
        recyclerRepositorios.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(),
                recyclerRepositorios,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        Items itemSelecionado = listaItems.get(position);
                        Intent i = new Intent(ActivityRepositorios.this, ActivityRepositorioDados.class);
                        i.putExtra("ItemSelecionado", itemSelecionado);
                        startActivity(i);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }

        ));*/

    }

    private void listarRepositorios(Integer idUsuario){

        //Estrutura de controle que permite percorrer sobre os registros
        Cursor cursor = mDb.query(GithubContract.ItemsEntry.tabelaNome, null, GithubContract.ItemsEntry.colunaIdOwner + " == " + idUsuario, null, null, null, null); //Recupera todas as tabelas com null
        if (cursor != null){
            while (cursor.moveToNext()){
                String nome = cursor.getString(cursor.getColumnIndex(GithubContract.ItemsEntry.colunaName));
                //Log.i("Repositorios", "listarRepositorios: " + nome);

                listaItems.add(nome);
                //Configurando o adapter
                adapterRepositorios = new AdapterRepositorios(listaItems, this);
                recyclerRepositorios.setAdapter(adapterRepositorios);
                recyclerRepositorios.setHasFixedSize(true);
                recyclerRepositorios.setLayoutManager(new LinearLayoutManager(this));
            }
        }



    }
}
