package com.moshiur.springrevision.basic;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("bubble")
public class BubbleSort implements SortingAlogrithms {
    @Override
    public void sort(int[] numbers) {
        System.out.println("Sorted using Bubble sort");
    }
}
