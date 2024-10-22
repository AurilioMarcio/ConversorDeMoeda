package br.com;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Converter {

    // Metodo para obter as taxas de câmbio da API
    public JsonObject obterTaxasDeCambio(String moedaBase) {
        try {
            // Obter a URL com a moeda base
            String url = Configuracao.getApiUrl(moedaBase);

            // Criar cliente HTTP
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Envia requisição e obter resposta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Imprimi a resposta completa da API para depuração
            //System.out.println("URL usada para a requisição: " + url);
            //System.out.println("Resposta da API: " + response.body());

            // Verifica se a resposta é válida
            if (response.statusCode() == 200) {
                // Analisa a resposta JSON
                JsonElement jsonElement = JsonParser.parseString(response.body());
                if (jsonElement.isJsonObject()) {
                    JsonObject jsonResponse = jsonElement.getAsJsonObject();
                    if (jsonResponse.has("conversion_rates")) {
                        return jsonResponse.getAsJsonObject("conversion_rates");
                    } else {
                        System.out.println("Chave 'conversion_rates' não encontrada no JSON.");
                    }
                } else {
                    System.out.println("Resposta não é um objeto JSON válido.");
                }
            } else {
                System.out.println("Erro na requisição. Código de status: " + response.statusCode());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
