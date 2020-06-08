package HomeMin;

public class Code0518_2 {
	public static void main(String[] args) {
		
		int[] intAry = {10, 8, 5, 3, 7, 1};
		int temp = 0;
		int a =0;
		for (int i=0; i<intAry.length; i++) {
			  int min = Integer.MAX_VALUE;
			  for (int j=i; j< intAry.length ; j++) {
			   if (min > intAry[j]) {
			      min = intAry[j];
			      a=j;
			      }
			   }
			  temp = intAry[i];
			  intAry[i] = intAry[a];
			  intAry[a] = temp;
		}
		
		for( int i=0; i<intAry.length; i++) {
			System.out.print(intAry[i]+" ");
			
		}
		
	}
}

