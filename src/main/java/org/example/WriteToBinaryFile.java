package org.example;

import java.io.*;

public class WriteToBinaryFile {
    public static void main(String[] args) {
        int[] field = {1, 2, 3, 0, 2, 1, 3, 0, 3};

        try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream("field.bin"))) {
            int compactField = 0;
            int bitOffset = 2; // Смещение бита
            for (int i = 0; i < 9; i++) {
                compactField |= (field[i] << bitOffset);
                bitOffset -= 2;
                if (bitOffset < 0) {
                    outputStream.writeByte(compactField);
                    compactField = 0;
                    bitOffset = 2;
                }
            }
            outputStream.writeByte(compactField); // Записываем последний байт
            System.out.println("Запись в файл успешно выполнена.");
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}