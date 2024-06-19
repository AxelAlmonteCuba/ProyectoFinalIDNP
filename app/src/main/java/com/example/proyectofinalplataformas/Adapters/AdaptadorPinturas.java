package com.example.proyectofinalplataformas.Adapters;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinalplataformas.Entitys.PinturasVo;
import com.example.proyectofinalplataformas.R;

import java.util.ArrayList;

public class AdaptadorPinturas extends RecyclerView.Adapter<AdaptadorPinturas.ViewHolderPinturas> {

    ArrayList<PinturasVo> listaPinturas;

    public AdaptadorPinturas(ArrayList<PinturasVo> listaPinturas) {
        this.listaPinturas = listaPinturas;
    }

    @Override
    public ViewHolderPinturas onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_pinturas,null,false);

        return new ViewHolderPinturas(view);
    }

    @Override
    public void onBindViewHolder( ViewHolderPinturas holder, int position) {

        holder.etNombre.setText(listaPinturas.get(position).getNombre());
        holder.etArtista.setText(listaPinturas.get(position).getArtista());
        holder.foto.setImageResource(listaPinturas.get(position).getFoto());
    }

    @Override
    public int getItemCount() {
        return listaPinturas.size();
    }

    public class ViewHolderPinturas extends RecyclerView.ViewHolder {

        TextView etNombre,etArtista;
        ImageView foto;
        public ViewHolderPinturas( View itemView) {
            super(itemView);
            etNombre=(TextView) itemView.findViewById(R.id.idNombre);
            etArtista=(TextView) itemView.findViewById(R.id.idArtista);
            foto=(ImageView) itemView.findViewById(R.id.idImagen);
        }
    }
}
