/*
Copyright (c) 2010-2016 Ekaterina A. Ivanova (iceja.net). All rights reserved. 
PROPRIETARY. For demo purposes only, not for redistribution or any commercial 
use.
*/

package chatserver.commands;

import chatserver.AbstractCommand;
import chatserver.User;

/**
 * date Jul 22, 2010
 * @author Ekaterina A. Ivanova
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
