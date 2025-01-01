package com.lcaohoanq.advanced.reflection;

import java.util.Comparator;
import java.util.List;

public class SortUtils {

    private SortUtils() {
    }

    /**
     * Sort a list of objects based on a single criterion
     *
     * @param list      List to sort
     * @param fieldName Field to sort by
     * @param order     Sort order
     * @param <T>       Type of objects in the list
     */
    public static <T> void sort(List<? extends T> list, String fieldName, SortOrder order,
                                Class<T> clazz) {
        sort(list, List.of(new SortCriterion(fieldName, order)), clazz);
    }

    /**
     * Sort a list of objects based on multiple criteria
     *
     * @param list     List to sort
     * @param criteria List of sorting criteria
     * @param <T>      Type of objects in the list
     */
    public static <T> void sort(List<? extends T> list, List<SortCriterion> criteria,
                                Class<T> clazz) {
        if (list == null || list.isEmpty() || criteria == null || criteria.isEmpty()) {
            return;
        }
        list.sort(createComparator(criteria, clazz));
    }

    private static <T> Comparator<T> createComparator(List<SortCriterion> sortCriteria,
                                                      Class<T> clazz) {
        return (o1, o2) -> {
            try {
                for (SortCriterion criterion : sortCriteria) {
                    var field = clazz.getDeclaredField(criterion.fieldName());
                    field.setAccessible(true);

                    var result = compareValues(
                        field.get(o1),
                        field.get(o2),
                        criterion.order()
                    );

                    if (result != 0) {
                        return result;
                    }
                }
                return 0;
            } catch (ReflectiveOperationException e) {
                throw new SortException("Error during sorting", e);
            }
        };
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> int compareValues(Object value1, Object value2,
                                                               SortOrder order) {
        // Handle null values
        if (value1 == null && value2 == null) {
            return 0;
        }
        if (value1 == null) {
            return order == SortOrder.ASC ? -1 : 1;
        }
        if (value2 == null) {
            return order == SortOrder.ASC ? 1 : -1;
        }

        // Type checking
        if (!value1.getClass().equals(value2.getClass())) {
            throw new SortException("Cannot compare values of different types: "
                                        + value1.getClass() + " and " + value2.getClass());
        }

        if (!(value1 instanceof Comparable)) {
            throw new SortException("Field type " + value1.getClass()
                                        + " does not implement Comparable");
        }

        // Perform comparison
        int comparison = ((Comparable) value1).compareTo(value2);
        return order == SortOrder.ASC ? comparison : -comparison;
    }
}

class SortException extends RuntimeException {

    public SortException(String message) {
        super(message);
    }

    public SortException(String message, Throwable cause) {
        super(message, cause);
    }
}

record SortCriterion(String fieldName, SortOrder order) {

}

enum SortOrder {
    ASC, DESC
}
