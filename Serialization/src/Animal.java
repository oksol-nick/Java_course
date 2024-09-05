import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@XmlRootElement
public class Animal implements Serializable {


    String genus;
    String breed;
    Integer age;
    Owner owner;

    public Animal(String genus, String breed, Integer age, Owner owner) {
        this.genus = genus;
        this.breed = breed;
        this.age = age;
        this.owner = owner;
    }

    @XmlElement
    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    @XmlElement
    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @XmlElement
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @XmlElement
    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
