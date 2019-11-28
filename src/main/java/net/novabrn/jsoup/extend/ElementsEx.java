package net.novabrn.jsoup.extend;

import net.novabrn.jsoup.extend.select.By;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.NoSuchElementException;

/**
 * @author 왕천용 WANGZHANYONG
 */
public class ElementsEx {
    private Elements rootElements;

    public ElementsEx(Elements rootElements) {
        this.rootElements = rootElements;
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
        return by.findElements(this.rootElements);
    }
}
