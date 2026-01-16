package com.api.hearthstone.ai;



import com.blizzard.hearthstone.core.model.MagicCard;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface CardSearchExtractor {

    @SystemMessage("Search the list of terms in the attribute description for the corresponding term, if not found, fill in the attribute with null")
    @UserMessage("use text {{it}} to extract information")
    MagicCard getCardSearch(String content);
}
