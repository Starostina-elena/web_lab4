package org.lia.lab4_v3.REST;

import jakarta.ejb.EJB;
import jakarta.ejb.EJBTransactionRolledbackException;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.lia.lab4_v3.Beans.AuthBean;
import org.lia.lab4_v3.DBEntity.UserEntity;
import org.lia.lab4_v3.Models.LoginRequest;
import org.lia.lab4_v3.Utils.PasswordHash;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

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
            PasswordHash passwordHash = new PasswordHash();
            UserEntity user = authBean.login(loginRequest.getUsername(), passwordHash.hash(loginRequest.getPassword()));
            return Response.ok().entity(user).build();
        } catch (NoResultException | EJBTransactionRolledbackException | NoSuchAlgorithmException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(LoginRequest loginRequest) {
        try {
            PasswordHash passwordHash = new PasswordHash();
            UserEntity user = authBean.register(loginRequest.getUsername(), passwordHash.hash(loginRequest.getPassword()));
            return Response.ok().entity(user).build();
        } catch (PersistenceException | EJBTransactionRolledbackException | NoSuchAlgorithmException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

}