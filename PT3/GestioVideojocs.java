import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GestioVideojocs {
    private static final String FITXER = "videojocs.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Videojoc> cataleg = carregarVideojocs();
        int opcio = 0;

        do {
            System.out.println("\n--- Menú Gestió de Videojocs ---");
            System.out.println("1. Afegir videojoc");
            System.out.println("2. Llistar tots els videojocs");
            System.out.println("3. Cercar videojocs per títol");
            System.out.println("4. Actualitzar un videojoc");
            System.out.println("5. Eliminar un videojoc");
            System.out.println("6. Sortir del programa");
            System.out.print("Tria una opció: ");
            
            if (sc.hasNextInt()) {
                opcio = sc.nextInt();
                sc.nextLine();
                
                switch (opcio) {
                    case 1:
                        afegirVideojoc(sc, cataleg);
                        break;
                    case 2:
                        llistarVideojocs(cataleg);
                        break;
                    case 3:
                        cercarVideojoc(sc, cataleg);
                        break;
                    case 4:
                        actualitzarVideojoc(sc, cataleg);
                        break;
                    case 5:
                        eliminarVideojoc(sc, cataleg);
                        break;
                    case 6:
                        System.out.println("Guardant dades i sortint... Fins aviat!");
                        desarVideojocs(cataleg);
                        break;
                    default:
                        System.out.println("Opció incorrecta. Tria un número de l'1 al 6.");
                }
            } else {
                System.out.println("Si us plau, introdueix un número vàlid.");
                sc.nextLine(); 
            }
        } while (opcio != 6);
        
        sc.close();
    }


    private static void afegirVideojoc(Scanner sc, ArrayList<Videojoc> cataleg) {
        System.out.println("\n-- Afegir Nou Videojoc --");
        System.out.print("Títol: ");
        String titol = sc.nextLine();
        System.out.print("Gènere (ex: acció, rol...): ");
        String genere = sc.nextLine();
        System.out.print("Any de llançament: ");
        int any = sc.nextInt();
        sc.nextLine(); // Netejar buffer
        System.out.print("Plataforma (ex: PC, Switch...): ");
        String plataforma = sc.nextLine();
        System.out.print("Preu: ");
        double preu = sc.nextDouble();
        sc.nextLine(); // Netejar buffer

        Videojoc nou = new Videojoc(titol, genere, any, plataforma, preu);
        cataleg.add(nou);
        desarVideojocs(cataleg);
        System.out.println("Videojoc afegit i desat correctament.");
    }

    private static void llistarVideojocs(ArrayList<Videojoc> cataleg) {
        System.out.println("\n-- Catàleg de Videojocs --");
        if (cataleg.isEmpty()) {
            System.out.println("No hi ha cap videojoc al catàleg.");
        } else {
            for (Videojoc v : cataleg) {
                System.out.println(v.toString());
            }
        }
    }

    private static void cercarVideojoc(Scanner sc, ArrayList<Videojoc> cataleg) {
        System.out.print("\nIntrodueix el text a cercar en el títol: ");
        String textCerca = sc.nextLine().toLowerCase();
        boolean trobat = false;

        System.out.println("\n-- Resultats de la cerca --");
        for (Videojoc v : cataleg) {
            if (v.getTitol().toLowerCase().contains(textCerca)) {
                System.out.println(v.toString());
                trobat = true;
            }
        }
        if (!trobat) {
            System.out.println("No s'ha trobat cap videojoc amb aquest text.");
        }
    }

    private static void actualitzarVideojoc(Scanner sc, ArrayList<Videojoc> cataleg) {
        System.out.print("\nIntrodueix el títol EXACTE del videojoc que vols actualitzar: ");
        String titol = sc.nextLine();
        Videojoc videojocTrobat = null;

        for (Videojoc v : cataleg) {
            if (v.getTitol().equalsIgnoreCase(titol)) {
                videojocTrobat = v;
                break;
            }
        }

        if (videojocTrobat != null) {
            System.out.println("Videojoc actual: " + videojocTrobat);
            System.out.println("Introdueix les noves dades:");
            
            System.out.print("Nou gènere: ");
            videojocTrobat.setGenere(sc.nextLine());
            
            System.out.print("Nou any de llançament: ");
            videojocTrobat.setAnyLlancament(sc.nextInt());
            sc.nextLine(); // Netejar buffer
            
            System.out.print("Nova plataforma: ");
            videojocTrobat.setPlataforma(sc.nextLine());
            
            System.out.print("Nou preu: ");
            videojocTrobat.setPreu(sc.nextDouble());
            sc.nextLine(); // Netejar buffer

            desarVideojocs(cataleg);
            System.out.println("Videojoc actualitzat i desat correctament.");
        } else {
            System.out.println("No s'ha trobat cap videojoc amb el títol: " + titol);
        }
    }

    private static void eliminarVideojoc(Scanner sc, ArrayList<Videojoc> cataleg) {
        System.out.print("\nIntrodueix el títol EXACTE del videojoc que vols eliminar: ");
        String titol = sc.nextLine();
        boolean eliminat = false;

        for (int i = 0; i < cataleg.size(); i++) {
            if (cataleg.get(i).getTitol().equalsIgnoreCase(titol)) {
                cataleg.remove(i);
                eliminat = true;
                break;
            }
        }

        if (eliminat) {
            desarVideojocs(cataleg);
            System.out.println("Videojoc eliminat correctament.");
        } else {
            System.out.println("No s'ha trobat cap videojoc amb aquest títol.");
        }
    }


    @SuppressWarnings("unchecked")
    private static ArrayList<Videojoc> carregarVideojocs() {
        ArrayList<Videojoc> cataleg = new ArrayList<>();
        File fitxer = new File(FITXER);
        
        if (fitxer.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fitxer))) {
                cataleg = (ArrayList<Videojoc>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error carregant el catàleg: " + e.getMessage());
            }
        }
        return cataleg;
    }

    private static void desarVideojocs(ArrayList<Videojoc> cataleg) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FITXER))) {
            oos.writeObject(cataleg);
        } catch (IOException e) {
            System.out.println("Error desant el catàleg: " + e.getMessage());
        }
    }
}