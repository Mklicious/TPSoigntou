package btsio.com.sointoumap.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import btsio.com.sointoumap.R;
import btsio.com.sointoumap.objects.Pharmacie;

/**
 * Created by dzak on 05/04/16.
 */
public class ListPharmacieAdapter extends ArrayAdapter<Pharmacie> {

    private Activity activity;

    private Pharmacie currentPharmacie;
    private List<Pharmacie> items;

    private int row;

    public ListPharmacieAdapter(Activity context, int resource, ArrayList<Pharmacie> m_pharmacies) {
        super(context, resource, m_pharmacies);
        activity = context;
        this.items = m_pharmacies;
        this.row = resource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(row, null);

            holder = new ViewHolder();
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if ((items == null) || ((position + 1) > items.size()))
            return view;

        currentPharmacie = items.get(position);

        holder.nom = (TextView) view.findViewById(R.id.nom);
        holder.distance = (TextView) view.findViewById(R.id.distance);

        // set view data displayed
        holder.nom.setText(currentPharmacie.getNom());
        holder.distance.setText("à " + currentPharmacie.getDistanceString() + "(km)");

        return view;
    }

    public class ViewHolder {
        public TextView nom, distance;
    }
}
