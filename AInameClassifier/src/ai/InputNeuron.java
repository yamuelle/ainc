/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import frames.MainGUI;


/**
 *
 * @author yannikmuller
 */
public class InputNeuron extends Neuron{
    private double activation ; 
    
    
    
    public void setInput(double input){
        activation =  input;
    }

    
    @Override
    public double getValue(){
        //MainGUI.printToConsole("Value abgefragt von :"+this.toString());
        return activation;
        
    }
    
}
