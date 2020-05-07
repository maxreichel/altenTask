package altenTask.code;

import java.util.ArrayList;
import java.util.Collections;

public class IntervalSorter {
	
	ArrayList<Interval> intervalList = new ArrayList<Interval>();
	
	public IntervalSorter(ArrayList<Interval> newList) {
		this.intervalList = newList;
	}
	
	public ArrayList<Interval> sortByStart(){
		Collections.sort(this.intervalList, Interval.CompareStart);
		System.out.println("Input Intervals:\n");
		for (int i =0; i<intervalList.size();i++) {
			System.out.println("[" + intervalList.get(i).intervalStart + "," + intervalList.get(i).intervalEnd + "] ");
		}
		System.out.println("\n");
		return intervalList;
	}
}
