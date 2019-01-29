
/*
 * 
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.text.*;
import java.util.*;
import java.io.PrintWriter;

public	class SUMMARYREPORT {
	
	//declare variables
	static String iString;	  //generic input string
	static int Counter = 0;
	static String iStudentID;	//first and last name
	static String iStudentName;	//Student name
	static String iGender;		// the gender for the students
	static String oGenders ;
	static String iMajors;		//the majors for the program
	static String iDonation;	//the donation amount for the program 
	static double dDonation;	//for converting string to double  
	static String iDates;		// the date that is use
	static double overallAmount = 0;
	static char CiGender;
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
	
	static double cGtStudentCtr;	//calculated grand total student counter
	static double cGtDonation;	//calculated grand total donation
	
	
	
	static double cGtDonations = 0;	//accumulator for my donations
	static double cGtAvgs = 0;	//accumulator for grand total average
	static Scanner MajorScanner;	//input device to read from .dat file
	static PrintWriter pw;		//used to write data to the .prt file
	
	static boolean eof = false;		//used to control user loop 
	static Character ErrorSwitch = 'Y';	// error switch equals yes
	static int cStudentCtr = 0;	//counter for number of employees
	static double cEmpPayTot = 0;	//accumulator for employees total"
	static SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/YYYY");
	static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	static Date date = new Date(); 
	static NumberFormat nf;		//use to format currency
	static String InformationTech;
	static String ManufacturingTech;
	static String TransportationTech; 
	//All counters Combinations
	static int Malecntr = 0, 
			femalecntr = 0,
			ITCntr = 0,
			MFCntr = 0, 
			TTCntr = 0,
			maleinforamtionTechCntr = 0,
			femaleinforamtionTechCntr = 0,
			malemanufacturingTechCntr = 0,
			femalemanufacturingTechCntr = 0,		
			femaletransportationTechCntr = 0,
			maletransportationTechCntr = 0
			;
	// All combinations For Amount Raised 
	
	static double cMaleAmtRaised = 0.0, cFemaleAmtRaised = 0.0, ITMaleAmtRaised = 0.0, ITFemaleAmtRaised = 0.0, MFMaleAmtRaised = 0.0, MFFemaleAmtRaised = 0.0, TTMaleAmtRaised = 0.0, TTFemaleAmtRaised = 0.0, ITAmtRaised = 0.0,MFAmtRaised = 0.0, TTAmtRaised = 0.0;
	static double cMaleAvrgContri = 0.0, cFemaleAvrgContri = 0.0, ITMaleAvrgContri = 0.0, ITFemaleAvrgContri = 0.0, MFMaleAvrgContri = 0.0, MFFemaleAvrgContri = 0.0, TTMaleAvrgContri = 0.0, TTFemaleAvrgContri = 0.0, ITAvrgContri = 0.0, MFAvrgContri = 0.0, TTAvrgContri = 0.0;
	
	
	
	//All Combin'ation for Avrage Contribution
	//static double 
	
	
	public static void main(String[] args) {
		
		
			
	
		//call init()
		init();
		
		
		
		 
		 
		 
		//loop until no more records
		while(!eof) {
			
			input();
			
			
		
			
		
	
				
				
				
							//printing prt
		
	
			calcs();
				}
					PrintReport();
				//call calcs()	
	
			
				overall();
	
		
		
	
	
		
		
		
		
		//close the print writer
		pw.close();
		
		
		System.out.println("Program ending, have a good one!");
		
	

		
		
	}
	public static void init() {
		
		
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
			pw = new PrintWriter(new File ("summary.prt"));
		} catch (FileNotFoundException e) {
			System.out.println("Output file error");
		}
		
		
		input();
		//writer column headings
		
			pw.format("%-65s%4s%63s%n", "DATE: " + formatter.format(date), "IHCC", " ");
			
			pw.format("%-57s%18s%56s%n", " ", "FUNRAISING REPORT", " ");
		
		
	
	}		
		//writer column headings
	
	
	
	
	
	public static void input() {
		
		 //string used to hold entire record being read
		//as long as there are more records to read
		if (MajorScanner.hasNext()) {
			
			record = MajorScanner.next();
			
			iStudentID = record.substring(0, 7);
			
			iGender = record.substring(7, 8);
			CiGender = iGender.charAt(0);
			iMajors = record.substring(8, 10);
			
			iDonation = record.substring(10, 17);

			try {
				dDonation=Double.parseDouble(iDonation);
				
				} catch (NumberFormatException e) {
					
				}
			//calcs();
		}
		else {		
			eof=true; //no more records set to true
	
		}
	}
	
	
	public static void calcs() {
		//calculate MALE counter
		Counter += 1;
		if(dDonation > 500) {
		int intMajors = Integer.parseInt(iMajors);
		if(intMajors == 1 || intMajors == 6 || intMajors == 8 || intMajors == 9 || intMajors == 10) {
			ITCntr += 1;
			ITAmtRaised += dDonation;
			ITAvrgContri = ITAmtRaised/ITCntr;
		} else if (intMajors == 4 || intMajors == 5 || intMajors == 7 || intMajors == 11) {
			MFCntr += 1;
			MFAmtRaised += dDonation;
			MFAvrgContri = MFAmtRaised/MFCntr;
		} else if (intMajors == 2 || intMajors == 3 || intMajors == 12 || intMajors == 13) {
			TTCntr += 1;
			TTAmtRaised += dDonation;
			TTAvrgContri = TTAmtRaised/TTCntr;
		}
		if(CiGender == 'M' & dDonation > 500) {
		if(intMajors == 1 || intMajors == 6 || intMajors == 8 || intMajors == 9 || intMajors == 10) {
			maleinforamtionTechCntr += 1;
			ITMaleAmtRaised += dDonation;
			ITMaleAvrgContri = ITMaleAmtRaised/maleinforamtionTechCntr;
		} else if (intMajors == 4 || intMajors == 5 || intMajors == 7 || intMajors == 11) {
			malemanufacturingTechCntr += 1;
			MFMaleAmtRaised += dDonation;
			MFMaleAvrgContri = MFMaleAmtRaised/malemanufacturingTechCntr;
		} else if (intMajors == 2 || intMajors == 3 || intMajors == 12 || intMajors == 13) {
			maletransportationTechCntr += 1;
			TTMaleAmtRaised += dDonation;
			TTMaleAvrgContri = TTMaleAmtRaised/malemanufacturingTechCntr;
		}
		Malecntr  = Malecntr +  1 ;
		cMaleAmtRaised += dDonation;
		overallAmount += dDonation;
		cMaleAvrgContri = cMaleAmtRaised/Malecntr;
		
		} else if (CiGender == 'F' & dDonation > 500) {
			if(intMajors == 1 || intMajors == 6 || intMajors == 8 || intMajors == 9 || intMajors == 10 && CiGender == 'F') {
				femaleinforamtionTechCntr += 1;
				ITFemaleAmtRaised += dDonation;
				ITFemaleAvrgContri = ITFemaleAmtRaised/femaleinforamtionTechCntr;
			} else if (intMajors == 4 || intMajors == 5 || intMajors == 7 || intMajors == 11) {
				femalemanufacturingTechCntr += 1;
				MFFemaleAmtRaised += dDonation;
				MFFemaleAvrgContri = MFFemaleAmtRaised/femalemanufacturingTechCntr;
			} else if (intMajors == 2 || intMajors == 3 || intMajors == 12 || intMajors == 13) {
				femaletransportationTechCntr += 1;
				TTFemaleAmtRaised += dDonation;
				TTFemaleAvrgContri = TTFemaleAmtRaised/femalemanufacturingTechCntr;
			}
			femalecntr = femalecntr + 1;
			cFemaleAmtRaised += dDonation;
			overallAmount += dDonation;
			cFemaleAvrgContri = cFemaleAmtRaised/femalecntr;
		}
		}
		//calculate FEMALE counter
				
		
	
		//calculated the donation account
		cMajDonation = cMajDonation + cDonation;
		//calculated state tax
			//cMajAvg = cMajDonation / cMajStudentCtr;
		//multiply FICA Tax
		cGtStudentCtr = cGtStudentCtr + cMajStudentCtr;
		//calculate total tax
		cGtDonation = cGtDonation + cMajDonation;
		//calculate net pay
			///cGtAvg = cGtDonation /cGtStudentCtr ;
		
	}
	//count = count + 1; - Long Way
	
	public static void PrintReport() {
		pw.println();
		DecimalFormat dfDonation = new DecimalFormat ("$##,###.00");
		
		//format total fields with the above formatters
		String maleDonation = dfDonation.format(cMaleAmtRaised);
		String femaleDonation = dfDonation.format(cFemaleAmtRaised);
		String maleavgDonation = dfDonation.format(cMaleAvrgContri);
		String femaleavgDonation = dfDonation.format(cFemaleAvrgContri);
		String ITDonation = dfDonation.format(ITAmtRaised);
		String ITavgDonation = dfDonation.format(ITAvrgContri);
        //v  s   v  s  v  
pw.format("%-23s%5s%15s%5s%15s%5s%5s%n", "Male : ", Malecntr ,"    ", "AMOUNT RAISED: " , maleDonation,   " " ,      "Average Contribution: "+maleavgDonation);
pw.format("%-23s%5s%15s%5s%15s%5s%5s%n", "Female : ", femalecntr ,"    ", "AMOUNT RAISED: " , femaleDonation,   " " ,      "Average Contribution: "+maleavgDonation);
pw.format("%-23s%5s%15s%5s%15s%5s%5s%n", "IT : ", ITCntr ,"    ", "AMOUNT RAISED: " , ITDonation,   " " ,      "Average Contribution: "+ITavgDonation);
pw.format("%-23s%5s%15s%5s%15s%5s%5s%n", "Manufacturing : ", MFCntr ,"    ", "AMOUNT RAISED: " , dfDonation.format(ITAmtRaised),   " " ,      "Average Contribution: "+dfDonation.format(MFAvrgContri));
pw.format("%-23s%5s%15s%5s%15s%5s%5s%n", "Transportation : ", TTCntr ,"    ", "AMOUNT RAISED: " , dfDonation.format(ITAmtRaised),   " " ,      "Average Contribution: "+dfDonation.format(TTAvrgContri));
pw.format("%-23s%5s%15s%5s%15s%5s%5s%n", "Male IT: ", maleinforamtionTechCntr ,"    ", "AMOUNT RAISED: " , dfDonation.format(ITMaleAmtRaised),   " " ,      "Average Contribution: "+dfDonation.format(ITMaleAvrgContri));
pw.format("%-23s%5s%15s%5s%15s%5s%5s%n", "Female IT: ", femaleinforamtionTechCntr ,"    ", "AMOUNT RAISED: " , dfDonation.format(ITFemaleAmtRaised),   " " ,      "Average Contribution: "+dfDonation.format(ITFemaleAvrgContri));
pw.format("%-23s%5s%15s%5s%15s%5s%5s%n", "Male Manufacturing: ", malemanufacturingTechCntr ,"    ", "AMOUNT RAISED: " , dfDonation.format(MFMaleAmtRaised),   " " ,      "Average Contribution: "+dfDonation.format(MFMaleAvrgContri));
pw.format("%-23s%5s%15s%5s%15s%5s%5s%n", "Female Manufacturing: ", femalemanufacturingTechCntr ,"    ", "AMOUNT RAISED: " , dfDonation.format(MFFemaleAmtRaised),   " " ,      "Average Contribution: "+dfDonation.format(MFFemaleAvrgContri));
pw.format("%-23s%5s%15s%5s%15s%5s%5s%n", "Male Transporation: ", maletransportationTechCntr ,"    ", "AMOUNT RAISED: " , dfDonation.format(TTMaleAmtRaised),   " " ,      "Average Contribution: "+dfDonation.format(TTMaleAvrgContri));
pw.format("%-23s%5s%15s%5s%15s%5s%5s%n", "Female Transporation: ", femaletransportationTechCntr ,"    ", "AMOUNT RAISED: " , dfDonation.format(TTFemaleAmtRaised),   " " ,      "Average Contribution: "+dfDonation.format(TTFemaleAvrgContri));



pw.println();
		
	
	

	
	}
	
	
	
	
	private static void overall() {

		DecimalFormat dfDonation = new DecimalFormat ("$##,###.00");
		//this is first System.out.println(Counter);
		// this is second System.out.println(overallAmount);
		// this is third average System.out.println(overallAmount/Counter);
		pw.format( "%-30s%5s%25s%5s%25s%n", "Overall:"+Counter, " ", "Amount Raised:"+dfDonation.format(overallAmount)," ",  "Average Contribution:"+dfDonation.format(overallAmount/Counter));
		
		
	}

	

	


}
	
	

