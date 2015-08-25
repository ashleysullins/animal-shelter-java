import java.time.LocalDate;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.sql2o.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AppIntegrationTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @ClassRule
  public static DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Portland Animal Shelter");
  }

  @Test
  public void nameIsDisplayedTest() {
    goTo("http://localhost:4567/admin");
    Animal myAnimal = new Animal("Princess", "f", 2, 1, 2, LocalDate.now());
    myAnimal.save();
    goTo("http://localhost:4567/");
    // should return Princess
    assertThat(pageSource()).contains(Animal.all().get(0).getName());
  }

  @Test
  public void genderIsDisplayedTest() {
    goTo("http://localhost:4567/admin");
    Animal myAnimal = new Animal("Princess", "f", 2, 1, 2, LocalDate.now());
    myAnimal.save();
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains(Animal.all().get(0).getGender());
  }
}
