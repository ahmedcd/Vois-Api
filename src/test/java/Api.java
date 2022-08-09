import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Api {


    // first step get random user using id
    @Test
    public void getUserByRandomId() {
        Response response = given().when().get("https://jsonplaceholder.typicode.com/users?id=1")
                .then().assertThat().statusCode(200).extract().response();
        String responseConvert= response.asString();
        System.out.println(responseConvert);

        //second step get the user Email
        JsonPath jsonPath = new JsonPath(responseConvert);
        String email = jsonPath.getString("email");
        System.out.println(email);
    }

    //post using same userID with a non-empty title and body
    @Test
    public void userPost() {
        Response response = given().contentType(ContentType.JSON)
                .when().get("https://jsonplaceholder.typicode.com/posts?userId=1")
                .then().assertThat().statusCode(200).extract().response();
        String responseInString = response.asString();
        System.out.println(responseInString);

        // return  response
        JsonPath jsonPath = new JsonPath(responseInString);
        String userPosts = jsonPath.getString("title");
        System.out.println(userPosts);

    }
}
