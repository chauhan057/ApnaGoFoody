package com.vishal.apnagofoody.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.vishal.apnagofoody.Adapter.CartListAdapter;
import com.vishal.apnagofoody.Helper.ChangeNumberItemsListner;
import com.vishal.apnagofoody.Helper.ManagementCart;
import com.vishal.apnagofoody.R;

public class CartActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView  recyclerViewList;
    private ManagementCart managementCart;
    private TextView totalFeeTxt,taxTxt,deliveryTxt,totalTxt,emptyTxt;
    private double tax;
    private ScrollView scrollView;
    private Button backBtn;
    private ImageView cartBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        managementCart=new ManagementCart(this);

        initView();
        initList();
        calculateCart();
        setVariable();

        cartBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this,MainActivity.class));
            }
        });

    }

    private void setVariable() {
        backBtn.setOnClickListener(v -> {
            Toast.makeText(CartActivity.this, "Congrates, Order Success", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter=new CartListAdapter(managementCart.getListCart(),this, () -> calculateCart());
        recyclerViewList.setAdapter(adapter);

        if (managementCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }
    private  void calculateCart(){
        double percentTax =0.02; //you can changed this item for tax price
        double delivery =10.0;
        tax=Math.round((managementCart.getTotalFee()*percentTax)*100.0)/100.0;

        double total =Math.round((managementCart.getTotalFee()+tax+delivery)*100.0)/100.0;
        double itemTotal =Math.round(managementCart.getTotalFee()*100.0)/100.0;

        totalFeeTxt.setText("$"+ itemTotal);
        taxTxt.setText("$"+tax);
        deliveryTxt.setText("$"+delivery);
        totalTxt.setText("$"+total);
    }

    private void initView() {
        totalFeeTxt=findViewById(R.id.totalFeeTxt);
        taxTxt=findViewById(R.id.taxTxt);
        deliveryTxt=findViewById(R.id.deliveryTxt);
        totalTxt=findViewById(R.id.totalTxt);
        recyclerViewList=findViewById(R.id.view3);
        scrollView=findViewById(R.id.scrollView);
        backBtn=findViewById(R.id.backBtn);
        emptyTxt=findViewById(R.id.emptyTxt);
        cartBackBtn =findViewById(R.id.cartBackBtn);
    }
}