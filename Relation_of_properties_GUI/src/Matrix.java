import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;



public class Matrix implements ActionListener 
{
    
     
     private static int col, row;  //dimentions
     private static int myMatrix [][];
     private static int tempMatrix [][];
     
     private static JTextField inputField [][];
     private static int result;
     private static JButton AsymmetricTest, SymmetricTest, AllTests,About,
     AntisymmetricTest, ReflexiveTest, IrreflexiveTest,
     EquivalenceTest, showMatrix, TransitiveTest,
             newMatrix;
     private static JPanel choosePanel [] = new JPanel[9];
     private static int lastCol , lastRow ;
     
     
     Matrix ()
     {
         col = row = 0;
         myMatrix = new int [0][0];
        // ChooseOperation();

     }
   
     
     
     
   

    //prompting for matrix's dimensions
     private static void getDimension() 
    {
      JTextField lField = new JTextField(5); //lenght field 
      JTextField wField = new JTextField(5); //col field
      
      //design input line
      JPanel choosePanel [] = new JPanel [2];
       choosePanel [0] = new JPanel();
       choosePanel [1] = new JPanel();
      choosePanel[0].add(new JLabel("Enter Dimensitions") );
      choosePanel[1].add(new JLabel("Rows:"));
      choosePanel[1].add(lField);
      choosePanel[1].add(Box.createHorizontalStrut(15)); // a spacer
      choosePanel[1].add(new JLabel("Cols:"));
      choosePanel[1].add(wField);
      
      result = JOptionPane.showConfirmDialog(null, choosePanel, 
               "Relaton of Properties",JOptionPane.OK_CANCEL_OPTION, 
               JOptionPane.PLAIN_MESSAGE);
        
      //save last dimensions
      lastCol = col;
      lastRow = row;
      
      
      //ok option
       if(result == 0)
       {
         
         if(wField.getText().equals(""))
             col = 0;
         else
         {
             if(isInt(wField.getText()))
             {
                 col = Integer.parseInt(wField.getText());
             }
             else
             {
                 JOptionPane.showMessageDialog(null, "Wrong Dimensions");
                 col = lastCol;
                 row = lastRow;
                 return;
             }
            
             if(isInt(lField.getText()))
             {
                 row = Integer.parseInt(lField.getText());
             }
             else
             {
                 JOptionPane.showMessageDialog(null, "Wrong Dimensions");
                 col = lastCol;
                 row = lastRow;
                 return;
             }
          
         }
       if(col < 1 || row < 1)
       {
           JOptionPane.showConfirmDialog(null, "You entered wrong dimensions", 
                   "Error",JOptionPane.PLAIN_MESSAGE);
           col  = lastCol;
           row = lastRow;
          
       }
       else
       {
           tempMatrix = myMatrix;
           myMatrix = new int [row][col];
            if(!setElements(myMatrix, "Fill your new binary matrix")) //filling the new matrix
            {
                //backup
                
                myMatrix = tempMatrix;
            }
       }
       }
       else if(result == 1)
       {
           col = lastCol;
           row = lastRow;
       }
     }//end get Dimension
    
     //setting a matrix's elementis
    private static boolean setElements(int matrix [][], String title )
    {
        int temp, temp1;             //temprature variable
        String tempString;
        
       JPanel choosePanel [] = new JPanel [row+2];
       choosePanel[0] = new JPanel();
       choosePanel[0].add(new Label(title ));
       choosePanel[choosePanel.length-1] = new JPanel();
       choosePanel[choosePanel.length-1].add(new Label("Note: blank fields considered as zeros"));
       inputField  = new JTextField [matrix.length][matrix[0].length];
        
       
       //lenght loop
       for(temp = 1; temp <= matrix.length; temp++)
       {
           choosePanel[temp] = new JPanel();
           
           
           for(temp1 = 0; temp1 < matrix[0].length; temp1++)
           {
               inputField [temp-1][temp1] = new JTextField(3);
               choosePanel[temp].add(inputField [temp-1][temp1]);
               
               if(temp1 < matrix[0].length -1)
               {
               choosePanel[temp].add(Box.createHorizontalStrut(15)); // a spacer
               }
               
           }//end col loop
           
       }//end row loop
       
       result = JOptionPane.showConfirmDialog(null, choosePanel, 
               null, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
     
      
      if(result == 0)
      {
          checkTextField(inputField);
       for(temp = 0; temp < matrix.length; temp++)
       {
        for(temp1 = 0; temp1 < matrix[0].length; temp1++)
            {
                tempString = inputField[temp][temp1].getText();
                
                
                 if(isInt(tempString))
                {
                matrix [temp][temp1] = Integer.parseInt(inputField[temp][temp1].getText());
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "You entered wrong elements");
                    
                    //backup
                    col = lastCol;
                    row = lastRow;
                    
                    return false;
                }                      
            }
       }
       return true;
    }
      else
          return false;
    
      
    }//end get Inputs
    
