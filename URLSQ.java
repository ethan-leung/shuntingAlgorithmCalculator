/* Author: Ethan Leung
 * NetID: eleung6
 * Project 1: URCalculator
 */

class URLSQ<E> extends URLinkedList<E> {
	
	URLinkedList<E> queue = new URLinkedList<E>();
	
	public void enqueue(E e) {
		queue.addLast(e);
	}
	
	public Object dequeue() {
		return queue.pollFirst();
	}
	
	public Object first() {
		return queue.peekFirst();
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	public int size() {
		return queue.size();
	}
	
	public String toString() {
		return queue.toString();
	}
}

class Stack<E> extends URLinkedList<E> {
	URLinkedList<E> stack = new URLinkedList<E>();
	
	public void push(E e) {
		stack.addLast(e);
	}
	
	public Object pop() {
		return stack.pollLast();
	}
	
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	public E peek() {
		return stack.peekLast();
	}
	
	public String toString() {
		return stack.toString();
	}
	
	public String test() {
		return stack.test();
	}
}
