package com.halil.bookstoreapi;

import java.util.Arrays;
import java.util.List;

public class Exam {
    public static void main(String[] args) {
        List drinks= Arrays.asList("can","cup");
        for(int container = 0;container<drinks.size();container++){
            System.out.println(drinks.get(container)+",");
        }
    }
}
