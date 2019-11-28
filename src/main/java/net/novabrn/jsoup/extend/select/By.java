package net.novabrn.jsoup.extend.select;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.seimicrawler.xpath.JXDocument;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @param <T> the type of args! it can be Elements Element Document
 * @author 왕천용 WANGZHANYONG
 */
public abstract class By<T> {
    public static By id(String id) {
        return new ById(id);
    }

    public static By tagName(String tagName) {
        return new ByTagName(tagName);
    }

    public static By cssSelector(String cssSelector) {
        return new ByCssSelector(cssSelector);
    }

    public static By xpath(String xpath) {
        return new ByXPath(xpath);
    }

    public Element findElement(T node) {
        Elements elements = findElements(node);
        if (elements == null || elements.isEmpty()) {
            throw new NoSuchElementException("Cannot locate an element using " + toString());
        }
        return elements.get(0);
    }

    /**
     * Find many elements.
     *
     * @return A list of elements matching the express.
     */
    public abstract Elements findElements(T node);

    private static class ById extends By {
        private String id;

        ById(String id) {
            this.id = id;
        }

        @Override
        public Elements findElements(Object node) {
            Elements elements = new Elements();
            if (node instanceof Elements) {
                throw new IllegalArgumentException("Type Element can't use this method");
            }

            if (node instanceof Document) {
                elements.add(((Document) node).getElementById(id));
            } else if (node instanceof Element) {
                elements.add(((Element) node).getElementById(id));
            }
            return elements;
        }
    }

    private static class ByTagName extends By {
        private String tagName;

        ByTagName(String tagName) {
            this.tagName = tagName;
        }

        @Override
        public Elements findElements(Object node) {
            Elements elements = new Elements();
            if (node instanceof Elements) {
                throw new IllegalArgumentException("Type Element can't use this method");
            }

            if (node instanceof Document) {
                elements = ((Document) node).getElementsByTag(this.tagName);
            } else if (node instanceof Element) {
                elements = ((Element) node).getElementsByTag(this.tagName);
            }
            return elements;
        }
    }

    private static class ByCssSelector extends By {
        private String cssSelector;

        ByCssSelector(String cssSelector) {
            this.cssSelector = cssSelector;
        }

        @Override
        public Elements findElements(Object node) {
            Elements elements = new Elements();
            if (node instanceof Document) {
                elements = ((Document) node).select(this.cssSelector);
            } else if (node instanceof Element) {
                elements = ((Element) node).select(this.cssSelector);
            }else if (node instanceof Elements) {
                elements = ((Elements) node).select(this.cssSelector);
            }
            return elements;
        }
    }

    private static class ByXPath extends By {
        private String xpath;

        public ByXPath(String xpath) {
            this.xpath = xpath;
        }

        @Override
        public Elements findElements(Object node) {
            JXDocument jxDocument = null;
            if (node instanceof Document) {
                jxDocument = JXDocument.create((Document) node);
            } else if (node instanceof Elements) {
                jxDocument = JXDocument.create((Elements) node);
            } else if (node instanceof Element) {
                Elements temp = new Elements();
                temp.add((Element) node);
                jxDocument = JXDocument.create(temp);
            }

            Elements result = new Elements();
            List<Object> temResult = jxDocument.sel(this.xpath);
            temResult.stream()
                    .filter(item -> item instanceof Element)
                    .map(item -> (Element) item)
                    .forEach(result::add);
            return result;
        }
    }
}
