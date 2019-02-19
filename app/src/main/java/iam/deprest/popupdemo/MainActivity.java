package iam.deprest.popupdemo;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import iam.deprest.popupdemo.dialogs.SimpleDialog;
import iam.deprest.popupdemo.dialogs.ToppingsDialog;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fabSnackBar;
    private Button btnToast, btnSimpleDialog, btnToppings;

    //onclicklistener nodig om knop te gebruiken
    private View.OnClickListener fabSnackBarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //uitvoer van knop:
            Snackbar sb = Snackbar.make(findViewById(R.id.main_container), "Hello pizza", Snackbar.LENGTH_INDEFINITE);
            sb.setActionTextColor(getResources().getColor(R.color.colorAccent));
            sb.setAction("Close", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TEST", "Clicked on snackbar");
                }
            });
            sb.show();
        }
    };


    //anonymous inner class ==
    private View.OnClickListener btnSimpleDialogOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SimpleDialog simpleDialog = new SimpleDialog();
            simpleDialog.show(getSupportFragmentManager(), "id");
        }
    };

    private View.OnClickListener toastOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "Bon Appetit!", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener toppingsOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ToppingsDialog toppingsDialog = new ToppingsDialog();
            //via setter een listener koppelen
            toppingsDialog.setDialogConfirmListener(dialogConfirmListener);
            toppingsDialog.show(getSupportFragmentManager(), "id");
        }
    };

    private ToppingsDialog.DialogConfirmListener dialogConfirmListener = new ToppingsDialog.DialogConfirmListener() {
        @Override
        public void confirm(ArrayList<String> toppings) {
            Toast.makeText(getApplicationContext(), toppings.toString(), Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabSnackBar = findViewById(R.id.fab_snackbar);
        //onclicklistener toevoegen aan knop
        fabSnackBar.setOnClickListener(fabSnackBarOnClickListener);

        btnToast = findViewById(R.id.btn_toast);
        //reageren als er geklikt wordt: CLICKLISTENER. Hier koppelen we knop + listener:
        btnToast.setOnClickListener(toastOnClickListener);

        btnSimpleDialog = findViewById(R.id.btn_simple_dialog);
        btnSimpleDialog.setOnClickListener(btnSimpleDialogOnClickListener);

        btnToppings = findViewById(R.id.btn_toppings);
        btnToppings.setOnClickListener(toppingsOnClickListener);
    }
}
