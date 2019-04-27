package com.pragati.libmange.librarymanagement.controller;

import com.pragati.libmange.librarymanagement.dao.ReserveDao;
import com.pragati.libmange.librarymanagement.vo.DocumentVO;
import com.pragati.libmange.librarymanagement.vo.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api")
public class ReserveController {

    @Autowired
    private ReserveDao reserveDao;

    @GetMapping("/reserve/{readerId}/{docId}/{libId}/{availableCopies}")
    public HttpResponse<String> insertReserve(@PathVariable("readerId") String readerId,
                                              @PathVariable("docId") Long docId,
                                              @PathVariable("libId") Long libId,
                                              @PathVariable("availableCopies") Long availableCopies){
        HttpResponse<String> resp = new HttpResponse<>();
        try {
            if(!reserveDao.checkReserveAndBorrowsCount(readerId)){
                resp.setMessage("countError");
                resp.setStatus(false);
                return resp;
            }
            reserveDao.insertReserve(readerId,docId,libId,availableCopies);
            resp.setMessage("reserved");
            resp.setStatus(true);
            return resp;
        }catch (Exception e){
            resp.setMessage("error");
            resp.setStatus(false);
            return resp;
        }
    }

    @GetMapping("/reserve/documents/{readerId}")
    public HttpResponse<List<DocumentVO>> getAllReservedDocuments(@PathVariable("readerId") String readerId){
        HttpResponse<List<DocumentVO>> resp = new HttpResponse<>();
        try{
            List<DocumentVO> dLst = reserveDao.findReservedDocuments(readerId);
            if(dLst.size() == 0){
                resp.setMessage("resEmpty");
                return resp;
            }
            resp.setMessage("resFilled");
            resp.setResponseObject(dLst);
            resp.setStatus(true);
            return resp;
        }catch (Exception e){
            resp.setMessage("resError");
            resp.setStatus(false);
            return resp;
        }
    }
}
