package com.dacs.choithuephongtro.mappers;

import com.dacs.choithuephongtro.entities.Detail;
import com.dacs.choithuephongtro.model.DetailDTO;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper
public interface DetailMapper {
    Detail detailDtoToDetail(DetailDTO detailDto);
    DetailDTO detailToDetailDto(Detail detail);
}
