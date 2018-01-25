/*
 * Wildfire Science Project Algorithm - Brute Force
 * @author Matthew Yu
 * @version 1.00 2017/2/9
 */

package wildfirescienceprojectalgorithm;

import java.util.*;
import java.io.*;
/**
 * Class WildfireScienceProjectAlgorithm is a class that determines a possible
 * algorithm using input data of PDSI, Avg Temp, and NDVI, as well as Acreage
 * Burned. It will use loops to create a new equation for each iteration and 
 * compare the result to the acreage burned in order to determine an average
 * percent error.
 * 
 * Steps: getCalculation - creates the variable coefficients by implementing 
 * increments for each separate coefficient in an array.
 * computeCalculation - computes the avg percent error by taking out the 
 * coefficient values for each array row from getCalculation, multiplies them by 
 * respective input data from file, and compares them with the observed acreage
 * burned in 4th column of input data file. (percent error funct, equationColl)
 * It then averages all percent errors from one row of equationColl into an 
 * eqnCollCompressed array that contains the avg percent error for each 
 * respective iteration found in step 1.
 * 
 * Storage of input data is in the format:
 * "PDSI, Avg Temp, NDVI, Acreage Burned \n"
 * Storage of comparison data is in the format:
 * "PDSI, Avg Temp, NDVI, Observed Acreage Burned \n"
 * A WildfireScienceProjectAlgorithm has a File Path and a Delimiter.
 */
public class WildfireScienceProjectAlgorithm 
{
    private static String DEFAULT_FILE = "DataN20082012N0Event.tsv";
    private static String DEFAULT_DELIM = ",";
    private String filePath = null;
    private String delim = null;
    public int[][] coeff;
    public int arraySize;
    
    /**
     * Constructs a default WildfireScienceProjectAlgorithm.
     */
    public WildfireScienceProjectAlgorithm()
    {
        this.filePath = WildfireScienceProjectAlgorithm.class.getResource("").getPath().substring(1) + DEFAULT_FILE;
        this.delim = DEFAULT_DELIM;
    }

     /**
     * Constructs a WildfireScienceProjectAlgorithm with a given File Path and Delimeter.
     * @param filePath location of the text file
     * @param delim delimiter of the of the WildfireScienceProjectAlgorithm 
     * /
    public WildfireScienceProjectAlgorithm(String filePath, String delim)
    {
        this.filePath = filePath;
        this.delim = delim;
    }   

     /**
      * getCalculation gets an array of all the coefficient values for the algorithm.
      * @param arraySize size of array
      * @return Integer Array of coefficient values
      */
    public int[][] getCalculation(int arrSize)
    {
        arraySize = arrSize;
        coeff = new int[arraySize][3]; 
        int rowCount = 0;
        for(int i = 0; i<50; i++) //1000
        {
            for(int j = 0; j<50; j++) //1000
            {
                for(int k = 0; k<50; k++) //1000
                {
                    coeff[rowCount][0] = -10 + i*-10; //20
                    coeff[rowCount][1] = 10 + j*10;
                    coeff[rowCount][2] = 100 + k*100;
                    rowCount++;
                }
            }
        }
        /*int printCount = 0;
        for(int i = 0; i<arraySize; i++)
        {
            System.out.println(coeff[i][1] + " " + coeff[i][2] + " " + coeff[i][3]);
        }
        */
        return coeff;
    }
 
     /**
      * getCalculation2 gets an array of all the coefficient values for the algorithm within a certain range.
      * @param arraySize size of array
      * @param var1Size size of var 1
      * @param var2Size size of var 2
      * @param var3Size size of var 3
      * @param var1Start starting value of var 1
      * @param var2Start starting value of var 2
      * @param var3Start starting value of var 3
      * @return Integer Array of coefficient values
      */
    public int[][] getCalculation2(int arraySize, int var1Size, int var2Size, 
        int var3Size, int var1Start, int var2Start, int var3Start)
    {
        coeff = new int[arraySize][3]; 
        int rowCount = 0;
        for(int i = 0; i<var1Size; i++)
        {
            for(int j = 0; j<var2Size; j++)
            {
                for(int k = 0; k<var3Size; k++)
                {
                    coeff[rowCount][0] = var1Start + i*10;
                    coeff[rowCount][1] = var2Start + j*10;
                    coeff[rowCount][2] = var3Start + k*10;
                    rowCount++;
                }
            }
        }
        return coeff;
    }
    
