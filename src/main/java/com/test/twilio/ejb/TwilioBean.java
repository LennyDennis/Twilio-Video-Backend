
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.twilio.ejb;

import javax.ejb.Stateless;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.twilio.jwt.accesstoken.AccessToken;
import com.twilio.jwt.accesstoken.VideoGrant;
import com.twilio.rest.video.v1.Room;
import java.net.URI;

@Stateless
public class TwilioBean {

    public static final String ACCOUNT_SID = "AC9ae513b49f5ffb35e8ce26ce7758ce23";
    public static final String AUTH_TOKEN = "a47b64fe75d02d25661e74a309896374";

    public String testTwilio() {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+18573664943"),
                new PhoneNumber("+15005550006"),
                "This is the ship that made the Kessel Run in fourteen parsecs?").create();

        System.out.println(message.getSid());

        return message.getSid();
    }

    public String getAccessToken(String userName, String roomName) {

        String twilioAccountSid = "AC9ae513b49f5ffb35e8ce26ce7758ce23";
        String twilioApiKey = "SK8e3c33a28b784da5ed04928b136548c2";
        String twilioApiSecret = "FuWWsyiTus0c4dePsSp1g56V4FYq9vtE";

        // Required for Video
        String identity = userName;

        // Create Video grant
        VideoGrant grant = new VideoGrant().setRoom(roomName);

        // Create access token
        AccessToken token = new AccessToken.Builder(
                twilioAccountSid,
                twilioApiKey,
                twilioApiSecret
        ).identity(identity).grant(grant).build();

        return token.toJwt();
    }

    public Room createPeerToPeerRoom(String roomName) {
        
        Room room = null;
        if (!roomName.isEmpty()) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        
            room = Room.creator()
                    .setRecordParticipantsOnConnect(true)
                    .setType(Room.RoomType.GO)
                    .setUniqueName(roomName)
                    .create();
        }
        return room;
    }
}
