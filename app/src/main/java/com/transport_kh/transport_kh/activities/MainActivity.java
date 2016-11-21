package com.transport_kh.transport_kh.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.transport_kh.transport_kh.R;

/**
 * Created by Dmytro on 20.11.2016.
 */
public class MainActivity extends AppCompatActivity {

    ListView lvTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);

        final String[] listOfRoutes = new String[]{"Маршрут №6"};



        /*lvTable = (ListView) findViewById(R.id.listView_table);
        lvTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent( MainActivity.this, RouteActivity.class );
                intent.putExtra("type", "trolleybus");
                String temp = listOfRoutes[i].substring( listOfRoutes[i].indexOf("№")+1);
                intent.putExtra("number",   temp);
                Log.e("test", "number: " + temp);
                startActivity( intent );
            }
        });*/


        /*findViewById(R.id.btn_trolleybus_table).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lvTable.setAdapter(new ArrayAdapter<>( MainActivity.this, android.R.layout.simple_dropdown_item_1line, listOfRoutes));
            }
        });*/
    }

}