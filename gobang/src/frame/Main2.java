package frame;

import java.util.Random;

import domain.SortedPlaces;
import entity.Place;

public class Main2 {

	public static void main(String[] args) {
		int i = 100;
		while (i-->0) {
			System.out.println(new Random().nextInt(3));
		}
	}
	

	public static void timeTest(){
		long t1, t2;
		int limit = 100_000;
		t1 = System.nanoTime();
		for (int i = 0; i < limit; i++) {
			test1();
		}
		t2 = System.nanoTime();
		System.out.println(t2 - t1);
	}
	
	
	public static void test1(){
		SortedPlaces so = new SortedPlaces();
		for (int i = 8 ; i >= 0; i--){
			for (int j = 10; j >= 0; j--){
				so.addOrInit(new Place(i, j), new Random().nextInt(10));
			}
		}
		so.getSortedPlaces();
	}
	



}
