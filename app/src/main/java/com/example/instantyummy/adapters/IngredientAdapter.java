package com.example.instantyummy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instantyummy.databinding.ItemIngredientBinding;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {
    private Context context;
    private List<String> ingredients;

    public IngredientAdapter(Context context, List<String> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemIngredientBinding binding = ItemIngredientBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String ingredient = ingredients.get(position);
        holder.bind(ingredient);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemIngredientBinding binding;

        public ViewHolder(@NonNull ItemIngredientBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(this);
        }

        public void bind(String ingredient) {
            binding.textViewIngredientName.setText(ingredient);
        }

        @Override
        public void onClick(View view) {
            //go to details screen
        }
    }
}