package com.vaild.test.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    /**
     * @valid  유효성체크에 통과하지 못하면  MethodArgumentNotValidException 이 발생한다.
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodValidException(MethodArgumentNotValidException e){

        ErrorResponse errorResponse = makeErrorResponse(e.getBindingResult());

        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private ErrorResponse makeErrorResponse(BindingResult bindingResult){

        String code = "";
        String description = "";
        String detail = "";

        //에러가 있다면
        if(bindingResult.hasErrors()){
            //DTO에 설정한 meaasge값을 가져온다
            detail = bindingResult.getFieldError().getDefaultMessage();

            //DTO에 유효성체크를 걸어놓은 어노테이션명을 가져온다.
            String bindResultCode = bindingResult.getFieldError().getCode();

            // 반복문을 통해서 배열 or List 형식으로 데이터를 출력하면 문제없이 사용 할 수 있을꺼 같다
            System.out.println("----------------");
            System.out.println(bindingResult.getAllErrors().get(0));
            System.out.println(bindingResult.getAllErrors().get(1));
            System.out.println("------------------");

            switch (bindResultCode){

                case "NotNull":
                    code = ErrorCode.NOT_NULL.getCode();
                    description = ErrorCode.NOT_NULL.getMessage();
                    break;
                case "Min":
                    code = ErrorCode.MIN_VALUE.getCode();
                    description = ErrorCode.MIN_VALUE.getMessage();
                    break;
            }
        }

        return new ErrorResponse(code, description, detail);
    }
}
