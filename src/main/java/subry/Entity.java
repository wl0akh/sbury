package sbury;

import org.jsoup.nodes.Document;

public interface Entity {
    abstract String getData(Document doc);
}