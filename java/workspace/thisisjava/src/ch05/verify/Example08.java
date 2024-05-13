package ch05.verify;

public class Example08 {
	public static void main(String[] args) {
		int[][] array = {
				{95, 86},
				{83, 92, 96},
				{78, 83, 93, 87, 88}
		};
		
		int totalSum = 0;
		double totalNum = 0;
		
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				totalNum += 1;
				totalSum += array[i][j];
			}
		}
		
		double totalAvg = (double) totalSum / totalNum;
		System.out.println("총합 : " + totalSum);
		System.out.println("평균 : " + totalAvg);
	}
}
