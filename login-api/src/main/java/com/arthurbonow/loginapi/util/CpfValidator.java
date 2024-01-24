package com.arthurbonow.loginapi.util;

//Este arquivo define a classe CpfValidator que é usada para validar o CPF (Cadastro de Pessoas Físicas), um número de
// identificação de indivíduos no Brasil. A classe contém métodos para verificar se o CPF é válido de acordo com seu
// formato e dígitos de verificação.
public class CpfValidator {

    // A expressão regular para o formato do CPF.
    private static final String CPF_REGEX = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";

    // Método para verificar se um CPF é válido.
    public static boolean cpfIsValid(String cpf) {
        // Verifica se o CPF é nulo ou não está no formato correto.
        if (cpf == null || !cpf.matches(CPF_REGEX)) {
            return false;
        }

        // Remove caracteres não numéricos do CPF.
        String cpfWithoutNonDigits = cpf.replaceAll("\\D", "");

        // Verifica se o CPF tem o comprimento correto e se todos os dígitos não são iguais.
        if (cpfWithoutNonDigits.length() != 11 || cpfWithoutNonDigits.chars().allMatch(n -> n == cpfWithoutNonDigits.charAt(0))) {
            return false;
        }

        // Verifica se os dígitos de verificação do CPF são válidos.
        return calculateVerificationDigit(cpfWithoutNonDigits.substring(0, 9)).equals(cpfWithoutNonDigits.substring(9, 11));
    }

    // Método para calcular os dígitos de verificação do CPF.
    private static String calculateVerificationDigit(String cpf) {
        int firstDigit = calculateDigit(cpf, 10);
        int secondDigit = calculateDigit(cpf + firstDigit, 11);
        return String.valueOf(firstDigit) + secondDigit;
    }

    // Método para calcular um dígito de verificação do CPF.
    private static int calculateDigit(String cpf, int factor) {
        int sum = 0;
        for (int i = 0; i < cpf.length(); i++, factor--) {
            int digit = Character.getNumericValue(cpf.charAt(i));
            sum += digit * factor;
        }
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }
}