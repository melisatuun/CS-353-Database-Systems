package bilkent.dmgtv.serviceimpl.mapper.base;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapConfig
{
}
