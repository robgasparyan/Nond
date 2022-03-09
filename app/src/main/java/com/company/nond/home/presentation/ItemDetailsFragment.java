package com.company.nond.home.presentation;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.company.nond.databinding.FragmentItemDetailsBinding;
import com.end.nond.extensions.ExtensionKt;

public class ItemDetailsFragment extends Fragment {
    private FragmentItemDetailsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentItemDetailsBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView();
    }

    private void setupView() {
        if (getArguments() != null) {
            ExtensionKt.loadImage(
                    binding.itemImage,
                    getArguments().getString(HomeFragment.IMAGE_URL, ""),
                    ExtensionKt.getImageLoader(requireContext()));
            binding.itemName.setText(getArguments().getString(HomeFragment.ITEM_NAME, ""));
            binding.itemPrice.setText(getArguments().getString(HomeFragment.ITEM_PRICE, ""));
        }

    }
}
