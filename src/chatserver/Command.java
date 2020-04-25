/*
Copyright (c) 2010-2016 Ekaterina A. Ivanova (iceja.net). All rights reserved. 
PROPRIETARY. For demo purposes only, not for redistribution or any commercial 
use.
*/
package chatserver;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Optional;

/**
 * @author Ekaterina A. Ivanova
 */
public interface Command extends Ignition {

    public void initCommand(User user, String content);


    
    default void tryingWrite() {
        Optional<AsynchronousSocketChannel> channel = this.getDestination();
        if (!channel.isPresent()) {
            System.out.println("command " + this.getClass().getName()
                    + " with message " + this.getText() + " was not initialized");
            return;
        }
        synchronized (this.getDestination()) {
            if (channel.get().isOpen()) {

                ByteBuffer writeBuffer = ByteBuffer.allocateDirect(1024);
                writeBuffer.clear();

                writeBuffer.put((this.getText() + "\r\n").getBytes());
                writeBuffer.flip();
                this.getDestination().get().write(writeBuffer, this, new CompletionHandler() {

                    public void completed(Object result, Object attachment) {
                        System.out.println("to user ");

                    }

                    public void failed(Throwable exc, Object attachment) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                });
            }
        }
    };
   public String getText();

    public Optional<AsynchronousSocketChannel> getDestination();

    
    public String getHelpDetails();

}
