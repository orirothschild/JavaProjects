package il.co.ilrd.quizzez;

public class Lis {
		// Function to find length of longest increasing subsequence
		public static int LIS(int[] A, int i, int n, int prev)
		{
			// Base case: nothing is remaining
			if (i == n) {
				return 0;
			}

			// case 1: exclude the current element and process the
			// remaining elements
			int excl = LIS(A, i + 1, n, prev);

			// case 2: include the current element if it is greater
			// than previous element in LIS
			int incl = 0;
			if (A[i] > prev) {
				incl = 1 + LIS(A, i + 1, n, A[i]);
			}

			// return maximum of above two choices
			return Integer.max(incl, excl);
		}

		// Program for Longest Increasing Subsequence
		public static void main(String[] args)
		{
			int[] A = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 };

			System.out.print("Length of LIS is " + LIS(A, 0, A.length, Integer.MIN_VALUE));
		}
}
