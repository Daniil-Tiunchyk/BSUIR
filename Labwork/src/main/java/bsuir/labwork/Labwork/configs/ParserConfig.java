package bsuir.labwork.Labwork.configs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParserConfig {
    private static ParserConfig instance;

    private String creditCardFilePath;

    private ParserConfig() {
        this.creditCardFilePath = "credit_cards.xml";
    }

    public static ParserConfig getInstance() {
        if (instance == null) {
            instance = new ParserConfig();
        }
        return instance;
    }
}
