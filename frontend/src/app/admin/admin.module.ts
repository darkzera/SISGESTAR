import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppTopbarComponent } from '../components/topbar/app.topbar.component';
import { AppFooterComponent } from '../components/footer/app.footer.component';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { SharedModule } from '../shared/shared.module';
import {BreadcrumbModule, MenuModule, PageNotificationModule} from '@nuvem/primeng-components';
import { VersionTagModule } from '@nuvem/angular-base';
import {BlockUIModule} from 'ng-block-ui';
import {HomeComponent} from './home/home.component';
import { TarefaModule } from './tarefa/tarefa.module';
import { TarefaFormComponent } from './tarefa/tarefa-form/tarefa-form.component';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AdminComponent,
    AppTopbarComponent,
    AppFooterComponent,
    HomeComponent,
    // TarefaComponent,
    // TarefaFormComponent,
  ],
    imports: [
        CommonModule,
        AdminRoutingModule,
        SharedModule,
        BreadcrumbModule,
        MenuModule,
        VersionTagModule,
        BlockUIModule,
        PageNotificationModule,
        TarefaModule,
        ReactiveFormsModule,
    ]
})
export class AdminModule { }
