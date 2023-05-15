package com.vishal.apnagofoody.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.vishal.apnagofoody.Adapter.FoodListAdapter;
import com.vishal.apnagofoody.Domain.FoodDomain;
import com.vishal.apnagofoody.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterFoodList;
    private RecyclerView recyclerViewFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerview();
        bottomNavigation();

    }

    private void bottomNavigation() {
        LinearLayout homeBtn =findViewById(R.id.homeBtn);
        LinearLayout cartBtn =findViewById(R.id.cartBtn);
        LinearLayout supportBtn =findViewById(R.id.supportBtn);
        LinearLayout settingBtn =findViewById(R.id.settingBtn);

        homeBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,MainActivity.class)));

        cartBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,CartActivity.class)));

        supportBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,SupportActivity.class)));

        settingBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,SettingActivity.class)));
    }

    private void initRecyclerview() {
        ArrayList<FoodDomain> items = new ArrayList<>();
        items.add(new FoodDomain("Cheese Burger",
                "Satisfy your cravings with our juicy cheese burger. \n"+
                " Made with 100% Angus beef patty and topped with\n"+
                "melted cheddar cheese,fresh lettuce ,tommato, and \n"+
                "our secret sauce, this classic burger will leave you\n"+
                " wanting more.served with chispy fries and a drink,\n"+
                "it's the perfect meal for any occusion.","fast_1", 15, 20, 120, 4));
        items.add(new FoodDomain("Pizza Peperoni",
                "Satisfy your cravings with our juicy cheese burger. \n"+
                        " Made with 100% Angus beef patty and topped with\n"+
                        "melted cheddar cheese,fresh lettuce ,tommato, and \n"+
                        "our secret sauce, this classic burger will leave you\n"+
                        " wanting more.served with chispy fries and a drink,\n"+
                        "it's the perfect meal for any occusion.","fast_2", 20, 25, 100, 5));
        items.add(new FoodDomain("Vegetable Pizza",
                "Satisfy your cravings with our juicy cheese burger. \n"+
                        " Made with 100% Angus beef patty and topped with\n"+
                        "melted cheddar cheese,fresh lettuce ,tommato, and \n"+
                        "our secret sauce, this classic burger will leave you\n"+
                        " wanting more.served with chispy fries and a drink,\n"+
                        "it's the perfect meal for any occusion.","fast_3", 35, 27, 200, 4.5));
        items.add(new FoodDomain("Cheese Burger with samosa",
                "Satisfy your cravings with our juicy cheese burger. \n"+
                        " Made with 100% Angus beef patty and topped with\n"+
                        "melted cheddar cheese,fresh lettuce ,tommato, and \n"+
                        "our secret sauce, this classic burger will leave you\n"+
                        " wanting more.served with chispy fries and a drink,\n"+
                        "it's the perfect meal for any occusion.","fast_1", 15, 25, 120, 4.9));

        recyclerViewFood=findViewById(R.id.view1);
        recyclerViewFood.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        adapterFoodList=new FoodListAdapter(items);
        recyclerViewFood.setAdapter(adapterFoodList);
    }
}