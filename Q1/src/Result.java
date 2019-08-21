import java.util.*;

import static java.lang.Math.pow;

public class Result {

    public static void customSort(List<Integer> arr) {

        List<Integer> result = customSortArray(arr);

        if (result.isEmpty()){
            System.out.println("Invalid Params - customSort");
        }

        System.out.println(result.toString());
    }


    public static List<Integer> customSortArray(List<Integer> arr){

        if (Objects.isNull(arr) || arr.isEmpty() || arr.size() > pow(10,6)){
            return new LinkedList<>();
        }

        // Count repetitions
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer integer : arr) {
            map.put(integer, map.getOrDefault(integer, 0) + 1);
        }

        // The idea is to create order lists by the number of repetitions in a PriorityQueue.
        // I store these lists in a TreeMap using the number of repetitions as key.
        Map<Integer, PriorityQueue<Integer>> map2 = new TreeMap<>();
        PriorityQueue<Integer> l;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer k = entry.getKey();
            Integer v = entry.getValue();
            l = map2.getOrDefault(v, new PriorityQueue<Integer>());
            for (int i = 0; i < v; i++) {
                l.add(k);
            }
            map2.put(v, l);
        }

        List<Integer> result = new LinkedList<>();

        map2.forEach((k, v) -> result.addAll(v));

        return result;
    }



    static void tests(){

        List<Integer> resultList = customSortArray(null);
        System.out.println("List should be empty: " + resultList.isEmpty());


        resultList = customSortArray(new LinkedList<Integer>());
        System.out.println("List should be empty: " + resultList.isEmpty());


        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(5);
        a.add(5);
        a.add(5);
        a.add(5);
        a.add(3);
        a.add(2);
        a.add(1);
        a.add(4);
        a.add(2);
        a.add(2);
        a.add(4);
        a.add(6);
        a.add(8);
        a.add(10);
        a.add(10);
        a.add(10);
        a.add(1);
        a.add(11);
        a.add(12);
        a.add(12);
        a.add(12);
        a.add(12);
        a.add(12);
        a.add(5);
        a.add(Integer.valueOf((int)(2*pow(10,6))));

        a.add(Integer.valueOf((int)(2*pow(10,6))));

        a.add(Integer.valueOf((int)(2*pow(10,6))));

        a.add(Integer.valueOf((int)(2*pow(10,6))));

        resultList = customSortArray(a);
        List<Integer> listToCompare = new LinkedList<>();

        int[] ar = {3, 6, 8, 11, 1, 1, 4, 4, 2, 2, 2, 10, 10, 10, 2000000, 2000000, 2000000, 2000000, 5, 5, 5, 5, 5, 12, 12, 12, 12, 12};
        for (int i : ar) {
            listToCompare.add(i);
        }
        System.out.println("List should be in custom order: " + resultList.equals(listToCompare));
    }

}