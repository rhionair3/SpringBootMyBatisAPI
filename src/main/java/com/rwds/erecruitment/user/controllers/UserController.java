package com.rwds.erecruitment.user.controllers;

import com.rwds.erecruitment.user.entities.User;
import com.rwds.erecruitment.user.mappers.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

@Controller
@Path("/users")
public class UserController {
    static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Autowired
    private UserMapper userMapper;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(User user) {
        LinkedHashMap<Object, Object> serviceResponse = new LinkedHashMap<Object, Object>();
        try {
            Set<ConstraintViolation<User>> validateErrors = validator.validate(user);
            if (validateErrors.isEmpty()) {
                if (userMapper.findBySurel(user.getSurel()) != null) {
                    serviceResponse.put("duplicate_Email", "user already exist");
                } else {
                    Integer createPerson = userMapper.insertPengguna(user);

                    if (createPerson != 1) {
                        serviceResponse.put("created", "unable to create user");
                    } else {
                        logger.info("Successfully created User.");
                        serviceResponse.put("created_msg", "Successfully created User");
                    }
                }
                return Response.status(Response.Status.CREATED).entity(serviceResponse).build();
            } else {
                logger.info("Failed to create a user due to field validation errors.");
                logger.debug("Unable to create a user due to validation errors using {}", user);
                // JSONObject jsonObj = new JSONObject(validateErrors.toString());
                serviceResponse.put("error", validateErrors.toString());
                return Response.status(400).entity(serviceResponse).build();

            }
        } catch (Exception e) {
            logger.debug("<< create()");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(serviceResponse).build();
        }
    }

    @GET
    @Produces("application/json")
    public Response getPenggunas() {

        LinkedHashMap<String, Object> response = new LinkedHashMap<String, Object>();
        try {
            List<User> listUsers = userMapper.getPenggunas();
            if (listUsers == null) {
                response.put("users", Collections.emptyMap());
            } else {
                response.put("total", listUsers.size());
                response.put("users", listUsers);
            }
            return Response.status(Response.Status.OK).entity(listUsers).build();
        } catch (Exception ex) {

            response.put("users", "Not Found");
            return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces("application/json")
    public Response updateUser(@PathParam("id") Integer id, User user) {
        // String personJson = gson.toJson(user);
        // logger.debug(">> create({})", personJson);
        // LinkedHashMap<Object, Object> apiResponse = new LinkedHashMap<>();
        LinkedHashMap<Object, Object> serviceResponse = new LinkedHashMap<Object, Object>();
        logger.info("Starting to create a person");

        try {
            Set<ConstraintViolation<User>> validateErrors = validator.validate(user);
            if (validateErrors.isEmpty()) {
                if (userMapper.findById(id) == null) {
                    serviceResponse.put("msg", "User Not Found");
                } else {
                    if (userMapper.findBySurelNotPengguna(user.getSurel(), user.getId()) != null) {
                        serviceResponse.put("duplicate_Email", "user already exist");
                    } else {
                        int updateUser = userMapper.updatePengguna(user);
                        if (updateUser == 0) {
                            serviceResponse.put("created", "unable to update User");
                        } else {
                            logger.info("Successfully update user.");
                            serviceResponse.put("update", user);
                        }
                    }
                }
                return Response.status(Response.Status.OK).entity(user).build();
            } else {
                logger.info("Failed to update a user due to field validation errors.");
                // logger.debug("Unable to update a user due to validation errors using {}",
                // personJson);
                serviceResponse.put("error", validateErrors.toString());

                return Response.status(400).entity(serviceResponse).build();
            }
        } catch (Exception e) {
            logger.debug("<< create()");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(serviceResponse).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getPengguna(@PathParam("id") int userId) {

        LinkedHashMap<String, Object> response = new LinkedHashMap<String, Object>();

        try {
            User user = userMapper.findById(userId);

            if (user == null) {
                response.put("user", Collections.emptyMap());
            } else {
                response.put("user", user);
            }
            return Response.status(Response.Status.OK).entity(user).build();
        } catch (Exception ex) {

            response.put("users", "Not Found");
            return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response deletePengguna(@PathParam("id") int userId) {

        // LinkedHashMap<Object, Object> apiResponse = new LinkedHashMap<>();
        LinkedHashMap<Object, Object> serviceResponse = new LinkedHashMap<Object, Object>();
        logger.info("Starting to delete a user");

        try {
            int deletePerson = userMapper.deleteById(userId);
            if (deletePerson == 0) {
                serviceResponse.put("delete", "unable delete user");
            } else {
                logger.info("Successfully delete user.");
                serviceResponse.put("delete", "Successfully delete user.");
            }
            return Response.status(Response.Status.OK).entity(serviceResponse).build();

        } catch (Exception e) {

            logger.debug("<< create()");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(serviceResponse).build();
        }
    }

}