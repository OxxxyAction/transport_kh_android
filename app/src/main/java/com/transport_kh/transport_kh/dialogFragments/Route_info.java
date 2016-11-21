package com.transport_kh.transport_kh.dialogFragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.transport_kh.transport_kh.R;
import com.transport_kh.transport_kh.activities.MapsActivity;
import com.transport_kh.transport_kh.models.ListItemRoute;

import java.util.Calendar;

/**
 * Created by Dmytro on 21.11.2016.
 */
public class Route_info extends DialogFragment {
    ListItemRoute info;

    public Route_info() {
    }

    public void init ( ListItemRoute info){
        this.info = info;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_route_info, container);
        TextView number = (TextView) view.findViewById(R.id.txtView_df_number);
        TextView cost = (TextView) view.findViewById(R.id.txtView_df_cost);
        TextView startpoint = (TextView) view.findViewById(R.id.txtView_df_start_point);
        TextView endpoint = (TextView) view.findViewById(R.id.txtView_df_end_point);
        TextView count = (TextView) view.findViewById(R.id.txtView_df_count);

        if(info!=null){
            number.setText( info.getNumber() );
            cost.setText( info.getCost() );
            startpoint.setText( info.getStartStation() );
            endpoint.setText( info.getEndStation() );
            count.setText( "Количество остановок:\n" + Integer.toString(info.getCount()));
        }

        view.findViewById(R.id.txtView_df_show_route).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                startActivity(new Intent( getActivity(), MapsActivity.class));
            }
        });
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
}