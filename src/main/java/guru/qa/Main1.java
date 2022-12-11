package guru.qa;

import java.util.*;

public class Main1 {
    public static void main(String[] args) {
        int[] intArr = new int[] {1, 33, -100};
        int[] intArr0 = new int[3];
        intArr0[0] = 1;
        intArr0[1] = 33;
        intArr0[2] = 100;
        int[][] twoDimensArray = new int[][] {intArr, intArr0};

        for (int i = 0; i < twoDimensArray.length; i++) {
            int[] arr = twoDimensArray[i];
            for (int a = 0; a < arr.length; a++) {
                if (arr[a] == 100) {
                    System.out.println("find " + intArr0[a]);
                    break;
                }
                System.out.println("n a ");
            }
        }


        //всего 4 цикла
        for (int i = 0; i < intArr0.length; i++) {
            if (intArr0[i] == 100) {
                break;
            }
            System.out.println("n i ");
        }

        for(int element : intArr0) {
            if (element == 100) {
                break;
            }
            System.out.println("n i ");
        }

        boolean founded = true;
        int desiredValue;
        while (!founded) {
            //search process
            founded = true;
        }

        do {

        } while (!founded);

        //коллекции
        List<Integer> intList = List.of(1, 33, 100);
        //intList.add(1);
        intList.remove((Integer) 1);
        intList.stream()
                .filter(e -> e == 100)
                .findFirst()
                .orElseThrow();

        Set<String> stringSet = Set.of("Sergey", "Sergey1");
        Set<String> stringSet1 = new HashSet<>();

        Map<String, People> aMap = Map.of(
                "123", new People(),
                "321", new People()
        );

        aMap.get("123");
        aMap.remove("123", new People());

        Set<String> keySet = aMap.keySet();
        Collection<People> values = aMap.values();
        Set<Map.Entry<String, People>> entries = aMap.entrySet();
        for (Map.Entry<String, People> entry: entries) {
            if (entry.getKey().equals("123")) {
                entry.getValue().seyRespect();
            }
        }

        for (String key : keySet) {
            if (key.equals("123")) {
                aMap.get(key).seyRespect();
            }
        }

    }
}
