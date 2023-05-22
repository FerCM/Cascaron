package com.fiscaliageneralags.fiscalia.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fiscaliageneralags.fiscalia.Activities.BuzonFiscalMain;
import com.fiscaliageneralags.fiscalia.Activities.CheckAuto;
import com.fiscaliageneralags.fiscalia.Activities.ComunicacionSocial;
import com.fiscaliageneralags.fiscalia.Activities.NumerosEmergenciaMain;
import com.fiscaliageneralags.fiscalia.Activities.OrientacionMain;
import com.fiscaliageneralags.fiscalia.Activities.PredenunciaMain;
import com.fiscaliageneralags.fiscalia.Activities.QueHacerEnCasoMain;
import com.fiscaliageneralags.fiscalia.Activities.UbicacionAgenciasMain;
import com.fiscaliageneralags.fiscalia.EvidenciaAnonimaMainModule;
import com.fiscaliageneralags.fiscalia.R;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

    private Context context;
   View itemView;
    private List <Model> serviciosList;
    public MenuAdapter(Context context, List<Model> listaServicios) {
        this.context= context;
        this.serviciosList= listaServicios;
   }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Model model = serviciosList.get(position);
        holder.servicio.setText(model.getName());
        Glide.with(context)
                .load(model.getImg())
                .into(holder.img);

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent navigationIntent;
               switch(position){
                   case 0:
                       System.out.println("Amonos recio");
                       navigationIntent = new Intent(context, PredenunciaMain.class);
                       break;
                   case 1:
                       navigationIntent = new Intent(context, EvidenciaAnonimaMainModule.class);

                       break;
                   case 2:
                       navigationIntent = new Intent(context, UbicacionAgenciasMain.class);

                       break;
                   case 3:
                       navigationIntent = new Intent(context, NumerosEmergenciaMain.class);

                       break;
                   case 4:
                       navigationIntent = new Intent(context, BuzonFiscalMain.class);

                       break;
                   case 5:
                       navigationIntent = new Intent(context, QueHacerEnCasoMain.class);

                       break;
                   case 6:
                       navigationIntent = new Intent(context, OrientacionMain.class);
                       break;
                   case 7:
                       navigationIntent = new Intent(context, ComunicacionSocial.class);

                       break;
                   case 8:
                       navigationIntent = new Intent(context, CheckAuto.class);
                       break;
                   default:
                       throw new IllegalStateException("Unexpected value: " + position);
               }
               context.startActivity(navigationIntent);
           }
       });
    }

    @Override
    public int getItemCount() {
        return serviciosList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView servicio;
        public ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            servicio= itemView.findViewById(R.id.text);
            img = itemView.findViewById(R.id.icon);
        }
    }
}
