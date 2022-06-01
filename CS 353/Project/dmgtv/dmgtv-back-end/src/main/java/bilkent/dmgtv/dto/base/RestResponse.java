package bilkent.dmgtv.dto.base;

public class RestResponse<T>
{
	private T data;
	private String title;
	private String message;

	public RestResponse(){}

	public RestResponse(T data){
		this.data = data;
	}

	public RestResponse(String title, String message) {
		this.title = title;
		this.message = message;
	}

	public RestResponse(T data, String title, String message) {
		this.data = data;
		this.title = title;
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
