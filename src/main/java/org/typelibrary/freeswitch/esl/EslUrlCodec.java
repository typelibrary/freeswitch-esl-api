package org.typelibrary.freeswitch.esl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public final class EslUrlCodec {

    public static final String encode(String string) {
        try {
            return URLEncoder.encode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // Should never happen since UTF-8 is part of JRE
            throw new IllegalStateException("UTF-8 charset not found!", e);
        }
    }

    public static final String decode(String string) {
        try {
            return URLDecoder.decode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // Should never happen since UTF-8 is part of JRE
            throw new IllegalStateException("UTF-8 charset not found!", e);
        }
    }

}
