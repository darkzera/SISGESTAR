import { Component, OnInit, ViewChild } from '@angular/core';
import { finalize } from 'rxjs/operators';
import { TarefaModel } from 'src/app/shared-models/tarefa-model';
import { TarefaService } from 'src/app/shared-services/tarefa-service';
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import { UsuarioService } from 'src/app/shared-services/usuario-service';
import { Router } from '@angular/router';
import { TarefaComentariosModalComponent } from '../tarefa-comentario/tarefa-comentarios.component';
import { ComentariosListModel } from 'src/app/shared-models/comentarioslist-model';

@Component({
    selector: 'app-modal-details-tarefa',
    templateUrl: './tarefa-detail.component.html',
    styleUrls: ['tarefa-details.component.scss']
})
export class TarefaDetailsModalComponent implements OnInit {

    tarefaDetalhes: TarefaModel = {};
    cmmtarios: ComentariosListModel[] = [];
    @BlockUI() blockUI: NgBlockUI;

    showDialog = false;
    @ViewChild('comentarios') comentarios!: TarefaComentariosModalComponent;


    constructor(protected tarefaService: TarefaService,
        private usuarioService: UsuarioService,
        private router: Router,
    ) { }

    ngOnInit(): void { }


    loadDetailsByIdTarefa(idTarefa: number) {
        this.blockUI.start();
        this.tarefaService.findById(idTarefa)
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe(tarefaResponse => {
                this.tarefaDetalhes = tarefaResponse
                this.comentarios.comentarios = tarefaResponse['comentarios'];
            });
    }

    showComentarios() {
        this.comentarios.comentarios = this.tarefaDetalhes.comentarios;
        this.showDialog = true;
    }

    closeModal() {
        this.showDialog = false;
    }

}