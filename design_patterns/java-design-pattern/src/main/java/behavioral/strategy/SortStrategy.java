package behavioral.strategy;

import java.util.List;

public interface SortStrategy {

    <T> void sort(List<T> items);
}
