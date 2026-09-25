# Acces-a-dades-RA1-PT2

## Requeriments

1. **Llegir un fitxer de text** (entrada.txt) amb **BufferedReader**.

2. Per cada línia llegida:
   - **Invertir la línia** (exemple: "Hola món" → "nóm aloH").

   - **Aplicar xifrat Cèsar:** cada caràcter es desplaça N posicions en Unicode.

3. Escriure el resultat a un fitxer de sortida (**xifrat.txt**) utilitzant **BufferedWriter**.

4. Crear un mètode per **desxifrar el fitxer**:
   - Llegir el fitxer xifrat.

   - Aplicar **desplaçament invers** de la clau.

   - Tornar a invertir cada línia per recuperar el missatge original.

   - Escriure el resultat a **desxifrat.txt**.

5. Opcional: permetre que l’usuari introdueixi la **clau de xifrat/desxifrat** per consola.

6. Mostrar per consola missatges de **progrés i errors**.

Decoradors (wrappers)

Què són?

    Són classes que envolten un flux bàsic (FileReader, FileWriter) i afegeixen funcionalitats (més velocitat, lectura línia a línia, etc.).

    Exemple típic del patró Decorator: no substitueix, sinó que amplia el comportament.

Principals decoradors per text:
Classe Funció Comentari
BufferedReader Permet llegir línies completes (readLine()), guarda dades en memòria intermèdia per més velocitat. Normalment s’usa sobre un FileReader.
BufferedWriter Escriu de manera eficient amb memòria intermèdia. Normalment s’usa sobre un FileWriter.
PrintWriter Escriu text de forma senzilla (té mètodes com println()). Molt pràctic per fitxers de text.
Esquemes habituals
Lectura de text (patró decorador)

BufferedReader br = new BufferedReader(new FileReader("fitxer.txt"));
String linia;
while ((linia = br.readLine()) != null) {
System.out.println(linia);
}
br.close();
Escriptura de text

BufferedWriter bw = new BufferedWriter(new FileWriter("fitxer.txt"));
bw.write("Hola món!");
bw.newLine(); // salta de línia
bw.write("Segona línia");
bw.close();
Escriptura més senzilla amb PrintWriter

PrintWriter pw = new PrintWriter(new FileWriter("fitxer.txt")); pw.println("HoPrintWriter pw = new PrintWriter(new FileWriter("fitxer.txt"));
pw.println("Hola món!");
pw.println("Segona línia");
pw.close();
la món!"); pw.println("Segona línia"); pw.close();
Taula comparativa: mètodes de wrappers de fitxers
Classe Mètode Descripció / Ús
BufferedReader read() Llegeix un sol caràcter (retorna int, -1 si final de fitxer).
read(char[] cbuf, int off, int len) Llegeix fins a len caràcters i els posa en l’array cbuf.
readLine() Llegeix una línia sencera (sense el salt de línia).
ready() Comprova si hi ha caràcters disponibles per llegir.
close() Tanca el flux i allibera recursos.
BufferedWriter write(int c) Escriu un sol caràcter.
write(char[] cbuf, int off, int len) Escriu un array de caràcters parcial.
write(String s, int off, int len) Escriu part d’una cadena.
newLine() Escriu el salt de línia adequat al sistema (\n o \r\n).
flush() Allibera el buffer i escriu tot el contingut al fitxer.
close() Tanca el flux i escriu el que quedi al buffer.
PrintWriter print(String s) Escriu text sense salt de línia.
println(String s) Escriu text amb salt de línia al final.
printf(String format, Object... args) Escriu text formatat com printf.
flush() Força l’escriptura immediata del buffer.
close() Tanca el flux i allibera recursos.

Exemple: Aplicació que llegeix, transforma i escriu fitxers

Enunciat:

    Llegir un fitxer entrada.txt línia per línia amb BufferedReader.

    Comptar paraules i caràcters.

    Escriure el contingut en majúscules en un fitxer sortida.txt amb BufferedWriter.

    Afegir un resum amb PrintWriter al final del fitxer: total de línies, paraules i caràcters.

import java.io.\*;

public class ExempleCompletWrappers {
public static void main(String[] args) {
String entrada = "entrada.txt";
String sortida = "sortida.txt";

        int totalLinies = 0;
        int totalParaules = 0;
        int totalCaracters = 0;

        try (
            // Obrim fluxos
            BufferedReader br = new BufferedReader(new FileReader(entrada));
            BufferedWriter bw = new BufferedWriter(new FileWriter(sortida));
            PrintWriter pw = new PrintWriter(bw) // PrintWriter envolta BufferedWriter
        ) {
            String linia;
            while ((linia = br.readLine()) != null) {
                totalLinies++;

                // Comptar paraules i caràcters
                String[] paraules = linia.trim().split("\\s+");
                totalParaules += paraules.length;
                totalCaracters += linia.replaceAll("\\s+", "").length();

                // Escriure línia en majúscules
                bw.write(linia.toUpperCase());
                bw.newLine(); // salt de línia
            }

            // Afegim resum amb PrintWriter
            pw.println("\n--- RESUM ---");
            pw.println("Total de línies: " + totalLinies);
            pw.println("Total de paraules: " + totalParaules);
            pw.println("Total de caràcters (sense espais): " + totalCaracters);

            // Assegurar escriptura al fitxer
            pw.flush();

            System.out.println("Processament complet. Fitxer creat: " + sortida);

        } catch (FileNotFoundException e) {
            System.out.println("Fitxer no trobat: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error d'entrada/sortida: " + e.getMessage());
        }
    }

}
