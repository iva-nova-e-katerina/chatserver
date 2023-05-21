package chatserver;

import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;

import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;
import java.util.concurrent.Executors;
import java.util.function.Predicate;


public class NIOServer {

    public static int port = 9999;
    
    public boolean running = true;
    AsynchronousServerSocketChannel assc = null;
    private LinkedHashMap<Integer, User> users = new LinkedHashMap<Integer, User>();
    private String WELCOME_MESSAGE = "welcome to telnet chat. \r\n commands: \r\n"
            + "to <user number> <text> - send to user \r\n"
            + "time - print system time \r\n"
            + "stop - terminate server \r\n"
            + "help <command> - display help details \r\n"
            + "--------------------------------\r\n "
            + "users online: \r\n ";
    
 
    public void tryingConnect() {
        if (assc.isOpen()) {
            User user = new User(this);
            user.setId(getUsers().keySet().size() + 1);
            this.assc.accept(this, user);
        } else {
            throw new IllegalStateException("");
        }
    }

    private Predicate<Command> testCommand = (cmd)->{
        return cmd == null;
    };
    
    public synchronized Command parseCommand(User user, String message) {
        Command command = null;
       
        String cmdName = "";
        StringTokenizer st = Util.tokenize.create(message);//new StringTokenizer(message);
        if (st.hasMoreTokens()) {
            cmdName = st.nextToken();
        }
       

        try {
            Class procClass = Class.forName("chatserver.commands." + cmdName.toLowerCase());
            command = (Command) procClass.newInstance();

            command.initCommand(user, message.substring(cmdName.length()));
            //Transceiver performOutput = command::tryingWrite;
    
        } catch (Exception ex) {
        }
        if (testCommand.test(command)){
           this.getUsers().forEach((Integer key, User val) -> {
                Command commandNew = new Message();
                commandNew.initCommand(val, "user #" + user.getId() + " said: " + message);
                commandNew.tryingWrite();
            });
            
        } else {            
            command.tryingWrite();
        }

        return command;
    }

    public void connect(int port) throws Exception {
        final AsynchronousChannelGroup asynchronousChannelGroup = AsynchronousChannelGroup.withCachedThreadPool(Executors.newCachedThreadPool(), 1000);
        this.assc = AsynchronousServerSocketChannel.open(asynchronousChannelGroup);
        this.assc.setOption(StandardSocketOptions.SO_RCVBUF,  16384);
        this.assc.setOption(StandardSocketOptions.SO_REUSEADDR, true);
        this.assc.bind(new InetSocketAddress(port));
        this.tryingConnect();
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        while (this.running) {
//            // infinite loop for accepting connections
        }
    }

    

    public static void main(String[] args) throws Exception {
        NIOServer nio = new NIOServer();
        nio.connect(port);
    }


    public String getWELCOME_MESSAGE() {
        //String result = new String(WELCOME_MESSAGE);
        StringBuffer result = new StringBuffer(WELCOME_MESSAGE);
        this.getUsers().forEach((Integer i, User val) -> {
            result.append("User " + val.getId() +  "\r\n ");
        });

        return result.toString();
    }


    public void setWELCOME_MESSAGE(String WELCOME_MESSAGE) {
        this.WELCOME_MESSAGE = WELCOME_MESSAGE;
    }

    /**
     * @return the users
     */
    public LinkedHashMap<Integer, User> getUsers() {
        return users;
    }

   
}


