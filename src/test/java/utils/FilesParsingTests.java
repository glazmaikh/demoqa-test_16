package utils;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Configuration;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import model.GlossEntry;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import temp.FilesLessonTests;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class FilesParsingTests {
    ClassLoader cl = FilesLessonTests.class.getClassLoader();

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C://webdrivers/chromedriver.exe");
        Configuration.browserSize = "1932x1160";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void zipParseTest() throws Exception {
        ZipFile zf = new ZipFile(new File("src/test/resources/example2.zip"));
        ZipInputStream zis = new ZipInputStream(Objects.requireNonNull(cl.getResourceAsStream("example2.zip")));
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            try (InputStream is = zf.getInputStream(entry)) {
                if (entry.getName().endsWith(".pdf")) {
                    PDF pdf = new PDF(is);
                    assertThat(pdf.text).contains("JUnit 5 User Guide");
                } else if (entry.getName().endsWith(".xlsx")) {
                    XLS xls = new XLS(is);
                    assertThat(xls.excel.getSheetAt(0).getRow(1).getCell(1).getStringCellValue()).contains("ovva");
                } else {
                    CSVReader csv = new CSVReader(new InputStreamReader(is));
                    List<String[]> csvList = csv.readAll();
                    assertThat(csvList.get(0)[1]).contains("Delhi");
                }
            }
        }
    }

    @Test
    void jsonParsFromJecksonTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        File json = new File("src/test/resources/glossEntry.json");
        GlossEntry glossEntry = mapper.readValue(json, GlossEntry.class);
        assertThat(glossEntry.GlossTerm).isEqualTo("Standard Generalized Markup Language");
        assertThat(glossEntry.ID).contains("SG");
        assertThat(glossEntry.Acronym).isEqualTo(123);
        assertThat(glossEntry.Abbrev).isTrue();
        assertThat(glossEntry.items.get(0)).isEqualTo("menu");
        assertThat(glossEntry.Wind.title).isEqualTo("text");
    }
}
