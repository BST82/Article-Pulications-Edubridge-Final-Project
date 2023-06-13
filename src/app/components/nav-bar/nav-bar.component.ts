import { Component, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent {

  @ViewChild('sidenav')
  sidenav!: MatSidenav;
      constructor(){

      }
     
    
      toggleSideNav(){
        this.sidenav.toggle();
      }

}
