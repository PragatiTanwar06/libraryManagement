package com.pragati.libmange.librarymanagement.controller;

import com.pragati.libmange.librarymanagement.dao.UserDao;
import com.pragati.libmange.librarymanagement.util.DaoServiceUtil;
import com.pragati.libmange.librarymanagement.vo.DocumentVO;
import com.pragati.libmange.librarymanagement.vo.ReaderVO;
import com.pragati.libmange.librarymanagement.vo.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api")
public class ReaderController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/reader/{id}")
    public HttpResponse<ReaderVO> getReaderById(@PathVariable("id") String id){
        System.out.println("in reader");
        HttpResponse<ReaderVO> resp = new HttpResponse<>();
        if(id.length() != 0){
            ReaderVO readerVO = userDao.findByReaderId(id);
            resp.setResponseObject(readerVO);
            resp.setStatus(true);
            return resp;
        }
        resp.setMessage("error");
        resp.setStatus(false);
        return resp;
    }

    @PostMapping("/reader/save")
    public HttpResponse<String> saveReader(@RequestBody ReaderVO readerVO){
        HttpResponse<String> resp = new HttpResponse<>();
        try{
                userDao.addReader(readerVO);
                resp.setMessage("success");
                resp.setStatus(true);
                return resp;
        }catch (Exception e){
            resp.setMessage("error");
            resp.setStatus(false);
            return resp;
        }
    }

    @PostMapping("/reader/frequent/book")
    public HttpResponse<List<ReaderVO>> mostFrequentBook(@RequestBody DocumentVO documentVO){
        HttpResponse<List<ReaderVO>> resp = new HttpResponse<>();
        if(documentVO !=null && documentVO.getLibId() != 0){
            List<ReaderVO> frequentBorrowers = userDao.findFrequentBorrowers(documentVO.getLibId());
            if(frequentBorrowers.isEmpty()){
                resp.setMessage("emptyLst");
                return resp;
            }
            resp.setResponseObject(frequentBorrowers);
            return resp;
        }
       resp.setMessage("error");
        return resp;
    }

    @GetMapping("/reader/id")
    public List<ReaderVO> allReaderId(){
        return userDao.getAllReaderID();
    }
}
