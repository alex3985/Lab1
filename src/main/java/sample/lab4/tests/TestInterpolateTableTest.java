package sample.lab4.tests;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class TestInterpolateTableTest {

    @Test
    public void TestInterpolateTable() {
        TestInterpolateTableSolver testInterpolateTable =
            new TestInterpolateTableSolver(
                new double[]{7, 8, 9, 10, 11},
                new double[]{1, 3.5, 11, 26.5, 53});

        double[][] substractionTable = testInterpolateTable
            .fillSubstractionYValuesTable();

        double[][] expectedSubstractionTable = {{1, 3.5, 11, 26.5, 53},
            {2.5, 7.5, 15.5, 26.5, 0},
            {5, 8, 11, 0, 0},
            {3, 3, 0, 0, 0},
            {0, 0, 0, 0, 0}};

        assertArrayEquals(substractionTable, expectedSubstractionTable);

    }


}
