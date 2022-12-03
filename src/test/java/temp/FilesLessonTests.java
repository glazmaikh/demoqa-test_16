package temp;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import model.Glossary;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class FilesLessonTests {

    static ZipFile zipFile;
    static ClassLoader cl;

    @BeforeAll
    static void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", "C://webdrivers/chromedriver.exe");
        Configuration.browserSize = "1932x1160";
        Configuration.holdBrowserOpen = true;
        zipFile = new ZipFile("src/test/resources/example2.zip");
        cl = FilesLessonTests.class.getClassLoader();
    }



//    использовать если нет атрибута href
//    static {
//        Configuration.fileDownload = FileDownloadMode.PROXY;
//        Configuration.downloadsFolder = "";
//    }
    @Test
    void selenideDownloadTest() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloadedFile = $("#raw-url").download();
        try (InputStream is = new FileInputStream(downloadedFile)) {
            byte[] bytes = is.readAllBytes();
            String textContent = new String(bytes, StandardCharsets.UTF_8);
            assertThat(textContent).contains("This repository is the home of the next generation of JUnit, _JUnit 5_");
        }
    }

    @Test
    void selenideUploadFileTest() {
        open("https://fineuploader.com/demos.html");
        $("input[type='file']").uploadFromClasspath("image.jpg");
        $("div.qq-file-info").shouldHave(Condition.text("image.jpg"));
        //get attribute value
        //$("span.qq-upload-file-selector").shouldHave(attribute("title", "image.jpg"));
    }

    @Test
    void pdfParseTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("junit-user-guide-5.9.1.pdf")) {

        }
    }

    @Test
    void xlsParseTest() throws Exception {
        try (InputStream resourcesAsStream = cl.getResourceAsStream("book.xlsx")) {
            XLS content = new XLS(resourcesAsStream);
            assertThat(content.excel.getSheetAt(0).getRow(1).getCell(1).getStringCellValue()).contains("ovva");
        }
    }

    @Test
    void csvParseTest() throws Exception {
        try (InputStream resources = cl.getResourceAsStream("example1.csv");
             CSVReader reader = new CSVReader(new InputStreamReader(resources))
        ) {
            List<String[]> content = reader.readAll();
            assertThat(content.get(0)[1]).contains("Delhi");
        }
    }

    @Test
    void zipParseTest() throws Exception {
        try (InputStream resources = cl.getResourceAsStream("simpe.zip");
             ZipInputStream zis = new ZipInputStream(resources)
        ) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                assertThat(entry.getName()).isEqualTo("simpe.xlsx");
            }
        }
    }

    @Test
    void toZipTest() throws Exception {
        File path = new File("C:/Users/serge/IdeaProjects/demoqa-tests_16/src/test/resources/example1.csv");
        File pathOut = new File("C:/Users/serge/IdeaProjects/demoqa-tests_16/src/test/resources/example2.zip");
        try (FileInputStream fis = new FileInputStream(path);
             ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(pathOut))
        ) {
            zos.putNextEntry(new ZipEntry(pathOut.getName()));
            byte[] bytes = new byte[1024];
            int length;
            while((length = fis.read(bytes)) >= 0) {
                zos.write(bytes, 0, length);
            }
        }
    }

    @Test
    void jsonParsTest() throws Exception {
        Gson gson = new Gson();
        try (InputStream resource = cl.getResourceAsStream("temp/glossary.json");
             InputStreamReader reader = new InputStreamReader(resource)
        ) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            assertThat(jsonObject.get("title").getAsString()).isEqualTo("example glossary");
            assertThat(jsonObject.get("GlossDiv").getAsJsonObject().get("flag").getAsBoolean()).isTrue();
        }
    }

    @Test
    void jsonParsImprovedTest() throws Exception {
        Gson gson = new Gson();
        try (InputStream resource = cl.getResourceAsStream("temp/glossary.json");
             InputStreamReader reader = new InputStreamReader(resource)
        ) {
            Glossary glossaryObject = gson.fromJson(reader, Glossary.class);
            assertThat(glossaryObject.title).isEqualTo("example glossary");
            assertThat(glossaryObject.glossDiv.title).isEqualTo("S");
            assertThat(glossaryObject.glossDiv.flag).isTrue();
        }
    }
}
