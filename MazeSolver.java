package eg.edu.alexu.csd.datastructure.maze.cs34_cs40;

import java.io.File;
import java.util.Scanner;

public class MazeSolver implements IMazeSolver {
	
	private class Node {
		
		private Object value;
		private Node next;
		
		private Node (Object v , Node n) {
			value = v;
			next = n;
		}
		
		private Object getValue() {
			return value;
		}
		
		private Node getNext() {
			return next;
		}
		
		private void setValue(Object v) {
			value= v;
		}
		
		private void setNext(Node n) {
			next = n;
		}
	}
	
	private class QueueLinkedBased{

		private int size;
		private Node head;
		private Node tail;
		
		private QueueLinkedBased() {
			size = 0;
		}

		private void enqueue(Object item) {
			Node node = new Node (item,null);
			if (isEmpty()) {
				head = node;
			}
			else {
				tail.setNext(node);
			}
			tail = node;
			size ++;
		}

		private Object dequeue(){
			if (isEmpty()) {
				return null;
			}
			else {
				Object temp = head.getValue();
				head = head.getNext();
				size --;
				if (isEmpty()) {
					tail = null;
				}
				return temp;
			}
		}

		private boolean isEmpty() {
			return (size == 0);
		}

		private int size() {
			return size;
		}

	}
	
	private class Stack{
		
		private Node top;
		private int size;
		
		private Stack() {
			top = null;
			size = 0;
		}
		
		private Object pop() {
			/**
			 * can't pop if it is empty
			 */
			if (isEmpty()) {
				return null;
			}
			else {
				Object result = top.getValue();
				top = top.getNext();
				size--;
				return result;
			}
		}

		private Object peek(){
			/**
			 * empty stack has no peek
			 */
			if (isEmpty()) {
				return null;
			}
			else {
				return top.getValue();
			}
		}

		private void push(Object element) {
			Node input = new Node(element, top);
			top = input;
			size++;
		}

		private boolean isEmpty() {
			if (size == 0) {
				return true;
			}
			else {
				return false;
			}
		}

		private int size() {
			return size;
		}

	}
	
	@Override
	public int[][] solveBFS(File maze) {

		return null;
	}

	@Override
	public int[][] solveDFS(File maze) {
		Scanner scan ;
		try {
			scan = new Scanner (maze);
		}
		catch(Exception e) {
			return null;
		}
		int row = scan.nextInt();
		int coloumn = scan.nextInt();
		String[] mazeMap = new String[row];
		int i,j;
		for (i = 0 ; i < row ; i++) {
			mazeMap[i] = scan.next();
		}
		scan.close();
		boolean found = false;
		Stack S = new Stack();
		int[] T = new int[2];
		for (i = 0 ; i < row ; i++) {
			for (j = 0 ; j < coloumn ; j++) {
				if (mazeMap[i].charAt(j) == 'S') {
					T[0] = i;
					T[1] = j;
					found = true;
					break;
				}
			}
			if (found) break;
		}
		int[][] result = null;
		S.push(T);
		while (!S.isEmpty()) {
			T =(int[]) S.peek();
			if (mazeMap[T[0]].charAt(T[1]) == 'E'){
				result = new int[S.size()][2];
				for (i = 0 ; !S.isEmpty() ; i++) {
					result[i] = (int[]) S.pop();
				}
				break;
			}
			mazeMap[T[0]] = mazeMap[T[0]].substring(0, T[1]) + 'X' + mazeMap[T[0]].substring(T[1]+1);
			//down
			if ((T[0] < row-1) && ((mazeMap[T[0]+1].charAt(T[1]) == '.')||(mazeMap[T[0]+1].charAt(T[1]) == 'E'))) {
				S.push(new int[] {T[0]+1,T[1]});
			}
			//right
			else if ((T[1] < coloumn-1) && ((mazeMap[T[0]].charAt(T[1]+1) == '.')||(mazeMap[T[0]].charAt(T[1]+1) == 'E'))) {
				S.push(new int[] {T[0],T[1]+1});
			}
			//up
			else if ((T[0] > 0) && ((mazeMap[T[0]-1].charAt(T[1]) == '.')||(mazeMap[T[0]-1].charAt(T[1]) == 'E'))) {
				S.push(new int[] {T[0]-1,T[1]});
			}
			//left
			else if ((T[1] > 0) && ((mazeMap[T[0]].charAt(T[1]-1) == '.')||(mazeMap[T[0]].charAt(T[1]-1) == 'E'))) {
				S.push(new int[] {T[0],T[1]-1});
			}
			else {
				S.pop();
			}
		}
		return result;
	}

}
