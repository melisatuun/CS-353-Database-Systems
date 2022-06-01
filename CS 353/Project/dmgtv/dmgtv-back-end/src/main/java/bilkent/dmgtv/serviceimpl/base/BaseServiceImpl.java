package bilkent.dmgtv.serviceimpl.base;

import bilkent.dmgtv.db.base.BaseEntity;
import bilkent.dmgtv.dto.base.BaseDto;
import bilkent.dmgtv.repository.base.BaseRepository;
import bilkent.dmgtv.service.base.BaseCrudService;
import bilkent.dmgtv.serviceimpl.mapper.base.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BaseServiceImpl<E extends BaseEntity, D extends BaseDto<UUID>> implements BaseCrudService<D>
{
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseServiceImpl.class);

	protected final BaseRepository<E, UUID> baseRepository;

	private final BaseMapper<E, D> baseMapper;

	public BaseServiceImpl(BaseRepository<E, UUID> baseRepository, BaseMapper<E, D> baseMapper) {
		this.baseRepository = baseRepository;
		this.baseMapper = baseMapper;
	}

	@Override
	public D create(D dto) throws EntityNotFoundException
	{
		E entity = baseMapper.dtoToEntity(dto);
		if (entity == null) {
			LOGGER.warn("The entity to save cannot be empty!");
			throw new EntityNotFoundException();
		}
		return baseMapper.entityToDto(baseRepository.save(entity));
	}

	@Override
	public List<D> createAll(List<D> dtoList) throws EntityNotFoundException
	{
		List<E> list = new ArrayList<>();
		for (D dto: dtoList)
		{
			E entity = baseMapper.dtoToEntity(dto);
			if (entity == null) {
				LOGGER.warn("The entity to save cannot be empty!");
				throw new EntityNotFoundException();
			}
			list.add(entity);
		}
		return baseMapper.entityListToDtoList(baseRepository.saveAll(list));
	}

	@Override
	public D read(UUID id) throws EntityNotFoundException
	{
		if (id == null)
		{
			LOGGER.warn("The id cannot be empty!");
			throw new EntityNotFoundException();
		}
		E base = baseRepository.getById(id);
		return baseMapper.entityToDto(base);
	}

	@Override
	public List<D> readAll()
	{
		return baseMapper.entityListToDtoList(baseRepository.findAll());
	}

	@Override
	public D update(D dto) throws EntityNotFoundException
	{
		if (dto == null) {
			LOGGER.warn("The entity to update cannot be empty!");
			throw new EntityNotFoundException();
		}
		Optional<E> dbEntity = baseRepository.findById(dto.getId());
		if (!dbEntity.isPresent()) {
			LOGGER.warn("The entity to update could not be found!");
			throw new EntityNotFoundException();
		}
		E entity = baseMapper.dtoToEntity(dto);
		baseRepository.save(entity);
		return dto;
	}

	@Override
	public List<D> updateAll(List<D> dtoList)
	{
		ArrayList<D> updatedList = new ArrayList();
		E entity;
		for (D dto: dtoList)
		{
			if (dto == null) {
				LOGGER.warn("The entity to update cannot be empty!");
				throw new EntityNotFoundException();
			}
			E dbEntity = baseRepository.getById(dto.getId());
			if (dbEntity == null) {
				LOGGER.warn("The entity to update could not be found!");
				throw new EntityNotFoundException();
			}
			entity = baseRepository.getById(dto.getId());
			entity = baseMapper.dtoToEntity(dto);
			baseRepository.save(entity);
			updatedList.add(dto);
		}
		return updatedList;
	}

	@Override
	public D delete(UUID id) throws EntityNotFoundException
	{
		Optional<E> entity = baseRepository.findById(id);
		if (!entity.isPresent())
		{
			LOGGER.warn("Entity with id " + id + " was not found!");
			throw new EntityNotFoundException();
		}
		baseRepository.delete(entity.get());
		return baseMapper.entityToDto(entity.get());
	}

	@Override
	public List<D> deleteAll(List<String> StringidList) throws EntityNotFoundException
	{
		List<E> entityList = new ArrayList<>();
		Optional<E> entity;
		D dto;
		for (String id: StringidList)
		{
			entity = baseRepository.findById(UUID.fromString(id));
			if (!entity.isPresent())
			{
				LOGGER.warn("Entity with id " + id + " was not found!");
				throw new EntityNotFoundException();
			}
			entityList.add(entity.get());
		}
		for (E deletedEntity : entityList)
		{
			baseRepository.delete(deletedEntity);
		}
		return baseMapper.entityListToDtoList(entityList);
	}

	@Override
	public boolean existsById(UUID id)
	{
		if (id == null)
		{
			LOGGER.warn("id cannot be empty!");
			return false;
		}
		return baseRepository.findById(id).isPresent();
	}
}
