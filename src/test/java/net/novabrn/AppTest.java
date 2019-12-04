package net.novabrn;

import net.novabrn.jsoup.extend.DocumentEx;
import net.novabrn.jsoup.extend.JsoupEx;
import net.novabrn.jsoup.extend.factory.NodeExFactory;
import net.novabrn.jsoup.extend.select.By;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author 왕천용 WANGZHANYONG
 */
public class AppTest {

    String url = "https://en.wikipedia.org";

    @Test
    public void baseTest() throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements newsHeadlines = doc.select("#mp-itn b a");
        assertNotNull(newsHeadlines.size() > 0);
        for (Element headline : newsHeadlines) {
            System.out.println(String.format("%s\n\t%s", headline.attr("title"), headline.absUrl("href")));
        }
    }

    @Test
    public void JsoupEx1() throws IOException {
        DocumentEx doc = JsoupEx.connect(url).setTimeout(1000).get();

        Elements els = doc.select(By.id("#mp-itn b a")).getRootElements();

        assertTrue(els.size() > 0);
        for (Element headline : els) {
            System.out.println(String.format("%s\n\t%s", headline.attr("title"), headline.absUrl("href")));
        }
    }

    @Test
    public void JsoupEx2() throws IOException {
        DocumentEx doc = JsoupEx.connect(url).setTimeout(1000).get();
        Elements els = doc
                .select(By.id("mp-itn"))
                .select(By.cssSelector("b a"))
                .getRootElements();
        assertTrue(els.size() > 0);
        for (Element headline : els) {
            System.out.println(String.format("%s\n\t%s", headline.attr("title"), headline.absUrl("href")));
        }
    }

    @Test
    public void JsoupEx3() throws IOException {
        DocumentEx doc = JsoupEx.connect(url).setTimeout(1000).get();

        Elements els = doc.select(By.id("mp-itn")).getRootElements();

        assertTrue(els.size() > 0);
        for (Element headline : els) {
            System.out.println(headline.html());
        }
    }

    @Test
    public void JsoupEx4() throws IOException {
        DocumentEx doc = JsoupEx.connect(url).setTimeout(1000).get();

        Elements els = doc.select(By.xpath("//*[@id='mp-itn']/ul/li/b/a")).getRootElements();

        assertTrue(els.size() > 0);
        for (Element headline : els) {
            System.out.println(String.format("%s\n\t%s", headline.attr("title"), headline.absUrl("href")));
        }
    }
}
