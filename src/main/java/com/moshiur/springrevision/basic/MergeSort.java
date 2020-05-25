package com.moshiur.springrevision.basic;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("merge")
public class MergeSort implements SortingAlogrithms {
    @Override
    public void sort(int[] numbers) {

        System.out.println("sorted numbers using Merge sort");
    }
}
