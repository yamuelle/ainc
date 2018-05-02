/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import java.util.ArrayList;

/**
 *
 * @author yannikmuller
 */
public class WorkingNeuron extends Neuron {
    
    ArrayList<Connection> connections = new ArrayList<>();
    
    
    double activation;
    
    @Override
    public double getValue(){
        double sum = 0;
        for(Connection c : connections){
            activation += c.link.getValue() * c.weight;
            //activation = 1 / (1 + Math.pow(Math.E, -activation));
            activation = Math.tanh(activation);
            //System.out.println(activation);
        }
        
        return activation;
    }
    
    public void addConnection(Connection c){
        connections.add(c);
    }


}
