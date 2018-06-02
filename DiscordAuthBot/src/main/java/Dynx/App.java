package Dynx;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;
import com.google.firebase.database.core.SyncTree;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;


import java.io.FileInputStream;
import java.util.List;

// BEFORE USING, PLEASE MAKE SURE YOU HAVE FOLLOWED INSTRUCTIONS ON THE README!

public class App  extends ListenerAdapter
{
    public static void main( String[] args ) throws Exception{
        JDA jda = new JDABuilder(AccountType.BOT).setToken(Ref.token).buildBlocking();
        jda.addEventListener(new App());
                                                                    // SET THIS TO YOUR PRIVATE KEY PATH
        FileInputStream serviceAccount = new FileInputStream("C:\\FIREBASEPROJECTPRIVATEKEY.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                                    //SET THIS TO YOUR REALTIME DATABASE URL
                .setDatabaseUrl("https://DISCORDAUTH.firebaseio.com")
                .build();
        FirebaseApp.initializeApp(options);

    }

    @Override
    public void onPrivateMessageReceived(PrivateMessageReceivedEvent evt) {
        User objUser = evt.getAuthor();
        final MessageChannel objMsgCh = evt.getChannel();
        final Message objMsg = evt.getMessage();

        if(objUser.isBot()) { return; }

        String message = objMsg.getContentRaw();

        final DatabaseReference ref = FirebaseDatabase
                .getInstance()
                .getReference();

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("keys").child(message).exists()){
                    if(dataSnapshot.child("keys").child(message).getValue().equals("FALSE")){
                        DatabaseReference keysRef = ref.child("keys");
                        keysRef.child(message).setValue("TRUE");
                        objMsgCh.sendMessage("KEY VERIFIED!").queue();
                        List<Guild> guildList = objUser.getMutualGuilds();
                        Guild milkFam = guildList.get(0);
                                                 //SET THE INSIDE OF THE QUOTES TO THE NAME OF THE ROLE YOU WANT TO ADD THE USER TO
                        List<Role> roles = milkFam.getRolesByName("member", true);
                                                //SET THIS TO THE NAME OF THE ROLE YOU WANT TO REMOVE THE USER FROM, IF YOU DONT WANT TO REMOVE A ROLE, JUST ADD A // BEFORE THE NEXT LINE AND THE LINE WITH A NUMBER 2 ON IT
                        List<Role> unverifiedRoles = milkFam.getRolesByName("unverified", true);
                        milkFam.getController().addSingleRoleToMember(milkFam.getMember(objUser), roles.get(0)).complete();
                    /* 2 */    milkFam.getController().removeSingleRoleFromMember(milkFam.getMember(objUser), unverifiedRoles.get(0)).complete();

                        return;
                    } else {
                        objMsgCh.sendMessage("KEY IS ALREADY IN USE!").queue();
                        return;
                    }
                } else {
                    objMsgCh.sendMessage("KEY IS INVALID!").queue();
                }

            }

            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Error reading database! " + databaseError.getMessage());
            }
        });

    }


}
