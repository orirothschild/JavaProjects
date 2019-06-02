package il.co.ilrd.ComplexNumber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComplexNumber implements Comparable<ComplexNumber> {
   private double real;
   private double imaginary;
   private final static int FOUR_BYTES = 32;
   private static final Pattern CHECKREGEX = Pattern.compile("([-]?[0-9]+\\.?[0-9]*?)([ ]?)([-|+]+[0-9]+\\.?[0-9]*?)[i$]");
   public ComplexNumber(double real, double imaginary) {
       this.real = real;
       this.imaginary = imaginary;
   }
 
   private boolean isSameDouble(double num,double num2) {
	   return num == num2;
   }

   public double getReal() {
       return real;
   }

   public double getImaginary() {
       return imaginary;
   }

   public void setReal(double real) {
       this.real = real;
   }

   public void setImaginary(double imaginary) {
       this.imaginary = imaginary;
   }

   public ComplexNumber add(ComplexNumber num) {
      return new ComplexNumber(real + num.real, imaginary +num.imaginary);
  
   }

   public ComplexNumber substract(ComplexNumber num) {
	   return new ComplexNumber(real - num.real, imaginary -num.imaginary);
   }

   public static ComplexNumber parse(String str) {
	   Matcher regexMatcher = CHECKREGEX.matcher(str);
	   
	   regexMatcher.matches(); 	   
       try {
    	   return (new ComplexNumber(Double.parseDouble(regexMatcher.group(1)),
    			   					 Double.parseDouble(regexMatcher.group(2))));
       }
       catch(Exception e) {
    	   return null;
       }
   }
   public boolean isReal() {
       return imaginary == 0; /*** isn't 0 + 0i real ??**/
   }

   public boolean isImaginary() {
	   return imaginary != 0&& real == 0;
   }

   public String toString() {
	   if (0 > imaginary) {
		   return (real + " " + imaginary + "i"); 
	   }
       return (real + " + " + imaginary + "i"); 
   }

   @Override
   public boolean equals(Object num) {
       if(num instanceof ComplexNumber) {
    	   ComplexNumber number = (ComplexNumber)num;
           return isSameDouble(real, number.real)&& isSameDouble(imaginary, number.imaginary);	   
       }
       return false;
       
   }

   @Override
   public int hashCode() {
	   //return Objects.hash(real, imaginary);
	   int result = (FOUR_BYTES / 2) + 1;
	   long doubleFieldBits = Double.doubleToLongBits(real);
	   
	   result = (FOUR_BYTES - 1) * result + (int)(doubleFieldBits ^ (doubleFieldBits >>> FOUR_BYTES));
	   
       return result;
  }

   @Override
   public int compareTo(ComplexNumber num) {
int compareRealResult = Double.compare(real, num.getReal());
	   
	   if (0 != compareRealResult) {
		   return compareRealResult;
	   }
	   
	   return Double.compare(imaginary, num.imaginary);
   }
}