     /**
      * computeCalculation gets an array of all the percent errors from each data input into the calculation.
      * @param 
      * @param
      * @throws java.io.IOException
      */
    public void computeCalculation() throws IOException
    {
        int coeff1 = 0;
        int coeff2 = 0;
        int coeff3 = 0;
        int rowCount = 0;
        double[][] equationColl = new double[arraySize][72];
        double[] eqnCollCompressed = new double[arraySize];
        double[] equationArr = new double[4];

        //System.out.println(in.next() + " nextConfirm");
        for(int i = 0; i<10; i++) //1000 //50
        {
            for(int j = 0; j<10; j++) //1000 //50
            {
                for(int k = 0; k<10; k++) //1000 //50
                {
                    coeff1 = coeff[rowCount][0];
                    coeff2 = coeff[rowCount][1];
                    coeff3 = coeff[rowCount][2];
                    Scanner in = new Scanner(new File(DEFAULT_FILE));
                    //for(int n=0; n<72; n++)()
                    
                    int n = 0;
                    while(in.hasNextLine()) //assume file has 72 lines
                    {
                            for(int l=0; l<4; l++)
                            {
                                equationArr[l] = in.nextDouble();
                                //System.out.println(equationArr[l] + " ");
                            } 
 
                        //System.out.println(equationArr + " equationDataInputConfirm");
                        equationColl[rowCount][n] = Math.abs((equationArr[3]-(coeff1*equationArr[0]+coeff2*equationArr[1]+coeff3*equationArr[2]))/equationArr[3]);
                        //System.out.println(coeff1 + " " + coeff2 + " " + coeff3 + " coeffPrint");
                        //System.out.println("[rowCount:n]"+rowCount+":"+n+"==>"+equationColl[rowCount][n]);
                        n++;
                    }                    
                    rowCount++;
                }
            }
        }
        System.out.println("WhereAmI?");
        //rowCount = 0;
        double avgPercentErr = 0;
        double sum = 0;
        for(int p=0; p<1250; p++) //p<arraySize
        {
            double avgCount = 1.0;            
            for(int o=0; o<72; o++)
            {

                //System.out.println(equationColl[rowCount][o]);
                sum += equationColl[p][o];
                avgPercentErr = sum/avgCount;
                avgCount++;
                
                //System.out.println("[rowCount:n:%err:avgCount]"+p+":"+o+":"+avgPercentErr+":"+avgCount+"==>"+equationColl[p][o]);                
            }

            //System.out.println(avgPercentErr);
            eqnCollCompressed[p] = avgPercentErr;
            System.out.println(eqnCollCompressed[p]);
            //rowCount++;
        }
        
        //System.out.println(eqnCollCompressed);
    }
            
    
           
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // TODO code application logic here
        try
        {
            System.out.println("Working 1.01");
            WildfireScienceProjectAlgorithm z = new WildfireScienceProjectAlgorithm();
            //Print out the entire list of possible coefficient combinations
            z.getCalculation(125000);
            /*int[][] array = z.getCalculation(125000);
            for(int x = 0; x < array.length; x++)
            {
                
                for(int y = 0; y < array[x].length; y++)
                    {
                        System.out.print(array[x][y]+ " ");
                    }
                System.out.println();
            }*/
            //System.out.println("getCalculationDone");
            //Print out the list of percent errors from a combination
            //Print out the entire list of average percent errors from equations
            z.computeCalculation();   
            
            
            
            
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            
        }
    }


}

    