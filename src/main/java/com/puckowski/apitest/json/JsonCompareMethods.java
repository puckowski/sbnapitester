package com.puckowski.apitest.json;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.skyscreamer.jsonassert.JSONCompareMode;

public class JsonCompareMethods
{
    private final String[] JSON_COMPARE_MODE_ARRAY = {
        "NON_EXTENSIBLE",
        "STRICT",
        "LENIENT",
        "STRICT_ORDER"
    };

    private Map<String, JSONCompareMode> mCompareModeMap;
    private Map<String, String> mCompareModeDetailsMap;

    public JsonCompareMethods() {
        initCompareModeMap();
        initCompareModeDetailsMap();
    }

    private void initCompareModeMap() {
        mCompareModeMap = new HashMap<String, JSONCompareMode>();

        mCompareModeMap.put("NON_EXTENSIBLE", JSONCompareMode.NON_EXTENSIBLE);
        mCompareModeMap.put("STRICT", JSONCompareMode.STRICT);
        mCompareModeMap.put("LENIENT", JSONCompareMode.LENIENT);
        mCompareModeMap.put("STRICT_ORDER", JSONCompareMode.STRICT_ORDER);

        mCompareModeMap = Collections.unmodifiableMap(mCompareModeMap);
    }

    private void initCompareModeDetailsMap() {
        mCompareModeDetailsMap = new HashMap<String, String>();

        mCompareModeDetailsMap.put("NON_EXTENSIBLE", "Not extensible and no strict ordering.");
        mCompareModeDetailsMap.put("STRICT", "Not extensible and strict ordering.");
        mCompareModeDetailsMap.put("LENIENT", "Extensible and no strict ordering.");
        mCompareModeDetailsMap.put("STRICT_ORDER", "Extensible and strict ordering.");

        mCompareModeDetailsMap = Collections.unmodifiableMap(mCompareModeDetailsMap);
    }

    public String[] getCompareMethods() {
        return JSON_COMPARE_MODE_ARRAY;
    }

    public JSONCompareMode getCompareMode(final String compareModeString) {
        return mCompareModeMap.get(compareModeString);
    }

    public String getCompareModeDetails(final String compareModeString) {
        return mCompareModeDetailsMap.get(compareModeString);
    }
}