import { Component, OnInit } from '@angular/core';
import {ReserveService} from '../../../service/reserve.service';
import {BorrowService} from '../../../service/borrow.service';
import {AuthService} from '../../../service/auth.service';

@Component({
  selector: 'app-documentstatus',
  templateUrl: './documentstatus.component.html',
  styleUrls: ['./documentstatus.component.css']
})
export class DocumentstatusComponent implements OnInit {

  constructor(private reserveService: ReserveService, private borrowsService: BorrowService, private authService: AuthService) { }

  reservedDocArr: any = [];
  borrowedDocArr: any = [];
  reserveMsg:boolean;
  borrowedMsg: boolean;
  returnDocMsg:boolean;
  returnBranchMsg;


  ngOnInit() {
    this.getBorrowedDocuments();
    this.getReservedDocuments();
  }

  getReservedDocuments(){
    this.reserveService.getReservedDocService(this.authService.getReaderId()).subscribe(
      (resp) =>{
        if(resp['message'] === 'resEmpty'){
          this.reserveMsg = true;
        }
        this.reservedDocArr = resp['responseObject'];
      }
    );
  }

  getBorrowedDocuments(){
    this.borrowsService.getBorroweddDocService(this.authService.getReaderId()).subscribe(
      (resp) => {
        if(resp['message'] === 'borrowedEmpty'){
          this.borrowedMsg = true;
        }
        console.log(resp)
        this.borrowedDocArr = resp['responseObject'];
      }
    );
  }

  transferDocument(docId,copyNo,libId){
    this.borrowsService.transferDocFromResToBorrowService(this.authService.getReaderId(),docId,copyNo,libId).subscribe(
      (resp) => {
        if(resp['message'] === 'transferSuccess'){
          this.getBorrowedDocuments();
          this.getReservedDocuments();
        }
      }
    );
  }

  returnDocument(borrowNo: number, branchLocation: string){
    this.borrowsService.returnDocument(borrowNo).subscribe(
      (resp) =>{
        if(resp['message'] === 'returnComplete'){
          this.returnDocMsg = true;
          this.returnBranchMsg = 'Thank You for returning document at branch location - ' + branchLocation;
          this.getBorrowedDocuments();
          this.getReservedDocuments();
        }
      }
    );
  }

}
