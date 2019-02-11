package sbury;

import java.util.Map;

@FunctionalInterface
public interface Filter {
    abstract boolean validate(Map element);
}