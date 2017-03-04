package com.akexorcist.deviceinformation.utility;

import com.akexorcist.deviceinformation.collector.InfoResultType;

import java.util.Locale;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class StringUtility {
    private static StringUtility utility;

    public static StringUtility getInstance() {
        if (utility == null) {
            utility = new StringUtility();
        }
        return utility;
    }

    public String wrapBlank(String str) {
        if (str == null || str.toLowerCase(Locale.getDefault()).equals("null"))
            str = "";
        return str;
    }

    public String wrapUnknown(String str) {
        if (str == null || str.toLowerCase(Locale.getDefault()).equals("null") || str.equals(""))
            str = InfoResultType.UNKNOWN;
        return str;
    }

    public String wrapUnknownWithLowerCase(String str) {
        if (str == null || str.toLowerCase(Locale.getDefault()).equals("null") || str.equals(""))
            str = InfoResultType.UNKNOWN.toLowerCase();
        return str;
    }

    public String wrapUrl(String str) {
        if (str != null)
            return str.replace("/", "_").replace(":", "_").replace(".", "_").replace(" ", "_");
        return str;
    }

    public String removeUnknown(String str) {
        return str.replace(InfoResultType.UNKNOWN.toLowerCase(), "").replace(InfoResultType.UNKNOWN, "");
    }
}
