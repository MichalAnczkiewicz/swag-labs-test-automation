package com.saucedemo.core.utils;

import java.util.Iterator;
import java.util.List;

public class ListUtils {

    /**
     * Checks if provided Integer list is sorted in descending order
     *
     * @return true if list sorted in descending order
     * false if list is not sorted in descending order
     */
    public static Boolean isSortedDescending(List<Integer> integerList) {

        if (integerList.isEmpty() || integerList.size() == 1) {
            return true;
        }

        Iterator<Integer> iter = integerList.iterator();
        Integer current, previous = iter.next();
        while (iter.hasNext()) {
            current = iter.next();
            if (previous < current) {
                return false;
            }
            previous = current;
        }
        return true;
    }

    public static Boolean isSortedAscending(List<Integer> integerList) {

        if (integerList.isEmpty() || integerList.size() == 1) {
            return true;
        }

        Iterator<Integer> iter = integerList.iterator();
        Integer current, previous = iter.next();
        while (iter.hasNext()) {
            current = iter.next();
            if (previous > current) {
                return false;
            }
            previous = current;
        }
        return true;
    }
}
