import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LlegirZoo {
    private static final String FITXER = "PT4/zoo.xml";

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(FITXER));
            NodeList animals = document.getElementsByTagName("animal");

            System.out.println("Animals del zoo: " + animals.getLength());
            System.out.println();

            for (int i = 0; i < animals.getLength(); i++) {
                Element animal = (Element) animals.item(i);
                mostrarAnimal(animal);
            }

            System.out.println("Animals que mengen carn: " + comptarPerAliment(animals, "Carn"));
            mostrarPerAliment(animals, "Carn");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.err.println("No s'ha pogut llegir l'XML: " + e.getMessage());
        }
    }

    private static void mostrarAnimal(Element animal) {
        System.out.println("Animal #" + animal.getAttribute("id"));
        System.out.println("Nom: " + valorDe(animal, "nom"));
        System.out.println("Espècie: " + valorDe(animal, "especie"));
        System.out.println("Aliment: " + valorDe(animal, "aliment"));
        System.out.println("Edat: " + valorDe(animal, "edat"));
        System.out.println();
    }

    private static int comptarPerAliment(NodeList animals, String aliment) {
        int total = 0;
        for (int i = 0; i < animals.getLength(); i++) {
            Element animal = (Element) animals.item(i);
            if (aliment.equalsIgnoreCase(valorDe(animal, "aliment"))) {
                total++;
            }
        }
        return total;
    }

    private static void mostrarPerAliment(NodeList animals, String aliment) {
        System.out.println("Animals que mengen " + aliment + ":");
        for (int i = 0; i < animals.getLength(); i++) {
            Element animal = (Element) animals.item(i);
            if (aliment.equalsIgnoreCase(valorDe(animal, "aliment"))) {
                System.out.println("- " + valorDe(animal, "nom"));
            }
        }
    }

    private static String valorDe(Element animal, String nomElement) {
        return animal.getElementsByTagName(nomElement).item(0).getTextContent();
    }
}