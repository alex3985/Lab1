package sample;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import sample.lab1.GaussSeidel;
import sample.lab1.MethodChlesky;
import sample.lab2.Combination.CombinationMethodSolver;
import sample.lab2.Combination.Function2Displayer;
import sample.lab2.DihotomyMethodSolver;
import sample.lab2.Displayer;
import sample.lab2.FunctionDisplayer;
import sample.lab2.FunctionInterval;
import sample.lab2.IntervalDisplayer;
import sample.lab3.FaddeevLeVerrier;
import sample.lab3.FaddeevLeVerrierDisplayer;
import sample.lab3.KrylovMethodDisplayer;
import sample.lab3.KrylovMethodSolver;
import sample.lab3.KrylovMethodSolverVector;
import sample.lab3.KrylovMethodSolverVectorDisplayer;
import sample.lab4.GaussianInterpolateMethodSolver;
import sample.lab4.InterpolateMethodDisplayer;

public class Controller {

    final int variables = 3; //number of variables which user needs to find
    //Field for input data
    public TextField variableX11;
    public TextField variableX12;
    public TextField variableX13;
    public TextField variableX21;
    public TextField variableX22;
    public TextField variableX23;
    public TextField variableX31;
    public TextField variableX32;
    public TextField variableX33;
    public TextField variableD1;
    public TextField variableD2;
    public TextField variableD3;
    public TextField FunctionField;
    //Radiobuttons for chosen method
    public RadioButton Cholesky;//
    public RadioButton Gauss;
    public RadioButton DichotomyRadio;
    public RadioButton LiovaMethodRadio;
    //Buttons
    public Button SolveButn;//Show the solution
    public Button PrintButn;//Safe the report
    public Button AboutButn;//Show the theory of the methods
    //Thare will be a solution
    public TextArea SolutionAre;
    public TextArea Lab2Area;
    public TextField from;
    public TextField to;
    public TextArea lab3TextArea;
    public TextField lab3A11;
    public TextField lab3A12;
    public TextField lab3A13;
    public TextField lab3A21;
    public TextField lab3A22;
    public TextField lab3A23;
    public TextField lab3A31;
    public TextField lab3A32;
    public TextField lab3A33;
    public RadioButton Lab3Mathod2;
    public TextField lab4X1;
    public TextField lab4X3;
    public TextField lab4X4;
    public TextField lab4X5;
    public TextField lab4X2;
    public TextField lab4Y1;
    public TextField lab4Y2;
    public TextField lab4Y3;
    public TextField lab4Y4;
    public TextField lab4Y5;
    public TextField lab4ResultCoefPower0;
    public TextField lab4ResultCoefPower1;
    public TextField lab4ResultCoefPower2;
    public TextField lab4ResultCoefPower3;
    public TextField lab4X1TextField;
    public TextField lab4Y1TextField;
    public TextField lab4Tria_2Y1TextField;
    public TextField lab4Tria_1Y1TextField;
    public TextField lab4Tria_3Y1TextField;
    public TextField lab4Tria_4Y1TextField;
    public TextField lab4X2TextField;
    public TextField lab4X3TextField;
    public TextField lab4X4TextField;
    public TextField lab4Y2TextField;
    public TextField lab4Y3TextField;
    public TextField lab4Tria_1Y2TextField;
    public TextField lab4Tria_2Y2TextField;
    public TextField lab4Tria_3Y2TextField;
    public TextField lab4Y4TextField;
    public TextField lab4Tria_1Y3TextField;
    public TextField lab4Tria_2Y3TextField;
    public TextField lab4X5TextField;
    public TextField lab4Y5TextField;
    public TextField lab4Tria_1Y4TextField;
    public Button lab4Solve;


    double ArrayA[] = new double[variables * variables]; // Array of input data of the coefficients
    double ArrayD[] = new double[variables]; // Array of input data

    //
    MethodChlesky methodChlesky;

