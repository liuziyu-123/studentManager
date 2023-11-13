package com.studentManager.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.studentManager.common.Entry.UserEntry;
import com.studentManager.user.entry.RegionEntry;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TsMapper extends BaseMapper<UserEntry> {

    List<RegionEntry> getProvince();

    List<RegionEntry> getCity(String provinceName);

    List<RegionEntry> getArea(String cityName);

}