    //for setting spaced fields as zeros
     private static void checkTextField (JTextField field [][] )
     {
         for(int temp = 0; temp < field.length; temp++)
         {
             for(int temp1 = 0; temp1 < field[0].length; temp1++)
             {
                 if(field[temp][temp1].getText().equals(""))
                 field[temp][temp1].setText("0");
             }
         }
     }//end reset
    
    private void ChooseOperation ()
    {
        int temp;
        
        
        for(temp = 0; temp < choosePanel.length; temp++)
        {
            choosePanel [temp] = new JPanel ();
        }
        
        ImageIcon chooseImage = new ImageIcon(getClass().getResource
                ("choose-button.png")) ;
        JLabel chooseLabel = new JLabel (chooseImage);
        choosePanel[0].add(chooseLabel);
        
        choosePanel[1].add(Box.createHorizontalStrut(15)); // a spacer
        
        choosePanel[6].add(Box.createHorizontalStrut(15)); // a spacer
        
        
        ImageIcon logoImage = new ImageIcon(getClass().getResource("logo.png")) ;
        JLabel logoLabel = new JLabel (logoImage);
        choosePanel[7].add(logoLabel);
        
        
        
        
        
        
        
        showMatrix = new JButton ("Show Matrix");
        showMatrix.setPreferredSize(new Dimension(175,35));
        showMatrix.addActionListener(this);
        choosePanel[2].add(showMatrix);
        
        SymmetricTest = new JButton ("Symmetric Test");
        SymmetricTest.setPreferredSize(new Dimension(175,35));
        SymmetricTest.addActionListener(this);
        choosePanel[2].add(SymmetricTest);
        
        AsymmetricTest = new JButton ("Asymmetric Test");
        AsymmetricTest.setPreferredSize(new Dimension(175,35));
        AsymmetricTest.addActionListener(this);
        choosePanel[2].add(AsymmetricTest);
        
        AntisymmetricTest = new JButton ("AntiSymmetric Test");
        AntisymmetricTest.setPreferredSize(new Dimension(175,35));
        AntisymmetricTest.addActionListener(this);
        choosePanel[3].add(AntisymmetricTest);
        
        ReflexiveTest = new JButton ("Reflexive Test");
        ReflexiveTest.setPreferredSize(new Dimension(175,35));
        ReflexiveTest.addActionListener(this);
        choosePanel[3].add(ReflexiveTest);
        
        IrreflexiveTest = new JButton ("Irreflexive Test");
        IrreflexiveTest.setPreferredSize(new Dimension(175,35));
        IrreflexiveTest.addActionListener(this);
        choosePanel[3].add(IrreflexiveTest);
        
        
        TransitiveTest = new JButton ("Transitive Test");
            TransitiveTest.setPreferredSize(new Dimension(175,35));
            TransitiveTest.addActionListener(this);
        choosePanel[4].add(TransitiveTest);
        
        
            EquivalenceTest = new JButton ("Equivalence Test");
           EquivalenceTest.setPreferredSize(new Dimension(175,35));
           EquivalenceTest.addActionListener(this);
           choosePanel[4].add(EquivalenceTest);
           
           AllTests = new JButton ("All Tests");
           AllTests.setPreferredSize(new Dimension(175,35));
           AllTests.addActionListener(this);
        choosePanel[4].add(AllTests);
                   
        
        
        newMatrix = new JButton("New Matrix");
        newMatrix.setPreferredSize(new Dimension(275,35));
        newMatrix.addActionListener(this);
        choosePanel[5].add(newMatrix);
        
        
        About = new JButton ("About the program");
        About.setPreferredSize(new Dimension(175,35));
        About.addActionListener(this);
     choosePanel[8].add(About);
        
        JOptionPane.showConfirmDialog(null, choosePanel, null,
               JOptionPane.CLOSED_OPTION , JOptionPane.PLAIN_MESSAGE);
         
    }
   
   
    @Override
    public  void actionPerformed(ActionEvent e) 
    {
        
        if(e.getSource() == showMatrix)
        {
            showMatrix( myMatrix, "Your Matrix");
        }
        if(e.getSource() == SymmetricTest)
        {
            Symmetric();
        }
        
        else if(e.getSource() == AsymmetricTest)
        {
            Asymmetric();
        }
         
        else    if(e.getSource() == AntisymmetricTest)
        {
            Antisymmetric();
        }
        else   if(e.getSource() == AllTests)
        {
            AllTest();
        }
        
        else    if(e.getSource() ==  ReflexiveTest)
        {
            Reflexive();
        }
         else   if(e.getSource() == IrreflexiveTest)
        {
             Irreflexive ();
        }
        
         else   if(e.getSource() == TransitiveTest )
        {
             Transitive();
        }
         
        else   if(e.getSource() == EquivalenceTest)
        {
            equivalence();
        }
        
        else   if(e.getSource() == newMatrix)
        {
            newMatrix();
        }
        else   if(e.getSource() == About)
        {
            About();
        }
    }//end action performed

    
    private static void showMatrix(int [][] matrix, String title )
    {
        int temp, temp1;             //temprature variable
        
       JPanel choosePanel [] = new JPanel [matrix.length+1];
       choosePanel[0] = new JPanel ();
       choosePanel[0].add( new JLabel (title) );
   
       for(temp = 1; temp <= matrix.length; temp++)
       {
           choosePanel[temp] = new JPanel();
           
           
           for(temp1 = 0; temp1 < matrix[0].length; temp1++)
           {
               if(matrix[temp-1][temp1] == -0)
               {
                  matrix[temp-1][temp1] = 0; 
               }
               choosePanel[temp].add(new JLabel(String.format("%d", matrix[temp-1][temp1])));
               
               if(temp1 < matrix[0].length -1)
               {
               choosePanel[temp].add(Box.createHorizontalStrut(15)); // a spacer
               }
               
           }//end col loop
           
       }//end row loop
       
    if(col == 0 || row == 0)
    {
        JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
    }
    else
    {
    
    JOptionPane.showMessageDialog(null, choosePanel, null, 
            JOptionPane.PLAIN_MESSAGE, null);
    }  
    }//end show Matrix

