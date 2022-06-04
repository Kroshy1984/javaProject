package com.company;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import java.util.Arrays;
import io.netty.buffer.Unpooled;

public class Server {
  int port, concurrentClientCount = 0;

  public Server(int port) {
    this.port = port;
    this.concurrentClientCount = 0;
  }

  public void run() throws Exception {
    EventLoopGroup bossGroup = new NioEventLoopGroup();
    EventLoopGroup workerGroup = new NioEventLoopGroup();
    try {
      ServerBootstrap b = new ServerBootstrap();
      b.group(bossGroup, workerGroup)
       .channel(NioServerSocketChannel.class)
       .childHandler(new ChannelInitializer<SocketChannel>() {
         @Override
         public void initChannel(SocketChannel ch) throws Exception {
             ch.pipeline().addLast(new ServerHandler());
         }
       })
       .option(ChannelOption.SO_BACKLOG, 128)
       .childOption(ChannelOption.SO_KEEPALIVE, true);

      // Bind and start to accept incoming connections.
      ChannelFuture f = b.bind(port).sync();

      // Wait until the server socket is closed.
      // In this example, this does not happen, but you can do that to gracefully
      // shut down your server.
      f.channel().closeFuture().sync();
    } finally {
      workerGroup.shutdownGracefully();
      bossGroup.shutdownGracefully();
    }
  }


  public static void main(String[] args) throws Exception {
      new Server(28563).run();
  }


  class ServerHandler extends ChannelInboundHandlerAdapter {
    String dataForProcessing = "";
    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
      concurrentClientCount++;
      System.out.print(concurrentClientCount + " concurrent clients are connected\n");
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
      try {
        String allQueriesInOneString = dataForProcessing + ((ByteBuf) msg).toString(CharsetUtil.UTF_8);
        String[] queries = allQueriesInOneString.split("\r\n");
        if (!allQueriesInOneString.substring(allQueriesInOneString.length()-2).equals("\r\n"))
        {//если последний запрос неполный - запоминаем его для дальнейшего рассмотрения
          dataForProcessing = queries[queries.length-1];
          queries = Arrays.copyOf(queries, queries.length-1);
        }
        else dataForProcessing = "";
        for (String query : queries)
        {
          String[] words = query.split(" ");
          switch (words[0])
          {
            case ("CODE"):
              StringBuilder codeStr = new StringBuilder(words[1]);
              for (int i=0;i<words[1].length();i++)
              {
                codeStr.setCharAt(i,(char)((int)words[1].charAt(i)+2));
              }
              ctx.write(Unpooled.copiedBuffer(codeStr, CharsetUtil.UTF_8));
              break;
            case ("DECODE"):
              StringBuilder deCodeStr = new StringBuilder(words[1]);
              for (int i=0;i<words[1].length();i++)
              {
                deCodeStr.setCharAt(i,(char)((int)words[1].charAt(i)-2));
              }
              ctx.write(Unpooled.copiedBuffer(deCodeStr, CharsetUtil.UTF_8));
              break;
            default:
              break;
          }
          //String answer = words[1].equals("+") ? Integer.toString(Integer.parseInt(words[2]) + Integer.parseInt(words[3]))+"\r\n" : "ERR Unknown operator\r\n";
          //ctx.write(Unpooled.copiedBuffer(answer, CharsetUtil.UTF_8));
        }
        ctx.flush();
      } finally {
        ((ByteBuf) msg).release();
      }
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
        concurrentClientCount--;
    }
    @Override
    public void channelInactive(final ChannelHandlerContext ctx) {
      concurrentClientCount--;
    }
  }
}
