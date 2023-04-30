package poly.store.baseResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponese {
	private String code;
	private String message;
	private Object data;
	
	public BaseResponese(String code, String message) {
		this.code = code;
		this.message = message;
	}
}