import java.net.*;
import java.io.*;
class Server{
        ServerSocket server;
        Socket socket;
        BufferedReader br ;
        PrintWriter out;
    //constructor
    public Server(){
     try {
        server = new ServerSocket(7777);
        System.out.println("server is ready to accept connection");
        System.out.println("waiting for server");
        socket=server.accept();

        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream());

        startReading();
        startWriting();
     } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
     }
      

    }
    public void startReading(){
        Runnable r1 = () -> {
            System.out.println("Reader started");
            try{
            while(true){
                
                String mgs = br.readLine();
                if(mgs.equals("bye")){
                    System.out.println("Client terminated the chat");
                    socket.close();
                    break;
                }
                System.out.println("Client :" + mgs);
            

        }
    } catch(Exception e){
       // e.printStackTrace();
       System.out.println("Connetion is closed");
    }
         
    };
    new Thread(r1).start();
}

public void startWriting(){
    Runnable r2=()->{
        try{
        while(!socket.isClosed())
        {
            
                BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                String content = br1.readLine();
                    out.println(content);
                    out.flush();       
                         if(content.equals("bye")){
                         System.out.println("Client terminated the chat");
                            socket.close();
                            break;
                    }
        } 
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Connetion is closed");
            }  
        
        }; 
    
    new Thread(r2).start();
}
    public static void main(String[] args) {
       
             
        System.out.println("this is server...going to start server");
        new Server();
    }
};