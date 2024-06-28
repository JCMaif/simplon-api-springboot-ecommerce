package fr.simplon.api.model;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    public static int nbYear(int p0, double percent, int aug, int p) {
        // your code
        int years = 0;
        while (p0 < p) {
            p0 += p0 * percent / 100 + aug;
            years++;
        }
        return years;
    }


public class Solution {

    public int solution(int number) {
        //TODO: Code stuff here
        // cherche les multiples de de 3 et 5 %3==0 et %5==0
        int somme = 0;

        for (int i = 0; i <= number; i++){
            if (i % 3 == 0 || i % 5 == 0) {
                somme += i;
            }

        }
        return somme;
    }

}


    public class Kata {
        public static int[][] twosDifference(int[] array) {
            // Initialiser une liste pour stocker les résultats
            List<int[]> result = new ArrayList<>();

            // Trier le tableau
            Arrays.sort(array);

            // Parcourir le tableau avec l'indice i
            for (int i = 0; i < array.length; i++) {
                // Parcourir le tableau avec l'indice j
                for (int j = i + 1; j < array.length; j++) {
                    // Si la différence entre les éléments est de 2, ajouter la paire à la liste de résultats
                    if (array[j] - array[i] == 2) {
                        result.add(new int[]{array[i], array[j]});
                    } else if (array[j] - array[i] > 2) {
                        // Puisque le tableau est trié, on peut arrêter la boucle intérieure plus tôt
                        break;
                    }
                }
            }

            // Convertir la liste de résultats en tableau 2D
            int[][] resultArray = new int[result.size()][];
            for (int i = 0; i < result.size(); i++) {
                resultArray[i] = result.get(i);
            }

            return resultArray;
        }
    }

    public class Kata {
        public static String createPhoneNumber(int[] numbers) {
            // Your code here!
           StringBuilder result = new StringBuilder();
            result.append("(");
            result.append(numbers[0]);
            result.append(numbers[1]);
            result.append(numbers[2]);
            result.append(") ");
            result.append(numbers[3]);
            result.append(numbers[4]);
            result.append(numbers[5]);
            result.append("-");
            result.append(numbers[6]);
            result.append(numbers[7]);
            result.append(numbers[8]);
            result.append(numbers[9]);

            return result.toString();
        }


    }


}