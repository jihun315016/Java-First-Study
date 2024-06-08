package com.codingrecipe.board.Controller;

import com.codingrecipe.board.dto.BoardDTO;
import com.codingrecipe.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
@RequiredArgsConstructor // final이 붙은 필드를 대상으로 생성자를 만들어준다.
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/save")
    public String save() {
        return "save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) { // @ModelAttribute 생략 가능
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "index";
    }

    // Model : Spring 객체에서 제공하는 인터페이스
    // 데이터를 화면으로 가져갈 수 있도록 전달해주는 객체
    // 구현 방식은 여러 가지가 있고 Restful API에서는 안 씀
    // Restful API를 사용하면 리턴 타입이 매번 바뀌는데 Model을 쓰면 일관된 리턴 타입(String)을 유지할 수 있다.
    // 스프링은 조직마다 사용하는 방법이 너무 달라서 정답은 없음
    @GetMapping("/list")
    public String findAll(Model model)
    {
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        System.out.println("boardDTOList = " + boardDTOList);
        return "list";
    }
}