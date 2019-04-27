import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms';
import {AuthService} from '../../service/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-adminauth',
  templateUrl: './adminauth.component.html',
  styleUrls: ['./adminauth.component.css']
})
export class AdminauthComponent implements OnInit {

  errorStatus: boolean;

  constructor(private authservice: AuthService, private router: Router) { }

  ngOnInit() {
    if (this.authservice.getReaderType() !== null){
      this.router.navigate(['welcome/admin']);
    }
  }

  login(adminFrm: NgForm){
    this.authservice.loginAdminService(adminFrm).subscribe(
      (resp) => {
        console.log(resp)
        this.errorStatus = resp['status'];
        if(resp['status']) {
          console.log(resp)
          const readerId = resp['responseObject'].readerId;
          const readerType = resp['responseObject'].readerType;
          sessionStorage.setItem('readerId', readerId);
          sessionStorage.setItem('rtype', readerType);
          this.router.navigate(['welcome/admin']);
        }
      }
    );
  }

}
