import java.sql.Date;
import java.util.List;
import org.sql2o.*;

public class Animal {
  private int id;
  private String name;
  private String gender;
  private int age;
  private Date date_of_admission;
  private int breed_id;
  private int species_id;
  private int customer_id;
  private String path_to_photo;

  public Animal(String name, String gender, int age) {
    this.name = name;
    this.gender = gender;
    this.age = age;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getGender() {
    return gender;
  }

  public int getAge() {
    return age;
  }

  public void setBreed(int breed_id) {
    this.breed_id = breed_id;
  }

  public int getBreed() {
    return breed_id;
  }

  public void setSpecies(int species_id) {
    this.species_id = species_id;
  }

  public int getSpecies() {
    return species_id;
  }

  public void setPhotoPath(String path_to_photo) {
    this.path_to_photo = path_to_photo;
  }

  public String getPhotoPath() {
    return path_to_photo;
  }

  public static List<Animal> all() {
    String sql = "SELECT * FROM animal";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Animal.class);
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO animal (name, gender, age) VALUES (:name, :gender, :age)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("gender", gender)
        .addParameter("age", age)
        .executeUpdate()
        .getKey();
    }
  }

  public static Animal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animal WHERE id=:id";
      Animal animal = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Animal.class);
      return animal;
    }
  }

  @Override
  public boolean equals (Object otherAnimal) {
    if (!(otherAnimal instanceof Animal)) {
      return false;
    } else {
      Animal newAnimal = (Animal) otherAnimal;
      return this.getName().equals(newAnimal.getName()) &&
        this.getGender().equals(newAnimal.getGender()) &&
        this.getAge() == newAnimal.getAge() &&
        this.getId() == newAnimal.getId();
    }
  }

  public void update(String newName) {
    this.name = newName;
    try (Connection con = DB.sql2o.open()) {
      String sql ="UPDATE animal SET name=:name WHERE id=:id";
      con.createQuery(sql)
        .addParameter("name", newName)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM animal WHERE id=:id";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

}
