package com.akexorcist.deviceinformation.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class AndroidUtility {
    private static AndroidUtility utility;

    public static AndroidUtility getInstance() {
        if (utility == null) {
            utility = new AndroidUtility();
        }
        return utility;
    }

    public String getStringFromInputStream(InputStream is) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
