package net.sympower.cityzen.apx;

import net.sympower.cityzen.apxrefactored.model.Quote;
import net.sympower.cityzen.apxrefactored.model.QuoteValue;
import net.sympower.cityzen.apxrefactored.util.ApxDataLoaderRefactored;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

;

@Component
public class ApxDataLoaderRefactoredTest {

    @Test
    public void load() throws Exception {

        ApxDataLoaderRefactored apxDataLoader = new ApxDataLoaderRefactored();
        apxDataLoader.setFileStr(getClass().getResource("apx-data.json").getFile());
        apxDataLoader.setFile(new File(apxDataLoader.getFileStr()));

        List<Quote> response = apxDataLoader.load();

        assertNotNull(response.equals(null));
        assertNotNull(response.isEmpty());
        assertTrue(response.size() > 0);

        Quote quoteFields = response.get(0);
        QuoteValue quoteValueFields = response.get(0).getQuoteValues().get(0);
        assertNotNull(quoteFields.equals(null));
        assertNotNull(quoteValueFields.equals(null));

    }


}
