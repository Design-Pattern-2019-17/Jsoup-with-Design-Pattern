package net.novabrn.jsoup.extend;

import net.novabrn.jsoup.extend.select.By;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.NoSuchElementException;

/**
 * @author 왕천용 WANGZHANYONG
 */
public class DocumentEx {
    private Document rootDoc;

    public DocumentEx(Document rootDoc) {
        this.rootDoc = rootDoc;
    }

    public Element selectFirst(By by) {
        Elements elements = select(by);
        if (elements == null || elements.isEmpty()) {
            throw new NoSuchElementException("Cannot locate an element using " + toString());
        }
        return elements.get(0);
    }

    /**
     * Find many elements.
     *
     * @return A list of WebElements matching the selector.
     */
    public Elements select(By by) {
        return by.findElements(this.rootDoc);
    }
}
