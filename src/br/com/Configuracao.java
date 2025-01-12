package br.com;

public class Configuracao {
    // Chave da API (mantenha isso privado)
    private static final String API_KEY = "Coloque sua Chave aqui"; // Substitua pela sua chave

    // URL base da API
    public static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    // Metodo para obter a URL completa com a moeda base
    public static String getApiUrl(String moedaBase) {
        return API_URL + moedaBase;
    }
}
