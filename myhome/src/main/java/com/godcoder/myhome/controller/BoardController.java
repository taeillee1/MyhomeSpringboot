package com.godcoder.myhome.controller;

import com.godcoder.myhome.model.Board;
import com.godcoder.myhome.repository.BoardRepository;
import com.godcoder.myhome.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model){
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards",boards);
        return "board/list";
    }

    @GetMapping("/form") //리스트페이지에서 쓰기버튼을 눌렀을 때 불려옴
    public String form(Model model, @RequestParam(required = false) Long id){

        if(id==null){
            model.addAttribute("board",new Board());//새로운 데이터 베이스 칸을 만들어 전송하고
        }
        else{
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board",board);
        }

        return "board/form";
    }

    @PostMapping("/form")//글을 쓰고 확인버튼을 눌렀을때 발동
    public String formSubmit(@Valid Board board, BindingResult bindingResult){//@ModelAttribute 이것은 사용자가 요청시 전달하는 값을 오브젝트 형태로 매핑해주는것을 의미한다

        boardValidator.validate(board, bindingResult);
        if(bindingResult.hasErrors()){
            return "board/form";
        }
        else{
            boardRepository.save(board); //Get매핑으로 board라는 키값으로 저장된 값을 데이터베이스 테이블에 저장하는것
            return "redirect:/board/list";
        }

    }
}
