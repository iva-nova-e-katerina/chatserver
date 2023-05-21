
package chatserver.commands;

import chatserver.AbstractCommand;
import chatserver.User;

/**
 * date Jul 22, 2010
 */
public class stop extends AbstractCommand {
    
    @Override
    public void initCommand(User user, String content) {
        user.getServer().running = false;
        System.exit(0);
    }

    /**
     *
     * @return
     */
    @Override
    public String getText() {
        return "server stop command";
    }

    @Override
    public String getHelpDetails() {
        return " terminating server ";
    }
}
