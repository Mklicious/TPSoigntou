package btsio.com.sointoumap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import btsio.com.sointoumap.adapters.ListPharmacieAdapter;
import btsio.com.sointoumap.objects.Pharmacie;

public class ListePharmacie extends Activity {

    public static final int MSG_ERR = 0;
    public static final int MSG_CNF = 1;
    public static final int MSG_IND = 2;

    Context context;
    ListView lv;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_pharmacie);

        context = this;

        lv = (ListView) findViewById(R.id.lv_pharmacies);

        mProgressDialog = ProgressDialog.show(this, "Veuillez patienter",
                "Initialisation...", true);

        // Thread pour la recuperation des pharmacies
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = null;
                String progressBarData = "Récupération des pharmacies...";
                // Positionnement du message
                msg = handler.obtainMessage(MSG_IND, (Object) progressBarData);
                // Send message to the handler
                handler.sendMessage(msg);
                // Get json data from server
                // * TO DO
                // Fake data since to do
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Pharmacie selectedPharmacie = Repository.getPharmacies().get(position);
                        openGoogleMap(selectedPharmacie);
                    }
                });

                msg = handler.obtainMessage(MSG_CNF,"Récupération terminée !");
                handler.sendMessage(msg);
            }
        }).start();
    }

    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            String text = null;
            switch (msg.what){
                case MSG_IND:
                    if (mProgressDialog.isShowing()){
                        mProgressDialog.setMessage(((String) msg.obj));
                    }
                    break;

                case MSG_ERR:
                    text = (String) msg.obj;
                    Toast.makeText(context, "Erreur: " + text, Toast.LENGTH_LONG).show();
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    break;

                case MSG_CNF:
                    text = (String) msg.obj;
                    Toast.makeText(context, "" + text,Toast.LENGTH_LONG).show();
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }

                    // Open list view
                    openListView();

                    mProgressDialog.dismiss();

            }
        }
    };

    public void openListView(){
        lv.setAdapter(new ListPharmacieAdapter(this, R.layout.row, Repository.getPharmacies()));
    }

    public void openGoogleMap(Pharmacie pharmacie){
        Intent intent = new Intent(context, GoogleMapPharmacie.class);
        intent.putExtra("lat", pharmacie.getLocation().getLatitude());
        intent.putExtra("long", pharmacie.getLocation().getLongitude());
        startActivity(intent);
    }
}
