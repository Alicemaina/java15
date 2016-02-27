import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Stylist.all().size());
  }

  @Test
  public void equalsOverride_returnsTrueIfNamesAreTheSameString_true() {
    Stylist firstStylist = new Stylist("Tyler");
    Stylist secondStylist = new Stylist("Tyler");
    assertTrue(firstStylist.equals(secondStylist));
  }
}
