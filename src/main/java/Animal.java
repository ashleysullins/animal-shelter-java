import java.sql.Date;

public class Animal {
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
}
