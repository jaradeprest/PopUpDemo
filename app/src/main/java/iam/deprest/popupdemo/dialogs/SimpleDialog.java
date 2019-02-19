package iam.deprest.popupdemo.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class SimpleDialog extends DialogFragment {



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder bobDeBuilder = new AlertDialog.Builder(getActivity());
        bobDeBuilder.setTitle("Order");
        bobDeBuilder.setMessage("Are you sure you want to order this?");
        bobDeBuilder.setNegativeButton("Cancel", null);
        bobDeBuilder.setPositiveButton("Place order", null);

        return bobDeBuilder.create();
    }
}
