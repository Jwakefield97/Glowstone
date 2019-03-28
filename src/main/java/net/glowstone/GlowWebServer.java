package net.glowstone;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import org.bukkit.entity.Player;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;


//for (Player player : getOnlinePlayers()) {
//    if (player.getName().toLowerCase().startsWith(name)) {
//        int delta = player.getName().length() - name.length();
//        if (bestPlayer == null || delta < bestDelta) {
//            bestPlayer = player;
//        }
//    }
//}


@SuppressWarnings("restriction")
class MyHandler implements HttpHandler {
	public GlowServer glowServer;

	public MyHandler(GlowServer glowServer) {
		this.glowServer = glowServer;
	}
	
	@SuppressWarnings("restriction")
	@Override
	public void handle(HttpExchange t) throws IOException {
	    InputStream is = t.getRequestBody();
	    //read(is); // .. read the request body
	    String response = "Online players: \n";
	    
	    for (Player player : glowServer.getOnlinePlayers()) {
	    	response += player.getDisplayName() + "\n";
	    	
	    }
	    
	    t.sendResponseHeaders(200, response.length());
	    OutputStream os = t.getResponseBody();
	    os.write(response.getBytes());
	    os.close();
	}
}




public class GlowWebServer {
	
	@SuppressWarnings("restriction")
	public static HttpServer server; 
	
	public GlowServer glowServer;
	
	@SuppressWarnings("restriction")
	public GlowWebServer(GlowServer glowServer) {
		this.glowServer = glowServer;
		try {
			server = HttpServer.create(new InetSocketAddress(80), 0);
			server.createContext("/", new MyHandler(glowServer));
			server.setExecutor(null); // creates a default executor
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("restriction")
	public void run() {
		
		server.start();
	}

}
