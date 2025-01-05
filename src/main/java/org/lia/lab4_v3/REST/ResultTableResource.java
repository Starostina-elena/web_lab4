package org.lia.lab4_v3.REST;

import jakarta.ejb.EJB;
import jakarta.ejb.EJBTransactionRolledbackException;
import jakarta.ejb.Stateless;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.lia.lab4_v3.Beans.AuthBean;
import org.lia.lab4_v3.Beans.ResultTableBean;
import org.lia.lab4_v3.DBEntity.PointEntity;
import org.lia.lab4_v3.DBEntity.UserEntity;
import org.lia.lab4_v3.Models.AddPointRequest;
import org.lia.lab4_v3.Models.TableRequest;
import org.lia.lab4_v3.Utils.PasswordHash;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

@Path("/points")
@Stateless
public class ResultTableResource {

    @EJB
    private ResultTableBean resultTableBean;
    @EJB
    private AuthBean authBean;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResults(TableRequest tableRequest) {
        UserEntity user = checkUser(tableRequest.getCreatorId(), tableRequest.getUsername(), tableRequest.getPassword());
        if (user == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        List<PointEntity> results = resultTableBean.getResults(user);
        return Response.ok().entity(results).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPoint(AddPointRequest addPointRequest) {
        UserEntity user = checkUser(addPointRequest.getCreatorId(), addPointRequest.getUsername(), addPointRequest.getPassword());
        if (user == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        PointEntity point = resultTableBean.addResult(addPointRequest, user);
        return Response.ok().entity(point).build();
    }

    public UserEntity checkUser(long id, String username, String password) {
        UserEntity user;
        PasswordHash passwordHash;
        try {
            user = authBean.getUserById(id);
            passwordHash = new PasswordHash();
        } catch (PersistenceException | EJBTransactionRolledbackException | NoSuchAlgorithmException e) {
            return null;
        }
        if (user == null || !Objects.equals(user.getUsername(), username) ||
                !Objects.equals(user.getPassword(), passwordHash.hash(password))) {
            return null;
        }
        return user;
    }
}
