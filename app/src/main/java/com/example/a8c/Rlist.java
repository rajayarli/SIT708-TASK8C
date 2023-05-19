package com.example.a8c;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.a8c.MyAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Rlist extends AppCompatActivity {
    private static List<String> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private static MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rlist);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new MyAdapter(itemList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        addItemToList(url);
    }

    public static void addItemToList(String url) {
        itemList.add(url);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}

