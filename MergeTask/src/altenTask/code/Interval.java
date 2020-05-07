package altenTask.code;
import java.util.ArrayList;
import java.util.Comparator;

public class Interval {
	//Class Variables
	int intervalStart;
	int intervalEnd;
	
	//Constructor
	public Interval(int start, int end) throws Exception{
	
		if(start<0 || end<0)
		{
			throw new Exception();
		}
		if(start>end) {
			this.intervalStart=end;
			this.intervalEnd=start;
		}else {
			this.intervalStart = start;
			this.intervalEnd = end;
		}
	}
	
	//getter, setter methods
	public int getIntervalStart() {
		return this.intervalStart;
	}
	
	public int getIntervalEnd() {
		return this.intervalEnd;
	}
	
	public void setIntervalStart(int newStart) {
		this.intervalStart =newStart;
	}
	
	public void setIntervalEnd(int newEnd) {
		this.intervalEnd = newEnd;
	}
	
	//Comparator to sort Intervals by start of Interval, Ascending
	public static Comparator<Interval> CompareStart = new Comparator<Interval>() {
		
		public int compare(Interval i1, Interval i2) {
			if(i1.getIntervalStart() < i2.getIntervalStart()) {
				return -1;
			}else if(i2.getIntervalStart()==i1.getIntervalStart()) {
				return 0;
			}else {
				return 1;
			}
		}
	};
	
	//Merge Function to find overlapping Intervals
	public static ArrayList<Interval> merge(ArrayList<Interval> inputIntervalList){
		
		//Empty List Case
		if(inputIntervalList.isEmpty()) {
			try {
				Interval empty = new Interval(0,0);
				ArrayList<Interval> emptyList = new ArrayList<Interval>();
				emptyList.add(1, empty);
				return emptyList;
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		}
		
		//Initiating sorted ArrayLists of input Intervals
		ArrayList<Interval> resultList = new ArrayList<Interval>();
		int tempLowestStart = 0;
		int tempHighestEnd = 0;
		
		IntervalSorter iSort = new IntervalSorter(inputIntervalList);
		ArrayList<Interval> tempList = iSort.sortByStart();
		
		//Merging overlapping Intervals
		while(tempList.size()>1) {
			tempLowestStart = tempList.get(0).getIntervalStart();
			tempHighestEnd = tempList.get(0).getIntervalEnd();
			int index = 0;
			for (int i = 1; i < tempList.size();i++) {
				
				//Overlapping Intervals
				if(tempList.get(i).getIntervalStart()<=tempHighestEnd) {
					//Check ends of Intervals to pick new Highest End
					if(tempHighestEnd <= tempList.get(i).getIntervalEnd()) {
						tempHighestEnd = tempList.get(i).getIntervalEnd();
					}
				}
				//Not Overlapping Anymore
				else {
					break;
				}
				index = i;
			}
			try {
				//Add resulting merged Interval to output List
				resultList.add(new Interval(tempLowestStart, tempHighestEnd));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//remove already checked Intervals
			for (int j = 0; j<=index;j++) {
				tempList.remove(0);
			}
		}
		
		//After the Merging, List can still include one non-merged Interval
		if(!tempList.isEmpty()) {
			resultList.add(tempList.get(0));
		}
		return resultList;
	}
	
	public static void main(String args[]) {
		ArrayList<Interval> testInputList = new ArrayList<>();
		ArrayList<Interval> testOutputList = new ArrayList<>();
		
		//Fill Input List
		try {
			testInputList.add(new Interval(1,5));
			testInputList.add(new Interval(12,15));
			testInputList.add(new Interval(4,6));
			testInputList.add(new Interval(3,10));
			testInputList.add(new Interval(11,15));
			testInputList.add(new Interval(20,7));
			testInputList.add(new Interval(21,22));
			testInputList.add(new Interval(25,50));
			
			testOutputList = merge(testInputList);
			System.out.println("Merged List:\n");
			
			for (int i=0;i<testOutputList.size();i++) {
				System.out.println("[" + testOutputList.get(i).intervalStart + ", "+ testOutputList.get(i).intervalEnd + "]" );
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
