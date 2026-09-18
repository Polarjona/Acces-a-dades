
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



public class pt1 {
    
    public static void main(String[] args) throws IOException {
    try {
        FileReader fr = new FileReader("text.txt");
        
        
        int c;
        int numCaracters = 0;
        int numLinies = 1;
        int numParaules = 0;
        int[] frequencia = new int[65536];
        
        
        while ((c = fr.read()) != -1) {
            System.out.print((char) c);
            numCaracters++;
            if (c == '\n' || c == '\r') {
                numLinies++;
            }
            if (c == ' ' || c == '\n' || c == '\t' || c == '\r') {
                numParaules++;
            }
            if (c != ' ' && c != '\t' && c != '\n' && c != '\r') {
                frequencia[c]++;
            }
        }
        fr.close();
        
        System.out.println("\nNombre de caracters: " + numCaracters);
        System.out.println("Nombre de linies: " + numLinies);
        System.out.println("Nombre de paraules: " + numParaules);
        int maxFreq = 0;
        int maxChar = -1;
        for (int i = 0; i < 65536; i++) {
            if (frequencia[i] > maxFreq) {
                maxFreq = frequencia[i];
                maxChar = i;
            }
        }
        if (maxChar != -1) {
            System.out.println("El caràcter que apareix més vegades es '" + (char) maxChar + "': " + maxFreq);
        }

    } catch (FileNotFoundException e) {

    System.out.println("El fitxer no existeix.");

    } catch (IOException e) {
    
        System.out.println("S'ha produït un error de lectura.");
    
    } catch (SecurityException e) {
    
        System.out.println("No tens permisos per accedir al fitxer.");
    }



}
}
