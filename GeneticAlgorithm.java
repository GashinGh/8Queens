package pkg8queen;
import java.util.*;
/**
 *
 * @author gashin gh
 */

 // Defining the class for Genetic Algorithm
public class GeneticAlgorithm {
    Chromosome[] _Pop=null;
    int _PopSize=-1;
    int _MaxGen=-1;
    int _Pc=-1;
    int _Pm=-1;
    // Get the parameters from the user
    public int SetGAParam (int PopSize, int MaxGen, byte Pc, byte Pm){
        if ( PopSize<1 || PopSize>200) { System.out.println("Error1"); return 1;}
        if (MaxGen <1 || MaxGen>10000) {System.out.println("Error2"); return 2;}
        if ( Pc<0 || Pc>100) {System.out.println("Error3"); return 3;}
        if (Pm<0 || Pm>100) {System.out.println("Error4"); return 4;}
        _PopSize=PopSize;
        _MaxGen=MaxGen;
        _Pc=Pc;
        _Pm=Pm;
        return 0;
        }
    // The next two function are used for randomly creating the chromosomes and connecting those to the Genetic Algorithm
    private int Initialize (){
        if( _PopSize==-1) { System.out.println("Error-1"); return -1;}
        _Pop=new Chromosome[_PopSize];
        for(int i=0; i<_PopSize;i++){
        _Pop[i]=InitializeChromosome();
        }
        return 0;
    }
    public Chromosome InitializeChromosome(){
        Chromosome ch=new Chromosome();
        int[] data=new int[8];
        for(int i=0; i<8; i++){
            data[i]=((int) (Math.random() * 8) )+ 1;
        }
        ch.Set(data);
        return ch;

    }
    // One of the main action in Genectic Algorithm is CrossOver which is done using the following function
    private int CrossOver (){
     if( _Pop==null) { System.out.println("Error1"); return 1;}
     if( _Pc==-1) { System.out.println("Error2"); return 2;}
    Chromosome[] temp=new Chromosome[_PopSize];
    System.arraycopy(_Pop,0,temp,0,_PopSize);
    _Pop=new Chromosome[2*_PopSize];
    System.arraycopy(temp,0,_Pop,0,_PopSize);
     int mtp[]=new int[_PopSize];
     Arrays.fill(mtp, -1);
     int c=0;
     int q;
     for(int i=0; i<_PopSize;i++){
         q=(int)(Math.random()*101);
         if(q < _Pc){
             mtp[i]=i;
             c++;
         }
     }
     int d=_PopSize;
     int j,k;

     for(int i=0;i<c/2;i++){
         j=(int)(Math.random()*_PopSize);
         k=(int)(Math.random()*_PopSize);
         while(mtp[j]==-1 || mtp[k]==-1){
          j=(int)(Math.random()*_PopSize);
          k=(int)(Math.random()*_PopSize);
     }
         Chromosome[] ch=new Chromosome[2];
         ch=CrossOver_Ch(_Pop[j],_Pop[k]);
         _Pop[d]=ch[0];
         _Pop[d+1]=ch[1];
         d++;
         mtp[j]=-1; mtp[k]=-1;
     }
     return 0;
    }
    // Helper function for CrossOver, to do the action between two singel chromosomes
    private Chromosome[] CrossOver_Ch(Chromosome a,Chromosome b) {
       Chromosome[] ch=new Chromosome[2];
       int[] c1=new int[8]; int[]c2=new int[8]; int[]d1=new int[8]; int[]d2=new int[8];
       a.Get(d1);
       b.Get(d2);
       int r=((int)(Math.random() *7) +1);
       System.arraycopy(d1,0,c1,0,r);
       System.arraycopy(d2,0,c2,0,r);
       for(int i=r;i<8;i++){
           c1[i]=d2[i];
           c2[i]=d1[i];
       }
       ch[0]=new Chromosome();
       ch[1]=new Chromosome();
       ch[0].Set(c1);
       ch[1].Set(c2);
       return ch;
    }
    // Mutation is another main action of GeneticAlgorithm that is done by the following function
    private int Mutation (){
     if( _Pop==null) { System.out.println("Error1"); return 1;}
     if( _Pm==-1) { System.out.println("Error2"); return 2;}
    Chromosome[] temp=new Chromosome[2*_PopSize];
    System.arraycopy(_Pop,0,temp,0,2*_PopSize);
    _Pop=new Chromosome[3*_PopSize];
    System.arraycopy(temp,0,_Pop,0,2*_PopSize);

    int d=_PopSize*2;
     for(int i=0; i<_PopSize;i++){
         int q=(int)(Math.random()*101);
         if(q<_Pm){
         int[] tp=new int[8];
         _Pop[i].Get(tp);
         int r=(int)(Math.random()*8);
         tp[r]=((int)(Math.random()*6))+1;
         Chromosome ch=new Chromosome();
         _Pop[d]=ch;
         _Pop[d].Set(tp);
         d++;
         }
     }
     return 0;
    }
    // The act of selection to choose beter chromosomes is done by Selection function
    private int Selection (){
        if( _Pop==null) { System.out.println("Error1"); return 1;}
        Chromosome[] temp=new Chromosome[_PopSize];
        for(int x=0;x<(3*_PopSize);x++){
            if(_Pop[x]==null){
                Chromosome ch=new Chromosome();
                int[] a={1,1,1,1,1,1,1,1};
                ch.Set(a);
            }}
        FillNull(_Pop);
        Arrays.sort(_Pop);

        System.arraycopy(_Pop,0,temp,0,_PopSize);
        _Pop=new Chromosome[_PopSize];
        System.arraycopy(temp,0,_Pop,0,_PopSize);
        return 0;
    }
      // The main function  for running the algorithm
      public int Run (Chromosome[] Result){
          SetGAParam(100, 100, (byte)40,(byte)40);
          int r = Initialize();
          if(r != 0){ System.out.println("Error1"); return 1;}
          for(int generation = 2; generation<_MaxGen; generation++){
          r = CrossOver();
          if(r!=0){ System.out.println("Error1"); return 1;}
          r = Mutation();
          if(r != 0){ System.out.println("Error1"); return 1;}
          r = Selection();
          if(r != 0){ System.out.println("Error1"); return 1;}
          }
        int[] v = new int[8];
        Chromosome ch = new Chromosome();
        _Pop[0].Get(v);
        ch.Set(v);
        Result[0]=ch;
        return 0;
}
      // Filling the null values in a Chromosomes
      private void FillNull(Chromosome[] a){
       for(int i=0; i<a.length; i++)
    {
        if(a[i] == null)
        {
        Chromosome ch=new Chromosome();
        int[] ch_d={1,1,1,1,1,1,1,1};
        a[i]=ch;
        a[i].Set(ch_d);
        }
    }
}
    }
