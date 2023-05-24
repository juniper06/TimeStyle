package com.example.timestyle.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timestyle.ImageHelper;
import com.example.timestyle.ProductDetailActivity;
import com.example.timestyle.R;
import com.example.timestyle.database.entity.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> productList;
    private Context context;

    public ProductAdapter(Context context, List<Product> productModelList) {
        this.context = context;
        this.productList = productModelList;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Product product = productList.get(position);
        String name = product.name;
        Bitmap bmpThumbnail = ImageHelper.convertByteArrayToBitmap(product.image);
        viewHolder.tvName.setText(name);
        viewHolder.ivProductImage.setImageBitmap(bmpThumbnail);
        viewHolder.layoutProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("id", product.id);
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivProductImage;
        public TextView tvName;
        public LinearLayout layoutProduct;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            tvName = view.findViewById(R.id.tvName);
            ivProductImage = view.findViewById(R.id.iv_product_image);
            layoutProduct = view.findViewById(R.id.layout_product);
        }
    }
}
