import {Component, ContentChild} from '@angular/core';
import {InputRefDirective} from './input-ref.directive';
import {ValidationUtil} from '../../utils/validation-util';

@Component({
  selector: 'app-generic-input',
  templateUrl: './generic-input.component.html',
  styleUrls: ['./generic-input.component.css']
})
export class GenericInputComponent {
  @ContentChild(InputRefDirective) input: InputRefDirective;

  constructor() { }

  getValidationMessage(): string {
    return ValidationUtil.getErrorMessages(this.input.control);
  }
}