  public static boolean q_for_symmetric = true;
  private static void Symmetric()
{
if(myMatrix.length < 1)
        {
            JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
        }
        else
        {

            for (int r = 0; r < myMatrix.length; r++) {
                for (int c = 0; c < myMatrix.length; c++) {

                    if (r == c)
                        q_for_symmetric = true;
                    else {
                        if (myMatrix[r][c] != myMatrix[c][r]) {
                            q_for_symmetric = false;
                            break;
                        }
                    }

                }
            }
    
    if (q_for_symmetric == true)
	JOptionPane.showMessageDialog(null, "Symmetric");
    else
	JOptionPane.showMessageDialog(null, "Not Symmetric");
    
        }//end else checking
}
    
private static void Asymmetric ()
{
    boolean q = true;
    if(myMatrix.length < 1)
        {
            JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
        }
        else
        {
            
            for (int r = 0; r < myMatrix.length; r++) {
                for (int c = 0; c < myMatrix.length; c++) {

                    if (r == c) {
                        if (myMatrix[r][c] != 0) {
                            q = false;
                            break;
                        } else {
                            if ((myMatrix[r][c] == 1) && (myMatrix[c][r] == 1)) {
                                q = false;
                                break;
                            }
                        }

                    }
                }
            }
        //end else of checking
    if (q == true)
	JOptionPane.showMessageDialog(null, "Asymmetric");
    else
	JOptionPane.showMessageDialog(null, "Not Asymmetric");
        }
}


private static void Antisymmetric ()
{
    
    boolean q = true;
      if(myMatrix.length < 1)
        {
            JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
        }
        else
        {
            for (int r = 0; r < myMatrix.length; r++) {
                for (int c = 0; c < myMatrix.length; c++) {

                    if (r == c)
                        q = true;
                    else {
                        if ((myMatrix[r][c] == 1) && (myMatrix[c][r] == 1)) {
                            q = false;
                            break;
                        }
                    }

                }
            }
     
        
      if (q == true)
		JOptionPane.showMessageDialog(null, "Antisymmetric");
	    else
		JOptionPane.showMessageDialog(null, "Not Antisymmetric");
        }//end else of checking
}

public static boolean q_for_reflexive = true;
    private static void Reflexive ()
{
    
	if(myMatrix.length < 1)
        {
            JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
        }
        else
        {

            for (int i = 0; i < myMatrix.length; i++) {

                if (myMatrix[i][i] != 1) {
                    q_for_reflexive = false;
                    break;
                }
            }
            
       
	
    if (q_for_reflexive == true)
	JOptionPane.showMessageDialog(null, "Reflexsive");
    else
	JOptionPane.showMessageDialog(null, "Not Reflexsive");
    
        }//end else checking
}
        

    
    private static void Irreflexive ()
{
 
	 boolean q = true;
	    if(myMatrix.length < 1)
	        {
	            JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
	        }
	        else
	        {
	            
	            for (int i = 0; i < myMatrix.length; i++) {
	                if (myMatrix[i][i] != 0) {
	                    q = false;
	                    break;
	                }
	            }

	       
	    if (q == true)
		JOptionPane.showMessageDialog(null, "Irreflexive");
	    else
		JOptionPane.showMessageDialog(null, "Not Irreflexive");
	    
	        }//end else of checking
}

    
    
