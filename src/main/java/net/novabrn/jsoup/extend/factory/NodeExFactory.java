package net.novabrn.jsoup.extend.factory;

import net.novabrn.jsoup.extend.DocumentEx;
import net.novabrn.jsoup.extend.ElementEx;
import net.novabrn.jsoup.extend.ElementsEx;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created with IntelliJ IDEA
 * User: wangyong
 * Date: 11/28/19
 * Time: 2:21 PM
 * Description:
 */
public class NodeExFactory {
    public static final DocumentEx create(Document document){
        return  new DocumentEx(document);
    }

    public static final ElementEx create(Element element){
        return  new ElementEx(element);
    }

    public static final ElementsEx create(Elements elements){
        return  new ElementsEx(elements);
    }
}
