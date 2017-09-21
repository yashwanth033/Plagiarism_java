/*
@Author Yaswant Reddy Kadiyam
-----------------------------------------------------------------
Plagiarism project in java
-----------------------------------------------------------------  
 */

package FileReading_plagiarism;

import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

/**
@author Yaswant Kadiyam Reddy
 */

public class MainClass {

	/*
	 -----------------------------------------------------------
	 The main class will read all files in the given directory
	 and enter the necessary data in to Filedata objects.
	 -----------------------------------------------------------
	 */
	
	public static void main(String[] args) {
		
	    try {
	    	FileOutputStream f = new FileOutputStream("Log.log",true);
	    	System.setOut(new PrintStream(f));
	    }
	    catch(Exception e) {
	    	System.out.println("Could not Log.");
	    }
		
		String timeStamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
		System.out.println(timeStamp);
		BufferedReader bfr;
		FileInputStream fis;
		ArrayList<String> stopwords = new ArrayList<String>();
		
		try {
			fis = new FileInputStream("/home/yaswant/Documents/Project_CSPP-2/Plagiarism_Java/stopwords.txt");
		    bfr = new BufferedReader(new InputStreamReader(fis));
		    String line = bfr.readLine();
		    while (line != null) {       
		    	stopwords.add(line);
		    	line = bfr.readLine();
		    }
		    fis.close();
		    bfr.close();
		}
		    
	    catch (FileNotFoundException e) {
		    System.out.println("File not found.\n");
		}
		catch (IOException e) {
		    System.out.println("IO exception.\n");
		}
		
		ArrayList<FileData> textfiles = new ArrayList<FileData>();

		File[] files = new File(args[0]).listFiles();

		for (File file : files) {
		    if (file.isFile()) {
		        textfiles.add(new FileData(file.getAbsolutePath(),file.getName()));
		    }
		}
		
		assert (!textfiles.isEmpty()):"Directory is empty";
		

		
		for (FileData x: textfiles) {
			x.fileReading();
			x.getWords();
			x.wordsfrequencies();
			x.removestopwords(stopwords);
		}
		System.out.print("-------------------------Bag Of Words----------------------------"+'\n');
		System.out.print("File\t\t");
		for(FileData x: textfiles) {
			System.out.print(x.filename+"\t");
		}
		System.out.print("\n");
		
		for(FileData x: textfiles) {
			System.out.print(x.filename+"\t");
			for(FileData y: textfiles) {
				if(x.filename.equals(y.filename)) {
					System.out.print("SAME\t\t");
				}
				else {
					Operators.bagOfWords(x,y);
				}
			}
			System.out.print("\n");
		}
	
		System.out.print("\n");
		
		System.out.print("------------------------------L C S-----------------------------"+"\n");
		System.out.print("File\t\t");
		for(FileData x: textfiles) {
			System.out.print(x.filename+"\t");
		}
		System.out.print("\n");
		
		for(FileData x: textfiles) {
			System.out.print(x.filename+"\t");
			for(FileData y: textfiles) {
				if(x.filename.equals(y.filename)) {
					System.out.print("SAME\t\t");
				}
				else {
					Operators.lCS(x,y);
				}
			}
			System.out.print("\n");
		}
		System.out.print("\n");
		
		System.out.print("------------------------Finger printing--------------------------"+"\n");
		System.out.print("File\t\t");
		for(FileData x: textfiles) {
			System.out.print(x.filename+"\t");
		}
		System.out.print("\n");		
		for(FileData x: textfiles) {
			System.out.print(x.filename+"\t");
			for(FileData y: textfiles) {
				if(x.filename.equals(y.filename)) {
					System.out.print("SAME\t\t");
				}
				else {
					Operators.fingerprinting(x,y);
				}
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
}