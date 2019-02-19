package iam.deprest.popupdemo.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import java.util.ArrayList;
import java.util.Arrays;

public class ToppingsDialog extends DialogFragment {

    //listener om gekozenlijst door te geven na het drukken op ok
    //interface nodig om methode te definiëren. Deze wordt in de activity geimplementeerd. Zo sturen we een callback van het fragment naar onze activity
    //~ we maken onze eigen onClickListener
    public interface DialogConfirmListener{
        void confirm(ArrayList<String> toppings);
    }
    private DialogConfirmListener dialogConfirmListener;
    //constructor: om koppeling te maken tussen dialoog en activity
    public void setDialogConfirmListener(DialogConfirmListener dialogConfirmListener) {
        this.dialogConfirmListener = dialogConfirmListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final String[] toppings = {"tomaat", "gehakt", "mozarella", "ajuin", "paprika", "hesp", "chorizo", "ananas", "maïs", "kip", "champignons"};
        boolean[] isChecked = new boolean[toppings.length];
        //array opvullen met items die aangevinkt zijn met BOOLEAN[]
        Arrays.fill(isChecked, false); //=> allemaal false, want alle checkboxes moeten leeg starten
        final ArrayList<String> gekozenToppings = new ArrayList<>(); //==lijst met gekozen toppings

        AlertDialog.Builder bob = new AlertDialog.Builder(getActivity());
        //getActivity toont waar ons dialogvenster moet inpassen

        bob.setTitle("Kies toppings");
        bob.setMultiChoiceItems(toppings, isChecked, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked){
                    gekozenToppings.add(toppings[which]); //index hebben we: which. Dus de topping zoeken met de index WHICH doen we hier. We voegen die toe aan de nieuwe lijst met gekozenToppings
                }else{
                    gekozenToppings.remove(toppings[which]); //als we perongelijk iets hebben aangevinkt, kunnen we die ook weer uitvinken en dan wordt die verwijderd uit de lijst met gekozenToppings.
                }
            }
        });
        //gekozen toppings opvangen:
        //TODO Listener aanvullen en keuzes bijhouden == OK

        bob.setNegativeButton("Cancel", null);
        bob.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogConfirmListener.confirm(gekozenToppings); //de positive button bevestigd de lijst met gekozenToppings en deze zal dan worden doorgestuurd in de activity.
            }
        });

        return bob.create();
    }
}
