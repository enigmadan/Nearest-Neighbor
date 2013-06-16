import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * 
 * Class that creates a confusion matrix based on two ArrayLists of DataObjects
 *
 */
public class NNConfuse {
  public NNConfuse(ArrayList<DataObject> train,ArrayList<DataObject> test) throws FileNotFoundException{
		ArrayList<String> classes = new ArrayList<String>();
		ArrayList<PosNeg> confusion = new ArrayList<PosNeg>();
		for(int i = 0;i<train.size();i++){
			if(!classes.contains(train.get(i).classAtt)){
				classes.add(train.get(i).classAtt);
			}
		}
		for(int i = 0;i<test.size();i++){

			if(!classes.contains(test.get(i).classAtt)){
				classes.add(test.get(i).classAtt);
			}

		}
		for(int i = 0;i<classes.size();i++){
			confusion.add(new PosNeg(classes.get(i)));
		}
		for(int i = 0;i<confusion.size();i++){
			for(int j = 0;j<confusion.size();j++){
				confusion.get(i).ar.add(new PosNeg(confusion.get(j).className));
			}
		}

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

		PrintWriter write = new PrintWriter("myResult");
		write.print(" ,");
		for(int i=0;i<confusion.size();i++){
			write.print(confusion.get(i).className+",");
		}
		for(int i=0;i<confusion.size();i++){
			write.print("\n"+confusion.get(i).className+",");
			for(int j = 0;j<confusion.size();j++){
				if(i == j){
					write.print(confusion.get(i).number+",");
				}else{
					write.print(confusion.get(i).ar.get(j).number+",");
				}
			}
		}
		write.close();
	}
	public class PosNeg{
		ArrayList<PosNeg> ar = new ArrayList<PosNeg>();
		String className;
		int number = 0;

		public PosNeg(String cls){
			className=cls;
		}
	}
}
