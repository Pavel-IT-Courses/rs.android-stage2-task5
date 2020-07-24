package com.example.catapplication.adapter;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.PermissionChecker;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catapplication.CatViewModel;
import com.example.catapplication.DetailedActivity;
import com.example.catapplication.MainActivity;

import static androidx.core.app.ActivityCompat.requestPermissions;
import static androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale;
import static androidx.core.content.PermissionChecker.checkSelfPermission;

public class Tester extends RecyclerView.Adapter implements View.OnClickListener {
    private AppCompatActivity activity;
    public Tester(AppCompatActivity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private void doSome(ImageView imageView) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String root = Environment.getExternalStorageDirectory().toString();

                if (checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE )
                    != PermissionChecker.PERMISSION_GRANTED) {

                    // Should we show an explanation?
                    if (shouldShowRequestPermissionRationale(
                        activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        // Explain to the user why we need to read the contacts
                    }

                    requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
                    Toast.makeText(activity, "HURA", Toast.LENGTH_LONG);

                    // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
                    // app-defined int constant that should be quite unique

                    return;
                }

                Intent intent = new Intent(activity, DetailedActivity.class);
                intent.putExtra("url", "image");
                String imageUrl = intent.getStringExtra("url");
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
