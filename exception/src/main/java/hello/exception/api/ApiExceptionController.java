package hello.exception.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hello.exception.exception.BadRequestException;
import hello.exception.exception.UserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ApiExceptionController {

	@GetMapping("/api/members/{id}")
	public MemberDto getMember(@PathVariable("id") String id) {
		if(id.equals("ex")) {
			throw new RuntimeException("잘못된 사용자");
		}
		if(id.equals("bad")) {
			throw new IllegalArgumentException("잘못된 입력 값");
		}
		if(id.equals("user-ex")) {
			throw new UserException("사용자 오류");
		}
		
		return new MemberDto(id, "hello " + id);
	}
	
	@GetMapping("/api/response-status-ex1")
	public String responseStatusEx1() {
		throw new BadRequestException();
	}
	
	@GetMapping("/api/response-status-ex2")
	public String responseStatusEx2() {
		//ResponseStatusException을 사용하면 동적으로 상태코드와 메시지를 변경할 수 있다.
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error.bad", new IllegalArgumentException());
	}
	@GetMapping("/api/default-handler-ex2")
	public String defaultException(@RequestParam Integer data) {
		// DefaultHandlerExceptionResolver가 내부에서 여러 오류를 처리해준다.
		// 예로 파라미터의 타입 오류 발생시 typemismatchException이 발생하는데
		// 이때 내부에서 sendError를 통해 상태코드를 500이 아닌 400으로 변경해준다.
		return "ok";
	}
	
	@Data
	@AllArgsConstructor
	static class MemberDto{
		private String memberId;
		private String name;
	}
}
