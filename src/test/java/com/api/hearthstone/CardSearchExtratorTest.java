package com.api.hearthstone;

import com.blizzard.hearthstone.core.model.HearthstoneCardSearch;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardSearchExtratorTest {

    @Test
    public void test() throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var string = "{\n" +
                "  \"keyword\": \"Provocar\",\n" +
                "  \"type\": \"MINION\",\n" +
                "  \"set\": \"CORE\",\n" +
                "  \"minionType\": \"ALL\",\n" +
                "  \"classe\": \"Mago\",\n" +
                "  \"mana\": null,\n" +
                "  \"health\": null,\n" +
                "  \"attack\": null\n" +
                "}";
       var response = objectMapper.readValue(string, HearthstoneCardSearch.class);

       assertEquals("Mago", response.classe());
    }
}
