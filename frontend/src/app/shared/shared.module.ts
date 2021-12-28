import { NgModule } from '@angular/core';
import { PRIMENG_IMPORTS } from './primeng-imports';
import {GenericFormComponent} from '../components/generic-form/generic-form.component';
import {GenericTableComponent} from '../components/generic-table/generic-table.component';
import {FormFooterButtonsDirective} from '../components/generic-form/form-footer-buttons.directive';
import {FormHeaderDirective} from '../components/generic-form/form-header.directive';
import {FormRefDirective} from '../components/generic-form/form-ref.directive';
import {InputRefDirective} from '../components/generic-input/input-ref.directive';
import {ConfirmationService} from 'primeng/api';
import {ModalService} from '../utils/modal.service';
import {DialogService} from 'primeng';
import {GenericInputComponent} from '../components/generic-input/generic-input.component';

@NgModule({
    declarations: [
        GenericFormComponent,
        GenericTableComponent,
        GenericInputComponent,
        FormFooterButtonsDirective,
        FormHeaderDirective,
        FormRefDirective,
        InputRefDirective
    ],
    imports: [
        PRIMENG_IMPORTS,
    ],
    providers: [ConfirmationService, ModalService, DialogService],
    exports: [
        PRIMENG_IMPORTS,
        GenericTableComponent,
        GenericFormComponent,
        InputRefDirective,
        FormHeaderDirective,
        FormRefDirective,
        FormFooterButtonsDirective,
        GenericInputComponent,
    ]
})
export class SharedModule { }
