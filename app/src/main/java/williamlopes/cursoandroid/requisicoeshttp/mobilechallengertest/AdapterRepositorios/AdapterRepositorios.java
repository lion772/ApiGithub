package williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.AdapterRepositorios;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URI;
import java.util.List;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import de.hdodenhof.circleimageview.CircleImageView;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.R;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.model.ListaRepositorios;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.model.Repositorio;

public class AdapterRepositorios extends RecyclerView.Adapter<AdapterRepositorios.MyViewHolder> {


    private List<Repositorio> listaRepositorios;
    private Context context;
    public AdapterRepositorios(List<Repositorio> listaRepositorios, Context context) {
        this.listaRepositorios = listaRepositorios;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_repositorios, parent, false);
        return new MyViewHolder( itemLista );
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Repositorio repositorio = listaRepositorios.get(position);

        holder.textoRepositorio.setText(repositorio.getNome());
        holder.textoDescricao.setText(repositorio.getNome());

        Uri uri = Uri.parse(repositorio.getAvatar());
        Glide.with(context).load( uri ).into(holder.imagem);


    }

    @Override
    public int getItemCount() {
        return listaRepositorios.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView textoRepositorio;
        private TextView textoDescricao;
        private ImageView imagem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textoRepositorio = itemView.findViewById(R.id.textRepositorio);
            textoDescricao = itemView.findViewById(R.id.textDescricao);
            imagem = itemView.findViewById(R.id.imagemUsuario);


        }
    }

}
