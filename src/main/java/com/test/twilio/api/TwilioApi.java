/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.twilio.api;

import com.test.twilio.ejb.TwilioBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("twilio")
@Produces({MediaType.TEXT_PLAIN})
public class TwilioApi {

    @EJB
    private TwilioBean tBean;

    @GET
    public Response testTwilioConnection() {
        return Response.ok(tBean.testTwilio()).build();
    }

    @GET
    @Path("token")
    public Response getTwilio(@QueryParam("username") String userName, @QueryParam("roomname") String roomName) {
        return Response.ok(tBean.getAccessToken(userName, roomName)).build();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("room")
    public Response createRoom(@QueryParam("roomname") String roomName) {
        return Response.ok(tBean.createPeerToPeerRoom(roomName)).build();
    }

}
