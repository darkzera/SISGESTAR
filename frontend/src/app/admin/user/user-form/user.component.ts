import {Component, OnInit} from '@angular/core';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ConfirmationService} from 'primeng/api';
import {ActivatedRoute, Router} from '@angular/router';
import {PageNotificationService} from '@nuvem/primeng-components';
import {UserMesages} from '../user-mesages';
import {UsuarioService} from '../../../shared-services/usuario-service';
import {finalize} from 'rxjs/operators';

@Component({
    selector: 'app-home',
    templateUrl: './user.component.html',
    styleUrls: ['user.component.scss']
})
export class UserComponent implements OnInit {
    MSG = UserMesages;
    form: FormGroup;
    isSubmited = false;
    @BlockUI() blockUI: NgBlockUI;
    title = 'Novo Usuario';

    constructor(
        private usuarioService: UsuarioService,
        protected confirmationService: ConfirmationService,
        protected router: Router,
        protected pageNotificationService: PageNotificationService,
        protected formBuilder: FormBuilder,
        protected route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.form = this.buildReactiveForm();
        this.route.queryParams.subscribe(param => {
            if (param.id) {
                this.title = 'Editar Usuario';
                this.obterPorId(param.id);
            }
        });
    }

    obterPorId(id: number) {
        this.blockUI.start();
        this.usuarioService.findById(id)
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe(usuario => this.form.patchValue(usuario));
    }

    buildReactiveForm() {
        return this.formBuilder.group({
            id: [null],
            hash: [null],
            nome: [null, [Validators.required, Validators.maxLength(255)]],
            email: [null, [Validators.required, Validators.maxLength(255)]],
        }, {updateOn: 'change'});
    }

    clearForm() {
        this.form.reset();
        this.title = 'Novo Usuario';
    }

    saveForm() {
        this.validarForm();
        if (this.form.invalid) {
            return;
        }

        this.form.controls.id ? this.editarUsuario() : this.salvarUsuario();
    }

    salvarUsuario() {
        this.blockUI.start();
        this.usuarioService.insert(this.form.value)
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe(() => {
                this.pageNotificationService.addSuccessMessage(UserMesages.USUARIO_SAVE_SUCESS);
                this.navegarParaDashboard();
            });
    }

    editarUsuario() {
        this.blockUI.start();
        this.usuarioService.insert(this.form.value)
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe(() => {
                this.pageNotificationService.addSuccessMessage(UserMesages.USUARIO_SAVE_SUCESS);
                this.navegarParaDashboard();
            });
    }

    navegarParaDashboard() {
        this.router.navigate(['../']);
    }

    validarForm() {
        this.isSubmited = true;
        for (const controlsKey in this.form.controls) {
            this.form.controls[controlsKey].markAsTouched();
        }
    }

    getValidationMessages(controlName: string): string {
        const control = this.form.controls[controlName];
        if ((control.dirty || control.touched) && control.errors) {
            return 'Campo obrigatório';
        }
        return '';
    }
}
