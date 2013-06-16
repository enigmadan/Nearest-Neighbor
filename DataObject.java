import java.util.ArrayList;

public class DataObject {
  public String classAtt;
	public String classPredict;
	public Boolean correct;
	ArrayList<Double> attributes = new ArrayList<Double>();
	
	//constructor add the actual class and the attributes
	public DataObject(String s, ArrayList<Double> ar){
		classAtt = s;
		attributes = ar;
	}

	//set the class prediction
	public void setPrediction(String prediction) {
		classPredict = prediction;
		if (classAtt.equals(classPredict)) {
			correct = true;
		}
		else {
			correct = false;
		}
	}
}
