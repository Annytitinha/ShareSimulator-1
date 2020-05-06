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
public class ShareCompany extends Company{
 
    private boolean isFinish;
    
    public ShareCompany(){
        
    }
    @Override
    public void initCompany(int id, int share_num, int price){
        this.id = id;
        this.share_num = share_num;
        this.price = price;
        this.sell_number = 0;
        this.isFinish = false;
    }
    @Override
    public int getShareNum(){
        return share_num;
    }
    public int getPrice(){
        return price;
    }
    public void setPrice(int price){
        this.price = price;
    }
    @Override
    public int getSellNum(){
        return sell_number;
    }
    public void setID(int id){
        this.id = id;
    }
    @Override
    public int getID(){
        return id;
    }
    @Override
    public void sell(){
        sell_number++;
        share_num--;
        if(share_num == 0){
            isFinish = true;
        }
        // if company sell 10 shares , price is double up
        if(sell_number > 0 && sell_number % 10 == 0){
            setDouble();
        }
    }
    @Override
    public boolean IsCompleted(){
        return isFinish;
    }
    @Override
    public String toString(){
        return "Company " + id;
    }
    @Override
    public int compareTo(Company company ) {
        return this.sell_number.compareTo(company.getSellNum());
   }
}
