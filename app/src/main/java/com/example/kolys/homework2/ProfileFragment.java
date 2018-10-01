package com.example.kolys.homework2;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    public TextView loginTV;
    public TextView emailTV;
    public Button editBtn;
    public String login;
    public String email;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        Bundle args = new Bundle();
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailTV = view.findViewById(R.id.tv_email);
        loginTV = view.findViewById(R.id.tv_login);
        editBtn = view.findViewById(R.id.btn_edit);
        loginTV.setText(login);
        emailTV.setText(email);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragmentFragment dialogFragmentFragment = new DialogFragmentFragment();
                dialogFragmentFragment.setCancelable(false);
                dialogFragmentFragment.show(getActivity().getSupportFragmentManager(), "tag");
            }
        });
    }

    public void saveData(String login, String email) {
        emailTV.setText(email);
        loginTV.setText(login);
        this.login = login;
        this.email = email;
    }
}
