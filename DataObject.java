import java.util.ArrayList;
import java.util.Set;


public class DataObject {

  public String classAtt;
	public String classPredict;
	public Boolean correct;
	ArrayList<Double> attributes = new ArrayList<Double>();

	public DataObject(String s, ArrayList<Double> ar){
		classAtt = s;
		attributes = ar;
	}
	
	public void setPrediction(String prediction) {
		classPredict = prediction;
		if (classAtt.equals(classPredict)) {
			correct = true;
		}
		else {
			correct = false;
		}
				
		//if classAtt = classPredict then correct = true else correct false
	}
}
