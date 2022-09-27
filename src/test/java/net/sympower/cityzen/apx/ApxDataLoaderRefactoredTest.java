package net.sympower.cityzen.apx;

import net.sympower.cityzen.apxrefactored.model.Quote;
import net.sympower.cityzen.apxrefactored.util.ApxDataLoaderRefactored;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

;

@Component
public class ApxDataLoaderRefactoredTest {

    @Test
    public void load() throws Exception {

        ApxDataLoaderRefactored apxDataLoader = new ApxDataLoaderRefactored();
        apxDataLoader.setFileStr(getClass().getResource("apx-data.json").getFile());
        apxDataLoader.setFile(new File(apxDataLoader.getFileStr()));

        List<Quote> response = apxDataLoader.load();

        assertNotNull(response);
        assertFalse(response.isEmpty());
        Quote quoteFields = response.get(0);
        assertNotNull(quoteFields.getQuoteValues());
        assertNotNull(quoteFields);

    }


}
