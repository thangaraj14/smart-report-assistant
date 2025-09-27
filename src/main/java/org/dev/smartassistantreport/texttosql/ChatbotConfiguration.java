package org.dev.smartassistantreport.texttosql;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Slf4j
@Configuration
class ChatbotConfiguration {

    @Bean
    PromptTemplate systemPrompt(
            @Value("classpath:system-prompt.st") Resource systemPrompt,
            @Value("classpath:db/migration/V1__create_table.sql") Resource ddlSchema
    ) throws IOException {
        log.info("Loading system prompt from {}", systemPrompt.getFilename());
        PromptTemplate template = new PromptTemplate(systemPrompt);
        template.add("ddl", ddlSchema.getContentAsString(Charset.defaultCharset()));
        return template;
    }

    @Bean
    ChatClient chatClient(ChatModel chatModel, PromptTemplate systemPrompt) {
        log.info("Loading chat client");
        return ChatClient
                .builder(chatModel)
                .defaultSystem(systemPrompt.render())
                .build();
    }

    @Bean
    ChatModel chatModel(OllamaApi ollamaApi) {
        OllamaOptions ollamaOptions = OllamaOptions.builder()
                .model("sqlcoder:7b")
                .temperature(0.0)
                .numPredict(256)
                .stop(List.of(";"))
                .build();
        return OllamaChatModel.builder().ollamaApi(ollamaApi).defaultOptions(ollamaOptions).build();
    }
}