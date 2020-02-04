package com.arobs.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static public List<String> names= new ArrayList<String>();
    static public Map<String,String> namesMap=new HashMap<String, String>();

    public static void main(String[] args) {
        names.add("Ana");
        names.add("Dan");
        names.add("Kate");
        names.add("Jess");
        names.add("Tessa");
        namesMap.put("fName","Sonia");
    }
}
