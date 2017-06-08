# Netty In Action
1. [Netty](http://netty.io/)是一款异步的事件驱动的**网络**应用程序框架，支持快速地开发可维护的**高性能**的面向协议的服务器和客户端
2. Netty主要构件块：
    + Channel
        - Java NIO的一个基本构造，代表一个到实体（如一个硬件设备、一个文件、一个网络套接字或者一个能够执行一个或者多个不同I/O操作的程序组件）的开放连接，如读操作和写操作
    + 回调
        - 一个回调就是一个方法，一个指向已经被提供给另外一个方法的方法引用，是在操作完成后通知相关方最常见的方式之一
    + Future
        - 另一种在操作完成时通知应用程序的方式，可以看做是一个异步操作的结果的占位符
    + 事件和ChannelHandler
3. 所有的Netty服务器都需要以下两部分：
    - 至少一个`ChannelHandler`——该组件实现了服务器从客户端接收的数据的处理，即它的业务逻辑
    - 引导——这是配置服务器的启动代码
4. Netty客户端锁涉及的两个主要代码部分也是业务逻辑和引导
5. `Channel`, `EventLoop`, `ChannelFuture`可以被认为是Netty网络抽象的代表
    - Channel----Socket
    - EventLoop----控制流、多线程处理、并发
        + 一个EventLoopGroup包含一个或多个EventLoop
        + 一个EventLoop在它的生命周期内只和一个Thread绑定
        + 所有由EventLoop处理的I/O事件都将在它专有的Thread上被处理
        + 一个Channel在它的生命周期内只注册一个EventLoop
        + 一个EventLoop可能会被分配一个或多个Channel
    - ChannelFuture----异步通知
6. `Bootstrap`--引导
    - 用于客户端，简单地称为Bootstrap，连接到远程主机和端口，需要一个EventLoopGroup
    - 用于服务器端，称为ServerBootstrap，绑定到一个本地端口，需要两个EventLoopGroup（可以是同一个实例）
7. Netty的Channel实现都是线程安全的，这意味着即使许多线程在使用同一个Channel，也不必担心同步问题
8. Netty提供的几种传输    
    + NIO:使用java.nio.channels包作为基础--基于选择器的方式
    + epoll:由JNI驱动的epoll()和非阻塞IO，这个传输支持只有在Linux上可用的多种特性，比NIO传输更快，且完全非阻塞
    + OIO:使用java.net包作为基础--使用阻塞流
    + Local:可以在VM内部通过管道进行通信的本地传输
    + Embedded:Embedded传输，允许使用ChannelHandler而又不需要一个真正的基于网络的传输
9. Java NIO选择器(`Selector`)运行在一个检查状态变化并对其作出相应响应的线程上，在应用程序对状态的改变做出响应之后，选择器将会被重置，并将重复这个过程。
