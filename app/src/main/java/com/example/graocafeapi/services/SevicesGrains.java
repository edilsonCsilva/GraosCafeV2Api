package com.example.graocafeapi.services;

import com.example.graocafeapi.model.TiposGraosCafe;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Callback;

public class SevicesGrains {
    public List<TiposGraosCafe> getAll() {
        List<TiposGraosCafe> grains = new ArrayList<>();
        grains.add(new TiposGraosCafe("Café Arábica"));
        grains.add(new TiposGraosCafe("Café Bourbon"));
        grains.add(new TiposGraosCafe("Café Kona"));
        grains.add(new TiposGraosCafe("Café Catuaí"));
        grains.add(new TiposGraosCafe("Café Acaiá"));
        grains.add(new TiposGraosCafe("Café Robusta"));
        grains.add(new TiposGraosCafe("Café Geisha"));
        return grains;
    }



    public List<TiposGraosCafe> getAllApi() {

        List<TiposGraosCafe> grains = new ArrayList<>();
        grains.add(new TiposGraosCafe("Café Arábica"));
        grains.add(new TiposGraosCafe("Café Bourbon"));
        grains.add(new TiposGraosCafe("Café Kona"));
        grains.add(new TiposGraosCafe("Café Catuaí"));
        grains.add(new TiposGraosCafe("Café Acaiá"));
        grains.add(new TiposGraosCafe("Café Robusta"));
        grains.add(new TiposGraosCafe("Café Geisha"));
        return grains;
    }




}
