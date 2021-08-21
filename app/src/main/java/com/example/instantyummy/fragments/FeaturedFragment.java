package com.example.instantyummy.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.instantyummy.databinding.FragmentFeaturedBinding;

public class FeaturedFragment extends Fragment {

    private FragmentFeaturedBinding binding;

    public FeaturedFragment() {
        // Required empty public constructor
    }

    public static FeaturedFragment newInstance() {
        FeaturedFragment fragment = new FeaturedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFeaturedBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
