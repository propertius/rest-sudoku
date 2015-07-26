package ar.com.cuscueta.esteban.sudoku;

import ar.com.cuscueta.esteban.sudoku.beans.Cell;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.http.MediaType;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.nio.charset.Charset;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class SudokuControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private WebTarget baseURI;
    private ObjectMapper objectMapper;

    @BeforeClass
    public static void beforeSudokuControllerTestClass() {
        SpringApplication.run(Application.class);
    }

    @Before
    public void before() throws Exception {
        Client client = ClientBuilder.newClient();
        baseURI = client.target("http://localhost:8080/");
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getOfficesShouldReturnTypeApplicationJson() {
        WebTarget path = baseURI.path("sudoku");
        assertThat(path.request().get().getMediaType().toString(), is(contentType.toString()));
    }

    @Test
    public void getSudokuShouldReturnABoard() throws Exception {
        WebTarget path = baseURI.path("sudoku");
        int length = 9;
        String json = path.request().get(String.class);
        Cell[][] actual = objectMapper.readValue(json, Cell[][].class);
        assertThat(actual.length, is(length));
    }

    @Test
    public void postValidateMoveIsValid() throws Exception {
        String randomBoard = "[" +
                "[0,0,0,7,0,0,0,6,5]," +
                "[0,0,0,0,4,0,2,0,8]," +
                "[0,0,6,0,0,8,9,1,7]," +
                "[0,0,8,0,0,0,0,0,9]," +
                "[0,2,0,0,8,0,5,0,0]," +
                "[0,0,0,0,0,0,0,8,2]," +
                "[6,5,0,4,9,7,0,2,1]," +
                "[0,4,7,5,0,0,0,0,0]," +
                "[0,0,9,0,6,3,0,0,0]]";
        WebTarget path = baseURI.path("validate");
        Response response = path.request().post(Entity.text(randomBoard));
        String output = response.readEntity(String.class);
        assertThat(output, is("true"));
    }

    @Test
    public void postValidateMoveIsNotValid() throws Exception {
        String randomBoard = "[" +
                "[0,0,7,7,0,0,0,6,5]," +
                "[0,0,0,0,4,0,2,0,8]," +
                "[0,0,6,0,0,8,9,1,7]," +
                "[0,0,8,0,0,0,0,0,9]," +
                "[0,2,0,0,8,0,5,0,0]," +
                "[0,0,0,0,0,0,0,8,2]," +
                "[6,5,0,4,9,7,0,2,1]," +
                "[0,4,7,5,0,0,0,0,0]," +
                "[0,0,9,0,6,3,0,0,0]]";
        WebTarget path = baseURI.path("validate");
        Response response = path.request().post(Entity.text(randomBoard));
        String output = response.readEntity(String.class);
        assertThat(output, is("false"));
    }

    @Test
    public void postValidateGameEndIsNotFinished() throws Exception {
        String randomBoard = "[" +
                "[0,0,0,7,0,0,0,6,5]," +
                "[0,0,0,0,4,0,2,0,8]," +
                "[0,0,6,0,0,8,9,1,7]," +
                "[0,0,8,0,0,0,0,0,9]," +
                "[0,2,0,0,8,0,5,0,0]," +
                "[0,0,0,0,0,0,0,8,2]," +
                "[6,5,0,4,9,7,0,2,1]," +
                "[0,4,7,5,0,0,0,0,0]," +
                "[0,0,9,0,6,3,0,0,0]]";
        WebTarget path = baseURI.path("finished");
        Response response = path.request().post(Entity.text(randomBoard));
        String output = response.readEntity(String.class);
        assertThat(output, is("false"));
    }

    @Test
    public void testValidateGameEndIsFinished() throws Exception {
        String randomBoard = "[" +
                "[1,6,3,5,9,2,8,4,7]," +
                "[2,5,7,3,4,8,9,6,1]," +
                "[4,9,8,7,1,6,2,5,3]," +
                "[5,3,1,9,6,4,7,2,8]," +
                "[9,7,6,2,8,5,1,3,4]," +
                "[8,2,4,1,7,3,6,9,5]," +
                "[7,1,5,4,2,9,3,8,6]," +
                "[6,4,2,8,3,1,5,7,9]," +
                "[3,8,9,6,5,7,4,1,2]]";
        WebTarget path = baseURI.path("finished");
        Response response = path.request().post(Entity.text(randomBoard));
        String output = response.readEntity(String.class);
        assertThat(output, is("true"));
    }

    @Test
    public void testValidateGameEndIsNotFinishedBecauseOfErrors() throws Exception {
        String randomBoard = "[" +
                "[1,1,3,5,9,2,8,4,7]," +
                "[2,5,7,3,4,8,9,6,1]," +
                "[4,9,8,7,1,6,2,5,3]," +
                "[5,3,1,9,6,4,7,2,8]," +
                "[9,7,6,2,8,5,1,3,4]," +
                "[8,2,4,1,7,3,6,9,5]," +
                "[7,1,5,4,2,9,3,8,6]," +
                "[6,4,2,8,3,1,5,7,9]," +
                "[3,8,9,6,5,7,4,1,2]]";
        WebTarget path = baseURI.path("finished");
        Response response = path.request().post(Entity.text(randomBoard));
        String output = response.readEntity(String.class);
        assertThat(output, is("false"));
    }
}
