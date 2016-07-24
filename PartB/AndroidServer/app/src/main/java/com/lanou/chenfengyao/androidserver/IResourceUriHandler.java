package com.lanou.chenfengyao.androidserver;

import java.io.IOException;

/**
 * Created by hasee on 2016/7/24.
 */
public interface IResourceUriHandler {
    boolean accept(String uri);

    void handler(String uri, HttpContext httpContext) throws IOException;
}
