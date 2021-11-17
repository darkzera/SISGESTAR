import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ConfirmationService, SelectItem} from 'primeng/api';
import {ActivatedRoute, Router} from '@angular/router';
import {PageNotificationService} from '@nuvem/primeng-components';
import {UsuarioService} from '../../../shared-services/usuario-service';
import {finalize} from 'rxjs/operators';
import {BaseEntityForm} from '../../../utils/base-entity-form';
import {UsuarioModel} from '../../../shared-models/usuario-model';
import {ModalService} from '../../../utils/modal.service';
import { TarefaService } from 'src/app/shared-services/tarefa-service';
import { TarefaMessages } from '../tarefa-messages';
import { TarefaModel } from 'src/app/shared-models/tarefa-model';

@Component({
    selector: 'app-tarefa-form',
    templateUrl: './tarefa-form.component.html',
    styleUrls: ['tarefa-form.component.scss']
})
export class TarefaFormComponent extends BaseEntityForm<TarefaModel> implements OnInit {
    MSG = TarefaMessages;
    form: FormGroup;
    @BlockUI() blockUI: NgBlockUI;
    title = 'Nova tarefa';
    SERVICE = this.tarefaService;
    candidatosResponsavel: SelectItem[] = [];
    @Output() saveTarefa = new EventEmitter<void>();

    constructor(
        private tarefaService: TarefaService,
        private usuarioService: UsuarioService,
        protected confirmationService: ConfirmationService,
        protected router: Router,
        protected pageNotificationService: PageNotificationService,
        protected formBuilder: FormBuilder,
        protected route: ActivatedRoute,
        protected modalService: ModalService
    ) {
        super(confirmationService, router, pageNotificationService, formBuilder, route, modalService);
    }

    ngOnInit() {
        this.form = this.buildReactiveForm();
        this.selectResponsavel();
    }

    buildReactiveForm() {
        return this.formBuilder.group({
            id: [null, [Validators.required, Validators.maxLength(255)]],
            titulo: [null, [Validators.required, Validators.maxLength(255)]],
            descricao: [null, [Validators.required, Validators.maxLength(500)]],
            idResponsavel: [null, [Validators.required], Validators.maxLength(500)],
        }, {updateOn: 'change'});
    }

    clearForm() {
        this.form.reset();
        this.title = 'Nova Tarefa';
    }

    salvarTarefa(tarefa: TarefaModel) {
        this.blockUI.start();
        this.tarefaService.insert(tarefa)
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe(() => {
                this.pageNotificationService.addSuccessMessage(TarefaMessages.SAVE_SUCESS);
                this.navegarParaDashboard();
            });
    }

    selectResponsavel(){
        this.usuarioService.findAllDropDown().subscribe(
            res => this.candidatosResponsavel = res
        )
    }

    editarUsuario(tarefa: TarefaModel) {
        this.blockUI.start();
        this.tarefaService.update(tarefa)
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe(() => {
                this.pageNotificationService.addSuccessMessage(TarefaMessages.SAVE_SUCESS);
                this.navegarParaDashboard();
            });
    }

    navegarParaDashboard() {
        this.saveTarefa.emit();
    }

    onLoadEntity(entity: TarefaModel) {
        this.form.patchValue(entity);
    }

    sendForm(entity: TarefaModel) {
        entity.id ? this.editarUsuario(entity) : this.salvarTarefa(entity);
    }
}