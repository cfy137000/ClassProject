package com.lanou.chenfengyao.androidserver;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hasee on 2016/7/24.
 */
public class StreamToolkit {
    public static final String readLine(InputStream nis){
        StringBuilder sb = new StringBuilder();
        int c1 = 0;
        int c2 = 0;
        while (c2 != -1 && !(c1 == '\r' && c2 =='\n')){
            c1 = c2;
            try {
                c2 = nis.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            sb.append((char)c2);
        }
        String result = sb.toString();
        result.trim();
        if(result.length() == 0){
            return null;
        }
        return result.toString();
    }
}
