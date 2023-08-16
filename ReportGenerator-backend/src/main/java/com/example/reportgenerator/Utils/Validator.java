package com.example.reportgenerator.Utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

/**
 * Utility class for JSON validation using Gson library.
 */
public class Validator {

    /**
     * Validate if the input string is a valid JSON.
     *
     * @param inputJson The input JSON string to be validated.
     * @return true if the input is a valid JSON, false otherwise.
     */
    public boolean validateJson(String inputJson) {
        try {
            JsonElement json = new Gson().getAdapter(JsonElement.class).fromJson(inputJson);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
