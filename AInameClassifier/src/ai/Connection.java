/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

/**
 *
 * @author yannikmuller
 */
public class Connection {
    
    
    double weight;
    Neuron link;
    
    public Connection(Neuron n, double w){
        weight = w;
        link = n;
    }
    
}
