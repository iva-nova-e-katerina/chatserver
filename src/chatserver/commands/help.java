
package chatserver.commands;

import java.util.StringTokenizer;
import chatserver.Command;
import chatserver.Message;
import chatserver.User;
import chatserver.Util;

/**
 * date Jul 22, 2010
 */
public class help extends Message {

    @Override
    public void initCommand(User user, String content) {
        this.setDestination(user.getChannel());
        String cmd = "";
        StringTokenizer st = Util.tokenize.create(content);
        if (st.hasMoreTokens()) {
            cmd = st.nextToken();
            try {
                Class procClass = Class.forName("chatserver.commands." + cmd.toLowerCase());
                Command command = (Command) procClass.newInstance();
                this.setText(command.getHelpDetails());
            } catch (Exception ex) {
               this.setText(cmd + " command not found");
            }
        }else{
           this.setText(cmd + " command not found");
        }
    }

    public String getHelpDetails(){
         return " display help on command";
    }
}
