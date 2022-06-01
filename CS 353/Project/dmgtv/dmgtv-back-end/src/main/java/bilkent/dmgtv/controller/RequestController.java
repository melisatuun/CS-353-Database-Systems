package bilkent.dmgtv.controller;

import bilkent.dmgtv.controller.base.BaseController;
import bilkent.dmgtv.dto.RequestDto;
import bilkent.dmgtv.service.RequestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("request")
public class RequestController extends BaseController<RequestDto>
{
	private final RequestService requestService;

	public RequestController(RequestService requestService)
	{
		super(requestService);
		this.requestService = requestService;
	}
}
