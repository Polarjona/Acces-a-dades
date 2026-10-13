import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CrearZoo {
    private static final String FITXER = "PT4/zoo.xml";

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element zoo = document.createElement("zoo");
            document.appendChild(zoo);

            afegirAnimal(document, zoo, 1, "Pingüí", "Aptenodytes forsteri", "Peix", 5);
            afegirAnimal(document, zoo, 2, "Lleó", "Panthera leo", "Carn", 8);
            afegirAnimal(document, zoo, 3, "Elefant", "Loxodonta africana", "Herba", 12);
            afegirAnimal(document, zoo, 4, "Tigre", "Panthera tigris", "Carn", 6);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(new DOMSource(document), new StreamResult(new File(FITXER)));

            System.out.println("Fitxer " + FITXER + " creat correctament.");
        } catch (ParserConfigurationException | TransformerException e) {
            System.err.println("No s'ha pogut crear l'XML: " + e.getMessage());
        }
    }

    private static void afegirAnimal(Document document, Element zoo, int id,
            String nom, String especie, String aliment, int edat) {
        Element animal = document.createElement("animal");
        animal.setAttribute("id", String.valueOf(id));
        zoo.appendChild(animal);

        afegirElement(document, animal, "nom", nom);
        afegirElement(document, animal, "especie", especie);
        afegirElement(document, animal, "aliment", aliment);
        afegirElement(document, animal, "edat", String.valueOf(edat));
    }

    private static void afegirElement(Document document, Element pare,
            String nom, String valor) {
        Element element = document.createElement(nom);
        element.setTextContent(valor);
        pare.appendChild(element);
    }
}