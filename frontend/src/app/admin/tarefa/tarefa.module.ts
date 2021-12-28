import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {TarefaRoutingModule} from './tarefa-routing.module';
import {SharedModule} from '../../shared/shared.module';
import { TarefaListComponent } from "./tarefa-list/tarefa-list.component";
import { TarefaFormComponent } from './tarefa-form/tarefa-form.component';
import { TarefaDetailsModalComponent } from "./tarefa-list/tarefa-details/tarefa-details.component";
import { TarefaComentariosModalComponent } from './tarefa-list/tarefa-comentario/tarefa-comentarios.component';

import {FieldsetModule} from 'primeng/fieldset';
import {TabViewModule} from 'primeng/tabview';
@NgModule({
    declarations: [
        TarefaListComponent,
        TarefaFormComponent,
        TarefaDetailsModalComponent,
        TarefaComentariosModalComponent,
    ],
    imports: [
        CommonModule,
        SharedModule,
        ReactiveFormsModule,
        TarefaRoutingModule
    ], 
    exports: [
        TarefaDetailsModalComponent,
    ],
})
export class TarefaModule { }
