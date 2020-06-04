package Homework_14_12.utils;

import Homework_14_12.pojo.TransactionDTO;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * Класс TransactionDeserializer.
 * Кастомный десериализатор для TransactionDTO.
 * @author Oleg Bech.
 * @email oleg071984@gnail.com
 */
public class TransactionDeserializer extends JsonDeserializer<TransactionDTO> {

    @Override
    public TransactionDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        return new TransactionDTO(
                node.get("uuid").asText(),
                node.get("date").asText(),
                node.get("account").asText(),
                node.get("type").asText(),
                node.get("sum").asDouble(),
                node.get("currency").asText(),
                node.get("balance").asDouble(),
                node.get("status").asText()
        );
    }
}
