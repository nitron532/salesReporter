import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

public class sales{

    public static ArrayList<String> readSales(String fileName) throws IOException{
        File directoryPath = new File("C:\\Users\\nitron\\Documents\\GitHub\\salesReporter\\");
        String contents[] = directoryPath.list();
        ArrayList<String> productList =  new ArrayList<String>();
        for(int p = 0; p<contents.length; p++){
            if(contents[p].equals(fileName)){
                System.out.println("Opening " + fileName + " list.");
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                try{
                String printout = br.readLine();
                while(printout != null){
                   productList.add(printout);
                    printout = br.readLine();
                }

            }
            catch (IOException ioe){
                ioe.printStackTrace();
                
            } 
            
            finally {
                if (br !=null){
                    br.close();
                }
                
            }
            }
            else if(p == contents.length - 1 && !contents[p].equals(fileName)){
                System.out.println("File not found.");
                break; // checks if each index matches inputted file name, if not, checks next index. when final index is reached and (&&) it doesn't
                // match inputted file name, then the file doesn't exist in the directory
            } 
        }
        return productList;
    
    }

    public static int returnNumberOfSales(String productName, double productPrice, ArrayList<String> salesList){
        int totalNumberSales = 0;
        for(String i : salesList){
            if(i.contains(productName)){
                int startIndexName = i.indexOf(productName);
                int endIndexName = startIndexName + productName.length() + 1;
                String strPrice = i.substring(endIndexName, i.indexOf(",",endIndexName));
                double totalIndividualSale = Double.parseDouble(strPrice);
                double totalIndividualAmt = totalIndividualSale / productPrice;
                totalNumberSales += totalIndividualAmt;
            }
            else{
                continue;
            }


        }

        return totalNumberSales;
    }


    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(System.in);
        
        System.out.println("What is the name of the file containing the sales record?");
        String inputtedFileName = scan.nextLine();
        ArrayList<String> salesArrayList = readSales(inputtedFileName);
        if(salesArrayList.isEmpty()){
            System.out.println("Make sure you typed the file name right, including the file type.");
        }
        else{
        // for(String i : salesArrayList){
        //     System.out.println(i);
        // } //instead of printing out every line, print one of each product type? iterate through each line, store product name in arraylist, check each iteration
        // //if product name matches any in the array list, if so then dont print, if not then add name to arraylist and print it
        boolean outerLoopControl = true;
        while(outerLoopControl == true){

        System.out.println("What is the name of the product as stated on the csv file?");
        String productName = scan.nextLine();
        System.out.println("What is the price of the product as stated on Shopify?");
        double productPrice = scan.nextDouble();

        int totalUnitsSold = returnNumberOfSales(productName,productPrice,salesArrayList);
        System.out.println("Total units sold: " +  totalUnitsSold);

        boolean innerLoopControl = true;
        while(innerLoopControl == true){

        System.out.println("Check another product? Type yes or no.");
        String decision = scan.nextLine();
        if(decision.equalsIgnoreCase("yes")){
            innerLoopControl = false;
            outerLoopControl = true;
        }
        else if (decision.equalsIgnoreCase("no")){
            innerLoopControl = false;
            outerLoopControl = false;
            scan.close();
        }
        else if(!decision.equalsIgnoreCase("no") || !decision.equalsIgnoreCase("yes")){
            System.out.println("Invald input. Input yes or no.");
            innerLoopControl = true;
            outerLoopControl = false;
        }
    }
        }
    }
}
}