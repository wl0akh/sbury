package sbury;

import java.util.Map;
import java.util.function.Predicate;

@FunctionalInterface
public interface Filter<Map> extends Predicate<Map> {
    abstract boolean test(Map element);
}