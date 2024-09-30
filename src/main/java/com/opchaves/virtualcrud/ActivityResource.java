package com.opchaves.virtualcrud;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

import java.net.URI;
import java.util.List;

import org.bson.types.ObjectId;

import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@Path("/activities")
@RunOnVirtualThread
public class ActivityResource {

  final static ObjectId USER_ID = new ObjectId("66fa811ac22c40057d5a8ac9");

  ActivityService activityService;

  public ActivityResource(ActivityService activityService) {
    this.activityService = activityService;
  }

  @GET
  public List<ActivityDTO> listAll() {
    return activityService.listAll();
  }

  @GET
  @Path("{id}")
  public ActivityDTO findById(String id) {
    ActivityDTO activity = activityService.findById(new ObjectId(id));
    if (activity == null) {
      throw new WebApplicationException("Activity not found", NOT_FOUND);
    }
    return activity;
  }

  @POST
  public Response createActivity(@Valid ActivityDTO input) {
    ActivityDTO activity = activityService.create(input, USER_ID);
    URI uri = URI.create("/activities/" + activity.id);
    return Response.created(uri).entity(activity).build();
  }

  @PUT
  @Path("{id}")
  public Response updateActivity(String id, @Valid ActivityDTO input) {
    ActivityDTO activity = activityService.update(new ObjectId(id), input, USER_ID);
    if (activity == null) {
      throw new WebApplicationException("Activity not found", NOT_FOUND);
    }
    return Response.ok(activity).build();
  }

  public Response deleteActivity(String id) {
    var oId = new ObjectId(id);
    ActivityDTO activity = activityService.findById(oId);
    if (activity == null) {
      throw new WebApplicationException("Activity not found", NOT_FOUND);
    }
    activityService.delete(oId, USER_ID);
    return Response.noContent().build();
  }
}
