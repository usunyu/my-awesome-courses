import java.util.Arrays;
import java.util.HashMap;

/*
Implement Min-Heap associated with Vertex
*/

public class Heap {
	private Vertex[] heap;
	private int size;
	private HashMap<Integer, Integer> map;	// map id to index in heap
	
	public Heap(int capacity) {
		heap = new Vertex[capacity];
		map = new HashMap<Integer, Integer>();
		size = 0;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void insert(Vertex vert) {
		if(map.containsKey(vert.getId())) {	// already in the heap
			heapifyUp(map.get(vert.getId()));
		}
		else {
			heap[size] = vert;
			map.put(vert.getId(), size);
			heapifyUp(size);
			size++;
		}
	}
	
	public Vertex extractMin() {
		if(size == 0) 
			return null;
		Vertex min = heap[0];
		size--;
		heap[0] = heap[size];
		heap[size] = null;	// clear
		map.remove(min.getId());
		if(size > 0) {
			map.put(heap[0].getId(), 0);
			heapifyDown(0);
		}
		return min;
	}
	
	private int getParent(int index) {
		if(index == 0) return -1;
		else return (index - 1) / 2;
	}
	
	private int getLeftChild(int index) {
		int left = index * 2 + 1;
		if(left < size)
			return left;
		else
			return -1;
	}
	
	private int getRightChild(int index) {
		int right = index * 2 + 2;
		if(right < size)
			return right;
		else
			return -1;
	}
	
	private void swap(int i, int j) {
		Vertex tmp = heap[i];
		// update map
		map.put(heap[i].getId(), j);
		map.put(heap[j].getId(), i);
		// update heap
		heap[i] = heap[j];
		heap[j] = tmp;
	}
	
	private void heapifyUp(int index) {
		Vertex current = heap[index];
		if(current == null)
			System.out.println();
		int p = getParent(index);
		Vertex parent = (p == -1 ? null : heap[p]);
		while(parent != null && parent.getDist() > current.getDist()) {
			swap(index, p);
			index = p;
			p = getParent(index);
			parent = (p >= 0 ? heap[p] : null);
		}
	}
	
	private int getMinChild(int index) {
		int left = getLeftChild(index);
		int right = getRightChild(index);
		int min = -1;
		if(right != -1)
			min = (heap[left].getDist() < heap[right].getDist() ? left : right);
		else if(left != -1)
			min = left;
		return min;
	}
	
	private void heapifyDown(int index) {
		Vertex current = heap[index];
		int min = getMinChild(index);
		Vertex child = (min == -1 ? null : heap[min]);
		while(child != null && child.getDist() < current.getDist()) {
			swap(index, min);
			index = min;
			min = getMinChild(index);
			child = (min == -1 ? null : heap[min]);
		}
	}

	@Override
	public String toString() {
		return "Heap [heap=" + Arrays.toString(heap) + "]";
	}
}
