package com.example.proyectofinalplataformas.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinalplataformas.Entitys.FavoritosVo;
import com.example.proyectofinalplataformas.Entitys.PinturasVo;
import com.example.proyectofinalplataformas.R;

import java.util.ArrayList;

public class AdaptadorFavoritos extends RecyclerView.Adapter<AdaptadorFavoritos.ViewHolderFavoritos> {


    ArrayList<FavoritosVo> listaFavoritos;
    public AdaptadorFavoritos(ArrayList<FavoritosVo> listaFavoritos) {
        this.listaFavoritos = listaFavoritos;
    }


    @Override
    public ViewHolderFavoritos onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_favoritos,null,false);

        return new ViewHolderFavoritos(view);
    }

    @Override
    public void onBindViewHolder( ViewHolderFavoritos holder, int position) {

        holder.etNombre.setText(listaFavoritos.get(position).getNombre());
        holder.etArtista.setText(listaFavoritos.get(position).getArtista());
        holder.foto.setImageResource(listaFavoritos.get(position).getFoto());
        holder.corazon.setImageResource(listaFavoritos.get(position).getCorazon());
    }

    @Override
    public int getItemCount() {
        return listaFavoritos.size();
    }

    public static class ViewHolderFavoritos extends RecyclerView.ViewHolder {
        TextView etNombre,etArtista;
        ImageView foto,corazon;
        public ViewHolderFavoritos( View itemView) {
            super(itemView);
            etNombre=(TextView) itemView.findViewById(R.id.idNombre2);
            etArtista=(TextView) itemView.findViewById(R.id.idArtista2);
            foto=(ImageView) itemView.findViewById(R.id.idImagen2);
            corazon=(ImageView) itemView.findViewById(R.id.idFavorito);



        }
    }
}
