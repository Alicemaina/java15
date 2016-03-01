import org.fluentlenium.adapter.FluentTest;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();


  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Hair Salon Database");
  }

  @Test
  public void clients_AreDisplayed() {
    Client testClient1 = new Client("John Smith");
    Client testClient2 = new Client("Jane Doe");
    testClient1.save();
    testClient2.save();
    String indexPath = "http://localhost:4567/";
    goTo(indexPath);
    assertThat(pageSource()).contains("John Smith");
    assertThat(pageSource()).contains("Jane Doe");
  }

  @Test
  public void clients_CanBeAdded() {
    Stylist stylist1 = new Stylist("Tyler");
    stylist1.save();
    String indexPath = "http://localhost:4567/";
    String addClientPath = "http://localhost:4567/new-client";
    goTo(addClientPath);
    fill("#client").with("Jim Frizzle");
    click("option",withText("Tyler"));
    click("#submit-client");
    assertThat(pageSource()).contains("Jim Frizzle");
  }

  @Test
  public void find_stylistsByClient_stylistName() {
    Stylist stylist1 = new Stylist("Tyler");
    stylist1.save();
    String indexPath = "http://localhost:4567/";
    String addClientPath = "http://localhost:4567/new-client";
    goTo(addClientPath);
    fill("#client").with("Jim Frizzle");
    click("option",withText("Tyler"));
    click("#submit-client");
    click("a", withText("Jim Frizzle"));
    assertThat(pageSource()).contains("Tyler");
  }
}
