package btsio.com.sointoumap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SaisieActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saisie);

        // When click
        Button btn = (Button) findViewById(R.id.btn_voir_liste);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openListePhamarcie();
            }
        });
    }

    public void openListePhamarcie(){
        Intent intent = new Intent(this, ListePharmacie.class);
        startActivity(intent);
    }
}
