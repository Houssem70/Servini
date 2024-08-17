package com.example.servini;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_home extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Dialog dialog;
    private Button ShowDialog;

    // IDs of your CardViews
    private static final int[] CARD_VIEW_IDS = {
            R.id.electricien,
            R.id.macon,
            R.id.menuisier,
            R.id.jardinier,
            R.id.peintre,
            R.id.plombier
    };
    public fragment_home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_home.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_home newInstance(String param1, String param2) {
        fragment_home fragment = new fragment_home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        dialog = new Dialog(requireContext());
        dialog.setContentView(R.layout.custom_dialog_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(requireContext().getDrawable(R.drawable.custom_dialog_background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        Button btnOkay = dialog.findViewById(R.id.btn_okay);
        Button btnCancel = dialog.findViewById(R.id.btn_cancel);

        btnOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "Okay", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                Intent intent = new Intent(getActivity(), Map.class);
                startActivity(intent);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "Cancel", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                showSecondDialog();
            }
        });

        for (int cardViewId : CARD_VIEW_IDS) {
            CardView cardView = view.findViewById(cardViewId);
            cardView.setOnClickListener(this);
        }

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.electricien)
        {
            dialog.show();
        } else if (view.getId()==R.id.menuisier) {
            dialog.show();
        } else if (view.getId()==R.id.macon) {
            dialog.show();
        } else if (view.getId()==R.id.jardinier) {
            dialog.show();
        } else if (view.getId()==R.id.plombier) {
            dialog.show();
        }
        else if (view.getId()==R.id.peintre) {
            dialog.show();
        }
    }
    private void showSecondDialog() {
        // Set up the second dialog
        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(R.layout.custom_dialog2); // Replace with your layout
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(requireContext().getDrawable(R.drawable.custom_dialog_background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        Button btnOkay = dialog.findViewById(R.id.btn_okay);
        Button btnCancel = dialog.findViewById(R.id.btn_cancel);

        btnOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "Okay", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                Intent intent = new Intent(getActivity(), Map.class);
                startActivity(intent);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "Cancel", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}