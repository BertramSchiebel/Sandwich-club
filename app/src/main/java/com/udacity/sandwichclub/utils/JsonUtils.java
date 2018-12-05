package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    private static final String TAG = JsonUtils.class.getSimpleName();

    public static Sandwich parseSandwichJson(String sandwichJsonStr)
    {
        final String OMW_NAME = "name";
        final String OMW_MAINNAME = "mainName";
        final String OMW_DESCRIPTON = "description";
        final String OMW_IMAGE ="image";

        Sandwich parsedSandwich = new Sandwich();
        try
        {
            JSONObject sandwichJSON = new JSONObject(sandwichJsonStr);
            JSONObject sandwichNamesJSON = sandwichJSON.getJSONObject(OMW_NAME);

            parsedSandwich.setMainName(sandwichNamesJSON.getString(OMW_MAINNAME));

            parsedSandwich.setDescription(sandwichJSON.getString(OMW_DESCRIPTON));
            parsedSandwich.setImage(sandwichJSON.getString(OMW_IMAGE));


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
