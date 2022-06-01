package bilkent.dmgtv.service;

import bilkent.dmgtv.db.MovieFilterYear;
import bilkent.dmgtv.db.ReviewRequest;
import bilkent.dmgtv.dto.MovieDto;
import bilkent.dmgtv.service.base.BaseCrudService;

import java.util.List;

public interface MovieService extends BaseCrudService<MovieDto>
{
    List<MovieDto> filterByYear(MovieFilterYear movieFilterYear);
}
