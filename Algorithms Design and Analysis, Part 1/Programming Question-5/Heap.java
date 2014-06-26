import java.util.Arrays;

/*
Implement Min-Heap associated with Vertex
*/

public class Heap {
	private Vertex[] heap;
	private int size;
	
	public Heap(int capacity) {
		heap = new Vertex[capacity];
		size = 0;
	}
	
	public void insert(Vertex vert) {
		heap[size] = vert;
		heapifyUp(size);
		size++;
	}
	
	public Vertex extractMin() {
		if(size == 0) 
			return null;
		Vertex min = heap[0];
		size--;
		heap[0] = heap[size];
		heap[size] = null;	// clear
		heapifyDown(0);
		return min;
	}
	
	private int getParent(int index) {
		int parent = (index - 1) / 2;
		if(parent >= 0)
			return parent;
		else
			return -1;
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
		heap[i] = heap[j];
		heap[j] = tmp;
	}
	
	private void heapifyUp(int index) {
		Vertex current = heap[index];
		int p = getParent(index);
		Vertex parent = (p == -1 ? null : heap[p]);
		while(parent != null && parent.getDist() < current.getDist()) {
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
