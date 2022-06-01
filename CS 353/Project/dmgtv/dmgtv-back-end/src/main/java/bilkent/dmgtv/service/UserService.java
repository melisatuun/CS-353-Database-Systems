package bilkent.dmgtv.service;

import bilkent.dmgtv.db.LoginRequest;
import bilkent.dmgtv.db.RegisterRequest;
import bilkent.dmgtv.db.UpdateUserRequest;
import bilkent.dmgtv.dto.UserDto;
import bilkent.dmgtv.service.base.BaseCrudService;

public interface UserService extends BaseCrudService<UserDto>
{
    UserDto login(LoginRequest loginRequest);
    UserDto register(RegisterRequest registerRequest);
    UserDto get(String username);
    UserDto update(String username, UpdateUserRequest updateUserRequest);
}
