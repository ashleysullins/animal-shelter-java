import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.time.LocalDate;

public class AnimalTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Animal.all().size(), 0);
  }

  @Test
  public void getName_returnsName() {
    Animal newAnimal = new Animal("Princess", "f", 2, 1, 2, LocalDate.now());
    assertEquals("Princess", newAnimal.getName());
  }

  @Test
  public void equals_returnsTrueIfNameSexAgeAreTheSame() {
    Animal animal1 = new Animal("Princess", "f", 2, 1, 2, LocalDate.now());
    Animal animal2 = new Animal("Princess", "f", 2, 1, 2, LocalDate.now());
    assertEquals(true, animal1.equals(animal2));
  }

  @Test
  public void save_assignsIdToObject() {
    Animal myAnimal = new Animal("Princess", "f", 2, 1, 2, LocalDate.now());
    myAnimal.save();
    assertEquals(Animal.all().get(0).getId(), myAnimal.getId());
  }

  @Test
  public void find_findsAnimalInDatabase_true() {
    Animal myAnimal = new Animal("Princess", "f", 2, 1, 2, LocalDate.now());
    myAnimal.save();
    Animal savedAnimal = Animal.find(myAnimal.getId());
    assertTrue(myAnimal.equals(savedAnimal));
  }

  @Test
  public void update_updateAnimalInDatabase_true() {
    Animal myAnimal = new Animal("Princess", "f", 2, 1, 2, LocalDate.now());
    myAnimal.save();
    myAnimal.update("Prince");
    assertEquals("Prince", Animal.all().get(0).getName());
  }

  @Test
  public void delete_deletesAnimalInDatabase_true() {
    Animal myAnimal = new Animal("Princess", "f", 2, 1, 2, LocalDate.now());
    myAnimal.save();
    myAnimal.delete();
    assertEquals(Animal.all().size(), 0);
  }
}
