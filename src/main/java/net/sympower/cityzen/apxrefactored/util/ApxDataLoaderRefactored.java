package net.sympower.cityzen.apxrefactored.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sympower.cityzen.apxrefactored.model.Quote;
import net.sympower.cityzen.apxrefactored.model.QuoteResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Configuration
@Slf4j
public class ApxDataLoaderRefactored {


    private static List<Quote> quoteList;

    @Value("${apxDataLoader.url}")
    private String fileStr;

    private File file;

    @PostConstruct
    public void init() throws IOException {
        if (this.file == null) {
            Resource resource = new ClassPathResource(fileStr);
            this.file = resource.getFile();
        }
    }

    @SneakyThrows(IOException.class)
    public List<Quote> load() {
        log.info("Inside ApxDataLoaderRefactored::load");
        if (null == quoteList) {
            ObjectMapper mapper = JsonMapper.builder().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).build();
            quoteList = mapper.readValue(file, QuoteResponse.class).getQuote();
            log.info("Json parse complete");

        }
        return quoteList;
    }

    public String getFileStr() {
        return fileStr;
    }

    public void setFileStr(String fileStr) {
        this.fileStr = fileStr;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
