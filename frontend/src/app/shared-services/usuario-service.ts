import {HttpClient, HttpHeaders, HttpClientModule} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {UsuarioModel} from '../shared-models/usuario-model';
import {ComentariosListModel} from '../shared-models/comentarioslist-model';
import { BaseEntityService } from '../utils/base-entity-service';
@Injectable({
    providedIn: 'root'
})
// export class UsuarioService {
export class UsuarioService extends BaseEntityService<UsuarioModel, any> {

    getEntity(): string {
        return 'usuarios';
    }

    constructor(protected http: HttpClient) {
        super(http);
    }


    insert(entity: UsuarioModel): Observable<UsuarioModel> {
        return this.http.post<UsuarioModel>(this.resourceUrl+"/", entity);
    }

    findById(id: number): Observable<UsuarioModel> {
        return this.http.get<UsuarioModel>(this.resourceUrl + '/' + id);
    }

    findAll(): Observable<UsuarioModel[]> {
        return this.http.get<UsuarioModel[]>(this.resourceUrl + '/');
    }

    update(entity: UsuarioModel): Observable<UsuarioModel> {
        return this.http.put<UsuarioModel>(this.resourceUrl, entity);
    }

    delete(id: number): Observable<void> {
        return this.http.delete<void>(this.resourceUrl + '/' + id);
    }

    findComentariosDoUsuario(idUsuario: number): Observable<UsuarioModel[]> {
        const test2 = this.http.get<UsuarioModel[]>(this.resourceUrl + '/' + idUsuario);
        console.log(this.http.get<UsuarioModel[]>(this.resourceUrl + '/' + idUsuario));
        return test2;
    }

    login(hash: string) {
        return this.http.get<UsuarioModel>(this.resourceUrl + '/obter-por-hash/' + hash);
    }

}
