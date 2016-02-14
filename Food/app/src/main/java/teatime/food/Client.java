//s.write() to send to server
//r.readLine() to receive message

package teatime.food;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.io.DataInputStream;


public class Client {
    private String userID;  //our ID
    private String username;  //our username
    private String password;    //our password
    private String time;    //our time
    private String location;    //our location
    ArrayList<User> users = new ArrayList<User>();

    //writing
    private  OutputStream outToServer;
    private  DataOutputStream out;

    //reading
    private InputStream inFromServer;
    private DataInputStream in;


    public static void main(String args[]){

        /*try {
            client.connectToServer();
            //client.testServer();
            //client.login();
            //REMEMBER TO CLOSE PROGRAM

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    final Client client = new Client();
        Runnable refreshRunnable = new Runnable() {
            public void run() {
                try {
                    client.refresh();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        };
        Client thissucks = new Client();

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(refreshRunnable, 0, 30, TimeUnit.SECONDS);

        System.out.println("QUIT");
    }

    public void connectToServer() throws IOException{
        String serverName = "149.125.68.143";
        int port = 5001;

        Socket client = new Socket(serverName, port);
        outToServer = client.getOutputStream();
        out = new DataOutputStream(outToServer);
        inFromServer = client.getInputStream();
        in =  new DataInputStream(inFromServer);
        System.out.println("CONNECTED TO: " + client.getRemoteSocketAddress());
    }


    public boolean signup(String user, String pass, String first, String last) throws IOException{
        boolean noCommas = true;
        for(int i = 0; i < user.length(); i++)
            if(user.charAt(i) == ',') noCommas = false;
        for(int i = 0; i < pass.length(); i++)
            if(pass.charAt(i) == ',') noCommas = false;
        for(int i = 0; i < first.length(); i++)
            if(first.charAt(i) == ',') noCommas = false;
        for(int i = 0; i < last.length(); i++)
            if(last.charAt(i) == ',') noCommas = false;
        String send = "1,"+user+","+pass+","+first+","+last+","+"8457295732";
        if(noCommas) {
            out.writeUTF(user);
            out.flush();
            try {
                String message = in.readUTF();
                if (message.equals("0")) {
                    System.out.println("Username already in use");
                } else if (message.length() == 8) {
                    userID = message;
                } else {
                    System.out.println("ERROR, INVALID ID");
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        return noCommas;

    }
    public void testServer() throws IOException{
        out.writeUTF("HEY");
        out.flush();
        System.out.println(in.readUTF());

    }

    public void setUp() throws IOException {

        ArrayList<String> phoneNumbers = new ArrayList<String>();
        String ret = null;
        int comma = 0;
        int id;

        for (int i = 0; i < phoneNumbers.size(); i++) {

            out.writeUTF("2" + phoneNumbers.get(i));
            out.flush();

            ret = in.readUTF();

            if (ret != "0") {

                id = Integer.parseInt(ret.substring(0,8).trim());
                users.get(i).setID(id);
                comma = ret.indexOf(",");
                users.get(i).setName(ret.substring(8, comma) + " " + ret.substring(comma + 1, ret.length() - 1));

            }
        }

    }

    public void refresh() throws IOException {

        String ret = null;

        for (int i = 0; i < users.size(); i++) {
            out.writeUTF("3" + users.get(i).getID());
            out.flush();


            ret = in.readUTF();

            if (ret != "0") {
                users.get(i).setTime(ret.substring(0, 5));
                users.get(i).setLocation(ret.substring(5, ret.length()));
            }
        }
    }

    public void button() throws IOException {

        out.writeUTF("4" + time + location);
        out.flush();

    }

    public boolean login() throws IOException {

        String ret = null;

        out.writeUTF("5" + username + password);
        out.flush();
        ret = in.readUTF();

        if (ret.equals("0"))
            return false;
        return true;

    }

}