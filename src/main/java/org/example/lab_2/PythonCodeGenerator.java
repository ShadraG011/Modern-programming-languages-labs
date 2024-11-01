package org.example.lab_2;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PythonCodeGenerator {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {


        PythonCodeGenerator pythonCodeGenerator = new PythonCodeGenerator();
        System.out.print("""
                1. Создать матрицу размером n*m, заполненную случайными числами от k до z
                2. Транспонировать матрицу: [[a11, ... , a1j], [a21, ... , a2j], ... [aij, ... , aij]]
                3. Написать функцию для получения подматрицы, состоящей из первых/последних n строк и первых/последних m столбцов;
                                
                Напишите один из предложеных шаблонов, по которому необходимо создать код: """);
        String pattern = sc.nextLine();
        System.out.println("\nКод на Python:\n" + pythonCodeGenerator.generateCodeForPython(pattern));

    }

    public String generateCodeForPython(String pattern) {
        if (pattern.contains("Создать")) {
            return generateCodeForMatrix(pattern);
        } else if (pattern.contains("Транспонировать")) {
            return generateCodeForMatrixTransportation(pattern);
        } else if (pattern.contains("подматрицы")) {
            return generateCodeForSubMatrix(pattern);
        } else {
            return "# Шаблон не распознан";
        }
    }

    /**
     * Напиши функцию для получения подматрицы, состоящей из первых/последних 2 строк и первых/последних 3 столбцов
     */
    private String generateCodeForSubMatrix(String pattern) {
        List<String> subPatterns = Arrays.asList(pattern.split(" "));
        String firstRow = "";
        String firstColumn = "";
        String lastRow = "";
        String lastColumn = "";

        for (int i = 0; i < subPatterns.size(); i++) {
            if (subPatterns.get(i).equals("первых")) {
                if (subPatterns.indexOf(subPatterns.get(i)) == i && lastRow.length() == 0) {
                    firstRow = subPatterns.get(i + 1);
                } else {
                    firstColumn = subPatterns.get(i + 1);
                    break;
                }
            } else if (subPatterns.get(i).equals("последних")) {
                if (subPatterns.indexOf(subPatterns.get(i)) == i && firstRow.length() == 0) {
                    lastRow = "-" + subPatterns.get(i + 1);
                } else {
                    lastColumn = "-" + subPatterns.get(i + 1);
                    break;
                }
            }
        }

        return String.format("""
                def show_sub_matrix(matrix):
                    sub_matrix = [row[%s:%s] for row in matrix[%s:%s]]
                    print("Полученная подматрица:")
                    [print(i) for i in sub_matrix]
                """, lastColumn, firstColumn, lastRow, firstRow);
    }

    /**
     * Создать матрицу размером 5x5, заполненную случайными числами от 1 до 100
     */
    private String generateCodeForMatrix(String pattern) {
        String[] matrixSize = pattern.split(" размером ")[1].split(",")[0].split("\\*");
        String[] fromTo = pattern.split(" от ")[1].split(" до ");


        return String.format("""
                import random
                matrix = [[random.randint(%s, %s + 1) for _ in range(%s)] for _ in range(%s)]
                print("Сгенерированная матрица:")
                [print(i) for i in matrix]
                """, fromTo[0], fromTo[1], matrixSize[0], matrixSize[1]);
    }

    /**
     * Транспонировать матрицу: [1, 2, 3], [4, 5, 6], [7, 8, 9]
     */
    private String generateCodeForMatrixTransportation(String pattern) {
        String matrixForTransportation = pattern.split("\\[\\[")[1].split("]]")[0];

        return String.format("""
                matrix = [[%s]]
                transposed = list(map(list, zip(*matrix)))
                print("Исходная матрица:")
                [print(i) for i in matrix]
                print("\\nТранспортированная матрица:")
                [print(i) for i in transposed]
                """, matrixForTransportation);
    }
}
