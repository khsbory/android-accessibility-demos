package com.nvisions.solutionsforaccessibility.FullscreenFragmentLayer;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.nvisions.solutionsforaccessibility.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FullscreenLayerBadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FullscreenLayerBadFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private LinearLayout ExampleMain;
    private FrameLayout FragmentFrame;
    private Activity rootActivity;
    private FragmentManager fm;
    private FragmentTransaction tr;
    private TextView ExitPoint;
    private TextView EntryPoint;
    private Button btnCloseLayer;


    public FullscreenLayerBadFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fullscreenLayerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FullscreenLayerBadFragment newInstance(String param1, String param2) {
        FullscreenLayerBadFragment fragment = new FullscreenLayerBadFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        rootActivity = getActivity();
        fm = getParentFragmentManager();
        tr = fm.beginTransaction();
        FragmentFrame = rootActivity.findViewById(R.id.FragmentLayerExample_Layer);
        ExampleMain = rootActivity.findViewById(R.id.FragmentLayerExample_Main);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fullscreen_layer_bad,container,false);
        
        ExitPoint = rootActivity.findViewById(R.id.fullscreen_fragment_layer_mainEntry);
        btnCloseLayer = v.findViewById(R.id.btn_CloseFragmentLayer);
        btnCloseLayer.setOnClickListener(this::goToBack);
        EntryPoint = v.findViewById(R.id.fullscreen_fragment_layer_fragEntry);
        return v;
    }

    public void goToBack(View v){
        fm.popBackStack();
    }
}