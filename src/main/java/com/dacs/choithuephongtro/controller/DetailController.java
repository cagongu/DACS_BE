package com.dacs.choithuephongtro.controller;

import com.dacs.choithuephongtro.Exception.NotFoundException;
import com.dacs.choithuephongtro.Response.DetailUploadImageResponse;
import com.dacs.choithuephongtro.model.DetailDTO;
import com.dacs.choithuephongtro.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController("/api/v1")
public class DetailController {
    private static final String DETAIL_PATH = "/detail";
    private static final String DETAIL_PATH_ID = "/{detailId}";


    @Autowired
    private DetailService detailService;

    @PostMapping(value = DETAIL_PATH)
    public ResponseEntity<DetailUploadImageResponse> CreateDetail(UUID id,@RequestParam("area") Float area, @RequestParam("description") String description, @RequestParam("file1") MultipartFile file1,
                                                                @RequestParam("file2") MultipartFile file2,
                                                                @RequestParam("file3") MultipartFile file3,
                                                                @RequestParam("file4") MultipartFile file4) throws IOException {
        detailService.store(id, area, description, file1,file2,file3,file4);
        DetailUploadImageResponse detailUploadImageResponse = new DetailUploadImageResponse();
        detailUploadImageResponse.setImage1(file1.getOriginalFilename());
        detailUploadImageResponse.setImage2(file2.getOriginalFilename());
        detailUploadImageResponse.setImage3(file3.getOriginalFilename());
        detailUploadImageResponse.setImage4(file4.getOriginalFilename());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(detailUploadImageResponse);
    }

    @PutMapping(DETAIL_PATH_ID)
    public ResponseEntity<DetailDTO> updateById(@PathVariable("detailId") UUID detailId, @Validated @RequestBody DetailDTO detailDTO) {

        if (detailService.updateDetail(detailId, detailDTO).isEmpty()) {
            throw new NotFoundException();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
