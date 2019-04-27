import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {NgForm} from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class BranchService {

  constructor(private http: HttpClient) { }

  getBranchIdAndLocation(){
    return this.http.get('http://localhost:7676/library/api/all/branch').pipe(
      map(resp => resp)
    );
  }

  mostBorrowedService(searchFrm: NgForm){
    return this.http.post('http://localhost:7676/library/api/branch/most/borrowed',searchFrm.value).pipe(
      map(resp => resp)
    );
  }
}
