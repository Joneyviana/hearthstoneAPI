package com.api.hearthstone.config;

import com.blizzard.hearthstone.core.HearthstoneCardApiQuery;
import com.blizzard.hearthstone.core.httpClient.HttpClientHearthstoneProvider;
import com.blizzard.hearthstone.core.model.HearthstoneRegion;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.ObjectMapper;

import java.util.Locale;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;

@Configuration
public class AppConfig {
    @Value("${app.hearthstone_id}")
    private String hearthstoneId;
    @Value("${app.hearthstone_secret}")
    private String hearthstoneSecret;
    @Value("${app.langchain_key}")
    private String langChainKey;


    @Bean
    public HearthstoneCardApiQuery hearthstoneCardApiQuery(){
        var hearthstoneApi = HttpClientHearthstoneProvider.create(hearthstoneId, hearthstoneSecret, HearthstoneRegion.AMERICA);
        return new HearthstoneCardApiQuery(hearthstoneApi).locale(Locale.of("pt", "BR"));
    }


    @Bean
    public ChatModel chatModel(){
        return OpenAiChatModel.builder()
                .apiKey(langChainKey)
                .modelName(GPT_4_O_MINI)
                .build();
    }

    public void setHearthstoneId(String hearthstoneId) {
        this.hearthstoneId = hearthstoneId;
    }

    public void setHearthstoneSecret(String hearthstoneSecret) {
        this.hearthstoneSecret = hearthstoneSecret;
    }

    public void setLangChainKey(String langChainKey) {
        this.langChainKey = langChainKey;
    }
}
