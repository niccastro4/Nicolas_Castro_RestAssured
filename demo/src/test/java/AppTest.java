import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.Test;
import config.TestConfig;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.lessThan;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestConfig {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void myFirstTest() {
        given()
                .log().all()
        .when()
                .get("quotes")
                // .get("https://dummyjson.com/quotes") // If we didn't have a Base/Config class
                // we would make the request like this
        .then()
                .log().all();
    }
    /**
     * Test method that validates the retrieval of a product with a specific productId from a RESTful web service.
     */
    @Test
    public void getProducts(){
        String PRODUCT_ENDPOINT = "products/{productId}";

        given()
                .pathParam("productId", "31")
                .log().all()
        .when()
                .get(PRODUCT_ENDPOINT)
        .then()
                .log().all();
    }
    @Test
    public void getProductsWithAssertions(){
        String PRODUCT_ENDPOINT = "products/{productId}";

        given()
                .pathParam("productId", "31")
                .log().all()
        .when()
                .get(PRODUCT_ENDPOINT)
        .then()
                .statusCode(200)
                .time(lessThan(3000L))
                .header("Content-Type", "application/json; charset=utf-8")
                .body("id", equalTo(31))
                .body("title", equalTo("Mornadi Velvet Bed"))
                .body("description", equalTo("Mornadi Velvet Bed Base with Headboard Slats Support Classic Style Bedroom Furniture Bed Set"))
                .body("price", equalTo(40))
                .body("discountPercentage", equalTo(17))
                .body("rating", equalTo(4.16f))
                .body("stock", equalTo(140))
                .body("brand", equalTo("Furniture Bed Set"))
                .body("category", equalTo("furniture"))
                .body("thumbnail", equalTo("https://i.dummyjson.com/data/products/31/thumbnail.jpg"))
                .body("images", hasItems(
                        "https://i.dummyjson.com/data/products/31/1.jpg",
                        "https://i.dummyjson.com/data/products/31/2.jpg",
                        "https://i.dummyjson.com/data/products/31/3.jpg",
                        "https://i.dummyjson.com/data/products/31/4.jpg",
                        "https://i.dummyjson.com/data/products/31/thumbnail.jpg"
                ))
                .log().all();
    }
    /**
     * Tests product creation by sending a POST request with product JSON to "products/add".
     * Logs all requests and responses for verification.
     */
    @Test
    public void postProduct() {
        String productJson = "{"
                + "\"id\": 11,"
                + "\"title\": \"Azure Elixir\","
                + "\"description\": \"Azure Elixir Luxurious Perfume for Men & Women - 100ml\","
                + "\"price\": 45,"
                + "\"discountPercentage\": 10,"
                + "\"rating\": 4,"
                + "\"stock\": 65,"
                + "\"brand\": \"Azure Elixirs\","
                + "\"category\": \"fragrances\","
                + "\"thumbnail\": \"https://i.dummyjson.com/data/products/12/thumbnail.jpg\","
                + "\"images\": ["
                + "\"https://i.dummyjson.com/data/products/12/1.jpg\","
                + "\"https://i.dummyjson.com/data/products/12/2.jpg\","
                + "\"https://i.dummyjson.com/data/products/12/3.png\","
                + "\"https://i.dummyjson.com/data/products/12/4.jpg\","
                + "\"https://i.dummyjson.com/data/products/12/thumbnail.jpg\""
                + "]"
                + "}";
        given()
                .contentType("application/json; charset=utf-8")
                .body(productJson)
                .log().all()
        .when()
                .post("products/add")
        .then()
                .statusCode(200)
                .log().all();
    }
    /**
     * Tests product creation by sending a POST request with product JSON to "products/add" also validates key response attributes..
     * Logs all requests and responses for verification.
     */
    @Test
    public void postProductWithAssertions() {
        String productJson = "{"
                + "\"id\": 11,"
                + "\"title\": \"Azure Elixir\","
                + "\"description\": \"Azure Elixir Luxurious Perfume for Men & Women - 100ml\","
                + "\"price\": 45,"
                + "\"discountPercentage\": 10,"
                + "\"rating\": 4,"
                + "\"stock\": 65,"
                + "\"brand\": \"Azure Elixirs\","
                + "\"category\": \"fragrances\","
                + "\"thumbnail\": \"https://i.dummyjson.com/data/products/12/thumbnail.jpg\","
                + "\"images\": ["
                + "\"https://i.dummyjson.com/data/products/12/1.jpg\","
                + "\"https://i.dummyjson.com/data/products/12/2.jpg\","
                + "\"https://i.dummyjson.com/data/products/12/3.png\","
                + "\"https://i.dummyjson.com/data/products/12/4.jpg\","
                + "\"https://i.dummyjson.com/data/products/12/thumbnail.jpg\""
                + "]"
                + "}";
        given()
                .contentType("application/json; charset=utf-8")
                .body(productJson)
                .log().all()
        .when()
                .post("products/add")
        .then()
                .statusCode(200)
                .time(lessThan(3000L))
                .header("Content-Type", "application/json; charset=utf-8")
                .body("title", startsWith("Azure"))
                .body("description", equalTo("Azure Elixir Luxurious Perfume for Men & Women - 100ml"))
                .body("price", equalTo(45))
                .body("rating", equalTo(4))
                .body("stock", equalTo(65))
                .body("brand", equalTo("Azure Elixirs"))
                .body("category", equalTo("fragrances"))
                .body("thumbnail", equalTo("https://i.dummyjson.com/data/products/12/thumbnail.jpg"))
                .body("images", hasItem("https://i.dummyjson.com/data/products/12/1.jpg"))
                .log().all();
    }
    /**
     * Tests updating a product by sending a PUT request and verifies the response status.
     */
    @Test
    public void putProduct() {
        String PRODUCT_ENDPOINT = "products/{param1}";
        String productJson = "{"
                + "\"title\": \"Emerald Essence\","
                + "\"description\": \"Emerald Essence Premium Scent for Men & Women - 110ml\","
                + "\"price\": 50,"
                + "\"discountPercentage\": 12,"
                + "\"rating\": 5,"
                + "\"stock\": 70,"
                + "\"brand\": \"Emerald Scents\","
                + "\"category\": \"fragrances\","
                + "\"thumbnail\": \"https://i.dummyjson.com/data/products/12/thumbnail.jpg\","
                + "\"images\": ["
                + "\"https://i.dummyjson.com/data/products/12/1.jpg\","
                + "\"https://i.dummyjson.com/data/products/12/2.jpg\","
                + "\"https://i.dummyjson.com/data/products/12/3.png\","
                + "\"https://i.dummyjson.com/data/products/12/4.jpg\","
                + "\"https://i.dummyjson.com/data/products/12/thumbnail.jpg\""
                + "]"
                + "}";
        given()
                .contentType("application/json; charset=utf-8")
                .body(productJson)
                .log().all()
        .when()
                .put(PRODUCT_ENDPOINT,10)
        .then()
                .statusCode(200)
                .log().all();
    }
    /**
     * Tests updating a product with assertions to validate the status code, headers, specific data fields, and the response time.
     */
    @Test
    public void putProductWithAssertions() {
        String PRODUCT_ENDPOINT = "products/{param1}";
        String productJson = "{"
                + "\"title\": \"Emerald Essence\","
                + "\"description\": \"Emerald Essence Premium Scent for Men & Women - 110ml\","
                + "\"price\": 50,"
                + "\"brand\": \"Emerald Scents\","
                + "\"category\": \"fragrances\","
                + "\"thumbnail\": \"https://i.dummyjson.com/data/products/12/thumbnail.jpg\","
                + "\"images\": ["
                + "\"https://i.dummyjson.com/data/products/12/1.jpg\","
                + "\"https://i.dummyjson.com/data/products/12/2.jpg\","
                + "\"https://i.dummyjson.com/data/products/12/3.png\","
                + "\"https://i.dummyjson.com/data/products/12/4.jpg\","
                + "\"https://i.dummyjson.com/data/products/12/thumbnail.jpg\""
                + "]"
                + "}";
        given()
                .contentType("application/json; charset=utf-8")
                .body(productJson)
                .log().all()
        .when()
                .put(PRODUCT_ENDPOINT,12)
        .then()
                .statusCode(200)
                .time(lessThan(2000L))
                .header("Content-Type", "application/json; charset=utf-8")
                .body("title", equalTo("Emerald Essence"))
                .body("description", equalTo("Emerald Essence Premium Scent for Men & Women - 110ml"))
                .body("price", equalTo(50))
                .body("brand", equalTo("Emerald Scents"))
                .body("category", equalTo("fragrances"))
                .body("thumbnail", equalTo("https://i.dummyjson.com/data/products/12/thumbnail.jpg"))
                .body("images[0]", equalTo("https://i.dummyjson.com/data/products/12/1.jpg"))
                .body("images[1]", equalTo("https://i.dummyjson.com/data/products/12/2.jpg"))
                .body("images[2]", equalTo("https://i.dummyjson.com/data/products/12/3.png"))
                .body("images[3]", equalTo("https://i.dummyjson.com/data/products/12/4.jpg"))
                .body("images[4]", equalTo("https://i.dummyjson.com/data/products/12/thumbnail.jpg"))
                .log().all();
    }
    /**
     * Tests the deletion of a product by its ID and logs the request and response.
     */
    @Test
    public void deleteProduct() {
        String PRODUCT_ENDPOINT = "products/{param1}";
        given()
                .contentType("application/json; charset=utf-8")
                .log().all()
        .when()
                .delete(PRODUCT_ENDPOINT,10)
        .then()
                .statusCode(200)
                .log().all();
    }
    /**
     * Tests the deletion of a product by its ID with assertions to validate the response duration and headers, and logs the request and response.
     */
    @Test
    public void deleteProductWithAssertions() {
        String PRODUCT_ENDPOINT = "products/{param1}";

        given()
                .contentType("application/json; charset=utf-8")
                .log().all()
        .when()
                .delete(PRODUCT_ENDPOINT, 10)
        .then()
                .statusCode(200)
                .time(lessThan(2000L))
                .header("Content-Type", "application/json; charset=utf-8")
                .log().all();
    }
    /**
     * Tests the retrieved product data against a predefined JSON schema to ensure its structure and data types.
     */
    @Test
    public void schemaValidation(){
        String PRODUCT_ENDPOINT = "products/{productId}";

        given()
                .pathParam("productId", "31")
                .log().all()
        .when()
                .get(PRODUCT_ENDPOINT)
        .then()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("productSchema.json"));
    }
}

