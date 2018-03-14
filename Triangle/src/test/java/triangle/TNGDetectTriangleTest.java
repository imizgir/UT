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

// Класс для проверки метода определения типа прямоугольника detectTriangle()
public class TNGDetectTriangleTest {

    private Triangle triangle;
    private double a = 2.0;
    private double b = 3.0;
    private double c = 4.0;
    private double sideA;
    private double sideB;
    private double sideC;

    final int TR_EQUILATERAL = 3;            // 0011 равносторонний
    final int TR_ISOSCELES = 2;              // 0010 равнобедренный
    final int TR_ORDINARY = 4;               // 0100 обычный
    final int TR_RECTANGULAR = 8;            // 1000 прямоугольный
    final int TR_RECTANGULAR_ISOSCELES = 10; // 1010 прямоугольный и равнобедренный

    @DataProvider(name = "negativeProvider")
    public Object[][] negativeProviderData() {
        return new Object[][]
                {
                        // NegativeSide
                        {new ArrayList<Double>(Arrays.asList(-a, b, c)), 0},
                        {new ArrayList<Double>(Arrays.asList(a, -b, c)), 0},
                        {new ArrayList<Double>(Arrays.asList(a, b, -c)), 0},
                        {new ArrayList<Double>(Arrays.asList(-a, -b, -c)), 0},
                        {new ArrayList<Double>(Arrays.asList(-a, -a, -a)), 0},
                        // ZeroSide
                        {new ArrayList<Double>(Arrays.asList(0.0, b, c)), 0},
                        {new ArrayList<Double>(Arrays.asList(a, 0.0, c)), 0},
                        {new ArrayList<Double>(Arrays.asList(a, b, 0.0)), 0},
                        // TooBigSide
                        {new ArrayList<Double>(Arrays.asList(a, b, a+b+1)), 0},
                        {new ArrayList<Double>(Arrays.asList(a, a+c+1, c)), 0},
                        {new ArrayList<Double>(Arrays.asList(b+c+1, b, c)), 0},
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
                        {new ArrayList<Double>(Arrays.asList(a, a, a)), TR_EQUILATERAL},
                        // ISOSCELES
                        {new ArrayList<Double>(Arrays.asList(a, b, b)), TR_ISOSCELES},
                        {new ArrayList<Double>(Arrays.asList(b, a, b)), TR_ISOSCELES},
                        {new ArrayList<Double>(Arrays.asList(b, b, a)), TR_ISOSCELES},
                        // ORDINARY
                        {new ArrayList<Double>(Arrays.asList(a, b, c)), TR_ORDINARY},
                        {new ArrayList<Double>(Arrays.asList(b, c, a)), TR_ORDINARY},
                        {new ArrayList<Double>(Arrays.asList(c, a, b)), TR_ORDINARY},
                        // RECTANGULAR
                        {new ArrayList<Double>(Arrays.asList(a, b, sqrt(a*a + b*b))), TR_RECTANGULAR},
                        {new ArrayList<Double>(Arrays.asList(sqrt(a*a + b*b), a, b)), TR_RECTANGULAR},
                        {new ArrayList<Double>(Arrays.asList(b, sqrt(a*a + b*b), a)), TR_RECTANGULAR},
                        // RECTANGULAR & ISOSCELES
                        {new ArrayList<Double>(Arrays.asList(a, a, sqrt(2*a*a))), TR_RECTANGULAR_ISOSCELES},
                        {new ArrayList<Double>(Arrays.asList(sqrt(2*a*a), a, a)), TR_RECTANGULAR_ISOSCELES},
                        {new ArrayList<Double>(Arrays.asList(a, sqrt(2*a*a), a)), TR_RECTANGULAR_ISOSCELES},
                };
    }


    @Test(dataProvider = "correctProvider")
    public void testDetectTriangle(ArrayList<Double> params, int expextedResult){
        sideA = params.get(0);
        sideB = params.get(1);
        sideC = params.get(2);
        this.triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertEquals(this.triangle.detectTriangle(), expextedResult, "[Fail with: " + sideA + "  " + sideB + "  " + sideC + "]");
    }

    @Test(dataProvider = "negativeProvider", expectedExceptions = Exception.class)
    public void testDetectNegativeTriangle(ArrayList<Double> params, int expextedResult){
        sideA = params.get(0);
        sideB = params.get(1);
        sideC = params.get(2);
        this.triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertEquals(this.triangle.detectTriangle(), expextedResult, "[Fail with: " + sideA + "  " + sideB + "  " + sideC + "]"); // expectedExceptions
    }

    @Test(dataProvider = "infNaNProvider", expectedExceptions = Exception.class)
    public void testDetectInfNaNTriangle(ArrayList<Double> params){
        sideA = params.get(0);
        sideB = params.get(1);
        sideC = params.get(2);
        this.triangle = new Triangle(sideA, sideB, sideC);
        this.triangle.detectTriangle(); // expectedExceptions
    }


}
