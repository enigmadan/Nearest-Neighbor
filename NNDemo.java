package knearest;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

public class NNDemo {

  static Scanner kbin = new Scanner(System.in);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		menu();
		
	}
	
	public static void menu(){
		displayWelcomeMessage();
		runProgram();
	}
	
	public static void displayWelcomeMessage(){
		System.out.println("=======================================================");
		System.out.println("            Welcome to Super KNN Cruncher              ");
		System.out.println("=======================================================");
		
		System.out.println("Super KNN Cruncher");	
	}
	
	public static void runProgram(){
		double perTrain;
		double perTest;
		String percent;
		String fileName;
		
		do{
			System.out.println("Please enter the percent for training use:");
			percent = kbin.next();
			perTrain = Double.parseDouble(percent);
			
			System.out.println("Please enter the percent for testing use:");
			percent = kbin.next();
			perTest = Double.parseDouble(percent);
		} while(perTrain < 0 || perTrain > 1 || perTest < 0 || perTest > 1);
		
		System.out.println("Please enter the file name to use:");
		fileName = kbin.next();
		
		ReadFile rf = new ReadFile(fileName, perTrain, perTest);
		rf.processData();
		
		NNPostProcessing pp = null;
		
		try{
		pp = new NNPostProcessing("myTrain.txt", "myTest.txt");
		} catch (FileNotFoundException e){
			e.getMessage();
		}
		
		int k = 5;
		KNNPrediction nn = new KNNPrediction(pp.trainer, pp.tester, k);
		
		nn.findNearestNeigh();
		
		NNConfuse con = null;
		try{
			con = new NNConfuse(pp.trainer, pp.tester);
			} catch (IOException e){
				e.getMessage();
			}
		
	}

}
