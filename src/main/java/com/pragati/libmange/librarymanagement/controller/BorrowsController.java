package com.pragati.libmange.librarymanagement.controller;

import com.pragati.libmange.librarymanagement.util.DaoServiceUtil;
import com.pragati.libmange.librarymanagement.vo.DocumentVO;
import com.pragati.libmange.librarymanagement.vo.FinesVO;
import com.pragati.libmange.librarymanagement.vo.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api")
public class BorrowsController {

    @Autowired
    private DaoServiceUtil daoServiceUtil;

    @GetMapping("/borrows/{readerId}/{docId}/{libId}/{availableCopies}")
    public HttpResponse<String> insertIntoBorrows(@PathVariable("readerId") String readerId,
                                                  @PathVariable("docId") Long docId,
                                                  @PathVariable("libId") Long libId,
                                                  @PathVariable("availableCopies") Long availableCopies){
       System.out.println("in borrows");
        HttpResponse<String> resp = new HttpResponse<>();
        try{
            if(!daoServiceUtil.reserveDao.checkReserveAndBorrowsCount(readerId)){
                resp.setMessage("countError");
                resp.setStatus(false);
                return resp;
            }
            daoServiceUtil.borrowsDao.insertIntoBorrows(readerId,docId,libId,availableCopies, null);
            resp.setStatus(true);
            resp.setMessage("successBorrow");
            return resp;

        }catch (Exception e){
            resp.setMessage("errorBorrow");
            resp.setStatus(false);
            return resp;
        }
    }

    @GetMapping("/borrows/documents/{readerId}")
    public HttpResponse<List<DocumentVO>> getAllReservedDocuments(@PathVariable("readerId") String readerId){
        HttpResponse<List<DocumentVO>> resp = new HttpResponse<>();
        try{
            List<DocumentVO> dLst = daoServiceUtil.borrowsDao.findBorrowedDocuments(readerId);
            if(dLst.size() == 0){
                resp.setMessage("borrowedEmpty");
                return resp;
            }
            resp.setMessage("borrowedFilled");
            resp.setResponseObject(dLst);
            resp.setStatus(true);
            return resp;
        }catch (Exception e){
            resp.setMessage("borrowedError");
            resp.setStatus(false);
            return resp;
        }
    }

    @GetMapping("/transfer/{readerId}/{docId}/{libId}/{copyNo}")
    public HttpResponse<String> transferDocFromResToBorrow(@PathVariable("readerId") String readerId,
                                                           @PathVariable("docId") Long docId,
                                                           @PathVariable("libId") Long libId,
                                                           @PathVariable("copyNo") Long copyNo){
        HttpResponse<String> resp = new HttpResponse<>();
        try{
            daoServiceUtil.borrowsDao.transferDocFromResToBorrow(readerId,docId,copyNo,libId);
            resp.setMessage("transferSuccess");
            return resp;
        }catch (Exception e){
            resp.setMessage("tranferError");
            return resp;
        }
    }

    @GetMapping("/return/{borrowNumber}")
    public HttpResponse<String> returnDocument(@PathVariable("borrowNumber") Long borrowNumber){
        HttpResponse<String> resp = new HttpResponse<>();
        try{
            daoServiceUtil.borrowsDao.returnDocument(borrowNumber);
            resp.setMessage("returnComplete");
            return resp;
        }catch (Exception e){
            resp.setMessage("returnError");
            resp.setStatus(false);
            return resp;
        }
    }

    @GetMapping("/fines/{readerId}")
    public HttpResponse<FinesVO> getFines(@PathVariable("readerId") String readerId){
        HttpResponse<FinesVO> resp = new HttpResponse<>();
        FinesVO finesVO = daoServiceUtil.readerFineService.calculateFines(readerId);
        if(finesVO == null){
            resp.setMessage("noFine");
            return resp;
        }
        resp.setResponseObject(finesVO);
        return resp;
    }

}
