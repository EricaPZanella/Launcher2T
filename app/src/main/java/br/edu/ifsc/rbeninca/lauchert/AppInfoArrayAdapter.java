package br.edu.ifsc.rbeninca.lauchert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AppInfoArrayAdapter extends RecyclerView.Adapter<AppInfoArrayAdapter.MyViewHolder> {
    Context mContext;
    int mResource;
    public  ArrayList<AppInfo> mDataset;



    public AppInfoArrayAdapter( Context context, int resource, ArrayList<AppInfo> objects) {
        super();
        this.mContext=context;
        this.mResource=resource;
        mDataset=objects;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_list_app,parent,false);
        MyViewHolder vh = new MyViewHolder(itemView);
        return  vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.textViewAppName.setText(mDataset.get(position).appname);

        holder.imageView.setImageDrawable(mDataset.get(position).icon);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewAppName;
        TextView textViewPackageName;
        TextView textViewVersionCode;
        TextView textViewVersionName;
        TextView textViewDataDir;
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textViewAppName= (TextView) itemView.findViewById(R.id.textViewAppName);
            imageView=(ImageView) itemView.findViewById(R.id.imageView);
        }

    }

};

//    public AppInfoArrayAdapter( Context context, int resource, ArrayList<AppInfo> objects) {
//        super(context, resource, objects);
//        this.mContext=context;
//        this.mResource=resource;
//
//
//    }
//
//
//    @Override
//    public View getView(int position,  View convertView,  ViewGroup parent) {
//
//
//        LayoutInflater inflater = LayoutInflater.from(mContext);
//        convertView = inflater.inflate(mResource,parent,false);
//
////Associando as referencias das views instanciadas para o item com identificadores locais
//        TextView textViewAppName= (TextView) convertView.findViewById(R.id.textViewAppName);
//        TextView textViewPackageName= (TextView) convertView.findViewById(R.id.textViewPackageName);
//        TextView textViewVersionCode= (TextView) convertView.findViewById(R.id.textViewVersionCode);
//        TextView textViewVersionName= (TextView) convertView.findViewById(R.id.textViewVersionName);
//        TextView textViewDataDir= (TextView) convertView.findViewById(R.id.textViewDataDir);
//        ImageView imageView=(ImageView) convertView.findViewById(R.id.imageView);
//
//        AppInfo appInfo = (AppInfo) getItem(position);
//
//        textViewAppName.setText(appInfo.appname);
//        textViewPackageName.setText(appInfo.pname);
//        textViewVersionCode.setText(Integer.toString(appInfo.versionCode));
//        textViewVersionName.setText(appInfo.versionName);
//        textViewDataDir.setText(appInfo.datadir);
//        imageView.setImageDrawable(appInfo.icon);
//
//        return convertView;
//    }
//}
