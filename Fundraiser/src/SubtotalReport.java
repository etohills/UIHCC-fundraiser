/*
 * 
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.text.*;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.io.PrintWriter;

public	class SubtotalReport {
	
	//declare variables
	static int l;
	static String iString;	  //generic input string
	static int[] MajorCount = new int[13];
	static String iStudentID;	//first and last name
	static String iStudentName;	//Student name
	static String iGender;		// the gender for the students
	static char cGender;
	static String oGenders ;
	static String iMajors;		//the majors for the program
	static int cMajors;
	static double nDonation;
	static Map<Double,Integer> map = new HashMap<Double,Integer>();
	static String iDonation;	//the donation amount for the program 
	static double dDonation;	//for converting string to double  
	static double tDonation;
	static String iDates;		// the date that is use
	
	static String oMajStudentCtr;	// sub total Student counter
	static String oMajDonation;		// sub total donation
	static String oMajAvg;			//sub total average
	static String oGtStudentCtr;	//grand total student counter
	static String oGtDonation;	// grand total donation
	static String oErrCtr;		// totals error counter
	static String  oMajorCode;		// output for major codes
	static String record;		//the whole record
	static int hMajor;		//holding field for my major
	static String oGtAvg;		//grand total average
	static int iDate;	//the date on which the student hand in there donation account
	static int oDate;	// date formatted
	static int iMajor;	// the major for the students
	static int iStudentIDs;		//students Id
	static double cDonation;	// calculated donation account
	static double cMajStudentCtr;	//sub total student counter
	static double cMajDonation;		//sub total donation
	static double cMajAvg;		//sub total average per student
	static double cGtStudentCtr;	//calculated grand total student counter
	static double cGtDonation;	//calculated grand total donation
	static double cGtAvg;		//calculated grand total aver per students
	static double cErrCtr;		//error counter
	static double oGtStudentCtrs = 00;	//accumulator for student counter
	static double cGtDonations = 0;	//accumulator for my donations
	static double cGtAvgs = 0;	//accumulator for grand total average
	static int students;
	static boolean nMajor = false;
	static Scanner MajorScanner;	//input device to read from .dat file
	static PrintWriter pw;		//used to write data to the .prt file
	
	static boolean eof = false;		//used to control user loop 
	static Character ErrorSwitch = 'Y';	// error switch equals yes
	static int cStudentCtr = 0;	//counter for number of employees
	static double cEmpPayTot = 0;	//accumulator for employees total"
	static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YYYY");
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	static Date date = new Date(); 
	static NumberFormat nf;		//use to format currency
	
	
	public static void main(String[] args) {
		
		
		//call init()
		init();
		
	
	
		
		//loop until no more records
		while(!eof) {
			
			//call validation
			//validation();
			
		
			//Majors();
				try {cMajors = Integer.parseInt(iMajors);
				}
			catch(Exception e) { System.out.println("Majors input");
				
			}
				if (hMajor!= cMajors) {
					Subtotal();
					nDonation = 0;
				}	
				//call output
				output();
				calcs();
				
				
			input();
			//call calcs()
		}
		MajorCount[0] = 3;
		Subtotal();
		Grandtotals();
	
	
		
		//close the print writer
		pw.close();
		
		
		System.out.println("Program ending, have a good DAY!");
	}

	public static void init() {
		hMajor = cMajors;
		
		//set scanner to the input file
		try {
			MajorScanner = new Scanner(new File("IHCCFUND.DAT"));
			MajorScanner.useDelimiter(System.getProperty("line.separator"));
		} catch (FileNotFoundException e) {
			
			System.out.println("File error");
			System.exit(1);
		}
	
		//initialize the PrintWritter object
		try {
			pw = new PrintWriter(new File ("subtotal.prt"));
		} catch (FileNotFoundException e) {
			System.out.println("Output file error");
		}
		
		
		input();
	
	
		
		
		//System.out.println(iMajor);
		//System.out.println(hMajor);
		
		//writer column headings
	
		pw.format("%-65s%4s%63s%n", "DATE: " + formatter.format(date), "IHCC", " ");
		
		pw.format("%-57s%18s%56s%n", " ", "FUNRAISING REPORT", " ");
		pw.println();
		
		pw.format( "%-7s%18s%6s%15s%25s%15s%25s%n", "STUDENT ID:  ", " " , " GENDER: " ,     "    "    ,  "MAJOR: ", "" , "DONATION");
		pw.println();
	}
	
	/*public static void students() {
		//int x = 0;
		//System.out.format("%-10s",formatter.format(date));
		pw.format( "%-30s%5s%25s%25s%25s%15s%10s%n", "STUDENT ID:  ", " " , " GENDER: " ,     "    "    ,  "MAJOR: ", "" , "DONATION");
		
		
	}*/
	
	
	public static void input() {
		
		 //string used to hold entire record being read
		//as long as there are more records to read
		if (MajorScanner.hasNext()) {
			
			record = MajorScanner.next();
			
			iStudentID = record.substring(0, 7);
			
			iGender = record.substring(7, 8);
			cGender = iGender.charAt(0);
			
			iMajors = record.substring(8, 10);
			
			iDonation = record.substring(10, 17);

			try {
				dDonation=Double.parseDouble(iDonation);
				
				} catch (NumberFormatException e) {
					
				}
		}
		else {		
			eof=true; //no more records set to true
	
		}
	}
	
	
	public static void calcs() {
		tDonation += dDonation;
		nDonation += dDonation;
	}
	//count = count + 1; - Long Way
	
	public static void output() {
		iMajor = Integer.parseInt(iMajors);
		nMajor = true;
		if(iMajor == 1) {
			MajorCount[0] += 1;
		oMajorCode= "COMPUTER SOFTWARE DEVELOPMENT ";
		hMajor = cMajors;
		}
		if(iMajor == 2) { 
		oMajorCode= "DIESEL POWER SYSTEMS TECHNOLOGY";
		MajorCount[1] += 1;
		//nDonation.add(dDonation);
		hMajor = cMajors;
		}
		if(iMajor == 3) { oMajorCode= "AUTOMOTIVE TECHNOLOGY";
		MajorCount[2] += 1;
		////nDonation.add(dDonation);
		hMajor = cMajors;
		}
		if(iMajor == 4) { oMajorCode= "LASER/ELECTRO-OPTICS TECHNOLOGY";
		MajorCount[3] += 1;
		//nDonation.add(dDonation);
		hMajor = cMajors;
		}
		if(iMajor == 5) { oMajorCode= "ROBOTICS/AUTOMATION TECHNOLOGY";
		MajorCount[4] += 1;
		//nDonation.add(dDonation);
		hMajor = cMajors;
		}
		if(iMajor == 6) { oMajorCode= "DIGITAL FORENSICS";
		MajorCount[5] += 1;
		//nDonation.add(dDonation);
		hMajor = cMajors;
		}
		if(iMajor == 7) { oMajorCode= "MACHINE TECHNOLOGY";
		MajorCount[6] += 1;
		//nDonation.add(dDonation);
		hMajor = cMajors;
		}
		if(iMajor == 8) { oMajorCode= "GEOSPATIAL TECHNOLOGY";
		MajorCount[7] += 1;
		//nDonation.add(dDonation);
		hMajor = cMajors;
		}
		if(iMajor == 9) { oMajorCode= "ADMINSTRATIVE ASSISTANT";
		MajorCount[8] += 1;
		//nDonation.add(dDonation);
		hMajor = cMajors;
		}
		if(iMajor == 10) { oMajorCode= "ACCOUNTING ASSISTANCE";
		MajorCount[9] += 1;
		///nDonation.add(dDonation);
		hMajor = cMajors;
		}
		if(iMajor == 11) { oMajorCode= "WELDING TECHNOLOGY";
		MajorCount[10] += 1;
		//nDonation.add(dDonation);
		hMajor = cMajors;
		}
		if(iMajor == 12) { oMajorCode= "AUTOMOTIVE COLLISION TECHNOLOGY";
		MajorCount[11] += 1;
		//nDonation.add(dDonation);
		hMajor = cMajors;
		}
		if(iMajor == 13) { oMajorCode= "AVAIATION PILOT TRAINING";
		MajorCount[12] += 1;
		//nDonation.add(dDonation);
		hMajor = cMajors;
		}
		switch(cGender) {
		case 'F': oGenders= "Female";
		break;
		case 'M': oGenders= "MALE";
		
		}
	//write detail line
	//pw.format("%-7s%5s%40s%5s%1s%5s%35s%5s%2s%5s%8s%4s%10s%n", iStudentID, " ", //iStudentName, " ", iGender, " ", oMajorCode, " ", " ", iDonation, " ", iDates);
		pw.format( "%-7s%5s%25s%20s%30s%20s%10s%n",iStudentID , " " , oGenders ,     "    "    , oMajorCode,"  ", dDonation );
		students++;
		//pw.flush();
		/*(for ( Double key : maptest.keySet() )
        {
            System.out.println( maptest.get( key ) );
        }*/
	}
	
	public static void Subtotal() {
		if(nMajor) {
		//this is for my SubTotals break
		DecimalFormat dfcMajStudentCtr = new DecimalFormat ("00");
		DecimalFormat dfcMajDonation = new DecimalFormat ("$##,###.00");
		DecimalFormat dfcMajAvg = new DecimalFormat ("$##,###.00");
		
		//format total fields with the above formatters
		String oMajStudentCtr = dfcMajStudentCtr.format(cGtStudentCtr);
		String oMajDonation = dfcMajDonation.format(nDonation);
		String oMajAvg = dfcMajAvg.format(cGtAvg);
		int p = Integer.parseInt(iMajors);
		//System.out.print(p);
		pw.println();
		//if(iMajor==1) {MajorCount[0]++;System.out.println("hello");}
		pw.format("%-1s%34s%10s%5s%1s%15s%5s%5s%5s%8s%15s%n","SUBTOTALS:", oMajorCode, " ", "RECORDS:", " ", MajorCount[p], " ", "DONATION AMOUNT:", " ", oMajDonation, " ");
		System.out.println(MajorCount[p]);
		pw.println();
		}
		}
	
	private static void Grandtotals() {
		//Create decimal formatters
		DecimalFormat dfcGtStudentCtr = new DecimalFormat ("00");
		DecimalFormat dfcGtDonation = new DecimalFormat ("$##,###.00");
		DecimalFormat dfcGtAvg = new DecimalFormat ("$##,###.00");
		
		//format total fields with the above formatters
		String oGtStudentCtr = dfcGtStudentCtr.format(cGtStudentCtr);
		String ttDonation = dfcGtDonation.format(tDonation);
		String oGtAvg = dfcGtAvg.format(cGtAvg);
		pw.println();
		pw.println();
		pw.format("%-1s%8s%10s%5s%1s%47s%19s%15s%n", "GRAND TOTALS:", " ", "STUDENTS:", " ", students, " ", "DONATION AMOUNT:",  ttDonation);
	}

	

	


}