    public static int[][] multiplyMatrices(int[][] matrix, int[][] matrix1, int row, int col) {
        int[][] product = new int[row][col];
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int k = 0; k < col; k++) {
                    product[i][j] += matrix[i][k] * matrix[k][j];
                }
            }
        }
        
       for(int v = 0; v<row; v++) {
    	   for(int u = 0; u< col; u++) {
    		   if (product[u][v]>=1)
    			   product[u][v]=1;
    	   }
       }

        return product;
    }

    
    //--------------------------------------------------------------------------------------
    public static boolean q_for_transitive = true;
    private static void Transitive ()
{
	int[][] matrixSquared =multiplyMatrices(myMatrix,myMatrix,row,col);
	 if(myMatrix.length < 1)
	        {
	            JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
	        }
	        else
	        {
    for (int r = 0; r < myMatrix.length; r++) {
        for (int c = 0; c < myMatrix.length; c++) {

            if (myMatrix[r][c] != matrixSquared[r][c]) {
            	q_for_transitive=false;
            	break;
            	}
        }
    }
	       
	 
    if (q_for_transitive == true)
	JOptionPane.showMessageDialog(null, "Transitive");
    else
	JOptionPane.showMessageDialog(null, "Not Transitive");
	        }//end else of checking
     
}
    
    private static void equivalence ()
    {
	if(myMatrix.length < 1)
        {
            JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
        }
        else
        {
	if (q_for_symmetric == true && q_for_reflexive == true && q_for_transitive == true)
	    	JOptionPane.showMessageDialog(null, "Equivalence");
	    else
		JOptionPane.showMessageDialog(null, "Not Equivalence");
        }
    }
   
    
    private static void AllTest ()
    {
     
    	 
    	    if(myMatrix.length < 1)
    	        {
    	            JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
    	        }
    	        else
    	        {
    	        Symmetric();
    	        Asymmetric();
    	        Antisymmetric();
    	        Reflexive();
    	        Irreflexive();
    	        Transitive();
    	        equivalence();
    	        
    	    
    	        }//end else of checking
    }

    private static void  About  ()
    {
	
           
            JOptionPane.showMessageDialog(null,
                    "<html> This program aims to help the students in the relation's properites topic <br> <br> Developed by:<br> <br> - Abdulaziz Hamid Ebrahim <br> </html>");
                
            
           
    }
   private static boolean isInt (String str)
   {
       int temp;
       if (str.length() == '0')
           return false;
       
       for(temp = 0; temp < str.length();temp++)
       {
           if(str.charAt(temp) != '+' && str.charAt(temp) != '-'
                   && !Character.isDigit(str.charAt(temp)))
           {
               return false;
           }
       }
       return true;
   }

    private static void newMatrix ()
    {        
        getDimension();
    }
     public static void main (String [] args)
    {
	
	 
	 
       Matrix m1 = new Matrix ();
       m1.ChooseOperation();
        
        
    }
}//end class

