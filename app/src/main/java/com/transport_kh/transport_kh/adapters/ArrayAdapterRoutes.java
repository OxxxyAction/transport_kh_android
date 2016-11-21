package com.transport_kh.transport_kh.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.transport_kh.transport_kh.R;
import com.transport_kh.transport_kh.models.ListItemRoute;

import java.util.List;

/**
 * Created by Dmytro on 20.11.2016.
 */
public class ArrayAdapterRoutes extends ArrayAdapter<ListItemRoute> {

    public ArrayAdapterRoutes(Context context, int resource, List<ListItemRoute> objects) {
        super(context, resource, objects);
    }
    static class ViewHolder{
        TextView number;
        TextView startStation;
        TextView endStation;
        TextView cost;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if( convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_lv_route, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.number = (TextView) convertView.findViewById(R.id.txtView_item_route_number);
            viewHolder.startStation = (TextView) convertView.findViewById(R.id.txtView_item_route_start);
            viewHolder.endStation = (TextView) convertView.findViewById(R.id.txtView_item_route_end);
            viewHolder.cost = (TextView) convertView.findViewById(R.id.txtView_item_route_cost);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.number.setText(getItem(position).getNumber());
        viewHolder.startStation.setText("От: "+getItem(position).getStartStation());
        viewHolder.endStation.setText("До: "+getItem(position).getEndStation());
        viewHolder.cost.setText("Стоимость: "+getItem(position).getCost());

        return convertView;
    }
}
