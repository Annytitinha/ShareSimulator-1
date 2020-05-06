/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulate;

import View.AlertDialog;
import View.StateView;
import View.ButtonView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javafx.application.Platform;
import javafx.concurrent.Task;

/**
 *
 * @author Anaiana Ramos 2016306
 */
public class Simulation {
    //get this Simulation instance (Singleton pattern design)
    
    private static Simulation instance = new Simulation();
    
    //company list
    private List<Company> company_list = new ArrayList<>();
    //investor list
    private List<Buyer> investor_list = new ArrayList<>();
    private List<Integer> sold_company_list = new ArrayList<>();

    private final int COUNTER = 100; // company and investor counter
    private int total_selling_number = 0;
    private boolean isDone = false;
    private String report;
    
    //get instance of this simulation
    public static Simulation getInstance() {
        return instance;
    }
    /**
     * initialize simulation
     */
    public void initSimulation() {
        company_list.clear();
        investor_list.clear();
        sold_company_list.clear();
        isDone = false;
        //initialize 100 company and 100 inverstor
        for (int i = 0; i < COUNTER; i++) {
            Company company = new ShareCompany();
            company.initCompany(i + 1, getRandom(500, 1000), getRandom(10, 100));
            company_list.add(company);

            Buyer invester = new Investor(i + 1, getRandom(1000, 10000));
            investor_list.add(invester);
        }
    }
    /**
     * Start simulation
     */
    private void startSimulation() {
        initSimulation();
        while (!end()) {
            //select random investor
            for (Buyer investor : investor_list) {
                if (investor.IsCompleted()) {
                    continue;
                }
                //select random company and buy
                int company_index = getRandom(0, COUNTER - 1);
                Company company = company_list.get(company_index);
                if (company.IsCompleted()) {
                    continue;
                }
                //get share price
                int price = company.getPrice();
                if (price < investor.getBudget()) {
                    //counter solid company
                    sold_company_list.add(company.getID());
                    //buy share
                    investor.buy(price);
                    company.sell();
                    total_selling_number++;
                    //check simulation state for each step
                    //some company increase share price as double and somethings reduce share price as 2%
                    checkSellState();
                    //print simulation log in GUI
                    printSimulationLog(company, investor, price);
                }
            }
        }
        Platform.runLater(() -> {
            StateView.getInstance().getLogArea().getItems().add("End Simulation !");
            System.out.println("End Simulation !");
            isDone = true;
        });
    }
    /**
     * Run simulation as background thread
     */
    public void simuationAsBackgroud() {
        StateView.getInstance().getLogArea().getItems();

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    startSimulation();
                } catch (Exception e) {
                    Platform.runLater(() -> {
                        StateView.getInstance().getProcessing().setVisible(false);
                        ButtonView.enableBtns();
                    });
                }
                return null;
            }
        };
        //simuation fail callback
        task.setOnFailed(e -> {
            Throwable exception = e.getSource().getException();
            if (exception != null) {
                Platform.runLater(() -> {
                    StateView.getInstance().getProcessing().setVisible(false);
                    ButtonView.enableBtns();
                    AlertDialog.getInstance().show("Fail to complete simulation !");
                });
            }
        });
        //simuation success callback
        task.setOnSucceeded(e -> {
            Platform.runLater(() -> {
                StateView.getInstance().getProcessing().setVisible(false);
                ButtonView.enableBtns();
                AlertDialog.getInstance().show("Success to complete simulation !");
            });
        });
        new Thread(task).start();
        StateView.getInstance().getProcessing().setVisible(true);
        ButtonView.disableBtns();
    }
    /**
     * Check simulation
     */
    private void checkSellState() {
        //any 10 shares are sold and if any company not share, share price is reduce
        if (total_selling_number > 0 && total_selling_number % 10 == 0) {
            for (Company item : company_list) {
                //check company sold any share or not
                if (!sold_company_list.contains(item.getID())) {
                    item.setReduce();
                }
            }
            sold_company_list.clear();
        }
    }
    /**
     * print simulation log
     * @param company
     * @param invester
     * @param price 
     */
    private void printSimulationLog(Company company, Buyer invester, int price) {
        final String log = invester.toString() + " buy share from " + company.toString() + "\r\n";
        System.out.print(log);
        Platform.runLater(() -> {
            StateView.getInstance().getLogArea().getItems().add(log);
        });
    }
    /**
     * Check simulation complete
     * If all company sold their shares, or If all investors spent their money, simulation is completed
     * @return 
     */
    private boolean end() {
        boolean company_end = true;
        boolean investor_end = true;
        //check all company sold all shares
        for (Company item : company_list) {
            if (!item.IsCompleted()) {
                company_end = false;
                break;
            }
        }
        //check all investor spent their money
        for (Buyer item : investor_list) {
            if (!item.IsCompleted()) {
                investor_end = false;
                break;
            }
        }
        if (company_end || investor_end) {
            return true;
        }
        return false;
    }
    /**
     * Random number generator
     * @param min
     * @param max
     * @return 
     */
    private int getRandom(int min, int max) {
        Random r = new Random();
        int result = r.nextInt(max - min + 1) + min;
        return result;
    }
    //check simulation is complete or not
    public boolean IsDone() {
        return isDone;
    }
    /**
     * generate simulation result report
     * @return 
     */
    public String getReport(){
        report = new String();
        getHighestCompany();
        getLowestCompany();
        getHighestInvestor();
        getLowestInvestor();
        return report;
    }
    /**
     * get highest company 
     */
    public void getHighestCompany() {
        Collections.sort(company_list, Collections.reverseOrder());
        List<Company> highest_company = new ArrayList<>();
        Company highest = company_list.get(0);
        for(Company item : company_list){
            if(item.getSellNum() == highest.getSellNum()){
                highest_company.add(item);
            }
        }
        report += "1. Company with the highest capital\r\n";
        for(Company item : highest_company){
            report += "\t-Name: " + item.toString() + "\r\n";
            report += "\t-Sold Shares: " + item.getSellNum() + "\r\n";
            report += "\t-Last Price: " + item.getPrice()+ "\r\n\r\n";
        }
    }
    /**
     * get lowest company
     */
    public void getLowestCompany() {
        Collections.sort(company_list);
        List<Company> lowest_company = new ArrayList<>();
        Company lowest = company_list.get(0);
        for(Company item : company_list){
            if(item.getSellNum() == lowest.getSellNum()){
                lowest_company.add(item);
            }
        }
        report += "2. Company with the lowest capital\r\n";
        for(Company item : lowest_company){
            report += "\t-Name: " + item.toString() + "\r\n";
            report += "\t-Sold Shares: " + item.getSellNum() + "\r\n";
            report += "\t-Last Price: " + item.getPrice()+ "\r\n\r\n";
        }
    }
    /**
     * get highest investor
     */
    public void getHighestInvestor() {
        Collections.sort(investor_list, Collections.reverseOrder());
        List<Buyer> highest_investor = new ArrayList<>();
        Buyer highest = investor_list.get(0);
        for(Buyer item : investor_list){
            if(item.getShareNum()== highest.getShareNum()){
                highest_investor.add(item);
            }
        }
        report += "3. Investor with the highest number of shares\r\n";
        for(Buyer item : highest_investor){
            report += "\t-Name: " + item.toString() + "\r\n";
            report += "\t-Shares: " + item.getShareNum()+ "\r\n\r\n";
        }
    }
    /**
     * get lowest investor
     */
    public void getLowestInvestor() {
        Collections.sort(investor_list);
        List<Buyer> lowest_investor = new ArrayList<>();
        Buyer lowest = investor_list.get(0);
        for(Buyer item : investor_list){
            if(item.getShareNum()== lowest.getShareNum()){
                lowest_investor.add(item);
            }
        }
        report += "4. Investor with the lowest number of shares\r\n";
        for(Buyer item : lowest_investor){
            report += "\t-Name: " + item.toString() + "\r\n";
            report += "\t-Shares: " + item.getShareNum()+ "\r\n\r\n";
        }
    }
}
