package bilkent.dmgtv.serviceimpl;

import bilkent.dmgtv.db.Request;
import bilkent.dmgtv.dto.RequestDto;
import bilkent.dmgtv.repository.RequestRepository;
import bilkent.dmgtv.service.RequestService;
import bilkent.dmgtv.serviceimpl.base.BaseServiceImpl;
import bilkent.dmgtv.serviceimpl.mapper.RequestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
public class RequestServiceImpl extends BaseServiceImpl<Request, RequestDto> implements RequestService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestServiceImpl.class);

	private final RequestRepository requestRepository;
	private final RequestMapper requestMapper;

	public RequestServiceImpl(RequestRepository requestRepository, RequestMapper requestMapper)
	{
		super(requestRepository, RequestMapper.INSTANCE);
		this.requestRepository = requestRepository;
		this.requestMapper = requestMapper;
	}

	@Override
	public RequestDto create(RequestDto dto) throws EntityNotFoundException
	{
		Request request = requestRepository.findByMovieNameAndProductionYear(dto.getMovieName(), dto.getProductionYear());

		if (request == null)
		{
			requestRepository.insert(UUID.randomUUID(), dto.getMovieName(), dto.getProductionYear());
			return requestMapper.entityToDto(requestRepository.findByMovieNameAndProductionYear(dto.getMovieName(), dto.getProductionYear()));
		}
		throw new EntityNotFoundException("Request already exists");
	}
}
