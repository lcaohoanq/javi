package behavioral.strategy;

public class Demo {

    public static void main(String[] args) {

        SortedList sortedList = new SortedList();
        sortedList.add("Java Core");
        sortedList.add("Java Design Pattern");
        sortedList.add("Java Library");
        sortedList.add("Java Framework");

        sortedList.setSortStrategy(new QuickSort());
        sortedList.sort();

        sortedList.setSortStrategy(new MergeSort());
        sortedList.sort();
    }

}
