import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {NgForm} from '@angular/forms';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  loginReaderService(readerLoginFrm: NgForm){
     return this.http.post('http://localhost:7676/library/api/login/reader',readerLoginFrm.value).pipe(
      map(resp => resp));
  }

  loginAdminService(adminLoginFrm: NgForm){
    return this.http.post('http://localhost:7676/library/api/login/admin',adminLoginFrm.value).pipe(
      map(resp =>resp));
  }

  getReaderId(): string{
    const id = sessionStorage.getItem('readerId');
    if (id != null){
      return id;
    }
    return null;
  }

  getReaderType(): string {
    const rtype = sessionStorage.getItem('rtype');
    if ( rtype != null){
      return rtype;
    }
    return null;
  }

  getReaderOrAdminByIdService(): any{
    if(this.getReaderId() != null) {
       return this.http.get('http://localhost:7676/library/api/reader/'+this.getReaderId()).pipe(
        map(resp => resp));
    }
  }

  isReader(): boolean{
    const i = this.getReaderType();
    if(this.getReaderType() === 'STUDENT' || this.getReaderType() === 'SENIOR_CITIZEN'){
      return true;
    }
    return false;
  }

  isAdmin():boolean {
    if(this.getReaderType() === 'STAFF'){
      return true;
    }
    return false;
  }

  logout(){
    sessionStorage.clear();
  }

}
