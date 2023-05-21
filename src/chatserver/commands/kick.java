
package chatserver.commands;

import chatserver.AbstractCommand;
import chatserver.Message;
import chatserver.User;

/**
 *
 * @author katya
 */
public class kick extends AbstractCommand{
    
    
    @Override
    public void initCommand(User user, String content) {
    /// this command should kick user
    }

    @Override
    public String getText() {
        return "user " ;
    }
    
    @Override
    public String getHelpDetails() {
        return " kicking out user ";
    }
    
}
