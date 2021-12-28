import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PageNotificationService } from '@nuvem/primeng-components';
import {UsuarioService} from '../shared-services/usuario-service';
import {UsuarioModel} from '../shared-models/usuario-model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    form: FormGroup;
    submit = false;

    constructor(
    private fb: FormBuilder,
    private router: Router,
    private notification: PageNotificationService,
    private usuarioService: UsuarioService
    ) { }

    iniciarForm() {
        this.form = this.fb.group({
          hash: [null, [Validators.required]],
        });
    }

    ngOnInit(): void {
        this.iniciarForm();
    }

    login() {
        this.submit = true;
        this.usuarioService.login(this.form.controls.hash.value)
            .subscribe( usuario => this.navegarParaAdmin(usuario),
            erro => {
                this.submit = false;
              this.notification.addErrorMessage(erro.error.message);
              localStorage.clear();
            });
    }

    private navegarParaAdmin(usuario: UsuarioModel) {
        localStorage.setItem('hash', usuario.hash);
        localStorage.setItem('user', JSON.stringify(usuario));
        this.router.navigate(['../admin']);
  }

}
