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


  // @Test
  // public void isALeapYear() {
  //   goTo("http://localhost:4567");
  //   fill("#year").with("2004");
  //   submit(".btn");
  //   assertThat(pageSource()).contains("Correct response");
  // }
} // end IntegrationTest
