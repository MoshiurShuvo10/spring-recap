package com.moshiur.springrevision.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class BinarySearchImpl {

    private Logger logger = LoggerFactory.getLogger(BinarySearchImpl.class);


    /*
    * Here, We used @Qualifier to set dependencies.
    * dependencie injection can be achieved by 3 ways.
    *
    * 1. dependency by name
    * ex: SortingAlgorithm bubbleSort or, SortingAlgorithm mergeSort
    *
    * 2. using @primary annotation
    * @Component
    * @primary
    * public class BubbleSort implements SortingAlgorithms {...}
    * if there is multiple beans of dependencies, @primary will get higher priority. That class will be autowired which
    * is annotated by @primary
    *
    * 3. Using @Qualifier
    * Each and every dependency class must be annotated by @Qualifier. And that class which has this dependencies,
    * will use @Qualifier annotaion
    * */
    @Autowired
    @Qualifier("merge")
    SortingAlogrithms sortingAlogrithms;

    public int binarySearch(int[] numbers,int elementToSearchFor){
        sortingAlogrithms.sort(numbers);
        System.out.println("Algorithm used: "+sortingAlogrithms);
        return 4 ;
    }

    @PostConstruct
    public void postConstruct(){
        logger.info("...............post construct of BinarySearchImple bean");
    }

    @PreDestroy
    public void preDestroy(){
        logger.info("..............pre destroy of BinarySearchImple bean");
    }
}
