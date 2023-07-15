package com.example.chapter2.controller;

import com.example.chapter2.model.CurrencyEntity;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class FetchData {
    public static final DateTimeFormatter formatter =DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static ArrayList<CurrencyEntity> fetch_range (String src, int N){
        String dateEnd= LocalDate.now().format(formatter);
        String dateStart = LocalDate.now().minusDays(N).format(formatter);
        //representing the date N days ago.
        System.out.println("Start: "+dateStart+", "+"End: "+ dateEnd);
        String base ="THB" ;
        String url_str = String.format("https://api.exchangerate.host/timeseries?" +
                          "base=%s&symbols=%s&start_date=%s&end_date=%s",base,src,dateStart,dateEnd);
        System.out.println("Currency code (src): "+src);//USD,11,5
        ArrayList<CurrencyEntity> histList= new ArrayList<CurrencyEntity>();
        String retrievedJson = null;
        try {
           retrievedJson = IOUtils.toString(new URL(url_str), Charset.defaultCharset());
           // System.out.println(retrievedJson);
        }
        catch(MalformedInputException | MalformedURLException e){
            System.out.println("Encountered a Malformed url exception");
        }
        catch(IOException e){
            System.out.println("Encounter an IO exception");
        }


        JSONObject jsonOBJ = new JSONObject(retrievedJson).getJSONObject("rates");
        Iterator  keysToCopyIterator=jsonOBJ.keys();//rates

        while(keysToCopyIterator.hasNext()){
            String key = (String) keysToCopyIterator.next();
            if (!jsonOBJ.getJSONObject(key).isEmpty()){
                Double rate= 1.0/Double.parseDouble(jsonOBJ.getJSONObject(key).get(src).toString());
                histList.add(new CurrencyEntity(rate,key));
            }
        }
        histList.sort(new Comparator<CurrencyEntity>(){

            @Override
            public int compare(CurrencyEntity o1, CurrencyEntity o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        return histList;
    }



}
