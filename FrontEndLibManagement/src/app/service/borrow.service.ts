import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class BorrowService {

  constructor(private http: HttpClient) { }

  saveIntoBorroesService(readerId, docId: number, libId: number, availableCopies: number){
    return this.http.get('http://localhost:7676/library/api/borrows/'+readerId+'/'+docId+'/'+libId+'/'+availableCopies).pipe(
      map(resp => resp)
    );
  }

  getBorroweddDocService(readerId: string){
    return this.http.get('http://localhost:7676/library/api/borrows/documents/'+readerId).pipe(
      map(resp => resp)
    );
  }

  transferDocFromResToBorrowService(readerId,docId,copyNo,libId){
    return this.http.get('http://localhost:7676/library/api/transfer/'+readerId+'/'+docId+'/'+libId+'/'+copyNo).pipe(
      map(resp => resp)
    );
  }

  returnDocument(borrowNumber: number){
    return this.http.get('http://localhost:7676/library/api/return/'+borrowNumber).pipe(
      map(resp => resp)
    );
  }

  getFinesReaderService(readerId: string){
    return this.http.get('http://localhost:7676/library/api/fines/'+readerId).pipe(
      map(resp => resp)
    );
  }
}
