import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {NgForm} from '@angular/forms';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ReaderService {

  constructor(private http: HttpClient) { }

  addReader(readerFrm: NgForm){
    return this.http.post('http://localhost:7676/library/api/reader/save', readerFrm.value).pipe(
      map(resp => resp)
    );
  }

  frequentBorrowedService(searchFrm: NgForm){
    return this.http.post('http://localhost:7676/library/api/reader/frequent/book',searchFrm.value).pipe(
      map(resp => resp)
    );
  }

  getAllReaderId(){
    return this.http.get('http://localhost:7676/library/api/reader/id').pipe(
      map(resp => resp)
    );
  }
}


