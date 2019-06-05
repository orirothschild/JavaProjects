package il.co.ilrd.poalimtest;

import java.util.Stack;

public class Poalimtest {
    
    public static void printReverseStringbuilder(String str) {
        StringBuilder str2 = new StringBuilder(str);
        System.out.println(str2.reverse());
    }
//    public static int idMatrixSize(int [][] matrix) {
//        int counter = 0;
//        for(int i = matrix.length -1; i >= 0; --i) {
//            for(int j = matrix[0].length -1; j >= 0; --j) {
//                if(i == j && checkId(matrix,i) == true && matrix[i][j] == 1) {
//                    ++counter;
//                }
//                if((i != j && matrix[i][j] != 0) ||( i == j && matrix[i][j] != 1)) {
//                break;
//                }
//            }
//        }
//        return counter;
//        
//    }
    
    public static int idMatrixSizeFaster(int [][] matrix) {
        int counter = 0;
        for(int i = matrix.length -1; i >= 0; --i) {
                if(matrix[i][i] == 1 && checkId(matrix,i) == true ) {
                    ++counter;
                }
                else {break;}
            
        }
        return counter;
        
    }
    public static boolean checkId(int [][] matrix ,int idex) {
        for(int i = idex + 1; i < matrix.length; ++i) {
            if(matrix[i][idex] != 0 || matrix[idex][i] != 0) {
                return false;
            }
        }
        return true;
    }
    
    public static void printReverse(String str) {
        char[] arr = str.toCharArray();
        for(int i = 0; i < str.length() / 2 ; ++i) {
            char temp = arr[i];
            arr[i] = str.charAt(str.length() - 1 - i);
            arr[str.length() -1 - i] = temp;
        }
        System.out.println(arr);
    }
    
    public static String  ReverseStringWithStack(String str) {
    	Stack<Character> stack = new Stack<>();
    	String string = "";
    	char [] arr = str.toCharArray();
    	for(int i = 0; i < arr.length; ++i) {
    		stack.push(arr[i]);
    		}
    	while (!stack.isEmpty()) {
    		string += stack.pop();
    	}
    	
		return string;
    }
    public static void main(String [] args) {
     /*   printReverseStringbuilder("hello");
        printReverse("hello");
        int [][] matrix = { {0,0,0,0},
                            {0,1,0,0},
                            {0,0,1,0},
                            {0,0,0,1}};
        System.out.println(idMatrixSizeFaster(matrix));*/
        System.out.println(ReverseStringWithStack("ABC"));
        }
    
    }