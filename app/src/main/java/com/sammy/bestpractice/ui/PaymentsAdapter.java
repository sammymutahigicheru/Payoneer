package com.sammy.bestpractice.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sammy.bestpractice.R;
import com.sammy.bestpractice.data.models.Applicable;
import com.sammy.bestpractice.databinding.LayoutPaymentItemBinding;

import java.util.Random;

public class PaymentsAdapter extends ListAdapter<Applicable, PaymentsAdapter.PaymentsViewHolder> {


    public PaymentsAdapter() {
        super(new DiffUtil.ItemCallback<Applicable>() {
            @Override
            public boolean areItemsTheSame(@NonNull Applicable oldItem, @NonNull Applicable newItem) {
                return oldItem.getCode().equals(newItem.getCode());
            }

            @Override
            public boolean areContentsTheSame(@NonNull Applicable oldItem, @NonNull Applicable newItem) {
                return oldItem.getLabel().equals(newItem.getLabel());
            }
        });
    }

    @NonNull
    @Override
    public PaymentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutPaymentItemBinding binding = LayoutPaymentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PaymentsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentsViewHolder holder, int position) {
        Applicable applicable = getItem(position);
        LayoutPaymentItemBinding binding = holder.binding;
        setRecyclerAnimation(holder.itemView, position);
        binding.tvPaymentName.setText(applicable.getLabel());

        Glide.with(binding.getRoot().getContext())
                .load(applicable.getLinks().getLogo())
                .placeholder(R.drawable.ic_baseline_credit_card_24)
                .into(binding.imgNetworkSrc);

    }

    private void setRecyclerAnimation(View view, int position) {
        int lastPosition = -1;
        if (position > lastPosition) {
            ScaleAnimation anim =
                    new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(new Random().nextInt(1001));
            view.startAnimation(anim);
            lastPosition = position;
        }
    }


    static class PaymentsViewHolder extends RecyclerView.ViewHolder {
        LayoutPaymentItemBinding binding;

        public PaymentsViewHolder(LayoutPaymentItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


    }

}