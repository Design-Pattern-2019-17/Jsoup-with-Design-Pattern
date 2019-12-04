package net.novabrn.jsoup.extend;

import lombok.Data;
import net.novabrn.jsoup.extend.factory.NodeExFactory;
import net.novabrn.jsoup.extend.select.By;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.NoSuchElementException;

/**
 * @author 왕천용 WANGZHANYONG
 */
@Data
public class ElementsEx {
    private Elements rootElements;

    public ElementsEx(Elements rootElements) {
        this.rootElements = rootElements;
    }

    public ElementEx selectFirst(By by) {
        Elements elements = select(by).getRootElements();
        if (elements == null || elements.isEmpty()) {
            throw new NoSuchElementException("Cannot locate an element using " + toString());
        }
        return NodeExFactory.create(elements.get(0));
    }

    /**
     * Find many elements.
     *
     * @return A list of WebElements matching the selector.
     */
    public ElementsEx select(By by) {
        return by.findElements(this.rootElements);
    }
}
