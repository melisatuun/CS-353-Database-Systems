package bilkent.dmgtv.serviceimpl.mapper.base;

import bilkent.dmgtv.db.base.BaseEntity;
import bilkent.dmgtv.dto.base.BaseDto;

import java.util.List;
import java.util.UUID;

public interface BaseMapper<E extends BaseEntity, D extends BaseDto<UUID>>
{
	E dtoToEntity(D dto);

	D entityToDto(E entity);

	List<E> dtoListToEntityList(List<D> dtoList);

	List<D> entityListToDtoList(List<E> entityList);
}
