package com.sakithya.rag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder, PgVectorStore vectorStore){
        this.chatClient= builder.defaultAdvisors(QuestionAnswerAdvisor.builder(vectorStore).build()).build();
    }

    @GetMapping("/")
    public String chat(@RequestBody String question){
        return chatClient.prompt()
                .user(question)
                .call()
                .content();
    }

}
