package net.glowstone.web;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.glowstone.GlowServer;
import net.glowstone.entity.GlowPlayer;

@RestController
@EnableAutoConfiguration
public class GlowWebServer {
	
	static GlowServer glowServer;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/")
	List<GlowPlayer> home() {
		return (List<GlowPlayer>) glowServer.getOnlinePlayers();
	}
	
	
	public static void main(String... args) {
        SpringApplication.run(GlowWebServer.class, args);
        glowServer = GlowServer.makeServer(args);
        glowServer.run();
    }
}
