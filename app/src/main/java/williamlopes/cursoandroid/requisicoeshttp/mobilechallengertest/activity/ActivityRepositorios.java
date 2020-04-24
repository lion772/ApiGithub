package williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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


    private List<String> listaRepositorios = new ArrayList<>();
    private List<String> listaDescricao = new ArrayList<>();
    private List<String> listaUsuario = new ArrayList<>();
    private TextView textoNomeUsuario;
    private CircleImageView imagemUsuario;

    private String nomeUsuario;
    private CircleImageView avatar;
    private RecyclerView recyclerRepositorios;

    private AdapterRepositorios adapterRepositorios;
    private SQLiteDatabase mDb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositorios);

        recyclerRepositorios = findViewById(R.id.recyclerRepositorios);
        textoNomeUsuario = findViewById(R.id.textoNomeUsuario);
        imagemUsuario = findViewById(R.id.imagemUsuario);


        SQLite dbHelper = new SQLite(this);
        mDb = dbHelper.getWritableDatabase();

        Integer idUsuario = getIntent().getIntExtra(GithubContract.OwnerEntry.colunaId, -1);

        listarRepositorios(idUsuario);








    }

    private void listarRepositorios(Integer idUsuario){

        //Estrutura de controle que permite percorrer sobre os registros
        Cursor cursor = mDb.query(GithubContract.ItemsEntry.tabelaNome, null, GithubContract.ItemsEntry.colunaIdOwner + " == " + idUsuario, null, null, null, null); //Recupera todas as tabelas com null
        if (cursor != null){
            while (cursor.moveToNext()){
                String nomeRepositorio = cursor.getString(cursor.getColumnIndex(GithubContract.ItemsEntry.colunaName));
                String descricao = cursor.getString(cursor.getColumnIndex(GithubContract.ItemsEntry.colunaDescription));


                //Log.i("Repositorios", "listarRepositorios: " + nome);

                listaRepositorios.add(nomeRepositorio);
                listaDescricao.add(descricao);

                //Configurando o adapter
                adapterRepositorios = new AdapterRepositorios(listaRepositorios, listaDescricao, this);
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

                                String repositorio = listaRepositorios.get(position);
                                Intent i = new Intent(ActivityRepositorios.this, ActivityRepositorioDados.class);
                                i.putExtra("repositorio", repositorio);
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

        Cursor cursorOwner = mDb.query(GithubContract.OwnerEntry.tabelaNome, null, null, null, null, null, null); //Recupera todas as tabelas com null
        if (cursorOwner != null){
            while (cursor.moveToNext()){

                String nomeUsuario = cursor.getString(cursor.getColumnIndex(GithubContract.OwnerEntry.colunaLogin));
                String avatarUsuario = cursor.getString(cursor.getColumnIndex(GithubContract.OwnerEntry.colunaAvatar));
                Log.i("nomeUsuario", "listarRepositorios: " + nomeUsuario);

                textoNomeUsuario.setText(nomeUsuario);

                Uri uri = Uri.parse(avatarUsuario);
                Glide.with(ActivityRepositorios.this).load(uri).into(imagemUsuario);


                }

            }
        }
    }

}
