
package chatserver;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * date Jul 15, 2010

 */
public class User implements CompletionHandler<AsynchronousSocketChannel, NIOServer> {

    private int id;
    private NIOServer server;
    private ByteBuffer m_buffer = ByteBuffer.allocateDirect(1024);
    private Optional<AsynchronousSocketChannel> channel;

    public User() {
    }

    public User(NIOServer nioserver) {
        this.server = nioserver;
    }

    public void completed(AsynchronousSocketChannel result, NIOServer attachment) {
        System.out.println("connected");
        this.setChannel((AsynchronousSocketChannel) result);
        attachment.getUsers().putIfAbsent(this.getId(), this);
        Command welcomeMessage = new Message();
        welcomeMessage.initCommand(this, attachment.getWELCOME_MESSAGE()
                + "\r\n you are user #" + this.getId() + "\r\n");
        welcomeMessage.tryingWrite();

        this.tryingRead(result);
        attachment.tryingConnect();

    }

    protected final void tryingRead(AsynchronousSocketChannel assc) {
        //int count;
        if (assc.isOpen()) {
            assc.read(this.getM_buffer(), this, new CompletionHandler<Integer, User>() {

                @Override
                public void completed(Integer length, User attachment) {
                    if(length == -1){
                        attachment.getM_buffer().clear();
                        return;
                    }
                    System.out.println(attachment.toString());
                    attachment.getM_buffer().flip();
                    while (attachment.getM_buffer().hasRemaining()) {
                        //int size = attachment.getM_buffer().remaining();
                        byte[] privateBuffer = new byte[length];
                        attachment.getM_buffer().get(privateBuffer);
                        String tempStr = new String(privateBuffer);
                        System.out.print(tempStr);
                        attachment.getServer().parseCommand(User.this, tempStr);
                    }
                    getM_buffer().clear();
                    System.out.println(Thread.getAllStackTraces().get(Thread.currentThread()).toString());
                    attachment.tryingRead(User.this.getChannel().get());
                }

                public void failed(Throwable exc, User attachment) {
                    try {
                        attachment.channel.get().close();
                        attachment.getServer().getUsers().remove(attachment.getId());
                    } catch (IOException ex) {
                        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } else {
            throw new IllegalStateException("");
        }
    }

    @Override
    public void failed(Throwable exc, NIOServer attachment) {
        try {
            this.channel.get().close();
            this.m_buffer.clear();
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("failed" + attachment.assc.provider().toString());

    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the server
     */
    public NIOServer getServer() {
        return server;
    }

    /**
     * @param server the server to set
     */
    public void setServer(NIOServer server) {
        this.server = server;
    }

    /**
     * @return the m_buffer
     */
    public ByteBuffer getM_buffer() {
        return m_buffer;
    }

    /**
     * @param m_buffer the m_buffer to set
     */
    public void setM_buffer(ByteBuffer m_buffer) {
        this.m_buffer = m_buffer;
    }

    /**
     * @return the channel
     */
    public Optional<AsynchronousSocketChannel> getChannel() {
        return channel;
    }

    /**
     * @param channel the channel to set
     */
    public void setChannel(AsynchronousSocketChannel channel) {
        this.channel = Optional.of(channel);
    }
}
