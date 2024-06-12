package com.dacs.choithuephongtro.controller;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/word")
public class WordTemplateController {

    @PostMapping("/fill-form")
    public String fillWordTemplate(@RequestBody Map<String, String> formData) throws IOException {
        String templateFilePath = "E:/code/choi-thue-phong-tro/src/main/resources/static/room/demo.docx";
        String outputFilePath = "E:/code/choi-thue-phong-tro/src/main/resources/static/room/demo2.docx";

        try (FileInputStream fis = new FileInputStream(templateFilePath);
             XWPFDocument document = new XWPFDocument(fis)) {

            List<XWPFParagraph> paragraphs = document.getParagraphs();

            for (XWPFParagraph paragraph : paragraphs) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    String text = run.getText(0);
                    if (text != null) {
                        text = text.replace("BÊN_A", formData.get("BÊN_A"));
                        text = text.replace("ĐẠI_DIỆN", formData.get("ĐẠI_DIỆN"));
                        text = text.replace("CHỨC_VỤ", formData.get("CHỨC_VỤ"));
                        text = text.replace("QUỐC_TỊCH", formData.get("QUỐC_TỊCH"));

                        run.setText(text, 0);
                    }
                }
            }

            try (FileOutputStream fos = new FileOutputStream(outputFilePath)) {
                document.write(fos);
            }

            return "Thông tin đã được điền vào file Word và lưu tại: " + outputFilePath;

        } catch (IOException e) {
            e.printStackTrace();
            return "Lỗi khi điền thông tin vào file Word.";
        }
    }
}