import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../service/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(public authService: AuthService, private route: Router) { }

  name;
  readerType;

  ngOnInit() {
    this.getLoggedInUserName();
    this.getReaderType();
  }

  getLoggedInUserName(){
    this.authService.getReaderOrAdminByIdService().subscribe(
      (resp) => {
        if (resp['status']){
          this.name = resp['responseObject'].name;
        }
      }
    );
  }

  getReaderType(){
    const r = this.authService.getReaderType();
    if(r === 'STAFF'){
      this.readerType = 'STAFF';
    } else {
      this.readerType = 'OTHER';
    }
  }

  logoutReader(){
    this.authService.logout();
    this.route.navigate(['reader_login']);
  }

}
