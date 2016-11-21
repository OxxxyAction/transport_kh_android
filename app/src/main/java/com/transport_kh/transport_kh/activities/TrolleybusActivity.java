package com.transport_kh.transport_kh.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.transport_kh.transport_kh.R;
import com.transport_kh.transport_kh.adapters.ArrayAdapterRoutes;
import com.transport_kh.transport_kh.dialogFragments.Route_info;
import com.transport_kh.transport_kh.models.ListItemRoute;
import com.transport_kh.transport_kh.models.Route;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * Created by Dmytro on 20.11.2016.
 */
public class TrolleybusActivity extends Activity {
    ListView lvTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trolleybus_activity);

        final String[] listOfRoutes = new String[]{"Маршрут №6"};

        lvTable = (ListView) findViewById(R.id.listview_trolleybus);

        lvTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Route_info info_temp = new Route_info();
                ListItemRoute tempoItem = (ListItemRoute) adapterView.getItemAtPosition(i);
                info_temp.init( tempoItem );
                info_temp.show(getFragmentManager(), "");
                /*Intent intent = new Intent( TrolleybusActivity.this, RouteActivity.class );
                intent.putExtra("type", "trolleybus");
                String temp = listOfRoutes[i].substring( listOfRoutes[i].indexOf("№")+1);
                intent.putExtra("number",   temp);
                Log.e("test", "number: " + temp);
                startActivity( intent );*/
            }
        });

        /*ListItemRoute temp = new ListItemRoute();
        temp.setNumber("#6");
        temp.setStartStation("Площадь свободы");
        temp.setEndStation("проспект Гагарина");
        temp.setCost("5 грн");*/
        ArrayList<ListItemRoute> tempArr = new ArrayList<>();
        /*tempArr.add(temp);
        tempArr.add(temp);
        tempArr.add(temp);*/
        ArrayList<Route> temp_arr = fromJsonToObject(loadJSONFromAssetArrayList());
        for (Route route : temp_arr) {
            Log.e("test", route.toString());
            if(route.getRoutes_transport_type().contains("trolleybus")){

                ListItemRoute temp = new ListItemRoute();
                temp.setNumber("№" + route.getRoutes_name());
                temp.setStartStation(route.getRoutes_way_forward().get(0));
                temp.setEndStation(route.getRoutes_way_back().get(0));
                temp.setCost("3 грн");
                temp.setCount(route.getRoutes_way_forward().size());
                tempArr.add(temp);
            }
        }
        Log.e("test",tempArr.size()+"");
        lvTable.setAdapter(new ArrayAdapterRoutes( TrolleybusActivity.this,R.layout.item_lv_route, tempArr));


    }

    public String loadJSONFromAssetArrayList() {
        String json = null;
            try {
                //InputStream is = getAssets().open(type + "_" + number + "_" + Integer.toString(i) + ".json");
                InputStream is = getAssets().open("dani_mist.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }

        return json;
    }
    private ArrayList<Route> fromJsonToObject(String json){
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.setLenient(true);
        ArrayList<Route> result = new ArrayList<>();
        JsonArray arr = parser.parse(json).getAsJsonArray();
        for (JsonElement jsonElement : arr) {
            JsonObject jObj = jsonElement.getAsJsonObject();
            result.add( new Route( jObj ) );

        }


       /* for(int j = 0; j < arr.size(); j++){
            String keyFinal="";
            Set<Map.Entry<String, JsonElement>> entries = arr.get(j).getAsJsonObject().entrySet();
            JsonArray arrTempTimeJson = null;
            for (Map.Entry<String, JsonElement> entry : entries) {
                keyFinal = entry.getKey();
                arrTempTimeJson = entry.getValue().getAsJsonArray();
            }
            ArrayList<Float> arrTimeTemp = new ArrayList<>();
            if(arrTempTimeJson!=null)
                for (JsonElement element : arrTempTimeJson) {
                    String tempTime = element.getAsString().replace(",",".");
                    //TODO
                    if(tempTime.contains("/"))
                        tempTime = tempTime.substring( tempTime.indexOf("/")+1 );
                    float valueTempTime = Float.parseFloat(tempTime);
                    // Log.e("test", "string: " + tempTime + " float: " + valueTempTime);
                    if(valueTempTime!=0) {
                        arrTimeTemp.add(valueTempTime);
                    }
                }
            //Log.e("test", "keyTest: " + keyFinal);
            //Log.e("test", "values:" + arrTimeTemp.toString());

            StationList tempStat = new StationList(keyFinal, arrTimeTemp);
            result.add(tempStat);
        }*/
        return result;
    }
}
