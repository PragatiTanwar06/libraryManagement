import { Component, OnInit } from '@angular/core';
import {BorrowService} from '../../../service/borrow.service';
import {AuthService} from '../../../service/auth.service';
import {ReaderService} from '../../../service/reader.service';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-avg-fine',
  templateUrl: './avg-fine.component.html',
  styleUrls: ['./avg-fine.component.css']
})
export class AvgFineComponent implements OnInit {

  constructor(private borrowService: BorrowService, private authService: AuthService, private readerService: ReaderService) { }

  noFineMsg: boolean;
  finesArr: any = [];
  avgFinePaid: number;
  showHeaderCard: boolean;

  allReaderIdArr: any = [];

  ngOnInit() {
    this.getAllReaderId();
    console.log(this.noFineMsg)
  }

  getAllReaderId(){
    this.readerService.getAllReaderId().subscribe(
      (resp) => {
        this.allReaderIdArr = resp;
        console.log(this.allReaderIdArr);
      }
    );
  }

  calculateAvgPaidFine(calculateFrm: NgForm){
    console.log(calculateFrm.value.readerId);
    this.borrowService.getFinesReaderService(calculateFrm.value.readerId).subscribe(
      (resp) => {
        if(resp['message'] === 'noFine'){
          this.noFineMsg = true;
          this.showHeaderCard = false;
        }else {
          this.showHeaderCard = true;
          this.noFineMsg = false;
          this.finesArr = resp['responseObject'].documentVO;
          this.avgFinePaid = resp['responseObject'].avgFineAlreadyPaid;
        }
      }
    );
  }

}
