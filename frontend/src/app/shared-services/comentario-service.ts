import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BaseEntityService } from "../utils/base-entity-service";
import { TarefaModel } from "../shared-models/tarefa-model";
import { Observable } from "rxjs";
import { Page } from "../utils/page";
import { ComentariosListModel } from "../shared-models/comentarioslist-model";

@Injectable({
    providedIn: 'root'
})
export class ComentarioService extends BaseEntityService<ComentariosListModel, any> {

    getEntity(): string {
        return 'comentarios';
    }

    constructor(protected http: HttpClient) {
        super(http);
    }

    findAllWithPages(): Observable<Page<TarefaModel>> {
        return this.http.get<Page<TarefaModel>>(this.resourceUrl + "?nome=");
    }

    findAllTarefasByResponsavelId(idResponsavel: number): Observable<Page<TarefaModel>> {
        return this.http.get<Page<TarefaModel>>(this.resourceUrl+"/" + idResponsavel);
    }

}
