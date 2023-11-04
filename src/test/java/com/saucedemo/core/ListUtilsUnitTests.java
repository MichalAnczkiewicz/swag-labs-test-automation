package com.saucedemo.core;

import com.saucedemo.core.utils.ListUtils;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.saucedemo.helpers.TestParametersFactory.CORE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag(CORE)
public class ListUtilsUnitTests {

    private static final String TEST_RESULT_MESSAGE_TRUE = "List is not sorted in correct order, but it should be.";
    private static final String TEST_RESULT_MESSAGE_FALSE = "List is sorted in correct order, but it shouldn't be.";

    @Test
    public void listInDescendingOrderShouldReturnTrueTest() {

        List<Integer> listInDescendingOrder = new ArrayList<>();
        Collections.addAll(listInDescendingOrder, 5, 4, 3);

        assertTrue(
                ListUtils.isSortedDescending(listInDescendingOrder),
                TEST_RESULT_MESSAGE_TRUE);
    }

    @Test
    public void listInAscendingOrderShouldReturnFalseTest() {

        List<Integer> listInAscendingOrder = new ArrayList<>();
        Collections.addAll(listInAscendingOrder, 3,4,5);

        assertFalse(
                ListUtils.isSortedDescending(listInAscendingOrder),
                TEST_RESULT_MESSAGE_FALSE);
    }

    @Test
    public void listWithOneElementShouldReturnTrueDescTest() {

        List<Integer> oneElementList = new ArrayList<>();
        oneElementList.add(5);

        assertTrue(
                ListUtils.isSortedDescending(oneElementList),
                TEST_RESULT_MESSAGE_TRUE);
    }

    @Test
    public void listInDescendingOrderWithRepeatedElementsShouldReturnTrueTest() {

        List<Integer> listInDescendingOrder = new ArrayList<>();
        Collections.addAll(listInDescendingOrder, 5, 4, 4, 3, 3, 2);

        assertTrue(
                ListUtils.isSortedDescending(listInDescendingOrder),
                TEST_RESULT_MESSAGE_TRUE);
    }

    @Test
    public void listInAscendingOrderShouldReturnTrueTest() {

        List<Integer> listInAscendingOrder = new ArrayList<>();
        Collections.addAll(listInAscendingOrder, 3, 4, 5);

        assertTrue(
                ListUtils.isSortedAscending(listInAscendingOrder),
                TEST_RESULT_MESSAGE_TRUE);
    }

    @Test
    public void listInDescendingOrderShouldReturnFalseTest() {

        List<Integer> listInDescendingOrder = new ArrayList<>();
        Collections.addAll(listInDescendingOrder, 5, 4, 3);

        assertFalse(
                ListUtils.isSortedAscending(listInDescendingOrder),
                TEST_RESULT_MESSAGE_FALSE);
    }

    @Test
    public void listWithOneElementShouldReturnTrueAscTest() {

        List<Integer> oneElementList = new ArrayList<>();
        oneElementList.add(5);

        assertTrue(
                ListUtils.isSortedAscending(oneElementList),
                TEST_RESULT_MESSAGE_TRUE);
    }

    @Test
    public void listInAscendingOrderWithRepeatedElementsShouldReturnTrueTest() {

        List<Integer> listInAscendingOrder = new ArrayList<>();
        Collections.addAll(listInAscendingOrder, 2, 3, 3, 4, 4, 5);

        assertTrue(
                ListUtils.isSortedAscending(listInAscendingOrder),
                TEST_RESULT_MESSAGE_TRUE);
    }
}
