package assignment06;


import components.list.DoublyLinkedList;
import components.list.List;
import components.list.ListOnArrays;
import components.util.Utilities;

/**
 * The class to test the performance of A6DoublyLinkedList versus ListOnArrays
 * 
 * @author Aaron Morgan (u0393600)
 * @author Alejandro Serrano (u1214728)
 *
 */
public class Timing {
	
	/**
	 * The method to test the performance of the add method.
	 * 
	 * @param list This should be an A6DoublyLinkedList list or a ListOnArrays list
	 * @param size An int: The desired size of the list
	 * @param location A String: Specifically, "front" "mid" or "end", the first letter may be capitalized
	 * @return A long: The total time it took to run the add method, at the specific location, converted from nanoseconds to microseconds
	 */
	public static long timeAdd(List<Integer> list, int size, String location) {
		int number = 10;
		long totalTime = 0;
		
		if(location.equals("front") || location.equals("Front")){
			for(int i = 0; i < size; i++) {
				long start = 0;
				start = System.nanoTime();
				list.add(0, number);
				long end = System.nanoTime();
				totalTime += (end-start);
			}
		}
		
		if(location.equals("mid") || location.equals("Mid")){
			for(int i = 0; i < size; i++) {
				int index = Utilities.randomIntBetween(0, list.size());
				long start = 0;
				start = System.nanoTime();
				list.add(index, number);
				long end = System.nanoTime();
				totalTime += (end-start);
			}
		}
		
		if(location.equals("end") || location.equals("End")){
			for(int i = 0; i < size; i++) {
				long start = 0;
				start = System.nanoTime();
				list.add(number);
				long end = System.nanoTime();
				totalTime += (end-start);
			}
		}
		
		return totalTime/1000;
	}
	
	/**
	 * This method runs the timeAdd method multiple times to get an average.
	 * 
	 * @param list This should be an A6DoublyLinkedList list or a ListOnArrays list
	 * @param size An int: The desired size of the list
	 * @param location A String: Specifically, "front" "mid" or "end", the first letter may be capitalized
	 * @param count The number of times the timeAdd method should be ran
	 * @return A long: The averaged time. This is divided by the count to get a better indication of the performance of the add method
	 */
	public static long averageAddTime(List <Integer> list, int size, String location, int count) {
		long averagedTime = 0;
		for(int i = 0; i < count; i++) {
			long time = timeAdd(list, size, location);
			averagedTime += time;
		}
		return averagedTime/count;
	}
	
	/**
	 * The method to test the performance of the remove method.
	 * 
	 * @param list This should be an A6DoublyLinkedList list or a ListOnArrays list
	 * @param size An int: The desired size of the list
	 * @param location A String: Specifically, "front" "mid" or "end", the first letter may be capitalized
	 * @return A long: The total time it took to run the remove method, at the specific location, converted from nanoseconds to microseconds
	 */
	public static long timeRemove(List<Integer> list, int size, String location) {
		int number = 10;
		long totalTime = 0;
		
		for(int i = 0; i < size; i++) {
			list.add(number);
		}
		
		if(location.equals("front") || location.equals("Front")){
			for(int i = 0; i < size; i++) {
				long start = 0;
				start = System.nanoTime();
				list.remove(0);
				long end = System.nanoTime();
				totalTime += (end-start);
			}
		}
		
		if(location.equals("mid") || location.equals("Mid")){
			for(int i = 0; i < size; i++) {
				int index = Utilities.randomIntBetween(0, list.size());
				long start = 0;
				start = System.nanoTime();
				list.remove(index);
				long end = System.nanoTime();
				totalTime += (end-start);
			}
		}
		
		if(location.equals("end") || location.equals("End")){
			for(int i = 0; i < size; i++) {
				long start = 0;
				start = System.nanoTime();
				list.remove(list.size()-1);
				long end = System.nanoTime();
				totalTime += (end-start);
			}
		}
		
		return totalTime/1000;
	}
	/**
	 * This method runs the timeRemove method multiple times to get an average.
	 * 
	 * @param list This should be an A6DoublyLinkedList list or a ListOnArrays list
	 * @param size An int: The desired size of the list
	 * @param location A String: Specifically, "front" "mid" or "end", the first letter may be capitalized
	 * @param count The number of times the timeRemove method should be ran
	 * @return A long: The averaged time. This is divided by the count to get a better indication of the performance of the remove method
	 */
	public static long averageRemoveTime(List <Integer> list, int size, String location, int count) {
		long averagedTime = 0;
		for(int i = 0; i < count; i++) {
			long time = timeRemove(list, size, location);
			averagedTime += time;
		}
		return averagedTime/count;
	}
	
