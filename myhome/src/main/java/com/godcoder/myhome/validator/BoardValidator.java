package com.godcoder.myhome.validator;


import com.godcoder.myhome.model.Board;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component
public class BoardValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Board.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors e) {

        Board b = (Board) obj;
        if(StringUtils.isEmpty(b.getTitle())){
            e.rejectValue("title", "key", "내용을 입력하세요.");
        } else{
            if(b.getTitle().length()<3){
                e.rejectValue("title", "key", "2글자 이상으로 작성해주세요");
            }
        }

        if(StringUtils.isEmpty(b.getContent())){
            e.rejectValue("content", "key", "내용을 입력하세요.");
        }

    }
}
