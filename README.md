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
