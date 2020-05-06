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
public class Investor extends Buyer{
    private int id;
    private int budget;
    private Integer share_num;
    private boolean isFinish;
    
    public Investor(int id, int budget){
        this.id = id;
        this.budget = budget;
        this.share_num = 0;
        this.isFinish = false;
    }
    @Override
    public int getID(){
        return id;
    }
    public void setID(int id){
        this.id = id;
    }
    @Override
    public int getBudget(){
        return budget;
    }
    @Override
    public int getShareNum(){
        return share_num;
    }
    public void setBudget(int budget){
        this.budget = budget;
    }
    @Override
    public void buy(int amount){
        budget -= amount;
        share_num++;
        if(budget == 0){
            isFinish = true;
        }
    }
    @Override
    public boolean IsCompleted(){
        return isFinish;
    }
    @Override
    public String toString(){
        return "Investor " + id;
    }
    @Override
    public int compareTo(Buyer investor ) {
        return this.share_num.compareTo(investor.getShareNum());
   }
}
