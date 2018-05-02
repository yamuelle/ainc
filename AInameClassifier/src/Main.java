
import ai.InputNeuron;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yannikmuller
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       ai.NeuralNet n = new ai.NeuralNet(16);
       ArrayList<InputNeuron> in = new ArrayList<>();
       
       in.add(n.addInput());
       in.add(n.addInput());
       in.add(n.addInput());
       in.add(n.addInput());
       
       n.addHiddenOne();
       n.addHiddenOne();
       
       n.addHiddenTwo();
       n.addHiddenTwo();
       
       n.addOutput();
       n.addOutput();
       
      n.randomizeWeights(5);
       //for(Double d : n.weights){
         //  System.out.println(d);
       //}
       n.createFullMesh();
       
       
       int index = 1;
       for(InputNeuron inp : in){
           inp.setInput(index++);
       }
       //System.out.println(n.print());
       double[] out = n.compute();
       System.out.println(out.length);
       System.out.println("Inputs");
       for(InputNeuron inp : in){
           System.out.println(inp.getValue());
       }
       System.out.println("Weights :");
       for(Double d : n.weights){
           if(d>=0)System.out.println(d);
           else{
               System.out.println(d);
           }
       }
       System.out.println("Outputs :");
       for(int i = 0;i<out.length;i++){
           System.out.println(out[i]);
           //System.out.println(out.length);
           
       }
       
       
       
    }
    
}
