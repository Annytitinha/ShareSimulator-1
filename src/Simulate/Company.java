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
public abstract class Company implements Comparable<Company> {

    public int id; // company id
    public int share_num; // company share number
    public Integer sell_number; // the number of share that is sold
    public int price; // current share price

    public abstract void initCompany(int id, int share_num, int price); // initialize company

    public abstract int getID(); //get comnpany id

    public abstract int getShareNum(); //get share number

    public abstract int getSellNum(); //get sold share number

    public abstract int getPrice(); //get current share price

    public abstract void sell(); // sell share

    public abstract boolean IsCompleted(); // check company sold all shares or not

    /**
     * double price
     */
    public void setDouble() {
        price = price * 2;
    }
    /**
     * reduce price as 2%
     */
    public void setReduce() {
        price = (int) (price * 0.98);
        if (price == 0) {
            price = 1;
        }
    }
}
