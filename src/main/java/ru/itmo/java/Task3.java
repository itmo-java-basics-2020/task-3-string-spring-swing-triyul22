package ru.itmo.java;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Task3 {

    /**
     * Напишите функцию, которая принимает массив целых чисел и циклически сдвигает элементы этого массива вправо:
     * A[0] -> A[1], A[1] -> A[2] .. A[length - 1] -> A[0].
     * Если инпут равен null - вернуть пустой массив
     */
    int[] getShiftedArray(int[] inputArray) {
        if (inputArray == null || inputArray.length == 0) {
            return new int[]{};
        }
        int length = inputArray.length;
        int value = inputArray[0];
        for (int i = 1; i <= length; i++) {
            int temp = inputArray[(i + length) % length];
            inputArray[(i + length) % length] = value;
            value = temp;
        }
        return inputArray;
    }

    /**
     * Напишите функцию, которая принимает массив целых чисел и возвращает максимальное значение произведения двух его элементов.
     * Если массив состоит из одного элемента, то функция возвращает этот элемент.
     * <p>
     * Если входной массив пуст или равен null - вернуть 0
     * <p>
     * Пример: 2 4 6 -> 24
     */
    int getMaxProduct(int[] inputArray) {
        if (inputArray == null || inputArray.length == 0) {
            return 0;
        }
        if (inputArray.length == 1) {
            return inputArray[0];
        }
        Arrays.sort(inputArray);
        return inputArray[inputArray.length - 2] * inputArray[inputArray.length - 1];
    }

    /**
     * Напишите функцию, которая вычисляет процент символов 'A' и 'B' (латиница) во входной строке.
     * Функция не должна быть чувствительна к регистру символов.
     * Результат округляйте путем отбрасывания дробной части.
     * <p>
     * Пример: acbr -> 50
     */
    int getABpercentage(String input) {
        if (input == null || input.length() == 0){
            return 0;
        }
        char[] arr = input.toCharArray();
        int count = 0;
        for (char c : arr) {
            if (c == 'A' || c == 'B' || c == 'a' || c == 'b') {
                count++;
            }
        }
        return  count * 100 / input.length();
    }

    /**
     * Напишите функцию, которая определяет, является ли входная строка палиндромом
     */
    boolean isPalindrome(String input) {
        if (input==null){
            return false;
        }
        char[] arr = input.toCharArray();
        for (int i = 0; i < arr.length / 2; i++) {
            if (arr[i] != arr[arr.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Напишите функцию, которая принимает строку вида "bbcaaaak" и кодирует ее в формат вида "b2c1a4k1",
     * где группы одинаковых символов заменены на один символ и кол-во этих символов идущих подряд в строке
     */
    String getEncodedString(String input) {
        if (input == null || input.length() == 0){
            return "";
        }
        char[] arr = input.toCharArray();
        int count = 1;
        StringBuilder res = new StringBuilder();
        char item = arr[0];
        if (arr.length ==1){
            return String.valueOf(item)+1;
        }
        for (int i = 1; i < arr.length; i++) {
            if (item == arr[i]) {
                count++;
                if (i == arr.length-1){
                    res.append(item).append(count);
                }
            } else {
                res.append(item).append(count);
                count=1;
                if (i == arr.length-1){
                    res.append(arr[i]).append(count);
                }
            }
            item = arr[i];
        }
        return res.toString();
    }

    /**
     * Напишите функцию, которая принимает две строки, и возвращает true, если одна может быть перестановкой (пермутатсией) другой.
     * Строкой является последовательность символов длинной N, где N > 0
     * Примеры:
     * isPermutation("abc", "cba") == true;
     * isPermutation("abc", "Abc") == false;
     */
    boolean isPermutation(String one, String two) {
        if (one == null || two == null || one.length() == 0 || two.length() == 0){
            return false;
        }
        char[] arr = one.toCharArray();
        StringBuilder sb = new StringBuilder(two);
        for (char c : arr) {
            String str = Character.toString(c);
            if (two.contains(str)) {
                sb.deleteCharAt(sb.indexOf(str));
            }
        }
        return sb.length() == 0;
    }

    /**
     * Напишите функцию, которая принимает строку и возвращает true, если она состоит из уникальных символов.
     * Из дополнительной памяти (кроме примитивных переменных) можно использовать один массив.
     * Строкой является последовательность символов длинной N, где N > 0
     */
    boolean isUniqueString(String s) {
        if (s == null || s.length() == 0){
            return false;
        }
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Напишите функцию, которая транспонирует матрицу. Только квадратные могут быть на входе.
     * <p>
     * Если входной массив == null - вернуть пустой массив
     */
    int[][] matrixTranspose(int[][] m) {
        if (m == null || m[0].length==0 || m[1].length == 0) {
            return new int[][]{{},{}};
        }
        for (int i = 0; i < m.length; i++) {
            for (int j = i; j < m.length; j++) {
                int temp = m[i][j];
                m[i][j] = m[j][i];
                m[j][i] = temp;
            }
        }
        return m;
    }

    /**
     * Напиишите функцию, принимающую массив строк и символ-разделитель,
     * а возвращает одну строку состоящую из строк, разделенных сепаратором.
     * <p>
     * Запрещается пользоваться функций join
     * <p>
     * Если сепаратор == null - считайте, что он равен ' '
     * Если исходный массив == null -  вернуть пустую строку
     */
    String concatWithSeparator(String[] inputStrings, Character separator) {
        if (inputStrings == null || inputStrings.length == 0) {
            return "";
        }
        if (separator == null) {
            separator = ' ';
        }
        StringBuilder res = new StringBuilder();
        for (String s : inputStrings) {
            res.append(s).append(separator);
        }
        return res.toString().substring(0, res.length()-1);
    }

    /**
     * Напишите функцию, принимающую массив строк и строку-перфикс и возвращающую кол-во строк массива с данным префиксом
     */
    int getStringsStartWithPrefix(String[] inputStrings, String prefix) {
        if (inputStrings == null|| inputStrings.length==0 || prefix == null){
            return 0;
        }
        int count = 0;
        for(String s : inputStrings){
            if(s.startsWith(prefix)){
                count++;
            }
        }
        return count;
    }
}
