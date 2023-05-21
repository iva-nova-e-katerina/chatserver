/*
Copyright (c) 2010-2016 Ivanova Ekaterina Alexeevna. All rights reserved.
PROPRIETARY. For demo purposes only, not for redistribution or any commercial 
use.
*/

package chatserver;

import java.nio.channels.AsynchronousSocketChannel;
import java.util.Optional;


/**
 * @author Ekaterina Alexeevna Ivanova
 * @since  Jul 15, 2010
 * @version 3.0
 *
 */
public abstract class AbstractCommand implements Command {

    private Optional<AsynchronousSocketChannel> destination;

    @Override
    public abstract void initCommand(User user, String content);
    @Override
    public abstract String getText();
   

    /**
     * @return the destination
     */
    @Override
    public Optional<AsynchronousSocketChannel> getDestination() {
        return destination;
    }

    /**
     * @param targetChannel the destination to set
     */
    protected void setDestination(Optional<AsynchronousSocketChannel> targetChannel) {
        this.destination = targetChannel;
    }

    @Override
    public String getHelpDetails() {
        return "help details are not specified";
    }
}
