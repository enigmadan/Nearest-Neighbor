package knearest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

/**
 * KNN Prediction Class
 * 
 * Classifies data objects based on k nearest neighbors algorithm.
 * 
 * @author jcseptienr
 *
 */
public class KNNPrediction {
	int k;
	ArrayList<DataObject> training;
	ArrayList<DataObject> testing;
	ArrayList<Neighbor> nearNeigh;
	HashMap<String, Integer> predict;
	HashMap<String, Double> predictWeighted;

	
	
	/** Constructor */
	public KNNPrediction (ArrayList<DataObject> tr, ArrayList<DataObject> te, int knn){
		training = tr;
		testing = te;
		nearNeigh = new ArrayList<Neighbor>();
		k = knn;
		predict = new HashMap<String, Integer>();
	}
	
	/**
	 * Find k nearest neighbors for all test objects
	 * 
	 * Pre: two list containing the training and testing
	 * data objects and k the number of near neighbors for
	 * classification
	 * 
	 * Post: each of the testing data objects is classified 
	 * based on nearest neighbors
	 * 
	 */
	public void findNearestNeigh(){
		for (int i = 0; i < testing.size(); i++){
			DataObject test = testing.get(i);
			
			for(int j = 0; j < training.size();j++){
				DataObject train = training.get(j);
				
				double dist = euclideanDistance(train, test);
				nearNeigh.add(new Neighbor(dist, train));
			}
			
			trimNeighbors();
			predictMajority(test);
			nearNeigh.clear();
		}
	}
	
	/**
	 * Find k nearest neighbors for all test objects
	 * 
	 * Pre: two list containing the training and testing
	 * data objects and k the number of near neighbors for
	 * classification
	 * 
	 * Post: each of the testing data objects is classified 
	 * based on nearest neighbors
	 * 
	 */
	public void findNearestWeighted(){
		for (int i = 0; i < testing.size(); i++){
			DataObject test = testing.get(i);
			
			for(int j = 0; j < training.size();j++){
				DataObject train = training.get(j);
				
				double dist = euclideanDistance(train, test);
				nearNeigh.add(new Neighbor(dist, train));
			}
			
			trimNeighbors();
			predictWeighted(test);
			nearNeigh.clear();
		}
	}
	
	/**
	 * Calculates the Euclidean Distance between two objects
	 * 
	 * Distance is calculated by the squared root of the attributes' difference 
	 * squared summation.
	 * 
	 * @param tnObj
	 * @param ttObj
	 * @return distance
	 */
	private double euclideanDistance(DataObject tnObj, DataObject ttObj){
		
		double distance = 0;
		double squaredSum = 0;
		
		int numOfAtt = tnObj.attributes.size();


		for (int i = 0; i < numOfAtt; i++){
			double diff = tnObj.attributes.get(i).doubleValue() 
										- ttObj.attributes.get(i).doubleValue();
			squaredSum += diff * diff;
		}
		
		distance = Math.sqrt(squaredSum);
		
		return distance;
	}
	
	/**
	 * Trims the near neighbors list nearest k data objects
	 * 
	 * Pre: a list containing the neighbors of a data object
	 * Post: a list containing k nearest data objects
	 */
	private void trimNeighbors(){
		Collections.sort(nearNeigh);
		int size = nearNeigh.size();
		nearNeigh.subList(k - 1, size).clear();
		nearNeigh.trimToSize();
	}
	
	/**
	 * Classifies a data object
	 * 
	 * Based on the k nearest neighbors class, classifies data object
	 * Pre: Data object without predicted classification
	 * Post: Data object predicted class is set based on the class of neighbors
	 * 
	 * @param o DataObject
	 */
	private void predictMajority(DataObject o){
		Neighbor n;
		for ( int i = 0; i < nearNeigh.size(); i++){
			n = nearNeigh.get(i);
			String att = n.neighbor.classAtt;
			 
			if (!predict.containsKey(att))
				 predict.put(att, new Integer(1));
			 else {
				 Integer val = predict.get(att);
				 val = Integer.valueOf(val.intValue() + 1);
				 predict.put(att, val);
			 }		 
		}
		
		String[] allAttr = predict.keySet().toArray(new String[0]);
		
		String majority = null;
		int maxNumInst = 0;
		
		for(int j = 0; j < allAttr.length; j++){
			int curNumInst = predict.get(allAttr[j]).intValue();
			if(curNumInst > maxNumInst){
				majority = allAttr[j];
				maxNumInst = curNumInst;	
			}
		}
		
		o.classPredict = new String(majority);
		
		predict.clear();
	}
	
	/**
	 * Classifies a data object
	 * 
	 * Based on the k nearest neighbors class, classifies data object
	 * Pre: Data object without predicted classification
	 * Post: Data object predicted class is set based on the weighted class of neighbors
	 * 
	 * @param o DataObject
	 */
	private void predictWeighted(DataObject o){
		Neighbor n;
		for ( int i = 0; i < nearNeigh.size(); i++){
			n = nearNeigh.get(i);
			String att = n.neighbor.classAtt;
			
			double distance = n.dist;
			double weight = 1 / distance;
			 
			if (!predictWeighted.containsKey(att))
				 predictWeighted.put(att, new Double(weight));
			 else {
				 Double val = predictWeighted.get(att);
				 val = Double.valueOf(val.doubleValue() + weight);
				 predictWeighted.put(att, val);
			 }		 
		}
		
		String[] allAttr = predictWeighted.keySet().toArray(new String[0]);
		
		String greatest = null;
		double maxWeight = 0;
		
		for(int j = 0; j < allAttr.length; j++){
			double curWeight = predictWeighted.get(allAttr[j]).doubleValue();
			if(curWeight > maxWeight){
				greatest = allAttr[j];
				maxWeight = curWeight;
			}
		}
		
		o.classPredict = new String(greatest);
		
		predict.clear();
	}
}
