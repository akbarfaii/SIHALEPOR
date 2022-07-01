package com.example.halepor.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;

import com.example.halepor.R;

public class fragmentHome extends Fragment {
    ViewFlipper img_flipper;

    public fragmentHome() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        img_flipper = view.findViewById(R.id.img_flipper);
        int images[] = {R.drawable.hpgenss,R.drawable.hpakhlak,R.drawable.hptiang};

        for (int image : images){
            flipper_images(image);
        }
        return view;
    }
    public void flipper_images(int image){
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);

        img_flipper.addView(imageView);
        img_flipper.setFlipInterval(2500);
        img_flipper.setAutoStart(true);
        img_flipper.setInAnimation(getActivity(), android.R.anim.slide_in_left);
        img_flipper.setOutAnimation(getActivity(), android.R.anim.slide_out_right);
    }
}