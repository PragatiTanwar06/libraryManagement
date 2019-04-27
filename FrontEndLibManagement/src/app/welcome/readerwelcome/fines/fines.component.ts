import { Component, OnInit } from '@angular/core';
import {BorrowService} from '../../../service/borrow.service';
import {AuthService} from '../../../service/auth.service';
import {ReaderService} from '../../../service/reader.service';

@Component({
  selector: 'app-fines',
  templateUrl: './fines.component.html',
  styleUrls: ['./fines.component.css']
})
export class FinesComponent implements OnInit {

  constructor(private borrowService: BorrowService, private authService: AuthService) { }

  noFineMsg: boolean;
  finesArr: any = [];
  totalFine: number;

  ngOnInit() {
    this.getFinesLst();

  }


  getFinesLst(){
    this.borrowService.getFinesReaderService(this.authService.getReaderId()).subscribe(
      (resp) => {
        if(resp['message'] === 'noFine'){
          this.noFineMsg = true;
        }else {
          this.noFineMsg = false;
          this.finesArr = resp['responseObject'].documentVO;
          this.totalFine = resp['responseObject'].totalFinesToPay;
        }
      }
    );
  }
}
