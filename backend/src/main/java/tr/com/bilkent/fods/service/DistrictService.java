package tr.com.bilkent.fods.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.bilkent.fods.dto.district.CityDTO;
import tr.com.bilkent.fods.dto.district.DistrictDTO;
import tr.com.bilkent.fods.entity.district.District;
import tr.com.bilkent.fods.mapper.DistrictMapper;
import tr.com.bilkent.fods.repository.DistrictRepository;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DistrictService {
    private final DistrictRepository districtRepository;

    @Autowired
    public DistrictService(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    public Set<CityDTO> getCities() {
        Set<String> cityNames = districtRepository.getCityNames();
        return cityNames.stream().map(CityDTO::new).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public List<DistrictDTO> getDistrictsOfCity(String cityName) {
        List<District> districts = districtRepository.getByCityName(cityName);
        return DistrictMapper.INSTANCE.districtsToDtos(districts);
    }
}
