package com.vishal.apnagofoody.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.vishal.apnagofoody.Domain.FoodDomain;
import com.vishal.apnagofoody.Helper.ChangeNumberItemsListner;
import com.vishal.apnagofoody.Helper.ManagementCart;
import com.vishal.apnagofoody.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {

    ArrayList<FoodDomain> listFoodSelected;
    private ManagementCart managementCart;
    ChangeNumberItemsListner changeNumberItemsListner;

    public CartListAdapter(ArrayList<FoodDomain> listFoodSelected, Context context, ChangeNumberItemsListner changeNumberItemsListner) {
        this.listFoodSelected = listFoodSelected;
        managementCart=new ManagementCart(context);
        this.changeNumberItemsListner = changeNumberItemsListner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.title.setText(listFoodSelected.get(position).getTitle());
    holder.feeEachItem.setText("$"+listFoodSelected.get(position).getPrice());
    holder.totalEachItem.setText("$"+ Math.round((listFoodSelected.get(position).getNumberinCart()* listFoodSelected.get(position).getPrice())));
    holder.num.setText((String.valueOf(listFoodSelected.get(position).getNumberinCart())));

    int drawableResourceId=holder.itemView.getResources().getIdentifier(listFoodSelected.get(position).getPicUrl(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .transform(new GranularRoundedCorners(30,30,30,30))
                .into(holder.pic);

        holder.plusItem.setOnClickListener(v ->
                managementCart.plusNumberFood(listFoodSelected, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListner.changed();
        }));

        holder.minusItem.setOnClickListener(v ->
                managementCart.minusNumberFood(  listFoodSelected, position, () -> {
                    notifyDataSetChanged();
                    changeNumberItemsListner.changed();
                }));

    }

    @Override
    public int getItemCount() {
        return listFoodSelected.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,feeEachItem,plusItem,minusItem;
        ImageView pic;
        TextView totalEachItem,num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.titleTxt);
            pic=itemView.findViewById(R.id.pic);
            feeEachItem=itemView.findViewById(R.id.feeEachItem);
            totalEachItem=itemView.findViewById(R.id.totalEachItem);
            plusItem=itemView.findViewById(R.id.plusCartBtn);
            minusItem=itemView.findViewById(R.id.minusCartBtn);
            num=itemView.findViewById(R.id.numberItemTxt);

        }
    }
}
