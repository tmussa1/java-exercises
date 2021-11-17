package fall2021.hw1.src.main.java.fall2021.hw1;

import java.util.Arrays;

public class Hw1_p1 {
	
	public static void find(int[] a, int x) {

		int matchCount = 0;

		for(int i = 0; i < a.length; i++){
			if(a[i] == x){
				System.out.println(a[i] + " is in a[" + i + "]");
				matchCount++;
			}
		}

		if(matchCount == 0){
			System.out.println(x + " does not exist");
		}
	}
	
	public static boolean isPrefix(String s1, String s2) {

		char [] chArr1 = s1.toCharArray();
		char [] chArr2 = s2.toCharArray();

		int matchCount = 0;

		for(int i = 0; i < s2.length(); i++){

			matchCount = 0;

			for(int j = i; j < i + s1.length() && j < s2.length(); j++) {
				if(j < s1.length() && chArr1[j] == chArr2[j]){
					matchCount++;
				}
			}

			if(matchCount == s1.length()){
				return true;
			}
		}

		return false;
	}
	
	
	public static void main(String[] args) {
		
		int[] a = {5, 3, 5, 6, 1, 2, 12, 5, 6, 1};
		
		find(a, 5);
		find(a, 10);
		System.out.println();
		
		String s1 = "abc";
		String s2 = "abcde";
		String s3 = "abdef";
		
		if (isPrefix(s1,s2)) {
			System.out.println(s1 + " is a prefix of " + s2);
		}
		else {
			System.out.println(s1 + " is not a prefix of " + s2);
		}
		
		if (isPrefix(s1,s3)) {
			System.out.println(s1 + " is a prefix of " + s3);
		}
		else {
			System.out.println(s1 + " is not a prefix of " + s3);
		}
	}
}