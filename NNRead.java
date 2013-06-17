import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.Arrays;
import java.util.*;




public class NNRead {
	private String path;
	private int numOfElements;
	private double trainingPercent;
	private double testingPercent;
	
	ArrayList<String> data;
	Random rgen = new Random();
	
	int counter = 0;

	
	/** Constructor */
	
	public NNRead(String filePath, double perTrain, double perTest){
		this.path = filePath;
		this.trainingPercent = perTrain;
		this.testingPercent = perTest;	
		this.data = new ArrayList<String>();
	}
	
	public void processData(){
		try {
			processFile();
		} catch (IOException e){
			e.getMessage();
		}
		
		makeDataSets();
	}
	
	/**
	* Reach data from raw file.
	* Read each data object into an array for further processing.
	* 
	*/
	private void processFile() throws IOException {
	
		BufferedReader textReader = new BufferedReader(new FileReader(this.path));		

		while( textReader.read() == '%' || textReader.read() == '@' || textReader.readLine() == null)
			textReader.readLine();
		
		String line = textReader.readLine();
		
		while(  line != null){
			data.add(line);
			numOfElements++;
			line = textReader.readLine();
		}
		
		Collections.shuffle(data);	
		
		textReader.close();	
		
	}
	
	
	private void makeDataSets(){
		try{
			createFile("myTrain", trainingPercent);
		} catch (IOException e){
			e.getMessage();
		}
		
		try{
			createFile("myTest", testingPercent);
		} catch (IOException e){
			e.getMessage();
		}
	}
	
	/**
	 * Create training/testing file from the data array using BufferedWritter class.
	 * The strings returned are randomly selection without replacement.
	 * <p>
	 * Pre: data String array
	 * Post: File created 'fileName'.txt.
	 */
	private void createFile(String fileName, double percent) throws IOException{
		BufferedWriter textWriter = new BufferedWriter(new FileWriter (fileName + ".txt"));

		int size = (int)Math.round(data.size()*percent) - 1;

		List<String> l = data.subList(counter, counter + size);
		ListIterator<String> li = l.listIterator();
		counter = size - 1;

		
		String tx = null;
		
		while (li.hasNext()){
			tx = li.next();
			if(tx != null){
				textWriter.write(tx);
				textWriter.newLine();
			}
		}
		
		textWriter.close();
	}
}
