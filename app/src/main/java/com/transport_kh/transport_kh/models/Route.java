package com.transport_kh.transport_kh.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

/**
 * Created by Dmytro on 20.11.2016.
 */
public class Route {
    private String routes_name;
    private String routes_transport_type;
    private String routes_way;
    private String routes_direction;
    private ArrayList<String> routes_way_forward;
    private ArrayList<String> routes_way_back;

    public Route(JsonObject jObj) {
        this.routes_name = jObj.get("routes_name").toString();
        this.routes_transport_type = jObj.get("routes_transport_type").toString();
        this.routes_way = jObj.get("routes_way").toString();
        this.routes_direction = jObj.get("routes_direction").toString();
        JsonArray routes_way_forward = jObj.getAsJsonArray("routes_way_forward");
        this.routes_way_forward = new ArrayList<>();
        for (JsonElement jsonElement : routes_way_forward) {
            this.routes_way_forward.add( jsonElement.getAsString() );
        }
        JsonArray routes_way_back = jObj.getAsJsonArray("routes_way_back");
        this.routes_way_back = new ArrayList<>();
        for (JsonElement jsonElement : routes_way_back) {
            this.routes_way_back.add( jsonElement.getAsString() );
        }
    }

    public String getRoutes_name() {
        return routes_name;
    }

    public void setRoutes_name(String routes_name) {
        this.routes_name = routes_name;
    }

    public String getRoutes_transport_type() {
        return routes_transport_type;
    }

    public void setRoutes_transport_type(String routes_transport_type) {
        this.routes_transport_type = routes_transport_type;
    }

    public String getRoutes_way() {
        return routes_way;
    }

    public void setRoutes_way(String routes_way) {
        this.routes_way = routes_way;
    }

    public String getRoutes_direction() {
        return routes_direction;
    }

    public void setRoutes_direction(String routes_direction) {
        this.routes_direction = routes_direction;
    }

    public ArrayList<String> getRoutes_way_forward() {
        return routes_way_forward;
    }

    public void setRoutes_way_forward(ArrayList<String> routes_way_forward) {
        this.routes_way_forward = routes_way_forward;
    }

    public ArrayList<String> getRoutes_way_back() {
        return routes_way_back;
    }

    public void setRoutes_way_back(ArrayList<String> routes_way_back) {
        this.routes_way_back = routes_way_back;
    }

    @Override
    public String toString() {
        return "Route{" +
                "routes_direction='" + routes_direction + '\'' +
                ", routes_way='" + routes_way + '\'' +
                ", routes_transport_type='" + routes_transport_type + '\'' +
                ", routes_name='" + routes_name + '\'' +
                '}';
    }
}