    public static FileChooser getFileChooserHtmL(String title) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html"));
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("All files (*.*)", "*.*"));
        fileChooser.setTitle(title);
        return fileChooser;
    }

    @FXML //Action for the button "Solve"
    private void ActionForSolution() {
        if (!isNotNumberAndEmpty()) //Checking a field for is not number and empty
        {
            if (isAllRadioButtonsOff(Cholesky, Gauss)) //Checking a radiobuttons for being selected
            {
                if (Cholesky.isSelected()) //My method is chosen
                {
                    FillArrays();
                    methodChlesky = new MethodChlesky(ArrayA, ArrayD);
                    if (methodChlesky.isZeroDiagonal()) {
                        Message("Method Chlesky demands no zero from a main diagonal");
                    } else if (methodChlesky.isZeroMinor()) {
                        Message(
                            "Method Chlesky demands no zero from the minors of a main diagonal");
                    } else {
                        methodChlesky.DoMethod();
                        SolutionAre.setText(FillAreaByChlessky());
                    }
                } else {
                    SolutionAre.setText(solveEquationByGaussSeidel());
                }
            }
        }

    }

    @FXML
    private void SaveAndOpenReport() {
        if (!isNotNumberAndEmpty()) //Checking a field for is not number and empty
        {
            if (isAllRadioButtonsOff(Cholesky, Gauss)) //Checking a radiobuttons for being selected
            {
                FillArrays();
            }
        }
        methodChlesky = new MethodChlesky(ArrayA, ArrayD);
        if (methodChlesky.isZeroDiagonal()) {
            Message("Method Chlesky demands no zero from a main diagonal");
        } else if (methodChlesky.isZeroMinor()) {
            Message("Method Chlesky demands no zero from the minors of a main diagonal");
        } else {
            methodChlesky.DoMethod();

            String Head = "<html>\n" +
                "<head>\n" +
                "\t<title>Report</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1 align=\"center\">Method Cholesky</h1>\n" +
                "<hr>\n" +
                "<h2> The converted system of equations in the matrix</h2>" +
                "<pre>" + FillAreaByChlessky() + "</pre>" +
                "<h1 align=\"center\">Method Gauss Seidel</h1>\n" +
                "<hr>\n" +
                "<h2>Solution</h2>" +
                "<pre>" + solveEquationByGaussSeidel() + "</pre>" +
                "</body>\n" +
                "</html>";

            FileChooser fileChooser = getFileChooserHtmL("Save Report");
            File file;
            if ((file = fileChooser.showSaveDialog(null)) != null) {
                try {
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                        bw.write(Head);
                        bw.close();
                        file.getCanonicalPath();
                        try {
                            Desktop.getDesktop().browse(file.toURI());
                        } catch (IOException e) {
                            Message(
                                "Sorry, but can't open a theory about Chlesky.\nPlease, try one more time.");
                        }
                    }
                } catch (IOException e) {
                    Message("Sorry, but can't save the report. Please, try one more time.");
                }
            }
        }
    }

    private void display(Set<Displayer> set, TextArea area) {
        StringBuilder builder = new StringBuilder();
        for (Displayer displayer : set) {
            builder.append(displayer.display())
                .append("\n");
        }
        area.setText(builder.toString());
    }

    @FXML
    public void SolveEquationLab2(ActionEvent actionEvent) {
        if (DichotomyRadio.isSelected()) {
            FunctionDisplayer functionDisplayer = new FunctionDisplayer();
            IntervalDisplayer intervalDisplayer = new IntervalDisplayer();
            Double a = Double.parseDouble(from.getText());
            Double b = Double.parseDouble(to.getText());
            DihotomyMethodSolver methodSolver =
                new DihotomyMethodSolver(FunctionField.getText(),
                    functionDisplayer, a, b);
            methodSolver.solve();
            LinkedHashSet<Displayer> displayers = new LinkedHashSet<>();
            displayers.add(functionDisplayer);
            display(displayers, Lab2Area);
        } else {
            Function2Displayer function2Displayer = new Function2Displayer();
            CombinationMethodSolver methodSolver = new CombinationMethodSolver(
                FunctionField.getText(),
                function2Displayer, new FunctionInterval(Double.parseDouble(from.getText()),
                Double.parseDouble(to.getText())));
            methodSolver.solve();
            LinkedHashSet<Displayer> displayers = new LinkedHashSet<>();
            displayers.add(function2Displayer);
            display(displayers, Lab2Area);
        }
    }

    //Open theory Cholesky
    @FXML
    private void OpenTheoryAboutCholesky() {
        File file =
            new File("src/sample/lab1/AboutMethodChlesky.htm");
        try {
            Desktop.getDesktop().browse(file.toURI());
        } catch (IOException e) {
            Message("Sorry, but can't open a theory about Chlesky.\nPlease, try one more time.");
        }

    }

    @FXML
    private void OpenTheoryAboutGaussSeidel() {

        File file = new File("src/sample/lab1/AboutGaussSeidel.pdf");
        try {
            Desktop.getDesktop().browse(file.toURI());
        } catch (IOException e) {
            e.printStackTrace();
            Message(
                "Sorry, but can't open a theory about Gauss Seidel.\nPlease, try one more time.");
        }
    }

    private String solveEquationByGaussSeidel() {
        FillArrays();
        double[][] M = new double[ArrayD.length][ArrayD.length + 1];
        for (int i = 0, h = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                M[i][j] = ArrayA[h];
                h++;
            }
            M[i][M.length] = ArrayD[i];
        }
        GaussSeidel gaussSeidel = new GaussSeidel(M);
        gaussSeidel.solve();
        StringBuilder sb = new StringBuilder();
        sb.append(gaussSeidel.getArrayB());
        sb.append(gaussSeidel.getArrayX());
        return sb.toString();
    }

    //Fill Soution area by Chlessky
    private String FillAreaByChlessky() {
        StringBuilder s = new StringBuilder();
        s.append("Matrix A:\n");
        for (int i = 0, j = 1; i < variables * variables; i++) {
            if ((i + 1) % (variables * j) == 1 && i != 0) {
                s.append("\n");
                j++;
            }
            s.append(round(ArrayA[i])).append(" ");
        }
        s.append("\n\nSolve the matrix B\n");
        s.append(methodChlesky.getSolutionOfB());
        s.append("Matrix B:\n");
        for (int i = 0, j = 1; i < variables * variables; i++) {
            if ((i + 1) % (variables * j) == 1 && i != 0) {
                s.append("\n");
                j++;
            }
            s.append(round(methodChlesky.getArrayB()[i])).append(" ");
        }
        s.append("\n\nSolve the matrix C\n");
        s.append(methodChlesky.getSolutionOfC());
        s.append("Matrix C:\n");
        for (int i = 0, j = 1; i < variables * variables; i++) {
            if ((i + 1) % (variables * j) == 1 && i != 0) {
                s.append("\n");
                j++;
            }
            s.append(round(methodChlesky.getArrayC()[i])).append(" ");
        }
        s.append("\n\nFind the all y:\n");
        s.append(methodChlesky.getSolutionOfY());
        s.append("Find the all x:\n");
        s.append(methodChlesky.getSolutionOfX());
        return s.toString();
    }

    //Arrays is fill of input data for method Chlessky
    private void FillArrays() {
        ArrayA[0] = Double.valueOf(variableX11.getText());
        ArrayA[1] = Double.valueOf(variableX12.getText());
        ArrayA[2] = Double.valueOf(variableX13.getText());
        ArrayA[3] = Double.valueOf(variableX21.getText());
        ArrayA[4] = Double.valueOf(variableX22.getText());
        ArrayA[5] = Double.valueOf(variableX23.getText());
        ArrayA[6] = Double.valueOf(variableX31.getText());
        ArrayA[7] = Double.valueOf(variableX32.getText());
        ArrayA[8] = Double.valueOf(variableX33.getText());
        ArrayD[0] = Double.valueOf(variableD1.getText());
        ArrayD[1] = Double.valueOf(variableD2.getText());
        ArrayD[2] = Double.valueOf(variableD3.getText());
    }

    //Method which checks a field for is not number and empty
    private boolean isNotNumberAndEmpty() {
        if (variableX11.getText().isEmpty() || variableX12.getText().isEmpty() || variableX13
            .getText().isEmpty()) {
            Message("One (or more) fields is empty. Please, fill out them all.");
            return true;
        }
        if (variableX21.getText().isEmpty() || variableX22.getText().isEmpty() || variableX23
            .getText().isEmpty()) {
            Message("One (or more) fields is empty. Please, fill out them all.");
            return true;
        }
        if (variableX31.getText().isEmpty() || variableX32.getText().isEmpty() || variableX33
            .getText().isEmpty()) {
            Message("One (or more) fields is empty. Please, fill out them all.");
            return true;
        }
        if (variableD1.getText().isEmpty() || variableD2.getText().isEmpty() || variableD3.getText()
            .isEmpty()) {
            Message("One (or more) fields is empty. Please, fill out them all.");
            return true;
        }
        if (!variableX11.getText().matches("-?\\d+\\.?\\d*") || !variableX12.getText()
            .matches("-?\\d+\\.?\\d*")
            || !variableX13.getText().matches("-?\\d+\\.?\\d*")) {
            Message("One (or more) fields is not number. Please, check them out.");
            return true;
        }
        if (!variableX21.getText().matches("-?\\d+\\.?\\d*") || !variableX22.getText()
            .matches("-?\\d+\\.?\\d*")
            || !variableX23.getText().matches("-?\\d+\\.?\\d*")) {
            Message("One (or more) fields is not number. Please, check them out.");
            return true;
        }
        if (!variableX31.getText().matches("-?\\d+\\.?\\d*") || !variableX32.getText()
            .matches("-?\\d+\\.?\\d*")
            || !variableX33.getText().matches("-?\\d+\\.?\\d*")) {
            Message("One (or more) fields is not number. Please, check them out.");
            return true;
        }
        if (!variableD1.getText().matches("-?\\d+\\.?\\d*") || !variableD2.getText()
            .matches("-?\\d+\\.?\\d*")
            || !variableD3.getText().matches("-?\\d+\\.?\\d*")) {
            Message("One (or more) fields is not number. Please, check them out.");
            return true;
        }
        return false;
    }

    //Method which checks a radiobuttons for not being selected
    private boolean isAllRadioButtonsOff(RadioButton... button) {
        for (RadioButton aButton : button) {
            if (aButton.isSelected()) {
                return true;
            }
        }
        Message("Please, choose the method of solution");
        return false;
    }

    //Dialog which says that user need to do
    private void Message(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Please, follow the instructions below!");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private String round(double d) {
        if (d == 0.0) {
            return String.valueOf(0);
        }
        d = d * 100;
        int i = (int) d;
        d = (double) i;
        d = d / 100;
        return String.valueOf(d);
    }

    @FXML
    public void solveEquationLab3(ActionEvent actionEvent) {
        Set<Displayer> displayers = new LinkedHashSet<>();
        List<Double> matrixA = this.getMatrixALab3();
        FaddeevLeVerrierDisplayer displayer = new FaddeevLeVerrierDisplayer();
        FaddeevLeVerrier faddeevLeVerrier = new FaddeevLeVerrier(matrixA);
        faddeevLeVerrier.solve(displayer);
        if (Lab3Mathod2.isSelected()) {
            displayers.add(displayer);
        } else {

            KrylovMethodSolver krylovMethodSolver = new KrylovMethodSolver(getMatrixLab3());
            KrylovMethodDisplayer krylovMethodDisplayer = new KrylovMethodDisplayer(
                krylovMethodSolver);
            KrylovMethodSolverVector krylovMethodSolverVector =
                new KrylovMethodSolverVector(getMatrixLab3(),
                    krylovMethodSolver.getCoefficientsP());
            KrylovMethodSolverVectorDisplayer vectorDisplayer =
                new KrylovMethodSolverVectorDisplayer(krylovMethodSolverVector,
                    krylovMethodSolver.getValues());
            displayers.add(krylovMethodDisplayer);
            displayers.add(vectorDisplayer);
        }
        display(displayers, lab3TextArea);
    }

    private double[][] getMatrixLab3() {
        double[][] matrixA = new double[3][3];
        matrixA[0][0] = (Double.valueOf(lab3A11.getText()));
        matrixA[0][1] = (Double.valueOf(lab3A12.getText()));
        matrixA[0][2] = (Double.valueOf(lab3A13.getText()));
        matrixA[1][0] = (Double.valueOf(lab3A21.getText()));
        matrixA[1][1] = (Double.valueOf(lab3A22.getText()));
        matrixA[1][2] = (Double.valueOf(lab3A23.getText()));
        matrixA[2][0] = (Double.valueOf(lab3A31.getText()));
        matrixA[2][1] = (Double.valueOf(lab3A32.getText()));
        matrixA[2][2] = (Double.valueOf(lab3A33.getText()));
        return matrixA;
    }

    private List<Double> getMatrixALab3() {
        List<Double> matrixA = new LinkedList<>();
        matrixA.add(Double.valueOf(lab3A11.getText()));
        matrixA.add(Double.valueOf(lab3A12.getText()));
        matrixA.add(Double.valueOf(lab3A13.getText()));
        matrixA.add(Double.valueOf(lab3A21.getText()));
        matrixA.add(Double.valueOf(lab3A22.getText()));
        matrixA.add(Double.valueOf(lab3A23.getText()));
        matrixA.add(Double.valueOf(lab3A31.getText()));
        matrixA.add(Double.valueOf(lab3A32.getText()));
        matrixA.add(Double.valueOf(lab3A33.getText()));
        return matrixA;
    }

    private double[] getXValuesOfTableInputLab4(){
        return getInitialValues(lab4X1, lab4X2, lab4X3, lab4X4, lab4X5);
    }

    private double[] getYValuesOfTableInputLab4(){
        return getInitialValues(lab4Y1, lab4Y2, lab4Y3, lab4Y4,lab4Y5);
    }

    private double[] getInitialValues(TextField lab41, TextField lab42, TextField lab43,
        TextField lab44, TextField lab45) {
        double[] initialY = new double[5];
        initialY[0] = Double.parseDouble(lab41.getText());
        initialY[1] = Double.parseDouble(lab42.getText());
        initialY[2] = Double.parseDouble(lab43.getText());
        initialY[3] = Double.parseDouble(lab44.getText());
        initialY[4] = Double.parseDouble(lab45.getText());
        return initialY;
    }

    private void setTableViewLab4(double[][] substractionYValuesTable, double[] initialX){
        lab4X1TextField.setText(String.valueOf(initialX[0]));
        lab4X2TextField.setText(String.valueOf(initialX[1]));
        lab4X3TextField.setText(String.valueOf(initialX[2]));
        lab4X4TextField.setText(String.valueOf(initialX[3]));
        lab4X5TextField.setText(String.valueOf(initialX[4]));
        lab4Y1TextField.setText(String.valueOf(substractionYValuesTable[0][0]));
        lab4Y2TextField.setText(String.valueOf(substractionYValuesTable[0][1]));
        lab4Y3TextField.setText(String.valueOf(substractionYValuesTable[0][2]));
        lab4Y4TextField.setText(String.valueOf(substractionYValuesTable[0][3]));
        lab4Y5TextField.setText(String.valueOf(substractionYValuesTable[0][4]));
        lab4Tria_1Y1TextField.setText(String.valueOf(substractionYValuesTable[1][0]));
        lab4Tria_1Y2TextField.setText(String.valueOf(substractionYValuesTable[1][1]));
        lab4Tria_1Y3TextField.setText(String.valueOf(substractionYValuesTable[1][2]));
        lab4Tria_1Y4TextField.setText(String.valueOf(substractionYValuesTable[1][3]));
        lab4Tria_2Y1TextField.setText(String.valueOf(substractionYValuesTable[2][0]));
        lab4Tria_2Y2TextField.setText(String.valueOf(substractionYValuesTable[2][1]));
        lab4Tria_2Y3TextField.setText(String.valueOf(substractionYValuesTable[2][2]));
        lab4Tria_3Y1TextField.setText(String.valueOf(substractionYValuesTable[3][0]));
        lab4Tria_3Y2TextField.setText(String.valueOf(substractionYValuesTable[3][1]));
        lab4Tria_4Y1TextField.setText(String.valueOf(substractionYValuesTable[4][0]));
    }

    private void setCoefficientsLab4( List<Double> coefficientsLab4){
       lab4ResultCoefPower0.setText(String.valueOf(coefficientsLab4.get(0)));
        lab4ResultCoefPower1.setText(String.valueOf(coefficientsLab4.get(1)));
        lab4ResultCoefPower2.setText(String.valueOf(coefficientsLab4.get(2)));
        lab4ResultCoefPower3.setText(String.valueOf(coefficientsLab4.get(3)));
    }

    @FXML
    public void calculateInterpolationCoefficient(ActionEvent actionEvent) {
        GaussianInterpolateMethodSolver solver =
            new GaussianInterpolateMethodSolver(getXValuesOfTableInputLab4(),getYValuesOfTableInputLab4());
        InterpolateMethodDisplayer displayer = new InterpolateMethodDisplayer(solver);
        List<Double> coefficientsOfPolinomByOrder = displayer.getCoefficientsOfPolinomByOrder();
        double[][] substractionYValuesTable = displayer.getSubstractionYValuesTable();
        setTableViewLab4(substractionYValuesTable,getXValuesOfTableInputLab4());
        setCoefficientsLab4(coefficientsOfPolinomByOrder);

    }
}
