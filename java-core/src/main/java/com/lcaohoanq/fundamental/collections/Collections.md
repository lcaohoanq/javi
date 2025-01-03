```txt
					Iterable
					
					
					Collection


	     List		  Queue                                   Set                             |                         Map
 	   /   |   \               \                                                                      |            
	  /    |    \	            \                                                                     |
	 /     |     \		     \	Deque				             Sorted               |                                     SortedMap
        /      |      \               \                                                                   |      
       /       |       \	/      \                                                                  | 
ArrayList    Vector    LinkedList      PriorityQueue	ArrayDeque   HashSet   LinkedHashSet    TreeSet   | HashMap     LinkedHashMap   HashTalbe   TreeMap
```

*NOTE*
- Có 4 nhóm chính List, Queue, Set, Map
- Cụm từ "Tree" ám chỉ rằng bộ Collection đó SORTED
- Khi ta khai cha new cha, thì phải implements hết tất cả các method của cha
  (Ví dụ List -> new List... có rất nhiều method ta cần phải implement, 
  không nên xài như vậy, hãy khai cha new con hoặc khai con new con)
	
***********************************************
List: implements (ArrayList, Vector, LinkedList)
	- theo thứ tự (lúc inserted)
	- có trùng lặp
	- 

Queue: extends Deque implements (LinkedList, ArrayDeque)
       		     implements (PriorityQueue)
	- 

Set: extends SortedSet implements (TreeSet)	
      		       implements (HashSet, LinkedHashSet) 

	- không thứ tự
	- cấm trùng (nếu phần tử nào add bị trùng giá trị có sẵn thì sẽ không add)
	- 
------------------------------------------------
Map: extends SortedMap implements (TreeMap)
     		       implements (HashMap, LinkedHashMap, HashTable)
	- không thứ tự
	- lưu một cặp key-value (key: unique)
=========================================================================================================================================================
Type                        Method                                                           Description

    public interface List<E> extends Collection<E> 

                    void add(int index,Object element)          Nó được sử dụng để chèn các phần tử vào list tại chỉ số index.
                    boolean addAll(int index,Collection c)	Nó được sử dụng để chèn tất cả các yếu tố của c vào danh sách tại chỉ số index.
                    object get(int index)                       Nó được sử dụng để trả về đối tượng được lưu trữ tại chỉ số index trong list.
List                object set(int index,Object element)	Nó được sử dụng để gán phần tử cho vị trí được chỉ định index trong list.
                    object remove(int index)                    Nó được sử dụng để xóa các phần tử tại vị trí có chỉ số index và trả về phần tử đã xóa.
                    ListIterator listIterator()                 Nó được sử dụng để trả về một Iterator mà bắt đầu từ phần tử đầu tiên của list.
                    ListIterator listIterator(int index)	Nó được sử dụng để trả về một Iterator mà phần tử bắt đầu từ chỉ số index chỉ định.
---------------------------------------------------------------------------------------------------------------------------------------------------------
    public interface ListIterator<E> extends Iterator<E>        ListIterator là một interface được sử dụng để duyệt các phần tử của List trong java
                                                                (ngoài ra ta có thể sử dụng forEach để duyệt)

                    boolean hasNext()                           Phương pháp này trả về true nếu list interator có tồn tại phần tử kế tiếp phần tử hiện tại.
                    Object next()                               Phương thức này trả về phần tử kế tiếp trong danh sách và vị trí con trỏ tăng lên 1.
ListIterator        boolean hasPrevious()                       Phương pháp này trả về true nếu list interator có tồn tại phần tử kế sau phần tử hiện tại.
                    Object previous()                           Phương thức này trả về phần tử kế sau trong danh sách và vị trí con trỏ giảm đi 1.
            
---------------------------------------------------------------------------------------------------------------------------------------------------------
    public interface Set<E> extends Collection<E>

                    boolean add(Object element)                 Nó được sử dụng để chèn các phần tử vào set.
                    boolean addAll(Collection c)                Nó được sử dụng để chèn tất cả các phần tử của c vào set.
                    void clear()                                Xóa tất cả các phần tử khỏi set.
                    boolean contains(Object element)            Trả về true nếu tập hợp này chứa phần tử đã chỉ định.
                    boolean containsAll(Collection c)           Trả về true nếu set chứa tất cả các phần tử của collection c đã chỉ định.
                    boolean equals(Object o)                    So sánh các đối tượng được chỉ định với set.
                    boolean isEmpty()                           Trả về true nếu set không chứa phần tử.
