package org.dev.smartassistantreport.texttosql;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SqlCoderGenerator {

    private ChatClient chatClient;

    public SqlCoderGenerator(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String generateSql(String message) {
        log.info("Generating SQL for message: {}", message);
        try {
            System.out.println("About to make chat client call...");
            ChatResponse chatResponse = chatClient.prompt(message).call().chatResponse();
            System.out.println("Chat client call completed successfully");
            System.out.println(chatResponse.getMetadata().getModel());
            String sqlQuery = chatResponse.getResult().getOutput().getText();
            if (!sqlQuery.toLowerCase().startsWith("select")) {
                log.error("Invalid SQL generated: {}", sqlQuery);
                throw new IllegalArgumentException("Expected SQL query, got: " + sqlQuery);
            }
            return sqlQuery;
        } catch (Exception ex) {
            System.err.println("Error during chat client call: " + ex.getMessage());
            ex.printStackTrace();
            log.error("Error generating SQL", ex);
        }
        return null;
    }
}
