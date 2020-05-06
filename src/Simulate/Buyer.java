/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulate;

/**
 *
 * @author Anaiana Ramos 2016306
 */
public abstract class Buyer implements Comparable<Buyer>{
    public abstract int getID(); // buyer id
    public abstract int getShareNum(); //get buy share number
    public abstract int getBudget(); //get current budget
    public abstract void buy(int amount); // buy share
    public abstract boolean IsCompleted(); // check buyer spend his all money
}
