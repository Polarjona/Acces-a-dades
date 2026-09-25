import java.io.*;
import java.util.Scanner;

public class XifradorCesar {

    public static void main(String[] args) {
        System.out.println("Benvingut al Xifrador Cèsar!");
        Scanner scanner = new Scanner(System.in);
        int clau = 0;

        System.out.print("Introdueix la clau numèrica per xifrar/desxifrar: ");
        if (scanner.hasNextInt()) {
            clau = scanner.nextInt();
        } else {
            System.out.println("Error: No has introduït un nombre sencer. Es farà servir la clau per defecte 3.");
            clau = 3;
        }

        System.out.println("\nProgrés: Iniciant processos...\n");

        xifrarFitxer("PT2/entrada.txt", "PT2/xifrat.txt", clau);
        desxifrarFitxer("PT2/xifrat.txt", "PT2/desxifrat.txt", clau);

        System.out.println("\nProgrés: Qualsevol operació ha finalitzat.");
    }

    private static void xifrarFitxer(String entrada, String sortida, int clau) {
        System.out.println("Progrés: Intentant xifrar el fitxer '" + entrada + "' cap a '" + sortida + "'...");

        try (
            BufferedReader br = new BufferedReader(new FileReader(entrada));
            BufferedWriter bw = new BufferedWriter(new FileWriter(sortida))
        ) {
            String linia;
            while ((linia = br.readLine()) != null) {
                // Invertir
                String liniaInvertida = new StringBuilder(linia).reverse().toString();

                // Aplicar xifrat cèsar a cada caràcter
                StringBuilder liniaXifrada = new StringBuilder();
                for (int i = 0; i < liniaInvertida.length(); i++) {
                    char c = liniaInvertida.charAt(i);
                    liniaXifrada.append((char)(c + clau));
                }

                bw.write(liniaXifrada.toString());
                bw.newLine();
            }
            System.out.println("Progrés: Xifrat completat amb èxit. (Fitxer '" + sortida + "' generat)");

        } catch (FileNotFoundException e) {
            System.out.println("Error: Fitxer no trobat a la fase de xifrat (" + e.getMessage() + ")");
        } catch (IOException e) {
            System.out.println("Error d'entrada/sortida durant el xifrat (" + e.getMessage() + ")");
        }
    }

    private static void desxifrarFitxer(String entrada, String sortida, int clau) {
        System.out.println("Progrés: Intentant desxifrar el fitxer '" + entrada + "' cap a '" + sortida + "'...");

        try (
            BufferedReader br = new BufferedReader(new FileReader(entrada));
            BufferedWriter bw = new BufferedWriter(new FileWriter(sortida))
        ) {
            String linia;
            while ((linia = br.readLine()) != null) {
                // Desfer xifrat cèsar a cada caràcter
                StringBuilder liniaDesxifrada = new StringBuilder();
                for (int i = 0; i < linia.length(); i++) {
                    char c = linia.charAt(i);
                    liniaDesxifrada.append((char)(c - clau));
                }

                // Desfer la inversió (tornar a invertir)
                String resultat = liniaDesxifrada.reverse().toString();

                bw.write(resultat);
                bw.newLine();
            }
            System.out.println("Progrés: Desxifrat completat amb èxit. (Fitxer '" + sortida + "' generat)");

        } catch (FileNotFoundException e) {
            System.out.println("Error: Fitxer no trobat a la fase de desxifrat (" + e.getMessage() + ")");
        } catch (IOException e) {
            System.out.println("Error d'entrada/sortida durant el desxifrat (" + e.getMessage() + ")");
        }
    }
}