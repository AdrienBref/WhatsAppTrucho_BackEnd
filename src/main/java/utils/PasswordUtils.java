package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {

    // Método para aplicar el hashing a la contraseña (sin salt).
    public static String hashPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashedPasswordBytes = messageDigest.digest(password.getBytes());
            return bytesToHex(hashedPasswordBytes);
        } catch (NoSuchAlgorithmException e) {
            // Manejar la excepción en caso de que no se encuentre el algoritmo de hashing.
            e.printStackTrace();
            return null;
        }
    }
    // Método para convertir un array de bytes a una cadena hexadecimal.
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}