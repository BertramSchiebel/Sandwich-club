package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String TAG = JsonUtils.class.getSimpleName();

    public static Sandwich parseSandwichJson(String sandwichJsonStr)
    {
        final String NAME = "name";
        final String MAINNAME = "mainName";
        final String ALSO_KNOWN_AS="alsoKnownAs";
        final String PLACE_OF_ORIGIN="placeOfOrigin";
        final String DESCRIPTON = "description";
        final String IMAGE ="image";
        final String INGREDIENTS ="ingredients";

        Sandwich parsedSandwich = new Sandwich();
        try
        {
            JSONObject sandwichJSON = new JSONObject(sandwichJsonStr);

            JSONObject sandwichNamesJSON = sandwichJSON.getJSONObject(NAME);
            parsedSandwich.setMainName(sandwichNamesJSON.getString(MAINNAME));

            JSONArray akaNameArray = sandwichNamesJSON.getJSONArray(ALSO_KNOWN_AS);
            ArrayList<String> akaNames = new ArrayList<>();
            for (int i = 0; i < akaNameArray.length(); i++)
            {
                String akaName=akaNameArray.getString(i);
                if (!akaName.isEmpty())
                {
                    akaNames.add(akaName);
                }
            }
            parsedSandwich.setAlsoKnownAs(akaNames);

            parsedSandwich.setPlaceOfOrigin(sandwichJSON.getString(PLACE_OF_ORIGIN));
            parsedSandwich.setDescription(sandwichJSON.getString(DESCRIPTON));
            parsedSandwich.setImage(sandwichJSON.getString(IMAGE));
            JSONArray ingredientsArray = sandwichJSON.getJSONArray(INGREDIENTS);
            ArrayList<String> ingredients = new ArrayList<>();
            for (int i = 0; i < ingredientsArray.length(); i++)
            {
                String igredient=ingredientsArray.getString(i);
                if (!igredient.isEmpty())
                {
                    ingredients.add(igredient);
                }
            }
            parsedSandwich.setIngredients(ingredients);

        }
        catch (JSONException e)
        {
            Log.e(TAG, "error parse JSON String");
            e.printStackTrace();
            return null;
        }

        return parsedSandwich;
    }
}
