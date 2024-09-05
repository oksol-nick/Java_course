import javax.xml.*;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.*;
import java.io.File;

public class AnimalXML {

    public static void main(String[] args) throws JAXBException {

        Owner owner = new Owner("Денис Кораблёв", "+7(912)815-35-27", "г.Санкт-Петербург, ул.Зюзина 15, кв.37");
        Animal cat = new Animal("Кошки", "Дворняга", 12, owner );

        File file = new File("./animal.xml");

        JAXBContext jaxbContext = JAXBContext.newInstance(Animal.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.marshal(cat, file);


    }
}
