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
		String check;
		do{
			menu();
			System.out.println("Run again? Y/N");
			check = kbin.next();
		}while(check.equals("Y")||check.equals("y"));


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
			System.out.println("Please enter the percent for training use (eg .25):");
			percent = kbin.next();
			perTrain = Double.parseDouble(percent);

			System.out.println("Please enter the percent for testing use:"
			+"\n(make sure the sum of this percent and the training percent are less than or equal to 1)");
			percent = kbin.next();
			perTest = Double.parseDouble(percent);
		} while((perTrain < 0 || perTrain > 1 || perTest < 0 || perTest > 1)||(perTrain+perTest)>1);

		System.out.println("Please enter the file name to use:");
		fileName = kbin.next();

		NNRead rf = new NNRead(fileName, perTrain, perTest);
		rf.processData();

		NNPostProcessing pp = null;

		try{
			pp = new NNPostProcessing("myTrain.txt", "myTest.txt");
		} catch (FileNotFoundException e){
			e.getMessage();
		}

		System.out.println("Please enter the number of nearest neighbors to use:");
		int k = kbin.nextInt();
		System.out.println("Use weights?  Y/N");
		String checker = kbin.next();
		NNPrediction nn = new NNPrediction(pp.trainer, pp.tester, k);

		if(checker.equals("Y")||checker.equals("y")){
			nn.findNearestWeighted();
		}else{

			nn.findNearestNeigh();
		}


		NNConfuse con = null;
		try{
			con = new NNConfuse(pp.trainer, pp.tester);
		} catch (IOException e){
			e.getMessage();
		}

	}

}
