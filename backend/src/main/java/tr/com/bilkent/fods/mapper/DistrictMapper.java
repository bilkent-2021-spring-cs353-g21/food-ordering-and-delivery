package tr.com.bilkent.fods.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tr.com.bilkent.fods.config.MapperConfig;
import tr.com.bilkent.fods.dto.district.DistrictDTO;
import tr.com.bilkent.fods.entity.district.District;

import java.util.List;

@Mapper(config = MapperConfig.class)
public interface DistrictMapper {
    DistrictMapper INSTANCE = Mappers.getMapper(DistrictMapper.class);

    @Mapping(target = ".", source = "districtKey")
    DistrictDTO districtToDto(District district);

    List<DistrictDTO> districtsToDtos(List<District> districts);
}
