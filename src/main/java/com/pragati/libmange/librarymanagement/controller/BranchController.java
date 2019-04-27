package com.pragati.libmange.librarymanagement.controller;

import com.pragati.libmange.librarymanagement.util.DaoServiceUtil;
import com.pragati.libmange.librarymanagement.vo.DocumentVO;
import com.pragati.libmange.librarymanagement.vo.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api")
public class BranchController {

    @Autowired
    private DaoServiceUtil daoServiceUtil;

    @GetMapping("/all/branch")
    public List<DocumentVO> getAllBranchInfo(){
        return daoServiceUtil.branchDao.findBranchAll();
    }

    @PostMapping("/branch/most/borrowed")
    public HttpResponse<List<DocumentVO>> findMostBorrowedBook(@RequestBody DocumentVO documentVO){
        HttpResponse<List<DocumentVO>> resp = new HttpResponse<>();
        if(documentVO != null && documentVO.getLibId() != 0){
            List<DocumentVO> mostBorrowedBook = daoServiceUtil.branchDao.findMostBorrowedBook(documentVO.getLibId());
            if(mostBorrowedBook.isEmpty()){
                resp.setMessage("emptyLst");
                return resp;
            }
            resp.setResponseObject(mostBorrowedBook);
            return resp;
        }
        resp.setMessage("error");
        return resp;
    }

}
