package org.example.lab_1;

import com.opencsv.CSVReader;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    private final Random rd = new Random();
    private final String[] letters = {"A", "B", "C", "D"};
    private static final Scanner sc = new Scanner(System.in);
    private static String path = "";

    private final List<Double> resultA = new ArrayList<>();
    private final List<Double> resultB = new ArrayList<>();
    private final List<Double> resultC = new ArrayList<>();
    private final List<Double> resultD = new ArrayList<>();

    public static void main(String[] args) {
        System.out.print("Введите абсолютный путь до папки в которой будут содержаться файлы в формате \"ПУТЬ/ДО/ПАПКИ\": ");
        path = sc.nextLine();
        Main main = new Main();
//
//        try {
//            main.createFiles(path);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        long startTime = System.currentTimeMillis();

        Thread thread1 = new Thread(() -> {
            double[] resultGroupA = main.calculationByGroup("A", String.format("%s/file %d.csv", path, 0));
            double[] resultGroupB = main.calculationByGroup("B", String.format("%s/file %d.csv", path, 0));
            double[] resultGroupC = main.calculationByGroup("C", String.format("%s/file %d.csv", path, 0));
            double[] resultGroupD = main.calculationByGroup("D", String.format("%s/file %d.csv", path, 0));
            main.commonMedians(resultGroupA[0], resultGroupB[0], resultGroupC[0], resultGroupD[0]);
            System.out.println("-------------------------------------------------");
            System.out.printf("Расчеты из файла \"file %d.csv\"%n", 0);
            main.showResultGroup(resultGroupA, resultGroupB, resultGroupC, resultGroupD);
            System.out.println("-------------------------------------------------");


        });

        Thread thread2 = new Thread(() -> {
            double[] resultGroupA = main.calculationByGroup("A", String.format("%s/file %d.csv", path, 1));
            double[] resultGroupB = main.calculationByGroup("B", String.format("%s/file %d.csv", path, 1));
            double[] resultGroupC = main.calculationByGroup("C", String.format("%s/file %d.csv", path, 1));
            double[] resultGroupD = main.calculationByGroup("D", String.format("%s/file %d.csv", path, 1));
            main.commonMedians(resultGroupA[0], resultGroupB[0], resultGroupC[0], resultGroupD[0]);
            System.out.println("-------------------------------------------------");
            System.out.printf("Расчеты из файла \"file %d.csv\"%n", 1);
            main.showResultGroup(resultGroupA, resultGroupB, resultGroupC, resultGroupD);
            System.out.println("-------------------------------------------------");
        });

        Thread thread3 = new Thread(() -> {
            double[] resultGroupA = main.calculationByGroup("A", String.format("%s/file %d.csv", path, 2));
            double[] resultGroupB = main.calculationByGroup("B", String.format("%s/file %d.csv", path, 2));
            double[] resultGroupC = main.calculationByGroup("C", String.format("%s/file %d.csv", path, 2));
            double[] resultGroupD = main.calculationByGroup("D", String.format("%s/file %d.csv", path, 2));
            main.commonMedians(resultGroupA[0], resultGroupB[0], resultGroupC[0], resultGroupD[0]);
            System.out.println("-------------------------------------------------");
            System.out.printf("Расчеты из файла \"file %d.csv\"%n", 2);
            main.showResultGroup(resultGroupA, resultGroupB, resultGroupC, resultGroupD);
            System.out.println("-------------------------------------------------");

        });

        Thread thread4 = new Thread(() -> {
            double[] resultGroupA = main.calculationByGroup("A", String.format("%s/file %d.csv", path, 3));
            double[] resultGroupB = main.calculationByGroup("B", String.format("%s/file %d.csv", path, 3));
            double[] resultGroupC = main.calculationByGroup("C", String.format("%s/file %d.csv", path, 3));
            double[] resultGroupD = main.calculationByGroup("D", String.format("%s/file %d.csv", path, 3));
            main.commonMedians(resultGroupA[0], resultGroupB[0], resultGroupC[0], resultGroupD[0]);
            System.out.println("-------------------------------------------------");
            System.out.printf("Расчеты из файла \"file %d.csv\"%n", 3);
            main.showResultGroup(resultGroupA, resultGroupB, resultGroupC, resultGroupD);
            System.out.println("-------------------------------------------------");

        });

        Thread thread5 = new Thread(() -> {
            double[] resultGroupA = main.calculationByGroup("A", String.format("%s/file %d.csv", path, 4));
            double[] resultGroupB = main.calculationByGroup("B", String.format("%s/file %d.csv", path, 4));
            double[] resultGroupC = main.calculationByGroup("C", String.format("%s/file %d.csv", path, 4));
            double[] resultGroupD = main.calculationByGroup("D", String.format("%s/file %d.csv", path, 4));
            main.commonMedians(resultGroupA[0], resultGroupB[0], resultGroupC[0], resultGroupD[0]);
            System.out.println("-------------------------------------------------");
            System.out.printf("Расчеты из файла \"file %d.csv\"%n", 4);
            main.showResultGroup(resultGroupA, resultGroupB, resultGroupC, resultGroupD);
            System.out.println("-------------------------------------------------");

        });

        Thread thread6 = new Thread(() -> {
            double[] resultGroupA = main.calculationByGroup("A", String.format("%s/file %d.csv", path, 5));
            double[] resultGroupB = main.calculationByGroup("B", String.format("%s/file %d.csv", path, 5));
            double[] resultGroupC = main.calculationByGroup("C", String.format("%s/file %d.csv", path, 5));
            double[] resultGroupD = main.calculationByGroup("D", String.format("%s/file %d.csv", path, 5));
            main.commonMedians(resultGroupA[0], resultGroupB[0], resultGroupC[0], resultGroupD[0]);
            System.out.println("-------------------------------------------------");
            System.out.printf("Расчеты из файла \"file %d.csv\"%n", 5);
            main.showResultGroup(resultGroupA, resultGroupB, resultGroupC, resultGroupD);
            System.out.println("-------------------------------------------------");

        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();


        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
            thread6.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime) / 1000;

        System.out.println("-------------------------------------------------");
        System.out.println("Расчеты общих медиан и средних отклонений: ");
        main.showResultGroup(
                main.calculateGroupParameters(main.resultA),
                main.calculateGroupParameters(main.resultB),
                main.calculateGroupParameters(main.resultC),
                main.calculateGroupParameters(main.resultD));
        System.out.println("-------------------------------------------------\n");
        System.out.println("Время выполнения (сек.): " + duration);
    }

    public void showResultGroup(double[] resultGroupA, double[] resultGroupB, double[] resultGroupC, double[] resultGroupD) {
        System.out.printf("Медианное значение в группе А: %s%n", resultGroupA[0]);
        System.out.printf("Среднее отклонение в группе А: %s%n", resultGroupA[1]);
        System.out.printf("\nМедианное значение в группе B: %s%n", resultGroupB[0]);
        System.out.printf("Среднее отклонение в группе B: %s%n", resultGroupB[1]);
        System.out.printf("\nМедианное значение в группе C: %s%n", resultGroupC[0]);
        System.out.printf("Среднее отклонение в группе C: %s%n", resultGroupC[1]);
        System.out.printf("\nМедианное значение в группе D: %s%n", resultGroupD[0]);
        System.out.printf("Среднее отклонение в группе D: %s%n", resultGroupD[1]);
    }

    private void commonMedians(double medianA, double medianB, double medianC, double medianD) {
        resultA.add(medianA);
        resultB.add(medianB);
        resultC.add(medianC);
        resultD.add(medianD);
    }

    public void createFiles(String path) throws IOException {
        for (int i = 0; i < 6; i++) {
            String dataToWrite = generateData();
            FileOutputStream fos = new FileOutputStream(String.format("%s/file %d.csv", path, i));
            fos.write(dataToWrite.getBytes(StandardCharsets.UTF_8));
            fos.flush();
            fos.close();
        }
    }

    private String generateData() {
        StringBuilder data = new StringBuilder();
        for (long i = 0; i < Math.pow(10, 8); i++) {
            data.append(rd.nextDouble(0, 1001)).append(",").append(letters[rd.nextInt(4)]).append("\n");
        }
        return data.toString();
    }

    public double[] calculationByGroup(String group, String path) {
        List<Double> groupNumbers = readFromCsv(group, path);
        return calculateGroupParameters(groupNumbers);
    }

    private double[] calculateGroupParameters(List<Double> groupNumbers) {
        double median = calculateMedian(groupNumbers);
        double mean = calculateMean(groupNumbers);
        double meanDeviation = calculateMeanDeviation(groupNumbers, mean);
        return new double[]{median, meanDeviation};
    }


    private static double calculateMean(List<Double> values) {
        long sum = 0;
        for (double value : values) {
            sum += value;
        }
        return (double) sum / values.size();
    }

    private static double calculateMeanDeviation(List<Double> values, double mean) {
        double sum = 0;
        for (double value : values) {
            sum += Math.abs(value - mean);
        }
        return sum / values.size();
    }

    private double calculateMedian(List<Double> values) {
        Collections.sort(values);
        int size = values.size();
        if (size % 2 == 0) {
            return (values.get(size / 2 - 1) + values.get(size / 2)) / 2.0;
        } else {
            return values.get(size / 2);
        }
    }

    private List<Double> readFromCsv(String group, String path) {
        List<Double> lines = null;
        try {
            FileReader filereader = new FileReader(path);

            CSVReader csvReader = new CSVReader(filereader);
            lines = new ArrayList<>();
            String[] tmp;
            while ((tmp = csvReader.readNext()) != null) {
                if (tmp[1].equals(group)) {
                    lines.add(Double.valueOf(tmp[0]));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }
}