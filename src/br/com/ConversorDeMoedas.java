import java.util.Scanner;

import br.com.Converter;
import com.google.gson.JsonObject;

public class ConversorDeMoedas {

    public static void exibirMenu() {
        System.out.println("Escolha uma conversão de moeda:");
        System.out.println("1. USD para EUR");
        System.out.println("2. USD para BRL");
        System.out.println("3. USD para JPY");
        System.out.println("4. USD para GBP");
        System.out.println("5. EUR para USD");
        System.out.println("6. BRL para USD");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        exibirMenu();
        int escolha = scanner.nextInt();

        String moedaBase = "USD";
        String moedaAlvo = "";
        switch (escolha) {
            case 1:
                moedaBase = "USD";
                moedaAlvo = "EUR";
                break;
            case 2:
                moedaBase = "USD";
                moedaAlvo = "BRL";
                break;
            case 3:
                moedaBase = "USD";
                moedaAlvo = "JPY";
                break;
            case 4:
                moedaBase = "USD";
                moedaAlvo = "GBP";
                break;
            case 5:
                moedaBase = "EUR";
                moedaAlvo = "USD";
                break;
            case 6:
                moedaBase = "BRL";
                moedaAlvo = "USD";
                break;
            default:
                System.out.println("Opção inválida.");
                return;
        }

        // Instanciar o serviço de conversão
        Converter service = new Converter();
        JsonObject taxas = service.obterTaxasDeCambio(moedaBase);

        // Exibir a taxa de conversão
        if (taxas != null && taxas.has(moedaAlvo)) {
            System.out.println("A taxa de câmbio de " + moedaBase + " para " + moedaAlvo + " é: " + taxas.get(moedaAlvo).getAsDouble());
        } else {
            System.out.println("Não foi possível obter a taxa de câmbio.");
        }
    }
}
