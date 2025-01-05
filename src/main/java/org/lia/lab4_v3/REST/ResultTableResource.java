package org.lia.lab4_v3.REST;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.lia.lab4_v3.Beans.ResultTableBean;
import org.lia.lab4_v3.DBEntity.PointEntity;
import org.lia.lab4_v3.DBEntity.UserEntity;
import jakarta.ws.rs.core.*;
import org.lia.lab4_v3.Models.Coordinates;

import java.util.List;

@Path("/result-table")
@Stateless
public class ResultTableResource {

    @EJB
    private ResultTableBean resultTableBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResults(
            @Context SecurityContext securityContext
    ) {
        UserEntity user = (UserEntity) securityContext.getUserPrincipal();
        List<PointEntity> results = resultTableBean.getResults(user);
        return Response.ok().entity(results).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPoint(
            @Context SecurityContext securityContext,
            Coordinates coordinates
    ) {
        UserEntity user = (UserEntity) securityContext.getUserPrincipal();
        PointEntity point = resultTableBean.addResult(coordinates, user);
        return Response.ok().entity(point).build();
    }
}
