/*
@Author Yaswant Reddy Kadiyam
-----------------------------------------------------------------
Plagiarism project in java
-----------------------------------------------------------------  
 */

package FileReading_plagiarism;

/*
 ----------------------------------------------------------------
 The package will have FileData Class which will have all data for
 each file necessary for plagiarism calculation. 
 ----------------------------------------------------------------
 */

import java.util.*;
import java.io.*;

public class FileData {
	
	protected boolean isfileempty;
	protected boolean issmall;
	protected String filename;
	protected String filepath;
	protected String filewords;
	protected String[] words;
	protected HashMap<String,Integer> wordFreqs;
	protected String filechars;
	protected int[] hash1;
	ArrayList<Integer> pmod;
	
	FileData(String filepath,String filename){
		
		this.isfileempty = true;
		this.filename = filename;
		this.filepath = filepath;
		this.filewords = "";
		this.wordFreqs = new HashMap<>();
		this.filechars = "";
		this.issmall = false;
		this.pmod = new ArrayList<Integer>();
		
	}
	
	public void fileReading(){
		BufferedReader br;
		FileInputStream fin;
		try {
			fin = new FileInputStream(this.filepath);
		    br = new BufferedReader(new InputStreamReader(fin));
		    String line = br.readLine();
		    while (line != null) {       
		    	line = line.replaceAll("[^\\w+ ]", "");
		    	line = line.replaceAll("\\W+", " ");
		    	line = line.toLowerCase();
		    	this.filewords = this.filewords+line+" ";
		    	line = br.readLine();
		    }
		    fin.close();
		    br.close();
		}
		catch (FileNotFoundException e) {
		    System.out.println("File not found.\n");
		}
		catch (IOException e) {
		    System.out.println("IO exception.\n");
		}
		if(this.filewords.equals("")){}
		else {
			this.isfileempty = false;
		}
	}
	
    public void getWords() {
		if(!(this.isfileempty)) {	
			this.words=this.filewords.split(" ");
		}		
	}
    
    public void wordsfrequencies() {  	
    	if(!(this.isfileempty)) {
	    	for (String s:this.words){
	    		if(!(wordFreqs.containsKey(s))) {
	    			wordFreqs.put(s,1);
	    		}
	    		else {
	    			wordFreqs.put(s,1+wordFreqs.get(s));
	    		}
	    	}
    	}    	
    }
    
    public int hasher(String string1,int pin){
		int h = 0;
		for(int i = 0;i<5;i++)
		{
			h += ((int)string1.charAt(pin+i))*Math.pow(5,(5-i));
		}
		return h;
	}
    
    public void removestopwords(ArrayList <String> stopword) {
    	if(!(this.isfileempty)) {
    		for(String x: this.words) {
        		if(!(stopword.contains(x))) {
        			this.filechars = this.filechars + x;
        		}
        	}
    				if((this.filechars.length()<5))
    				{
    					this.issmall = true;
    				}
    				else {
    					this.hash1 = new int[this.filechars.length()-4];
    					for(int i =0;i<this.hash1.length;i++)
    					{
    						this.hash1[i] = this.hasher(this.filechars,i);
    					}
    					for(int i=0;i<this.hash1.length;i++) {
    						if(this.hash1[i]%1==0) {
    							this.pmod.add(this.hash1[i]);
    						}
    					}
    				}
    	}
    }    
}