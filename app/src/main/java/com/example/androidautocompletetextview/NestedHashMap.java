package com.example.androidautocompletetextview;

import java.util.*;
import java.io.*;

public class NestedHashMap
{
    private HashMap<String, HashMap> outerMap;
    private static HashMap<String, Integer> innerMap;
    private static BufferedReader input;
    private static String[] tokens;

    public NestedHashMap()
    {
        outerMap = new HashMap<String, HashMap>();
    }

    public void getSource() throws IOException
    {

        try {
            input = new BufferedReader(new FileReader("/Users/Tom/Desktop/H.Dip Software Development & Design/CT874 Programming I/My Java Programs/Test.txt"));

        } catch (Exception e) {
            System.out.println("fileread prob");
        }

        String line = null;
        StringBuilder sInput = new StringBuilder();
        while ((line = input.readLine()) != null) {
            sInput.append(line);
        }
        String output = sInput.toString();
        //Get rid of all non-alpha characters (except spaces)
        String alphaString = output.replaceAll("[^a-zA-Z ]", "");

        //Split the text into words
        tokens = alphaString.split(" ");

    }

    public void createWordPairs()
    {
        for (int i = 0; i <= tokens.length - 3; i++)
        {
            //create wordpairs
            String wordPair = tokens[i] + tokens[i + 1];
            //create thirdWord
            String thirdWord = tokens[i + 2];
            addElement(wordPair, thirdWord);
        }
    }

    public void addElement(String key1, String key2)
    {
        HashMap innerMap = outerMap.get(key1);
        if (innerMap == null) {
            innerMap = new HashMap<String, Integer>();
            innerMap.put(key2, 1);
            outerMap.put(key1, innerMap);
            //innerMap.put(key2,1);
        }
        else if (innerMap.containsKey(key2)) {

            int temp = (Integer) innerMap.get(key2);
            temp++;
            innerMap.put(key2, temp);
        }
        else innerMap.put(key2, 1);
    }

    public Object getElement(String key1, String key2)
    {
        HashMap innerMap = outerMap.get(key1);
        if (innerMap == null) {
            return null;
        }
        return innerMap.get(key2);
    }

    public void displayKeys()
    {

        System.out.println("\nFor-each method");
        for (Object key : outerMap.keySet()) {
            System.out.println("\t" + key);
            HashMap<String, Integer> innerMap = (HashMap<String, Integer>) outerMap.get(key);

            for (Object innerkey : innerMap.keySet())
            {
                System.out.println("\t\tinner loop value " + innerkey + " ," + innerMap.get(innerkey));
            }
        }
    }


    public void outerMapSize()
    {
        System.out.println("OuterMap is: " + outerMap.size());
    }

    public String maxValue(String wordKey, int order)
    {


        HashMap<String, Integer> innerMap = (HashMap<String, Integer>) outerMap.get(wordKey);
        List<Integer> list = new ArrayList<Integer>(innerMap.values());
        Collections.sort(list, Collections.reverseOrder());
        List<Integer> top3 = list.subList(0, 3);
        String result = null;
        if (order == 1) {
            result = list.get(0).toString();
        }
        if (order == 2) {
            result = list.get(1).toString();
        }
        return result;
    }
}
