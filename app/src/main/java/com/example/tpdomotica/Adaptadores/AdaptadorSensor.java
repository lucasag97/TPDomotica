package com.example.tpdomotica.Adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tpdomotica.R;
import com.example.tpdomotica.Entidades.Sensor;

import java.util.ArrayList;

public class AdaptadorSensor extends
        RecyclerView.Adapter<AdaptadorSensor.SensorViewHolder> implements View.OnClickListener {
    ArrayList<Sensor> ListaSensores;
    private View.OnClickListener listener;

    public AdaptadorSensor(ArrayList<Sensor> listaSensores){
        this.ListaSensores = listaSensores;
    }

    @Override
    public SensorViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_sensor,null,false);
        view.setOnClickListener(this);
        return new AdaptadorSensor.SensorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SensorViewHolder sensorViewHolder, int i) {
        sensorViewHolder.Nombre.setText(sensorViewHolder.Nombre.getContext().getResources().getString(R.string.sensor)+" "+ListaSensores.get(i).getTIPO());
        String tipo = ListaSensores.get(i).getTIPO();
        int valor = ListaSensores.get(i).getVALOR_ACTUAL();
        int umbral = ListaSensores.get(i).getUMBRAL();

        switch (tipo){
            case "iluminacion":
                sensorViewHolder.Informacion.setText(sensorViewHolder.Informacion.getContext().getResources().getString(R.string.ilu_bien));
                sensorViewHolder.icono.setImageResource(R.mipmap.ic_iluminacion_foreground);
                break;
            case "gas":
                if(valor < umbral) {
                    sensorViewHolder.Informacion.setText(sensorViewHolder.Informacion.getContext().getResources().getString(R.string.gas_lib));
                }else{
                    sensorViewHolder.Informacion.setText(sensorViewHolder.Informacion.getContext().getResources().getString(R.string.gas_det));
                }
                sensorViewHolder.icono.setImageResource(R.mipmap.ic_humo_foreground);
                break;
            case "movimiento":
                if(valor < umbral) {
                    sensorViewHolder.Informacion.setText(sensorViewHolder.Informacion.getContext().getResources().getString(R.string.no_mov));
                }else{
                    sensorViewHolder.Informacion.setText(sensorViewHolder.Informacion.getContext().getResources().getString(R.string.mov_si));
                }
                sensorViewHolder.icono.setImageResource(R.mipmap.ic_movimiento_foreground);
                break;
            case "temperatura":
                sensorViewHolder.Informacion.setText(sensorViewHolder.Informacion.getContext().getResources().getString(R.string.valor_actual)+": "+ListaSensores.get(i).getVALOR_ACTUAL()+"°C");
                sensorViewHolder.icono.setImageResource(R.mipmap.ic_temperatura_foreground);
        }
    }

    @Override
    public int getItemCount() {
        return ListaSensores.size();
    }
    public void setOnclickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }
    }

    public class SensorViewHolder extends RecyclerView.ViewHolder {
        TextView Nombre,Informacion;
        ImageView icono;
        public SensorViewHolder(View itemView){
            super(itemView);
            Nombre = (TextView) itemView.findViewById(R.id.idNombre);
            Informacion = (TextView) itemView.findViewById(R.id.idInfo);
            icono = (ImageView) itemView.findViewById(R.id.idImagen);
        }
    }
}
