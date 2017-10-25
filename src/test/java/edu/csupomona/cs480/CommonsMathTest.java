package edu.csupomona.cs480;

import org.apache.commons.math3.linear.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Erick's Test:
 * This class test the class to return the correct matrix multiplication
 */
public class CommonsMathTest {
    @Test
    public void MatrixMultiplicationTest(){
        double[][] matrixData1 =  {{3d,5d}, {0d,3d}};
        double[][] matrixData2 =  { {2d,9d}, {0d,5d}};
        double[][] answer2D =  {{6,52},{0,15}};
        RealMatrix answer = MatrixUtils.createRealMatrix(answer2D);

        RealMatrix m = MatrixUtils.createRealMatrix(matrixData1);
        RealMatrix n = MatrixUtils.createRealMatrix(matrixData2);

        RealMatrix p = m.multiply(n);
        System.out.println(p.toString());
        assertEquals(p,answer);

    }
}
