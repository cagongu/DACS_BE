package com.dacs.choithuephongtro.service;

import com.dacs.choithuephongtro.mappers.DetailMapper;
import com.dacs.choithuephongtro.model.DetailDTO;
import com.dacs.choithuephongtro.repositories.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class DetailServiceImpl implements DetailService {
    @Autowired
    private DetailRepository detailRepository;
    @Autowired
    private DetailMapper detailMapper;

    @Override
    public DetailDTO store(UUID detailId, Float area, String description, MultipartFile file1, MultipartFile file2, MultipartFile file3,MultipartFile file4) throws IOException {
        DetailDTO detailDTO = detailMapper.detailToDetailDto(detailRepository.findById(detailId).orElse(null));

        detailDTO.setImage1(file1.getBytes());

        detailDTO.setImage2(file2.getBytes());

        detailDTO.setImage3(file3.getBytes());

        detailDTO.setImage4(file4.getBytes());

        detailDTO.setArea(area);

        detailDTO.setDescription(description);

        detailRepository.save(detailMapper.detailDtoToDetail(detailDTO));

        return detailDTO;
    }

    @Override
    public Optional<DetailDTO> updateDetail(UUID uuid, DetailDTO detailDTO) {
        AtomicReference<Optional<DetailDTO>> atomicReference = new AtomicReference<>();

        detailRepository.findById(uuid).ifPresentOrElse(found -> {
            if(detailDTO.getArea() != null){
                found.setArea(detailDTO.getArea());

            }
            if(StringUtils.hasText(detailDTO.getDescription())){
                found.setDescription(detailDTO.getDescription());
            }
            if(detailDTO.getImage1().length > 0){
                found.setImage1(detailDTO.getImage1());
            }
            if(detailDTO.getImage2().length > 0){
                found.setImage2(detailDTO.getImage2());
            }
            if(detailDTO.getImage3().length > 0){
                found.setImage3(detailDTO.getImage3());
            }
            if(detailDTO.getImage4().length > 0){
                found.setImage4(detailDTO.getImage4());
            }

            atomicReference.set(Optional.of(detailMapper.detailToDetailDto(detailRepository.save(found))));
        }, () -> atomicReference.set(Optional.empty()));

        return atomicReference.get();    }
}
