package com.pragati.libmange.librarymanagement.controller;

import com.pragati.libmange.librarymanagement.dao.UserDao;
import com.pragati.libmange.librarymanagement.vo.HttpResponse;
import com.pragati.libmange.librarymanagement.vo.ReaderVO;
import com.pragati.libmange.librarymanagement.vo.enums.ReaderTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@CrossOrigin
public class LoginRestController {

    @Autowired
    private UserDao userDao;

    @PostMapping("/login/reader")
    public HttpResponse<ReaderVO> loginReader(@RequestBody ReaderVO readerVO){
        HttpResponse<ReaderVO> resp = new HttpResponse<>();
        if(readerVO != null && readerVO.getReaderId() != null){
            ReaderVO reader = userDao.findByReaderId(readerVO.getReaderId());
            if(reader != null){
                if(!reader.getReaderType().equals(ReaderTypeEnum.STAFF)) {
                    resp.setResponseObject(reader);
                    resp.setMessage("Reader Found");
                    resp.setStatus(true);
                    return resp;
                }
            }
        }
        resp.setMessage("Error in fetching reader");
        resp.setStatus(false);
        return resp;
    }

    @PostMapping("/login/admin")
    public HttpResponse<ReaderVO> loginAdmin(@RequestBody ReaderVO readerVO){
        HttpResponse<ReaderVO> resp = new HttpResponse<>();
        if(readerVO != null && readerVO.getReaderId() != null && readerVO.getPassword() != null && readerVO.getPassword().length() != 0 && readerVO.getReaderId().length() != 0){
            ReaderVO reader = userDao.findByIdAndPassword(readerVO.getReaderId(),readerVO.getPassword());
            if(reader != null){
                resp.setResponseObject(reader);
                resp.setMessage("Reader Found");
                resp.setStatus(true);
                return resp;
            }
        }
        resp.setMessage("Error in fetching reader");
        resp.setStatus(false);
        return resp;
    }

}
