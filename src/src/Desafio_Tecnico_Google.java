package src;

import java.util.List;

public class Desafio_Tecnico_Google {

    public static int levenshteinDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }

        
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                int cost = word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1;
                dp[i][j] = Math.min(
                    Math.min(dp[i - 1][j] + 1,    
                             dp[i][j - 1] + 1),   
                    dp[i - 1][j - 1] + cost      
                );
            }
        }

        return dp[len1][len2];
    }

    public static String corrigirPalavra(List<String> dicionario, String palavraDigitada) {
        if (dicionario.contains(palavraDigitada)) {
            return palavraDigitada;
        }

        String palavraCorrigida = null;
        int menorDistancia = 3;

        for (String palavra : dicionario) {
            int distancia = levenshteinDistance(palavraDigitada, palavra);
            if (distancia < menorDistancia) {
                menorDistancia = distancia;
                palavraCorrigida = palavra;
            }
        }

        
        return palavraCorrigida;
    }

    public static void main(String[] args) {
        List<String> dicionario = List.of("casa", "cama", "carro", "gato", "pato");
        String[] testes = { "casa", "caso", "gatu", "xyz" };

        for (String palavra : testes) {
            System.out.println(
                "Entrada: " + palavra +
                " -> Correção: " + corrigirPalavra(dicionario, palavra)
            );
        }
    }

}
