package Selendroid.Steps.API;

import Selendroid.Runner.CucumberRunner;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.testng.Assert.assertEquals;

public class APISteps extends CucumberRunner {
    private Response response;
    private String path;
    private JSONParser jsonParser = new JSONParser();

    @When("the users endpoint exists")
    public void the_user_endpoint_exists() {
        RestAssured.baseURI = config.getProperty("BASE_URL");
    }

    @When("user sends Get API request of user from page {int}")
    public void user_sends_get_api_request_of_user_from_page(Integer pageNumber) {
        path = "users?page="+pageNumber;
        response = given().get(path).then().extract().response();
    }

    @Then("user must get status code {int}")
    public void user_must_get_status_code(Integer responseCode) {
        assertEquals(responseCode.intValue(), response.getStatusCode(), "response code is not " + responseCode);
    }

    @Then("user verifies first name of id {int} is {string}")
    public void user_verifies_first_name_of_id_is(Integer id, String expectedName) {
        JSONArray jsonArray = null;
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.body().asString());
            jsonArray = (JSONArray) jsonObject.get("data");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (jsonArray != null) {
            for (Object o : jsonArray) {
                JSONObject object = (JSONObject) o;
                if (object.get("id").toString().equals(id.toString())) {
                    Assert.assertEquals(object.get("first_name"), expectedName);
                    return;
                }
            }
        }
        Assert.fail("user with id " + id + " is not available");
    }

    @When("user sends Post API request of users with name as {string} and job as {string}")
    public void user_sends_post_api_request_of_users_with_name_as_and_job_as(String name, String job) {
       path = "users";
        String validRequest = "{\n" +
                "  \"name\": \""+name+"\",\n" +
                "  \"job\": \""+job+"\" \n}";
        response =  given()
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(validRequest)
                .post(path)
                .then().extract().response();

    }

    @Then("user verifies id is generated")
    public void user_verifies_id_is_generated() {
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.body().asString());
            Assert.assertTrue(Integer.parseInt((String) jsonObject.get("id"))>0,"user id is not generated");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Then("user verifies response json scheme")
    public void user_verifies_response_json_scheme() {
        System.out.println(response);
        response.then().assertThat().body(matchesJsonSchema(new File(System.getProperty("user.dir") + "/src/main/resources/User.json")));
    }

}
