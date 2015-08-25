import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String,Object>();
      model.put("template", "templates/home.vtl");
      model.put("animals", Animal.all());

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/admin", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/admin.vtl");

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  //   post("/add-animal", (request, response) -> {
  //     Map<String, Object> model = new HashMap<String,Object>();
  //     model.put("template", "templates/home.vtl");
  //
  //     String name = request.queryParams("animalName");
  //     String gender = request.queryParams("animalGender");
  //     int age = Integer.parseInt(request.queryParams("animalAge"));
  //     int breed_id = Integer.parseInt(request.queryParams("animalBreed"));
  //     int species_id = Integer.parseInt(request.queryParams("species"));
  //
  //     Animal newAnimal = new Animal(name, gender, age);
  //     newAnimal.save();
  //     newAnimal.setBreed(breed_id);
  //     newAnimal.setSpecies(species_id);
  //
  //     return new ModelAndView(model, layout);
  //   }, new VelocityTemplateEngine());
  }

  //Algorithm goes here
}
