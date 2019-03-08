/*Immutable cyclic integer list implements with an array
 * and an index pointer*/
public class cyclicIntList {
	
	private int list[];
	private int current;
	
	public cyclicIntList(int length) {
		list = new int[length];
		current = 0;
	}
	
	//set the data to current and move to the next
	public void setC(int data) {
		list[current] = data;
		next();
	}
	
	//get current element
	public int getC() {
		return list[current];
	}
	
	//return the current one and iterate to the next element
	public int next() {
		int i = list[current];
		
		if (current < list.length - 1)
			++current;
		else
			current = 0;
		
		return i;
	}
}
