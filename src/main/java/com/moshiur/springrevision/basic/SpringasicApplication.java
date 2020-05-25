package com.moshiur.springrevision.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringasicApplication {

	public static void main(String[] args) {
		/*
		* In a non-spring scenario, we would get the BinarySearchImpl object as follows,
		* BinarySearchImpl binarySearch = new BinarySearch(new BubbleSort());
		* if we wanted to use mergeSort,  BinarySearchImpl binarySearch = new BinarySearch(new BubbleSort());
		*
		* In the above lines, we're creating a BubbleSort object, injecting it to the BinarySearchImpl object while
		* creating BinarySearchImpl object. So, two objects are being created and one dependency is being injected.
		*
		* Spring takes care of these tasks using ApplicationContext.
		* ApplicationContext applicationContext = SpringApplication.run(SpringasicApplication.class, args);--> this line
		* is creating two objects and performing one dependency injection with the help of the annotations.
		* 
		* */

		ApplicationContext applicationContext = SpringApplication.run(SpringasicApplication.class, args);
		BinarySearchImpl binarySearch0 = applicationContext.getBean(BinarySearchImpl.class);
		BinarySearchImpl binarySearch1 = applicationContext.getBean(BinarySearchImpl.class);
		BinarySearchImpl binarySearch2 = applicationContext.getBean(BinarySearchImpl.class);
		BinarySearchImpl binarySearch3 = applicationContext.getBean(BinarySearchImpl.class);
		System.out.println("----------------------------objects--------------------------");
		System.out.println(binarySearch0);
		System.out.println(binarySearch1);
		System.out.println(binarySearch2);
		System.out.println(binarySearch3);
		System.out.println("----------------------------objects--------------------------");
		int result = binarySearch0.binarySearch(new int[]{1,2,3,4},3);
		System.out.println("result: "+result);

	}

}
