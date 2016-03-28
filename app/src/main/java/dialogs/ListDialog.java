package dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by Ciego on 27/12/2014.
 */
public class ListDialog extends DialogFragment {

    public static ListDialog newInstance(String title){

        ListDialog dialog = new ListDialog();
        Bundle bundle = new Bundle();
        bundle.putString("TITLE", title);

        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //Con getArguments obtenemos el bundle de extras
        String title = getArguments().getString("TITLE");

        final String options[] = {"OK", "No hagas nada", "No se", "Cerrar"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String optionLabel = options[which];


            }
        });

        return builder.create();
    }
}



/*librariesfordevelopers*/