	/**
	 * The method to test the performance of the reverse method.
	 * 
	 * @param list This should be an A6DoublyLinkedList list or a ListOnArrays list
	 * @param size An int: The desired size of the list
	 * @param location A String: Specifically, "front" "mid" or "end", the first letter may be capitalized
	 * @return A long: The total time it took to run the reverse method, at the specific location, converted from nanoseconds to microseconds
	 */
	public static long timeReverse(List<Integer> list, int size) {
		int number = Utilities.randomIntBetween(0, 10_000);
		for(int i = 0; i < size; i++) {
			list.add(number);
		}
		long start = 0;
		start = System.nanoTime();
		list.reverse();
		long end = System.nanoTime();
		
		long totalTime = end-start;
		
		return totalTime/1000;
	}
	
	/**
	 * This method runs the timeReverse method multiple times to get an average.
	 * 
	 * @param list This should be an A6DoublyLinkedList list or a ListOnArrays list
	 * @param size An int: The desired size of the list
	 * @param location A String: Specifically, "front" "mid" or "end", the first letter may be capitalized
	 * @param count The number of times the timeReverse method should be ran
	 * @return A long: The averaged time. This is divided by the count to get a better indication of the performance of the reverse method
	 */
	public static long averageReverseTime(List<Integer> list, int size, int count) {
		long averagedTime = 0;
		for(int i = 0; i < count; i++) {
			long time = timeReverse(list, size);
			averagedTime += time;
		}
		return averagedTime/count;
	}
	
	public static void main (String[] args) {
		List<Integer> testAdd = new A6DoublyLinkedList<>();
		List<Integer> testRemove = new A6DoublyLinkedList<>();
		List<Integer> testReverse = new A6DoublyLinkedList<>();
		
		List<Integer> testListAdd = new ListOnArrays<>();
		List<Integer> testListRemove = new ListOnArrays<>();
		List<Integer> testListReverse = new ListOnArrays<>();
		
		int[] sizeArray = new int[] {10, 100, 1000, 10000, 100000, 1000000};
		
		for(int i = 0; i < sizeArray.length; i++) {
			int size = sizeArray[i];
			System.out.println("N       " + "Insertion At   " + "Time (microseconds)");
			System.out.println("--------------------------------");
			System.out.println(size + "      " + "Front" + "         " + averageAddTime(testAdd, size, "front", 10));
			System.out.println(size + "      " + "Middle" + "        " + averageAddTime(testAdd, size, "mid", 10));
			System.out.println(size + "      " + "End" + "           " + averageAddTime(testAdd, size, "end", 10));
			System.out.println();
			System.out.println();
		}
		
		for(int i = 0; i < sizeArray.length; i++) {
			int size = sizeArray[i];
			System.out.println("N       " + "Removal At   " + "Time (microseconds)");
			System.out.println("--------------------------------");
			System.out.println(size + "      " + "Front" + "         " + averageRemoveTime(testRemove, size, "front", 10));
			System.out.println(size + "      " + "Middle" + "        " + averageRemoveTime(testRemove, size, "mid", 10));
			System.out.println(size + "      " + "End" + "           " + averageRemoveTime(testRemove, size, "end", 10));
			System.out.println();
			System.out.println();
		}
		
		for(int i = 0; i < sizeArray.length; i++) {
			int size = sizeArray[i];
			System.out.println("N       " + "Reversal   " + "Time (microseconds)");
			System.out.println("--------------------------------");
			System.out.println(size + "      " + "         " + averageReverseTime(testReverse, size, 10));
			System.out.println();
			System.out.println();
		}	

	}
}
