package com.vishal.apnagofoody.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vishal.apnagofoody.Domain.FoodDomain;
import com.vishal.apnagofoody.Helper.ManagementCart;
import com.vishal.apnagofoody.R;

public class DetailActivity extends AppCompatActivity {
    private Button addToCartBtn;
    private TextView plusBtn,minusBtn,titleTxt,feeTxt,descriptionTxt,numberOrderTxt,startTxt,caloryTxt,timeTxt;
    private ImageView picFood;
    private FoodDomain object;
    private int numberOrder = 1;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        managementCart =new ManagementCart(DetailActivity.this);

        initView();
        getBundle();
    }

    private void getBundle() {
        object =(FoodDomain) getIntent().getSerializableExtra("object");
        int drawableResourceId =this.getResources().getIdentifier(object.getPicUrl(),"drawable",this.getPackageName());
        Glide.with(this).load(drawableResourceId).into(picFood);

        titleTxt.setText(object.getTitle());
        feeTxt.setText("$"+object.getPrice());
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(""+numberOrder);
        caloryTxt.setText(object.getEnergy()+"");
        startTxt.setText(object.getScore()+"");
        timeTxt.setText(object.getTime()+" min");
        addToCartBtn.setText("Add to Cart - $"+Math.round(numberOrder*object.getPrice()));

        plusBtn.setOnClickListener(v -> {
            numberOrder=numberOrder + 1;
            numberOrderTxt.setText(""+numberOrder);
            addToCartBtn.setText("Add to Cart - $"+Math.round(numberOrder*object.getPrice()));

        });

        minusBtn.setOnClickListener(v -> {
            numberOrder=numberOrder - 1;
            numberOrderTxt.setText(""+numberOrder);
            addToCartBtn.setText("Add to Cart - $"+Math.round(numberOrder*object.getPrice()));

        });
        addToCartBtn.setOnClickListener(v -> {
            object.setNumberinCart(numberOrder);
            managementCart.insertFood(object);
            startActivity(new Intent(DetailActivity.this,MainActivity.class));
        });


    }

    private void initView() {
        addToCartBtn=findViewById(R.id.addToCartBtn);
        titleTxt=findViewById(R.id.titleTxt);
        feeTxt=findViewById(R.id.priceTxt);
        descriptionTxt=findViewById(R.id.descriptionTxt);
        numberOrderTxt=findViewById(R.id.numberItemTxt);
        plusBtn=findViewById(R.id.plusCarBtn);
        minusBtn=findViewById(R.id.minusCartBtn);
        picFood=findViewById(R.id.foodPic);
        startTxt=findViewById(R.id.starTxt);
        caloryTxt=findViewById(R.id.CalTxt);
        timeTxt=findViewById(R.id.timeTxt);
    }
}