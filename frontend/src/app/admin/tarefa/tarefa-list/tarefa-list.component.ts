import { Component, OnInit, ViewChild } from '@angular/core';
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import { ConfirmationService } from 'primeng/api';
import { ActivatedRoute, Router } from '@angular/router';
import { PageNotificationService } from '@nuvem/primeng-components';
import { finalize } from 'rxjs/operators';
import { TarefaModel } from 'src/app/shared-models/tarefa-model';
import { GenericTableColumn } from 'src/app/shared/models/generic-table-column';
import { GenericTableComponent } from 'src/app/components/generic-table/generic-table.component';
import { GenericTableButton } from 'src/app/shared/models/generic-table-button';

import { TarefaUtil } from "../tarefa-util";
import { TarefaService } from 'src/app/shared-services/tarefa-service';

import { ModalService } from 'src/app/utils/modal.service';
import { TarefaDetailsModalComponent } from './tarefa-details/tarefa-details.component';
import { TarefaFormComponent } from '../tarefa-form/tarefa-form.component';
import { TarefaMessages } from '../tarefa-messages';

@Component({
    selector: 'app-home',
    templateUrl: './tarefa-list.component.html',
    styleUrls: ['tarefa-list.component.scss']
})
export class TarefaListComponent implements OnInit {
 
    showDialog = false;
    @ViewChild('detail') tarefaDetails!: TarefaDetailsModalComponent;
    @BlockUI() blockUI: NgBlockUI;
    SERVICE = this.tarefaService;
    tarefas: TarefaModel[] = [];

    COLUMNS: GenericTableColumn[] = TarefaUtil.COLUMNS;
    @ViewChild(GenericTableComponent) tarefa: GenericTableComponent;
    BUTTONS: GenericTableButton<TarefaModel>[] = [
        {
            icon: 'edit',
            description: 'editar',
            action: row => this.editar(row.id)
        },
        {
            icon: 'delete',
            description: 'deletar',
            action: row => this.confirmacaoDeletar(row.id)
        },
        {
            icon: 'more',
            description: '+',
            action: row => this.fetchDetails(row.id)
        },
    ];

    constructor(
        protected confirmationService: ConfirmationService,
        protected pageNotificationService: PageNotificationService,
        protected route: ActivatedRoute,
        protected router: Router,
        private tarefaService: TarefaService,
        private modalService: ModalService,
    ) {
    }

    ngOnInit() {
        this.loadTable();
    }

    loadTable() {
        this.blockUI.start();
        this.tarefaService.findAllWithPages()
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe(tarefas => this.tarefa.result = tarefas);
    }

    fetchDetails(id: number): void {
        this.showDialog = true;
        this.tarefaDetails.loadDetailsByIdTarefa(id);
    }

    closeModal() {
        this.showDialog = false;
        this.loadTable();
    }  

    confirmacaoDeletar(id: number) {
        this.confirmationService.confirm({
            header: 'Deseja deletar?',
            message: 'Essa alteração não poderar ser desfeita!',
            accept: () => this.delete(id),
            acceptLabel: 'Sim',
            rejectLabel: 'Não'
        });
    }

    delete(id: number): void {
        this.blockUI.start();
        this.tarefaService.delete(id)
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe(() => this.loadTable());
    }

    editar(id: number) {
        // this.blockUI.start();
        // this.tarefaService.update(tarefa)
        //     .pipe(finalize(() => this.blockUI.stop()))
        //     .subscribe(() => {
        //         this.pageNotificationService.addSuccessMessage(TarefaMessages.SAVE_SUCESS);
        //     });
    }
}



