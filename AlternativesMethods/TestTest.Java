import java.util.Scanner;

public class Alternatives {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter 1 to test Alternatives or 0 to exit: ");
        int enter = input.nextInt();
        System.out.println();

        while (enter == 1) {
            System.out.print("Enter the number of rows:");
            int row = input.nextInt();

            System.out.print("Enter the number of columns:");
            int col = input.nextInt();

            System.out.println();
            int matrix[][] = new int[row][col];

            // Read the matrix values-----------------------
            System.out.println("(((((Enter the elements of the matrix)))))");
            for (int r = 0; r < col; r++) {
                System.out.println();
                for (int c = 0; c < row; c++) {
                    System.out.print("Enter the index (" + r + "," + c + "): ");
                    matrix[r][c] = input.nextInt();
                }
            }
            System.out.println();
            System.out.println("Your matrix is: ");

            // Display the elements of the matrix----------------
            // loop through array's rows
            for (int r = 0; r < matrix.length; r++) {
                // loop through columns of current row
                for (int c = 0; c < matrix[r].length; c++)
                    System.out.printf("%d ", matrix[r][c]);

                System.out.println();
            }


            System.out.println("Choose from the list: ");
            System.out.println("All                       = 1");
            System.out.println("Symmetric_Alternative     = 2");
            System.out.println("Reflexive_Alternative     = 3");
            System.out.println("Transitive_Alternative    = 4");

            int Test = input.nextInt();
            System.out.println();

            System.out.println("--------------------------------");
            switch (Test) {
            case 1:
                
        	Symmetric_Alternative(matrix);
        	Reflexive_Alternative(matrix);
        	Transitive_Alternative(matrix);
                break;
            case 2:
        	Symmetric_Alternative(matrix);
                break;
            case 3:
        	Reflexive_Alternative(matrix);
                break;
            case 4:
        	Transitive_Alternative(matrix);
                break;
                
            }
            System.out.print("Enter 1 to another test or 0 to exit: ");
            enter = input.nextInt();
        }
    }


    // ----------------------------------------------------------------------
    public static void Symmetric_Alternative(int array[][]) {
	
 	int T = 0;
 	
         for (int r = 0; r < array.length; r++) {
             for (int c = 0; c < array.length; c++) {
         	if (r != c) {
                  if (array[r][c] == array[c][r])
                     T++;
                 }

             }
         }
         
         if (T == array.length * (array.length-1))
             System.out.println("Symmetric");
         else
         
             
             System.out.println("Not Symmetric");
     }
     // Reflexive Alternative------------------------------------------------
     public static void Reflexive_Alternative(int array[][]) {
     	int ok=0;
     	
     	for (int i = 0; i < array.length; i++) {
     		for (int j=0; j< array.length; j++) {
     			if (i==j) {
     				if (array[i][j]==1)
     					ok++;
     			}
             		
     		}
     	}
     	
     	if (ok==array.length)
     	    System.out.println("Reflexsive");
         else
             System.out.println("Not Reflexsive");
     }
    //  Transitive Alternative ------------------------------------------------
    public static void Transitive_Alternative(int array[][]) {
        int Print = 1;
        int n;

        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length; j++) {

                if (array[i][j] == 1) {
                    n = 0;

                    while (n < array.length) {
                        if (array[j][n] == 1) {
                            if (array[i][n] != 1) {
		if (Print == 0)
                                break;
                                System.out.println("Not transitive");
                                Print = 0;
                                
                            }
                            
                        }
                        
                        n++;
                    } // end while loop
                }
                

            } // end for loop 1
        } // end for loop 2
        if (Print == 1)
            System.out.println("Transitive");

    }
    
    
}
