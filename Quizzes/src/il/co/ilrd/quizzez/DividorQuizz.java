package il.co.ilrd.quizzez;

import java.util.Arrays;

public class DividorQuizz {

		public static int [] quizzdivision(int[] arr) {
			int [] arrbegining = new int[arr.length];
			int [] arrend = new int[arr.length];
			int [] arrreturn = new int[arr.length];
			int length = arr.length;
			int i;
			arrbegining[0] = 1;
			
			for(i = 1; i <arr.length; ++i) {
				arrbegining[i] = arrbegining[i - 1] * arr[i -1];
			}
			
			arrend[arr.length -1] = 1;
			for(i = arr.length -2 ; i >= 0; --i) {
				arrend[i] = arrend[i +1] * arr[i +1];
			}
			
			for(i = 0; i <arr.length; ++i) {
				arrreturn[i] = arrbegining[i] * arrend[i];
			}
			return arrreturn;
		}
		
		public static void main(String[]args) {
			int[] arr = {2,3,4};
			int[] returnedarr  = quizzdivision(arr);
			System.out.println(Arrays.toString(returnedarr));
		}
}
