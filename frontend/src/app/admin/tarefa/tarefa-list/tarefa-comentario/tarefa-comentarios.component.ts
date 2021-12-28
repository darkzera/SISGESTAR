import { Component, OnInit, ViewChild } from '@angular/core';
import { ComentariosListModel } from 'src/app/shared-models/comentarioslist-model';
import {FieldsetModule} from 'primeng/fieldset';
import { UsuarioService } from 'src/app/shared-services/usuario-service';

@Component({
    selector: 'app-modal-comentarios-tarefa',
    templateUrl: './tarefa-comentarios.component.html',
    styleUrls: ['tarefa-comentarios.component.scss']
})
export class TarefaComentariosModalComponent implements OnInit {

    comentarios: ComentariosListModel[] = []; 

    constructor(usuarioService: UsuarioService) {}

    ngOnInit(): void { 

    }





}