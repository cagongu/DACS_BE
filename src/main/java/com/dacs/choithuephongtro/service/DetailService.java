package com.dacs.choithuephongtro.service;

import com.dacs.choithuephongtro.model.DetailDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public interface DetailService {
    DetailDTO store(UUID detailId, Float area, String description, MultipartFile file1, MultipartFile file2,MultipartFile file3,MultipartFile file4) throws IOException;

    Optional<DetailDTO> updateDetail(UUID uuid, DetailDTO detailDTO);
}
