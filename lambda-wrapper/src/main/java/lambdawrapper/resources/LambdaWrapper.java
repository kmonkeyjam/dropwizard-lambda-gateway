package lambdawrapper.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tina on 11/6/15.
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class LambdaWrapper {
  private static ObjectMapper mapper = new ObjectMapper();

  public LambdaWrapper() throws GeneralSecurityException, IOException {
  }

  @GET
  @Path("/status")
  public Response getStatus() throws IOException {
    Map<String, Object> result = new HashMap<>();
    InputStream stream = LambdaWrapper.class.getClassLoader().getResourceAsStream("git.json");
    result.put("git", mapper.readValue(stream, ObjectNode.class));
    return Response.ok(result).build();
  }
}
