import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BaseEntityService } from "../utils/base-entity-service";
import { TarefaModel } from "../shared-models/tarefa-model";
import { Observable } from "rxjs";
import { Page } from "../utils/page";

@Injectable({
    providedIn: 'root'
})
export class TarefaService extends BaseEntityService<TarefaModel, any> {

    getEntity(): string {
        return 'tarefas';
    }

    constructor(protected http: HttpClient) {
        super(http);
    }

    findByIdFake(): Observable<TarefaModel> {
        return this.http.get<TarefaModel>(this.resourceUrl + "/1");
    }


    findAllWithPages(): Observable<Page<TarefaModel>> {
        return this.http.get<Page<TarefaModel>>(this.resourceUrl + "?nome=");
    }

    findAllTarefasByResponsavelId(idResponsavel: number): Observable<Page<TarefaModel>> {
        return this.http.get<Page<TarefaModel>>(this.resourceUrl+"/" + idResponsavel);
    }

}
