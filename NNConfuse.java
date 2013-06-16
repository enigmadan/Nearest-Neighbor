import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * Class that creates a confusion matrix based on two ArrayLists of DataObjects
 *
 */
public class NNConfuse {
	public NNConfuse(ArrayList<DataObject> train,ArrayList<DataObject> test) throws IOException{
		//initialize ArrayLists
		ArrayList<String> classes = new ArrayList<String>();
		ArrayList<PosNeg> confusion = new ArrayList<PosNeg>();
		//what are all the classes?
		for(int i = 0;i<train.size();i++){
			if(!classes.contains(train.get(i).classAtt)){
				classes.add(train.get(i).classAtt);
			}
		}
		//even more possible classes
		for(int i = 0;i<test.size();i++){
			if(!classes.contains(test.get(i).classAtt)){
				classes.add(test.get(i).classAtt);
			}
		}
		
		//fill arraylist with classes
		for(int i = 0;i<classes.size();i++){
			confusion.add(new PosNeg(classes.get(i)));
		}
		
		//fill array incorrect guess list with classes
		for(int i = 0;i<confusion.size();i++){
			for(int j = 0;j<confusion.size();j++){
				confusion.get(i).ar.add(new PosNeg(confusion.get(j).className));
			}
		}
		
		//check if class actually matches prediction and put
		//in correct position in confusion matrix
		for(int i=0;i<test.size();i++){
			for(int j=0;j<confusion.size();j++){
				if(confusion.get(j).className.equals(test.get(i).classAtt)){
					if(test.get(i).classAtt.equals(test.get(i).classPredict)){
						confusion.get(j).number++;
					}else{
						for(int k = 0;k<confusion.size();k++){
							if(confusion.get(k).className.equals(test.get(i).classPredict)){
								confusion.get(j).ar.get(k).number++;
							}
						}
					}
				}
			}
		}
		
		//print first row classes
		FileWriter writer = new FileWriter("myResult");
		writer.write(" ,");
		for(int i=0;i<confusion.size();i++){
			writer.write(confusion.get(i).className+",");
		}
		
		//print everything else in its place.
		for(int i=0;i<confusion.size();i++){
			writer.write("\n"+confusion.get(i).className+",");
			for(int j = 0;j<confusion.size();j++){
				if(i == j){
					writer.write(confusion.get(i).number+",");
				}else{
					writer.write(confusion.get(i).ar.get(j).number+",");
				}
			}
		}
		writer.close();
	}
	//private class posneg contains a class with its number of occurences and an arraylist of misses
	private class PosNeg{
		ArrayList<PosNeg> ar = new ArrayList<PosNeg>();
		String className;
		int number = 0;

		public PosNeg(String cls){
			className=cls;
		}
	}
}
