# DISCORD AUTH BOT
### BY: DYNX (@DynxSZN)

#### SETUP

###### BEFORE WE START, MAKE SURE YOU HAVE ATLEAST THE JDK8 INSTALLED! ALSO MAKE SURE YOU HAVE A JAVA IDE (SUCH AS ECLIPSE) INSTALLED!

1. Create a json file with the following format:
    {
     "keys" : {
     "keyone" : "FALSE",
     "keytwo" : "FALSE"
     }
   }
   * Feel free to add more keys in the same format with a comma at the end to make sure you have multiple keys entered!
   * Also make sure that any new key you create has "FALSE" next to it in all uppercase!
2. Create a firebase project
3. Click on Database -> Realtime Database
4. Click on the three dots on the top right, click import json and import the json created in step one
5. Copy and save the link at the top of the realtime database table. You will need this for later.
6. Click on the gear icon next to project overview in the top left and click project settings
7. Click on the service accounts tab
8. Under the Firebase Admin SDK tab, click generate new private key and save this file somewhere you will remember
9. Go to discordapp.com/developers and sign in
10. Create a new bot with your name or any preferences you would like
11. Click save and then scroll down and click create new bot user
12. Click on show token and copy and save this token for later
13. Go ahead and open up the code you will find two main files, App.java and Ref.java
  * App.java is the main file
  * Ref.java contains your token
14. Go into App.java and replace the following things:
  * Read the comments (the lines in green) and make the necessary changes to the code

### ENJOY :D FEEL FREE TO PM ME ON TWITTER @DynxSZN IF YOU NEED ANY HELP!
