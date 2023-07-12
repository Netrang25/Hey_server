import java.io.*;

import java.net.*;

public class client {

    Socket socket;
        BufferedReader br ;
        PrintWriter out;
    public client()
    {
        try {
            System.out.println("Sending Request to connection");
            socket=new Socket("127.0.0.1",7777);
            System.out.println("connetion done");
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            startReading();
            startWriting();

            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Connetion is closed");

        }
    }
    public void startReading(){
        Runnable r1 = () -> {
            System.out.println("Reader started");
            try{
            while(!socket.isClosed()){
                
                String mgs = br.readLine();
                if(mgs.equals("bye")){
                    System.out.println("Client terminated the chat");
                    socket.close();
                    break;
                }
                System.out.println("Server :" + mgs);
            

        }
    } catch(Exception e){
        System.out.println("Connetion is closed");
    }
         
    };
    new Thread(r1).start();
}


    public void startWriting(){
            Runnable r2=()->{
                try{
                while(true)
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
                        e.printStackTrace();
                    }  
                
                }; 
            
            new Thread(r2).start();
    }
    public static void main(String[] args) {
        System.out.println("This is client");
        new client();
    }
    
}
