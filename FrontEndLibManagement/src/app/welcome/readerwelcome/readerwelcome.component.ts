import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms';
import {DocumentsearchService} from '../../service/documentsearch.service';
import {ReserveService} from '../../service/reserve.service';
import {AuthService} from '../../service/auth.service';
import {formatDate} from '@angular/common';
import {BorrowService} from '../../service/borrow.service';

@Component({
  selector: 'app-readerwelcome',
  templateUrl: './readerwelcome.component.html',
  styleUrls: ['./readerwelcome.component.css']
})
export class ReaderwelcomeComponent implements OnInit {

  documentSearchArr = [];
  errorMsg: boolean;
  showTable: boolean;
  reserveMsg: boolean;
  reserveCountError: boolean;
  docFrm: NgForm;
  borrowsMsg: boolean;
  borrowsError: boolean;


  constructor(private documentSearchService: DocumentsearchService, private reserveServe: ReserveService,private authServe: AuthService, private borrosService: BorrowService) { }

  ngOnInit() {
  }

  documentSearch(documentFrm: NgForm){
    this.documentSearchService.findDocumentService(documentFrm).subscribe(
      (resp) =>{
        console.log(resp);
        this.docFrm = documentFrm;
        this.showTable = true;
        this.documentSearchArr = resp['responseObject'];
        this.errorMsg = resp['message'];
      }
    );
  }

  reserve(docId: number, libId: number,availableCopies: number){
    this.borrowsMsg = false;
    console.log(docId,'---'+libId)
    this.reserveServe.insertReserveService(this.authServe.getReaderId(),docId,libId,availableCopies).subscribe(
      (resp) => {
        if(resp['message'] === 'countError') {
          this.reserveCountError = true;
        }
        if(resp['status']){
          this.documentSearchService.findDocumentService(this.docFrm).subscribe(
            (r) =>{
              this.showTable = true;
              this.documentSearchArr = r['responseObject'];
              this.errorMsg = r['message'];
              this.reserveMsg = true;
            }
          );
        } else {
          this.reserveMsg = false;
        }
      }
    );
  }

  borrow(docId: number,libId: number,availableCopies: number){
    this.reserveMsg = false;
    this.borrosService.saveIntoBorroesService(this.authServe.getReaderId(),docId,libId,availableCopies).subscribe(
      (resp) => {
        if(resp['message'] === 'countError') {
          this.reserveCountError = true;
        }
        if(resp['status']){
          this.documentSearchService.findDocumentService(this.docFrm).subscribe(
            (r) =>{
              this.showTable = true;
              this.documentSearchArr = r['responseObject'];
              this.errorMsg = r['message'];
              this.borrowsMsg = true;
            }
          );
        } else {
          this.borrowsMsg = false;
        }
      }
    );
  }

}
