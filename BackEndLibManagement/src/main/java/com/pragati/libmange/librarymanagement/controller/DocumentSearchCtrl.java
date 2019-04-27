package com.pragati.libmange.librarymanagement.controller;

import com.pragati.libmange.librarymanagement.dao.DocumentDao;
import com.pragati.libmange.librarymanagement.util.DaoServiceUtil;
import com.pragati.libmange.librarymanagement.vo.DocumentVO;
import com.pragati.libmange.librarymanagement.vo.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin
public class DocumentSearchCtrl {

    @Autowired
    private DocumentDao documentDao;
    @Autowired
    private DaoServiceUtil daoServiceUtil;

    @PostMapping("/search/document")
    public HttpResponse<List<DocumentVO>> searchDocument(@RequestBody DocumentVO documentVO){
        HttpResponse<List<DocumentVO>> resp = new HttpResponse<>();
        if(documentVO!=null){
            List<DocumentVO> d = documentDao.findDocumentByIdOrTitleOrPubName(documentVO);
            if(d == null){
                resp.setMessage("");
                return resp;
            }
            if(d!=null){
                if(d.size() == 0){
                    resp.setMessage("noresult");
                    return resp;
                }
                resp.setResponseObject(d);
                resp.setStatus(true);
                return resp;
            }
        }
        resp.setMessage("Error in fetching document");
        resp.setStatus(false);
        return resp;
    }

    @GetMapping("/all/documents")
    public List<DocumentVO> getAllDocumentTitleAndName(){
        return documentDao.findAllDocumentTitleAndId();
    }

    @PostMapping("/add/document")
    public HttpResponse<String> addDocCopy(@RequestBody DocumentVO documentVO){
        HttpResponse<String> resp = new HttpResponse<>();
        if(documentVO != null){
            try{
                documentDao.insertDocumentCopy(documentVO);
                resp.setStatus(true);
                resp.setMessage("added");
                return resp;
            } catch (Exception ex){
                resp.setStatus(false);
                resp.setMessage("error");
                return resp;
            }
        }
        return resp;
    }

    @PostMapping("/search/document/admin")
    public HttpResponse<List<DocumentVO>> searchDocumentForAdmin(@RequestBody DocumentVO documentVO){
        HttpResponse<List<DocumentVO>> resp = new HttpResponse<>();
        if(documentVO.getDocumentId() != 0 || documentVO != null){
            List<DocumentVO> doc = daoServiceUtil.adminDocumentSearchService.searchDocument(documentVO.getDocumentId());
            if(doc.isEmpty()){
                resp.setMessage("emptyLst");
                return resp;
            }
            resp.setResponseObject(doc);
            return resp;
        }
        return null;
    }

    @GetMapping("/document/most/popular")
    public HttpResponse<List<DocumentVO>> findMostBorrowedBook(){
        HttpResponse<List<DocumentVO>> resp = new HttpResponse<>();
            List<DocumentVO> mostPopularBook = daoServiceUtil.documentDao.findMostPopularBook();
            if(mostPopularBook.isEmpty()){
                resp.setMessage("emptyLst");
                return resp;
            }
            resp.setResponseObject(mostPopularBook);
            return resp;
        }

}
