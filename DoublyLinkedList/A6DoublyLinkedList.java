package assignment06;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import components.list.AbstractList;
import components.list.DoublyLinkedList;

/**
 * @author Aaron Morgan (u0393600)
 * @author Alejandro Serrano (u1214728)
 *
 * @param <E>
 */
public class A6DoublyLinkedList<E> extends DoublyLinkedList<E> {
	
	/**
	 * The Node class to be used for referencing values
	 *
	 * @param <E>
	 */
	private class Node <E> {
		E data; // Holds the data of the node
		Node next; //References the next node
		Node prev; //References the previous node
	}
	
	/**
	 * The Iterator class to be used to iterate over the list
	 *
	 * @param <E>
	 */
	private class DoublyLinkedIterator <E> implements Iterator <E>{
		
		private Node <E> current; //Keeps a reference of the current node of the list
		private int expectedModCount; // The modCount that should be equivalent to the modCount of the A6DoublyLinkedList list
		
		/**
		 * Constructor for the Iterator
		 */
		DoublyLinkedIterator(){
			current = head.next;
			expectedModCount = modCount;
		}

		@Override
		public boolean hasNext() {
			if(expectedModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			return current.data != null;
		}

		@Override
		public E next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			E element = current.data;
			current = current.next;
			
			return element;
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
	
	private int size; //The size of the list
	private int modCount; //The count of how many times the list has been changed
	private Node head; // The reference to the first node of the list
	private Node tail;// The reference to the last node of the list
	
	public A6DoublyLinkedList (){
		
		this.size = 0;
		this.modCount = 0;
		head = new<E> Node();
		tail = new <E> Node();
		head.next = tail;
		tail.prev = head;
	}
	
	@Override
	public void clear() {
		this.size = 0;
		head = new<E> Node();
		tail = new <E> Node();
		head.next = tail;
		tail.prev = head;
		
		modCount++;
	}
	
	@Override
	public int size() {
		return this.size;
	}
	
	@Override
	public Iterator<E> iterator(){
		DoublyLinkedIterator <E> iterator = new DoublyLinkedIterator();
		return iterator;
	}
	
	@Override
	public void add(E e) {
		assert e != null : "Violation of: e is not null";
		Node <E> value = new Node <>();
		value.data = e;
		
		Node <E> tailPrev = tail.prev;
		
		tailPrev.next = value;
		value.prev = tailPrev;
		value.next = tail;
		tail.prev = value;
		size++;
		modCount++;
	}
	
	@Override
	public void add(int index, E val) {
		assert 0 <= index : "Violation of: 0 <= index";
		assert index <= this.size() : "Violation of: index < this.size()";
		assert val != null : "Violation of: val is not null";
		
		Node <E> value = new Node <E>();
		value.data = val;
		
		//The first check to see if the list is currently empty
		if(this.size() == 0) {
			head.next = value;
			Node <E> n = head.next;
			n.prev = head;
			head.next = n;
			n.next = tail;
			tail.prev = n;
			size++;
			modCount++;
		}
		else {
			//If the desired index is 0, it only needs to reference the head
			if(index == 0) {
				Node <E> temp = head.next;
				head.next = value;
				Node <E> n = head.next;
				n.prev = head;
				n.next = temp;
				temp.prev = n;
				size++;
				modCount++;
			}
			//Otherwise, just add at the desired index. Two new nodes will need to be created: One for
			//the previous node and one for the next node.
			else {
				Node <E> current = new Node <E>();
				current = head.next;
				int count = 0;
				//Traverses the list from the head to the desired index
				while (count < index) {
					current = current.next;
					count++;
				}
				Node <E> n = current.prev;
				n.next = value;
				value.prev = n;
				value.next = current;
				current.prev = value;
				size++;
				modCount++;
			}	
		}
	}
	
	@Override
	public E remove(int index) {
		assert 0 <= index : "Violation of: 0 <= index";
		assert index < this.size() : "Violation of: index < this.size()";
		
		// We check whether we need to remove from the tail 
		if(index == this.size()-1) {
			Node <E> temp = tail.prev;
			Node <E> tempPrev = tail.prev.prev;
			tempPrev.next = tail;
			tail.prev = tempPrev;	
			size--;
			modCount++;
			return temp.data;
		}
		// Similarly here, but we check to see if the index is at the beginning
		// we simply need to change the reference of the head. 
		else if(index == 0) {
			Node <E> current = head.next;
			Node <E> tempNext = head.next.next;
			head.next = tempNext;
			tempNext.prev = head;
			size--;
			modCount++;
			return current.data;
		}
		// Otherwise, we check if the index is greater of less than the size of the list
		// this will help us lower the amount of data the loop needs to check
		else if (index > this.size()/2) {
			Node <E> current = tail.prev;
			int count = this.size()-1;
			while(count > index) {
				current = current.prev;
				count--;
			}
			Node<E> previous = current.prev;
			Node<E> next = current.next;
			previous.next = next;
			next.prev = previous;
			size--;
			modCount++;
			return current.data;
		}
		else {
		
			int count = 0;
			Node<E> current = head.next;
			while (count < index) {
				current = current.next;
				count++;
			}
			Node<E> previous = current.prev;
			Node<E> next = current.next;

			previous.next = next;
			next.prev = previous;
			size--;
			modCount++;

			return current.data;
		}
	}
	
	@Override
	public E get(int index) {
		assert 0 <= index : "Violation of: 0 <= index";
		assert index < this.size() : "Violation of: index < this.size()";
		
		int count = 0;
		Iterator<E> iterator = this.iterator();
		while (count < index) {
			iterator.next();
			count++;
		}
		return iterator.next();
	}
	
	@Override
	public int indexOf(E e) {
		assert e != null : "Violation of: e is not null";
		int index = 0;
		
		Iterator<E> iterator = this.iterator();
		while(iterator.hasNext()){
			if(iterator.next().equals(e)) {
				return index;
			}
			index++;
		}
		return -1;
	}
	
	@Override
	public void reverse() {
		//Two nodes are created to reference the first and last nodes
		Node <E> tempFront = head.next;
		Node <E> tempBack = tail.prev;
		//This loop goes from the first node and the last node, and
		//moves towards the middle, swapping the data
		for(int i = 0; i < this.size()/2; i++) {
			E frontData = tempFront.data;
			E backData = tempBack.data;
			
			tempBack.data = frontData;
			tempFront.data = backData;
			
			tempFront = tempFront.next;
			tempBack = tempBack.prev;
		}
		modCount++;
	}
	@Override
	public String toString() {
		
		StringBuilder string = new StringBuilder();
		string.append("[");
		Iterator<E> iterator = this.iterator();
		int count = 0;
		while(iterator.hasNext()) {
			if(count == this.size()-1) {
				string.append(iterator.next());
			}else {
				string.append(iterator.next());
				string.append(",");
			}
			count++;
		}
		string.append("]");
		return string.toString();
	}
  
}