Set                 int hashCode()                              Trả về giá trị mã băm
                    Iterator iterator()                         Trả về một trình vòng lặp iterator để duyệt qua các phần tử của set.
                    boolean remove(Object o)                    Xóa phần tử đã chỉ định khỏi set.
                    boolean removeAll(Collection c)             Xóa khỏi set tất cả các phần tử của nó được chứa trong collection c đã chỉ định.
                    boolean retainAll(Collection c)             Chỉ giữ lại các phần tử trong set được chứa trong collection c đã chỉ định.
                    int size()                                  Trả về số lượng các phần tử của set.
                    Object[] toArray()                          Trả về một mảng chứa tất cả các phần tử trong set.
                    T[] toArray(T[] a)                          Trả về một mảng chứa tất cả các phần tử trong set, kiểu run-time của mảng trả về là kiểu đã chỉ định.
---------------------------------------------------------------------------------------------------------------------------------------------------------
    public class HashMap<K,V> extends AbstractMap<K,V>
    implements Map<K,V>, Cloneable, Serializable
            Trong đó:   K: đây là kiểu key để lưu trữ.
                        V: đây là kiểu giá trị được ánh xạ.

                    void clear()                                Xóa tất cả các phần tử của HashMap.
                    Object clone()                              Trả về một bản copy của HashMap.
                    boolean containsKey(Object key)             Trả về true nếu HashMap chứa một phần tử có key được chỉ định.
                    boolean containsValue(Object value)         Trả về true nếu HashMap chứa một phần tử có giá trị (value) được chỉ định.
                    Set entrySet()                              Trả về Collection view các ánh xạ có trong HashMap.
                    Object get(Object key)                      Trả về giá trị của key được chỉ định.
                    boolean isEmpty()                           Trả về true nếu HashMap trống.
                    Set keySet()                                Trả về một Set interface chứa tất cả các key của HashMap.
                    Object put(Object key, Object value)	Thêm một cặp key-value vào HashMap.
                    void putAll(Map t)                          Sao chép các phần tử của Map được chỉ định vào HashMap.
                    Object remove(Object key)                   Xóa một phần tử có key được chỉ định ra khởi HashMap.
                    int size()                                  Trả về số phần tử của HashMap.
                    Collection values()                         Trả về Collection của các giá trị có trong HashMap.

    Duyệt các phần tử của HashMap có 3 cách: forEach, Iterator, Map.Entry<Key,Value>
    
                    Ví dụ này chúng ta sẽ sử dụng hàm keySet() để lấy ra một đối tượng Set chứa các key của HashMap. 
                    Sau đó sử dụng vòng lặp for để lặp các key của đối tượng Set. 
                    Sử dụng các key này để lấy ra giá trị tương ứng được lưu trữ trong đối tượng HashMap:

                    public static void main(String args[]) {
                            // init hashMap
                            HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
                            // add elements to hashMap
                            hashMap.put(1, "Java");
                            hashMap.put(3, "C++");
                            hashMap.put(2, "PHP");
                            hashMap.put(4, "Python");
                            // hien thi HashMap
                            show(hashMap);
                        }

                    public static void show(HashMap<Integer, String> hashMap) {
                        Set<Integer> keySet = hashMap.keySet();
                        for (Integer key : keySet) {
                            System.out.println(key + " - " + hashMap.get(key));
                        }
                    }
-----------------------------------------------------------------------------------------------------------------------------
                    Bạn có thể sử dụng phương thức entrySet() để duyệt các phần tử của HashMap, 
                    phương thức này trả về một đối tượng Map.Entry. Vi dụ:

                    public static void main(String args[]) {
                        // init map
                        HashMap<Integer, String> map = new HashMap<Integer, String>();
                        // add elements to map
                        map.put(1, "Java");
                        map.put(3, "C++");
                        map.put(2, "PHP");
                        map.put(4, "Python");
                        // show map
                        for (Map.Entry<Integer, String> entry : map.entrySet()) {
                            System.out.println(entry.getKey() + " - " + entry.getValue());
                        }
                    }
-----------------------------------------------------------------------------------------------------------------------------
                    
                    Duyệt từng key của map -> map.keySet().iterator();

                    public static void main(String args[]) {
                        // khoi tao map
                        TreeMap<String, String> map = new TreeMap<String, String>();
                        // them cac phan tu vao map
                        map.put("J", "Java");
                        map.put("C", "C++");
                        map.put("P", "PHP");
                        map.put("Py", "Python");
                        // show hashMap
                        Iterator<String> itr = map.keySet().iterator();
                        while (itr.hasNext()) {
                            System.out.println(map.get(itr.next()));
                        }
                    }