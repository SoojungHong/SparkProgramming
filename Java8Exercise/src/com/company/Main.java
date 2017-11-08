package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Main myTest = new Main();
        //myTest.testStream1();
        //myTest.testStream2();
        //myTest.testStream3();
        myTest.testStream4();
    }

    private void testStream1() {
        System.out.println("====== Java 8 Stream Test 1======");

        List<String> myList = Arrays.asList("a1", "a2", "b1", "c1", "c2");
        /*
        Stream<String> streamString = myList.stream();
        Stream<String> filteredString = streamString.filter(s -> s.startsWith("c")); //filter string start with c
        filteredString.map(String::toUpperCase).sorted().forEach(System.out::println);
        */

        myList
                .stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
    }

    private void testStream2() {
        System.out.println("====== Java 8 Stream Test 2 ======");

        List<String> myList = Arrays.asList("the one", "a1", "a2", "a3");
        myList
                .stream()
                .findFirst()
                .ifPresent(System.out::println);
    }

    private void testStream3() {
        Arrays.stream(new int[]{1,2,3})
                .map(n -> 2 * n + 1)
                .average()
                .ifPresent(System.out::println);
    }

    private void testStream4() {
        Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1)) //1 is index 1 - in this case 1, 2, 3
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);
    }
}
