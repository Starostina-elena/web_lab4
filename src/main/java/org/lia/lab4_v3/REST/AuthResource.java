package org.lia.lab4_v3.REST;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.lia.lab4_v3.Beans.AuthBean;
import org.lia.lab4_v3.DBEntity.UserEntity;
import org.lia.lab4_v3.Models.LoginRequest;

@Path("/auth")
@Stateless
public class AuthResource {

    @EJB
    private AuthBean authBean;

    @GET
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest loginRequest) {
        try {
            UserEntity user = authBean.login(loginRequest.getUsername(), loginRequest.getPassword());
            return Response.ok().entity(user).build();
        } catch (NoResultException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(LoginRequest loginRequest) {
        try {
            UserEntity user = authBean.register(loginRequest.getUsername(), loginRequest.getPassword());
            return Response.ok().entity(user).build();
        } catch (PersistenceException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

    }

}