/*
@Author Yaswant Reddy Kadiyam
-----------------------------------------------------------------
Plagiarism project in java. Takes text files from given path.
(Folder should only contain text files) special characters have 
been removed except '_'. Empty files have handled.
-----------------------------------------------------------------  
 */

package FileReading_plagiarism;

import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

/*
--------------------------------------------------------------
@author Yaswant Kadiyam Reddy
Main class will print time stamp, Matrix form of similarities.
Every output in a log file.
---------------------------------------------------------------- */

public class MainClass {

	/*
	 -----------------------------------------------------------
	 The main class will read all files in the given directory
	 and enter the necessary data in to File data objects.
	 -----------------------------------------------------------
	 */
	
	public static void main(String[] args) {
		
		/*
		 ----------------------------------------------------------------
		 Writing to log file.
		 ---------------------------------------------------------------- 
		 */
		
	    try {
	    	FileOutputStream f = new FileOutputStream("Log.log",true);
	    	System.setOut(new PrintStream(f));
	    }
	    catch(Exception e) {
	    	System.out.println("Could not Log.");
	    }
		
	    /*
	     ---------------------------------------------------------------
	     Printing Time stamp.
	     --------------------------------------------------------------- 
	     */
	    
		String timeStamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
		System.out.println(timeStamp);
		
		/*
		 --------------------------------------------------------------
		 Reading stop words to Array list 'stopwords'. 
		 --------------------------------------------------------------
		 */
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
		
		/*
		 -----------------------------------------------------------------
		 Creating File Data objects by reading files from directory into
		 'textfiles' array list. Empty directory handled.
		 -----------------------------------------------------------------
		 */
		
		ArrayList<FileData> textfiles = new ArrayList<FileData>();

		File[] files = new File(args[0]).listFiles();

		for (File file : files) {
		    if (file.isFile()) {
		        textfiles.add(new FileData(file.getAbsolutePath(),file.getName()));
		    }
		}
		
		assert (!textfiles.isEmpty()):"Directory is empty";
		

		/**
		 -----------------------------------------------------------------------
		 Filling data in File Data Objects.
		 -----------------------------------------------------------------------
		 */
		
		for (FileData x: textfiles) {
			x.fileReading();
			x.getWords();
			x.wordsfrequencies();
			x.removestopwords(stopwords);
		}
		
		/*
		 -----------------------------------------------------------------------
		 Calculating and printing bag of words in matrix form.
		 -----------------------------------------------------------------------
		 */
		
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
		
		/*
		 ---------------------------------------------------------------------
		 Calculating and printing L.C.S in matrix form.
		 ---------------------------------------------------------------------
		 */
		
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
		
		/*
		----------------------------------------------------------------------
		Printing Finger printing in matrix form.
		----------------------------------------------------------------------
		*/
		
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