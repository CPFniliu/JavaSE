package euler.auxiliary;

/**
 * @author CPF
 * question 18 & 67
 *
 */
public class Maximumpathsum {
		int[][] maxCount;
		int result;
		private int maxRow;
		private int[][] arr;
		long count = 0;
		
		public long getCount() {
			return count;
		}

		public Maximumpathsum(int[][] arrays) {
			if (arrays == null) {
				throw new NullPointerException();
			}
			this.arr = arrays;
			maxCount = new int[arr.length][arr.length];
			maxRow = arr.length - 1;
		}

		public int compute() {
			jmp(0, 0, 0);
			return result;
		}
		
		public void clear(){
			arr = null;
			maxCount = null;
			result = 0;
			maxRow = 0;
			count = 0;
		}

		public void jmp(int sum, int row, int col) {
			count++;
			sum += arr[row][col];
			if (sum <= maxCount[row][col]) {
				return;
			}
			maxCount[row][col] = sum;
			if (row >= maxRow) {
				submit(sum);
				return;
			}
			jmp(sum, row + 1, col);
			jmp(sum, row + 1, col + 1);
		}
		
		private void submit(int sum) {
			if (result < sum){
				result = sum;
			}
		}
	
}
