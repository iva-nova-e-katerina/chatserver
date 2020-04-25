/*
Copyright (c) 2010-2016 Ekaterina A. Ivanova (iceja.net). All rights reserved. 
PROPRIETARY. For demo purposes only, not for redistribution or any commercial 
use.
*/

package chatserver.commands;

import chatserver.AbstractCommand;
import chatserver.User;

/**
 *
 * @author Ekaterina A. Ivanova
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
