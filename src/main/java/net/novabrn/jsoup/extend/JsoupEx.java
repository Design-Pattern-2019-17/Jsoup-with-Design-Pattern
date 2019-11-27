package net.novabrn.jsoup.extend;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 왕천용 WANGZHANYONG
 */
public class JsoupEx {

    private int timeOut = 0;

    private Connection conn;

    private Document doc;

    private JsoupEx(Connection conn) {
        this.conn = conn;
    }

    public static JsoupEx connect(String url) {
        return new JsoupEx(Jsoup.connect(url));
    }

    public JsoupEx setTimeout(int time) {
        this.timeOut = time;
        return this;
    }

    public DocumentEx get() throws IOException {
        doc = conn.timeout(timeOut).get();
        DocumentEx documentEx = new DocumentEx(doc);
        return documentEx;
}

    public DocumentEx post() throws IOException {
        doc = conn.timeout(timeOut).post();
        DocumentEx documentEx = new DocumentEx(doc);
        return documentEx;
    }
}

