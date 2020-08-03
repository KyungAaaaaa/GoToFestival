package com.example.stampinseoul2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ThemeCultureFragment extends Fragment {
    private RecyclerView themeRecyvlerView;
    private LinearLayoutManager layoutManager;
    public static ThemeCultureFragment newInstance(){
        ThemeCultureFragment themeCultureFragment = new ThemeCultureFragment();
        return themeCultureFragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_theme, container, false);
        themeRecyvlerView = rootView.findViewById(R.id.themeRecyvlerView);

        layoutManager = new LinearLayoutManager(getActivity());
        themeRecyvlerView.setLayoutManager(layoutManager);
        rootView.setBackgroundColor(Color.RED);
        return rootView;
    }

}
