import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {NgForm} from '@angular/forms';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class DocumentsearchService {

  constructor(private http: HttpClient) { }

  findDocumentService(documentSearchFrm: NgForm){
    return this.http.post('http://localhost:7676/library/api/search/document', documentSearchFrm.value).pipe(
      map(resp => resp)
    );
  }

  findAllDocTitleAndIdService(){
    return this.http.get('http://localhost:7676/library/api/all/documents').pipe(
      map(resp => resp)
    );
  }

  addDocumentCopy(docCopyFrm: NgForm){
    return this.http.post('http://localhost:7676/library/api/add/document',docCopyFrm.value).pipe(
      map(resp => resp)
    );
  }

  searchDocumentForAdmin(docSearchFrm: NgForm){
    return this.http.post('http://localhost:7676/library/api/search/document/admin',docSearchFrm.value).pipe(
      map(resp => resp)
    );
  }

  mostPopularDocumentService(){
    return this.http.get('http://localhost:7676/library/api/document/most/popular').pipe(
      map(resp => resp)
    );
  }
}
