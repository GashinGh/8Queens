
package pkg8queen;
import java.util.*;
/**
 *
 * @author gashin gh
 */
 //Design the Chromosome class for Genetic Algorithm

public class Chromosome implements Comparable<Chromosome>{
    private int[] _Data=null;
    private int _FitVal;

    //Getting the fitval value
    public int FitVal(){
    return _FitVal;
}
// The set method is for updating the chromosome's data
    public int Set(int[] NewData){
        if(NewData.length!=8) {System.out.println("Error-2"); return -2;}
        _Data= new int[8];
        for(int i=0; i<8;i++){
            if(NewData[i]>8 || NewData[i]<1){
                System.out.println("Error" + i+1);
                return i+1;
            }
            this._Data[i]=NewData[i];
        }
        EvalFitness();
        return 0;
    }

    // copy the chromosome's
    public int Get(int[] RetData){
     if(RetData.length!=8) {System.out.println("Error-2"); return -2; }
     if(_Data==null) {System.out.println("Error-1"); return -1;}
     //RetData=_Data;
     System.arraycopy(_Data,0,RetData,0,8);
     return 0;


    }

    // calculate the FitVal based on the data
    private void EvalFitness(){
    _FitVal=0;
  for(int i=0; i<7;i++)
    for(int j=i+1;j<8;j++){
    if( _Data[i]==_Data[j]) _FitVal++;
    if(j-i==Math.abs(_Data[i]-_Data[j])) _FitVal++;
  }
    }
  // implement how to compare two instances of chromosome
  public int compareTo(Chromosome ch){

  if(_FitVal > ch.FitVal())
  return 1;
  else if(_FitVal < ch.FitVal())
  return -1;
  else
  return 0;
  }
}
