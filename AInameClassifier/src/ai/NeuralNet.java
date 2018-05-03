/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author yannikmuller
 */
public class NeuralNet {

    public ArrayList<InputNeuron> inputs;
    public ArrayList<WorkingNeuron> hidden1;
    public ArrayList<WorkingNeuron> hidden2;
    public ArrayList<WorkingNeuron> outputs;
    public ArrayList<Double> weights;

    public static int sizeOfNet;

    public NeuralNet(int totalSize) {
        sizeOfNet = totalSize;
        inputs = new ArrayList<>();
        outputs = new ArrayList<>();
        hidden1 = new ArrayList<>();
        hidden2 = new ArrayList<>();
        weights = new ArrayList<>();
    }

    public void createFullMesh() {
        if (sizeOfNet != weights.size()) {
            System.err.println("Error ! createFullMesh Error");
            System.out.println("sizeOfNet = " + sizeOfNet + "  weights.size() = " + weights.size());
            return;
        }
        int tmpCount = weights.size() - 1;

        for (WorkingNeuron o : outputs) {

            for (WorkingNeuron h2 : hidden2) {
                //System.out.println(tmpCount);
                o.addConnection(new Connection(h2, weights.get(tmpCount)));
                tmpCount -= 1;

            }
        }
        for (WorkingNeuron h2 : hidden2) {
            for (WorkingNeuron h1 : hidden1) {
                //System.out.println(tmpCount);
                h2.addConnection(new Connection(h1, weights.get(tmpCount)));
                tmpCount -= 1;
            }
        }
        for (WorkingNeuron h1 : hidden1) {
            for (InputNeuron in : inputs) {
                //System.out.println(tmpCount);
                h1.addConnection(new Connection(in, weights.get(tmpCount)));
                tmpCount -= 1;
            }
        }

    }

    public InputNeuron addInput() {
        InputNeuron newIN = new InputNeuron();
        inputs.add(newIN);
        return newIN;
    }

    public WorkingNeuron addHiddenOne() {
        WorkingNeuron newH1 = new WorkingNeuron();
        hidden1.add(newH1);
        return newH1;
    }

    public WorkingNeuron addHiddenTwo() {
        WorkingNeuron newH2 = new WorkingNeuron();
        hidden2.add(newH2);
        return newH2;
    }
    public void loadToNet(double[] inArr){
        int count = 0;
        for(InputNeuron inpn : inputs){
            inpn.setInput(inArr[count]);
            count = count +1;
        }
    }

    public WorkingNeuron addOutput() {
        WorkingNeuron newOut = new WorkingNeuron();
        outputs.add(newOut);
        return newOut;
    }

    public double[] computeAprox() {
        double[] result = new double[outputs.size()];
        int index = 0;
        for (WorkingNeuron out : outputs) {
            result[index] = out.getValue();
            index += 1;
        }
        double cool = result[0];
        double uncool = result[1];
        
        if(cool > uncool && cool > 0){
            result[0] = 1;
            result[1] = 0;
        }else {
            result[0] = 0;
            result[1] = 1;
        }
        return result;
    }
    public double[] compute(){
        double[] result = new double[outputs.size()];
        int index = 0;
        for (WorkingNeuron out : outputs) {
            result[index] = out.getValue();
            index += 1;
        }
        double cool = result[0];
        double uncool = result[1];
        
       /* if(cool > uncool){
            result[0] = 1;
            result[1] = 0;
        }else {
            result[0] = 0;
            result[1] = 1;
        }*/
        return result;
    }
    
    public double[] procString(String input){
        String name = input;
        name = name.toLowerCase();
        char[] cName = name.toCharArray();
        if (cName.length > 15) {
            JOptionPane.showMessageDialog(null, "Der Name ist zu lang ");
            
            return null;
        }
        char[] cNameproc = new char[15];
        int addSpacesF = (int) ((15 - cName.length) / 2);
        int addSpacesB = (int) 15 - addSpacesF - cName.length;
        for(int i = 0 ; i < addSpacesF ; i++){
            cNameproc[i]=' ';
        }
        for(int i = addSpacesF ; i < addSpacesF+cName.length ; i++){
            cNameproc[i]=cName[i-addSpacesF];
        }
        for(int i = 14; i >= addSpacesF+cName.length;i--){
            cNameproc[i]=' ';
        }
        
        String tmp ="";
        for(int i = 0 ; i < cNameproc.length ; i++){
            tmp += cNameproc[i];
        }
        //JOptionPane.showMessageDialog(this, addSpacesF);
        //JOptionPane.showMessageDialog(this,addSpacesB);
        //JOptionPane.showMessageDialog(this,tmp);
        
        double[] proc = new double[15];
        for(int i = 0 ; i < proc.length ; i++){
            char t = cNameproc[i];
            if(((int)t)!=32){
                proc[i] = ((int)t)-96;
            }
            else{
                proc[i]=0;
            }
            proc[i] = proc[i]/26;
        }
        tmp = "";
        for(int i = 0 ; i < cNameproc.length ; i++){
            tmp += proc[i] + " ";
        }
        //JOptionPane.showMessageDialog(this,tmp);
        return proc;
    }

    public String print() {
        String aus = "";
        for (InputNeuron in : inputs) {
            aus += in.toString() + "\n";
        }
        for (WorkingNeuron w : hidden1) {
            aus += "hidden1" + w.toString() + "\n";
        }
        for (WorkingNeuron w : hidden2) {
            aus += "HIDDEN2" + w.toString() + "\n";
        }
        for (WorkingNeuron w : outputs) {
            aus += "OUT " + w.toString() + "\n";
        }

        return aus;
    }
    
    public void randomizeWeights(int maxWeight){
        
        weights.clear();
         for(int i = 0 ; i<sizeOfNet ; i++ ){
           if(((long)(Math.random()*100)+System.currentTimeMillis())%2==0){
               weights.add(0-(new Random().nextDouble()*maxWeight));
           }else{
               weights.add(new Random().nextDouble()*maxWeight);
           }
           
       }
    }

}
