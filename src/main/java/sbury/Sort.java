package sbury;

import java.util.Map;
import java.util.Comparator;

public interface Sort<Map> extends Comparator<Map> {
    abstract int compare(Map p1, Map p2);
}