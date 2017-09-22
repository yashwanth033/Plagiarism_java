/*
@Author Yaswant Reddy Kadiyam
-----------------------------------------------------------------
Plagiarism project in java
-----------------------------------------------------------------  
 */

package FileReading_plagiarism;

import java.util.ArrayList;

/**
 * @author Yaswant Reddy Kadiyam
-------------------------------------------------------------------
The operator class has functions for calculating cosine similarity,
LCS and Finger-printing.
-------------------------------------------------------------------
 */
public class Operators {
	
	static void bagOfWords(FileData file1,FileData file2) {
		
		/*
		--------------------------------------------------------------
		This function takes two files and calculate their cosine
		similarity and print it.
		--------------------------------------------------------------
		*/
		
		if(file1.isfileempty||file2.isfileempty) {
			System.out.printf("%s\t\t","Empty");
		}
		else {
			int num = 0;
			double den1 = 0.0;
			double den2 = 0.0;
			double cossim;
			for(String k:file1.wordFreqs.keySet()) {
				if(file2.wordFreqs.containsKey(k)) {
					num += (file1.wordFreqs.get(k)*file2.wordFreqs.get(k));
				}		
			}
			for(String f:file1.wordFreqs.keySet()) {
				den1 += Math.pow(file1.wordFreqs.get(f),2);
			}
			den1 = Math.sqrt(den1);
			for(String f:file2.wordFreqs.keySet()) {
				den2 += Math.pow(file2.wordFreqs.get(f),2);
			}
			den2 = Math.sqrt(den2);
			cossim = (num/(den1*den2))*100;
			System.out.printf("%.2f\t\t",cossim);
		}		
	}
	
	static void lCS(FileData file1, FileData file2) {
		
		/*
		 ------------------------------------------------------
		 This function takes two files and calculate their 
		 L.C.S and print it.
		 ------------------------------------------------------
		 */

		if(file1.isfileempty||file2.isfileempty) {
			System.out.printf("%s\t\t","Empty");
		}
		else {
			int z = 0;
			int i = 0;
			int c = 0;
			for(i=0;i<file1.words.length;i++)
			{
				int k = i;
				int j = 0;
				c = 0;
				while(k<file1.words.length && j<file2.words.length)
				{
					while (((k<file1.words.length) && (j<file2.words.length)) && (file1.words[k].equals(file2.words[j])))
					{	
						c += file1.words[k].length();
						k += 1;
						j += 1;
					}
					if(k<file1.words.length && j<file2.words.length)
					{
						if(!(file1.words[k].equals(file2.words[j])))
						{
							k = i;
							if(c>z)
								{
									z = c;
								}
							c = 0;
							if(!(file1.words[k].equals(file2.words[j])))
								{
									j += 1;
								}
						}
					}
				}	
				if(c>z)
				{
					z = c;
				}
			}
			if(c>z)
			{
				z = c;
			}
			double len_dct1 = 0;
			double len_dct2 = 0;
			for(i=0;i<file1.words.length;i++)
			{
				len_dct1 = len_dct1 + file1.words[i].length();
			}
			for(i=0;i<file2.words.length;i++)
			{
				len_dct2 = len_dct2 + file2.words[i].length();
			}
			double lcs = ((2*z)/(len_dct1+len_dct2))*100;
			System.out.printf("%.2f\t\t",lcs);
		}
		
		
	}
	
	static void fingerprinting(FileData file1,FileData file2) {
		
		/*
		 ------------------------------------------------------------
		 This function takes two files to calculate their finger
		 printing similarity and print it.
		 ------------------------------------------------------------
		 */
		
		if((file1.isfileempty||file2.isfileempty)) {
			System.out.print("EMPTY\t\t");
		}
		else {
			if((file1.issmall||file2.issmall)) {
				System.out.print("SMALL\t\t");
			}
			else {
				int i = 0;
				int j = 0;
				double cnt = 0;
				ArrayList<Integer> dummy1 = new ArrayList<Integer>();
				ArrayList<Integer> dummy2 = new ArrayList<Integer>();
				for (Integer x:file1.pmod) {
					dummy1.add(x);
				}
				for(Integer x:file2.pmod) {
					dummy2.add(x);
				}
				for(i=0;i<dummy1.size();i++) {
					for(j=0;j<dummy2.size();j++) {
						if((dummy1.get(i)).equals(dummy2.get(j))){
							cnt += 1;
							dummy2.set(j,0);
							break;
						}
					}
				}

				double totalln = dummy1.size()+dummy2.size();
				if(totalln==0)
				{
					System.out.print("SMALL\t\t");
				}
				else
				{
					double fnprnt = ((2*cnt)/totalln)*100;
					System.out.printf("%.2f\t\t",fnprnt);
				}
				
			}


		}
		
	}
}