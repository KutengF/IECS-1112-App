package com.example.breakfastorderingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements MenuAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private List<MenuItem> menuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        menuItems = generateDummyData();
        MenuAdapter adapter = new MenuAdapter(menuItems, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<MenuItem> generateDummyData() {
        String[] imageUrls = new String[]{
                "https://www.assaggioboston.com/news/wp-content/uploads/2018/12/shutterstock_228423190.jpg",
                "https://one15marina.com/wp-content/uploads/2020/05/Food-Plating.jpg",
                "https://2.bp.blogspot.com/-fj8Tvd9vLtE/WYRZGo2vjWI/AAAAAAAAA5g/qZcLcmIzx_cKUwhRoTrqlHKPEBjoQPlvwCPcBGAYYCw/s1600/515a02bf50d48_1.jpg",
                "https://example.com/french_toast.jpg",
                "https://i.pinimg.com/736x/76/6e/12/766e1207dc4895fa7c11322a90682e44--plating-ideas-food-plating.jpg",
                "https://discoveryourindonesia.com/wp-content/uploads/2017/04/Food-in-Sumbawa.jpg",
                "https://eatforlonger.com/wp-content/uploads/2021/07/Does-All-Fruit-Have-Seeds-768x432.jpg",
                "https://i2.wp.com/www.findhomeremedy.com/wp-content/uploads/2011/12/Fruit-Diet.jpg",
                "https://tf01.themeruby.com/recipe/wp-content/uploads/sites/2/2019/07/f120.jpg",
                "https://th.bing.com/th/id/OIP.jDpqlo5HczK-tbKXdgjWJwHaE7?pid=ImgDet&rs=1",
                "https://comfybelly.com/wp-content/uploads/2014/07/Chow-Mein-low-res-4-of-7-600x400.jpg",
                "https://cdn.shopify.com/s/files/1/1454/9374/articles/Pad_Thai_4_main_1400x.jpg?v=1593189992"
        };

        List<MenuItem> items = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 12; i++) {
            items.add(new MenuItem(
                    "Item " + (i + 1),
                    "Description " + (i + 1),
                    random.nextDouble() * 10 + 1, // Random price between $1 and $11
                    imageUrls[random.nextInt(imageUrls.length)]
            ));
        }

        return items;
    }

    @Override
    public void onAddToCartClick(MenuItem menuItem) {
        Intent intent = new Intent(MainActivity.this, CheckoutActivity.class);
        intent.putExtra("menuItem", menuItem);
        startActivity(intent);
    }
}