package com.mamadou.dockermultistaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
public class DockerMultiStagingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DockerMultiStagingApplication.class, args);
	}

	@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Message> messages() {
		String content = "Bob The Builder, Bob Marley, Tarzan, Banana, Mango";
		return Stream.of(content.split(","))
					 .map(Message::new)
					 .collect(Collectors.toList());
	}


	private class Message {
		private String content;

		public Message(String content) {
			this.content = content;
		}

		public String getContent() {
			return content;
		}
	}
}
