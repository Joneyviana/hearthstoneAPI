package com.api.hearthstone.web;

import com.api.hearthstone.ai.CardSearchExtractor;
import com.blizzard.hearthstone.core.HearthstoneCardApiQuery;
import com.blizzard.hearthstone.core.model.HearthstoneCard;
import dev.langchain4j.model.chat.ChatModel;

import dev.langchain4j.service.AiServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/cards")
public class HearthstoneController {

    private final HearthstoneCardApiQuery hearthstoneCardApiQuery;

    private final ChatModel chatModel;

    public HearthstoneController(HearthstoneCardApiQuery hearthstoneCardApiQuery, ChatModel chatModel) {
        this.hearthstoneCardApiQuery = hearthstoneCardApiQuery;
        this.chatModel = chatModel;
    }

    @PostMapping("/search")
    public ResponseEntity<List<HearthstoneCard>> getCards(@RequestBody Message message) {
        var words = message.content().split(" ");
        if (words.length == 1) {
            return new ResponseEntity<>(hearthstoneCardApiQuery
                    .textFilter(message.content()).search().cards(), HttpStatus.OK);
        }
        var cardSearchExtractor = AiServices.builder(CardSearchExtractor.class).chatModel(chatModel).build();
        var hearthstoneCardSearch = cardSearchExtractor.getCardSearch(message.content());
        return new ResponseEntity<>(hearthstoneCardApiQuery.search(hearthstoneCardSearch).cards(), HttpStatus.OK);
    }
}
