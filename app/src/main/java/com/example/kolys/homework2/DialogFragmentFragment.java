package com.example.kolys.homework2;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class DialogFragmentFragment extends DialogFragment {

    View dialogView;
    EditText login;
    EditText email;
    MyListener callback;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialogfragment, null);
        login = dialogView.findViewById(R.id.et_login);
        email = dialogView.findViewById(R.id.et_email);
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
        adb.setTitle("Input Data")
                .setView(dialogView)
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                })
                .setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        save();
                    }
                });
        return adb.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MyListener) {
            callback = (MyListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement MyListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    private void save() {
        callback.callBack( login.getText().toString(), email.getText().toString());
    }
}