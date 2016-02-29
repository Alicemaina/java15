import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.List;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("clients", Client.all());
      model.put("stylists", Stylist.all());
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());

      int selectedStylist = Integer.parseInt(request.queryParams("stylist"));
      List<Client> clientsByStylist = Client.findByStylist(selectedStylist);
      String stylistName = Stylist.find(selectedStylist).getName();

      model.put ("listStylistName", stylistName);
      model.put ("clients", clientsByStylist);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/new-client", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("clients", Client.all());
      model.put("stylists", Stylist.all());
      model.put("template", "templates/new-client.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/new-client", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      String newName =request.queryParams("client");
      int stylistID = Integer.parseInt(request.queryParams("stylist"));
      Client newClient = new Client(newName);
      newClient.save();
      newClient.setStylistID(stylistID);
      newClient.update();
      
      model.put("clients", Client.all());
      model.put("stylists", Stylist.all());
      model.put("template", "templates/new-client.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
