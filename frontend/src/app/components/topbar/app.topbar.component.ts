import { Component } from '@angular/core';
import {AdminComponent} from '../../admin/admin.component';
import {Router} from '@angular/router';

@Component({
    selector: 'app-topbar',
    templateUrl: './app.topbar.component.html'
})
export class AppTopbarComponent {

    constructor(
        public app: AdminComponent,
        private router: Router

    ) {
    }

    sair() {
        localStorage.clear();
        this.router.navigate(['../login']);
    }
}
