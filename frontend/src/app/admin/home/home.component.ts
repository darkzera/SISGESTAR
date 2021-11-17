import {Component, OnInit} from '@angular/core';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {finalize} from 'rxjs/operators';
import {HomeMesages} from './home-mesages';
import {UsuarioModel} from '../../shared-models/usuario-model';
import {ComentariosListModel  } from "../../shared-models/comentarioslist-model";
import {UsuarioService} from '../../shared-services/usuario-service';
import {Router} from '@angular/router';
import {ConfirmationService} from 'primeng/api';

import { ResponsavelService } from "../../shared-services/responsavel-service";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['home.component.scss']
})
export class HomeComponent implements OnInit {
    MSG = HomeMesages;
    usuarios: UsuarioModel[] = [];
    comentarios: ComentariosListModel[] = [];
    @BlockUI() blockUI: NgBlockUI;

    constructor(
        private usuarioService: UsuarioService,
        private responsavelService: ResponsavelService,
        private router: Router,
        private confirmationService: ConfirmationService,
    ) {
    }

    ngOnInit() {
        this.updateTable();
    }

    updateTable() {
        this.blockUI.start();

        this.usuarioService.findAll()
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe(usuarios => this.usuarios = usuarios);
    }

    confirmacaoDeletar(id: number) {
        this.confirmationService.confirm({
            header: 'Deseja deletar esse Usuario?',
            message: 'Essa alteração não poderar ser desfeita!',
            accept: () => this.deletarUsuario(id),
            acceptLabel: 'Sim',
            rejectLabel: 'Não'
        });
    }

    deletarUsuario(id: number) {
        this.blockUI.start();
        this.usuarioService.delete(id)
            .pipe(finalize(() => this.blockUI.stop()))
            .subscribe(() => this.updateTable());
    }


    editUser(id: number) {
        const extras = {
            queryParams: { id }
        };
        this.router.navigate(['../user',], extras);
    }
}
