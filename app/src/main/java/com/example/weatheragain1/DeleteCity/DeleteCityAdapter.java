package com.example.weatheragain1.DeleteCity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.weatheragain1.R;
import com.example.weatheragain1.sqlite.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

public class DeleteCityAdapter extends ArrayAdapter<DeleteCityType> {
    int id;
    public DeleteCityAdapter(@NonNull Context context, int resource, @NonNull List<DeleteCityType> objects) {
        super(context, resource, objects);
        id = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DeleteCityType type = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(id, parent, false);
        TextView textView = view.findViewById(R.id.delete_city);
        ImageView imageView = view.findViewById(R.id.imageView2);
        textView.setText(type.getCity());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setMessage("是否删除");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),type.getCity(),Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
        return view;
    }
}
