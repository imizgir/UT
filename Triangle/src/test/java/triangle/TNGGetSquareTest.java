package triangle;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.NaN;
import static java.lang.Double.POSITIVE_INFINITY;
import static java.lang.Math.sqrt;

public class TNGGetSquareTest {

    private Triangle triangle;
    private double a = 2.0;
    private double b = 3.0;
    private double c = 4.0;
    private double sideA;
    private double sideB;
    private double sideC;

    @DataProvider(name = "negativeProvider")
    public Object[][] negativeProviderData() {
        return new Object[][]
                {
                        // Negative Side
                        {new ArrayList<Double>(Arrays.asList(-a, b, c))},
                        {new ArrayList<Double>(Arrays.asList(a, -b, c))},
                        {new ArrayList<Double>(Arrays.asList(a, b, -c))},
                        {new ArrayList<Double>(Arrays.asList(-a, -b, -c))},
                        // Zero Side
                        {new ArrayList<Double>(Arrays.asList(0.0, b, c))},
                        {new ArrayList<Double>(Arrays.asList(a, 0.0, c))},
                        {new ArrayList<Double>(Arrays.asList(a, b, 0.0))},
                        // Too Big Side
                        {new ArrayList<Double>(Arrays.asList(a, b, a+b+1))},
                        {new ArrayList<Double>(Arrays.asList(a, a+c+1, c))},
                        {new ArrayList<Double>(Arrays.asList(b+c+1, b, c))},
                };
    }

    @DataProvider(name = "infNaNProvider")
    public Object[][] infNaNProviderData() {
        return new Object[][]
                {
                        // Infinity
                        {new ArrayList<Double>(Arrays.asList(POSITIVE_INFINITY, b, c))},
                        {new ArrayList<Double>(Arrays.asList(a, POSITIVE_INFINITY, c))},
                        {new ArrayList<Double>(Arrays.asList(a, b, POSITIVE_INFINITY))},
                        {new ArrayList<Double>(Arrays.asList(NEGATIVE_INFINITY, b, c))},
                        {new ArrayList<Double>(Arrays.asList(a, NEGATIVE_INFINITY, c))},
                        {new ArrayList<Double>(Arrays.asList(a, b, NEGATIVE_INFINITY))},
                        // NaN
                        {new ArrayList<Double>(Arrays.asList(NaN, b, c))},
                        {new ArrayList<Double>(Arrays.asList(a, NaN, c))},
                        {new ArrayList<Double>(Arrays.asList(a, b, NaN))},
                };
    }

    @DataProvider(name = "correctProvider")
    public Object[][] correctProviderData() {
        return new Object[][]
                {
                        // EQUILATERAL
                        {new ArrayList<Double>(Arrays.asList(a, a, a)), 1.732051},
                        // ISOSCELES
                        {new ArrayList<Double>(Arrays.asList(a, b, b)), 2.828427},
                        {new ArrayList<Double>(Arrays.asList(b, a, b)), 2.828427},
                        {new ArrayList<Double>(Arrays.asList(b, b, a)), 2.828427},
                        // ORDINARY
                        {new ArrayList<Double>(Arrays.asList(a, b, c)), 2.904738},
                        {new ArrayList<Double>(Arrays.asList(b, c, a)), 2.904738},
                        {new ArrayList<Double>(Arrays.asList(c, a, b)), 2.904738},
                        // RECTANGULAR
                        {new ArrayList<Double>(Arrays.asList(a, b, sqrt(a*a + b*b))), (a*b)/2},
                        {new ArrayList<Double>(Arrays.asList(sqrt(a*a + b*b), a, b)), (a*b)/2},
                        {new ArrayList<Double>(Arrays.asList(b, sqrt(a*a + b*b), a)), (a*b)/2},
                        // RECTANGULAR & ISOSCELES
                        {new ArrayList<Double>(Arrays.asList(a, a, sqrt(2*a*a))), (a*a)/2},
                        {new ArrayList<Double>(Arrays.asList(sqrt(2*a*a), a, a)), (a*a)/2},
                        {new ArrayList<Double>(Arrays.asList(a, sqrt(2*a*a), a)), (a*a)/2},
                };
    }

    @Test(dataProvider = "correctProvider")
    public void testGetSquareCorrectTriangle(ArrayList<Double> params, Double expectedResult){
        sideA = params.get(0);
        sideB = params.get(1);
        sideC = params.get(2);
        this.triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertEquals(this.triangle.getSquare(), expectedResult, 0.01,"[Fail with: " + sideA + "  " + sideB + "  " + sideC + "]");
    }

    @Test(dataProvider = "negativeProvider", expectedExceptions = Exception.class)
    public void testGetSquareNegativeTriangle(ArrayList<Double> params){
        sideA = params.get(0);
        sideB = params.get(1);
        sideC = params.get(2);
        this.triangle = new Triangle(sideA, sideB, sideC);
        this.triangle.getSquare(); // expectedExceptions
    }

    @Test(dataProvider = "infNaNProvider", expectedExceptions = Exception.class)
    public void testGetSquareInfNaNTriangle(ArrayList<Double> params){
        sideA = params.get(0);
        sideB = params.get(1);
        sideC = params.get(2);
        this.triangle = new Triangle(sideA, sideB, sideC);
        this.triangle.getSquare(); // expectedExceptions
    }
}
